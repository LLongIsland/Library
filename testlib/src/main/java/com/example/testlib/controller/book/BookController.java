package com.example.testlib.controller.book;

import com.example.testlib.bl.book.BookService;
import com.example.testlib.po.Book;
import com.example.testlib.vo.BookVO;
import com.example.testlib.vo.ResponseVO;
import com.example.testlib.vo.SubBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/excise")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addAlbum")
    public ResponseVO addBook(@RequestBody BookVO bookVO){
        return bookService.addBook(bookVO);
    }

    @PostMapping("/addSubAlbum")
    public ResponseVO addSubBook(@RequestBody SubBookVO subBookVO){
        return bookService.updateBook(subBookVO.getId(),subBookVO.getNumber());
    }
    @GetMapping("/getAllAlbums")
    public ResponseVO getAllSearchBooks(@RequestParam String title){
        List<Book> test=bookService.getAllSearchBooks(title);
        return ResponseVO.buildSuccess(test);
    }
}
