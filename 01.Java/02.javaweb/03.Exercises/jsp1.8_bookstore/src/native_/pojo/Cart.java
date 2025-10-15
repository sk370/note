package native_.pojo;

import dao.BookDao;
import dao.impl.BookDaoImpl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车界面
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/8 17:21
 */
public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<>();//key是商品编号，value是商品信息

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }


    /**
     * 添加商品项——添加到购物车里
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());//参数carItem是新添加的数据，添加之前，先检查是否在购物车中已有此类物品，如果有，则改变数量和价格，没有则添加新物品
        if(item == null){//之前没添加过
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount() + 1);//数量加1
            item.setStock(item.getStock() - 1);//数量加1
//            item.setTotalPrice(item.getPrice() * item.getCount());//item.getPrice()是bigdecimal类型，而item.getCount()是integer类型，二者无法直接使用 * ，需要使用数学方法
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//这里item.getCount已经是更新过了的
        }
    }

    /**
     * 修改购物车内的商品数量
     * @param id 修改的哪一条
     * @param count 修改后的数量
     */
    public void updateItem(Integer id, Integer count) {

        CartItem item = items.get(id);
        Integer oldCount = item.getCount();

        BookDao bookDao = new BookDaoImpl();
        Book book = bookDao.queryBookById(id);
        Integer nowCount = book.getStock();
        if(count > oldCount){
            bookDao.updateBook(new Book(id, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock()- (count - oldCount), book.getImgPath()));
        }else{
            bookDao.updateBook(new Book(id, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock()+ (count - oldCount), book.getImgPath()));
        }

        if (item != null) {
            item.setCount(count);//修改数量
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品巡项，将该条数据都删除
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 购物车商品总数量
     * @return
     */
    public Integer getTotalCount() {
        Integer totaLCount = 0;
        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totaLCount += entry.getValue().getCount();
        }
        return totaLCount;
    }

    /**
     * 购物车的商品总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer, CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totaLCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

}
