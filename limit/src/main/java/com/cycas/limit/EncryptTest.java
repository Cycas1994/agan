package com.cycas.limit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author xin.na
 * @since 2024/2/4 20:17
 */
@Slf4j
public class EncryptTest {

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        // application.properties, jasypt.encryptor.password
        encryptor.setPassword("lzkjv1dwdb3kv5kyo5mv7gsuvqoggk");
        Pwd pwd = new Pwd();
        String jsonStr = JSON.toJSONString(pwd);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        log.info("jsonObject:{}", jsonObject);
        TreeMap<String, Object> map = new TreeMap<>(jsonObject);
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            String msg = "%s %s %s";
            String val = stringObjectEntry.getValue().toString();
            log.info("{} {} {}", stringObjectEntry.getKey(), val, encryptor.encrypt(val));
            System.out.println(String.format(msg, stringObjectEntry.getKey(), val, encryptor.encrypt(val)));
        }
    }

    static class Pwd {
        String mexico_bill_rw = "127.0.0.1";

        public String getMexico_bill_rw() {
            return mexico_bill_rw;
        }

        public void setMexico_bill_rw(String mexico_bill_rw) {
            this.mexico_bill_rw = mexico_bill_rw;
        }
    }
}
