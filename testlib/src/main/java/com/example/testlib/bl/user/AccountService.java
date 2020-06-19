package com.example.testlib.bl.user;

import com.example.testlib.po.User;
import com.example.testlib.vo.ResponseVO;
import com.example.testlib.vo.UserForm;
import com.example.testlib.vo.UserVO;

public interface AccountService {
    User login(UserForm userForm);
    ResponseVO addUser(String account);
    ResponseVO addUser(UserVO userVO);
}
