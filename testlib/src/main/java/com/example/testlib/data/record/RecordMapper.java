package com.example.testlib.data.record;

import com.example.testlib.po.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    List<Record> getRecordByAccount(@Param("account") String account);
    List<Record> getAllRecord();
    int createRecord(Record record);
    Record getRecordById(@Param("id") int id);
    int updateCondi(@Param("id") int id,@Param("condi") int condi,@Param("returnTime") String returnTime);
}
