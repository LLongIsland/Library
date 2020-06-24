package com.example.testlib.bl.user;

import com.example.testlib.po.User;
import com.example.testlib.vo.UserVO;
import com.example.testlib.vo.UserForm;
import com.example.testlib.vo.ResponseVO;

public interface AccountService {
    User login(UserForm userForm);
    ResponseVO addUser(String account);
    ResponseVO addUser(UserVO userVO);
    ResponseVO delUser(String account);
    ResponseVO delUser(UserVO userVO);
}
