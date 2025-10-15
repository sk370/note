---
title: RabbitMQ
urlname: qfcxen
date: '2022-08-28 17:35:50 +0800'
tags: [RabbitMQ]
categories: [消息队列]
---

*RabbitMQ 是流行的消息队列服务软件，是开源的 AMQP（高级消息队列协议）实现。支持多种客户端，如：Java、Python、C、PHP、Ruby、JavaScript 等，用于在分布式系统中存储转发消息，可以实现异步处理、流量削峰、系统解耦，在易用性、扩展性、高可用等方面表现优异。*
<!-- more -->

## 第 1 章消息队列介绍

### 1.1MQ 相关概念

#### 1.1.1 含义

MQ(messagequeue)，从字面意思上看，本质是个队列，FIFO 先入先出，只不过队列中存放的内容是 message 而已，还是一种跨进程的通信机制，用于上下游传递消息。在互联网架构中，MQ 是一种非常常见的上下游“逻辑解耦+物理解耦”的消息通信服务。使用了 MQ 之后，消息发送上游只需要依赖 MQ，不用依赖其他服务。

#### 1.1.2 意义

1. 流量消峰：将短时、大量请求使用消息队列做缓冲，排队执行，减少服务器压力。
2. 应用解耦：故障服务出现故障后，操作缓存在消息队列里，等到服务恢复后再执行，减少了不同服务间的强依赖关系，提高了系统的可用性。
3. 异步处理：服务间调用过程需要很长时间完成时，使用消息总线去传送执行结果信息，不需要服务去等待服务。
   - 传统方式 1：A 过一段时间去调用 B 的查询 api 查询。
   - 传统方式 2：A 提供一个 callbackapi，B 执行完之后调用 api 通知 A 服务。

#### 1.1.3 分类

1. ActiveMQ：
   - 优点：单机吞吐量万级，时效性 ms 级，可用性高，基于主从架构实现高可用性，消息可靠性较低的概率丢失数据
   - 缺点:官方社区现在对 ActiveMQ5.x 维护越来越少，高吞吐量场景较少使用。
2. Kafka：
   - 大数据的杀手锏。吞吐量百万级，分布式、不会丢失数据。消费者采用 Pull 方式获取消息,消息有序,通过控制能够保证所有消息被消费且仅被消费一次;有优秀的第三方 KafkaWeb 管理界面 Kafka-Manager；在日志领域比较成熟，被多家公司和多个开源项目使用；功能支持：功能较为简单，主要支持简单的 MQ 功能，在大数据领域的实时计算以及日志采集被大规模使用。
   - 缺点：Kafka 单机超过 64 个队列/分区，Load 会发生明显的飙高现象，队列越多，load 越高，发送消息响应时间变长，使用短轮询方式，实时性取决于轮询间隔时间，消费失败不支持重试；支持消息顺序，但是一台代理宕机后，就会产生消息乱序，社区更新较慢；
3. RocketMQ：RocketMQ 出自阿里巴巴的开源产品，用 Java 语言实现，在设计时参考了 Kafka，并做出了自己的一些改进。被阿里巴巴广泛应用在订单，交易，充值，流计算，消息推送，日志流式处理，binglog 分发等场景。
   - 优点:单机吞吐量十万级,可用性非常高，分布式架构,消息可以做到 0 丢失,MQ 功能较为完善，还是分布式的，扩展性好,支持 10 亿级别的消息堆积，不会因为堆积导致性能下降,源码是 java 我们可以自己阅读源码，定制自己公司的 MQ。
   - 缺点：支持的客户端语言不多，目前是 java 及 c++，其中 c++不成熟；社区活跃度一般,没有在 MQ 核心中去实现 JMS 等接口,有些系统要迁移需要修改大量代码
4. RabbitMQ：2007 年发布，是一个在 AMQP(高级消息队列协议)基础上完成的，可复用的企业消息系统，是当前最主流的消息中间件之一。
   - 优点:由于 erlang 语言的高并发特性，性能较好；吞吐量到万级，MQ 功能比较完备,健壮、稳定、易用、跨平台、支持多种语言如：Python、Ruby、.NET、Java、JMS、C、PHP、ActionScript、XMPP、STOMP 等，支持 AJAX 文档齐全；开源提供的管理界面非常棒，用起来很好用,社区活跃度高；更新频率相当高。
   - 缺点：商业版需要收费,学习成本较高

#### 1.1.4 消息队列的选择

1. kafka：大型公司、大数据公司、日志采集需求的公司使用。
2. RocketMQ：金融领域。
3. RabbitMQ：中小型公司的优选。

### 1.2 RabbitMQ

#### 1.2.1 RabbitMQ 的概念

RabbitMQ 是一个消息中间件：它接受并转发消息。你可以把它当做一个快递站点，当你要发送一个包裹时，你把你的包裹放到快递站，快递员最终会把你的快递送到收件人那里，按照这种逻辑 RabbitMQ 是一个快递站，一个快递员帮你传递快件。 RabbitMQ 与快递站的主要区别在于，它不处理快件而是接收，存储和转发消息数据。

#### 1.2.2 RabbitMQ 的四大核心概念

1. 生产者：产生数据发送消息的程序是生产者
2. 交换机：交换机是 RabbitMQ 非常重要的一个部件，一方面它接收来自生产者的消息，另一方面它将消息推送到队列中。
   - 交换机必须确切知道如何处理它接收到的消息，是将这些消息推送到特定队列还是推送到多个队列，亦或者是把消息丢弃，这个得有交换机类型决定
3. 队列：队列是 RabbitMQ 内部使用的一种数据结构， 尽管消息流经 RabbitMQ 和应用程序，但它们只能存储在队列中。队列仅受主机的内存和磁盘限制的约束，本质上是一个大的消息缓冲区。
   - 许多生产者可以将消息发送到一个队列，许多消费者可以尝试从一个队列接收数据。
4. 消费者：消费与接收具有相似的含义。消费者大多时候是一个等待接收消息的程序。
   - 请注意生产者，消费者和消息中间件很多时候并不在同一机器上。同一个应用程序既可以是生产者又是可以是消费者。

#### 1.2.3 RabbitMQ 模式

1. 简单模式：
   - ![image.png](rabbitmq/image.png)
2. 工作模式：
   - ![image.png](rabbitmq/image-1669758340650.png)
3. 发布订阅模式：
   - ![image.png](rabbitmq/image-1669758342059.png)
4. 路由模式：
   - ![image.png](rabbitmq/image-1669758343437.png)
5. 主题模式：
   - ![image.png](rabbitmq/image-1669758345312.png)
6. RPC 模式：
   - ![image.png](rabbitmq/image-1669758347269.png)
7. 发布确认模式：

#### 1.2.4 工作原理

![image.png](rabbitmq/image-1669758358009.png)

- Producer：生产者
- Broker：接收和分发消息的应用， RabbitMQ Server 就是 Message Broker
  - Exchange： 交换机。message 到达 broker 的第一站，根据分发规则，匹配查询表中的 routing key，分发消息到 queue 中去。常用的类型有： direct (point-to-point), topic (publish-subscribe) and fanout(multicast)
    - Queue： 消息最终被送到这里等待 consumer 取走
- Connection： publisher／ consumer 和 broker 之间的 TCP 连接
  - Channel：信道。如果每一次访问 RabbitMQ 都建立一个 Connection，在消息量大的时候建立 TCPConnection 的开销将是巨大的，效率也较低。 Channel 是在 connection 内部建立的逻辑连接，如果应用程序支持多线程，通常每个 thread 创建单独的 channel 进行通讯， AMQP method 包含了 channel id 帮助客户端和 message broker 识别 channel，所以 channel 之间是完全隔离的。 Channel 作为轻量级的 Connection 极大减少了操作系统建立 TCP connection 的开销
- Consumer：消费者

图中没有画的还有 Virtual host，Broker 包含多个 Virtual host， Virtual host 包含多个 Exchange 和 Channel。

### 1.3 rabbitmq 安装（3.8.8 版本）

#### 1.3.1 安装过程

RabbitMQ 的使用需要 erlang 的运行环境，相关步骤如下：

1. 根据本人习惯，在/usr/local 下新建/erlang 和/rabbitmq 目录
2. 分别将 erlang-21.3-1.el7.x86_64.rpm 和 rabbitmq-server-3.8.8-1.el7.noarch.rpm 防止对应目录下
   - ![image.png](rabbitmq/image-1669758363495.png)
