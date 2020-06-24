package com.example.testlib.bl.book;


import com.example.testlib.po.Book;
import com.example.testlib.vo.BookVO;
import com.example.testlib.vo.ResponseVO;

import java.util.List;

public interface BookService {
    ResponseVO addBook(BookVO bookVO);
    ResponseVO updateBook(int id,int number);
    ResponseVO delBook(BookVO bookVO);
    List<Book> getAllSearchBooks(String title);
    Book getBookById(Integer id);
}
