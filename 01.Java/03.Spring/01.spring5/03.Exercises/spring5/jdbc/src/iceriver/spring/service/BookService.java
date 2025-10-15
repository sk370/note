package iceriver.spring.service;

import iceriver.spring.dao.BookDao;
import iceriver.spring.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:29
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book){
        bookDao.add(book);
    }
    public void updateBook(Book book){
        bookDao.update(book);
    }
    public void deleteBook(Integer id){
        bookDao.delete(id);
    }
    public int findCount(){
        return bookDao.findCount();
    }
    public Book findOne(Integer id){
        return bookDao.findOne(id);
    }
    public List<Book> findAll(){
        return bookDao.findAll();
    }
    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAdd(batchArgs);
    }
    public void batchUpdate(List<Object[]> batchArgs){
        bookDao.batchUpdate(batchArgs);
    }
    public void batchDelete(List<Object[]> batchArgs){
        bookDao.batchDelete(batchArgs);
    }
}
