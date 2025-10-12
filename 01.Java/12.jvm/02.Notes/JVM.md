---
title: JVM
urlname: dgxehd
date: '2022-07-18 07:51:00 +0800'
tags: [JVM]
categories: [JVM]
---
*Java Virtual Machine，Java 虚拟机的简称，是 Java 技术的核心。是专门为执行单个计算机程序而设计。本文详细描述了Java虚拟机的构成、发展以及使用调优。并对Java类在虚拟机的加载过程、保存、存放形式进行研究。*
<!-- more -->
## 第 1 章 JVM 和 Java 体系结构

> 参考书
> 
> - Java 虚拟机规范（Java SE 8 版）
> - 深入理解 Java 虚拟机（JVM 高级特性与最佳实践）【第 2/3 版】
> - 深入理解 JVM&G1 GC
> - 揭秘 Java 虚拟机（JVM 设计原理与实践）
> - Java 虚拟机基础教程
> - 实战 Java 虚拟机（JVM 故障诊断与性能优化）
> - Java 虚拟机精讲
> - 码出高效（Java 开发手册）
> - 自己动手写 Java 虚拟机

### 1.1 JVM 介绍

#### 1.1.1 认识 JVM

1. JVM：Java Virtual Machine，Java 虚拟机的简称，是 Java 技术的核心。
2. 虚拟机：
   - 系统虚拟机：如 Visual Box，VMware，提供了一个可运行完整操作系统的软件平台
   - 程序虚拟机：如 JVM，专门为执行单个计算机程序而设计。
3. 作用：二进制字节码的运行环境。
4. 特点：
   - 一次编译，到处运行
   - 自动内存管理
   - 自动垃圾回收
5. JVM 拥有语言无关性，并未与 Java 语言绑定，只要该编程语言转换为字节码文件遵循了 Java 虚拟机的规范，它就能被 Java 虚拟机所识别并装在运行。
6. 任何能够再 Java 虚拟机上执行的字节码格式均一样，统称为 jvm 字节码。
7. JVM 是遵循 JSR-292 规范的，该规范的一系列项目推动 JVM 从“Java 语言的虚拟机”向“多语言虚拟机”方向发展。

#### 1.1.2 Java 和 JVM 的重大发展历程

1. 2000 年，SUN 发布了 JDK1.3，同时发布了 Java HotSpot Virtual Machine，成为 Java 的默认虚拟机。
2. 2006 年，SUN 将 Java 开源，称为 open JDK。
3. 2008 年，Oracle 收购了 BEA，得到了 JRockit 虚拟机。
4. 2010 年，Oracle 收购了 SUN，获得了 Java 商标和 HotSpot 虚拟机，并启动了将 JRockit 整合至 HotSpot 的计划。
5. 2011 年，发布 JDK7，正式启用新垃圾回收器 G1。
6. 2014 年，发布 JDK8，基本完成了 JRockit 向 HotSpot 的整合。【目前所用版本】
7. 2017 年，发布 JDK9，G1 称为默认 GC。
8. 2017 年，IBM 开源 J9（虚拟机）
9. 2018 年，发布 JDK11，发布 ZGC——一款新的 GC
10. 2019 年，JDK12 发布，open JDK 加入了 RedHat 开发的 Shenandoah GC——一款 GC

#### 1.1.3 各类 JVM

1. 1996 年，SUN 发布 Java1.0，发布了 Sun Classic VM。虚拟机内部只提供解释器，使用 JIT 编译器需要进行外挂，同时会接管虚拟机执行系统，二者不能配合工作。
2. Exact VM，jdk1.2 时发布，使得编译器和解释器可以混合工作。
3. HotSpot VM，jdk1.3 时发布，成为**默认虚拟机**。
4. JRockit，BEA 发布，JDK8 时，整合至 HotSpot VM
5. J9，IBM 发布，全称 IBM Technology for Java Virtual Machine，简称 IT4J，J9 是内部代号，2017 年开源 J9 VM，称为 OpenJ9，由 Eclipse 基金会管理，又称 Eclipse OpenJ9
6. KVM 和 CDC/CLDC Hotspot，Oracle 发布的 Java ME 虚拟机
7. Azul VM，与特定硬件平台绑定、软硬件配合的专用虚拟机
8. Liquid VM，自身实现了一个专用操作系统的必要功能。
9. Apache Harmony
10. Microsoft JVM，只能再 windows 平台下运行
11. Taobao JVM，深度定制且开源

### 1.2 JVM 体系

#### 1.2.1 JVM 的整体结构

JVM 由类加载子系统、运行时数据区、执行引擎、本地方法接口、本地方法库组成，采用了解释器与编译器并行的架构。其结构简图如下：
![第02章_JVM架构-简图.jpg](jvm/第02章_JVM架构-简图.jpg)
详图如下：
![第02章_JVM架构-中.jpg](jvm/第02章_JVM架构-中.jpg)

#### 1.2.2 Java 代码执行流程

![image.png](jvm/image.png)

#### 1.2.3 JVM 的架构模型

1. Java 编译器输入指令流是基于栈的指令集架构【原因是跨平台设计】。
2. 基于栈式架构的特点：
   - 设计和实现更简单，适用于资源受限的系统
   - 避开了寄存器的分配难题：使用零地址指令方式分配
   - 指令流中的指令大部分是零地址指令，其执行过程依赖于操作栈。指令集更小，编译器容易实现
   - 不需要硬件支持，可移植性更好，更好实现跨平台
3. 基于寄存器架构的特点：
   - 典型的应用是 x86 的二进制指令集：比如传统的 PC 以及 Android 的 Davlik 虚拟机。
   - 指令集架构则完全依赖硬件，与硬件的耦合度高，可移植性差。
   - 性能优秀和执行更高效
   - 花费更少的指令去完成一项操作
   - 在大部分情况下，基于寄存器架构的指令集往往都以一地址指令、二地址指令和三地址指令为主，而基于栈式架构的指令集却是以零地址指令为主

#### 1.2.4 JVM 的生命周期

1. 虚拟机的启动
   - Java 虚拟机的启动是通过引导类加载器（bootstrap class loader）创建一个初始类（initial class）来完成的，这个类是由虚拟机的具体实现指定的。
2. 虚拟机的执行
   - 一个运行中的 Java 虚拟机有着一个清晰的任务：执行 Java 程序
   - 程序开始执行时他才运行，程序结束时他就停止。
   - 执行一个所谓的 Java 程序的时候，真真正正在执行的是一个叫做 Java 虚拟机的进程。
3. 虚拟机的退出
   - 程序正常执行结束
   - 程序在执行过程中遇到了异常或错误而异常终止。
   - 由于操作系统用现错误而导致 Java 虚拟机进程终止。
   - 某线程调用 Runtime 类或 System 类的 exit( )方法，或 Runtime 类的 halt( )方法，并且 Java 安全管理器也允许这次 exit( )或 halt( )操作。
   - 除此之外，JNI（Java Native Interface）规范描述了用 JNI Invocation API 来加载或卸载 Java 虚拟机时，Java 虚拟机的退出情况。

## 第 2 章 类加载子系统

### 2.1 作用

![image.png](/jvm/image-1669529615702.png)
类加载器子系统负责从文件系统或者网络中加载 Class 文件，要求 class 文件在文件开头有特定的文件标识（符合某种语言规范——如 Java 规范）。

### 2.2 ClassLoader

ClassLoader 只负责 class 文件的加载，至于 class 文件是否可以运行则由 Execution Engine（执行引擎）决定。
加载的类信息（称为 DNA 元数据模板），存放于一块称为方法区的内存空间。除了类信息外，方法区中还会存放运行时常量池的信息，可能还包括字符串字面量和数字常量（这部分常量信息是 Class 文件中常量池部分的内存映射）。

> - 一句话理解：就是要给把`.class`文件加载到 JVM 的的搬运工，加载以流的形式加载。

#### 2.2.1 ClassLoader 的常用方法和获取方法

常用方法：
![1664940176765.jpg](/jvm/1664940176765.jpg)
获取方法：

- 获取当前类的 ClassLoader
  - clazz.getClassLoader()，clazz 是类的 Class 对象【参考反射】
- 获取当前线程上下文的 ClassLoader
  - Thread.currentThread().getContextClassLoader()
- 获取系统的 ClassLoader（扩展类加载器：ExtClassLoader）
  - ClassLoader.getSystemClassLoader()
- 获取调用者的 ClassLoader
  - DriverManager.getCallerClassLoader

### 2.3 加载流程

![第02章_类的加载过程.jpg](jvm/第02章_类的加载过程.jpg)
![image.png](jvm/image-1669529630282.png)

- 阶段一：加载。
  - 通过一个类的全限定名获取定义此类的二进制字节流，将这个字节流代表的静态存储结构转化为方法区的运行时数据结构。
  - 在内存中生成一个代表这个类的 java.lang.Class 对象，作为方法区的这个类的各种数据的访问入口。
  - 加载`.class`文件的方式：
    - 从本地系统中直接加载。
    - 通过网络获取，典型场景：Web Applet。
    - 从 Zip 压缩包中获取，称为日后 jar、war 格式的基础。
    - 运行时计算生成，使用最多的就是：动态代理技术（java.lang.reflact.Proxy)。
    - 从其他文件生成，典型场景：JSP 应用。
    - 从专有数据库中提取.class 文件，比较少见。
    - 从加密文件中获取，典型的防 Class 文件被反编译的保护措施。
- 阶段二：链接。
  - **验证**
    - 目的在于确保 Class 文件的字节流中包含信息符合当前的虚拟机要求，保证被加载类的正确性，不会危害虚拟机的自身安全。
      - 可在 Java 虚拟机运行的二进制文件开头是 CA FE BA BE，称为魔数。
    - 注意包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证。
  - **准备**
    - 为**类变量**（static 修饰的变量）分配内存并且设置该类变量的默认初始值。
    - 这里不包含用 final 修饰的 static，因为 final 在编译的时候就会分配了~~，准备阶段会显示初始化~~。
    - 这里不会为**实例变量**分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到 Java 堆中。
  - **解析**
    - 就常量池内的符号引用转化为直接引用的过程。
    - 事实上，解析操作往往会随着 JVM 在执行完初始化之后再执行。
    - 符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《Java 虚拟机规范》的 Class 文件格式中，直接引用的就是直接指向目标的指针、相对偏移或者一个简介定位到目标的句柄。
    - 解析动作主要针对类或者接口、字段、类方法、接口方法、方法类型。对应常量池中的 CONSTANT_Class_info、CONSTANT_Fieldref_info、CONSTANT_Methodref_info 等。
- 阶段三：初始化。
  - 初始化阶段就是执行类构造器方法<clinit>()的过程。
  - 此方法不需要定义，是 Javac 编译器自动收集类中所有的类变量的赋值动作和静态代码块中的语句合并而来。构造器方法中指令按语句在源文件中出现的顺序执行。
  - <clinit>()不同于类的构造器，只有代码中有静态变量或静态代码块，字节码文件才有此方法。
  - 若该类有父类，JVM 会保证子类的()执行之前，父类的()已经执行完毕
  - 虚拟机必须保证一个类的<clinit>() 方法在多线程下被同步加锁。

### 2.4 类加载器分类

#### 2.4.1 概述

![image.png](jvm/image-1669529639107.png)
JVM 支持两种类型的类加载器，分别是引导类加载器（Bootstrap ClassLoader）和自定义加载器（User-Defined ClassLoader）——Java 虚拟机规范将所有派生于抽象类 ClassLoader 的类加载器都称为自定义类加载器。

```java
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);
        // 获取其上层：拓展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        // sun.misc.Launcher$ExtClassLoader@1540e19d
        System.out.println(parent);
        // 获取顶层加载器 BootStrapClassLoader 获取不到 因为C/C++写的
        ClassLoader parent1 = parent.getParent();
        // null
        System.out.println(parent1);

        // 对于用户来说 默认使用的是系统类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);
        // sun.misc.Launcher$ExtClassLoader@1540e19d
        ClassLoader parent2 = classLoader.getParent();
        System.out.println(parent2);
        // 在上层
        // null
        ClassLoader parent3 = parent2.getParent();
        System.out.println(parent3);

        // Java的核心类库都是使用引导类加载的
        // String 类是使用引导类加载器加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
```

通过上述代码可以发现：

- 系统类加载器是 AppClassLoader，其上层为 ExtClassLoader，可以通过代码获取。
- ExtClassLoader 的上层为 BootstrapClassLoader，但无法通过代码获取（获取到 null），因为 BootstrapClassLoader 是 C/C++语言实现的。
- 程序员使用的类加载器（默认）为 AppClassLoader。
- Java 的核心类库使用的类加载器是 BootstrapClassLoader。

由此可见类加载器共分为三个等级：

- 顶级：BootstrapClassLoader
- 扩展级：ExtClassLoader
- 程序级（系统级）：AppClassLoader

#### 2.4.2 BootstrapClassLoader

启动类加载器（引导类加载器 Bootstrap ClassLoader）是使用 C/C++语言实现的，嵌套在 JVM 内部。

- 它用来加载 Java 的核心库（JAVA_HOME/jre/lib/rt.jar、resources.jar 或者 sun.boot.class.path 路径下的内容），用于提供 JVM 自身需要的类。
- 并不继承自 java.lang.ClassLoader ，没有父加载器。
- 加载拓展类和应用程序类加载器，并指定为他们的父加载器。
- 处于安全考虑，Bootstrap 启动类只加载包名为 java、javax、sun 等开头的类。

```java
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println("启动类加载器");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toString());
        }
    }
}
```

```java
启动类加载器
file:/D:/jdk/jdk1.8/jre/lib/resources.jar
file:/D:/jdk/jdk1.8/jre/lib/rt.jar
file:/D:/jdk/jdk1.8/jre/lib/sunrsasign.jar
file:/D:/jdk/jdk1.8/jre/lib/jsse.jar
file:/D:/jdk/jdk1.8/jre/lib/jce.jar
file:/D:/jdk/jdk1.8/jre/lib/charsets.jar
file:/D:/jdk/jdk1.8/jre/lib/jfr.jar
file:/D:/jdk/jdk1.8/jre/classes
```

#### 2.4.3 ExtClassLoader

扩展类加载器（Extension ClassLoader）Java 语言编写，由 sun.misc.Launcher&ExtClassLoader 实现。

- 派生于 ClassLoader 类
- 父类加载器为启动类加载器 BootstrapClassLoader
- 从 java.ext.dirs 系统属性所指定的目录中加载类库，或者从 JDK 的安装目录 jre/lib/ext 子目录（拓展目录）下加载类库。如果用户创建的 Jar 放在此目录下，也会自带由拓展类加载器加载。

```java
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println("拓展类加载器");
        String property = System.getProperty("java.ext.dirs");
        for (String path : property.split(";")) {
            System.out.println(path);
        }
    }
}
```

```java
拓展类加载器
D:\jdk\jdk1.8\jre\lib\ext
C:\WINDOWS\Sun\Java\lib\ext
```

#### 2.4.4 AppClassLoader

应用程序类加载器（系统类加载器 AppClassLoader）Java 语言编写，由 sun.misc.Launcher&AppClassLoader 实现。

- 派生于 ClassLoader 类。
- 父类加载器为拓展类加载器（ExtClassLoader）。
- 它负责加载的是环境变量 classpath 或者系统属性 java.class.path 指定路径下的类库。
- **该类加载时程序中默认的类加载器**，一般来说，Java 应用程序都是由它来完成加载。
- 通过 ClassLoader#getSystemClassLoader()方法可以获取到该类加载器。

### 2.5 自定义类加载器

此处描述的自定义类加载器不同于 2.4.1 描述的自定义类加载器，此处自定义类加载器可以理解为高度定制的类加载器，有以下需求场景：

- 隔离加载类：中间件有自己依赖的 jar 包，应用程序也有自己依赖的 jar 包，有时存在类名冲突的情况。
- 修改类加载的方式：动态加载。
- 拓展加载源：上述代码中可以看到默认加载源是 jdk 目录，如果有其他来源，如网络则可以进行扩展。
- 防止源码泄漏：防止源码被反编译、篡改，发布源代码前进行加密，运行前进行解密。

用户自定义类加载器实现步骤：

- 继承抽象类 java.lang.ClassLoader 类的方法。
- 在 JDK1.2 之前，继承 ClassLoader 类并重写 loadClass()方法。
- 在 JDK1.2 之后，不再建议用户去覆盖 loadClass()方法，而是建议把自定义类加载逻辑写在 findClass()方法中。
- 在编写自定义类加载器的时候，如果没有太过于复杂的有要求，可以直接继承 URLClassLaoder 类，这样可以避免自己编写 findClass() 方法以及器获取字节码流的方式，使得自定义类加载器编写更加简洁。

```java
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] result = getClassFromCustomPath(name);
            if(result == null){
                throw new FileNotFoundException();
            }else{
                return defineClass(name,result,0,result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException(name);
    }
    private byte[] getClassFromCustomPath(String name){
        //从自定义路径中加载指定类:细节略
        //如果指定路径的字节码文件进行了加密，则需要在此方法中进行解密操作。
        return null;
    }
}
```

### 2.7 双亲委派机制

#### 2.7.1 概述

Java 虚拟机对 class 文件采用的是按需加载的方式，也就是说当需要使用该类的时候才会将它的 class 文件加载到内存生成 class 对象。而且加载某个类的 class 文件的时候，Java 虚拟机采用的是双亲委派机制，也就是把请求交给父类处理，它是一种任务委派机制。
它具有以下优势：

- 避免类的重复加载。class 文件只能被某一种类加载器加载一份。
- 保护程序安全，防止核心 API 被随意篡改。防止应用程序中定义与核心包相同的全类名类。

#### 2.7.2 工作原理

![image.png](jvm/image-1669529656255.png)

- 如果一个类加载器收到了类加载的请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行。
- 如果父类加载器还存在器父类加载器，则进一步向上委托，一次递归请求最终到达顶层的启动类加载器。
- 如果父类加载器可以完成类加载任务，就成功返回，如果父类加载器无法完成此任务，子加载器才会尝试自己去加载。
- 接口是由引导类加载器加载，而接口的实现则是由系统类加载器（线程上下文加载器）加载。

#### 2.7.3 沙箱安全机制

自定义 java.lang.String 类，但是在加载自定义 String 类的时候会率先使用引导类加载器加载，而引导类加载器在加载的过程中会先加载 JDK 自带的文件(rt.jar 包中的 java\lang\String.class)，这样就可以保证对 Java 核心源代码的保护。

### 2.8 补充内容

#### 2.8.1 同一个类的判断

在 JVM 中表示两个 class 对象是否为同一个类存在的两个必要条件：

- 全限定类名必须完全一致。
- 加载这个类的 ClassLoader(指的是 ClassLoader 实例对象)必须相同。

换句话说，JVM 中即使这两个类对象(class 对象)来源于同一个 class 文件，被同一个虚拟机加载，只要加载它们的 ClassLoader 对象不同，那么这两个类对象也是不相等。

JVM 必须知道一个类型是由启动加载器的还是由用户加载器加载的。如果一个类型是由用户类加载器加载的，那么 JVM 会将这个类加载器的一个引用作为类型信息的一部分保存在方法区内。当解析一个类型到另一个类型的引用的时候，JVM 需要保证这两个类型的加载器是相同的。

#### 2.8.2 主动使用和被动使用

Java 程序对类的使用分为主动使用和被动使用（是否会导致类的初始化—是否执行了加载的初始化过程）：
主动使用：

- 创建类的实例（new）。
- 访问某个类的或者接口的静态变量，或者对该静态变量赋值。
- 调用类的静态方法。
- 反射(Class.forName("xxx.xxx.xxx"))。
- 初始化一个类的子类。
- Java 虚拟机启动的时候被标记为启动类的类。
- Java7 开始支持动态的语言支持
  - java.lang.invoke.MethodHandle =实例的解析结果
  - REF_getStatic、REF_putStatic、REF_invokeStatic 对应的类没有初始化，则初始化。

被动使用：其他情况。

## 第 3 章 运行时数据区

### 3.1 概述

![image.png](jvm/image-1669529662916.png)
JVM 的内存布局规定了 Java 在运行过程中内存申请、分配、管理的策略，保证了 JVM 的高效稳定运行。不同的 JVM 对于内存的划分方式和管理机制存在着部分差异，经典的 JVM 内存布局如下：
![image.png](jvm/image-1669529667721.png)
以阿里的为例，详细布局如下：![image.png](jvm/image-1669529672211.png)
Java 虚拟机定义了若干种程序运行期间会使用到的运行时数据区，其中堆和方法区随着虚拟机的销毁而销毁（一个虚拟机一份）。其余三部分是与线程一一对应的，这些与线程对应的数据区域会随着线程开始和结束而创建和销毁。
下图中灰色的是单独线程私有的，红色的是多个线程共享的

- 每个线程：独立包括程序计数器、栈、本地栈
- 线程间共享：堆、堆外内存（永久代或者元空间、代码缓存）

![第03章_线程共享和私有的结构.jpg](jvm/第03章_线程共享和私有的结构.jpg)
Class Runtime：位于 java.lang.Runtime 包下，每个 Java 应用程序都有一个类 Runtime 实例，它允许应用程序与运行应用程序的环境进行交互。 可以从 getRuntime 方法获得当前运行时。

### 3.2 线程（虚拟机角度）

JVM 允许一个应用有多个线程并行执行，在 HotSpot JVM 里，每个线程都与操作系统的本地线程直接映射。线程是一个程序里面的运行单元。

- 当一个 Java 线程准备好执行以后，此时一个操作系统的本地线程也同时创建，Java 线程执行结束之后，本地线程也会回收。
- 操作系统负责所有线程的安排调度（安排线程到任何一个可用的 CPU 上），一旦本地线程初始化成功，它就会调用 Java 线程中的 run()方法。

JVM 系统线程（后台线程、守护线程，不包括调用 public static void main(String[] args)的 main 线程以及所有 main 线程创建的线程）主要包括：

- 虚拟机线程： 这种线程的操作是需要 JVM 达到安全点才会出现，执行类型包括 "stop-the-world"的垃圾收集器，线程栈收集，线程挂起以及偏向锁撤销。
- 周期任务线程：这种线程是时间周期事件的体现（比如中断），一般用于周期性操作的任务调度。
- GC 线程：对在 JVM 里面不同种类的垃圾收集行为提供支持。
- 编译线程：运行时将字节码编译到本地代码。
- 信号调度线程：这种线程接受信号并发送给 JVM，在它内部通过调用适当的方法进行处理。

### 3.3 程序计数器

