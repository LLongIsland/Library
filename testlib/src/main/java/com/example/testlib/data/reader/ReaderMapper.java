package com.example.testlib.data.reader;

import com.example.testlib.po.Reader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReaderMapper {
    List<Reader> getReadersByAccount(@Param("account") String account);
    List<Reader> getAllReaders();
    int createNewReader(Reader reader);
    int delReader(@Param("account") String account);
}
