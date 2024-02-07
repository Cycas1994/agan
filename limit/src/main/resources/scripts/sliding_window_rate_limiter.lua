--KEYS[1]: 限流key
--ARGV[1]: 当前时间窗口的开始时间
--ARGV[2]: 请求的时间戳(也作为score)
--ARGV[3]: 阈值

-- 1.移除时间窗口之前的数据
redis.call('zremrangeByScore', KEYS[1], 0, ARGV[1])

-- 2.统计当前元素数量
local res = redis.call('zcard', KEYS[1])

-- 3.是否超过阈值
if (res == nil) or (res < tonumber(ARGV[3])) then
    -- 4.保存请求的时间戳
    redis.call('zadd', KEYS[1], ARGV[2], ARGV[2])
    return 1
else
    return 0
end