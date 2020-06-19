package com.example.testlib.controller.record;

import com.example.testlib.bl.record.RecordService;
import com.example.testlib.vo.RecordVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/reback")
    public ResponseVO rebackBook(@RequestParam int recordId){
        return recordService.rebackBook(recordId);
    }
}
