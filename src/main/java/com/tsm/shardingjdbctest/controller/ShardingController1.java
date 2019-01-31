package com.tsm.shardingjdbctest.controller;

import com.alibaba.fastjson.JSON;
import com.tsm.shardingjdbctest.domain.User;
import com.tsm.shardingjdbctest.repository.TestMapper;
import io.shardingsphere.api.HintManager;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import io.shardingsphere.transaction.api.TransactionTypeHolder;
import io.shardingsphere.transaction.spi.xa.XATransactionManager;
import io.shardingsphere.transaction.xa.manager.AtomikosTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.List;
import java.util.Map;


/**
 * @Author: 江千月
 * @Date: 2019/1/22 17:45
 * @Version 1.0
 * sharding-jdbc读写分离+分库分表测试
 */
@RestController
@RequestMapping("user")
public class ShardingController1 {
    @Resource
    private TestMapper testMapper;
//    @Resource
//    private DataSource dataSource;

    @GetMapping("")
    @Transactional
    public String test() throws Exception{

        List<Map> list=testMapper.selectAllUser();
        System.out.println(JSON.toJSONString(list));
        return JSON.toJSONString(list);

    }


    @GetMapping("/add")
    @Transactional
    public String test1() throws Exception{
        User user=new User();
        user.setAge((int)(Math.random()*1000));
        user.setName("aaa");

        int a=testMapper.addOneUser(user);
        System.out.println(JSON.toJSONString(user));;
        return JSON.toJSONString(a);

    }

    /**
     * 删除测试
     * @param id
     * @return
     */
    @GetMapping("/del")
    public String test2(@RequestParam("id") String id){
        int a=testMapper.delOneUser(id);
        return JSON.toJSONString(a);
    }

    /**
     * 测试分库分表后的排序问题
     * @return
     * @throws Exception
     */
    @GetMapping("/sort")
    @Transactional
    public String test11() throws Exception{
        //由于未做mysql主从复制，所以强制走写库进行测试
        HintManager.getInstance().setMasterRouteOnly();

        List<Map> list=testMapper.selectAllUserSortBy();
        System.out.println(JSON.toJSONString(list));
        return JSON.toJSONString(list);

    }

    /**
     * test Transaction
     * @return
     * @throws Exception
     */
    @GetMapping("/back")
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public String test1111() throws Exception{

        User user1=new User();
        user1.setAge(24+(int)(Math.random()*60)*6);
        user1.setName("qq");
        System.out.println(JSON.toJSONString(testMapper.addOneUser(user1)));
//        if(true){
//            throw new RuntimeException("test error");
//        }
        User user2=new User();
        user2.setAge(25+(int)(Math.random()*60)*6);
        user2.setName("qq");
        System.out.println(JSON.toJSONString(testMapper.addOneUser(user2)));
        return "success";

    }

}
