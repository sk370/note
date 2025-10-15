package servlet; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/4 14:48
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import native_.pojo.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchStudentServlet", value = "/searchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 模拟查询到的数据
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            students.add(new Student(i + 1, "name" + i, 18 + i, "phone" + i));
        }
//        2.将查询到的结果返回给客户端——请求转发
        request.setAttribute("students", students);
        request.getRequestDispatcher("/test/showStudent.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
