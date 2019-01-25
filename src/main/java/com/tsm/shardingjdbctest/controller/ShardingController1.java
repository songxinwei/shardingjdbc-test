//package com.tsm.shardingjdbctest.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.tsm.shardingjdbctest.domain.Student;
//import com.tsm.shardingjdbctest.domain.User;
//import com.tsm.shardingjdbctest.repository.TestMapper;
//import io.shardingsphere.api.HintManager;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author: 江千月
// * @Date: 2019/1/22 17:45
// * @Version 1.0
// * sharding-jdbc分库分表测试
// */
//@RestController
//@RequestMapping("user")
//public class ShardingController1 {
//    @Resource
//    private TestMapper testMapper;
//
//    @GetMapping("")
//    @Transactional
//    public String test() throws Exception{
//
//        List<Map> list=testMapper.selectAllUser();
//        System.out.println(JSON.toJSONString(list));
//        return JSON.toJSONString(list);
//    }
//
//
//    @GetMapping("/add")
//    @Transactional
//    public String test1(){
//        User user=new User();
//        user.setName("aaa");
//        int a=testMapper.addOneUser(user);
//        return JSON.toJSONString(a);
//    }
//
//    @GetMapping("/del")
//    public String test2(@RequestParam("id") String id){
//        int a=testMapper.delOneUser(id);
//        return JSON.toJSONString(a);
//    }
//
//
//}
