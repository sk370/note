package servlet; /**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/5 8:39
 */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FileUploadServlet", value = "/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
//    String UPLOAD_DIRECTORY = "\u202AC:\\Users\\iceri\\Pictures";//如何保存在服务器的路径上？
//    private static final String UPLOAD_DIRECTORY = "C:\\Users\\iceri\\Pictures";//设置上传文件保存位置
//    private static final String UPLOAD_DIRECTORY = "upload";//设置上传文件保存位置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;//设置缓冲区大小3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;//设置上传文件最大限制40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;//没看懂设置啥50MB？——根据后文，意思是本次表单提交的数据总大小50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        判断是否是上传类型
        if(!ServletFileUpload.isMultipartContent(request)){
            PrintWriter writer = response.getWriter();
            writer.println("Error!表单属性必须包含 enctype=multipart/form-data。");
            writer.flush();
            return;
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();//设置文件上传对象
        diskFileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);//设置缓冲区大小
        diskFileItemFactory.setRepository(new File(System.getProperty("java.io.tmdir")));//设置临时存储目录 ？？？

        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);//创建文件上传处理器
        servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);//设置文件上传的最大值
        servletFileUpload.setSizeMax(MAX_REQUEST_SIZE);//设置表单上传的最大值

        servletFileUpload.setHeaderEncoding("UTF-8");//防止上传文件名乱码

//        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;//构建临时路径存储
//        System.out.println(getServletContext().getRealPath("/"));
//        System.out.println(File.separator);
//        File uploadDir = new File(uploadPath);
//        if(!uploadDir.exists()){
//            uploadDir.mkdir();
//        }

        List<FileItem> fileItems = null;
        try {
            fileItems = servletFileUpload.parseRequest(request);
            System.out.println(fileItems);
            if(fileItems != null && fileItems.size() > 0){
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()){//isFormField()判断是不是普通表单，取反表示判断是不是文件上传表单
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        System.out.println("文件名：" + fileItem.getName());
                    }else{
                        System.out.println("表单项的 name 属性值：" + fileItem.getFieldName());
                        System.out.println("表单项的 value 属性值：" + fileItem.getString("UTF-8"));
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
