package fruit.controllers;

import fruit.dao.FruitDAO;
import fruit.dao.impl.FruitDAOImpl;
import fruit.pojo.Fruit;
import fruit.service.FruitService;
import fruit.service.impl.FruitServiceImpl;
import myssm.utils.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/22 13:13
 */
//@WebServlet("/fruit.do")//有了DispatcherServlet后，不需要了
public class FruitController{
    FruitService fruitService = null;

    private String index(String oper,String keywords,Integer pageNo,HttpServletRequest request){
        if(pageNo == null){
            pageNo = 1;
        }
        HttpSession session = request.getSession();
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){//判断是不是查询请求
            if(StringUtil.isEmpty(keywords)){
                keywords = "";
            }
            session.setAttribute("keywords", keywords);
        }else{
            Object keywordsObj = session.getAttribute("keywords");
            if(keywordsObj != null){
                keywords = (String)keywordsObj;
            }else{
                keywords = "";
            }
        }
        List<Fruit> fruitList = fruitService.getFruitListByPage(keywords, pageNo);//分页显示，显示第?页，带关键词查询
        int fruitCount = fruitService.getFruitCount(keywords);//获取总结记录条数
        int totalPage = (fruitCount + 5 - 1) / 5;//总页数
        //保存到session作用域
        session.setAttribute("pageNo", pageNo);
        session.setAttribute("fruitList", fruitList);
        session.setAttribute("totalPage",totalPage);
        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        fruitService.addFruit(new Fruit(0 , fname , price , fcount , remark));
        return "redirect:fruit.do";
    }

    private String delete(Integer fid) {
        if (fid != null) {
            fruitService.delFruitById(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitService.getFruitById(fid);
            req.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";
    }

    private String update(String fname, Integer price, Integer fcount,Integer fid,String remark) {
        fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }
}
