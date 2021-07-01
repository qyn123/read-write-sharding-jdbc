# read-write-sharding-jdbc
#sharding-sphere
#读写分离背景
面对日益增加的系统访问量，数据库的吞吐量面临着巨大瓶颈。 对于同一时刻有大量并发读操作和较少写操作类型的应用系统来说，将数据库拆分为主库和从库，主库负责处理事务性的增删改操作，
从库负责处理查询操作能够有效的避免由数据更新导致的行锁，使得整个系统的查询性能得到极大的改善。通过一主多从的配置方式，可以将查询请求均匀的分散到多个数据副本，能够进一步的提升系统的处理能力。 
使用多主多从的方式，不但能够提升系统的吞吐量，还能够提升系统的可用性，可以达到在任何一个数据库宕机，甚至磁盘物理损坏的情况下仍然不影响系统的正常运行。
与将数据根据分片键打散至各个数据节点的水平分片不同，读写分离则是根据SQL语义的分析，将读操作和写操作分别路由至主库与从库。
#读写分离使用规范：
  #支持项：
      提供一主多从的读写分离配置，可独立使用，也可配合分库分表使用；
      独立使用读写分离支持SQL透传；
      同一线程且同一数据库连接内，如有写入操作，以后的读操作均从主库读取，用于保证数据一致性；
      基于Hint的强制主库路由。
  #不支持项：
      主库和从库的数据同步；
      主库和从库的数据同步延迟导致的数据不一致；
      主库双写或多写；
      跨主库和从库之间的事务的数据不一致。主从模型中，事务中读写均用主库。
#核心概念             
主库：添加、更新以及删除数据操作所使用的数据库，目前仅支持单主库。
从库：查询数据操作所使用的数据库，可支持多从库。
主从同步：将主库的数据异步的同步到从库的操作。由于主从同步的异步性，从库与主库的数据会短时间内不一致。
负载均衡策略：通过负载均衡策略将查询请求疏导至不同从库。 

#开发步骤：
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
     
#split-database-table-master   
#分库分表
 #背景
传统的将数据集中存储至单一数据节点的解决方案，在性能、可用性和运维成本这三方面已经难于满足互联网的海量数据场景。
从性能方面来说，由于关系型数据库大多采用 B+ 树类型的索引，在数据量超过阈值的情况下，索引深度的增加也将使得磁盘访问的 IO 次数增加，进而导致查询性能的下降；同时，高并发访问请求也使得集中式数据库成为系统的最大瓶颈。
从可用性的方面来讲，服务化的无状态型，能够达到较小成本的随意扩容，这必然导致系统的最终压力都落在数据库之上。而单一的数据节点，或者简单的主从架构，已经越来越难以承担。数据库的可用性，已成为整个系统的关键。
从运维成本方面考虑，当一个数据库实例中的数据达到阈值以上，对于 DBA 的运维压力就会增大。数据备份和恢复的时间成本都将随着数据量的大小而愈发不可控。一般来讲，单一数据库实例的数据的阈值在 1TB 之内，是比较合理的范围。
在传统的关系型数据库无法满足互联网场景需要的情况下，将数据存储至原生支持分布式的 NoSQL 的尝试越来越多。 但 NoSQL 对 SQL 的不兼容性以及生态圈的不完善，使得它们在与关系型数据库的博弈中始终无法完成致命一击，而关系型数据库的地位却依然不可撼动。
数据分片指按照某个维度将存放在单一数据库中的数据分散地存放至多个数据库或表中以达到提升性能瓶颈以及可用性的效果。 数据分片的有效手段是对关系型数据库进行分库和分表。分库和分表均可以有效的避免由数据量超过可承受阈值而产生的查询瓶颈。 除此之外，分库还能够用于有效的分散对数据库单点的访问量；
分表虽然无法缓解数据库压力，但却能够提供尽量将分布式事务转化为本地事务的可能，一旦涉及到跨库的更新操作，分布式事务往往会使问题变得复杂。 使用多主多从的分片方式，可以有效的避免数据单点，从而提升数据架构的可用性。
通过分库和分表进行数据的拆分来使得各个表的数据量保持在阈值以下，以及对流量进行疏导应对高访问量，是应对高并发和海量数据系统的有效手段。 数据分片的拆分方式又分为垂直分片和水平分片。
#垂直分片
 按照业务拆分的方式称为垂直分片，又称为纵向拆分，它的核心理念是专库专用。 在拆分之前，一个数据库由多个数据表构成，每个表对应着不同的业务。而拆分之后，则是按照业务将表进行归类，
 分布到不同的数据库中，从而将压力分散至不同的数据库。 下图展示了根据业务需要，将用户表和订单表垂直分片到不同的数据库的方案。  
