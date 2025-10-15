package dao.impl;

import dao.BookDao;
import native_.pojo.Book;

import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 17:24
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?, ?, ?, ?, ?, ?);";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    /**
     * 只能依据id修改
     *
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?, `author` = ? , `price` = ? , `sales` = ? , `stock` = ? , `img_path` = ? where id = ?";//注意：如果不修改某个字段的值，应该怎么传参【核心是怎么传入原来的值】？传入null就把数据库中的字段值改为了null，存在数据安全风险
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
//        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book where id = ?";//同下
        String sql = "select * from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
//        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book";//同下
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";//count和(不能有空格
        Object o = queryForSingleValue(sql);//其他方法返回的都是数据库内数据的对象,所以要用这个方法//这里还有object类型转换integer类型的问题
        Integer count = Integer.parseInt(o.toString());
        return count;
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book limit ?, ?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";//count和(不能有空格
        Object o = queryForSingleValue(sql, min, max);//其他方法返回的都是数据库内数据的对象,所以要用这个方法//这里还有object类型转换integer类型的问题
        Integer count = Integer.parseInt(o.toString());
        return count;
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` from t_book where price between ? and ? order by price limit ?, ?";
        return queryForList(Book.class,sql,min, max, begin,pageSize);
    }
}
