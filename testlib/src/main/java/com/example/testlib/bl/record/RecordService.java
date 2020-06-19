package com.example.testlib.bl.record;

import com.example.testlib.po.Record;
import com.example.testlib.vo.RecordVO;
import com.example.testlib.vo.ResponseVO;

import java.util.List;

public interface RecordService {
    List<Record> getRecordByAccount(String account);
    ResponseVO createRecord(RecordVO recordVO);
    ResponseVO rebackBook(int recordId);
}
