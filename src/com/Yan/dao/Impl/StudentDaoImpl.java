package com.Yan.dao.Impl;

import com.Yan.dao.BaseDao;
import com.Yan.dao.StudentDao;
import com.Yan.pojo.Student;

import java.util.List;

/**
 * @Auther: Yan
 * @Date: 2022/4/9 - 04 - 09 - 10:32
 * @Description: com.Yan.dao
 * @version: 1.0
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public List<Student> findBypaage(int currentPage, int pageSize) {
        String sql= "select * from student limit ?,?;";
        List list = BaseQuary(Student.class, sql, (currentPage - 1) * pageSize, pageSize);
        return list;
    }

    @Override
    public int findTotalSize() {
        String sql= "select count(1) from student;";
        int totalSize = BaseQuaryint(sql);
        return totalSize;
    }
}
