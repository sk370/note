package native_.pojo;
import java.util.List;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/6 15:55
 */

/**
 * 使用泛型,使其不仅能对图书分页,也能对用户\购物车等进行分页
 * @param <T>
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页码
    private Integer pageSize = PAGE_SIZE;//当前页显示数量
    private Integer pageTotalCount;//总记录数（对象）
    private List<T> items;//当前页数据
    private String url;//根据不同页面的路径显示分页效果

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

//      数据有效边界检查
        if(pageNo < 1){
            pageNo =1;
        }
        if(pageNo >pageTotal){
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", getPageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

}
