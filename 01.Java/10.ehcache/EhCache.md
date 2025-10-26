---
title: EhCache
urlname: sbfqth
date: '2022-10-02 11:39:52 +0800'
tags: [EhCache]
categories: [缓存]
---

*EhCache 是一种广泛使用的开源 Java 分布式缓存。主要面向通用缓存,Java EE 和轻量级容器。可以和大部分 Java 项目无缝整合，例如： Hibernate 中的缓存就是基于 EhCache 实现的。*
<!-- more -->

## 1 介绍

ehcache 可以保存登录信息到缓存里，防止高并发情况下数据库的访问压力，主要解决本地缓存的问题，分布式缓存共享不便。

EhCache 是一种广泛使用的开源 Java 分布式缓存。主要面向通用缓存,Java EE 和轻量级容器。可以和大部分 Java 项目无缝整合，例如： Hibernate 中的缓存就是基于 EhCache 实现的。

EhCache 支持内存和磁盘存储，默认存储在内存中，如内存不够时把缓存数据同步到磁盘中。 EhCache 支持基于 Filter 的 Cache 实现，也支持 Gzip 压缩算法。
优点：EhCache 直接在 JVM 虚拟机中缓存，速度快，效率高。

缺点：缓存共享麻烦，集群分布式应用使用不方便。

## 2 基本使用

### 2.1 引入依赖

```xml
<dependencies>
  <dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.6.11</version>
    <type>pom</type>
  </dependency>
</dependencies>
```

### 2.2 配置 EhCache

在 resouces 目录下创建 ehcache.xml 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache>
    <!--磁盘的缓存位置-->
    <diskStore path="java.io.tmpdir/ehcache"/>
    <!--默认缓存-->
    <defaultCache
            maxEntriesLocalHeap="10000"
            eternal="false"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            maxEntriesLocalDisk="10000000"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
        <persistence strategy="localTempSwap"/>
    </defaultCache>

    <!--helloworld缓存-->
    <cache name="HelloWorldCache"
           maxElementsInMemory="1000"
           eternal="false"
           timeToIdleSeconds="5"
           timeToLiveSeconds="5"
           overflowToDisk="false"
           memoryStoreEvictionPolicy="LRU"/>

    <!--
     defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
   -->
    <!--
      name:缓存名称。
      maxElementsInMemory:缓存最大数目
      maxElementsOnDisk：硬盘最大缓存个数。
      eternal:对象是否永久有效，一但设置了，timeout将不起作用。
      overflowToDisk:是否保存到磁盘，当系统宕机时
      timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
      timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
      diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
      diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
      diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
      memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
      clearOnFlush：内存数量最大时是否清除。
      memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
      FIFO，first in first out，这个是大家最熟的，先进先出。
      LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
      LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
   -->
</ehcache>
```

### 2.3 测试基本使用

```java
public class TestEH {
    public static void main(String[] args) {
        //获取编译目录下的资源的流对象
        InputStream input = TestEH.class.getClassLoader().getResourceAsStream("ehcache.xml");
        //获取 EhCache 的缓存管理对象
        CacheManager cacheManager = new CacheManager(input);
        //获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");
        //创建缓存数据
        Element element = new Element("name","zhang3");
        //存入缓存
        cache.put(element);
        //从缓存中取出
        Element element1 = cache.get("name");
        System.out.println(element1.getObjectValue());
    }
}
```

## 3 整合 Shiro

### 3.1 引入依赖

```xml
<!--Shiro整合EhCache-->
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-ehcache</artifactId>
    <version>1.4.2</version>
</dependency>
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

### 3.2 配置 EhCache

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache name="ehcache" updateCheck="false">
    <!--磁盘的缓存位置-->
    <diskStore path="java.io.tmpdir"/>
    <!--默认缓存-->
    <defaultCache
            maxEntriesLocalHeap="1000"
            eternal="false"
            timeToIdleSeconds="3600"
            timeToLiveSeconds="3600"
            overflowToDisk="false">
    </defaultCache>
    <!--登录认证信息缓存：缓存用户角色权限-->
    <cache name="loginRolePsCache"
           maxEntriesLocalHeap="2000"
           eternal="false"
           timeToIdleSeconds="600"
           timeToLiveSeconds="0"
           overflowToDisk="false"
           statistics="true"/>
</ehcache>
```

### 3.3 将要保存的信息添加到缓存管理器

创建 springboot 和 shiro 的整合环境，在此基础上，在配置类中添加代码，获取缓存管理器：

```java
@Configuration
public class ShiroConfig {
    //配置 SecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        //1 创建 defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //2 创建加密对象，并设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.1 采用 md5 加密
        matcher.setHashAlgorithmName("md5");
        //2.2 迭代加密次数
        matcher.setHashIterations(3);
        //3 将加密对象存储到 myRealm 中
        myRealm.setCredentialsMatcher(matcher);
        //4 将 myRealm 存入 defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(myRealm);
        //4.1 设置 rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        // 4.6设置缓存管理器
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        //5 返回
        return defaultWebSecurityManager;
    }
    //缓存管理器
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        InputStream is =null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache-shiro.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CacheManager cacheManager = new CacheManager(is);
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }
}
```

此时再进行登录测试，第一次登录会查询数据库获取角色、权限信息，后续登出，再登录时，可以正常进行页面访问，但没有再进行数据库查询，说明使用了缓存。