程序计数寄存器（Program Counter Register）又称 PC 寄存器。寄存器存储线程相关的指令信息。CPU 只有把数据装载到寄存器才能够运行。
**JVM 中的 PC 寄存器是对物理 PC 寄存器的一种抽象模拟，** 并非广义上的物理寄存器，将其翻译为 PC 计数器（或指令计数器）会更加的贴切（也称为程序钩子），并且也不容易引起一些不必要的误会。
作用：PC 寄存器用来存储指向下一条指令的地址，即将要执行的指令代码，由执行引擎读取下一条指令。（指令值存放在栈帧的方法返回地址区域）
特点：

- 是一块很小的内存空间，几乎可以忽略不计，也是运行速度最快的存储区域。
- 在 JVM 规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程的生命周期保持一致。
- 任何时间一个线程都只有一个方法在执行，也就是所谓的**当前方法**，程序计数器会存储当前线程执行 Java 方法的 JVM 指令地址，或者，如果是在执行 native 方法，则是为指定值（underfed）。
- 它是程序控制流的指示器、分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
- 字节码解释器工作的时候就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。
- 它是唯一的一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 的区域。

如下图部分反编译代码截图，PC 寄存器保存反编译文件中的指令地址（由于是栈结构，记录偏移地址即可知道具体的指令地址），执行引擎会读取 PC 寄存器中的指令地址，得到地址对应的操纵指令，转换为计算机识别的机器指令发送给 CPU 执行。
![image.png](jvm/image-1669529682927.png)

#### 3.3.1 常见面试题

1. 使用 PC 寄存器存储字节码指令地址有什么用呢？为什么使用 PC 寄存器记录当前线程的执行地址呢？
   - 因为 CPU 需要不停的切换各个线程，这时候切换回来以后，就得知道接着从哪开始继续执行。
   - JVM 的字节码解释器（将字节码转换机器码，解释器逐行进行）就是需要通过改变 PC 寄存器的值来明确下一条具体应该执行那条字节码指令
2. PC 寄存器为什么设定为**线程私有**的？
   - 由于 CPU 时间片轮转限制，众多线程在并发执行的过程中，任何一个确定的时刻，一个处理器或者多核处理器中的一个内核，只会执行某个线程中的一条指令，CPU 会不停的做任务切换，为了能够准确记录各个线程正在执行的当前字节码指令的地址，最好的办法就是为每一个线程都分配一个 PC 寄存器，这样一来各个线程之间就可以独立计算，从而不会出现相互干扰的情况。

> 扩展：CPU 时间片就是 CPU 分给各个程序的执行时间，每个线程被分配一个时段，这就是时间片。

### 3.4 虚拟机栈（VMS)

#### 3.4.1 概述

虚拟机栈出现的背景：
   - 由于跨平台的设计，Java 指令都是根据栈来设计的，不同平台的 CPU 架构不同，所以不能设计为基于寄存器的。
   - 优点是跨平台，指令集少，编译器容易实现，缺点是性能下降，实现同样的功能需要更多的指令。
内存中的栈和堆：
   - 栈是运行时的单位，而堆是存储的单位。
   - 即：栈是解决程序的运行问题，即程序如何执行，或者说如何处理数据。堆解决的问题是数据存储的问题，即数据怎么放，放在哪儿。
Java 虚拟机栈：
- Java 虚拟机栈（Java Virtual Machine Stack），早期也叫 Java 栈，每个线程在创建的时候都会创建一个虚拟机栈，其内部保存的一个个栈帧（Stack Frame）对应的一次次的方法调用。
- 是线程私有的。

生命周期：

- 生命周期和线程一致。

作用：

- 主管 Java 程序的运行，它保存方法的局部变量（8 种基本数据类型、对象的引用地址）、部分结果，并参与方法的调用和返回。

优点：

- 栈是一种快速有效的分配存储的方式，访问速度仅次于程序计数器。
- JVM 直接对 Java 栈的操作只有 2 个：
  - 每个方法执行，伴随着进栈（入栈、出栈）。
  - 执行结束后的出栈工作。
- 对于栈来说不存在垃圾回收问题
  - 无 GC 但有 OOM(Out Of Memmory)和 StackOverflowError

设置栈内存的大小：

- -Xss 以字节为单位 ，单位是可以调节的。
- ![image.png](jvm/image-1669529689926.png)

栈顶缓存技术：

- 基于栈式架构的虚拟机所使用的零地址指令（只有操作码，没有操作数的指令）更加紧凑，但是完成一项操作的时候必然需要使用更多的入栈的出栈指令，这同时也就意味着需要将更多的指令分派（instruction dispatch）次数和内存读/写次数。
- 由于操作数是存在内存中的，因此频繁的执行内存读/写操作必然会影响执行速度。为了解决这个问题，Hotspot JVM 的设计者们提供了栈顶缓存技术（ToS Top-of-Stack Cashing）将栈顶元素全部缓存在物理的 CPU 的寄存器中，以此降低对内存的读/写次数。提升执行引擎的执行效率。

各区是否存在 Error 和 GC：

|  | Sof | oom | GC |
| --- | --- | --- | --- |
| 方法区 | | √ | √ |
| 堆空间 | | √ | √ | × |
| 程序计数器 | × | × | × |
| 本地方法栈 | √ | √ | × |
| 虚拟机栈 | √ | √ | × |

#### 3.4.2 栈的存储单位和运行原理

栈的存储单位：

- 每个线程都有自己的栈，栈中的数据都是以栈帧（Stack Frame）的格式存在。
- 在这个线程上正在执行的每个方法都各自对应一个栈帧（Stack Frame）。
- 栈帧是一个内存区块，是一个数据集，维系着方法执行过程中的各种数据信息。

栈的运行原理：

- JVM 直接对 Java 栈的操作只有两个，就是对栈帧的压栈和出栈，遵循先进后出/后进先出原则。
- 在一条活动的线程中，一个时间点上，只会有一个活动的栈帧。即只有当前正在执行的方法的栈帧（栈顶栈帧）是有效的，这个栈帧被称为当前栈帧（Current Frame），与当前栈帧相对应的方法就是当前方法（Current Method），定义这个方法的类就是当前类（Current Class）。
- 执行引擎运行所有的字节码指令只针对当前栈帧进行操作。
- 如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，放在栈的顶端，成为新的当前栈帧。
- 不同的线程中所包含的栈帧是不允许存在相互引用的，即不可能在一个栈帧之中引用另外一个线程的栈帧。
- 如果当前方法调用了其他的方法，方法返回之际，当前栈帧会传回次方法的执行结果给前一个栈帧，接着虚拟机会丢弃当前的栈帧，使得前一个栈帧重新成为当前栈帧。
- Java 方法有两种返回函数的方式，一种是正常的函数返回，使用 return 指令，另外一种是抛出异常，不管使用那种方式，都会导致栈帧被弹出。

```java
public class InvokeMethodFrame {
    public static void main(String[] args) {
        System.out.println("InvokeSelf.main 开始执行...");
        method1();
        System.out.println("InvokeSelf.main 执行结束...");
    }
    public static void method1() {
        method2();
        System.out.println("InvokeSelf.method1");
    }
    public static void method2() {
        method3();
        System.out.println("InvokeSelf.method2");
    }
    public static void method3() {
        method4();
        System.out.println("InvokeSelf.method3");
    }
    public static void method4() {
        System.out.println("InvokeSelf.method4");
    }
}
```

![image.png](jvm/image-1669529705642.png)

#### 3.4.3 栈帧的内部结构

栈帧的内部结构：

- 局部变量表（Local Variables）。
- 操作数栈（Operand Stack）（或表达式栈）。
- 动态链接（Dynamic Linking）（或指向运行时常量池的方法引用）。
- 方法返回地址（Return Address）（或方法正常退出或者异常退出的定义）。
- 一些附加信息。

![image.png](jvm/image-1669529708132.png)

##### 3.4.3.1 局部变量表（Local Variables）

局部变量表也被称为局部变量数组或本地变量表：

- 定义为一个数字数组，主要用于存储方法参数和定义在方法体内的局部变量，这些数据类型包括各类型的基本数据类型、对象引用（reference），以及 returnAddress 类型。
- 由于局部变量表是建立在线程的栈上，是线程的私有数据。因此不存在数据不安全的问题。
- 局部变量表所需的容量大小是编译器确定下来的，并保存在方法的 Code 属性的 maximum local variables 数据数据项中，在方法运行期间是不会改变局部变量表的大小的。
  - ![image.png](jvm/image-1669529710731.png)
- 方法嵌套调用的次数由栈的大小决定，一般来说，栈越大，方法嵌套调用次数越多。而对一个函数而言，它的参数和局部变量越多，使得局部变量表膨胀，它的栈帧就越大，一满足方法调用所需传递的信息增加的需求。进而函数调用就会占用更多的栈空间，而导致其嵌套调用的次数就会减少。
- 局部变量表中的变量只在当前方法中调用有效，在方法执行的时候，虚拟机通过使用局部变量表完成参数值到参数列表的传递过程。当方法调用结束的时候，随着方法栈帧的销毁，局部变量表也会随之销毁。
- 在栈帧中，与性能调优关系最密切的就是前面提到的局部变量表，在方法执行时，虚拟机使用局部变量表完成方法的传递。
- 局部变量表中的变量也是重要的垃圾回收根节点，只要被局部变量表中直接或者间接引用的对象都不会被回收。

局部变量表的存储单元：

- 局部变量表最基本的存储单元是 Slot（变量槽），index 从 0 开始，到数组长度-1 的索引结束，通过这个索引即可成功访问到局部变量表中指定的局部变量值。
- 在局部变量表中，32 位以内的类型只占用一个 Slot（包括 returnAddress 类型），64 位的类型（long 和 double）占用 2 个 Slot（占用两个索引，访问时使用前一个索引）。
  - byte、short、char、float 在存储之前被转换为 int，boolean 也被转为 int，0 代表 false，非 0 代表 true。
  - long 和 double 则占据两个 Slot。
- 当一个实例方法被调用的时候，它的方法参数和方法体内部定义的局部变量将会按照顺序被复制到局部变量表中的每一个 Slot 上。
- 如果当前帧是由构造方法或者实例方法创建的，那么该对象引用 this 将会存放在 index 为 0 的 Slot 处，其余的参数按照参数表顺序继续排列。
  - 所以静态方法中无法使用 this 调用，因为 this 变量不存在于当前方法的局部变量表中。
- 栈帧中的局部变量表中的槽位是可以重用的，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。
  - ![image.png](jvm/image-1669529714720.png)
  - ![image.png](jvm/image-1669529718931.png)

静态变量、实例变量与局部变量的对比：

- 类变量：linking 的 prepare 阶段 - 给类变量默认赋值 -> initial 阶段 - 给类变量显示赋值 （静态代码块中）
- 实例变量：随着对象的创建，会在堆空间进行分配实例变量空间，并进行默认赋值。
- 局部变量：在使用之前必须显示赋值（否则编译不通过）。

##### 3.4.3.2 操作数栈（Operand Stack）

一般而言”Java 虚拟机的解释引擎是基于栈的执行引擎“的描述中，栈指的就是操作数栈。
操作数栈，也可以称为表达式栈（Expression Stack），在方法执行的过程中，主要用于保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间。

- 根据字节码指令，往栈中写入数据或者提取数据，即入栈（push）/出栈（pop）
- 某些字节码指令将值压入操作数栈，其余的字节码指令将操作数取出栈，使用它们后再把结果压入栈。
  - ![image.png](jvm/image-1669529722107.png)
- 操作数栈就是 JVM 执行引擎的一个工作区，当一个方法刚开始执行的时候，一个新栈帧也随之被创建出来，这个方法的操作数栈是空的。
- 每一个操作数栈都会拥有一个明确的栈深度用于存储数值，其所需的最大深度在编译器就定义好了，保存在方法的 Code 属性中，为 max_stack 的值。
- 栈中的任何一个元素都可以是任意的 Java 数据类型。
  - 32bit 的类型占用一个栈单位深度。
  - 62bit 的类型占用两个栈单位深度。
- 操作数栈虽然使用数组结构实现，但不能通过索引的方式来进行数据访问，只能通过标准的入栈（push）和出栈（pop）操作来完成一次数据访问。
- 如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中，并更新 PC 寄存器的下一条需要执行的字节码指令。
- 操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这由编译器再编译期间进行验证，同时在类加载中的类检验阶段的数据流分析阶段要再次验证。

##### 3.4.3.3 动态链接（Dynamic Linking）

有些文章中将动态链接、方法返回地址、其他附加信息总称为帧数据区。

- 每一个栈帧内部都包含一个指向运行时常量池中该栈帧所属方法的引用。包含这个引用的目的就是为了支持当前方法的代码能够实现动态链接（Dynamic Linking）。比如：involvedynamic 指令。
- 在 Java 源文件被编译到字节码文件的时候，所有的变量（包含类变量、成员变量、局部变量吗？）和方法引用都作为符号引用）（Symbolic Reference）保存在 class 文件的常量池中。比如：描述一个方法调用了另外的其他方法的时候。就是通过常量池中指向方法的符号引用来表示的，动态链接的作用就是将这些符号引用转换为调用方法的直接引用。
- **静态链接：**
  - 当一个字节码文件被装载到 JVM 内部的时候，如果被调用的 **目标方法在编译器可知**，且在运行期保持不变时，这种情况下将调用方法的符号引用转换为直接引用的过程叫做静态链接。
- **动态链接：**
  - 如果**被调用的方法在编译期间无法被确定下来**，也就是说，只能够在程序运行期将调用方法的符号引用转换为直接引用，由于这种引用转换过程具备动态性，因此也就称之为动态链接。

##### 3.4.3.4 方法返回地址和附加信息的补充知识——方法调用

在 JVM 中，将符号引用转换为调用方法的直接引用与方法的绑定机制相关（符号引用、直接引用参看 3.4.3.3 动态链接）：

- 早期绑定
  - 早期绑定就是指被调用的 目标方法如果在编译期间可知，且运行期保持不变时，即可将这个方法与所属的类型进行绑定，这样一来，由于明确了被调用的目标方法究竟是哪一个，因此可以使用静态链接的方式将符号引用转为直接引用。
- 晚期绑定
  - 如果被调用的方法在编译期无法被确定下来，只能够在程序运行期间根据实际的类型绑定相关的方法，这种绑定叫做晚期绑定。

Java 中任意一个普通方法都具备虚函数（C++中）的特征，如果某个方法不想用于虚函数的特征的时候，可以使用 final 关键字来标记：

- 非虚方法：如果方法在编译期就确定了具体的调用版本，这个版本在运行时候是不可变的，这样的方法叫做非虚方法。
  - 静态方法、私有方法、final 方法、实例构造器、父类方法都是非虚方法。
- 虚方法：除了非虚方法都是虚方法。

虚拟机中提供的方法调用的指令：

- 普通调用指令：
  - invokestatic：调用静态方法。
  - invokespecial：调用私有方法、父类方法等非虚方法。
  - invokevirtual：调用所有的虚方法（final 方法在这调用，但是非虚方法）。
  - invokeinterface：调用接口方法。
- 动态调用指令
  - invokedynamic：动态解析出需要调用的方法，然后执行。

前四条指令固化在虚拟机内部，方法的执行调用不可人为干涉，而 invokedynamic 指令则支持用户确定方法版本。invokestatic 指令和 invokespecial 指令调用的方法称为非虚方法。其余的（final 修饰的除外）称为虚方法。
关于 involvedynamic 指令：

- JVM 字节码指令集一直比较稳定，一直到 Java7 中才增加了一个 invokedynamic 指令，这是 Java 为了实现[动态类型语言]支持而做的一种表达方式。
- 但是在 Java7 中并没有提供直接生成 involvedynamic 指令的方法，需要借助 ASM 这种底层字节码工具来生成 involvedynamic 指令。直到 Java8 的 Lambda 表达式的出现，involvedynamic 指令才有了直接的生成方式。
- Java7 中增加的动态语言类型支持的本质是对 Java 虚拟机规范的修改，而不是对 Java 语言规则的修改，这里比较复杂，最直接的受益者就是运行在 Java 平台的动态语言的编译器。

动态类型语言和静态类型语言：

- 动态类型语言的静态类型语言的区别就是对类型的检查是在编译期还是在运行期，满足前者就是静态类型语言，满足后者就是动态类型语言。
- 静态类型语言就是判断变量自身的类型信息；动态类型语言是判断变量值的信息，变量没有类型信息，变量值才有类型信息。

Java 中方法重写的本质：

- 找到操作数栈顶的第一个元素随执行的对象的实际类型，记作 C。
- 如果在类型 C 中找到与常量池中描述符、简单名称（？）都相符的方法，则进行访问权限校验，如果通过则返回这个方法的直接引用，查找过程结束；如果不通过，就返回 java.lang.IllegalAccessError 异常。
  - 程序试图访问一个属性或者调用一个方法的时候，这个属性或者方法没有权限进行访问。
- 否则，按照继承关系从下往上一依此对 C 的各个父类进行第 2 步的搜索和验证。
- 如果始终没有找到，就抛出 java.lang.AbstractMethodError 异常。

虚方法表：

- 在面向对象的编程中，会很繁琐的使用到动态分派（invokevirtual），如果每次动态分派的过程中都要重新在类的方法元数据中搜索合适的目标的话，就可能影响到执行效率。因此，为了提高性能，JVM 采用在类的方法区建立一个虚方法表（virtual method table）（非虚方法不会出现在表中）来实现，使用索引表来代替查找。
- 每个类都有一个虚方法表，表中存放着各个方法的实际入口。
- 虚方法表在什么时候会被创建？
  - 在类加载的链接阶段（解析过程）被创建并开始初始化，类的变量初始值准备完成之后，JVM 会把该类的方法表 也初始化完毕。

##### 3.4.3.5 方法返回地址（Return Address）

方法返回地址存放调用该方法的 PC 寄存器的值。

方法退出后都要返回到该方法被调用的位置，方法正常退出的时候，调用者的 PC 寄存器的值作为返回地址，即调用该方法的指令的下一条指令的地址。而通过异常退出的，返回的地址是要通过异常表来确定，栈帧中一般不会保持这部分的信息。

当一个方法开始执行之后，只有两种方式可以退出这个方法：

- 执行引擎遇到任何一个方法返回的字节码指令（return），就会有返回值传递给上层的方法调用者，简称正常完成出口。
  - 一个方法在正常调用完成之后究竟需要使用哪一个返回指令还需要根据方法返回值的实际数据类型而定。
  - 在字节码指令中，返回指令包含 ireturn （返回值是 boolean、byte、char、short 和 int 类型时使用），lreturn、freturn、dreturn、areturn（返回值引用类型）、return（为 void 方法、实例化初始方法（构造方法、静态代码块）、类和接口的初始方法使用）。
- 在方法执行的过程中遇到了异常（Exception），并且这个异常没有在方法内进行处理，也就是只要在本地方法异常表没有搜索到匹配的异常处理器，就会导致方法退出，简称异常完成出口。
  - 方法执行过程中抛出异常时的异常处理，存储在一个异常处理表中，方便在发生异常的时候找到处理异常的代码。
  - ![](jvm/2022-12-01-13-37-08.png)

本质上，方法的退出就是当前栈帧出栈的过程。因此，需要恢复上层方法的局部变量表、操作数栈、讲返回值压入调用者栈帧的操作数栈、设置 PC 寄存器值等，让调用者方法继续执行下去。

正常完成出口和异常完成出口的区别在于：通过异常出口完成退出的不会给它的上层调用者产生任何的返回值。

##### 3.4.3.6 一些附加信息

栈帧中还允许携带与 Java 虚拟机实现相关的一些附加信息，例如：对程序调试支持的信息。

### 3.5 堆

#### 3.5.1 概述

每个进程拥有一个 JVM 实例，一个 JVM 实例只存在一个堆内存，堆也是 Java 内存管理的核心区域：

- Java 堆区在 JVM 启动的时候即被创建。其空间大小也就确定了。是 JVM 管理的最大的一块内存空间
  - 堆内存的大小可以使用参数进行调节（-Xms10m -Xmx10m 最小、最大内存设置，只能影响新生代、老年代）。
- 《Java 虚拟机规范》规定，堆可以处于物理上不连续的内存空间中，但是在逻辑上他应该是连续的。
- 所有的线程共享 Java 堆，在这里还可以划分为线程私有缓冲区（Thread Local Allocation Buffer ，TLAB）
- 《Java 虚拟机规范》中对 Java 堆的描述是：所有的对象实例以及数组都对应当在运行时分配在堆上
  - “几乎“所有的对象实例都在这里进行分配内存——从实际使用角度看。
- 数组和对象可能永远不会存储在栈上，因为栈帧中保存引用，这个引用指向对象或者数组在堆中的位置。
  - ![image.png](jvm/image-1669529730790.png)
- 在方法结束后，堆中的对象不会马上被移除，仅仅在垃圾收集的时候，才会被移除。（如果马上移除，GC 线程会频繁调用，挤占用户线程，影响性能）
- 堆是 GC（Garbage Collection 垃圾收集器）执行垃圾回收的重点区域。
- jdk 自带查看堆内存分析的工具。在 jdk 的安装目录下的 bin 下的 jvisualvm.exe（单独的 jre 没有这个工具）

#### 3.5.2 内存划分

##### 3.5.2.1 堆空间逻辑划分

现代的垃圾收集器大部分都基于**分代收集理论**设计。堆空间细分为：

- Java7 以及之前堆的内存逻辑分为三部分：新生区+养老区+永久区
  - Young Generation Space：新生区 Young/New
    - 又被划分为 Eden 区和 Survivor 区
  - Tenure Generation Space：养老区 Old/Tenure
  - Permanent Space：永久区 Perm
  - ![image.png](jvm/image-1669529736075.png)
- Java8 及以后堆内存逻辑上分为三部分：新生区+养老区+元空间
  - Young Generation Space：新生区 Young/New
    - 又被划分为 Eden 区和 Survivor 区
  - Tenure Generation Space：养老区 Old/Tenure
  - Meta Space：元空间 Meta
  - ![image.png](jvm/image-1669529742299.png)
- 约定称谓：新生代=新生代 =年轻代，养老区=老年区=老年代 ，永久代=永久区。

##### 3.5.2.2 堆空间分代思想

其实不分代也是完全可以的，分代的唯一理由就是优化 GC 性能，如果没有分代，那么所有的对象都在一起。GC 的时候，就会对整堆进行全局扫描，然而很多对象都是朝生夕死的，如果分代的话，把这些对象聚集在一起，GC 先回收这部分，就会节省很多空间和资源。

经过研究，不同对象的生命周期不同，70%~99% 都是临时对象

- 新生代：由 Eden、两块大小相同的 Survivor（又称 from/to，s0/s1） 构成，to 总为空。
- 老年代：存放新生代中经历多次 GC 依然存活的对象。

##### 3.5.2.3 堆内存分配策略（对象提升(promotion)规则）

