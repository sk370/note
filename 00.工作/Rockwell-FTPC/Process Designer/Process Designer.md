## 主页

以下帮助文档可从 Process Designer 的 HELP 菜单或 FactoryTalk ProductionCentre 主页上的 HELP 链接访问:

- Process Designer API方法汇总：描述了应用程序开发者可用的Plant Operations的类和方法。
- Process Designer及对象帮助：介绍了Process Designer 接口和Plant Operations对象。本节还包括有关Plant Operations对象和构建应用程序所需功能的概念信息。
- Plant Operations数据字典：描述生产库模式的表和属性。
- 脚本语言概述：描述了Process Designer脚本语言。
- Plant Operations发行说明：显示当前安装的Plant Operations版本的发行说明。

Copyright 2018 罗克韦尔自动化技术公司保留所有权。

修改于 4/25/2018 11:50:07

## 术语

## Process Designer API方法总结

## Process Designer 和对象帮助

## 数据字典

## 脚本语言概览

Process Designer 脚本语言是一种面向对象和基于事件的语言，允许您使用 Process Designer API 和 JavaTM API 构建脚本。脚本语言通过在 API 中合并许多所需的功能，简化了构建自定义应用程序的过程。脚本语言中，不需要创建实例化类。API 文档中描述了类、方法和字段。

**基于表达式**

脚本语言是基于表达式的，在运行时，脚本通过脚本解释器交互式计算。脚本环境按顺序计算表达式，然后将最后一个结果传递给 Java 环境。

**基本数据**

基本数据自动转换为对象。例如，声明了一个变量 `a = 1`，它将被转换为对象 `java.lang.Integer.a = 1`，算术运算使用包装对象进行计算。

**方法**

脚本可以包含一些函数，这些函数可以接受任意数量的参数，并通过它们的名称和参数数量来标识。可以将函数赋给变量。除了由 Java 对象包装的基本类型值之外，参数和结果按原样传递——引用传参。

## 发行说明