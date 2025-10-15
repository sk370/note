package iceriver.spring.dao;

import iceriver.spring.pojo.Book;

import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:35
 */
public interface BookDao {
    void add(Book book);

    void update(Book book);

    void delete(Integer id);

    int findCount();

    Book findOne(Integer id);

    List<Book> findAll();

    void batchAdd(List<Object[]> batchArgs);

    void batchUpdate(List<Object[]> batchArgs);

    void batchDelete(List<Object[]> batchArgs);
}
