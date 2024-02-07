--KEYS[1]: 限流 key
--ARGV[1]: 阈值
--ARGV[2]: 时间窗口，计数器的过期时间
local rateLimitKey = KEYS[1];
local rate = tonumber(ARGV[1]);
local rateInterval = tonumber(ARGV[2]);

local allowed = 1;
local ttlResult = 0;
-- 每次调用, 计数器rateLimitKey的值都会加1
local currValue = redis.call('incr', rateLimitKey);

if (currValue == 1) then
    -- 初次调用, 通过给计数器rateLimitKey设置过期时间rateInterval达到固定时间窗口的目的
    redis.call('expire', rateLimitKey, rateInterval);
else
    -- 当计数器的值(固定时间窗口内)大于阈值时, 返回0, 不允许访问
    if (currValue > rate) then
        allowed = 0;
        ttlResult = redis.call('ttl', rateLimitKey);
    end
end
return { allowed, ttlResult }