如果对象在 Eden 出现并经过第一次 Minor GC 之后仍然存活，并且能被 Survivor 容纳的话，将被移动到 Survivor 区中。每熬过一次 MinorGC，年龄就增加 1 岁，当年龄增加到一定的程度（默认为 15 岁）就会晋升到老年代。

对象晋升老年代的年龄，可以通过 ： -XX:MaxTenuringThreshold 来设置。

针对不同的年龄段的对象分配原则如下：

- 优先分配到 Eden。
- 大对象（通常指需要连续存储空间的对象）直接分配到老年代。
  - 尽量避免程序中出现过多的大对象。
- 长期存活的对象分配到老年代。

动态对象年龄判断：

- 如果 Survivor 区中的相同年龄的所有的对象的总和大于 Survivor 空间的一半，年龄大于或者等于该年龄的对象可以直接进入老年代，无需等到 MaxTenuringThreshold 中要求的年龄

空间分配担保：-XX:HandlePromotionFailure

##### 3.5.2.4 对象分配过程(TLAB）

TLAB 指 Thread Local Allocation Buffer（线程本地分配缓存区）。由于堆区是线程共享区域，任何线程都可以访问到堆区中的共享数据。而对象实例的创建在 JVM 中非常频繁，因此在并发环境下从堆区中划分内存空间是线程不安全的。如果要避免多个线程操作同一个地址，需要使用加锁等机制，但会影响分配速度。因此将堆中 Eden 区继续进行划分，JVM 为每个线程分配了一个私有缓存区域，私有不足则再使用共享的 Eden 区。

- ![第08章_TLAB.jpg](jvm/第08章_TLAB.jpg)
- 私有缓存区域的划分时从内存模型角度而不是垃圾收集的角度。
- 多线程同时分配内存的时候，使用 TLAB 可以避免一系列的非线程安全问题。同时还能提升内存分配的吞吐量，称为快速分配策略。
- 几乎所有的 OpenJDK 衍生出来的 JVM 都提供了 TLAB 设计。
- 尽管不是所有的对象实例都能在 TLAB 中成功分配内存，但是 JVM 确实把 TLAB 作为内存分配的首选。
- 在程序中，开发人员可以通过选项`-XX:UseTLAB`设置是否开启 TLAB 空间（默认开启）。
- 默认情况下，TLAB 空间的内存非常小，仅仅占有整个 Eden 空间的 1%。可以通过选项`-XX:TLABWasteTargetPercent`设置 TLAB 空间所占用的 Eden 空间的百分比大小。
- 一旦对象在 TLAB 空间分配内存失败的时候，JVM 就会尝试使用加锁机制来确保数据操作的原子性，直接使用 Eden 区分配内存。

对象分配过程：
![第08章_对象分配过程.jpg](jvm/第08章_对象分配过程.jpg)

#### 3.5.3 堆空间大小、设置与查看

Java 堆用来存储 Java 对象实例，那么堆的大小在 JVM 启动的时候就已经设定好了，可以通过选项设置 "-Xmx"和"-Xms"来进行设置 （设置的是年轻代+年老代的大小）：

- -Xms：表示堆区的启始内存大小，等价于 -XX:InitialHeapSize。
- -Xmx：表示堆区的最大内存，等价于 -XX:MaxHeapSize。
- 说明：-X 是 JVM 的运行参数，ms 是 Memory Start 的缩写。

一旦堆区的内存大小超过-Xmx 所指定的最大内存时，将会抛出 OutOfMemoryError 异常。

- 默认情况下：
  - 初始内存大小：物理电脑内存大小/64
  - 最大内存大小：物理电脑内存大小/4

```java
public class HeapSizeDemo {
    public static void main(String[] args) {
        long initialMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("-Xms:" + (initialMemory / 1024 / 1024) + " M");
        System.out.println("-Xms:" + (maxMemory / 1024 / 1024) + " M");
        System.out.println("系统内存大小：" + (initialMemory * 64 / 1024 / 1024 / 1024) + " G");
        System.out.println("系统内存大小：" + (maxMemory * 4 / 1024 / 1024 / 1024) + " G");
    }
}
```

查看 JVM 的内存参数设置：

- 方式一（命令行指令）：
  - jps：查看 jvm 线程
  - jstat -gc 线程代号
  - ![image.png](jvm/image-1669529757868.png)
  - 新生代空间：S0C+S1C+EC
  - 老年代空间：EC
- 方式二：JVM 运行参数
  - -XX:+PrintGCDetails
  - ![image.png](jvm/image-1669529760327.png)
  - PSYoungGen 新生代、ParOldGen 老年代、Metaspace 元空间

一般将-Xms 和-Xmx 两个参数设置为相同的值，可以使垃圾回收机制在清理完堆区之后不需要重新分隔计算堆区的大小，从而提升性能。

#### 3.5.4 OOM

