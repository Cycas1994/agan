package com.cycas.service.impl;

import com.cycas.service.ExampleService;
import org.springframework.stereotype.Service;

@Service("exampleServiceImpl")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public String sayHi(String str) {

        return str;
    }

}
