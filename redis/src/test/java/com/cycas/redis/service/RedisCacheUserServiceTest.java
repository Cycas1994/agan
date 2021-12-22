package com.cycas.redis.service;

import com.cycas.redis.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisCacheUserServiceTest {

    @Autowired
    private RedisCacheUserService service;

    @Autowired
    private ValueOperations<String, Object> redisString;

    @Test
    void selectUserById() {
        System.out.println("redis before use : " + redisString.get("user::ID0001"));
        System.out.println(service.selectUserById("ID0001"));
        System.out.println("redis after use : " + redisString.get("user::ID0001"));
        System.out.println();
    }

    @Test
    void selectUser() {
        System.out.println(redisString.get("user::list"));
        List<User> list = service.selectUser();
        System.out.println(list.size());
        System.out.println(redisString.get("user::list"));
    }

    @Test
    void selectUserByIdWithCondition() {

        User user1 = service.selectUserByIdWithCondition("ID0002", 19);
        System.out.println(user1);
        System.out.println("redis data[ID0002] : " + redisString.get("user::ID0002"));

        User user2 = service.selectUserByIdWithCondition("ID0003", 20);
        System.out.println(user2);
        System.out.println("redis data[ID0003]: " + redisString.get("user::ID0003"));
    }

    @Test
    void selectUserByIdWithUnless() {
        User user1 = service.selectUserByIdWithUnless("ID0004", 19);
        System.out.println(user1);
        System.out.println("redis data[ID0004] : " + redisString.get("user::ID0004"));

        User user2 = service.selectUserByIdWithUnless("ID0005", 20);
        System.out.println(user2);
        System.out.println("redis data[ID0005]: " + redisString.get("user::ID0005"));
    }

    @Test
    void insertUser() {
        User user = new User("10086", "insert_name", 11);
        service.insertUser(user);
        System.out.println(redisString.get("user::10086"));
    }

    @Test
    void insertUserWithCondition() {
        User user2 = new User("10088", "insert_name", 10);
        service.insertUserWithCondition(user2);
        System.out.println(redisString.get("user::10087"));
    }

    @Test
    void updateUser() {
        User user3 = new User("10086", "update_name", 12);
        service.updateUser(user3);
        System.out.println(redisString.get("user::10086"));
    }

    @Test
    void deleteUserById() {
        System.out.println(redisString.getOperations().keys("user::*"));
        service.deleteUserById("10086");
        System.out.println(redisString.getOperations().keys("user::*"));
    }

    @Test
    void deleteUserByIdAndCleanCache() {
        System.out.println(redisString.getOperations().keys("user::*"));
        service.deleteUserByIdAndCleanCache("10087");
        System.out.println(redisString.getOperations().keys("user::*"));
    }
}