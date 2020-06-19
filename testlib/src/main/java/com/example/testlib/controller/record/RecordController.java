package com.example.testlib.controller.record;

import com.example.testlib.bl.record.RecordService;
import com.example.testlib.po.Record;
import com.example.testlib.vo.DisplayRecordVo;
import com.example.testlib.vo.RecordVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excise")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/getAllBorrowRecords")
    public ResponseVO getRecordsByAccount(@RequestParam String raccount){
        return ResponseVO.buildSuccess(recordService.getRecordByAccount(raccount));
    }
    @PostMapping("/borrow")
    public ResponseVO borrowBook(@RequestBody RecordVO recordVO){
        return recordService.createRecord(recordVO);
    }
    @GetMapping("/reback")
    public ResponseVO rebackBook(@RequestParam int id,@RequestParam String returnTime){
        return recordService.rebackBook(id,returnTime);
    }
}