参考文档：[](基础] 第 8 篇：常见 OOM 异常](https://www.yuque.com/icanci/th0qbg/ogug4d?view=doc_embed&inner=LtUb3)

```java
/**
 * VM args: -Xms20m -Xmx20m
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class HeapOomCase {
    static class OomObject {
    }
    public static void main(String[] args) {
        ArrayList<OomObject> list = new ArrayList<>();
        while (true) {
            list.add(new OomObject());
        }
    }
}
```

![动画.gif](jvm/动画.gif)
![image.png](jvm/image-1669529767992.png)
过程参见 3.5.6。

#### 3.5.5 年轻代和老年代内存结构

存储在 JVM 中的 Java 对象可以被划分为两类：

- 一类是生命周期毕竟短的瞬时对象。这类对象的创建和消亡都非常迅速。
- 另外一类对象的生命周期却非常长，在某些极端的情况下还能与 JVM 的生命周期保持一致。

Java 堆区进一步细分的话，可以划分为年轻代（YoungGen）和老年代（OldGen）：

- 其中年轻代又可以分为 Eden 空间，Survivor0 空间和 Survivor1 空间（有时也叫 from 区和 to 区）。

空间大小比例（一般不做修改）：

- Young : Old = 1 : 2
- Eden : s0 : s1 = 8 : 1 : 1（Hotspot 中的缺省比例）
  - 但使用时比例为 6:1:1，这是因为虚拟机的自适应内存分配策略。
  - `-XX:-UseAdaptiveSizePolicy`表示关闭自适应的内存分配策略，但使用时发现并不能其作用。
  - 要想看到 8:1:1 的内存分配，可使用`-XX:SurvivorRatio=8`设置。

![image.png](jvm/image-1669529771473.png)
配置新生代老年代在堆结构的占比：

- `-XX:NewRatio=2`（默认）：表示新生代占 1，老年代占 2，新生代占整个堆的 1/3。
- `-XX:NewRatio=4`：表示新生代占 1，老年代占 4，新生代占整个堆的 1/5。

几乎所有的 Java 对象都是在 Eden 区被 new 出来的：

- 绝大部分的 Java 对象的销毁都在新生代进行了。
  - IBM 公司的专门研究表明：新生代 80% 的对象都是”朝生夕死“的。
- 可以使用`-Xmn`设置新生代的最大内存大小（一般不使用该指令设置，而使用`-XX:NewRatio=2`，如果二者同时设置了，`-Xmn`优先。

#### 3.5.6 图解对象的内存分配过程

- new 的对象先放在伊甸园区。
- 当伊甸园区填满的时候，程序又需要创建新的对象，JVM 的垃圾回收器将对伊甸园进行垃圾回收（YGC/Minor GC），将伊甸园区中的不不再被其他对象所引用的对象进行销毁，再加载新的对象放在伊甸园区。然后将伊甸园区的幸存对象移动到 S0 区。
  - ![image.png](jvm/image-1669529774676.png)
  - 红色代表不再使用的对象。
  - 绿色中的数字代表年龄，移动一次则+1。
- 如果伊甸园区再次触发垃圾回收，此时上次幸存下来的放到幸存者 S0 区域的，如果没有回收，就放到 S1 区。伊甸园区的对象放到 S1 区。此时 S0 为空。
  - ![image.png](jvm/image-1669529776953.png)
- 后续如果伊甸园区再次经历垃圾回收，此时会重新放回 S0 区，接着再去 S1 区，循环此过程。即伊甸园区的有用对象总是先放到空的 S 区。
  - 注意：只有 Eden 区满了之后才会触发 YGC，而幸存者区满了不会触发 YGC，但是触发 YGC 会将 Eden 区和幸存者区一起回收。
- 当一个对象经历了 15 次 Minor GC 之后，就会放到养老区。
  - ![image.png](jvm/image-1669529779237.png)
  - 可以设置参数：-XX:MaxTenuringThreshold=n 进行设置
- 当养老区的内存不足的时候，再次触发 GC：Major GC 进行养老区的内存清理。
- 如果养老区执行了 Major GC 之后依旧无法进行对象的保存，就会产生 OOM 异常。

总结：

- 针对幸存者 s0，s1 区的总结：复制之后有交换，谁空谁是 to。
- 关于垃圾回收：频繁在新生区收集，很少在养老区收集，几乎不在永久区/元空间收集。

特殊情况：

- 新创建的大对象在伊甸园区放不下，会尝试在老年区存放，老年区存放不下会 OOM。
  - 存放老年代时，jdk8 会不动 Eden 区，直接把新对象放到 old 区。jdk7 会先把 eden 区的放到幸存者区，然后 eden 区放新创建的对象。
- S 区存放来自伊甸园区的对象时，如果对象太大，可以直接放到老年区。
- ![image.png](jvm/image-1669529782108.png)

#### 3.5.7 堆空间参数设置

- `-XX:+PrintFlagsInitial`：查看所有参数的默认初始值。
- `-XX:+PrintFlagsFinal`：查看所有参数的当前设置值（如果修改了默认值，显示的时候会以`:=`的形式显示，没有修改的没有`:`。
- `-Xms`：初始堆空间内存 （默认大小为 物理内存空间/64）。
- `-Xmx`：最大堆空间内存（默认大小为 物理内存空间/4）。
- `-Xmn`：设置新生代的大小（初始值及最大值）。
- `-XX:NewRatio`：配置新生代与老年代在堆结构的占比。
- `-XX:SurvivorRatio`：设置新生代中 Eden 和 S0、S1 空间的比例。
- `-XX:MaxTenuringThreshold`：设置新生代垃圾最大年龄。
- `-XX:+PrintGCDetails`：输出详细的 GC 处理日志。
- `-XX:PrintGC`或`-verbose:gc`：打印 GC 的简要信息。
- `-XX:HandlePromotionFailure`：是否设置空间分配担保。

`-XX:HandlePromotionFailure`参数说明：在发生 Minor GC 之前，虚拟机会检查老年代最大可用的连续空间是否大于新生代所有对象的总空间。

- 如果大于，则此此 Minor GC 是安全的。
- 如果小于，则虚拟机会查看-XX:HandlePromotionFailure 设置值是否允许担保失败：
  - 如果 HandlePromotionFailure=true，那么会继续检查老年代最大可用连续空间是否大于历次晋升到老年代的对象的平均大小。
    - 如果大于，则尝试进行一次 Minor GC，但这次 Minor GC 仍然是有风险的
    - 如果小于，则改为一次 Full GC（新生代、老年代进行一次回收）。
  - 如果 HandlePromotionFailure=false 则改为进行一次 Full GC。
- 在 JDK6 Update24（jdk7）之后，HandlePromotionFailure 参数不会再影响到虚拟机的空间分配担保策略（即默认为 true，且不能调整），虽然源码中定义了 HandlePromotionFailure 参数，但是在代码中，没有使用它。

#### 3.5.8 逃逸分析

将堆上的对象分配到栈上，需要使用逃逸分析手段。这是一种可以有效减少 Java 程序中同步负载和内存堆分配压力的跨函数全局数据流的分析算法。通过逃逸分析，Java Hotspot 编译器能够分析出一个新的对象的引用的适用范围从而觉得是否要将这个对象分配到堆上。
逃逸分析的基本行为就是分析对象的动态作用域：

- 当一个对象在方法中被定义之后，对象只在方法内部使用（内部消亡），则认为没有发生逃逸。没有发生逃逸的对象，就可以分配到栈上，随着方法执行结束，栈空间就被移除。
- 当一个对象在方法中被定义后，它被外部的方法所引用，则认为发生逃逸，例如调用参数传递到其他方法中。

```java
public static StringBuilder method3() {
    StringBuilder sb = new StringBuilder();//栈内存中线程不安全（sb未内部消亡）
    sb.append("a");
    sb.append("b");
    return sb;
}
public static String method4() {
    StringBuilder sb = new StringBuilder();//栈内存中线程安全（sb内部消亡，因为toString()它有返回值，method4方法的最终结果是toString()的返回结果）
    sb.append("a");
    sb.append("b");
    return sb.toString();
}
```

上述代码中，method3 中 sb 对象发生了逃逸，method4 中没有发生逃逸。
快速判断是否发生逃逸分析：new 的对象实体是否可以在方法外部调用，简而言之，只是在方法内部使用此对象则没有发生逃逸。
JDK6u23（jdk7）版本之后，Hotspot 中默认开启了逃逸分析

- 如果使用较早的版本
  - -XX:+DoEscapeAnalysis：显式开启逃逸分析。
  - -XX:+PrintEscapeAnalysis：查看逃逸分析筛选结果。

**结论：开发中能使用局部变量的，就不要在方法外进行定义。**
使用逃逸分析，编译器可以对代码做如下优化：

- **栈上分配**。将堆分配转化为栈分配，
- **同步策略**。如果一个对象被发现只能从一个线程被访问到，那么对于这个对象的操作可以不考虑同步。
  - ![image.png](jvm/image-1669529788009.png)
- **分离对象或者标量替换**。有的对象可能不需要作为一个联系的内存结构存在也可以被访问到，那么对象的部分（或全部）可以不存储在内存（堆中），而是存储在 CPU 寄存器中（栈中）。

栈上分配：

- JIT 编译器在编译期间根据逃逸分析的结果，发现如果一个对象并没有逃逸出方法的话，就可能被优化成栈上分配。分配完成之后，继续在调用栈内执行，最后线程结束，栈空间被回收，局部变量对象也被回收，这样就无需进行垃圾回收了。
- 常见的栈上分配场景：
  - 给成员变量赋值、方法返回值、实例引用传递（引用成员变量）发生了逃逸，除此之外可视为没有发生逃逸，可以使用栈上分配。

同步策略 - 锁消除：

- 线程之间同步的代价是非常高的，同步的后果是降低并发性和性能。
- 在动态编译的同步块的时候，JIT 编译器可以借助逃逸分析来判断同步块所使用的锁对象是否只能被一个线程访问而没有被发布到其他线程。如果没有（只是当前线程在使用），那么 JIT 编译器在这个同步块的时候就会取消这部分代码的同步，这样就可以大大提高并发性和性能。这个取消同步的过程就叫同步省略，也叫锁消除。

分离对象或者标量替换：

- 标量（Scalar）是指一个无法在分解成更小的数据的数据，Java 中的原始数据类型就是标量。
- 相对的还可以分解的数据就叫做**聚合量（Aggregate）**，Java 中的对象就是聚合量，因为可以分解为其他的聚合量和标量。
- 在 JIT 阶段，如果经过逃逸分析，发现一个对象不会被外界访问的话，那么经过 JIT 优化，就会把这个对象拆解成若干个成员变量来替代。这个过程就是**标量替换。**
- 标量替换可以大大减少堆内存的占用，因为不需要创建对象，那么就不再需要分配堆内存。
- 标量替换为栈上分配提供了很好的基础。
- 开启标量替换：-XX:+EliminateAllocations（默认打开）。

```java
public class Test {
    public static void main(String[] args) {
        alloc();
    }
    public static void alloc() {
        Point point = new Point(1, 23);
        System.out.println(point.x);
        System.out.println(point.y);
    }
}
class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

可以看到，Point 这个聚合变量经过逃逸分析之后，发现他并没有逃逸，就可以被替换为两个聚合量了：

```java
public class Test {
    public static void main(String[] args) {
        alloc();
    }
    public static void alloc() {
        int x = 1;
        int y = 23;
        System.out.println(x);
        System.out.println(y);
    }
}
class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
```

总结：

- 关于逃逸分析的论文在 1999 就发表了，但是在 JDK6 才实现，技术不是很成熟。
- 根本原因就是 无法保证逃逸分析的性能消耗一定能高于他的消耗，虽然经过逃逸分析可以做标量替、栈上分配、和锁消除，但是逃逸分析自身也需要进行一系列复杂的分析，也是一个相对耗时的过程。
- 虽然技术不是很成熟，但是他是即时编译器优化技术中一个十分重要的手段。

#### 3.5.9 垃圾回收策略

##### 3.5.9.1 Minor GC、Major GC 和 Full GC

JVM 在进行 GC 的时候，并非每次都对新生代、老年代、方法区三个内存区域一起回收，大部分的时候回收都是指新生代。

针对 Hotspot VM 的实现，它里面的 GC 按照回收区域又分为 2 种类型：一种是部分收集（Partial GC），一种是整堆收集（Full GC）：

- 部分收集：不是完整的整个 Java 堆收集。
  - 新生代收集（Minor GC 完全等价于 YGC）：只是在新生代的垃圾收集。
  - 老年代收集（Major GC/ Old GC）：只是对老年代的垃圾收集。
    - 目前只有 CMS GC 会有单独收集老年代的行为。
    - **注意很多时候 Major GC 和 Full GC 混淆使用，需要具体判别老年代回收还是整堆回收。**
  - 混合收集（Mixed GC）：收集整个新生代和部分老年代的垃圾收集。
    - 目前 只有 G1 GC 会有这种行为。
- 整堆收集（Full GC）：收集整个 Java 堆和方法区的垃圾收集。

##### 3.5.9.2 年轻代 GC（Minor GC/YGC）的触发机制

- 当年轻代空间不足的时候，就会触发 Minor GC，这里的年轻代满指的是 Eden 区满，而不是 S0 或者 S1 满。Servivor 满不会触发 Minor GC（但是每次 Minor GC 会清理年轻代的内存，包括 Eden/S0/S1）
- 因为 Java 对象大多都**具备朝生夕死**的特性，所以 Minor GC 非常频繁，一般回收速度也很快。
- Minor GC 会引发 STW（Stop The World），暂停其他用户线程，等待垃圾回收结束，用户线程才恢复执行。

##### 3.5.9.3 老年代 GC（Major GC/Full GC）的触发机制

- 指发生在老年代的 GC，对象从老年代消失的时候，Major GC 或 Full GC 就发生了。
- 出现了 Major GC ，经常会伴随至少一次的 Minor GC（但是非绝对的，在 Parallel Scavenge 收集器的收集策略里就有直接进行 Major GC 的策略选择过程）
  - 也就是在老年代空间不足的时候，会先尝试触发 Minor GC。如果之后空间还不足，则触发 Major GC。
- Major GC 的速度会比 Minor GC 慢十倍一样，STW 的时间更长。
- 如果 Major GC 之后，内存还不足，就报 OOM。

##### 3.5.9.4 Full GC 触发机制

- 触发 Full GC 的情况有有下面五种
  - 调用 System.gc()的时候，系统建议执行 Full GC，但是不是必然执行的。
  - 老年代空间不足。
  - 方法区空间不足。
  - 通过 Minor GC 后进入老年代的平均大小大于老年代的可用内存。
  - 由 Eden 区、Survivor space0（From Space）区向 Survivor space1（To Space）区复制的时候，对象大小大于 To Space 的可用内存，则把该对象转存到老年代。且老年代 的可用内存小于该对象大小。
  - **说明：Full GC 是开发或者调优种尽量避免的，这样暂停的时间会短一些。**

### 3.6 方法区（元空间）

#### 3.6.1 栈、堆、方法区之间的交互关系

![image.png](jvm/image-1669529798973.png)
![image.png](jvm/image-1669529806700.png)

![image.png](jvm/image-1669529803952.png)

#### 3.6.2 方法区的理解

《Java 虚拟机规范》中明确说明：尽管所有的方法区在逻辑上是属于堆的一部分，但是一些简单的实现可能不会选择区进行垃圾收集或者压缩。但是对于 HotSpot 虚拟机而言，方法区还有个名字叫 Non-Heap（非堆），目的就是要和堆分开。所以，方法区可以看作是一块独立于 Java 堆的内存空间。

方法区（Method Area）和堆一样，是各个线程共享的内存区域。

方法区在 JVM 启动的时候被创建，并且它的实际物理内存空间中和 Java 堆区都一样可以是不连续的。

- 方法区的大小和堆空间一样，可以选择固定大小或者可拓展。
- 方法区的大小决定了系统可以保存多少个类，如果系统定义了太多的类，导致方法区溢出，虚拟机就会抛出内存溢出错误：`java.lang.OutOfMemory:PermGen space`（jdk7 之前）或者`java.lang.OutOfMemory:Metaspace`
  - 如加载了大量的第三方 jar 包。或者 Tomcat 部署的工程过多。大量动态生成反射类。

关闭 JVM 就会释放这个区域的内存。

#### 3.6.3 Hostpot 中方法区的演进

Hostpot 虚拟机中，在 JDK7 以及之前，习惯上把方法区称为永久代。在 JDK8 以及以后，使用元空间取代了永久代。本质上方法区和永久代并不等价，仅仅只是对 Hotspot 而言的。《Java 虚拟机规范》对如何实现方法区不做统一要求。

- 现在看来，当年使用永久代，不是好的 idea，导致 Java 程序更容易 OOM（超过 -XX:MaxPermSize 上限）。

JDK8 完全废弃了永久代的概念，改用和 JRockit、J9 一样在本地内存中实现的元空间（Metaspace）：

- 元空间的本质和永久代类似，都是 JVM 规范中方法区的体现，不过元空间与永久代最大区别就是：**元空间不在虚拟机设置的内容中，而是使用本地内存。**
- 永久代、元空间不仅仅是名字改了，内部结构也进行了调整。
- 根据《Java 虚拟机规范》的规定，如果方法区无法满足新的内存分配需要的时候，将抛出 OOM 异常。

Hotspot 中方法区的变化：

- JDK1.6 之前用永久代（permanent generation） 静态变量存放在永久代上。
- JDK1.7 有永久代，但是已经逐步“去永久代”，字符串常量池、静态变量移除，保存在堆中。
- JDK1.8 之后 无永久代。类型信息、字段、方法、常量保存在本地内存的元空间，但是字符串常量池、静态变量仍在堆。
- ![第08章_方法区的演进细节-hotspot.jpg](jvm/第08章_方法区的演进细节-hotspot.jpg)
  - 上述图中静态变量指的是变量引用，参考 3.6.6.5。

永久代为什么要被元空间替换：

- 直接原因是要整合 JRockit 虚拟机，JRockit 没有永久代。
- 永久代的设置空间大小时很难确定的。
  - 如 web 工程，运行中需要动态加载很多类，如果加载动态类过多，很容易导致永久代内存满（java.lang.OutOfMemoryException:PerGen)。
  - 元空间和永久代的区别就是：元空间不在虚拟机中，而是使用本地内存。因此，默认情况下，元空间的大小仅仅受本地内存限制。
- 对永久代进行调优时很困难的。

StringTable 为什么需要调整：

- JDK7 中将 StringTable 放到了堆空间中。因为永久代的回收效率很低，在 full GC 才会触发。而 full GC 在老年代不足、永久代不足才会触发。这就导致 StringTable 回收效率不高，而在开发中会有大量的字符串被创建，回收效率低，导致永久代内存不足。放到堆里，能及时回收内存。

静态引用的对象实体始终都存在堆空间的老年代。引用名 jdk1.6 在永久代，jdk1.7 在堆，jdk1.8 在元空间。
![](jvm/2022-12-01-13-41-39.png)

- staticObj（的引用）随着 Test 类信息的加载存放在方法区。
- instanceObj（的引用）随着 Test 对象的实例存放在 Java 堆区。
- localObj（的引用）存放在 foo()方法栈帧的局部变量表中。
- 但三者的对象实例均存放在堆中。

##### 3.6.3.1 直接内存（本地内存）

直接内存不是虚拟机运行时数据区的一部分，也不是《Java 虚拟机规范》中定义的内存区域。直接内存是在 Java 堆外的、直接向系统申请的内存空间。

- 来源于 NIO，通过 DirectByteBuffer 可以直接操作 Native 内存。
- 通常访问直接内存的速度高于 Java 堆，也就是读写性能更高。
  - 因此读写频繁的场合可能会考虑使用直接内存。
  - Java 的 NIO 库允许 Java 程序使用直接内存，用户数据缓冲区。

注意点：

- 也可能导致 OutOfMemoryError 异常
- 由于直接内存存在在 Java 堆外，因此它的大小不会直接受限于-Xmx 指定的最大堆大小，但是系统内存是有限的，Java 堆和直接内存的总和依旧受限于操作系统能给出的操作内存
- **缺点：**
  - 分配回收成本高
  - 不受 JVM 内存回收管理
- 直接内存的大可以通过 MaxDirectMemorySize 设置
- 如果不指定，默认与堆的最大值-Xmx 参数保持一致。

简单理解：Java Process Memory = Java Heap + native Memory

#### 3.6.4 设置方法区的大小及 OOM

方法区的大小不必是固定的，JVM 可以根据应用的需要动态调整：

- JDK7 以及以前：
  - `-XX:PermSize`：设置永久代的初始分配空间，默认值是 20.75M。
  - `-XX:MaxPermSize`：来设定永久代的最大可分配空间，32 位机器默认时 64M，64 位机器默认是 82M。
  - 当 JVM 加载的类的信息容量超过了这个值，就会抛出异常 java.lang.OutOfMemoryError:PermGen space。
- JDK8 以及以后：
  - 元空间大小可以使用参数-XX:MetaspaceSize 和-XX:MaxMetaspaceSize 指定。
  - 默认值依赖于平台。windows 下`-XX:MetaspaceSize`是 21M，`-XX:MaxMetaspaceSize`的值是-1，即没有限制。
  - 与永久代不同，如果不指定大小，默认情况下，虚拟机会耗尽所有的可用系统内存，如果元数据区发生溢出，虚拟机一样会抛出异常 OutOfMemoryError:Metaspace。
  - -XX:MetaspaceSize 设置初始值的大小。对于 64 位服务端的 JVM 来说，其默认的 -XX:MetaspaceSize 值为 21 M，这就是最高水线。一旦触及这个水位线，Full GC 将会被触发并卸载没用的类。然后这个高水位线会被重置。新的水位线取决于 GC 之后释放了多少空间。如果释放过多，就适当降低该值，如果释放过低，就提升该值，前提是不超过 -XX:MaxMetaspaceSize
  - 如果初始化的高为太低，上述的调整就会发生很多次，可以发现执行了多次 Full GC，建议将 -XX:MetaspaceSize 设置一个较高的值

#### 3.6.5 元空间 OOM

演示：

```java
/**
 * JDK8
 * VM:-XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 */
public class OomTest extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            OomTest oomTest = new OomTest();
            for (int i = 0; i < 10000; i++) {
                // 用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                // 指明版本号
                // 指明访问权限
                // 类的名字
                // 包名
                // 父类
                // 接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                // 返回 byte[]
                byte[] bytes = classWriter.toByteArray();
                // 类的加载
                oomTest.defineClass("Class" + i, bytes, 0, bytes.length);
                j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(j);
        }
    }
}
```

![image.png](jvm/image-1669529824212.png)
解决 OOM:

- 要解决 OOM 异常或者 heap space 异常，一般的手段是首先通过内存映射分析工具如（Eclipse Memory Analyzer）对 dump 出来的堆转储快照进行分析，重点是确认内存中的对象是否是必要的，也就是先分清楚到底是出现了内存泄漏（Memory Leak）还是内存溢出（Memory Overflow）。
- 如果只是内存泄漏，可以进一步通过工具检查泄漏对象到 GC Roots 的引用链。于是就能找到泄漏对象是通过怎样的路径与 GC Roots 相关联并导致垃圾回收器无法自动回收，掌握了泄漏对象的类型信息，以及 GC Roots 的引用链信息，就可以比较准确的定位出泄漏代码的位置。
- 如果不存在内存泄漏，也就是说内存中的对象必须要存活，那就应该检查虚拟机的堆参数 （-Xms -Xmx）与机器物理内存对比看是否还可以调大，从代码上检查是否存在某些对象的生命周期过长、持有状态时间过长、尝试减少运行期的内存消耗。

#### 3.6.6 方法区的存储内容

《深入理解 Java 虚拟机》书种对方法区（Method Area）存储的内容描述如下：它用于存储已经被虚拟机加载的类型信息、常量、域信息、静态变量、即时编译器编译（JIT）之后的代码缓存等。
![第09章_方法区存储信息.jpg](jvm/第09章_方法区存储信息.jpg)

##### 3.6.6.1 类型信息

类 Class、接口 interface 、枚举 enum、注解 annotation，JVM 必须在方法区存储以下类型信息。

- 这个类型的完整有效名称（全名=包名.类名）。
- 这个类型的直接父类的完整有效名（对于 interface 或是 java.lang.Object）都没有父类。
- 这个类型的修饰符（public abstract，final 的某个子集）。
- 这个类型直接接口的一个有序列表。

##### 3.6.6.2 运行时常量池

要弄清楚方法区，需要理解清楚 ClassFile，因为加载类的信息都在方法区。而要弄清楚方法区的运行时常量池，需要理解清楚 ClassFile 中的常量池。

一个有效的字节码文件除了包含类的版本信息、字段、方法以及接口等描述信息外，还包含一项信息那就是常量池表（Constant Pool Table） ，包括了各种字面量和对类型、域、方法的符号引用等。
![](jvm/20141010133603275)

一个 Java 源文件中的类、接口、编译后会产生一个字节码文件，而 Java 中的字节码需要数据支持，通常这种数据类型会很大以至于不能直接存到字节码里面，换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用，在动态链接的时候会用到运行时常量池。

常量池中的内容：

- 数值
- 字符串值
- 类引用
- 字段引用
- 方法引用

小结：常量池可以看作是一张表。虚拟机指令根据这张常量表找到要执行的类名、方法名、参数类型、字面量等类型。

常量池表（Constant Pool Teble）是 Class 文件的一部分，用于存放编译器生成的各种字面量与符号引用，这部分内容将在类加载之后存放在方法区的运行时常量池中。

JVM 会为每个已经加载的类型（类和接口）都维护一个常量池，池中的数据项像数组项一样，是通过索引访问的。

运行时常量池中包含多种不同的常量，包括编译器就已经明确的数值字面量，也包括运行期解析后才能够获得的方法或者字段引用。此时不再是常量池中的符号地址了，这里换为真实地址。

- 运行时常量池，相较于 Class 文件常量池的另一重要特性：具备动态性。

运行时常量池类似于传统编程语言的符号表（Symbol table），但是他所包含的数据却比符号表更加丰富一些。

当创建类或者接口的时候的运行时常量池的时候，如果构造运行时常量池所需的内存空间超过了方法区所能提供的最大值，则 JVM 会抛出 OutOfMemeoryError 异常。

##### 3.6.6.3 域（Field）信息

JVM 必须在方法区中保存类型的所有域相关的信息以及域的声明顺序。

- 域名称、域类型、域修饰符（public private protected static final volatile transient 的某个子集）。

##### 3.6.6.4 方法（Method）的信息

JVM 必须保存所有方法的以下信息，同域信息一样包括声明顺序。

- 方法名称。
- 方法的返回类型（或者 void）。
- 方法参数的数量和类型（按顺序）。
- 方法的修饰符 （public private protected static final synchronized native abstract 的某个子集）。
- 方法的字节码（bytecodes）、操作数栈、局部变量表以及大小（abstract 和 native 方法除外）。
- 异常表（abstract 和 native 方法除外）
  - 每个异常处理的开始位置、结束位置、代码处理在程序计数器中的偏移地址、被捕获的异常类的常量池索引。

##### 3.6.6.5 non-final 的类变量（静态变量）

静态变量和类关联在一起，随着类的加载和而加载它们成为类数据在逻辑上的一部分。

- 类变量被类的所有实例共享，即使没有类实例的时候也可以访问它。
- 被声明为 final 的类变量的处理方式不同，其在编译器就会被分配了值。
- ![image.png](jvm/image-1669529837791.png)

#### 3.6.7 方法区的垃圾回收

《Java 虚拟机规范》对于是否进行方法区的垃圾回收没有进行约束，如 Java11 中的 ZGC 收集器就不支持类卸载。
方法区的垃圾收集主要回收两部分内容：常量池中废弃的常量和不再使用的类型。

- 常量回收：
  - 方法区常量池主要存放两大类常量：字面量和符号引用。字面量比较的是比较接近 Java 语言的常量概念，如文本字符串，被声明为 final 的常量值等。而符号引用则属于编译原理方面的概念，包括下面这三类常量：
    - 类和接口的全限定名
    - 字段的名称和描述符
    - 方法的名称和描述符
  - Hotspot 虚拟机对常量池的回收策略是很明确的，只要常量池中的常量没有被任何地方引用，就可以被回收。
    - 回收废弃常量与回收 Java 堆中的对象非常相似。
- 不再使用的类型回收：
  - 不再使用的类型需要同时满足三个条件：
    - 该类所有的实例都已经被回收（堆内存中已被回收）
    - 加载该类的类加载器已经被回收
    - 该类对应的 Class 类对象没有任何在任何地方被引用（无法通过反射访问该类）。
  - 上述三个条件的满足条件非常苛刻，同时在大量使用反射、动态代理、CGLIB 等字节码框架，动态生成的 jsp 以及 osgi 的这类频繁自定义类加载器的场景中，通常需要 Java 虚拟机具备类型卸载能力，以保证不会对方法区造成过大的内存压力。

### 3.7 本地方法栈（Native Method Stack）

![image.png](jvm/image-1669529841057.png)
Java 虚拟机用于管理 Java 方法的调用，而本地方法栈用于管理本地方法的调用。

- 线程私有。
- 允许被实现固定或者是可拓展的内存大小（在内存溢出方面与虚拟机栈是相同的）：
  - 如果线程请求分配的栈容量超过本地方法栈的最大容量，Java 虚拟机会抛出一个 StackOverflowError 异常。
  - 如果可以动态拓展，那么在拓展到无法申请到足够的内存的时候或者在创建新的线程没有足够的内存的时候，就会抛出 OutOfMemaryError 异常。
- Native Method Stack 中登记 native 方法，在 Execution Engine 执行的时候加载本地方法库。
- 当某个线程调用一个本地方法的时候，就进入了一个全新的并且不再受虚拟机限制的世界，他和虚拟机有相同的权限。
  - 本地方法可以通过本地方法接口访问虚拟机内部的运行时数据区。
  - 可以直接使用本地处理器中的寄存器。
  - 可以直接从本地内存的堆中分配任意数量的内存。
- 并不是所有的 JVM 都支持本地方法，因为 Java 虚拟机规范并没有明确要求本地方法栈的使用语言、具体实现方式、数据结构等，如果 JVM 产品不打算支持 native 方法，也可以无需实现本地方法栈。
- 在 Hotspot JVM 中直接将本地方法栈和虚拟机栈合二为一。

### 3.8 常见面试题

#### 3.8.1 虚拟机栈（栈）

1. 虚拟机栈：栈中可能出现的异常。
   - Java 虚拟机规范允许 Java 栈的大小是动态的或者是固定不变的。
     - 如果采用固定大小的 Java 虚拟机栈，那每一个线程的 Java 虚拟机栈容量可以在线程创建的时候独立选定，如果请求超过虚拟机栈的最大容量，Java 虚拟机将会抛出 StackOverflowError 异常。
       - ![image.png](jvm/image-1669529845529.png)
     - 如果 Java 虚拟机栈可以动态扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程的时候，没有足够的内存去创建虚拟机栈。那么 Java 虚拟机将会抛出 OutOfMemoryError 异常。
2. 举例栈溢出的情况：
   - 递归调用深度太深 （StackOverflowError）
   - 通过 -Xss 设设置大小
   - 整个内存不足了就会出现 OOM 问题
3. 调整栈的大小，就能保证不出现溢出吗？
   - 不一定，如死循环只能延迟栈溢出的时间。
   - 但有可能可以解决栈内存溢出问题。
4. 分配的栈内存越大越好吗？
   - 并不是，内存空间是有限的，栈内存变大了，其他的空间就少了。
5. 垃圾回收是否会涉及到虚拟机栈？
   - 不会。垃圾回收只涉及堆空间和方法区。
6. 方法中定义的局部变量是否线程安全？
   - 具体问题具体分析。

```java
//内部产生，内部消亡的，就是安全的，否则就是不安全的
public class StringBuilderTest {
    public static void method1() {
        StringBuilder sb = new StringBuilder();//线程安全（sb内部消亡）
        sb.append("a");
        sb.append("b");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
    public static void method2(StringBuilder sb) {//线程不安全（sb外部产生）
        sb.append("a");
        sb.append("b");
    }
    public static StringBuilder method3() {
        StringBuilder sb = new StringBuilder();//线程不安全（sb未内部消亡）
        sb.append("a");
        sb.append("b");
        return sb;
    }
    public static String method4() {
        StringBuilder sb = new StringBuilder();//线程安全（sb内部消亡，因为toString()它有返回值，method4方法的最终结果是toString()的返回结果）
        sb.append("a");
        sb.append("b");
        return sb.toString();
    }
}
```

#### 3.8.2 堆空间

1. 堆是分配对象存储的唯一选择吗？
   - 不是。在《深入理解 Java 虚拟机》中关于 Java 堆内存中有这样的一段描述：
     - 随着 JIT 编译器的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化，所有的对象都分配到堆上就渐渐变得不是那么绝对了。
        - 在 Java 虚拟机中。对象是在 Java 堆中分配内存的，这是一个普遍的常识。但是有一种特殊情况，如果经过逃逸分析（Escape Analysis）后发现，一个对象并没有逃逸出方法的话，那么就有可能被优化成栈上分配，这样就无需再堆上分配内存，也无需进行垃圾回收了，这是最常见的对外存储技术。
        - 此外，前面提到的基于 OpenJDK 深度定制的 TaoBaoVM，其中创新的 GCIH（GC Invisible heap）技术实现 off-heap，将生命周期比较长的 Java 对象从 heap 移到 heap 外，并且 GC 不能管理 GCIH 内部的对象，以此达到降低 GC 的回收频率和提升 GC 的回收效率的目的。
        - 但是，由于逃逸分析并不成熟，且逃逸分析自身需要进行一系列复杂分析，此过程也相对耗时，所以可以认为对象实例是分配到堆上的。
        - 而且 jdk7 之后，intern 字符串的缓存和静态变量也是分配到堆上的，也符合对象实例分配在堆上。
          - jdk7 之前，intern 字符串的缓存和静态变量分配在永久代上。
2. JVM 内存模型，有哪些区？分别是干嘛的？
3. Java8 的内存分代改进
4. JVM 内存分哪几个区域？每个区域的作用是什么
5. JVM 的内存分布/内存结构？栈和堆的区别？堆的结构？为什么需要两个 Survivor 区
6. Eden 和 Survivor 的比例分配
7. JVM 内存分区，为什么要有新生代和老年代？新生代和老年代为什么要分为 Eden 和 Survivor
8. JVM 的运行时数据区
9. 什么时候对象会进入老年代
10. jvm 永久代中会发生垃圾收集吗？Java 规范并不约束，

### 3.9 本章总结

![](jvm/image-1669529850525.png)
stack 是虚拟机栈，stack frame 是栈帧。

## 第 4 章 本地方法接口

![image.png](jvm/image-1669529856308.png)
本地方法接口：

- 简单的来讲，一个 Native Method 就是一个 Java 调用非 Java 代码的接口。
- Native Method 的实现由非 Java 语言实现，比如 C。这个特征并非 Java 所特有，如 C++可以使用 extern "c" 告知 C++编译器去调用以个 C 的函数。
- native method 的定义像 Java Interface，Java 中并不提供实现体，因为其实现体是由其他语言在外面实现的。
- 本地接口的作用是融合不同的编程语言为 Java 所用，它的初衷是融合 C/C++程序。
- native 关键字可以与除了 abstract 外的关键字公用（因为 abstract 修饰说明不能有方法体，但是 native 是有方法体的，不过是别的语言实现的）。

为什么需要使用 Native Method：有些层次的任务用 Java 实现起来不容易，或者对程序的效率很在意时：

- 与 Java 环境外交互（主要原因）：
  - 本地方法提供了一种交流机制，为程序员提供了一个非常简洁的接口，而无需去了解 Java 应用之外的繁琐的细节。
- 与操作系统交互（执行效率）：
  - JVM 不是一个完整的系统，它经常依赖于一些底层系统的支持，如操作系统。通过使用本地方法，就可以用 Java 实现 jre 与底层系统的交互，甚至 JVM 的一部分就是使用 C 写的。还有，如果要使用一些 Java 语言本身没有提供封装的操作系统的特性的时候，也需要使用本地方法。
- Sun's Java：
  - Sun 的解释器使用 C 实现的，这使得它能像一些普通的 C 一样与外界交互。
  - jre 大部分是用 Java 实现的，它也通过一些本地方法与外界交互。
  - 例如：类 java.lang.Thread 的 setPriority()方法使用 Java 实现的，但是调用的是 setPriority0(),这个是本地方法 ，C 实现的，被植入 JVM 内部。

现状：

- 目前该方法使用的越来越少了，除非是与硬件相关的应应用，比如通过 Java 程序驱动打印机或者 Java 系统管理生产设备，在企业级应用很少见。

## 第 5 章 执行引擎

### 5.1 执行引擎的作用和工作流程

![image.png](jvm/image-1669529861287.png)
虚拟机与物理机的区别是物理机的执行引擎是建立在处理器、缓存、指令集和操作系统层面上的，而虚拟机的执行引擎则是由软件自行实现的，因此可以不受物理条件的制约指令集和执行引擎的结构体系，能够执行哪些不被硬件直接支持的指令集格式。

JVM 的主要任务是负责 装载字节码到其内部，但是字节码并不能够直接运行在操作系统之上，因为字节码指令并非等价于本地机器指令，它内部包含的仅仅只是一些能够被 JVM 所识别的字节码指令、符号表、以及其他辅助信息。

执行引擎（Execution Engine）的任务就是 将字节码指令解释/编译为对应平台的本地机器指令才可以，简单来说 JVM 的执行引擎充当了高级语言翻译为机器语言的译者。
![](jvm/image-1669529863618.png)

每当执行完一项指令之后，PC 寄存器就会更新下一条需要被执行的指令的地址，执行引擎在操作数栈中找到具体的指令，翻译成机器指令。

方法在执行的过程中，执行引擎可能通过存储在局部变量表中的对象引用准确定位到存储在 Java 堆区中的对象的实例信息，以及通过对象头中的元数据指针定位到目标对象的类型信息。

从外观上来看，所有的 Java 虚拟机的执行引擎的输入、输出都是一致的：输入是字节码二进制流，处理过程是字节码解析执行的等效流程，输出的是执行结果。

### 5.3 高级语言执行过程

![第12章_理解执行引擎.jpg](jvm/第12章_理解执行引擎.jpg)
指令：

- 由于机器码是由 0 和 1 组成的二进制序列，可读性实在太差，于是人们发明了指令
- 指令就是把机器码中特定的 0 和 1 序列，简化成相应的指令（一般使用英文简写，如 mov，inc 等），可读性稍好。
- 由于不同的硬件平台，执行同一个操作，对应的机器码可能不同，所以不同的硬件平台的同一种指令（如 mov）对应的机器码也不同。

指令集：

- 不同的硬件平台，各自支持的指令，是有差别的。因此每个平台所支持的指令，称之为对应平台的指令集：
  - x86 指令集，对应的是 x86 架构的平台
  - ARM 指令集，对应的是 ARM 架构的平台

机器码：

- 各种用二进制编码的方式表示的指令，叫做机器指令码，用它编写的程序就是机器语言。
- 机器语言虽然能够被计算机理解和接受，但是和人们的语言差别太大，不容易被人们理解和记忆，并且用它编程容易出差错。
- 用它编写的程序一经输入到计算机，CPU 直接读取执行，因此和其他语言编写的程序相比，执行速度最快。
- 机器指令与 CPU 紧密相关，所以不同种类的 CPU 所对应的机器指令也就不同。

汇编语言：

- 由于机器指令的可读性还是太差，于是人们有发明了汇编语言。
- 在汇编语言中，用助记符（Mnemonics）代替机器指令的操作码，用地址符号（Symbol）或者标号（Label）代替指令操作数的地址。
- 在不同的硬件平台，汇编语言对应着不同的机器语言指令集，通过汇编过程转成机器指令。
- 由于计算机只认识指令码，所以用汇编语言编写的程序还必须翻译成机器指令码，计算机才能识别和执行。

高级语言：

- 为了使得计算机用户编程序更容易一些，后来就出现了各种高级计算机语言，高级语言比机器语言、汇编语言更接近人的语言。
- 当计算机执行高级语言编写程序的时候，仍然需要将程序解释和编译成机器的指令码。完成这个过程的程序就叫做解释程序或者编译程序。

![](jvm/image-1669529870129.png)
字节码：

- 字节码是一种中间状态（中间码）的二进制代码（文件），它比机器码更抽象，需要直译器转译之后才能成为机器码。
- 字节码主要是为了实现特定软件运行和软件环境、与硬件环境无关。
- 字节码的实现方式是通过编译器和虚拟机器，编译器将源码编译成字节码，特定平台上的虚拟机器将字节码转译为可以执行的命令。

### 5.3 Java 程序的编译运行和解释运行

#### 5.3.1 执行过程总览

![](jvm/image-1669529872502.png)
上图中最下蓝色行为编译原理的过程。

大部分的程序代码转换成物理机的目标代码或者虚拟机可以执行的指令集之前，都需要经过上图的各个步骤。
![](jvm/image-1669529874463.png)
解释器：当 Java 虚拟机启动的时候会根据预定义的规范 **对字节码采用逐行解释的方式执行** ，将每条字节码文件中的内容"翻译"为对应平台的本地机器指令执行。

JIT(Just In Time Compiler)编译器：将源代码直接编译成和本地机器平台相关的机器语言。
在 JVM 执行 Java 源码的时候，通常解释执行和编译执行二者结合起来执行，所以说 Java 语言式半解释半编译的运行。

#### 5.3.2 解释器

解释器真正意义上所承担的角色就是一个运行时“翻译者”，将字节码文件中的内容“翻译”为对应平台的本地机器指令执行。
当一条字节码指令被解释执行完成之后，接着再根据 PC 寄存器中记录的下一条需要被执行的字节码指令执行解释操作。
在 Java 发展历史里，一共有两套解释执行器，即古老的字节码解释器、现在普遍使用的模板解释器：

- 字节码解释器：在执行时通过纯软件代码模拟字节码的执行，效率非常地下。
- 模板解释器：将每一条字节码和一个模板函数相关联，模板函数中直接产生这条字节码执行的机器码，从而很大程度上提高了解释器的性能。
  - 在 Hotspot VM 中，解释器主要由 Interpreter 模块和 Code 模块构成：
    - Interpreter 模块：实现了解释器的核心功能。
    - Code 模块：用于管理 Hotspot VM 在运行时生成的本地机器指令。

由于解释器在设计上和实现上非常简单，因此除了 Java 之外，其他语言也基于解释器执行。但是今天基于解释器执行已经沦落为低效的代名词。
为了解决这个问题，JVM 平台支持一种即时编译的技术。即时编译的目的是避免函数被解释执行，而是将整个函数体编译成机器码，每次该函数执行的时候，只执行编译后的机器码即可，这种方式可以使得执行效率大幅度提升。

#### 5.3.3 JIT 编译器

Hotspot 虚拟机是采用解释器和即时编译器并存的架构，在其运行的时候，会找到解释器和即时编译器相互合作的节点。各自取长补短。
既然 Hotspot VM 中已经内置了 JIT 编译器了，那么为啥还需要再使用解释器来“拖累”程序的执行性能呢？比如 JRrockit VM 就不包含解释器，字节码全部依靠即时编译后执行 - 所以它号称最快的虚拟机
当程序启动的时候，解释器可以马上发挥作用，省去编译的时间，响应速度快。编译器想要发挥作用，需要把代码编译为本地代码，会消耗一定的时间。但是编译为本地代码后，执行效率高。

- 所以 JRockit 虚拟机执行性能很高，但是在启动的时候会会花费更多的时间。

Hotspot 虚拟机解释器与 JIT 编译器二者皆用，当虚拟机启动的时候，解释器首先发生作用，不必等待即时编译器全部编译完再执行，这样可以省去不必要的编译时间。随着时间的推移，越来越多的热点代码被编译为机器指令代码，所以程序执行会越来越快。

同时，如果遇到编译器不能编译成机器指令的时候，还可以使用解释器作为后备方案。

Java 语言的编译期其实是一段不确定的操作过程，因为它可能是指一个前端编译器（也叫编译器的前端）把`.java`文件转换成`.class`文件的过程；也可能是指后端运行编译器（JIT 编译器 Just In Time Compiler） 把字节码转换为机器码的过程；还可能是使用静态提前编译器（AOT 编译器 Ahead Of Time Compiler）直接把`.java `文件编译成本地机器码的过程。

#### 5.3.4 热点代码及探测方式

是否需要启动 JIT 编译器将字节码直接编译为对应平台的本地机器指令，则需要根据代码被调用执行的频率 而定。这样的被编译为本地代码的字节码，称为热点代码。

JIT 编译器在运行时会针对那些被频繁调用的“热点代码”做出深度优化，将其直接编译为对应平台的本地机器指令，以提升 Java 程序的执行性能。

一个被多次调用的方法，或者是一个方法体内部循环次数比较多的循环体都可以称为是热点代码，因此都可以通过 JIT 编译器翻译为本地机器指令。由于这种编译方式发生在方法的执行过程中，因此也被称为栈上替换。或者简称为 OSR（On Stack Replacement）。

Hotspot VM 采用的热点探测方式是基于计数器的热点探测。Hotspot VM 为每一个方法都建立 2 个不同类型的计数器，分别为方法调用计数器，分别为方法调用计数器（Invocation Counter）和回边计数器（Back Edge Counter）。

方法调用计数器：

- 用于统计方法被调用的次数，默认阈值在 Client 模式下是 1500 次，在 Server 模式下是 10000 次。这个阈值可以通过虚拟机参数 -XX:ComplieThreshold 来设定。
- 当一个方法被调用的时候，会先检查该方法是否存在被 JIT 编译过的版本，如果存在，则优先使用编译后的本地代码来执行，如果不存在已经被编译的版本，则将此方法的调用计数器值加 1，然后判断**方法调用计数器和回边调用计数器之和**是否超过方法调用计数器的阈值，如果超过，就向编译器提交一个该方法的编译请求。
- ![](jvm/第12章_方法调用计数器.jpg)

回边计数器

- 统计方法中循环体代码的执行次数，建立回边计数器统计就是为了触发 OSR 编译。
- ![第12章_回边计数器.jpg](jvm/第12章_回边计数器.jpg)

热度衰减：

随着程序运行时间加长，方法调用次数肯定会达到阈值，但实际上程序的运行环境和运行要求并没有发生变化。所以方法调用计数器是一个相对值，即一段时间之内方法被调用的次数。

当超过一定的时间限制，调用次数仍未达到阈值，则该方法的调用计数器就会减少一半，这个过程成为方法调用计数器热度的衰减（Counter Decay），而这段时间就称为此方法统计的半衰周期（Counter Half Life Time）。

进行热度衰减的动作是在虚拟机内部进行垃圾收集的时候顺便执行的，可以使用虚拟机参数`-XX:-UseCouinterDecay` 关闭，使用`-XX:CounterHalfLifeTime` 参数可以设置半衰周期的时间，单位是秒。

### 5.4 Hostpot VM

#### 5.4.1 Hostpot VM 设置程序的执行方式

缺省情况下 Hotspot 虚拟机使用解释器与编译器并存的架构，也可以对其进行调整，设置为完全采用解释器或者完全采用即时编译器

- -Xint:完全采用解释器模式执行程序
- -Xcomp:完全采用即时编译器模式执行程序，如果编译出现问题，解释器会介入
- -Xmixed:采用解释器+即时编译器混合模式执行程序
- 命令行修改：
  - ![image.png](jvm/image-1669529885073.png)
- 虚拟机运行参数修改（针对当前的代码）：
  - ![image.png](jvm/image-1669529887290.png)

#### 5.4.2 Hostpot VM 中 JIT 分类

在 Hotspot VM 内嵌两个 JIT 编译器，分别为 Client/ Server Compiler，但大多数情况下称之为 C1 编译器和 C2 编译器，可以使用命令行指令或参数设置使用哪一种编译器（64 位的 JDK 不支持切换，只能使用 server 版本）。

- `-clien`：C1 编译器会对字节码进行简单和可靠的优化，耗时短，以达到更快的编译速度。
- `-server` C2 编译器进行耗时较长的优化，以及激进优化。但是优化的代码执行效率高。

C1 编译器优化策略：

- 方法内联：将引用的函数代码编译到引用点处，这样就可减少栈帧的生成，减少参数的传递和跳转过程。
- 去虚拟化：对唯一的实现类进行内联。
- 冗余消除：在运行期间会把一些不会执行的代码折叠掉。

C2 的优化主要是在全局层面，逃逸分析是优化的基础。基于逃逸分析在 C2 上有如下几种优化：

- 标量替换：用标量替换聚合变量。
- 栈上分配：对于为逃逸的对象分配对象的在栈而不是在堆。
- 同步消除：清除同步动作，通常指的是 synchronized。

分层编译（Tiered Compiler）策略：程序解释执行（不开启性能监控）可以触发 C1，将字节码编译成机器码，可以进行简单优化，也可加上性能优化，C2 编译会根据性能监控信息进行激进优化。分层策略也就是说 C1、C2 编译器并没有只是用一个。

- 不过在 Java7 版本之后，一旦显式使用"-server"命令时，默认使用 c1 和 c2 共同执行。

总结：

- JIT 编译器出来的机器码性能高于解释器解释执行的代码。
- C2 编译器启动时长比 C1 编译器长，系统稳定执行后，C2 编译器执行速度远快于 C1 编译器。

#### 5.4.3 Graal 编译器与 AOT 编译器

Graal 编译器（相比对 C2 编译器）：在 JDK10 之后，Hotspot 加入了一个全新的即时编译器：Graal 编译器，编译效果可以追平 C2 编译器。目前还在实验阶段，需要激活才能使用：

- -XX:+UnlockExperimentalVMOptions
- -XX:+UseJVMCICompiler

AOT 编译器（相比于 JIT 编译器）：JDK9.0 之后加入了 AOT 编译器（静态提前编译器 Ahead Of Time Compiler），同时引入了实验性的 AOT 编译工具 jaotc ，它借助了 Graal 编译器，将所输入的 Java 类文件转换为机器码，并存放到生成的动态共享库中。

- AOT 编译器是与即时编译器对立的一个概念，即时编译器在程序运行的时候，而 AOT 编译器是在程序运行之前。
- 最大好处：可以直接执行，不必等程序预热，减少第一次运行慢的体验。
- 缺点：
  - 破坏了 Java”一次编译，到处运行“，因为直接编译之后的文件是固定的，必须为每种系统都编译。
  - 降低了 Java 动态链接的动态性，加载的代码在编译器期就必须全部已知。
  - 还需要继续优化中，最初只支持 Linux x64 java base。

### 5.5 垃圾回收器

参加本文第 8 章[垃圾回收器](https://www.yuque.com/zhuyuqi/java/dgxehd?inner=T4sus)

## 第 6 章 对象的实例化内存布局与访问定位

### 6.1 对象的实例化

对象的创建方式及创建过程：
![第10章_对象的实例化.jpg](jvm/第10章_对象的实例化.jpg)

1. 判断对象的对应的类是否加载、链接、初始化：

虚拟机遇到一条 new 指令，首先要去检查这个指令的参数能否在 Metaspace 的常量池中定位到一个类的符号引用，并且检查这个符号引用代表的类是否已经被加载、解析、和初始化（即判断类元信息是否存在），如果没有，那么在双亲委派机制模式下，使用当前类加载器以及 ClassLoader+全限定名为 key 查找对应的`.class`文件，如果没有找到文件，就抛出 ClassNotFoundException 异常。如何找到，则进行类加载，并生成对应的 Class 类对象。

2. 为对象分配内存：

首先区计算对象占用空间的大小，接着在内存中划分一块内存给新的对象，如果实例成员变量是引用变量，仅仅分配引用变量空间即可，也就是 4 个字节大小。

- **如果内存规整**
  - 指针碰撞
    - 如果内存是规整的，那么虚拟机将采用指针碰撞法（Bump The Pointer）来为对象分配内存
    - 意思是所有用过的内存在一边，空闲的内存在另一边，中间放着一个指针作为分界点的指示器，分配内存就仅仅是把指针向空闲的那边挪动一段与对象大小相等的举例罢了，如果垃圾回收器选择的是 Serial、ParNew 这种基于压缩算法的，虚拟机采用这种分配方式。一般使用带有 compact（整理）过程的收集器的时候，使用指针碰撞。
- **如果内存不规整**
  - 虚拟机需要维护一个列表。
  - 空闲列表分配：如果对象不是规整的，已经使用的内存和未使用的内存相互交错，那么虚拟机将采用的是空闲列表法来为对象分配内存。意思是虚拟机维护了一个列表，记录上哪些内存是可用的吗，再分配的时候从列表中找到一块足够大的空间划分给对象实例，并更新列表上的内容，这种分配方式成为“空闲列表（Free List）”

3. 处理并发安全问题：

   - 采用 CAS 失败重试、区域加锁保证更新的原子性。
   - 每个线程预先分配一块 TLAB。- 通过-XX:+/-UseTLAB 参数来设定。

4. 初始化分配到的空间（默认初始化）：

所有属性是设置默认时值，保证对象实例字段在不赋值的时候可以直接使用。

5. 设置对象的对象头

将对象的所属类（即类的元数据信息）、对象的 HashCode 和对象的 GC 信息、锁信息、等数据存储再对象的对象头中。在这个过程具体设置方式取决于 JVM 的实现。

6. 执行 init 方法进行初始化

在 Java 程序员的视角看来，初始化才正式开始。初始化成员变量、执行实例化代码块，调用类的构造方法，并把堆内对象首地址赋值给引用变量。
因此一般来说（由字节码中是否跟随着有 invokespecial 指令决定），new 指令之后会执行方法，把对象按照程序员的意愿进行初始化，这样一个真正的可用的对象才算完全的创建出来。
![看不懂先别看](jvm/image-1669529899096.png "看不懂先别看")

### 6.2 对象的内存布局

![image.png](jvm/image-1669529902385.png)

1. 对象头：
   - 运行时元数据（Mark World）
     - 哈希值
     - GC 分代年龄
     - 锁状态标志
     - 线程持有的锁
     - 偏向线程 ID
     - 偏向时间戳
   - 类型指针
     - 指向类元素数据的 InstanceClass，确定该对象的所属类型
2. 实例数据（Instance Data）
   - 对象真正存储的有效信息，包括程序代码中定义的各种类型的字段（包括从父类继承下来的和本身拥有的字段）
   - 规则
     - 相同宽度的字段总是被分配到一起
     - 父类中定义的变量会出现在子类之前
     - 如果 CompactFields 参数为 true（默认为 true）：子类的窄变量可能插入到父类变量的空隙
3. 对齐填充（Padding）：不是必须的，也没有特殊的含义，仅仅是占位符。

案例：

```java
public class ConsumerTest {
    public static void main(String[] args) {
        Customer customer = new Customer();
    }
}
class Customer {
    int id = 1001;
    String name;
    Account acct;
    {
        name = "匿名用户";
    }
    public Customer() {
        acct = new Account();
    }
}
class Account {
}
```

![image.png](jvm/image-1669529906266.png)
问题：字符串常量池在堆空间吗？3.6.6.2 作何理解？

### 6.3 对象的访问定位

JVM 中如何通过栈帧的对象引用访问到其内部的对象实例？
![](jvm/image-1669529913774.png)
![第10章_对象访问定位.jpg](jvm/第10章_对象访问定位.jpg)
Java 虚拟机规范并未明确规定使用哪种方式，Hotspot 使用了直接指针的方式。
句柄访问：

- 优点：reference 中存储稳定句柄地址，对象被移动（垃圾收集时候移动对象很普遍）时会改变句柄中的实例数据指针即可，reference 本身不需要被修改。
- 缺点：需要多占用一些空间。
- ![](jvm/image-1669529918232.png)

直接指针：

- ![](jvm/image-1669529919852.png)

### 6.4 面试题

1. 对象在 JVM 中如何存储？
2. 对象的头信息包含哪些东西？

## 第 7 章 String Table

### 7.1 String 的基本特性

- String：字符串，使用一对`""`引起来表示：
  - `String s1 = "hello";`
  - `String s2 = new String("hello");`
- String 类声明为 final 的，不可被继承
- String 实现了 Serializable 接口：表示字符串是支持序列化的；实现了 Comparable 接口：表示 String 可以比较大小、排序。
- 在 JDK8 以及之前，String 内部定义了**final char[] value**用来存储字符串数据，JDK9 之后使用 **byte[]加编码标记，节约了一些空间。**
  - 同时 StringBuilder、StringBuffer 进行了修改。
- String：代表不可变的字符序列。简称：不可变性
  - 当对字符串重新赋值的时候，需要重写指定内存区域赋值，而不是修改原有的 value 进行赋值。
  - 不能对现有的字符串进行连接操作，也需要重新指定内存区域，不是修改原有的 value 进行赋值。
  - 当调用 String 的 replace()方法修改指定字符或者字符串的时候，也需要重新指定内存区域赋值，不能使用原有的 value 赋值
- 通过字面量的方式（区别于 new）给一个字符串赋值，此时的字符串声明在常量池中。
- 字符串常量池中是不会存储相同内容的字符串。
- String 的 String Pool 是一个固定大小的 HashTable，默认长度为 1009，如果放进去 String Pool 的 String 非常多，就会造成 Hash 冲突严重，从而导致链表很长，而链表长了之后，会直接影响调用 String.intern()的性能。
  - 使用 **-XX:StringTableSize** 可设置 StringTable 的长度
  - 在 JDK6 中的 StringTable 是固定的，就是 1009 的长度，如果字符串常量池中的字符串过多就会导致效率下降很快，StirngTableSize 设置没有要求
  - 在 JDK7 中，StringTable 的默认长度为 60013，StirngTableSize 没有要求
  - JDK8 开始 1009 是可设置的最小值
  - Stirng.intern()方法：如果字符串常量池中没有对应的字符串的话，就添加在常量池

### 7.2 String 的内存分配

在 Java 语言中有 8 种基本数据类型和 String，这些类型为了使它们在运行速度中更快，更节省内存，都提供了常量池。

常量池就相似于一个 Java 系统级别提供的缓存。8 中基本类型都是系统协调的，String 类型的常量池比较特殊，主要的使用方法有 2 种：

- 直接使用双引号声明 String 的对象会直接存储在常量池种
- 如果不是使用双引号声明的 String 对象，可以使用 String 提供的 intern() 方法

在 JDK6 以及以前，字符串常量池存放在永久代。JDK7 字符串常量池的位置调整到 Java 堆内。

- 所有的字符串都保存在堆中，和其他普通对象一样，这样可以在进行调优的时候仅仅需要调整堆大小就可以了
- 因而 jdk7 中，使用 String.intern();创建字符串更优。

Java8 元空间，字符串常量池在堆。

- **StringTable 为什么需要调整**
  - 之前的永久代比较小，放大量的字符串，会占用很大的空间
  - 永久代垃圾回收的频率很低

Java 语言规范要求完全相同的字符字面常量，应该包含同样的 Unicode 字符序列，并且必须是指向同一个 String 类实例

```java
public class Memory {
    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        Memory mem = new Memory();
        mem.foo(obj);
    }
    public void foo(Object param) {
        String str = param.toString();
        System.out.println(str);
    }
}
```

![](jvm/image-1669529923701.png)

### 7.3 字符串的拼接操作

- 常量与常量的拼接结果在常量池，原理是编译期优化。
- 常量池中不会存在相同内容的常量。
- 只要其中有一个是变量，结果就在堆中。变量拼接的原理是 StringBuilder，并调用 append()方法，在调用 toString()方法。
  - 但如果拼接的变量都用 final 修饰（常量引用），则任然使用编译器优化放在常量池，不使用 Stringbuilder()。
  - 针对 final 修饰类、方法、基本数据类型、引用数据类型的量的结果的时候，能使用 fianl 就使用 final。
- 如果拼接的结果调用 intern()方法，则主动将常量池中还没有的字符串对象放入池中，并返回此对象的地址。
- 使用 StringBuilder 的 append()方式执行效率远高于拼接，全程只会创建一个 StringBuilder。
  - 拼接会创建较多的 StringBuilder 和 String 对象，内存占用更大，且进行 GC 时，会花费更多的时间。
  - 在使用 StringBuilder 和 StringBuffer 的时候，如果已知具体的大小，可以使用有参构造函数创建一个指定初始大小的数组，从避免返回扩容转移数组的空间和资源消耗，进一步提高效率。

### 7.4 intern()方法

intern 方法会从字符串常量池中查询当前字符串是否存在，若不存在，就会将当前字符串放入常量池中。

只要字符串对象调用了 intern() 方法，那么返回值是指向字符串常量池中的数据。具体参考 7.5.2

2022.10.08 个人理解：intern() 方法并不改变原字符串变量的指向，只是维护字符串常量池，如果常量池中已经有，jdk6、7、8 均不做操作，如果常量池中没有，jdk6 复制一份新对象放到常量池，jdk7、8 复制一份对象地址放到常量池。

结论：对于程序中大量的重复的字符串，可以使用 intern() 方法节省空间。

### 7.5 面试题

#### 7.5.1 字符串拼接

1. new String("ab") 会创建几个对象？

两个，一个是 new 关键字在堆空间创建的，一个是在字符串常量池创建的 ab。字节码文件如下：

```java
 0 new #2 <java/lang/String>
 3 dup
 4 ldc #3 <ab>
 6 invokespecial #4 <java/lang/String.<init>>
 9 astore_1
