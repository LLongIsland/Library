package com.example.testlib.blImpl.user;

import com.example.testlib.bl.user.AccountService;
import com.example.testlib.data.user.AccountMapper;
import com.example.testlib.po.User;
import com.example.testlib.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public User login(UserForm userForm) {
        User user=accountMapper.getAccountByName(userForm.getEmail());
        if(null==user||!user.getPassword().equals(userForm.getPassword()))
            return null;
        return user;
    }
}
