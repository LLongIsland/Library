package com.example.testlib.controller.reader;

import com.example.testlib.bl.reader.ReaderService;
import com.example.testlib.vo.ReaderVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/excise")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/getAllReaders")
    public ResponseVO getReaders(@RequestParam String account){
        return ResponseVO.buildSuccess(readerService.getReaders(account));
    }
    @PostMapping("/addReader")
    public ResponseVO addReader(@RequestBody ReaderVO readerVO){
        return readerService.addReader(readerVO);
    }
    @GetMapping("/removeReaders")
    public ResponseVO delReader(@RequestParam Integer index){

        return readerService.delReader(index);
    }
}