3. 安装 Erlang 语言环境：`rpm -ivh erlang-21.3-1.el7.x86_64.rpm`
   - ![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661688034115-1164de83-5660-461e-8fd2-36040157fa75.png#averageHue=%23f2eeea&clientId=u17323d1b-8be9-4&crop=0&crop=0&crop=1&crop=1&errorMessage=unknown%20error&from=paste&height=79&id=ue120f2ec&margin=%5Bobject%20Object%5D&name=image.png&originHeight=79&originWidth=596&originalType=binary∶=1&rotation=0&showTitle=false&size=8780&status=error&style=none&taskId=ua4ffecb1-3246-4efb-a9aa-385aef7ff4e&title=&width=596)
   - ![image.png](rabbitmq/image-1669758365903.png)
   - emmmm，erlang 应该是分散安装了……
4. 安装 socat 依赖：`yum install socat -y`
   - ![image.png](rabbitmq/image-1669758367782.png)
5. 安装 rabbitmq：`rpm -ivh rabbitmq-server-3.8.8-1.el7.noarch.rpm`
   - ![image.png](rabbitmq/image-1669758378606.png)
6. 设置开机启动：`chkconfig rabbitmq-server on`
   - ![image.png](rabbitmq/image-1669758382234.png)
7. 启动服务：`/sbin/service rabbitmq-server start`或者`systemctl start rabbitmq-server`
   - 重启服务：`systemctl restart rabbitmq-server`
8. 查看运行状态：`/sbin/service rabbitmq-server status`或者`systemctl status rabbitmq-server`
   - ![image.png](rabbitmq/image-1669758384718.png)
9. 停止服务：`/sbin/service rabbitmq-server stop`或者`systemctl stop rabbitmq-server`
10. 开启 web 管理插件：`rabbitmq-plugins enable rabbitmq_management`
11. 访问虚拟机 ip+端口：`[http://192.168.150.100:15672/](http://192.168.150.100:15672/)`

- ![image.png](rabbitmq/image-1669758392864.png)
- 默认账号 guest、密码 guest，此时还不能登录，需要开启权限，但一般设置新账户。

12. 创建账户：`rabbitmqctl add_user admin 123`
13. 设置 admin 添加 administrator 角色：`rabbitmqctl set_user_tags admin administrator`

- 此时就已经可以登录了
- ![image.png](rabbitmq/image-1669758395219.png)

14. 设置用户权限：`rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"`

- ![image.png](rabbitmq/image-1669758397191.png)

15. 查看用户和角色：`rabbitmqctl list_users`

- ![image.png](rabbitmq/image-1669758400508.png)

16. 关闭应用：`rabbitmqctl stop_app`
17. 清除应用：`rabbitmqctl reset`
18. 重启应用：`rabbitmqctl start_app`

#### 1.3.2 rabbitmqctl 命令

```bash
[root@localhost rabbitmq-server-3.8.8]# rabbitmqctl -help

Usage

rabbitmqctl [--node <node>] [--timeout <timeout>] [--longnames] [--quiet] <command> [<command options>]

Available commands:

Help:

   autocomplete                  Provides command name autocomplete variants
   help                          Displays usage information for a command
   version                       Displays CLI tools version

Nodes:

   await_startup                 Waits for the RabbitMQ application to start on the target node
   reset                         Instructs a RabbitMQ node to leave the cluster and return to its virgin state
   rotate_logs                   Instructs the RabbitMQ node to perform internal log rotation
   shutdown                      Stops RabbitMQ and its runtime (Erlang VM). Monitors progress for local nodes. Does not require a PID file path.
   start_app                     Starts the RabbitMQ application but leaves the runtime (Erlang VM) running
   stop                          Stops RabbitMQ and its runtime (Erlang VM). Requires a local node pid file path to monitor progress.
   stop_app                      Stops the RabbitMQ application, leaving the runtime (Erlang VM) running
   wait                          Waits for RabbitMQ node startup by monitoring a local PID file. See also 'rabbitmqctl await_online_nodes'

Cluster:

   await_online_nodes            Waits for <count> nodes to join the cluster
   change_cluster_node_type      Changes the type of the cluster node
   cluster_status                Displays all the nodes in the cluster grouped by node type, together with the currently running nodes
   force_boot                    Forces node to start even if it cannot contact or rejoin any of its previously known peers
   force_reset                   Forcefully returns a RabbitMQ node to its virgin state
   forget_cluster_node           Removes a node from the cluster
   join_cluster                  Instructs the node to become a member of the cluster that the specified node is in
   rename_cluster_node           Renames cluster nodes in the local database
   update_cluster_nodes          Instructs a cluster member node to sync the list of known cluster members from <seed_node>

Replication:

   cancel_sync_queue             Instructs a synchronising mirrored queue to stop synchronising itself
   sync_queue                    Instructs a mirrored queue with unsynchronised mirrors (follower replicas) to synchronise them

Users:

   add_user                      Creates a new user in the internal database
   authenticate_user             Attempts to authenticate a user. Exits with a non-zero code if authentication fails.
   change_password               Changes the user password
   clear_password                Clears (resets) password and disables password login for a user
   delete_user                   Removes a user from the internal database. Has no effect on users provided by external backends such as LDAP
   list_users                    List user names and tags
   set_user_tags                 Sets user tags

Access Control:

   clear_permissions             Revokes user permissions for a vhost
   clear_topic_permissions       Clears user topic permissions for a vhost or exchange
   list_permissions              Lists user permissions in a virtual host
   list_topic_permissions        Lists topic permissions in a virtual host
   list_user_permissions         Lists permissions of a user across all virtual hosts
   list_user_topic_permissions   Lists user topic permissions
   list_vhosts                   Lists virtual hosts
   set_permissions               Sets user permissions for a vhost
   set_topic_permissions         Sets user topic permissions for an exchange

Monitoring, observability and health checks:

   list_bindings                 Lists all bindings on a vhost
   list_channels                 Lists all channels in the node
   list_ciphers                  Lists cipher suites supported by encoding commands
   list_connections              Lists AMQP 0.9.1 connections for the node
   list_consumers                Lists all consumers for a vhost
   list_exchanges                Lists exchanges
   list_hashes                   Lists hash functions supported by encoding commands
   list_queues                   Lists queues and their properties
   list_unresponsive_queues      Tests queues to respond within timeout. Lists those which did not respond
   ping                          Checks that the node OS process is up, registered with EPMD and CLI tools can authenticate with it
   report                        Generate a server status report containing a concatenation of all server status information for support purposes
   schema_info                   Lists schema database tables and their properties
   status                        Displays status of a node

Parameters:

   clear_global_parameter        Clears a global runtime parameter
   clear_parameter               Clears a runtime parameter.
   list_global_parameters        Lists global runtime parameters
   list_parameters               Lists runtime parameters for a virtual host
   set_global_parameter          Sets a runtime parameter.
   set_parameter                 Sets a runtime parameter.

Policies:

   clear_operator_policy         Clears an operator policy
   clear_policy                  Clears (removes) a policy
   list_operator_policies        Lists operator policy overrides for a virtual host
   list_policies                 Lists all policies in a virtual host
   set_operator_policy           Sets an operator policy that overrides a subset of arguments in user policies
   set_policy                    Sets or updates a policy

Virtual hosts:

   add_vhost                     Creates a virtual host
   clear_vhost_limits            Clears virtual host limits
   delete_vhost                  Deletes a virtual host
   list_vhost_limits             Displays configured virtual host limits
   restart_vhost                 Restarts a failed vhost data stores and queues
   set_vhost_limits              Sets virtual host limits
   trace_off
   trace_on

Configuration and Environment:

   decode                        Decrypts an encrypted configuration value
   encode                        Encrypts a sensitive configuration value
   environment                   Displays the name and value of each variable in the application environment for each running application
   set_cluster_name              Sets the cluster name
   set_disk_free_limit           Sets the disk_free_limit setting
   set_log_level                 Sets log level in the running node
   set_vm_memory_high_watermark  Sets the vm_memory_high_watermark setting

Definitions:

   export_definitions            Exports definitions in JSON or compressed Erlang Term Format.
   import_definitions            Imports definitions in JSON or compressed Erlang Term Format.

Feature flags:

   enable_feature_flag           Enables a feature flag on target node
   list_feature_flags            Lists feature flags

Operations:

   close_all_connections         Instructs the broker to close all connections for the specified vhost or entire RabbitMQ node
   close_connection              Instructs the broker to close the connection associated with the Erlang process id
   eval                          Evaluates a snippet of Erlang code on the target node
   eval_file                     Evaluates a file that contains a snippet of Erlang code on the target node
   exec                          Evaluates a snippet of Elixir code on the CLI node
   force_gc                      Makes all Erlang processes on the target node perform/schedule a full sweep garbage collection
   resume_listeners              Resumes client connection listeners making them accept client connections again
   suspend_listeners             Suspends client connection listeners so that no new client connections are accepted

Queues:

   delete_queue                  Deletes a queue
   purge_queue                   Purges a queue (removes all messages in it)

Deprecated:

   hipe_compile                  DEPRECATED. This command is a no-op. HiPE is no longer supported by modern Erlang versions
   node_health_check             DEPRECATED. Performs intrusive, opinionated health checks on a fully booted node. See https://www.rabbitmq.com/monitoring.html#health-checks instead

Use 'rabbitmqctl help <command>' to learn more about a specific command

```

#### 1.3.3 rabbitmq-plugins 命令

```bash
[root@localhost rabbitmq-server-3.8.8]# rabbitmq-plugins

Usage

rabbitmq-plugins [--node <node>] [--timeout <timeout>] [--longnames] [--quiet] <command> [<command options>]

Available commands:

Help:

   autocomplete  Provides command name autocomplete variants
   help          Displays usage information for a command
   version       Displays CLI tools version

Monitoring, observability and health checks:

   directories   Displays plugin directory and enabled plugin file paths
   is_enabled    Health check that exits with a non-zero code if provided plugins are not enabled on target node

Plugin Management:

   disable       Disables one or more plugins
   enable        Enables one or more plugins
   list          Lists plugins and their state
   set           Enables one or more plugins, disables the rest

Use 'rabbitmq-plugins help <command>' to learn more about a specific command
```

#### 1.3.4 web 管理界面

### 1.4 使用

#### 1.4.1 maven 整合

```xml
<!--指定 jdk 编译版本-->
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <configuration>
        <source>8</source>
        <target>8</target>
      </configuration>
    </plugin>
  </plugins>
</build>
<dependencies>
  <!--rabbitmq 依赖客户端-->
  <dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>5.8.0</version>
  </dependency>
  <!--操作文件流的一个依赖-->
  <dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
  </dependency>
</dependencies>
```

#### 1.4.2 整合 springboot

1. 依赖：

```xml
<dependencies>
  <!--RabbitMQ 依赖-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

2. 配置文件：

```properties
spring.rabbitmq.host=192.168.150.100
spring.rabbitmq.port=5672
spring.rabbitmq.username=admin
spring.rabbitmq.password=123
```

3. 使用@EnableRabbit 注解开启功能
4. 队列声明在配置类中，提供者写在 controller 中，消费者写在监听器中。

## 第 2 章 6 种模式及消息策略

### 2.1 模式 1：简单模式

1. 创建 maven 工程。
2. 引入依赖：
3. 创建生产者：

```java
public class Producer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
        factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();//获取连接对象
        Channel channel = connection.createChannel();//获取连接中通道

        /**
         * 生成一个队列，参数解释如下:
         * 1.队列名称，不存在时则自动创建
         * 2.队列里面的消息是否持久化 默认消息存储在内存中【true 持久化队列】
         * 3.exclusive 是否独占队列（只允许当前队列使用）【true 独占队列】
         * 4.是否自动删除 最后一个消费者断开连接以后 该队列是否自动删除 【true 自动删除】
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hello world";
        /**
         * 发送一个消息，参数解释如下:
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());//简单模式发送
        System.out.println("消息发送完毕");
        channel.close();
        connection.close();
    }
}

```

![image.png](rabbitmq/image-1669758418832.png)
![image.png](rabbitmq/image-1669758421007.png)
执行 main 方法， 查看 web 管理插件，可以看到生成了一个 hello 队列，且准备就绪，等待被消费。

- ![image.png](rabbitmq/image-1669758422482.png)
- ![image.png](rabbitmq/image-1669758424098.png)

4. 创建消费者：

```java
public class Consumer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
        factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息....");
        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 【false 手动应答】
         * 3.消费者成功消费的回调
         * 4.消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        channel.close();
        connection.close();
    }
}
```

![image.png](rabbitmq/image-1669758430996.png)

- 执行 main 方法，此时 hello 队列中的消息会被消费掉
- ![image.png](rabbitmq/image-1669758433160.png)

### 2.2 模式 2：工作模式

#### 2.2.1 工作模式代码

工作队列(又称任务队列)的主要思想是队列中产生大量待处理的消息，消费（处理）能力不足，导致不能即使得到响应。工作模式提供了多个消费线程，共同处理生产者生产的消息。
但需要注意的是，一个消息只能被处理一次，不能被多次处理，不同消费者处理消息的机制是轮循方式。

1. 优化简单模式连接工厂代码，创建工具类。

```java
public class RabbitMQUtils {
    private static Connection connection = null;
    private static Channel channel = null;

    /**
     * 得到一个通道
     * @return
     * @throws Exception
     */
    public static Channel getChannel() {
        try {
            ConnectionFactory factory = new ConnectionFactory();//创建一个连接工厂
            factory.setHost("192.168.150.100");//连接rabbitmq主机
//        factory.setPort(5672);//设置端口号，默认5672可以则可以不写这句
//        factory.setVirtualHost("/");//设置连接哪个虚拟主机，默认虚拟主机/时可以不写这句
            factory.setUsername("admin");
            factory.setPassword("123");

            connection = factory.newConnection();//获取连接对象
            channel = connection.createChannel();//获取连接中通道
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }
    public static void close(){
        if (channel != null){
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }

        }

        if (connection != null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
```

2. 创建生产者，使用输入实现源源不断地消息，生产者生成队列（queueDeclare）、发送消息（basicPublish）的方法与简单模式没有区别。

```java
public class Producer {
    private final static String QUEUE_NAME = "worker";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtils.getChannel();
        /**
         * 生成一个队列，参数解释如下:
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 【true 可以多个消费者消费】
         * 4.是否自动删除 最后一个消费者端开连接以后 该队列是否自动删除 【true 自动删除】
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("输入要发送的消息内容，按0退出");
            String str = scanner.nextLine();
            if("0".equals(str)){
                break;
            }else{

                /**
                 * 发送一个消息，参数解释如下:
                 * 1.发送到那个交换机
                 * 2.路由的 key 是哪个
                 * 3.其他的参数信息
                 * 4.发送消息的消息体
                 */
                channel.basicPublish("", QUEUE_NAME, null, str.getBytes());//简单模式发送
                System.out.println("本次消息发送完毕");
            }
        }
        System.out.println("输入结束！");
        RabbitMQUtils.close();
    }
}
```

3. 使用多线程创建 3 个消费者，消费者消费消息（basicConsume）的方法与简单模式没有区别

```java
public class Consumer extends Thread {
    @Override
    public void run() {
        try {
            Channel channel = RabbitMQUtils.getChannel();
            String name = Thread.currentThread().getName();
            System.out.println( name + "等待接收消息....");
            //推送的消息如何进行消费的接口回调
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println(name + "消费了一条消息，内容是" + message);
            };
            //取消消费的一个回调接口 如在消费的时候队列被删除掉了
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println("消息消费被中断");
            };
            /**
             * 消费者消费消息
             * 1.消费哪个队列
             * 2.消费成功之后是否要自动应答 true 代表自动应答 【false 手动应答】
             * 3.消费者成功消费的回调
             * 4.消费者取消消费的回调
             */
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static String QUEUE_NAME = "worker";

