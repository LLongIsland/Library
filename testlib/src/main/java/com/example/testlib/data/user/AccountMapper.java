package com.example.testlib.data.user;

import com.example.testlib.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    User getAccountByName(@Param("email") String email);
    int createNewAccount(User user);
    int delAccount(@Param("email") String email);
}