垂直分片往往需要对架构和设计进行调整。通常来讲，是来不及应对互联网业务需求快速变化的；而且，它也并无法真正的解决单点瓶颈。 垂直拆分可以缓解数据量和访问量带来的问题，但无法根治。如果垂直拆分之后，表中的数据量依然超过单节点所能承载的阈值，则需要水平分片来进一步处理。
#水平分片
水平分片又称为横向拆分。 相对于垂直分片，它不再将数据根据业务逻辑分类，而是通过某个字段（或某几个字段），
根据某种规则将数据分散至多个库或表中，每个分片仅包含数据的一部分。 例如：根据主键分片，偶数主键的记录放入 0 库（或表），
奇数主键的记录放入 1 库（或表）。
水平分片从理论上突破了单机数据量处理的瓶颈，并且扩展相对自由，是分库分表的标准解决方案。
#挑战
虽然数据分片解决了性能、可用性以及单点备份恢复等问题，但分布式的架构在获得了收益的同时，也引入了新的问题。
面对如此散乱的分库分表之后的数据，应用开发工程师和数据库管理员对数据库的操作变得异常繁重就是其中的重要挑战之一。
他们需要知道数据需要从哪个具体的数据库的分表中获取。
另一个挑战则是，能够正确的运行在单节点数据库中的 SQL，在分片之后的数据库中并不一定能够正确运行。
例如，分表导致表名称的修改，或者分页、排序、聚合分组等操作的不正确处理。
跨库事务也是分布式的数据库集群要面对的棘手事情。 合理采用分表，可以在降低单表数据量的情况下，尽量使用本地事务，
善于使用同库不同表可有效避免分布式事务带来的麻烦。 在不能避免跨库事务的场景，有些业务仍然需要保持事务的一致性。 
而基于 XA 的分布式事务由于在并发度高的场景中性能无法满足需要，并未被互联网巨头大规模使用，他们大多采用最终一致性
的柔性事务代替强一致事务。
#步骤
spring:
  shardingsphere:
    # 数据库
    datasource:
      # 数据库的别名
      names: ds0,ds1
      # 数据库0
      ds0:
        ###  数据源类别
        type: com.alibaba.druid.pool.DruidDataSource
        driverClassName: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/ds0?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8
        username: root
        password: root
      # 数据库1
      ds1:
        ###  数据源类别
        type: com.alibaba.druid.pool.DruidDataSource
        driverClassName: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/ds1?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8
        username: root
        password: root

    # *** 数据库分库分表配置 start
    sharding:
      # 水平拆分的数据库（表） 配置分库 + 分表策略 行表达式分片策略
      # 1.默认分库策略 ds0,ds1:通过字段id进行分库策略，对2取余为0入库0，对2取余为1入库1
      default-database-strategy:
        inline:
          sharding-column: id
          algorithm-expression: ds$->{id % 2}
      # 2.默认分表策略 user_0,user_1:通过字段age进行分库策略，对2取余为0入库0，对2取余为1入库1
      default-table-strategy:
        inline:
          sharding-column: age  # 分表策略 其中user为逻辑表 分表主要取决于age行
          algorithm-expression: user_$->{age % 2}
      # 数据节点
      tables:
        user:
          actual-data-nodes: ds$->{0..1}.user_$->{0..1}
      # *** 数据库分库分表配置 end

    #    sharding:
    #      # 默认数据库
    #      default-data-source-name: ds0
    #      default-database-strategy:
    #        inline:
    #          sharding-column: user_id
    #          algorithm-expression: ds$->{user_id % 2}
    #      tables:
    #        user:
    #            #指定user表里面主键id生成策略 雪花算法
    #          key-generator:
    #            column: user_id
    #            type: SNOWFLAKE
    #          actual-data-nodes: ds$->{0..1}.user_split_$->{0..1}
    #          table-strategy:
    #            inline:
    #              sharding-column: age
    #              algorithm-expression: user_split_$->{age % 2}
    #      binding-tables: user

    props:
      # 打印SQL
      sql.show: true
      check:
        table:
          metadata: true
          # 是否在启动时检查分表元数据一致性
          enabled: true
      query:
        with:
          cipher:
            column: true
  main:
    allow-bean-definition-overriding: true
#导包：
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
             <groupId>com.baomidou</groupId>
             <artifactId>mybatis-plus-boot-starter</artifactId>
             <version>3.3.2</version>
         </dependency>
         <dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
             <version>8.0.23</version>
         </dependency>
         <dependency>
             <groupId>org.apache.shardingsphere</groupId>
             <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
             <version>4.0.0-RC1</version>
         </dependency>
         <dependency>
             <groupId>io.shardingsphere</groupId>
             <artifactId>sharding-jdbc-spring-namespace</artifactId>
             <version>3.1.0</version>
         </dependency>
         <dependency>
             <groupId>org.projectlombok</groupId>
             <artifactId>lombok</artifactId>
             <version>1.18.20</version>
         </dependency>
         <dependency>
             <groupId>com.alibaba</groupId>
             <artifactId>druid-spring-boot-starter</artifactId>
             <version>1.1.16</version>
         </dependency>`    
`
#数据库
创建ds0,ds1库，库里分别创建user_0,user_1
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_1
-- ----------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;