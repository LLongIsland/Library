package com.example.testlib.controller.book;

import com.example.testlib.bl.book.BookService;
import com.example.testlib.po.Book;
import com.example.testlib.vo.BookVO;
import com.example.testlib.vo.ResponseVO;
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


    @GetMapping("/removeAlbums")
    public ResponseVO delBook(@RequestParam Integer id){
        return bookService.delBook(id);
    }
    @PostMapping("/addSubAlbum")
    public ResponseVO addSubBook(@RequestBody BookVO BookVO){
        return bookService.updateBook(BookVO.getId(),BookVO.getNum());
    }
    @GetMapping("/getAllAlbums")
    public ResponseVO getAllSearchBooks(@RequestParam String title){
        List<Book> test=bookService.getAllSearchBooks(title);
        return ResponseVO.buildSuccess(test);
    }
}