10 return
```

2. new String("a") + new String("b") 创建了几个对象？

五个。常量池中 a、b，堆中 new String 的 a、b，堆中 new StringBuilder。

如果再深究，由于 new Builder()会调用 toString()方法，也就是还会创建 new String("ab")，也就是第六个对象。但是，常量池里没有创建 ab 对象。

#### 7.5.2 intern()

1. 判断结果：

```java
public class StringInternDemo {
    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = new String("1");
        System.out.println(s == s2);//false、false
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);//false、true
    }
}
```

jdk6：false、false

jdk7、jdk8：false、true

jdk7、8 的原因：

- `new String("1")`创建了两个对象，所以`s.intern()`并不会再起作用。
  - ![image.png](jvm/image-1669529927119.png)
- `new String("1") + new String("1")`创建了 5 个对象，但常量池中没有”11“这个数，`s3.intern()`在常量池创建了”11“。
  - ![image.png](jvm/image-1669529929845.png)

2. 进阶：判断结果。

```java
public class StringInternDemo {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        s1.intern();
        String s2 = "11";
        System.out.println(s1 == s2);//true
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);//false
    }
}
```

`s1.intern()`在常量池创建了 11，`s3.intern()`什么也没干。

```java
public class StringInternDemo {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        s1.intern();
        String s2 = "11";
        System.out.println(s1 == s2);//true
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        String s5 = s3.intern();
        System.out.println(s5 == s4);//true
    }
}
```

总结：

- jdk6 中，将这个字符串对象尝试放入串池
  - 如果串池中有，则不会放入，返回已有的串池中的对象的地址
  - 如果没有，会把此对象复制一份，放入串池，并返回串池中的对象地址
- jdk7 中，将这个字符串对象尝试放入串池
  - 如果串池中有，则并不会放入。返回已有的串池中的对象的地址
  - 如果没有，会把此对象的引用地址复制一份，放入串池，并返回串池中引用地址的地址

3. 进阶

![image.png](jvm/image-1669529933878.png)![image.png](jvm/image-1669529936071.png)

4. 进阶

![image.png](jvm/image-1669529939230.png)

### 7.6 G1 的 String 去重操作

针对的是堆区，常量池本身就 1 份。

- Java 应用的数据测试有如下结果：
  - 堆存活数据集合里面的 String 对象占用了 25%
  - 堆存活数据集合里面重复的对象有 13.5%
  - String 的平均长度是 45
- 重复是指 equals 为真。
- ![image.png](jvm/image-1669529947714.png)
- ![](jvm/2022-12-01-13-53-03.png)

## 第 8 章 垃圾回收

### 8.1 概述

#### 8.1.1 垃圾的概念

Java 和 C++的区别核心就是内存动态分配和垃圾收集技术。

1960 年，第一门开始使用内存动态分配和垃圾收集技术的 Lisp 语言诞生。

垃圾是指在运行程序中没有任何指针指向的对象，这个对象就是需要被回收的垃圾。

#### 8.1.2 垃圾回收的意义

对于高级语言来说如果不进行垃圾回收，内存迟早都会被消耗完。如果不及时对内存中的垃圾进行清理，那么这些垃圾会一直占用空间，直到程序运行结束，被占用的空间就浪费了，可能会导致内存溢出。

除了释放没用的对象，垃圾回收也可以清除内存里的记忆碎片。碎片整理将所占用的堆内存移动到堆的一端，以便 JVM 将整理出来的内存分配给新的对象。

在早期的 C/C++时代，垃圾回收基本上是手动执行的，开发人员可以使用 new 关键字进行内存申请 ，并使用 delete 关键字进行内存释放。

这种方式可以灵活控制内存释放的时间，但是会给开发人员带来 频繁申请和释放内存的管理负担，倘若有一处内存区间由于程序员编码的问题忘记被回收，那么就会产生内存泄漏，垃圾对象永远多无法被清除，随着系统运行时间的不断增长，垃圾对象所耗内存可能持续上升，直到出现了内存溢出并造成应用程序崩溃。

内存泄漏：一个对象已经成为了垃圾，但是无法被回收。

#### 8.1.3 Java 的自动内存管理

好处：

- 自动内存管理，无需开发人员手动参与内存的分配与回收，这样降低内存泄漏和内存溢出的风险。
- 自动内存管理机制，将程序员从繁重的内存管理中释放出来，可以更专心的专注于业务开发。

担忧：

- 对于 Java 开发人员来讲，自动内存管理就是一个黑匣子。会严重弱化 Java 开发人员在程序出现内存溢出是定位问题和解决问题的能力。
- 此时了解 JVM 的自动内存分配和内存回收原理就显得非常重要，只有在真正了解 JVM 是如何自动管理内存，才能在遇见 OutOfMemoryError 的时候，快速的根据错误异常日志定位问题和解决问题。
- 当需要排查各种内存溢出、内存泄漏的问题的时候，当垃圾收集称为系统达到更高并发量的瓶颈的时候，我们就必须针对这些“自动化”的技术实施必要的监控和调节。

垃圾回收器可以对年轻代回收，也可以对老年代回收，甚至是全堆和方法区的回收
其中，Java 堆是垃圾回收的工作重点。从次数上来讲：

- 频繁收集 Young 区
- 较少收集 Old 区
- 基本不动 Perm 区（元空间）

### 8.2 垃圾回收算法

#### 8.2.1 垃圾标记阶段

垃圾标记阶段：在 GC 执行垃圾回收之前，首先需要区分出内存中哪些是存活对象，哪些是已经死亡的对象。只有被标记为死亡的对象，GC 才会执行垃圾回收，释放掉其所占用的内存空间。

当一个对象已经不再被任何存活对象引用的时候，就可以宣判为已经死亡。

判断对象存活一般有两种方式：引用计数法和可达性分析算法。

##### 8.2.1.1 引用计数算法

对每个对象保存一个整型的引用计数器属性，用于记录对象被引用的情况。对于一个对象 A，只要有一个对象引用类 A，则 A 的引用计数器+1，当引用失效的时候，引用计数器就减去 1，只要对象 A 的引用计数器为 0；即表示 A 不可能再被使用，可进行回收。

- 优点：实现简单，垃圾对象便于识别；判定效率高，回收没有延迟性。
- 缺点：
  - 它需要单独的字段存储计数器，增加了储存空间的开销。
  - 每次赋值都需要更新计数器，伴随着加法和减法操作，增加了时间开销。
  - 引用计数器有一个严重的问题，即无法处理循环引用的情况，这是一条致命的缺陷，存在内存泄漏的问题，导致在 Java 的垃圾回收器中没有使用到这类算法。

引用计数算法是很多语言的资源回收选择，如 Python 支持引用计数和垃圾收集机制。Python 解决循环引用问题的方式：

- 手动解除：在合适的时机，解除引用关系
- 使用弱引用 weakref：weakref 是 Python 的标准库，为了解决循环引用。

##### 8.2.1.2 可达性分析（或者根搜索算法、追踪性垃圾收集）

可达性分析算法是以根对象集合（GC Roots）为起始点，按照从上至下的方式搜索被根对象集合所连接的目标对象是否可达，只有能够被根对象集合直接或者间接连接的对象才是存活对象。如果目标对象没有任何引用链相连，则是不可达的，就意味着该对象已经死亡，可以标记为垃圾对象。

- "GC Roots"根集合就是一组必须活跃的引用。
- ![](jvm/image-1669529953984.png)

使用可达性分析算法后，内存中的存活对象都会被根对象集合直接或者间接连接着，搜索所走过的路径叫做引用链（Reference Chain）。

在 Java 语言中，GC Roots 包括以下几类元素

- 虚拟机栈中引用的对象（局部变量表中）
  - 比如：各个线程被调用的方法中使用到的参数、局部变量
- 本地方法栈内 JNI（本地方法）引用的对象
- 方法区中类的静态属性引用的对象
  - 比如：Java 类的引用类型静态变量
- 方法区中常量引用对象
  - 比如：字符串常量池（StringTable）里的引用
- 所有同步锁 synchronized 持有的对象
- Java 虚拟机内部的引用
  - 基本数据类型对应的 Class 对象，一些常驻得异常对象（NullPointerException、OutOfMemory）、系统类加载器。
- 反射 Java 虚拟机内部情况得 JMXBean、JVMTI 中注册得回调、本地代码缓存
- 除了这些固定的 GC Roots 集合以外，根据用户所选用的垃圾收集器以及当前回收内存区域不同，还可以有其他对象“临时性”的加入，共同构成完成 GC Roots 集合，比如：分代收集和局部回收（Partial GC）

GC Roots 记忆小技巧：由于 Root 采用栈方式存储变量和指针，所以如果一个指针保存了堆内存里面的对象，但是自己又不存放在堆内存里面，那它就是一个 Root。
注意：

- 如果要使用可达性分析算来判断内存是否可回收，那么分析工作必须在一个能保障一致性的快照中进行，这点不满足的话分析结果的准确性就无法保证。
- 这点也是导致 GC 进行时必须"Stop The World"的一个重要原因
  - 即使是号称（几乎）不会发生停顿的 CMS 收集器中，枚举根节点的时候也是必须要停顿的。

#### 8.2.2 清除阶段

##### 8.2.2.1 标记 - 清除算法（Mark-Sweep）

最先被 J.McCarthy 等人在 1960 年提出并应用于 Lisp 语言。

执行过程：当堆中的有效空间（available memory）被耗尽的时候，就会停止整个程序（也称为 Stop The World），然后进行两项工作：

- 标记：Collector 从引用根节点开始遍历，标记所有被引用的对象，一般是在对象的 Header 中记录为可达对象 （标记的是**非清除对象**）。
- 清除：Collector 对堆内存从头到尾进行线性的遍历，如果发现某个对象在其 Header 中没有标记为可达对象，则将其回收。

**缺点：**

- 效率不算高。
- 在进行 GC 的时候，需要停止整个应用程序，导致用户体验差。
- 这种方式清理出来的空闲内存是不连续的，产生内存碎片，需要维护一个空闲列表（空闲列表参考 6.1 第 2 点）。

**注意：** 这里所说的清除不是真的置空，而是把需要清除的对象地址保存在空闲的地址列表里，下次有新的对象需要加载的时候，判断垃圾位置的空间是否足够，够就存放（覆盖）。

##### 8.2.2.2 复制算法（copyting）

为了解决标记-清除算法在垃圾回收效率方面的缺陷，复制算法出现。其核心思想是将或者的内存空间分为两块，每次只使用其中一块，在垃圾回收的时候将正在使用的内存的活着的对象复制到未使用的内存块中，之后清除正在使用的内存块中的所有对象，交换两个内存的角色，最后完成垃圾回收。（幸存者 1 区和 2 区使用了这种算法）

**优点：**

- 没有标记和清除过程，实现简单，运行高效
- 复制过去之后保证空间的连续性，不会出现”碎片“问题

**缺点：**

- 需要 2 倍的空间
- 对于 G1 这种将内存拆分成大量 region 的 GC，复制意味着 GC 需要维护 region 之间对象的引用关系，不管是内存或者时间开销都不小。
- 如果系统中的垃圾对象很少，复制算法就不是很理想，它要复制的对象太多。

**应用场景**

- 在新生代，对常规的应用的垃圾回收，一次通常可以回收 70%~99%的内存空间，回收性价比很高，所以现在的商业虚拟机都是使用这种方式作为收集算法回收新生代。

##### 8.2.2.3 标记-压缩算法（mark-compact 算法、标记整理算法）

复制算法的高效性是建立在存活对象少、垃圾对象多的前提下的，这种情况在新生代经常发生，但是在老年代，更常见的是大部分对象都是存活对象。如果依然使用复制算法，因为存活的对象很多，复制的成本也很高。

标记清除算法可以应用在老年代中，但是执行效率低效，而且在执行完内存回收之后还会产生内存碎片，所以 JVM 的设计者在此基础上设计了标记 - 压缩算法。

执行过程：

- 第一阶段和标记清除算法一样，从根节点开始标记所有被引用的对象。
- 第二阶段将所有的存活对象压缩到内存的一端，按照顺序排放
- 之后，清理边界外所有的空间

标记-压缩算法的最终效果等同于标记 - 清除算法执行完成之后，再进行一次内存碎片整理，因此，也可以称之为标记-清除-压缩（Mark-Sweep-Compact）算法。二者的本质是标记清除算法是一种非移动式的回收算法，标记-压缩是移动式的。

优点：

- 消除了标记-清除算法的内存区域分散的缺点。给新对象分配内存的时候，JVM 只需要持有一个内存的起始地址即可。
- 消除了复制算法当中，内存减半的高额代价。

缺点：

- 从效率上说，标记-整理算法要低于复制算法。
- 移动对象的同时，如果对象被引用，还需要调整引用的地址。
- 移动过程种，需要全程暂停用户应用程序，即 STW。

##### 8.2.2.4 算法对比

![image.png](jvm/image-1669529959324.png)

##### 8.2.2.5 分代收集算法

没有一种算法可以完全取代其他算法，它们都有自己的优点和缺点，所以不同生命周期的对象可以采取不同的收集方式，以便提供回收效率。

核心思想：具体问题具体分析。

几乎所有的 GC 都是采用分代收集（Generational Collecting）算法执行垃圾回收的。

Hotspot 虚拟机：

- 年轻代：区域相对老年代较小，对象生命周期短，存活率低，回收频繁，使用复制算法最快。
- 老年代：区域较大，对象生命周期长，存活率高、回收不及年轻代频繁。这个时候一般使用标记-清除或者标记清除与标记整理一起出现。
  - 标记(Mark)阶段的开销于存活对象的数量成正比。
  - 清除(Sweep)阶段的开销与所管理区域的大小成正比。
  - 整理(Compact)阶段的开销与存活对象的数据成正比。

以 HotSpot 中的 CMS 回收器为例，CMS 是基于 Mark-Sweep 实现的，对于对象的回收效率很高。对于碎片问题，CMS 采用基于 Mark-Compact 算法的 Serial Old 回收器作为补偿措施：当内存回收不佳（碎片导致的 Concurrent Mode Failure 时），将采用 Serial Old 收集器执行 Full GC 对老年代进行内存的整理。

##### 8.2.2.6 增量收集算法

在上述的算法，在垃圾回收的过程中，都会出现 STW 的状态，影响程序的运行，所以增量收集（Incremental Collecting ）算法的诞生。

基本思想：

- 让垃圾收集线程和应用程序线程交替执行。每次垃圾回收线程只收集一小部分区域空间，接着切换到应用线程，依此反复，直到完成。
- 总的来说，基础仍然是传统的标记-清除算法和复制算法。增量收集算法通过对线程间冲突的妥善管理，允许垃圾收集线程以分阶段的方式完成标记、清理、或复制工作。

缺点：由于在垃圾回收过程中，间断性的还执行了应用程序代码，虽然能减少系统的停顿时间，但是线程上下文切换，会使得垃圾回收的总体成本上升，造成系统的吞吐量下降。

##### 8.2.2.7 分区算法

一般来说，在相同的条件下，堆空间越大，一次 GC 时所需要的时间就越长，GC 产生的停顿就越长。为了控制好停顿时间，将堆分为不同的区块，从而减少一次 GC 的影响。

分代算法按照对象的生命周期长短划分为 2 个部分，分区算法将整个堆划分为连续的不同小空间，每个小空间独立使用，独立回收，可以一次控制多个小区。
![](jvm/image-1669529961996.png)

### 8.3 对象的 finalization 机制

Java 语言提供了对象终止（finalization）机制来允许开发人员提供对象被销毁之前的自定义处理逻辑。

当垃圾回收器发现没有引用指向一个对象，即：垃圾回收此对象之前，总会先调用这个对象的 finalize() 方法。

finalize()方法允许在子类中被重写，用于在对象被回收时进行资源释放，通常在这个方法中进行一些资源释放和清理的工作，比如关闭文件、套接字和数据库连接等。

永远不要主动调用某个对象的 finalize() 方法，应该交给垃圾回收机制调用，理由包括下面三点：

- 在 finalize()时可能导致对象复活。
- finalize()方法的执行时间是没有保障的，它完全由 GC 线程决定，极端情况下，若不发生 GC，则 finalize()方法没有执行机会。
- 一个糟糕的 finalize()会严重影响 GC 性能

由于 finalize()方法的存在，虚拟机中的对象一般处于三种可能的状态：一般来说，如果从所有根节点都无法访问到某个对象，说明对象已经不再使用此对象了，需要被回收。但是事实上，也并非是 “非死不可”的，这时候它们暂时处于”缓刑“的状态，一个无法触及的对象由可能再某个条件下”复活“，此时对它的回收就是不合理的。

- 可触及的：从根节点开始，可以达到这个对象
- 可复活的：对象所有的引用都被释放，但是可能再 finalize()中复活，但是只有一次复活的机会。
- 不可触及的：对象的 finalize()被调用，并且没有复活，那么就会进入不可触及状态。不可触及的状态不可能被复活，因为 finalize()只调用一次。

具体过程：判断一个对象 objA 是否可被回收，至少要经历 2 次标记过程：

1. 如果对象 objA 到 GC Roots 没有引用连接，则进行第一次标记。
2. 进行筛选，判断此对象是否有必要执行 finalize()方法：
   - 如果没有重写 finalize()方法，或者 finalize()方法已经被虚拟机调用过，则虚拟机视为”没有必要执行“，objA 被判断是不可触及的。
   - 如果对象 objA 重写了 finalize()方法，且还未执行过，那么 objA 会被插入到 F-Queue 队列中，由虚拟机自动创建的、低优先级的 Finalizer 线程触发其 finalize()方法执行。
     - ![image.png](jvm/image-1669529965383.png)
   - finalize()方法是对象逃亡的最后机会，稍后 GC 会对 F-Queue 队列中的对象进行第二次标记。此时如果 finalize()方法与引用链的任何一个对象建立了联系，那么 objA 会被移出即将回收集合。后续 objA 再出现不可触及状态时，直接进行回收，因为 finalize()方法只能被调用一次。

```java
public class CanReliveObj {
    public static CanReliveObj obj;// 类变量，属于 GC Roots
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this;// 当前对象与引用链上任意一个对象建立了联系
    }
    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 对象第一次调用自己
            obj = null;
            System.gc();
            System.out.println("第一次GC");
            Thread.sleep(2000);//Finalizer线程优先级低，暂停2秒进行等待
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is alive");
            }
            System.out.println("第二次GC");
            obj = null;
            System.gc();
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is alive");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

