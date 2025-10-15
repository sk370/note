package servlet; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/4 22:04
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UploadServlet", value = "/uploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("上传成功~");
        ServletInputStream inputStream = request.getInputStream();

        byte[] buffer = new byte[102400];
        int read = inputStream.read(buffer);


    }

}
