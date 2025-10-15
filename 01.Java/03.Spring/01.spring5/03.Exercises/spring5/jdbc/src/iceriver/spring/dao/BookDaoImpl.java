package iceriver.spring.dao;

import iceriver.spring.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/16 16:35
 */

@Repository
public class BookDaoImpl implements BookDao{
//    注入jdbc
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into t_book values(?,?,?)";
        int update = jdbcTemplate.update(sql, book.getUserId(), book.getUserName(), book.getUstatus());
        System.out.println(update);
    }

    @Override
    public void update(Book book) {
        String sql = "update t_book set username=?,ustatus=? where user_id = ?";
        int update = jdbcTemplate.update(sql, book.getUserName(), book.getUstatus(),book.getUserId());
        System.out.println(update);
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from t_book where user_id=?";
        int update = jdbcTemplate.update(sql, id);
        System.out.println(update);
    }

    @Override
    public int findCount() {
        String sql = "select count(*) from t_book ";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
        return count;
    }

    @Override
    public Book findOne(Integer id) {
        String sql = "select * from t_book where user_id = ?";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
        System.out.println(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        String sql = "select * from t_book";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        System.out.println(bookList);
        return bookList;
    }

    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        String sql = "insert into t_book values(?,?,?)";
        int[] add = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(add));
    }

    @Override
    public void batchUpdate(List<Object[]> batchArgs) {
        String sql = "update t_book set username=?,ustatus=? where user_id = ?";
        int[] update = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(update));
    }

    @Override
    public void batchDelete(List<Object[]> batchArgs) {
        String sql = "delete from t_book where user_id=?";
        int[] add = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(add));
    }
}
