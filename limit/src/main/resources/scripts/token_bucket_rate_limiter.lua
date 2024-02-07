--KEYS[1]: 令牌桶剩余令牌数 key
--KEYS[2]: 令牌桶最后填充令牌时间 key

--ARGV[1]: 令牌桶每秒生成令牌数量
--ARGV[2]: 令牌桶容量
--ARGV[3]: 当前时间戳 单位秒
--ARGV[4]: 请求的令牌数

local tokens_key = KEYS[1]
local timestamp_key = KEYS[2]

local rate = tonumber(ARGV[1])
local capacity = tonumber(ARGV[2])
local now = tonumber(ARGV[3])
local requested = tonumber(ARGV[4])

-- 令牌桶填满所需时间
local fill_time = capacity / rate
-- 保证时间充足
local ttl = math.floor(fill_time * 2)

redis.log(redis.LOG_WARNING, "rate " .. ARGV[1])
redis.log(redis.LOG_WARNING, "capacity " .. ARGV[2])
redis.log(redis.LOG_WARNING, "now " .. ARGV[3])
redis.log(redis.LOG_WARNING, "requested " .. ARGV[4])
redis.log(redis.LOG_WARNING, "filltime " .. fill_time)
redis.log(redis.LOG_WARNING, "ttl " .. ttl)

-- 剩余令牌数
local last_tokens = tonumber(redis.call('get', tokens_key))
-- 第一次时, 没有值, 所以桶是满的
if last_tokens == nil then
    last_tokens = capacity
end

-- 上次刷新令牌时间
local last_refreshed = tonumber(redis.call('get', timestamp_key))
if last_refreshed == nil then
    last_refreshed = 0
end

-- 距离上次令牌刷新的时间间隔
local delta = math.max(0, now - last_refreshed)
-- 填充令牌
local filled_tokens = math.min(capacity, last_tokens + (delta * rate))

local allowed = filled_tokens >= requested
local new_tokens = filled_tokens
local allowed_num = 0
if allowed then
    -- 令牌数大于请求数
    new_tokens = filled_tokens - requested;
    allowed_num = 1
end

-- 设置令牌桶令牌数
redis.call('setex', tokens_key, ttl, new_tokens)
redis.call('setex', timestamp_key, ttl, now)

return { allowed_num, new_tokens }