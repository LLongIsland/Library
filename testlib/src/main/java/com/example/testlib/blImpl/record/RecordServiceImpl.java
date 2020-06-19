package com.example.testlib.blImpl.record;

import com.example.testlib.bl.book.BookService;
import com.example.testlib.bl.record.RecordService;
import com.example.testlib.data.record.RecordMapper;
import com.example.testlib.po.Book;
import com.example.testlib.po.Record;
import com.example.testlib.vo.RecordVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final static String NUM_ERROR = "数量不足";
    @Autowired
    private RecordMapper recordMapper;
    private BookService bookService;
    @Override
    public List<Record> getRecordByAccount(String account) {
        return recordMapper.getRecordByAccount(account);
    }

    @Override
    public ResponseVO createRecord(RecordVO recordVO) {
        Record record=new Record();
        BeanUtils.copyProperties(recordVO,record);
        record.setCondi(0);
        Book book=bookService.getBookById(record.getAid());
        if(book.getNum()<=0)
            return ResponseVO.buildFailure(NUM_ERROR);
        try{
            recordMapper.createRecord(record);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        bookService.updateBook(book.getId(),book.getNum()-1);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO rebackBook(int recordId) {
        Book book=bookService.getBookById(recordMapper.getRecordById(recordId).getAid());
        bookService.updateBook(book.getId(),book.getNum()+1);
        recordMapper.updateCondi(recordId,1);
        return ResponseVO.buildSuccess(true);
    }
}
