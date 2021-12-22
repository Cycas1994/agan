一、SpringBoot 配置Redis
1.1 pom 引入spring-boot-starter-data-redis 包
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
1.2 properties配置文件配置redis信息
默认连接本地6379端口的redis服务，一般需要修改配置，例如：
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=20
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=10
# 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=1000

二、RedisTemplate<K,V>类的配置
Spring 封装了RedisTemplate<K,V>对象来操作redis。
2.1 Spring对RedisTemplate<K,V>类的默认配置(了解即可)
Spring在 org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration类下配置的两个RedisTemplate的Bean。
(1) RedisTemplate<Object, Object>
　　这个Bean使用JdkSerializationRedisSerializer进行序列化，即key, value需要实现Serializable接口，redis数据格式比较难懂。
(2) StringRedisTemplate，即RedisTemplate<String, String>
key和value都是String。当需要存储实体类时，需要先转为String，再存入Redis。一般转为Json格式的字符串，所以使用StringRedisTemplate，需要手动将实体类转为Json格式。如

ValueOperations<String, String> valueTemplate = stringTemplate.opsForValue();
Gson gson = new Gson();

valueTemplate.set("StringKey1", "hello spring boot redis, String Redis");
String value = valueTemplate.get("StringKey1");
System.out.println(value);

valueTemplate.set("StringKey2", gson.toJson(new Person("theName", 11)));
Person person = gson.fromJson(valueTemplate.get("StringKey2"), Person.class);
System.out.println(person);

2.2 配置一个RedisTemplate<String,Object>的Bean
Spring配置的两个RedisTemplate都不太方便使用，所以可以配置一个RedisTemplate<String,Object> 的Bean，key使用String即可(包括Redis Hash 的key)，value存取Redis时默认使用Json格式转换。如下
@Bean(name = "template")
public RedisTemplate<String, Object> template(RedisConnectionFactory factory) {
    // 创建RedisTemplate<String, Object>对象
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    // 配置连接工厂
    template.setConnectionFactory(factory);
    // 定义Jackson2JsonRedisSerializer序列化对象
    Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper om = new ObjectMapper();
    // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会报异常
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jacksonSeial.setObjectMapper(om);
    StringRedisSerializer stringSerial = new StringRedisSerializer();
    // redis key 序列化方式使用stringSerial
    template.setKeySerializer(stringSerial);
    // redis value 序列化方式使用jackson
    template.setValueSerializer(jacksonSeial);
    // redis hash key 序列化方式使用stringSerial
    template.setHashKeySerializer(stringSerial);
    // redis hash value 序列化方式使用jackson
    template.setHashValueSerializer(jacksonSeial);
    template.afterPropertiesSet();
    return template;
}

所以可以这样使用：
@Autowired
private RedisTemplate<String, Object> template;
public void test002() {   
    ValueOperations<String, Object> redisString = template.opsForValue();   
    // SET key value: 设置指定 key 的值   
    redisString.set("strKey1", "hello spring boot redis");   
    // GET key: 获取指定 key 的值   
    String value = (String) redisString.get("strKey1");   
    System.out.println(value);   
    redisString.set("strKey2", new User("ID10086", "theName", 11));   
    User user = (User) redisString.get("strKey2");   
    System.out.println(user);
}

