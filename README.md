read-write-sharding-jdbc
sharding-sphere
读写分离背景
面对日益增加的系统访问量，数据库的吞吐量面临着巨大瓶颈。 对于同一时刻有大量并发读操作和较少写操作类型的应用系统来说，将数据库拆分为主库和从库，主库负责处理事务性的增删改操作，
从库负责处理查询操作能够有效的避免由数据更新导致的行锁，使得整个系统的查询性能得到极大的改善。通过一主多从的配置方式，可以将查询请求均匀的分散到多个数据副本，能够进一步的提升系统的处理能力。 
使用多主多从的方式，不但能够提升系统的吞吐量，还能够提升系统的可用性，可以达到在任何一个数据库宕机，甚至磁盘物理损坏的情况下仍然不影响系统的正常运行。
与将数据根据分片键打散至各个数据节点的水平分片不同，读写分离则是根据SQL语义的分析，将读操作和写操作分别路由至主库与从库。
读写分离使用规范：
 支持项：
      提供一主多从的读写分离配置，可独立使用，也可配合分库分表使用；
      独立使用读写分离支持SQL透传；
      同一线程且同一数据库连接内，如有写入操作，以后的读操作均从主库读取，用于保证数据一致性；
      基于Hint的强制主库路由。
  不支持项：
      主库和从库的数据同步；
      主库和从库的数据同步延迟导致的数据不一致；
      主库双写或多写；
      跨主库和从库之间的事务的数据不一致。主从模型中，事务中读写均用主库。
  核心概念             
主库：添加、更新以及删除数据操作所使用的数据库，目前仅支持单主库。
从库：查询数据操作所使用的数据库，可支持多从库。
主从同步：将主库的数据异步的同步到从库的操作。由于主从同步的异步性，从库与主库的数据会短时间内不一致。
负载均衡策略：通过负载均衡策略将查询请求疏导至不同从库。 

开发步骤：
1）导入maven依赖  
` <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-web</artifactId>
             <version>2.4.5</version>
         </dependency>
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-test</artifactId>
             <version>2.4.5</version>
         </dependency>
         <dependency>
             <groupId>org.apache.shardingsphere</groupId>
             <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
             <version>4.0.0-RC1</version>
         </dependency>
         <dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
             <version>8.0.23</version>
         </dependency>
         <dependency>
             <groupId>com.baomidou</groupId>
             <artifactId>mybatis-plus-boot-starter</artifactId>
             <version>3.3.2</version>
         </dependency>
         <dependency>
             <groupId>com.alibaba</groupId>
             <artifactId>druid-spring-boot-starter</artifactId>
             <version>1.1.16</version>
         </dependency>`      
 2）配置文件
 `spring:
    main:
      allow-bean-definition-overriding: true
    shardingsphere:
      datasource:
        names:
          master,slave
        # 主数据源
        master:
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3307/test?characterEncoding=utf-8
          username: root
          password: root
        # 从数据源
        slave:
          type: com.alibaba.druid.pool.DruidDataSource
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3308/test?characterEncoding=utf-8
          username: root
          password: root
      masterslave:
        # 读写分离配置
        load-balance-algorithm-type: round_robin
        # 最终的数据源名称
        name: dataSource
        # 主库数据源名称
        master-data-source-name: master
        # 从库数据源名称列表，多个逗号分隔
        slave-data-source-names: slave
      props:
        # 开启SQL显示，默认false
        sql:
          show: true
  
  
    #load-balance-algorithm-type 用于配置从库负载均衡算法类型，可选值：ROUND_ROBIN(轮询)，RANDOM（随机）
  
    #props.sql.show=true 在执行SQL时，会打印SQL，并显示执行库的名称`  
