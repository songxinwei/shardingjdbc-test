# shardingjdbc-test
springboot2+shardingjdbc3+mybatis demo

## 请教下，这个demo哪里写错了
## Can you tell me,what's wrong with my demo?

# 如果直接启动项目，会发生如下错误
Cannot find data source in sharding rule, invalid actual data node is: 'mydb1.user1'
这个错误经过本人的断点测试，是因为配置文件里数据库名称和节点使用的数据库名称对不上造成的（此时，项目可以直接启动，正常进入DataSource初始化）

# 修改配置文件
将ds_master修改为mydb1，将ds_slave修改为mydb2，让节点名称对应起来
报错如下
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
Reason: Failed to determine a suitable driver class

这是因为springboot配置多数据源初始化失败？
那为什么不改上面配置前是成功的呢？
读写分离模式是正常的呢？
