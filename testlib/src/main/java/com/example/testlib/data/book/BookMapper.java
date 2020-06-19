package com.example.testlib.data.book;

import com.example.testlib.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    int addBook(Book book);
    int updateNum(@Param("id") int id,@Param("num") int number);
    Book getBookById(@Param("id")int id);
    List<Book> getBooksByTitle(@Param("title") String title);
    List<Book>getAllBooks();
}
