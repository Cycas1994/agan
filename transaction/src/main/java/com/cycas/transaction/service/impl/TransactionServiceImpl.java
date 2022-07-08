package com.cycas.transaction.service.impl;

import com.cycas.transaction.dao.StudentDAO;
import com.cycas.transaction.pojo.Student;
import com.cycas.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void required() {
        Student student = new Student();
        student.setsId("2");
        student.setsName("2");
        studentDAO.insert(student);
        int i = 1/0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiredNew() {
        Student student = new Student();
        student.setsId("2");
        student.setsName("2");
        studentDAO.insert(student);
//        int i = 1/0;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
        Student student = new Student();
        student.setsId("2");
        student.setsName("2");
        studentDAO.insert(student);
        int i = 1/0;
    }

    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void notSupported() {

    }
}
