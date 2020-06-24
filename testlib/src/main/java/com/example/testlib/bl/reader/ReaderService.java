package com.example.testlib.bl.reader;

import com.example.testlib.po.Reader;
import com.example.testlib.vo.ReaderVO;
import com.example.testlib.vo.ResponseVO;

import java.util.List;

public interface ReaderService {
    List<Reader> getReaders(String account);
    ResponseVO addReader(ReaderVO readerVO);
    ResponseVO delReader(ReaderVO readerVO);
}