![image.png](jvm/image-1669529968189.png)

### 8.4 GC Roots 分析

#### 8.4.1 MAT 工具

MAT 是 Memory Analyzer 的简称 它是一款功能强大的 Java 堆内存分析器，用于查找内存泄漏以及查看内存消耗情况。
MAT 是基于 Eclipse 开发的，是一款免费的性能分析工具。官网 ： [http://eclipse.org/mat/](https://gitee.com/link?target=http%3A%2F%2Feclipse.org%2Fmat%2F)
获取 dump 文件：

- 方式一：命令行使用 jmap
  - ![image.png](jvm/image-1669529970198.png)
- 方式二：使用 jvisualvm
  - ![image.png](jvm/image-1669529971856.png)
  - 右键快照即可保存。

生成 dump 文件后，使用 MAT 分析即可。

#### 8.4.2 Jprofiler 进行 GC Roots 溯源

需要配合 IDEA 插件使用。

### 8.5 垃圾回收相关概念

#### 8.5.1 System.gc()

默认情况下，通过 System.gc()或者 Runtime.getRuntime().gc()的调用，会显式触发 Full GC，同时对老年代和新生代进行回收，尝试释放被丢弃对象占用的内存。

然而 System.gc()调用附带也该免责声明，无法保证对垃圾收集器的调用。

JVM 实现者可以通过 System.gc() 调用来决定 JVM 的 GC 行为 ，而一般情况下，垃圾回收应该自自动进行的，无需手动触发，否则就太过于麻烦了。

#### 8.5.2 内存溢出与内存泄漏

内存溢出：

- javadoc 中对 OutOfMemoryError 的解释是，没有空闲内存，并且垃圾收集器也无法提供更多内存：
  - Java 虚拟机的堆内存不够
    - 比如：可能存在内存泄漏问题；也有可能是堆的大小不合理，比如我们要处理比较可观的数据量，但是没有显示指定 JVM 堆大小或者指定数值偏小。
  - 代码中创建了大量的大对象，并且长时间不能被垃圾收集器收集（存在引用）。
    - 在抛出 OutOfMemoryError 之前，通常垃圾收集器会被触发尽其所能去收集空间。但也不是任何情况下垃圾回收器都会被触发，比如分配一个超级大的对象，JVM 判断出垃圾收集不能解决这个问题，就直接抛出 OutOfMemoryError。

内存泄漏：也称作”存储渗漏“ ，严格来说：只有对象不会被程序用到了，但是 GC 又不能回收他们的情况，才叫内存泄漏。但是实际情况很多时候一些不太好的实践（或疏忽）会导致对象的生命周期变得很长，甚至 OOM，也可以叫做宽泛意义上的内存泄漏。尽管内存泄漏不会立刻引起程序崩溃，但是一旦发生，程序中的内存就会被逐步蚕食，直到发生 OutOfMemoryError。

举例：

- 单例模式：单例的生命周期和应用程序时一样长的，所以单例程序中，如果持有对象外部引用的话，那么这个外部对象时不能被回收的，则会导致内存泄漏的发生。
- 一些提供 close 的资源未关闭而导致内存泄漏：数据库连接、网络连接、和 IO 连接必须手动 close，否则不能回收。

#### 8.5.3 Stop The World

Stop The World 简称 STW，指的是 GC 事件发生的过程中，会产生应用程序的停顿。停顿产生时整个应用程序线程都会被暂停，没有任何响应，有点卡死的感觉，所以要减少 STW 的发生。

可达性分析的枚举根节点（GC Roots）会导致所有的 Java 执行线程停顿：

- 分析工作必须在一个确保一致性的快照中进行
- 一致性指的是整个分析过程系统像是冻结在某个点上
- 如果出现分析过程中对象引用关系还在不断地变化，则分析地结果无法保证

STW 事件和采用哪款 GC 无关，所有地 GC 都有这个事件。STW 是 JVM 后台自动发起和完成的，在用户不可见的情况下，把用户正常的工作线程全部停掉。System.gc()会导致 Stop-the-world 的发生，所以要避免使用。

#### 8.5.4 垃圾回收的并行与并发

并发（concurrent）：

- 操作系统中，一个时间段有几个程序都处于启动到运行完毕之间，且这几个程序都是在同一个处理器上运行。
- 并发不是真正的 ”同时进行“ 只是 CPU 的时间片轮转，让用户觉得很快，没有切换的感觉。

![image.png](jvm/image-1669529976115.png)
并行（parallel）：

- 当系统有一个以上的 CPU 的时候，一个 CPU 执行一个进程，另一个 CPU 可以执行另外一个进程。两个进程互不抢占 CPU 资源。可以同时进行，故称之为并行（Parallel）
- 其实决定并行的不是 CPU 的数量，而是 CPU 的核心数量，一个 CPU 多核心也可以并行。

![image.png](jvm/image-1669529977903.png)
二者对比

- 并发：指的是多个事情，在同一个时间段内同时发生了。
- 并行：指的是多个事情，在同一个时间点上同时发生了。
- 并发的多个任务是相互抢占资源的。
- 并行的读个任务是不相互抢占资源的。
- 只有多个 CPU 或者一个 CPU 多核心的情况中，才会发生并行。否则，看似同时发生的事情，其实都是并发执行的。

垃圾回收的并发与并行

- 并行（Parallel）：指多条垃圾回收线程并行工作。
- 串行（Serial）：单线程执行
  - 如果内存不够，程序暂停，启动 JVM 垃圾回收器进行垃圾回收。回收完在启动程序的线程。

![image.png](jvm/image-1669529980358.png)

- 并发（Concurrent）：指用户线程与垃圾收集器线程同时执行（但一定不是并行，因为仍然存在 STW）。
  - 用户程序继续执行，垃圾在另外一个核心上执行。

![image.png](jvm/image-1669529981973.png)

#### 8.5.5 安全点和安全区域

程序执行时并非在所有地方都能停下来开始 GC，只有在特定位置才能停下来开始 GC。这些位置称为 “安全点（Safe Point）”

Safe Point 的选择，通常会根据是否具有让程序长时间执行的特性为标准。比如：选择一些执行时间较长的指令作为 Safe Point ，如方法调用、循环跳转、异常跳转等。

如何在 GC 发生时，检查所有的线程都跑到最近的安全点停顿下来呢？

- 抢断式中断（目前没有虚拟机采用）
  - 首先中断所有线程，如有线程不在安全的，就恢复线程，让线程跑到安全点。
- 主动式中断
  - 设置一个中断标志，各个线程运行到 Safe Point 的时候主动轮询这个标志，为 true，就将自己进行中断挂起。

安全区域（safe region）：指一段代码片段中对象的引用关系不会发生变化，这个区域的任何位置开始 GC 都是安全的。如 sleep 或 blocked 状态的线程。安全区域可以看作安全点的扩展。
执行过程：

- 当线程运行到 safe region 的代码时，首先标识已经进入了 safe region，如果这段时间内发生了 GC，JVM 会忽略标识为 safe region 状态的线程。
- 当线程即将离开 safe region 时，会检查 JVM 是否已经完成 GC，如果完成了，则继续运行，否则线程必须等待直到收到可以安全离开 safe region 的信号为止。

#### 8.5.6 各类引用

jdk1.2 对 Java 引用的概念进行了扩充，分为强引用（strong reference）、软引用（soft reference）、弱引用（weak reference）和虚引用（phantom reference）4 种，这 4 种引用强度一次减弱。

除了强引用，其余 3 种可以在 java.lang.ref 包中找到定义，可以在开发中直接使用。
![image.png](jvm/image-1669529985605.png)

除了 FinalReference 是使用默认修饰（包内可见），其他都是 public 修饰的。

##### 8.5.6.1 强引用

程序代码之中普遍存在的引用赋值（默认的引用类型），如 Object o = new Object()，只要强引用存在，GC 就永远不会回收被引用的对象，就算是内存不足的时候，JVM 直接抛出 OutOfMemoryError 也不会去回收。

- 如果想中断强引用与对象之间的联系，可以显式的将强引用赋值为 null，这个时候就等待 GC 回收对对象即可。

特点：

- 强引用可以直接访问目标对象
- 强引用所指向的对象在任何时候都不会被系统回收，即使 OOM。
- 强引用可能导致内存泄漏（Java 内存泄漏的主要原因之一）。

##### 8.5.6.2 软引用

软引是用来描述一些非必需但是仍有用的对象。在内存足够的时候，软引用对象不会被回收，只有在内存不足的时候，系统会回收软引用对象，如果回收了软引用对象之后仍然没有足够的内存，才会抛出内存溢出异常，这种特性非常适合用来做缓存。【不足即回收】

##### 8.5.6.3 弱引用

被弱引用关联的对象只能生存到下一次垃圾收集器之前，当垃圾收集器工作室，无论内存空间是否足够，都会回收掉被弱引用关联的对象。【发现及回收】

WeakHashMap 类及其实现 WeakHashMap 类在 java.util 包内，它实现了 Map 接口，是 HashMap 的一种实现，它使用弱引用作为内部数据的存储方案。

```java
public void test() {
    Map map;
    // 弱引用 HashMap
    map = new WeakHashMap();
    for (int i = 0; i < 10000; i++) {
        map.put("key" + i, new byte[i]);
    }
    // 强引用HashMap
    map = new HashMap();
    for (int i = 0; i < 10000; i++) {
        map.put("key" + i, new byte[i]);
    }
}
```

WeakHashMap 会在系统内存紧张时使用弱引用，自动释放掉持有弱引用的内存数据。但如果 WeakHashMap 的 key 都在系统内持有强引用，那么 WeakHashMap 就退化为普通的 HashMap，因为所有的表项都无法被自动清理。

##### 8.5.6.4 虚引用

又称为幻影引用或幽灵引用。

一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获得一个对象的实例。为一个对象设置虚引用关联的唯一目的时对象被收集器回收的时候收到一个系统通知。【对象跟踪】

创建虚引用需要在构造器内传入一个引用队列。

引用队列可以与软引用、弱引用以及虚引用一起配合使用，当垃圾回收器准备回收一个对象时，如果发现它还有引用，那么就会在回收对象之前，把这个引用加入到与之关联的引用队列中去。

程序可以通过判断引用队列中是否已经加入了引用，来判断被引用的对象是否将要被垃圾回收，这样就可以在对象被回收之前采取一些必要的措施。
![image.png](jvm/image-1669529989793.png)

##### 8.5.6.5 终结器引用

终结器引用用于实现对象 finalize()方法，无需手动编码，其内部配合引用队列使用。
在 GC 时，终结器引用入队，由 finalizer 线程通过终结器引用找到被引用的对象并调用它的 finalize()方法，第二次 GC 时才能回收被引用的对象。

### 8.6 垃圾回收器

#### 8.6.1 GC 分类与性能指标

垃圾回收器没有在规范中进行过多的规定，可以由不同厂商、不同版本的 JVM 来实现。

按线程数：可分为串行垃圾回收器和并行垃圾回收器。

- 串行回收：在同一时间段内只允许有一个 CPU 用于执行垃圾回收操作，此时工作线程被暂停（STW），直至垃圾收集工作结束。
  - 在单 CPU 处理器或者较小的应用内存等硬件平台不是特别优越的场合，串行回收器的性能表现可以超过并行回收器和并发回收器。所以串行回收默认被应用在客户端的 Client 模式下的 JVM 中
- 并行回收：可以运用多个 CPU 共同执行垃圾回收，因此提升了应用的吞吐量，不过并行回收仍然与串行回收一样，采用独占式，使用 “Stop the World”机制。
  - 在并发能力较强的 CPU 上，并行回收器产生的停顿时间要短于串行回收器。

按工作模式：

- 并发式垃圾回收器与应用程序线程交替工作，以尽可能较少程序的停顿时间。
- 独占式垃圾回收器（Stop The World）一旦运行，就停止程序中所有的用户线程，直到垃圾回收过程完全结束。

按照碎片处理方式：

- 压缩式垃圾回收器会在回收完成之后，对存活的对象进行压缩整理，消除回收后的碎片。
- 非压缩式的垃圾回收器不进行这个操作。

按照工作的内部区间划分，又可以分为年轻代垃圾回收器和老年代垃圾回收器。

性能指标：

- 吞吐量：运行用户代码的时间占总运行时间的比例。
  - （总运行时间：程序的运行时间+内存回收的时间）。
- 垃圾收集器开销：垃圾收集所有时间与总运行时间的比例。
- 暂停时间：执行垃圾回收的时候，程序的工作线程被暂停的时间。
- 收集频率：相对于应用程序的执行，收集操作发生的频率。
- 内存占用：Java 堆区所占用的内存大小。
- 快速：一个对象从诞生到回收所经历的时间。

吞吐量、暂停时间、内存占用 共同构成了一个“不可能三角”，由于三者不可同时满足，所以一般只追求吞吐量和暂停时间。

吞吐量：

- 吞吐量就是 CPU 用于运行用户代码的时间与 CPU 总消耗时间的比值，即吞吐量 = 运行用户代码时间 / （用户代码时间 + 垃圾收集时间）
  - 虚拟机运行 100 分钟，垃圾收集 1 分钟，那么吞吐量就是 99%
- 这种情况下，应用程序能容忍比较高的暂停时间，因此，高吞吐量的应用程序有更长的时间基准，快速响应式不必考虑的
- 吞吐量优先，意味着在单位时间内，STW 的时间最短。

暂停时间：

- 暂停时间是指一个时间段内应用程序线程暂停，让 GC 执行的状态。
  - GC 期间 100 毫秒的暂停时间，说明这 100 毫秒期间没有任何应用程序是活动的。
- 暂停时间优先，意味着让单次 STW 的时间最短。

吞吐量和暂停时间的对比：

- 高吞吐量较好是因为让程序的最终用户感觉只有用户程序在做“生产性”工作，直觉上，吞吐量越高程序运行越快。
- 对于一个交互式应用程序具有低的较大的暂停时间非常重要。
- 然而高吞吐量和低暂停时间是一对相互竞争（矛盾）的目标。吞吐量优先需要降低内存回收的执行频率，导致需要更长的暂停时间。低暂停时间意味着 GC 只能频繁执行回收内存，导致吞吐量下降。

在设计或者使用 GC 算法的时候，必须明确目标：一个 GC 算法只能针对两个目标之一，或者尝试找到一个二者的折衷。

现在标准：在最大吞吐量优先的情况下，降低停顿时间。

```java
//终端的命令及显示内容的含义
jstat -gc 2764 250 20   //2764表示进程id ，250表示250毫秒打印一次 ，20表示一共打印20次
S0C：第一个幸存区的大小
S1C：第二个幸存区的大小
S0U：第一个幸存区的使用大小
S1U：第二个幸存区的使用大小
EC：伊甸园区的大小
EU：伊甸园区的使用大小
OC：老年代大小
OU：老年代使用大小
MC：方法区大小
MU：方法区使用大小
CCSC:压缩类空间大小
CCSU:压缩类空间使用大小
YGC：年轻代垃圾回收次数
YGCT：年轻代垃圾回收消耗时间
FGC：老年代垃圾回收次数
FGCT：老年代垃圾回收消耗时间
GCT：垃圾回收消耗总时间
```

#### 8.6.2 不同垃圾回收器发展

历史：

- 1999 年随 JDK1.3.1 一起来的是串行方式的 Serial Gc ，它是第一款 GC。ParNew 垃圾收集器是 serial 收集器的多线程版本（并行）
- 2002 年 2 月 26 日，Parallel Gc 和 concurrent Mark SweepGC（CMS）跟随 JDK1.4.2—起发布
  - Parallel Gc 在 JDK6 之后成为 HotSpot 默认 GC。
- 2012 年，在 JDK1.7u4 版本中，G1 可用。
- 2017 年，JDK9 中 G1 变成默认的垃圾收集器，以替代 CMS。
- 2018 年 3 月，JDK10 中 G1 垃圾回收器的并行完整垃圾回收，实现并行性来改善最坏情况下的延迟。
- 2018 年 9 月，JDK11 发布。引入 Epsilon 垃圾回收器，又被称为"No-Op(无操作)" S 回收器。同时，引入 ZGC:可伸缩的低延迟垃圾回收器(Experimental)。
- 2019 年 3 月，JDK12 发布，增强 G1，自动返回未用堆内存给操作系统，同时引入 Shenandoah GC：低停顿时间的 GC。
- 2019 年 9 月，JDK13 发布。增强 ZGC，自动返回未用堆内存给操作系统。
- 2020 年 3 月，JDK14 发布。删除 CMS 垃圾回收器。扩展 ZGC 在 macoS 和 windows 上的应用。

7 款经典垃圾收集器（在 JDK11 的 ZGC 之前）

- 串行回收器：Serial、Serial Old
- 并行收集器：ParNew、Parallel Scavenge、Parallel Old
- 并发回收器：CMS、G1

7 款经典垃圾回收器与垃圾分代之间的关系：
![](jvm/image-1669529996404.png)
- 新生代收集器：Serial、ParNew、Parallel Scavenge
- 老年代收集器：Serial Old、Parallel Old、CMS
- 整堆收集器：G1

组合关系（两个收集器间有连线，表明它们可以搭配使用）：
![](jvm/image-1669529998137.png)
Serial/Serial old、Seria1/CMS、ParNew/Serial old、ParNew/CMS、Parallel Scavenge/Serial old、Parallel Scavenge/Parallel old、G1、ZGC

- 其中 serial old 作为 CMS 出现"concurrent Mode Failure"失败的后备预案。
- (红色虚线)由于维护和兼容性测试的成本，在 JDK 8 时将 Serial+CMS、ParNew+Serial old 这两个组合声明为废弃(JEP 173)，并在 JDK 9 中完全取消。
- (绿色虚线)JDK14 弃用 Parallel Scavenge 和 Serial0ld Gc 组合(JEP336)。
- (虚线边框)JDK14 删除 CMS 垃圾回收器(JEP 363)。

#### 8.6.3 查看默认垃圾回收器

-XX:+PrintCommandLineFlags 查看命令行参数，包括使用的垃圾回收器，JDK8 没有使用 G1，也没有使用 CMS，JDK9 使用了 G1。
执行命令行指令：`jinfo -flag 相关垃圾回收器参数 进程id`。
![image.png](jvm/image-1669530001453.png)

#### 8.6.4 串行回收（Serial 与 Serial Old）

Serial 收集器是最基本、历史最悠久的垃圾收集器。JDK1.3 之前回收新生代唯一的选择。

Serial 收集器作为 Hotspot 中 Client 模式下的默认新生代垃圾收集器。

- Serial 收集器采用复制算法、串行回收和”Stop-the-world“ 机制的方式执行内存回收。

Serial Old 收集器是运行在 Client 模式下默认的老年代垃圾收集器。

- Serial Old 收集器同样采用了串行回收和”Stop the World“机制，只不过内存回收算法使用的是标记-压缩算法。

Serial Old 在 Server 模式下主要有两个用途：

- 与新生代的 Parallel Scavenge 配合使用。
- 作为老年代 CMS 收集器的后备垃圾收集方案。

![](jvm/image-1669530004255.png)
串行回收进行垃圾收集的时候，必须暂停其他所有的工作线程，直到它收集结束(Stop-The-World)。

优势：简单而高效(和其他收集器的单线程比)。对于限定的单个 CPU 的环境来说，Serial 收集器由于没有线程相互的开销，专心做垃圾收集，自然可以获得最高的单线程的收集效率。

- 运行在 Client 模式下的虚拟机是个不错的选择。
- 在用户的桌面应用场景中，可用内存一般不大(几十 MB 至一两百 MB)，可以在较短时间内完成垃圾收集(几十 ms 至一百多 ms)，只要不频繁发生,。使用串行回收器是可以接受的。

在 HotSpot 虚拟机中，使用`-XX:+UseSerialGC`等价于新生代用 Serial GC，且老年代用 Serial Old GC。
总结：现在已经不用串行的了。而且在限定单核 CPU 才可以使用，现在都不是单核的了。对于交互性较强的应用而言，串行回收的 STW 是不可接受的。

#### 8.6.5 并行回收（ParNew）

Par 是 Parallel 的缩写，New：只能处理的是新生代。

ParNew 收集器除了采用并行回收的方式执行内存回收外，与 Serial 几乎没有任何区别。ParNew 收集器在年轻代中同样也是采用复制算法、"stop-the-World"机制。

- ParNew 是很多 JVM 运行在 server 模式下新生代的默认垃圾收集器。

![](jvm/image-1669530007668.png)
对于新生代，回收次数频繁，使用并行方式高效；对于老年代，回收次数少，使用串行方式节省资源（CPU 并行需要切换线程，串行可以省去切换线程的资源）。

ParNew 收集器在多 CPU 环境下，可以利用多核心的资源，可以更快完成垃圾收集。但是在单个 CPU 的环境下，ParNew 收集器不比 Serial 收集器跟高效。

除了 Serial 外，目前只有 ParNew GC 能与 CMS 收集器配合工作。

参数配置：

- -XX:UseParNewGC：手动设置是否开启。
- -XX:ParallelGCThreads：限制线程数量，默认开启个 CPU 数据相同的线程数。

#### 8.6.6 吞吐量优先回收（Parallel Scavenge 与 Parallel Old）

HotSpot 的新生代中除了 ParNew 收集器是基于并行回收的以外，Parallel scavenge 收集器同样也采用了复制算法、并行回收和"stop the World"机制。

和 ParNew 收集器不同，Parallel Scavenge 收集器的目标则是达到一个可控制的吞吐量（Throughput），它也被称为吞吐量优先的垃圾收集器。自适应调节策略也是 Parallel scavenge 与 ParNew 一个重要区别。

高吞吐量则可以高效率地利用 CPU 时间，尽快完成程序的运算任务，主要适合在后台运算而不需要太多交互的任务。因此，常在服务器环境中使用，例如：批处理、订单处理、工资支付、科学计算的应用程序。

Parallel old 收集器，jdk1.6 时用来代替老年代的 serial old 收集器。Parallel old 收集器采用了标记-压缩算法，但同样也是基于并行回收和”Stop - the World“机制。
![](jvm/image-1669530010739.png)

在程序吞吐量优先的应用场景中，Parallel 收集器和 Parallel Old 的结合在 Server 模式下的内存回收性能很不错，Java8 中默认是此收集器。

- `-XX:+UseParallelGC`手动设置年轻代使用 Parallel 并行收集器执行回收任务
- `-XX:+UseParallelOldGC`：手动指定老年代使用并行回收收集器
  - 上面的两个参数，开启一个，另外一个也会激活。且在 JDK8 默认开启。
- `-XX:ParallelGCThreads`：设置年轻代并行收集器的线程数。
  - 默认情况下，当 CPU 的数量小于 8 个，ParallelGCThreads 的值等于 CPU 数量
  - 当 CPU 数量大于 8 个，ParallelGCThreads 的值等于`3+[](5*CPU_Count]/8]`。
- `-XX:MaxGCPauseMillis`：设置垃圾收集器最大停顿时间（也就是 STW 的时间）单位是毫秒
  - 为了尽可能把停顿的时间控制在 MaxGCPauseMillis 内，收集器工作的时候会设置堆的大小或者其他的参数。
  - 使用此参数需要谨慎。
- `-XX:GCTimeRatio`：垃圾回收时间占总时间的比例（`= 1/（N+1）`） 用于衡量吞吐量的大小
  - 取值范围（1~100 ） 默认 99。
- `-XX:+UseAdaptiveSizePolicy`：设置具有自适应调节（默认开启）。
  - 该模式会自动调整年轻代的大小，包括 Eden 和 Survivor 的比例，晋升老年代对象的年龄。

#### 8.6.7 低延迟回收（CMS）

在 JDK1.5 时期，Hotspot 推出了一款在强交互应用中几乎可认为时代意义的垃圾收集器：CMS (Concurrent-Mark-Sweep)收集器，这款收集器是 HotSpot 虚拟机中第一款真正意义上的并发收集器，它第一次实现了让垃圾收集线程与用户线程同时工作。

CMS 收集器的关注点是尽可能缩短垃圾收集时用户线程的停顿时间。停顿时间越短（低延迟）就越适合与用户交互的程序，良好的响应速度能提升用户体验。

- 目前很大一部分的 Java 应用集中在互联网站或者 B/s 系统的服务端上，这类应用尤其重视服务的响应速度，希望系统停顿时间最短，以给用户带来较好的体验。

CMS 的垃圾收集算法采用标记-清除算法，并且也会"Stop-the-world"。

CMS 作为老年代的收集器，却无法与 JDK1.4 中已经存在新生代收集器 Parallel Scavenge 配合工作，所以采用 CMS 来收集老年代时，新生代只能在 ParNew 或者 Serial 收集器中选择一个。

在 G1 出现之前，CMS 使用还是非常广泛的，一直到今天仍然有很多系统使用 CMS GC。
![](jvm/image-1669530014798.png)

CMS 的执行过程比之前的收集器都要复杂，整个过程分为 4 个主要阶段，即初始标记阶段、并发标记阶段、重新标记阶段和并发清除阶段。

- 初始标记（Initial-Mark）阶段：在这个阶段，程序中所有的工作线程都会因为 STW 机制而出现短暂的暂停，这个阶段的任务仅仅是为了标记出 GC Roots 能直接关联的对象 一旦标记完成之后就会恢复之前被暂停的所有应用线程，由于直接关联对象比较小，所以这里速度非常快。
- 并发标记（Concurrent-Mark）阶段：从 GC Roots 的直接关联对象开始遍历整个对象图的过程，这个过程耗时长但是不需要停顿用户线程，可以与工作线程一起并发执行。
- 重新标记（Remark）阶段：由于在并发标记阶段中，程序的工作线程会和垃圾收集线程同时运行或者交叉运行，因此为了修正并发标记期间因用户程序继续运作而导致标记产生变动的那一部分对象标记记录，这个阶段的停顿稍长，但也远比并发标记阶段短。存在 STW 的情况。
- 并发清除（Concurrent-Sweep）阶段：清理删除掉标记的已经判断为死亡的对象，释放内存空间 因为不需要移动存活对象，所以也是并发执行。

尽管 CMS 收集器采用的并发回收（非独占式），在初始标记与重新标记这两个阶段需要执行 Stop the world 机制来暂停工作线程，但时间很短，而且最耗费时间的并发标记与并发清除阶段都不需要暂停工作，所以整体的回收是低延迟的。

由于在垃圾收集阶段用户线程没有中断，所以在 CMS 回收过程中，还应该确保应用程序用户线程有足够的内存可用。因此，CMS 收集器不能像其他收集器那样等到老年代几乎完全被填满了再进行收集，而是当堆内存使用率达到某一阈值时，便开始进行回收，以确保应用程序在 CMS 工作过程中依然有足够的空间支持应用程序运行。要是 CMS 运行期间预留的内存无法满足程序需要，就会出现一次“Concurrent Mode Failure”失败，这时虚拟机将启动后备预案：临时启用 Serial old 收集器来重新进行老年代的垃圾收集，这样停顿时间就很长了。

CMS 使用标记清除算法，不可避免会有内存碎片。CMS 只能选择空闲列表进行内存分配。无法使用指针碰撞，只能使用空闲列表执行内存分配。

有人觉得 Mark-Sweep 会造成内存碎片，那么为什么不把算法换成 Mark-Compact？因为在并发清除的时候，用户线程还在执行，那要是把地址修改，明显不合适。所以标记压缩更适合 STW 的场景使用。

CMS 优点：

- 并发收集
- 低延迟

CMS 的弊端

- 会产生内存碎片。大对象无法分配时，导致提前触发 FullGC。
- CMS 收集器对 CPU 资源非常敏感。与工作线程并行，占用 CPU 资源，总吞吐量会降低。
- CMS 收集器无法处理浮动垃圾。并发标记阶段如果产生新的垃圾对象，CMS 将无法对这些垃圾进行标记，导致回收不及时，只能在下一次执行 GC 时释放。

CMS 设置的参数

- `-XX:+UseConcMarkSweepGC`：手动指定使用 CMS 收集器执行内存任务（作用于老年代）
  - 开启该参数之后，会自动将`-XX:+UseParNewGC`打开。即：ParNew(Young 区)+CMS(Old 区)+Serial Old 的组合
    - Serial Old 预备收集，防止 CMS 与用户线程同时进行时内存不足。
- `-XX:CMSInitiatingOccupanyFraction`：设置内存使用率的阈值，达到阈值就开始回收。
  - JDK5 之前默认为 68%，6 之后为 92%。
  - 如果内存增长缓慢，可以设置一个稍大的值，可以有效降低 CMS 的触发频率。反之，如果应用程序内存增长很快，应该降低这个阈值，避免频繁触发 CMS，甚至触发 Serial Old。
- `-XX:+UseCMSCompactAtFullCollection`：用于指定在执行完 Full GC 后对内存空间进行压缩整理，以此避免内存碎片的产生。不过由于内存压缩整理过程无法并发执行，所带来的问题就是停顿时间变得更长了。
- `-XX:CMSFullGCsBeforeCompaction`：设置在执行多少次 Full GC 后对内存空间进行压缩整理。
- `-XX:ParallelCMSThreads`：设置 CMS 的线程数量。CMS 默认启动的线程数是（ParallelGcThreads+3)/4，ParallelGcThreads 是年轻代并行收集器的线程数。

