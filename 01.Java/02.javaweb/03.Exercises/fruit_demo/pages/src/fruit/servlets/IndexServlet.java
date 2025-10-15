package fruit.servlets;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import myssm.myspringmvc.ViewBaseServlet;
import myssm.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = 1;
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        FruitDAO fruitDAO = new FruitDAOImpl();
//        List<Fruit> fruitList = fruitDAO.getFruitList();//不分页显示
        List<Fruit> fruitList = fruitDAO.getFruitListByPage(pageNo);//分页显示，显示第?页
        int fruitCount = fruitDAO.getFruitCount();//获取总结记录条数
        int totalPage = (fruitCount + 5 - 1) / 5;//总页数
        //保存到session作用域
        HttpSession session = request.getSession();
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);
        session.setAttribute("totalPage", totalPage);
        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index", request, response);
    }
}
