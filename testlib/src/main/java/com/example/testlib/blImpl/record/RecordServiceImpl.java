package com.example.testlib.blImpl.record;

import com.example.testlib.bl.book.BookService;
import com.example.testlib.bl.reader.ReaderService;
import com.example.testlib.bl.record.RecordService;
import com.example.testlib.data.record.RecordMapper;
import com.example.testlib.po.Book;
import com.example.testlib.po.Record;
import com.example.testlib.vo.RecordVO;
import com.example.testlib.vo.DisplayRecordVo;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    private final static String NUM_ERROR = "数量不足";
    private final static String CREATE_ERROR = "创建记录失败";
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;

    @Override
    public List<DisplayRecordVo> getRecordByAccount(String account) {
        List<Record> records;
        if (account.equals("")) {
            records = recordMapper.getAllRecord();
        } else {
            records = recordMapper.getRecordByAccount(account);
        }
        List<DisplayRecordVo> displayRecordVos = new ArrayList<DisplayRecordVo>();
        for (int i = 0; i < records.size(); i++) {
            DisplayRecordVo displayRecordVo = new DisplayRecordVo();
            BeanUtils.copyProperties(records.get(i), displayRecordVo);
            displayRecordVo.setAtitle(bookService.getBookById(displayRecordVo.getAid()).getTitle());
            String temp=displayRecordVo.getRaccount();
            if(temp!=null) {
                if(readerService.getReaders(temp).size()!=0)
                    displayRecordVo.setRname(readerService.getReaders(temp).get(0).getName());
            }
            displayRecordVos.add(displayRecordVo);
        }
        return displayRecordVos;
    }

    @Override
    public ResponseVO createRecord(RecordVO recordVO) {
        Record record = new Record();
        BeanUtils.copyProperties(recordVO, record);
        record.setCondi(0);
        Book book = bookService.getBookById(record.getAid());
        if (book.getNum() <= 0)
            return ResponseVO.buildFailure(NUM_ERROR);
        try {
            recordMapper.createRecord(record);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(CREATE_ERROR);
        }
        bookService.updateBook(book.getId(), -1);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO rebackBook(int recordID, String returnTime) {
        Book book = bookService.getBookById(recordMapper.getRecordById(recordID).getAid());
        bookService.updateBook(book.getId(), 1);
        recordMapper.updateCondi(recordID, 1, returnTime);
        return ResponseVO.buildSuccess(true);
    }
}