#### 8.6.8 区域化分代式（Gabage First）

##### 8.6.8.1 概述

出现背景：
为了适应不断扩大的内存和不断增加的处理器数量，进一步降低暂停时间（pause time），同时兼顾良好的吞吐量。官方给 G1 设定的目标是在延迟可控的情况下获得尽可能高的吞吐量，担当全能收集器的重任与期望。

命名由来：
G1 是一个并行回收器，它把内存分割为很多不相关的区域（Region）（物理上不连续的）， 使用不同的 Region 来表示 Eden、幸存者 0 区、幸存者 1 区、老年代。G1 GC 有计划的避免对 Java 堆进行全区域的垃圾收集，而是跟踪各个 Region 里面垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。由于这种方式的侧重点在于回收垃圾最大量的区间（Region)，所以命名为：垃圾优先（Garbage First） 。

Gl (Garbage-First）是一款面向服务端应用的垃圾收集器，主要针对配备多核 CPU 及大容量内存的机器，以极高概率满足 GC 停顿时间的同时，还兼具高吞吐量的性能特征。

发展历史：
在 JDK1.7 版本正式启用，移除了 Experimental（实验）的标识，是 JDK 9 以后的默认垃圾回收器，取代了 CMS 回收器以及 Parallel + Parallel old 组合。被 oracle 官方称为全功能的垃圾收集器。

- 在 jdk8 中还不是默认的垃圾回收器，需要使用`-XX:+UseG1Gc`来启用。
- CMS 已经在 JDK 9 中被标记为废弃（deprecated）。

##### 8.6.8.2 G1 的优势（特点）

并行与并发

- 并行性：G1 在回收期间，可以有多个 GC 线程同时工作，有效利用多核计算能力。此时用户线程 STW。
- 并发性：G1 拥有与应用程序交替执行的能力，部分工作可以和应用程序同时执行，因此，一般来说，不会在整个回收阶段发生完全阻塞应用程序的情况。

分代收集：

- 从分代上看，G1 依然属于分代型垃圾回收器，它会区分年轻代和老年代，年轻代依然有 Eden 区和 survivor 区。但从堆的结构上看，它不要求整个 Eden 区、年轻代或者老年代都是连续的，也不再坚持固定大小和固定数量。
- 将堆空间分为若干个区域（Region)，这些区域中包含了逻辑上的年轻代和老年代。
- 和之前的各类回收器不同，它同时兼顾年轻代和老年代。

