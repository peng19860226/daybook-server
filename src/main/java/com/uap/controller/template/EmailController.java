package com.uap.controller.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uap.model.EmailEntity;
import com.uap.model.PageResult;
import com.uap.model.utils.ResponseWrapper;
import com.uap.service.EmailService;
import com.uap.utils.SecurityAuthenUtil;

import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/emails")
    public ResponseWrapper<PageResult> emailsList(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult pageResult = new PageResult();
        pageResult.setData(emailService.emailsList(pageSize, page * pageSize));
        pageResult.setTotalCount(emailService.emailsSize(pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @PostMapping("/email")
    public ResponseWrapper<EmailEntity> insertEntity(@RequestBody EmailEntity emailEntity) {
        int userid = SecurityAuthenUtil.getId();
        emailEntity.setUserid(userid);
        emailService.insertEntity(emailEntity);
        return new ResponseWrapper<>(emailEntity);
    }

    @DeleteMapping("/admin/emails")
    public ResponseWrapper<List<String>> deleteEmails(@RequestBody List<String> groupId) {
        emailService.deleteEmails(groupId);
        return new ResponseWrapper<>(groupId);
    }

}
