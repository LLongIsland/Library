package com.example.testlib.blImpl.user;

import com.example.testlib.vo.ResponseVO;
import com.example.testlib.bl.user.AccountService;
import com.example.testlib.data.user.AccountMapper;
import com.example.testlib.po.User;
import com.example.testlib.vo.UserVO;
import com.example.testlib.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public User login(UserForm userForm) {
        User user=accountMapper.getAccountByName(userForm.getEmail());
        if(null==user||!user.getPassword().equals(userForm.getPassword()))
            return null;
        return user;
    }
    @Override
    public ResponseVO addUser(String account) {
        UserVO userVO=new UserVO();
        userVO.setEmail(account+"@smail.nju.edu.cn");
        userVO.setPassword("123456");
        userVO.setName(account);
        userVO.setAccount(account);
        userVO.setCredit(1);
        userVO.setCondi(0);
        return addUser(userVO);
    }
    @Override
    public ResponseVO addUser(UserVO userVO) {
        User user=new User();
        BeanUtils.copyProperties(userVO,user);
        if(accountMapper.getAccountByName(user.getEmail())!=null)
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        accountMapper.createNewAccount(user);
        return ResponseVO.buildSuccess();
    }
}