空间整合：

- CMS：”标记-清除”算法、内存碎片、若干次 GC 后进行一次碎片整理。
- G1 将内存划分为一个个的 region，内存的回收是以 region 作为基本单位的。Region 之间是复制算法，但整体上实际可看作是标记-压缩（Mark-Compact)算法，两种算法都可以避免内存碎片。这种特性有利于程序长时间运行，分配大对象时不会因为无法找到连续内存空间而提前触发下一次 GC。尤其是当 Java 堆非常大的时候，G1 的优势更加明显。

可预测的停顿时间模型 （软实时：Soft Real-Time）：

- 这是 G1 相对于 CMS 的另一大优势，G1 除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为 M 毫秒的时间片段内，消耗在垃圾收集上的时间不得超过 N 毫秒。
- 由于分区的原因，G1 可以只选取部分区域进行内存回收，这样缩小了回收的范围，因此对于全局停顿情况的发生也能得到较好的控制。
- G1 跟踪各个 Region 里面的垃圾堆积的价值大小（回收所获得的空间大小以及回收所需时间的经验值），在后台维护一个优先列表，每次根据允许的收集时间，优先回收价值最大的 Region。保证了 G1 收集器在有限的时间内可以获取尽可能高的收集效率。
- 相比于 CMS GC，G1 未必能做到 CMS 在最好情况下的延时停顿，但是最差情况要好很多。

G1 回收器的缺点：

- 相较于 CMS，G1 还不具备全方位、压倒性优势。比如在用户程序运行过程中，G1 无论是为了垃圾收集产生的内存占用（Footprint）还是程序运行时的额外执行负载（overload）都要比 CMS 要高。
- 从经验上来说，在小内存应用上 CMS 的表现大概率会优于 G1，而 G1 在大内存应用上则发挥其优势。平衡点在 6-8GB 之间。

##### 8.6.8.3 G1 回收器参数设置

- -XX:+UseG1GC：手动指定使用 G1 收集器执行内存回收任务。JDK9 不用设置为默认。
- -XX:G1HeapRegionsize：设置每个 Region 的大小。值是 2 的幂，范围是 1MB 到 32MB 之间，目标是根据最小的 Java 堆大小划分出约 2048 个区域。默认是堆内存的 1/2000。
- -XX:MaxGCPauseMillis：设置期望达到的最大 GC 停顿时间指标(JVM 会尽力实现，但不保证达到)。默认值是 200ms。
- -XX:ParallelGCThread：设置 STW 时 GC 的工作线程数的值。最多设置为 8
- -XX:ConcGCThreads：设置并发标记的线程数。将 n 设置为并行垃圾回收线程数(ParallelGcThreads)的 1/4 左右。
- -XX:InitiatingHeapoccupancyPercent：设置触发并发 GC 周期的 Java 堆占用率阈值。超过此值，就触发 Gc。默认值是 45。

##### 8.6.8.4 G1 的适用场景

- 面向服务端应用，针对具有大内存、多处理器的机器。(在普通大小的堆里表现并不惊喜)
- 最主要的应用是需要低 Gc 延迟，并具有大堆的应用程序提供解决方案。
  - 如在堆大小约 6GB 或更大时，可预测的暂停时间可以低于 0.5 秒；(G1 通过每次只清理一部分而不是全部的 Region 的增量式清理来保证每次 GC 停顿时间不会过长）。
- 用来替换掉 JDK1.5 中的 CMS 收集器;在下面的情况时，使用 G1 可能比 CMS 好:
  - 超过 50%的 Java 堆被活动数据占用。
  - 对象分配频率或年代提升频率变化很大。
  - GC 停顿时间过长（长于 0.5 至 1 秒）。
- HotSpot 垃圾收集器里，除了 G1 以外，其他的垃圾收集器使用内置的 JVM 线程执行 GC 的多线程操作，而 G1 GC 可以采用应用线程承担后台运行的 GC 工作，即当 JVM 的 Gc 线程处理速度慢时，系统会调用应用程序线程帮助加速垃圾回收过程。

##### 8.6.8.5 分区 Region：化整为零

使用 G1 收集器时，它将整个 Java 堆划分成约 2048 个大小相同的独立 Region 块，可以通过-Xx:GlHeapRegionsize 设定。**所有的 Region 大小相同，且在 JVM 生命周期内不会被改变。**
虽然还保留有新生代和老年代的概念，但新生代和老年代不再是物理隔离的它们都是一部分 Region（不需要连续）的集合。通过 Region 的动态分配方式实现逻辑上的连续。
![image.png](jvm/image-1669530022991.png)

- 一个 region 有可能属于 Eden，Survivor 或者 old/Tenured 内存区域。但是一个 region 只可能属于一个角色，可以变换（如 old 变为 eden）。图中的 E 表示该 region 属于 Eden 内存区域，s 表示属于 survivor 内存区域，o 表示属于 old 内存区域。图中空白的表示未使用的内存空间
- Gl 垃圾收集器还增加了一种新的内存区域，叫做 Humongous（大对象）内存区域，如图中的 H 块。主要用于存储大对象，如果超过 1.5 个 region，就放到 H。
- **设置 H 的原因**
  - 对于堆中的大对象，默认直接会被分配到老年代，但是如果它是一个短期存在的大对象就会对垃圾收集器造成负面影响。为了解决这个问题，G1 划分了一个 Humongous 区，它用来专门存放大对象。如果一个 H 区装不下一个大对象，那么 G1 会寻找连续的 H 区来存储。为了能找到连续的 H 区，有时候不得不启动 Full GC。G1 的大多数行为都把 H 区作为老年代的一部分来看待。

对象分配过程：指针碰撞及线程本地分配缓存（TLAB）
![image.png](jvm/image-1669530025172.png)

##### 8.6.8.6 G1 回收垃圾的过程

- 主要包括以下三个环节：
  - 年轻代 GC （Young GC）
  - 老年代并发标记过程（Concurrent Marking）
  - 混合回收（Mixed GC）
  - （如果需要。单线程、独占式、高强度的 Full GC 还是继续存在的，它针对 GC 的评估失败提供了一种失败保护机制，即强力回收）

![image.png](jvm/image-1669530027887.png)

- 顺时针：Young GC -> Young GC + Concurrent mark -> Mixed GC 顺序进行垃圾回收。
- 应用程序分配内存，当年轻代的 Eden 区用尽时开始年轻代回收过程；G1 的年轻代收集阶段是一个并行的独占式收集器。在年轻代回收期，G1 GC 暂停所有应用程序线程，启动多线程执行年轻代回收。然后从年轻代区间移动存活对象到 survivor 区间或者老年区间，也有可能是两个区间都会涉及。
- 当堆内存使用达到一定值（默认 45%）时，开始老年代并发标记过程。
- 标记完成马上开始混合回收过程。对于一个混合回收期，G1 GC 从老年区间移动存活对象到空闲区间，这些空闲区间也就成为了老年代的一部分。和年轻代不同，老年代的 G1 回收器和其他 GC 不同，G1 的老年代回收器不需要整个老年代被回收，一次只需要扫描/回收小部分老年代的 Region 就可以了。同时，这个老年代 Region 是和年轻代一起被回收的。
  - 举个例子：一个 web 服务器，Java 进程最大堆内存为 4G，每分钟响应 1500 个请求，每 45 秒钟会新分配大约 2G 的内存。G1 会每 45 秒钟进行一次年轻代回收，每 31 个小时整个堆的使用率会达到 45%，会开始老年代并发标记过程，标记完成后开始四到五次的混合回收。

G1 回收过程一：年轻代 GC

- 当 JVM 启动的时候，G1 先准备好 Eden 区，程序在执行的过程中不断创建对象到 Eden 区，当 Eden 区空间耗尽的时候，G1 会启动一次年轻代垃圾回收过程（只有 Eden 区能触发 GC，幸存者区不会触发，但是 Eden 出发了也会同时回收幸存者区）
- YGC 时，首先 G1 停止应用程序的执行（stop-The-world），G1 创建回收集（Collection set），回收集是指需要被回收的内存分段的集合，年轻代回收过程的回收集包含年轻代 Eden 区和 survivor 区所有的内存分段。
- ![image.png](jvm/image-1669530030817.png)
- **第一阶段，扫描根（GC Roots）**
  - 根是指 static 变量指向的对象，正在执行的方法调用链条上的局部变量等。根引用连同 RSet 记录的外部引用作为扫描存活对象的入口。
- **第二阶段，更新 RSet。**
  - 处理 dirty card queue(脏卡表)中的 card，更新 RSet。此阶段完成后，**RSet 可以准确的反映老年代对所在的内存分段中对象的引用。**
    - 应用程序中引用赋值语句 object.field=object，JVM 会在之前和之后执行特殊的操作以在 dirty card queue(脏卡表)中入队一个保存了对象引用信息的 card。在年轻代回收的时候，G1 会对 dirty card queue(脏卡表)中所有的 card 进行处理，以更新 RSet，保证 RSet 实时准确反映引用关系。
    - 在赋值语句处直接更新 RSet 时，由于 RSet 需要线程同步，开销大，使用队列会提高性能。
- **第三阶段，处理 RSet。**
  - 识别被老年代对象指向的 Eden 中的对象，这些被指向的 Eden 中的对象被认为是存活的对象。
- **第四阶段，复制对象。**
  - 此阶段，对象树被遍历，Eden 区内存段中存活的对象会被复制到 survivor 区中空的内存分段，Survivor 区内存段中存活的对象如果年龄未达阈值，年龄会加 1，达到阀值会被会被复制到 old 区中空的内存分段。如果 survivor 空间不够，Eden 空间的部分数据会直接晋升到老年代空间。
- **第五阶段，处理引用。**
  - 处理 Soft，Weak，Phantom，Final，JNI Weak 等引用。最终 Eden 空间的数据为空，GC 停止工作，而目标内存中的对象都是连续存储的，没有碎片，所以复制过程可以达到内存整理的效果，减少碎片。

G1 回收过程二：并发标记过程：

- **初始标记阶段**
  - 标记从根节点直接可达的对象。这个阶段是 STW 的，并且会触发一次年轻代 GC。
- **根区域扫描(Root Region Scanning)**
  - G1 GC 扫描 survivor 区直接可达的老年代区域对象，并标记被引用的对象。这一过程必须在 young gc 之前完成。
- **并发标记(Concurrent Marking)**
  - 在整个堆中进行并发标记(和应用程序并发执行)，此过程可能被 young GC 中断。在并发标记阶段，若发现区域对象中的所有对象都是垃圾，那这个区域会被立即回收。同时，并发标记过程中，会计算每个区域的对象活性(区域中存活对象的比例)。
- **再次标记(Remark)**
  - 由于应用程序持续进行，需要修正上一次的标记结果。是的。G1 中采用了比 CMS 更快的初始快照算法:snapshot-at-the-beginning (SATB)
- **独占清理(cleanup,STW)**
  - 计算各个区域的存活对象和 GC 回收比例，并进行排序识别可以混合回收的区域。为下阶段做铺垫。是 STW 的。
  - 这个阶段并不会实际上去做垃圾的收集。
- **并发清理阶段**
  - 识别并清理完全空闲的区域。

G1 回收过程三：混合回收

- 当越来越多的对象晋升到老年代 old region 时，为了避免堆内存被耗尽，虚拟机会触发一个混合的垃圾收集器，即 Mixed Gc，该算法并不是一个 old Gc，除了回收整个 Young Region，还会回收一部分的 old Region。这里需要注意:是一部分老年代，而不是全部老年代。可以选择哪些 old Region 进行收集，从而可以对垃圾回收的耗时时间进行控制。也要注意的是 Mixed Gc 并不是 Full GC。
- 并发标记结束以后，老年代中百分百为垃圾的内存分段被回收了，部分为垃圾的内存分段被计算了出来。默认情况下，这些老年代的内存分段会分 8 次（可以通过-xx:G1MixedGccountTarget 设置）被回收。
- 混合回收的回收集(Collection Set）包括八分之一的老年代内存分段，Eden 区内存分段，Survivor 区内存分段。混合回收的算法和年轻代回收的算法完全一样，只是回收集多了老年代的内存分段。具体过租请参考上面的年轻代回收过程。
- 由于老年代中的内存分段默认分 8 次回收，G1 会优先回收垃圾多的内存分段。垃圾占内存分段比例越高的，越会被先回收。并且有一个阈值会决定内存分段是否被回收，-XX:G1MixedGCLiveThresholdPercent，默认为 65%，意思是垃圾占内存分段比例要达到 65%才会被回收。如果垃圾占比太低，意味着存活的对象占比高，在复制的时候会花费更多的时间。
- 混合回收并不一定要进行 8 次。有一个阈值-xX:G1HeapwastePercent，默认值为 10%，混合回收并不一定要进行 8 次。有一个阈值-XX:G1HeapwastePercent，默认值为 10%，存的比例低于 10%，则不再进行混合回收。因为 GC 会花费很多的时间但是回收到的内存却很少。
- ![image.png](jvm/image-1669530035170.png)

G1 回收过程四：Full GC

- G1 的初衷就是要避免 Full GC 的出现。但是如果上述方式不能正常工作，G1 会停止应用程序的执行（Stop-The-World），使用单线程的内存回收算法进行垃圾回收，性能会非常差，应用程序停顿时间会很长。
- 要避免 Full GC 的发生，一旦发生需要进行调整。什么时候会发生 Full Gc 呢?：比如堆内存太小，当 G1 在复制存活对象的时候没有空的内存分段可用，则会回退到 full gc，这种情况可以通过增大内存解决。
- 导致 G1Full GC 的原因可能有两个:
  - Evacuation 的时候没有足够的 to-space 来存放晋升的对象;
  - 并发处理过程完成之前空间耗尽。

G1 回收过程：补充

- 从 Oracle 官方透露出来的信息可获知，回收阶段(Evacuation)其实本也有想过设计成与用户程序一起并发执行，但这件事情做起来比较复杂，考虑到 G1 只是回收一部分 Region, 停顿时间是用户可控制的，所以并不迫切去实现，而**选择把这个特性放到了 G1 之后出现的低延迟垃圾收集器(即 ZGC)** 中。另外，还考虑到 G1 不是仅仅面向低延迟，停顿用户线程能够最大幅度提高垃圾收集效率，为了保证吞吐量所以才选择了完全暂停用户线程的实现方案。

##### 8.6.8.7 G1 回收器优化建议

- 年轻代大小
  - 避免使用-Xmn 或-XX:NewRatio 等相关选项显示设置年轻代大小，因为固定年轻代的大小会覆盖暂停时间目标
- 暂停时间目标不要太过严苛
  - G1 GC 的吞吐量目标是 90%的应用程序时间和 10%的垃圾回收时间
  - 评估 G1 GC 的吞吐量时，暂停时间目标不要太严苛。目标太过严苛表示你愿意承受更多的垃圾回收开销，而这些会直接影响到吞吐量。

##### 8.6.8.8 Remembered Set（记忆集 RSet）

G1 比 CMS 多占用 10%~20%的空间，其中一部分就用于存放记忆集。

垃圾回收时可能存在的问题：一个 Region 不可能是孤立的，一个 Region 中的对象可能被其他任意 Region 中对象引用。判断对象存活时，需要扫描整个 Java 堆才能保证准确，如回收新生代也不得不同时扫描老年代，这样的话会降低 Minor Gc 的效率。所有收集器都有这个问题，只是 G1 更严重。

解决方案：

- 无论 G1 还是其他分代收集器，JVM 都是使用 Remembered Set 来避免全局扫描。
- 每个 Region 都有一个对应的 Remembered Set，每次 Reference 类型数据写操作时，都会产生一个 write Barrier（写屏障）暂时中断操作，然后检查将要写入的引用指向的对象是否和该 Reference 类型数据在不同的 Region（其他收集器：检查老年代对象是否引用了新生代对象）。
- 如果不同，通过 cardTable（卡表）把相关引用信息记录到引用指向对象的所在 Region 对应的 Remembered set 中。
- 当进行垃圾收集时，在 GC 根节点的枚举范围加入 Remembered Set，就可以保证不进行全局扫描，也不会有遗漏。

![image.png](jvm/image-1669530039326.png)

#### 8.6.9 垃圾回收器对比

Hotspot 有这么多的垃圾回收器，Serial GC、Parallel GC、Concurrent Mark Sweep GC 这三个有什么不同呢？

- 如果你要最小化使用内存和并行开销，请选 Serial GC。
- 如果你想要最大化应用程序的吞吐量，请选 Parallel GC。
- 如果你想要最小化 GC 的中断或停顿时间，请选 CMS GC。
- 其他：JDK9 中 CMS 标记过时。JDK14 全面删除 CMS。

![image.png](jvm/image-1669530043103.png)
Java 垃圾收集器的配置对于 JVM 优化来说是一个很重要的选择，选择合适的垃圾收集器可以让 JVM 的性能有一个很大的提升。

- 优先调整堆的大小让 JVM 自适应完成。
- 如果内存小于 100M，使用串行收集器
- 如果是单核、单机程序，并且没有停顿时间的要求，串行收集器。
- 如果是多 CPU、需要高吞吐量、允许停顿时间超过 1 秒，选择并行或者 VM 自己选择。
- 如果是多 CPU、追求低停顿时间，需快速响应（比如延迟不能超过 1 秒，如互联网应用），使用并发收集器。
- 官方推荐 G1，性能高。**现在互联网的项目，基本都是使用 G1。**

#### 8.6.10 垃圾回收器的未来发展

G1 GC 在 JDK 10 以后，Full Gc 已经是并行运行，在很多场景下，其表现还略优于 Parallel Gc 的并行 Full GC 实现。

在 Serverless 等新的应用场景下，Serial GC 找到了新的舞台。

CMS GC 因为其算法的理论缺陷等原因，在 JDK9 中已经被标记为废弃，并在 JDK14 版本中移除。
ZGC 的目标是在尽可能对吞吐量影响不大的前提下，实现在任意堆内存大小下都可以把垃圾收集的停顿时间限制在十毫秒以内的低延迟。《深入理解 Java 虚拟机》一书中这样定义 ZGC: zGc 收集器是一款基于 Region 内存布局的，（暂时）不设分代的，使用了读屏障、染色指针和内存多重映射等技术来实现可并发的标记-压缩算法的，以低延迟为首要目标的一款垃圾收集器。

ZGC 的工作过程可以分为 4 个阶段:并发标记-并发预备重分配-并发重分配-并发重映射等。ZGC 几乎在所有地方并发执行的，除了初始标记的是 STW 的。所以停顿时间几乎就耗费在初始标记上，这部分的实际时间是非常少的。

### 8.7 常用的显示 GC 日志的参数

内存分配与垃圾回收的参数列表

- `-XX:+PrintGC`：输出 Gc 日志。类似`-verbose:gc`
  - ![image.png](jvm/image-1669530046051.png)
- `-XX:+PrintGCDetails`：输出 Gc 的详细日志
  - ![image.png](jvm/image-1669530048648.png)
- `-XX:+PrintGCTimestamps`：输出 Gc 的时间戳（以基准时间的形式)
  - ![image.png](jvm/image-1669530050876.png)
- `-XX:+PrintGCDatestamps`：输出 Gc 的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800)
- `-XX :+PrintHeapAtGC`：在进行 GC 前后打印出堆栈信息
  - ![image.png](jvm/image-1669530052714.png)
- `-Xloggc:../logs/gc.log`：日志文件的输出路径
  - logs 位于工程目录的根目录下
  - ![image.png](jvm/image-1669530054493.png)
  - ![image.png](jvm/image-1669530056710.png)
- 案例一：
  - ![image.png](jvm/image-1669530058553.png)
  - ![image.png](jvm/image-1669530061352.png)
- 日志分析工具：GCViewer、GCEasy、GcHisto、GCLogViewer、Hpjmeter、garbagecat 等。
