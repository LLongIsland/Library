package com.example.testlib.bl.user;

import com.example.testlib.po.User;
import com.example.testlib.vo.UserForm;

public interface AccountService {
    User login(UserForm userForm);
}