    public static void main(String[] args) throws Exception {
        Consumer thread1 = new Consumer();
        Consumer thread2 = new Consumer();
        Consumer thread3 = new Consumer();
        thread1.setName("消费者1");
        thread2.setName("消费者2");
        thread3.setName("消费者3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

4. 执行测试：注意，需要先启动生产者创建消息队列，否则消费者会报错。
   - ![image.png](rabbitmq/image-1669758444259.png)![image.png](rabbitmq/image-1669758439622.png)

### 2.3 模式 3：发布订阅模式

#### 2.3.1 交换机及交换机与队列绑定

简单模式和工作模式中，生产者直接将消息发送给队列，消息被不同的消费者只能处理一次。 实际生产中，一个消息可能需要多个消费者处理，为了满足上述要求，rabbitmq 引入了 Exchanges（交换机）。
RabbitMQ 消息传递模型的核心思想是: 生产者生产的消息从不会直接发送到队列。实际上，通常生产者甚至都不知道这些消息传递传递到了哪些队列中。相反， 生产者只能将消息发送到交换机(exchange)，交换机工作的内容非常简单， 一方面它接收来自生产者的消息，另一方面将它们推入队列。
交换机必须确切知道如何处理收到的消息。 是应该把这些消息放到特定队列还是说把他们到许多队列中还是说应该丢弃它们。 这就的由交换机的类型来决定。
Exchanges 类型：

1. 直接(direct)
   - ![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661761640373-e7a7acbc-acae-47f6-90a9-81f8526187e8.png#averageHue=%23f5f1ec&clientId=ud8da96fa-c680-4&crop=0&crop=0&crop=1&crop=1&errorMessage=unknown%20error&from=paste&height=19&id=GqyJA&margin=%5Bobject%20Object%5D&name=image.png&originHeight=19&originWidth=667&originalType=binary∶=1&rotation=0&showTitle=false&size=7919&status=error&style=none&taskId=u48ea1b8c-bd45-402d-b8d6-0732add8b8d&title=&width=667)
   - 该方法的第一个参数即为交换机的名称，空字符串表示默认或无名称交换机
2. 主题(topic)
3. 标题(headers)
4. 扇出(fanout) ——即发布订阅

临时队列：

1. 临时需要一个空队列，当断开与消费者的连接时，队列自动删除。
2. 创建方式：`String queueName = channel.queueDeclare().getQueue();`

绑定（bindings）：
binding 其实是 exchange 和 queue 之间的桥梁，它告诉我们 exchange 和那个队列进行了绑定关系。比如说下面这张图告诉我们的就是 X 与 Q1 和 Q2 进行了绑定 。
![image.png](rabbitmq/image-1669758451445.png)
绑定时使用唯一的 Routing key，从而实现队列与交换机的唯一绑定。当多个队列持有这个 Routing key 时，表示多个队列获得了消息.

#### 2.4.2 Fanout 介绍

![image.png](rabbitmq/image-1669758453671.png)
Fanout（扇出） 这种类型非常简单，它将接收到的所有消息广播到它知道的所有队列中。 系统中默认有 exchange 类型：
![image.png](rabbitmq/image-1669758456075.png)

#### 2.4.3 示例

创建一个生产者，发送消息后，由两个消费者接收，一个消费者在控制台输出，一个进行本地保存。
实现思路：创建一个扇出交换机，绑定两个队列（两个队列使用相同的 Routing key），利用扇出的特性——广播数据即可实现。

```java
产者发送消息：
channel.basicPublish(EXCHANGE_NAME,"",null,str.getBytes());

消费者获取指定的交换机：
channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

生成临时队列：
Stringqueue=channel.queueDeclare().getQueue();

临时队列绑定交换机
channel.queueBind(queue,EXCHANGE_NAME,"");

消费消息：
channel.basicConsume(queue,true,deliverCallback,consumerTag->{});
```

![image.png](rabbitmq/image-1669758459759.png)

### 2.4 模式 4：路由模式

#### 2.4.1 介绍

![image.png](rabbitmq/image-1669758462292.png)
Direct exchange ——直接交换机，与 fanout 的区别是 Routing key 不同。

#### 2.4.2 多重绑定

Routing key 相同情况时的直接交换机。与扇出交换机相同。

#### 2.4.3 示例

![image.png](rabbitmq/image-1669758464639.png)
实现上述模式：

```java
生产者发送消息：
channel.basicPublish(EXCHANGE_NAME,"info",null,str.getBytes());
channel.basicPublish(EXCHANGE_NAME,"error",null,str.getBytes());

消费者获取指定的交换机：
channel.exchangeDeclare(EXCHANGE_NAME,"direct");


生成队列：
channel.queueDeclare("console",false,false,false,null);
channel.queueDeclare("console",false,false,false,null);
channel.queueDeclare("disk",false,false,false,null);

队列绑定交换机
channel.queueBind("console",EXCHANGE_NAME,"info");
channel.queueBind("console",EXCHANGE_NAME,"warning");
channel.queueBind("disk",EXCHANGE_NAME,"error");

消费消息：
channel.basicConsume("console",true,deliverCallback,consumerTag->{});
channel.basicConsume("console",true,deliverCallback,consumerTag->{});
channel.basicConsume("console",true,deliverCallback,consumerTag->{});
```

![image.png](rabbitmq/image-1669758467654.png)
上述交换机章 ，消费者消费消息只与 Routing key 相关。

### 2.5 模式 5：话题模式

#### 2.5.1 介绍

fanout 只能广播、direct 只能单线发送（路由唯一），如果想要一个消费者能够接收多个路由的消息，则无法实现。
topic 交换机的消息的 routing_key 是一个长度不超过 255 字节的单词列表，单词以点号分隔开。如 "stock.usd.nyse", "nyse.vmw","quick.orange.rabbit"。其中：

- \*(星号)可以代替一个单词
- #(井号)可以替代零个或多个单词

当一个队列绑定键是#,那么这个队列将接收所有数据，就有点像 fanout
如果队列绑定键当中没有#和\*出现，那么该队列绑定类型就是 direct 了

#### 2.5.2 示例

![image.png](rabbitmq/image-1669758474470.png)

```java
生产者发送消息：
for(Map.Entry<String,String>bindingKeyEntry:bindingKeyMap.entrySet()){
	StringbindingKey=bindingKeyEntry.getKey();
	Stringmessage=bindingKeyEntry.getValue();
	channel.basicPublish(EXCHANGE_NAME,bindingKey,null,
	message.getBytes("UTF-8"));
	System.out.println("生产者发出消息"+message);
}

消费者获取指定的交换机：
channel.exchangeDeclare(EXCHANGE_NAME,"topic");


生成队列：
channel.queueDeclare("Q2",false,false,false,null);
channel.queueDeclare("Q1",false,false,false,null);
channel.queueDeclare("Q2",false,false,false,null);

队列绑定交换机
channel.queueBind("Q2",EXCHANGE_NAME,"*.*.rabbit");
channel.queueBind("Q1",EXCHANGE_NAME,"*.ogange.*");
channel.queueBind("Q2",EXCHANGE_NAME,"lazy.#");

消费消息：
channel.basicConsume("Q2",true,deliverCallback,consumerTag->{});
channel.basicConsume("Q1",true,deliverCallback,consumerTag->{});
channel.basicConsume("Q2",true,deliverCallback,consumerTag->{});
```

![image.png](rabbitmq/image-1669758482633.png)

### 2.7 模式 7：发布确认策略

#### 2.7.1 原理

针对工作模式消息持久化仍然存在丢失的风险，rabbitmq 通过引入发布确认模式。

1. 生产者将信道设置成 confirm 模式（`channel.confirmSelect()`），该信道上面发布的消息都将会被指派一个唯一的 ID(从 1 开始)。
2. 消息被投递到所有匹配的队列之后， broker 就会发送一个确认 ack 给生产者(包含消息的唯一 ID)，这就使得生产者知道消息已经正确到达目的队列了。
3. 如果消息和队列是可持久化的，那么确认消息会在将消息写入磁盘之后发出。
4. broker 回传给生产者的确认消息中 delivery-tag 域包含了确认消息的序列号。
5. broker 也可以设置 basic.ack 的 multiple 域，表示到这个序列号之前的所有消息都已经得到了处理。

理解：发布确认针对的是 producer 和 broker 之间的关系，而不是 producer 和 consumer 的关系。

#### 2.7.2 单个发布确认策略——同步方式

发布一个消息之后只有它被确认发布，后续的消息才能继续发布,`waitForConfirmsOrDie(long)`这个方法只有在消息被确认的时候才返回，如果在指定时间范围内这个消息没有被确认那么它将抛出异常。
相较于其他策略，单个发布确认策略发布速度较慢，因为没有确认发布的消息就会阻塞，这种方式最多提供每秒不超过数百条的发布消息的吞吐量。
![image.png](rabbitmq/image-1669758486973.png)

#### 2.7.3 批量发布确认策略——同步方式

单个发布确认存在消息发布速度较慢的问题。当消息发送固定条数后进行确认，确认成功后发送下一批次的消息，能够稍微提高点发送速率。
但是还是没法解决发生故障时是哪条消息的问题。
总结：同步的方式总是存在阻塞的问题。
![image.png](rabbitmq/image-1669758489675.png)

#### 2.7.4 异步确认发布策略

解决上述两策略信息丢失无法定位问题。
该策略利用消费者的 ackCallback 和 nackCallback 的回调函数，分别确认发送成功的消息和失败的消息。
![image.png](rabbitmq/image-1669758492156.png)

#### 2.7.5 处理未确认消息

最好的解决的解决方案就是把未确认的消息放到一个基于内存的能被发布线程访问的队列，比如说用 ConcurrentLinkedQueue 这个队列在 confirm callbacks 与发布线程之间进行消息的传递。
ConcurrentSkipListMap 是一个线程安全、有序、支持高并发的哈希表。 ![image.png](rabbitmq/image-1669758495273.png)
![image.png](rabbitmq/image-1669758497436.png)

#### 2.7.6 发布策略的选择

1. 单独发布消息：同步等待确认， 简单，但吞吐量非常有限。
2. 批量发布消息：批量同步等待确认， 简单，合理的吞吐量， 一旦出现问题但很难推断出是哪条消息出现了问题。
3. 异步处理：最佳性能和资源使用，在出现错误的情况下可以很好地控制，但是实现起来稍微难些 。

### 2.8 可靠投递——消费端

#### 2.8.1 概念

1. 概念：为了保证消息在发送过程中不丢失，rabbitmq 引入消息应答机制，消息应答就是:消费者在接收到消息并且处理该消息之后，告诉 rabbitmq 它已经处理了， rabbitmq 可以把该消息删除了。
2. 分类：
   - 自动应答：消息发送后立即被认为传送成功。对消费者的处理效率和连接或 channel 的稳定性要求较高，仅适用于系统环境非常好的情况下。
   - 手动应答：
     - Channel.basicAck(用于肯定确认)
     - Channel.basicNack(用于否定确认)
     - Channel.basicReject(用于否定确认) ——比 Channel.basicNack 少一个 multiple 参数。

```java
public void basicAck(long deliveryTag, boolean multiple) throws IOException {
    long realTag = deliveryTag - this.activeDeliveryTagOffset;
    if (multiple && deliveryTag == 0L) {
        realTag = 0L;
    } else if (realTag <= 0L) {
        return;
    }

    this.transmit(new Ack(realTag, multiple));
    this.metricsCollector.basicAck(this, deliveryTag, multiple);
}

public void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException {
    long realTag = deliveryTag - this.activeDeliveryTagOffset;
    if (multiple && deliveryTag == 0L) {
        realTag = 0L;
    } else if (realTag <= 0L) {
        return;
    }

    this.transmit(new Nack(realTag, multiple, requeue));
    this.metricsCollector.basicNack(this, deliveryTag);
}

public void basicReject(long deliveryTag, boolean requeue) throws IOException {
    long realTag = deliveryTag - this.activeDeliveryTagOffset;
    if (realTag > 0L) {
        this.transmit(new Reject(realTag, requeue));
        this.metricsCollector.basicReject(this, deliveryTag);
    }
}
```

3. 手动应答的优势：批量应答并且减少网络拥堵。
4. multiple 参数：
   - true：代表批量应答 channel 上所有未应答的消息。
   - false：代表只应答 channel 上队列的第一个消息。
5. 消息重新入队列

消费者由于某些原因失去连接(其通道已关闭，连接已关闭或 TCP 连接丢失)， 导致消息未发送 ACK 确认， RabbitMQ 将了解到消息未完全处理，并将对其重新排队。如果此时其他消费者可以处理，它将很快将其重新分发给另一个消费者。这样，即使某个消费者偶尔死亡，也可以确保不会丢失任何消息。

- ACK 确认：TCP 协议中的消息确认

![image.png](rabbitmq/image-1669758511365.png)

#### 2.8.2 手动应答实现——消费者 basicConsume 方法

要实现手动应答及消息重新入队列，则需要将 basicConsume 方法的第二个参数传入 false，同时第三个参数 deliverCallback 的回调中，将未被消费的消息放回队列。
代码实现：生产者在 worker 基础上不变，消费者 1 设置睡眠时间，当它处理消息时停止程序，再次重启消费者程序，可以看到消息立即被其他消费者获取。
![image.png](rabbitmq/image-1669758513696.png)

### 2.9 可靠投递——发送端

![image.png](rabbitmq/image-1669758517728.png)
发送端可靠投递主要靠消费者发送成功时，Broker 的 confirmCallback 回调和队列的 returnCallback，具体使用见第 4 章。

### 2.10 消息持久化

#### 2.10.1 队列持久化——生产者 queueDeclare 方法

解决 rabbitmq 服务停止，队列丢失的问题。

- 创建队列时，将生产者的 queueDeclare 方法第二个参数传入 true。
- 注意：要持久化的队列要在创建时设定好，不能再后期修改。

#### 2.10.2 消息持久化——生产者 basicPublish 方法

解决 rabbitmq 服务停止，消息丢失的问题。
创建消息时，将生产者的发送消息方法（basicPublish）第 3 个参数设置为 MessageProperties.PERSISTENT_TEXT_PLAIN。
这种方式并不能 完全保证消息不丢失，该方式的可能再存盘的 io 过程中遇到异常情况，因而持久性保证并不强。

### 2.11 其他消息处理机制

#### 2.11.1 不公平分发

在消费者执行 baseConsume 方法前，调用`channe.basicQos(1)`。`channe.basicQos(0)`表示轮询分发，即默认值。
不公平分发情况下，如果该消费者当前消息没处理完，则不会接收新的消息，消息会被派送给其他消费者。

#### 2.11.2 预取值

当 channe.basicQos(）传入>=2 的数时，表示获取队列中的 2 条信息，而不管该消费者的执行速率。

## 第 3 章 死信队列和延迟队列

### 3.1 死信队列

#### 3.1.1 死信介绍

死信，就是无法被消费的消息。
一般来说， producer 将消息投递到 broker 或者直接到 queue 里了， consumer 从 queue 取出消息进行消费，但某些时候由于特定的原因导致 queue 中的某些消息无法被消费，这样的消息如果没有后续的处理，就变成了死信。
有死信自然就有了死信队列。

1. 来源：
   - 消息 TTL 过期
   - 队列达到最大长度(队列满了，无法再添加数据到 mq 中)
   - 消息被拒绝(basic.reject 或 basic.nack)并且 requeue=false
2. 应用：
   - 下单未成功支付订单自动失效。

#### 3.1.2 死信代码架构——过期时间

![image.png](rabbitmq/image-1669758521347.png)
C1 代码：
![image.png](rabbitmq/image-1669758523783.png)
生产者正常生产即可，C2 消费者正常消费即可。
死信队列代码的要点是图中红框部分。

#### 3.1.3 死信代码架构——队列最大长度

![image.png](rabbitmq/image-1669758526563.png)

#### 3.1.4 死信代码架构——消息被拒绝

![image.png](rabbitmq/image-1669758529968.png)

### 3.2 延迟队列

#### 3.2.1 介绍

本质上是死信队列的一种。延时队列就是用来存放需要在指定时间被处理的元素的队列。
使用场景：

1. 订单在十分钟之内未支付则自动取消
2. 新创建的店铺，如果在十天内都没有上传过商品，则自动发送消息提醒。
3. 用户注册成功后，如果三天内没有登陆则进行短信提醒。
4. 用户发起退款，如果三天内没有得到处理则通知相关运营人员。
5. 预定会议后，需要在预定的时间点前十分钟通知各个与会人员参加会议

![image.png](rabbitmq/image-1669758532817.png)

#### 3.2.2 TTL

TTL 是 RabbitMQ 中一个消息或者队列的属性，表明一条消息或者该队列中的所有消息的最大存活时间，单位是毫秒。
如果一条消息设置了 TTL 属性或者进入了设置 TTL 属性的队列，那么这条消息如果在 TTL 设置的时间内没有被消费，则会成为"死信"。
如果同时配置了队列的 TTL 和消息的 TTL，那么较小的那个值将会被使用。
队列 TTL 和消息 TTL 的区别：

- 如果设置了队列的 TTL 属性，那么一旦消息过期，就会被队列丢弃(如果配置了死信队列被丢到死信队列中)。
- 如果只设置了消息的过期时间，消息即使过期，也不一定会被马上丢弃，因为消息是否过期是在即将投递到消费者之前判定的，如果当前队列有严重的消息积压情况，则已过期的消息也许还能存活较长时间。
- 如果不设置 TTL，表示消息永远不会过期。
- 如果将 TTL 设置为 0，则表示除非此时可以直接投递该消息到消费者，否则该消息将会被丢弃。

#### 3.2.3 延迟信息和延迟队列示例

![image.png](rabbitmq/image-1669758537505.png)

1. 创建交换机、队列、并进行绑定

```java
@Configuration
public class TTLQueueConfig {
    public static final String X_EXCHANGE = "X";
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String QUEUEA = "QA";
    public static final String QUEUEB = "QB";
    public static final String DEAD_LETTER_QUEUE = "QD";//死信队列

    /**
     * 声明普通交换机
     * @return
     */
    @Bean("xExchange")//注入时可以使用别名
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    /**
     * 声明死信交换机
     * @return
     */
    @Bean("yExchange")//注入时可以使用别名
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }
    /**
     * 声明普通队列
     * @return
     */
    @Bean("queueA")//注入时可以使用别名
    public Queue queueA(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        arguments.put("x-dead-letter-routing-key", "YD");//设置死信ROUTITNGKEY
        arguments.put("x-message-ttl", 10000);//设置消息过期时间
        return QueueBuilder.durable(QUEUEA).withArguments(arguments).build();
    }
    /**
     * 声明普通队列
     * @return
     */
    @Bean("queueB")//注入时可以使用别名
    public Queue queueB(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);//设置死信交换机
        arguments.put("x-dead-letter-routing-key", "YD");//设置死信ROUTITNGKEY
        arguments.put("x-message-ttl", 40000);//设置消息过期时间
        return QueueBuilder.durable(QUEUEB).withArguments(arguments).build();
    }
    /**
     * 声明死信队列
     * @return
     */
    @Bean("queueD")//注入时可以使用别名
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    /**
     * 队列A绑定普通交换机
     * @param queueA
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");//with()参数XA为routingkey
    }
    /**
     * 队列B绑定普通交换机
     * @param queueB
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueABindingB(@Qualifier("queueB") Queue queueB,
                                  @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");//with()参数XB为routingkey
    }
    /**
     * 队列D绑定死信交换机
     * @param queueD
     * @param yExchange
     * @return
     */
    @Bean
    public Binding queueABindingD(@Qualifier("queueD") Queue queueD,
                                  @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");//with()参数XD为routingkey
    }
}
```

2. 生产者

```java
@RestController
@RequestMapping("ttl")
@Slf4j
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间为{}，发送一条信息给两个队列{}", new Date().toString(), message);
        rabbitTemplate.convertAndSend("X", "XA","消息来自ttl为10s的队列" + message);
        rabbitTemplate.convertAndSend("X", "XB","消息来自ttl为40s的队列" + message);
    }
}
```

3. 消费者

```java
@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @RabbitListener(queues = "QD")//接收消息，队列QD
    public void receiveD(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间为{}，收到的死信队列消息{}", new Date().toString(), msg);
    }
}
```

4. 测试：浏览器请求：`http://localhost:8080/ttl/sendMsg/嘻嘻嘻`
   - ![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661819294052-7196f9c4-0d39-4ea4-82a6-44a72a4ed75e.png#averageHue=%23f0eeec&clientId=ud8da96fa-c680-4&crop=0&crop=0&crop=1&crop=1&errorMessage=unknown%20error&from=paste&height=74&id=u126c8901&margin=%5Bobject%20Object%5D&name=image.png&originHeight=74&originWidth=1459&originalType=binary∶=1&rotation=0&showTitle=false&size=31091&status=error&style=none&taskId=u3d8ca033-55a2-452e-ae45-21bc269cf33&title=&width=1459)
5. 存在问题：代码灵活性不高，队列没有扩展性，新增延时时就需要重新创建队列。
6. 优化：不设置过期时间的队列 QC，让生产者设置消息过期时间。

![image.png](rabbitmq/image-1669758542745.png)
![image.png](rabbitmq/image-1669758545098.png)
![image.png](rabbitmq/image-1669758547436.png)
![image.png](rabbitmq/image-1669758549635.png)

7. 存在问题：虽然实现了在发送消息时设置过期时间，但同时发两条消息时，如果第一个消息未发送，则会阻塞后续消息的发送（队列特征），导致延迟时间不起作用。

#### 3.2.4 Rabbitmq 插件实现延迟队列（新增延迟交换机）

插件用于优化上述第 7 点问题。

1. 安装插件：找到`/usr/lib/rabbitmq/lib/rabbitmq_server-3.8.8/plugins目录`
   - ![image.png](rabbitmq/image-1669758553829.png)
   - ![image.png](rabbitmq/image-1669758555819.png)
   - 将插件包`rabbitmq_delayed_message_exchange-3.8.0.ez`拷贝至 plugins 目录中：
   - ![image.png](rabbitmq/image-1669758551818.png)
   - 使用：`rabbitmq-plugins enable rabbitmq_delayed_message_exchange`安装插件
   - ![image.png](rabbitmq/image-1669758558027.png)
2. 重启 rabbitmq：执行`rabbitmqctl start_app`或者`systemctl restart rabbitmq-server`重启服务。
3. 登录 web 管理控制台，在 exchanges 界面新增 exchange，检查是否出现`x-delayed-message`选项：
   - ![image.png](rabbitmq/image-1669758560191.png)
4. 插件作用分析：默认情况下，延时发生在队列中，安装插件后，演示发生在交换机。

#### 3.2.5 延迟交换机示例

![image.png](rabbitmq/image-1669758563603.png)

1. 消费者、生产者没有变化，创建交换机发生变化：

![image.png](rabbitmq/image-1669758565525.png)
![image.png](rabbitmq/image-1669758571542.png)

### 3.3 延迟队列总结

延时队列在需要延时处理的场景下非常有用，使用 RabbitMQ 来实现延时队列可以很好的利用 RabbitMQ 的特性，如：消息可靠发送、消息可靠投递、死信队列来保障消息至少被消费一次以及未被正确处理的消息不会被丢弃。另外，通过 RabbitMQ 集群的特性，可以很好的解决单点故障问题，不会因为单个节点挂掉导致延时队列不可用或者消息丢失。
当然，延时队列还有很多其它选择，比如利用 Java 的 DelayQueue，利用 Redis 的 zset，利用 Quartz 或者利用 kafka 的时间轮，这些方式各有特点,看需要适用的场景

## 第 4 章 发布确认高级

### 4.1 介绍

#### 4.1.1 问题来源

在生产环境中由于一些不明原因，导致 rabbitmq 重启，在 RabbitMQ 重启期间生产者消息投递失败，导致消息丢失，需要手动处理和恢复。

#### 4.1.2 解决思路

![image.png](rabbitmq/image-1669758578558.png)
如果 rabbitmq 宕机，发送者根本不知道消息发到了那里，为了解决 rabbitmq 宕机，思路一：引入缓存，当交换机不可用时，由缓存接收数据。思路二：为了让发送者知道交换机不可用，则需要交换机给发送者一个确认收到消息的回调，为了保证消息不丢失，再将消息返回给生产者，生产者再进一步处置。

### 4.2 代码实现

#### 4.2.1 配置文件开启发布确认功能

spring.rabbitmq.publisher-confirm-type=correlated

- NONE：禁用发布确认模式，是默认值
- CORRELATED：发布消息成功到交换器后会触发回调方法
- SIMPLE：（相当于单个确认，参见 2.3）
  - 其一效果和 CORRELATED 值一样会触发回调方法，
  - 其二在发布消息成功后使用 rabbitTemplate 调用 waitForConfirms 或 waitForConfirmsOrDie 方法，等待 broker 节点返回发送结果，根据返回结果来判定下一步的逻辑。
    - 注意：waitForConfirmsOrDie 方法如果返回 false 则会关闭 channel，则接下来无法发送消息到 broker

#### 4.2.2 增加交换机回调接口

该回调接口可以单独写，也可以让生产者实现，这里采用了单独写的方式。
![image.png](rabbitmq/image-1669758582718.png)
消费者和提供者代码不变（同 2.3），测试结果如下：
![image.png](https://cdn.nlark.com/yuque/0/2022/png/1604140/1661826276751-385bfb57-f145-466c-a2c0-df310e667b99.png#averageHue=%23f3f1ef&clientId=ud8da96fa-c680-4&crop=0&crop=0&crop=1&crop=1&errorMessage=unknown%20error&from=paste&height=70&id=u8ca78ad0&margin=%5Bobject%20Object%5D&name=image.png&originHeight=70&originWidth=1366&originalType=binary∶=1&rotation=0&showTitle=false&size=27791&status=error&style=none&taskId=u0c4b45fa-0828-42e6-8128-1da9f9ee4ae&title=&width=1366)
测试交换机不可用（如改名字）、routingkey 不可用，会看到相应的提示。
此时已经实现了交换机给发送者返回是否收到的消息，但队列还没收到消息的问题还没解决，首先肯定的是队列收不到消息，则需要对收不到的消息进行回退，或者加入死信队列。

#### 4.2.3 回退消息

在仅开启了生产者确认机制的情况下，交换机接收到消息后，会直接给消息生产者发送确认消息。
但由于该消息不可路由，该消息会被直接丢弃，此时生产者是不知道消息被丢弃这个消息。
RabbitTemplate 中，mandatory 参数可以在当消息传递过程中不可达目的地时将消息返回给生产者。

1. 核心配置文件中增加`spring.rabbitmq.publisher-returns=true`，开启回退功能。
2. 在 callback 类中新增实现接口`RabbitTemplate.ReturnCallback`，重写`returnedMessage()`，并将当前类注入 RabbitTemplate。

![image.png](rabbitmq/image-1669758586908.png)
![image.png](rabbitmq/image-1669758595015.png)
setMandatory 设置为 false 的结果。
![image.png](rabbitmq/image-1669758592450.png)

### 4.3 备份交换机

#### 4.3.1 介绍

mandatory 参数和回退消息，使 rabbitmq 获得了对无法投递消息的感知能力，有机会在生产者的消息无法被投递时发现并处理。
但此时，只是知道了这些信息，还不知道如何处理。仅打印日志来处理这些无法路由的消息是很不优雅的做法。
前文中为队列设置死信交换机来存储那些处理失败的消息，但是这些不可路由消息根本没有机会进入到队列，因此无法使用死信队列来保存消息。
在 RabbitMQ 中，有一种备份交换机的机制存在，可以很好的应对这个问题。当为某一个交换机声明一个对应的备份交换机时，就是为它创建一个备胎，当交换机接收到一条不可路由消息时，将会把这条消息转发到备份交换机中，由备份交换机来进行转发和处理。
通常备份交换机的类型为 Fanout ，这样就能把所有消息都投递到与其绑定的队列中，然后我们在备份交换机下绑定一个队列，这样所有那些原交换机无法被路由的消息，就会都进入这个队列了。
同时，还可以建立一个报警队列，用独立的消费者来进行监测和报警。
![image.png](rabbitmq/image-1669758600620.png)

#### 4.3.2 代码实现

1. 配置类——声明交换机，备份交换机声明同普通声明，确认交换机需要构造。

![image.png](rabbitmq/image-1669758603087.png)

2. 报警消费者与其他消费者无异。

![image.png](rabbitmq/image-1669758605157.png)
注意：mandatory 参数与备份交换机同时使用时，备份交换机优先级高 。

## 第 5 章 rabbitmq 扩展知识

### 5.1 幂等性

#### 5.1.1 使用场景

幂等性即消息重复消费。
消费者在消费 MQ 中的消息时， MQ 已把消息发送给消费者，消费者在给 MQ 返回 ack 时网络中断，故 MQ 未收到确认信息，该条消息会重新发给其他的消费者，或者在网络重连后再次发送给该消费者，但实际上该消费者已成功消费了该条消息，造成消费者消费了重复的消息。

#### 5.1.2 解决方案

思路：MQ 消费者的幂等性的解决一般使用全局 ID 或者写个唯一标识比如时间戳 或者 UUID 或者订单消费者消费 MQ 中的消息也可利用 MQ 的该 id 来判断，或者可按自己的规则生成一个全局唯一 id，每次消费消息时用该 id 先判断该消息是否已消费过。
操作方法：

1. 唯一 ID+指纹码机制 ： 用户规则+时间戳+任意唯一信息码。利用查询语句进行判断这个 id 是否存在数据库中。优势是实现简单；劣势就是在高并发时，如果是单个数据库就会有写入性能瓶颈，当然也可以采用分库分表提升性能，但也不是最推荐的方式。
2. Redis 原子性：利用 redis 执行 setnx 命令，天然具有幂等性。从而实现不重复消费。

### 5.2 优先级队列

#### 5.2.1 使用场景

订单催付的场景。
曾经后端系统是使用 redis 来存放的定时轮询，redis 只能用 List 做一个简单的消息队列，并不能实现一个优先级的场景。
RabbitMQ 可以设定消息的优先级，否则就是默认优先级。

#### 5.2.2 设置方式

1. web 控制台设置方式：

![image.png](rabbitmq/image-1669758609483.png)
![image.png](rabbitmq/image-1669758615779.png)

2. 代码实现方式：

```java
//队列设置优先级
Map<String, Object> params = new HashMap();
params.put("x-max-priority", 10);
channel.queueDeclare("hello", true, false, false, params);
// 消息设置优先级
AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
channel.basicPublish("", QUEUE_NAME, properties, message.getBytes());
```

注意：要让队列实现优先级需要做的事情有如下事情:队列需要设置为优先级队列，消息需要设置消息的优先级，消费者需要等待消息已经发送到队列中才去消费因为，这样才有机会对消息进行排序。

### 5.3 惰性队列

#### 5.3.1 使用场景

惰性队列：消息保存在磁盘中，防止队列中消息积压占满内存。
默认情况下，当生产者将消息发送到 RabbitMQ 的时候，队列中的消息会尽可能的存储在内存之中，这样可以更加快速的将消息发送给消费者。
即使是持久化的消息，在被写入磁盘的同时也会在内存中驻留一份备份。当 RabbitMQ 需要释放内存的时候，会将内存中的消息换页至磁盘中，这个操作会耗费较长的时间，也会阻塞队列的操作，进而无法接收新的消息。

#### 5.3.2 模式

队列具备两种模式： default 和 lazy。默认的为 default 模式。
lazy 模式即为惰性队列的模式，可以通过调用 channel.queueDeclare 方法的时候在参数中设置，也可以通过 Policy 的方式设置，如果一个队列同时使用这两种方式设置的话，那么 Policy 的方式具备更高的优先级。
`rabbitmqctl set_policy Lazy "^myqueue$" '{"queue-mode":"lazy"}' --apply-to queues`
如果要通过声明的方式改变已有队列的模式的话，那么只能先删除队列，然后再重新声明一个新的。
在队列声明的时候可以通过“x-queue-mode”参数来设置队列的模式，取值为“default”和“lazy”。

```java
Map<String, Object> args = new HashMap<String, Object>();
args.put("x-queue-mode", "lazy");
channel.queueDeclare("myqueue", false, false, false, args);
```

## 第 6 章 RabbitMQ 集群

### 6.1 搭建集群

#### 6.1.1 搭建集群要求

集群必须使用相同的 cookie。

#### 6.1.2 搭建步骤

搭建 node2 备用 node1，node3 备用 node2 的集群。

1. 复制 node1 机器的 cookie 给 node2 和 node3。在 node1 上执行远程操作命令：
   - `scp /var/lib/rabbitmq/.erlang.cookie root@node2:/var/lib/rabbitmq/.erlang.cookie`
   - `scp /var/lib/rabbitmq/.erlang.cookie root@node3:/var/lib/rabbitmq/.erlang.cookie`
2. 启动 RabbitMQ 服务,顺带启动 Erlang 虚拟机和 RbbitMQ 应用服务(在三台节点上分别执行以下命令)：`rabbitmq-server -detached`
3. 在 node2 执行（将 node2 添加给 node1）：
   - `rabbitmqctl stop_app`：rabbitmqctl stop 会将 Erlang 虚拟机关闭， rabbitmqctl stop_app 只关闭 RabbitMQ 服务
   - `rabbitmqctl reset`
   - `rabbitmqctl join_cluster rabbit@node1`
   - `rabbitmqctl start_app`(只启动应用服务)
4. 在 node3 执行（将 node3 添加给 node2）：
   - `rabbitmqctl stop_app`：`rabbitmqctl stop` 会将 Erlang 虚拟机关闭， `rabbitmqctl stop_app`只关闭 RabbitMQ 服务
   - `rabbitmqctl reset`
   - `rabbitmqctl join_cluster rabbit@node1`
   - `rabbitmqctl start_app`(只启动应用服务)
5. 查看集群状态（在任意节点执行）：`rabbitmqctl cluster_status`
   - ![image.png](rabbitmq/image-1669758620699.png)
6. 需要重新设置用户（任选一台）
   - 创建账号`rabbitmqctl add_user admin 123`
   - 设置用户角色`rabbitmqctl set_user_tags admin administrator`
   - 设置用户权限`rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*"`
   - 登录 web 控制台查看：
   - ![image.png](rabbitmq/image-1669758623749.png)
7. 解除集群节点(演示解除 node2，在 node1 机器执行)
   - `rabbitmqctl stop_app`
   - `rabbitmqctl reset`
   - `rabbitmqctl start_app`
   - `rabbitmqctl cluster_status`
   - `rabbitmqctl forget_cluster_node rabbit@node2`

### 6.2 镜像队列

#### 6.2.1 介绍

上述过程搭建了一个集群，但此时还没有生效，不能共享队列。
如果 RabbitMQ 集群中只有一个 Broker 节点，那么该节点的失效将导致整体服务的临时性不可用，并且也可能会导致消息的丢失。
可以将所有消息都设置为持久化，并且对应队列的 durable 属性也设置为 true，但是这样仍然无法避免由于缓存导致的问题：因为消息在发送之后和被写入磁盘井执行刷盘动作之间存在一个短暂却会产生问题的时间窗。
通过 publisherconfirm 机制能够确保客户端知道哪些消息己经存入磁盘，尽管如此， 一般不希望遇到因单点故障导致的服务不可用。
引入镜像队列(Mirror Queue)的机制，可以将队列镜像到集群中的其他 Broker 节点之上，如果集群中的一个节点失效了，队列能自动地切换到镜像中的另一个节点上以保证服务的可用性。

#### 6.2.2 搭建过程

任意登录一台 node 的 web 管理控制台，在 admin 界面，选择右侧 policy：
![image.png](rabbitmq/image-1669758626351.png)
此时，如果 node1 停机，node2 会成为镜像队列。

### 6.3 Haproxy+Keepalive 实现高可用负载均衡

#### 6.3.1 介绍

前面已经实现了镜像队列，保证收到的消息不丢失。
但目前还是固定连接到 node1，如果 node1 下线，则存在收不到消息的情况。
![image.png](rabbitmq/image-1669758628210.png)

#### 6.3.2 Haproxy 实现负载均衡

HAProxy 提供高可用性、负载均衡及基于 TCPHTTP 应用的代理，支持虚拟主机，它是免费、快速并且可靠的一种解决方案，包括 Twitter,Reddit,StackOverflow,GitHub 在内的多家知名互联网公司在使用。
HAProxy 实现了一种事件驱动、单一进程模型，此模型支持非常大的井发连接数。
注意：负载均衡的代理服务器还有 nginx,lvs。之间的区别: `http://www.ha97.com/5646.html`
以下演示将本地主机作为负载均衡服务器。

1. 在所有节点上下载 haproxy：`yum -y install haproxy`
2. 修改所有节点的 ip 为本地主机 ip：`vim /etc/haproxy/haproxy.cfg`
   - ![image.png](rabbitmq/image-1669758631613.png)
3. 分别启动 haproxy：
   - `haproxy -f /etc/haproxy/haproxy.cfg`
   - `ps -ef | grep haproxy`
4. 访问：http://localhost:8888/stats 查看

#### 6.3.3 Keepalived 实现双机(主备)热备

上述过程使用本地主机模拟了 haproxy 代理服务器，如果该服务器下线，仍然不能保证服务与集群的有效连接。

---

下面没懂，就不写了。
网上找了一张架构图，大概意思应该是 master 和 backup 两台主机均安装 keeplived 和 haproxy。但是请求应该发给谁呢？图中的 192.168.1.4 是请求来源呢，还是接收请求的服务器？请求接到后怎么转给 master 或 backup 呢？
![image.png](rabbitmq/image-1669758634579.png)

### 6.4 Federation Exchange（联合交换机）

异地 broker，数据不一致。数据在 A 地，请求发到了 B 地。在搭建了集群的情况下，能够实现数据从 A 调到 B 地，但远距离传输存在延迟，影响性能。
Federation Exchange 主要作用是从 A 地共享数据给 B 地，但不能从 B 地到 A 地。（不能双向共享有毛用啊！）
![image.png](rabbitmq/image-1669758636741.png)
须安装 Federation 插件：

1. 在每台机器上开启 federation 相关插件：
   - `rabbitmq-plugins enable rabbitmq_federation`
   - `rabbitmq-plugins enable rabbitmq_federation_management`
2. 先运行 consumer 在 node2 创建 fed_exchange。
3. downstream(node2)配置 upstream(node1)
   - ![image.png](rabbitmq/image-1669758639931.png)
   - amqp://admin:123@node1
     - admin：账号
     - 123：密码
     - node1：上游主机名
4. 添加 policy：
   - ![image.png](rabbitmq/image-1669758643280.png)
5. 成功状态：
   - ![image.png](rabbitmq/image-1669758645683.png)

### 6.5 Federation Queue（联合队列）

联邦队列可以在多个 Broker 节点(或者集群)之间为单个队列提供均衡负载的功能。一个联邦队列可以连接一个或者多个上游队列(upstream queue)，并从这些上游队列中获取消息以满足本地消费者消费消息的需求。
![image.png](rabbitmq/image-1669758647318.png)
图示表示的是节点 2 联邦到节点 1，数据使用节点 1 流向节点 2 的。

1. 编写代码
2. 添加 stream（同联邦交换机）
3. 添加 policy
   - ![image.png](rabbitmq/image-1669758649317.png)

### 6.6 Shovel

与 Federation 具备的数据转发功能类似， Shovel 够可靠、持续地从一个 Broker 中的队列(作为源端，即 source)拉取数据并转发至另一个 Broker 中的交换器(作为目的端，即 destination)。
作为源端的队列和作为目的端的交换器可以同时位于同一个 Broker，也可以位于不同的 Broker 上。
![image.png](rabbitmq/image-1669758650953.png)

1. 开启插件(需要的机器都开启)
   - `rabbitmq-plugins enable rabbitmq_shovel`
   - `rabbitmq-plugins enable rabbitmq_shovel_management`
2. 添加 shovel 源和目的地
   - ![image.png](rabbitmq/image-1669758653086.png)

### 6.6 **Federation/Shovel 与集群的**对比

| **Federation/Shovel**                                                               | **cluster**                                                |
| ----------------------------------------------------------------------------------- | ---------------------------------------------------------- |
| exchange 是逻辑分离的,可能有不同拥有者                                              | 单个逻辑 exchange                                          |
| 不限制 rabbitmq 和 erlang 版本                                                      | rabbitmq 和 erlang 版本要保持一致                          |
| exchange 可以通过不可靠(公网)网络连接,<br/>直接使用 amqp 连接,但是需要设置用户权限. | 可靠(内网)网络,通信依赖 Erlang interode,共享 erlang cookie |
| 拓扑结构可以是单项或双向                                                            | 节点两两互联                                               |
| cap 理论中的 ap                                                                     | cap 理论中的 cp                                            |
| exchange 可以有单独的信息,有些消息是本地的                                          | 每个节点的消息都是相同的                                   |
| 客户端只能看到连接的服务器队列                                                      | 客户端可以看到集群内所有队列                               |

shovel 跟 federation 使用类似,但使用的是 erlang client,更底层,更多配置,也更灵活. 默认使用动态配置。
shovel 特性:

1. 松耦合 不限制 vhost,user 甚至 erlang/rabbitmq 的版本
2. 网络友好 不限制网络范围,遵循 amqp 协议即可
3. 高自由度 每个节点可以随意组合其他节点
