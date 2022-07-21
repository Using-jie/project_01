package com.example.ssmdemo01.controller;

import com.example.ssmdemo01.domain.Book;
import com.example.ssmdemo01.exception.BusinessException;
import com.example.ssmdemo01.exception.SystemException;
import com.example.ssmdemo01.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YongJ
 * @date 2022/6/15 11:17
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag= bookService.save(book);
        return new Result(flag ? Code.SAVEOK:Code.SAVEERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag= bookService.update(book);
        return new Result(flag ? Code.UPDATEOK:Code.UPDATEERR,flag);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag= bookService.delete(id);
        return new Result(flag ? Code.DELEDEOK:Code.DELEDEERR,flag);

    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
//        //模拟业务异常，包装成自定义异常
//        if(id==1){
//            throw new BusinessException("有点儿凶哟！！",Code.BUSINESS_ERR);
//        }
//        //模拟系统异常，将可能出现的异常进行包装，转换成自定义异常
//        try{
//            int i=1/0;
//        }catch (Exception e){
//            throw new SystemException("服务器访问超时，请重试",e,Code.SYSTEM_TIMEOUT_ERR);
//        }
        Book book = bookService.getById(id);
        Integer code = book!=null ? Code.GETOK:Code.GETERR;
        String msg = book!=null ? "":"数据查询失败， 请重试！";
        return new Result(code,book,msg);

    }

    @GetMapping
    public Result getAll() {
        List<Book> booklist = bookService.getAll();
        Integer code = booklist!=null ? Code.GETOK:Code.GETERR;
        String msg = booklist!=null ? "":"数据查询失败， 请重试！";
        return new Result(code,booklist,msg);

    }

}
