package com.example.testlib.blImpl.book;

import com.example.testlib.bl.book.BookService;
import com.example.testlib.data.book.BookMapper;
import com.example.testlib.po.Book;
import com.example.testlib.vo.BookVO;
import com.example.testlib.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final static String BOOK_EXIST = "图书已存在";
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseVO addBook(BookVO bookVO) {
        Book book=new Book();
        BeanUtils.copyProperties(bookVO,book);
        book.setNum(1);
        try{
            bookMapper.addBook(book);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(BOOK_EXIST);
        }
        return ResponseVO.buildSuccess();
    }


    @Override
    public ResponseVO updateBook(int id, int number) {
        Book book=bookMapper.getBookById(id);
        bookMapper.updateNum(id,book.getNum()+number);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Book> getAllSearchBooks(String title) {
        if(title.equals(""))
            return bookMapper.getAllBooks();
        return bookMapper.getBooksByTitle(title);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }
}
