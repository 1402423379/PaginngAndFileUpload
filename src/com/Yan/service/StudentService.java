package com.Yan.service;

import com.Yan.pojo.PageBean;
import com.Yan.pojo.Student;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:34
 * @Description: com.Yan.service
 * @version: 1.0
 */
public interface StudentService {
    PageBean<Student> findByPage(int currentPage, int pageSize);
}

