package com.example.testlib.blImpl.reader;

import com.example.testlib.bl.reader.ReaderService;
import com.example.testlib.data.reader.ReaderMapper;
import com.example.testlib.po.Reader;
import com.example.testlib.vo.ReaderVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final static String ACCOUNT_EXIST = "学号已存在";
    @Autowired
    private ReaderMapper readerMapper;

    @Override
    public List<Reader> getReaders(String account) {
        if(account.equals(""))
            return readerMapper.getAllReaders();
        return readerMapper.getReadersByAccount(account);
    }

    @Override
    public ResponseVO addReader(ReaderVO readerVO) {
        Reader reader=new Reader();
        BeanUtils.copyProperties(readerVO,reader);
        if(readerMapper.getReadersByAccount(reader.getAccount())!=null)
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        readerMapper.createNewReader(reader);
        return ResponseVO.buildSuccess();
    }
}
