//package com.tsm.shardingjdbctest.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.tsm.shardingjdbctest.domain.Student;
//import com.tsm.shardingjdbctest.repository.TestMapper;
//import io.shardingsphere.api.HintManager;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
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
// * sharding-jdbc读写分离测试
// */
//@RestController
//public class ShardingController {
//    @Resource
//    private TestMapper testMapper;
//
//    @GetMapping("/read")
//    @Transactional
//    public String test() throws Exception{
//
//        for(int i=0;i<100;i++){
//            Runnable runnable = new Runnable()  {
//                @Override
//                public void run() {
//                    List<Map> list=testMapper.selectAllStudent();
//                    System.out.println(JSON.toJSONString(list));
//                }
//            };
//            Thread s=new Thread(runnable);
//            s.start();
//
//        }
//
//        // 强制路由主库(只要在读写分离的配置基础上，查询前加这一句，就会强制走主库了)
//        HintManager.getInstance().setMasterRouteOnly();
//
//        List<Map> list=testMapper.selectAllStudent();
//        System.out.println(JSON.toJSONString(list));
//        return JSON.toJSONString(list);
//    }
//
//
//    @GetMapping("/add")
//    @Transactional
//    public String test1(){
//        Student s=new Student();
//        s.setName("aaa");
//        s.setAge("24");
//        int a=testMapper.addOneStudent(s);
//        return JSON.toJSONString(a);
//    }
//
//    @GetMapping("/del")
//    public String test2(@RequestParam("id") String id){
//        int a=testMapper.delOneStudent(id);
//        return JSON.toJSONString(a);
//    }
//
//
//}
