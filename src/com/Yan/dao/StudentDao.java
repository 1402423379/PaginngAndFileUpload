package com.Yan.dao;

import com.Yan.pojo.Student;

import java.util.List;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:32
 * @Description: com.Yan.dao
 * @version: 1.0
 */
public interface StudentDao {
    List<Student> findBypaage(int currentPage, int pageSize);

    int findTotalSize();
}
