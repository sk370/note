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
        request.setCharacterEncoding("UTF-8");
        int pageNo = 1;
        HttpSession session = request.getSession();

        String oper = request.getParameter("oper");
        String keywords = null;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){//判断是查询请求，还是翻页请求
            pageNo = 1;
            keywords = request.getParameter("keyword");
            if(StringUtil.isEmpty(keywords)){
                keywords = "";
            }
            session.setAttribute("keywords", keywords);
        }else{
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordsObj = session.getAttribute("keywords");
            if(keywordsObj != null){
                keywords = (String)keywordsObj;
            }else{
                keywords = "";
            }
        }


        FruitDAO fruitDAO = new FruitDAOImpl();
//        List<Fruit> fruitList = fruitDAO.getFruitList();//不分页显示
//        List<Fruit> fruitList = fruitDAO.getFruitListByPage(pageNo);//分页显示，显示第?页，不带关键词查询
        List<Fruit> fruitList = fruitDAO.getFruitListByPage(keywords, pageNo);//分页显示，显示第?页，不带关键词查询
//        int fruitCount = fruitDAO.getFruitCount();//获取总结记录条数，不带关键词查询
        int fruitCount = fruitDAO.getFruitCount(keywords);//获取总结记录条数，不带关键词查询
        int totalPage = (fruitCount + 5 - 1) / 5;//总页数
        //保存到session作用域
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);
        session.setAttribute("totalPage",totalPage);
        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
