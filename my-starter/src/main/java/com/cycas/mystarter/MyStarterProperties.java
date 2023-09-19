package com.cycas.mystarter;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author NaXin
 * @since 2023-08-01
 */
@ConfigurationProperties(prefix = "mystarter")
public class MyStarterProperties {

    private String message = "Hello from My Starter";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
