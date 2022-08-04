package com.cycas.commonutil.mq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;

import java.util.Properties;

public class RocketMqManualSendMsg {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // AccessKeyId 阿里云身份验证，在阿里云用户信息管理控制台创建。
        properties.put(PropertyKeyConst.AccessKey, "XXXXXXXX");
        // AccessKeySecret 阿里云身份验证，在阿里云用户信息管理控制台创建。
        properties.put(PropertyKeyConst.SecretKey, "XXXXXXXX");
        //设置发送超时时间，单位：毫秒。
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
        // 设置TCP接入域名，进入消息队列RocketMQ版控制台的实例详情页面的接入点区域查看。
        properties.put(PropertyKeyConst.NAMESRV_ADDR, "http://aaaaaaaaaaaaaaaaa.mq-internet-access.mq-internet.aliyuncs.com:80");

        Producer producer = ONSFactory.createProducer(properties);
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();
        // TODO 消息体
        String str = "{\"amount\":1530339,\"appid\":\"msp-order-k12\",\"body\":\"高一英语大班课程1(21秋下),高一化学大班课程1(21秋下【7f8b369c4a8e4d78ab48c70dea4b0e44】\",\"channel\":\"hui_fu_wx_jsapi\",\"channelApplyId\":\"6666000110058351\",\"clientIp\":\"117.135.80.220\",\"confirmSettleFlag\":0,\"createTime\":1635493414112,\"credential\":\"{\\\"timeStamp\\\":\\\"1635493414\\\",\\\"package\\\":\\\"prepay_id=wx29154334011794b0c2b5e0b2bf7ee20000\\\",\\\"paySign\\\":\\\"R18WjC6Eo6fpMUjjr+KYW2TTZZvEQhdqFbOus4enEh8rmlaj8l8ROnNAlQMm6pYM5AvbSsth0VawNwPZV0KuF767Ozhp5PcfXaL+9jWywN+EM0tPnt5TLvyofDDeKgG9Z+RQNEHFKK+6Mm8mCVK6Z3U4cLPRO0FnJOZPcAS3jg3EiakzQQ/wH7L9XzIFXuQhdtcWKxOrLMJOF44+ahkTSklARs8kxnXkK1VQDjlxxVbpJTqySuaOSbBg+gukfdivi0feAJ1B3SKDf9xNhlnnxPjePI/s2K76yZj9fC0CAZT5pAemd/bbC3q1WdbZowkv6261daabWvhpNx+i0hqE5Q==\\\",\\\"appId\\\":\\\"wx4af8f6640561083a\\\",\\\"signType\\\":\\\"RSA\\\",\\\"nonceStr\\\":\\\"R2I2FcXzAT2l2r5QtFtwHaaj6Gc5yEl6\\\"}\",\"extra\":{\"deviceType\":\"UNIVERSALQR\",\"businessSceneType\":\"mspPay\"},\"failureCode\":\"200\",\"failureMsg\":\"ok\",\"id\":\"617ba626ca8d4e229c3027a2\",\"merchantCode\":\"1000130145672101033\",\"mode\":\"online\",\"orderNo\":\"7f8b369c4a8e4d78ab48c70dea4b0e44\",\"paid\":true,\"payChannelRequestNo\":\"1453990877272809534\",\"recipient\":false,\"refunded\":false,\"reversed\":false,\"serialNo\":1453990877247647761,\"status\":3,\"subject\":\"高一英语大班课程1(21秋下)\",\"timeExpire\":1635497013471,\"timePaid\":1635493427000,\"transactionNo\":\"002900TOP3B211029154333P711ac13274600000\",\"transactionTime\":1635493413638}";

        Message msg = new Message(
                "xxxxxx", // topic
                "xxxxxx", // tags
                str.getBytes());

        // 设置代表消息的业务关键属性，请尽可能全局唯一。
        // 以方便您在无法正常收到消息情况下，可通过消息队列RocketMQ版控制台查询消息并补发。
        // 注意：不设置也不会影响消息正常收发。
        msg.setKey("ORDERID_test001");

        // 由于在oneway方式发送消息时没有请求应答处理，如果出现消息发送失败，则会因为没有重试而导致数据丢失。若数据不可丢，建议选用可靠同步或可靠异步发送方式。
        producer.sendOneway(msg);

        // 在应用退出前，销毁Producer对象。
        // 注意：如果不销毁也没有问题。
        producer.shutdown();
        System.out.println("11111111111111111111");
    }
}
