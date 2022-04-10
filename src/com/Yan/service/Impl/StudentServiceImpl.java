package com.Yan.service.Impl;

import com.Yan.dao.Impl.StudentDaoImpl;
import com.Yan.dao.StudentDao;
import com.Yan.pojo.PageBean;
import com.Yan.pojo.Student;
import com.Yan.service.StudentService;

import java.util.List;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:35
 * @Description: com.Yan.service.Impl
 * @version: 1.0
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    //分页数据封装的业务处理
    @Override
    public PageBean<Student> findByPage(int currentPage, int pageSize) {
        //查询出该页所有数据
        List<Student> students = studentDao.findBypaage(currentPage,pageSize);
        //查询出共有多少条数据
        int totalSize = studentDao.findTotalSize();
        //总页数
        int totalPage = totalSize%pageSize==0? totalSize/pageSize:totalSize/pageSize+1;
        //当前页
        //页大小
        PageBean<Student> pageBean = new PageBean<>(students, totalSize, totalPage, pageSize, currentPage);
        return pageBean;
    }
}
