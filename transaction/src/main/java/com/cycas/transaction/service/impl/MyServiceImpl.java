package com.cycas.transaction.service.impl;

import com.cycas.transaction.dao.StudentDAO;
import com.cycas.transaction.pojo.Student;
import com.cycas.transaction.service.MyService;
import com.cycas.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    @Override
    public void transactional() {
        Student student = new Student();
        student.setsId("1");
        student.setsName("1");
        studentDAO.insert(student);
        try {
            transactionService.nested();
        } catch (Exception e) {
            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
//        transactionService.nested();
    }
}
