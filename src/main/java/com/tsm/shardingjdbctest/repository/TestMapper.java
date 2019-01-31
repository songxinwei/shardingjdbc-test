package com.tsm.shardingjdbctest.repository;



import com.tsm.shardingjdbctest.domain.Age;
import com.tsm.shardingjdbctest.domain.Student;
import com.tsm.shardingjdbctest.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: 江千月
 * @Date: 2019/1/17 13:49
 * @Version 1.0
 */
@Mapper
public interface TestMapper {
    List<Map> selectAllStudent();
    List<Map> selectAllStudent1();
    int addOneStudent(Student student);

    int delOneStudent(String id);

    List<Map> selectAllUser();

    int addOneUser(User user);

    int delOneUser(String id);

    List<Map> selectAllUserSortBy();

//    List<Map> selectAllAge();
//
//    int addOneAge(Age age);
//
//    int delOneAge(String id);

}
