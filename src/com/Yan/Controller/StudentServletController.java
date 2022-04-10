package com.Yan.Controller;

import com.Yan.pojo.PageBean;
import com.Yan.pojo.Student;
import com.Yan.service.Impl.StudentServiceImpl;
import com.Yan.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:33
 * @Description: com.Yan.Controller
 * @version: 1.0
 */
@WebServlet(urlPatterns = "/StudentServletController.do")
public class StudentServletController extends HttpServlet {
    StudentService StudentService = new StudentServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收数据
        //页码数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        //页大小
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        //调用service层服务处理业务逻辑
        PageBean<Student> pageBean = StudentService.findByPage(currentPage, pageSize);
        //将数据放入请求域
        req.setAttribute("pageBean",pageBean);
        //响应数据，页面跳转
        req.getRequestDispatcher("showStudent.jsp").forward(req,resp);
    }
}
