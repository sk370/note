# Python

> *基于廖雪峰*

## 1. Python基础

### 1.1 Python解释器

1. CPython：官方软件自带。
   1. `>>>`作为提示符。
2. IPython：基于CPython增强。
   1. `In[序号]`作为提示符。
3. PyPy：JIT技术动态编译，追求速度。
   1. 与CPython执行结果可能不同。
4. Jython：可以将Python代码编译成Java字节码执行。
5. IronPython：可以将Python代码编译成 `.net`字节码执行。

### 1.2 输入和输出

#### 1.2.1 输出

1. 简单输出：

   ```python
   >>> print('hello, world')
   hello, world
   ```

2. 多字符串输出：遇到逗号“,”会输出一个空格。

   ```python
   >>> print('The quick brown fox', 'jumps over', 'the lazy dog')
   The quick brown fox jumps over the lazy dog
   ```

#### 1.2.2 输入

1. 简单输入：

   ```python
   >>> name = input()
   Michael
   >>> name
   'Michael'
   >>> print(name)
   Michael
   ```

2. 带输入提示的输入：

   ```python
   >>>name = input('please enter your name: ')
   please enter your name: Michael
   print('hello,', name)
   ```

### 1.3 数据类型和变量

1. 整数。
2. 浮点数。
3. 字符串：使用`'`或`"`包裹的任意文本。
   1. `\`转移字符。
4. 布尔值：`True`、`False`
5. 空值：`None`

> **同一个变量可以反复赋值，而且可以是不同类型的变量**

### 1.4 字符编码

1. ASCII编码：只有大小写英文字母、数字和一些符号。
   1. 一个字符占用一个字节（byte）
2. Unicode标准：一个字符通常占用两个字节。
3. UTF-8：可变长编码。英文字母一个字节，汉字三个字节，生僻字4~6个字节。
4. `ord()`函数获取字符的整数表示，`chr()`函数把编码转换为对应的字符.

   ```python
   >>> ord('A')
   65
   >>> ord('中')
   20013
   >>> chr(66)
   'B'
   >>> chr(25991)
   '文'
   ```

5. `len()`函数计算的是str的字符数，如果换成bytes，`len()`函数就计算字节数：

   ```python
   >>> len('ABC')
   3
   >>> len(b'ABC')
   3
   ```

6. python文件开头：

   ```python
   #!/usr/bin/env python3
   # -*- coding: utf-8 -*-
   ```

   1. 第一行注释是为了告诉Linux/OS X系统，这是一个Python可执行程序，Windows系统会忽略这个注释；
   2. 第二行注释是为了告诉Python解释器，按照UTF-8编码读取源代码。
   3. 同时，必须使用UTF-8 without BOM编码方式打开文件。

### 1.5 格式化

1. %运算符用来格式化字符串。
   1. 在字符串内部，`%s`表示用字符串替换，`%d`表示用整数替换，`%f`表示用浮点数替换，`%x`表示用十六进制数替换；
   2. 有几个`%?`占位符，后面就跟几个变量或者值，顺序要对应好。如果只有一个`%?`，括号可以省略。
   3. 格式化整数和浮点数还可以指定是否补0和整数与小数的位数。

   ```python
   >>> 'Hello, %s' % 'world'
   'Hello, world'
   >>> 'Hi, %s, you have $%d.' % ('Michael', 1000000)
   'Hi, Michael, you have $1000000.'
   >>>print('%2d-%02d' % (3, 1))
   3-01
   >>>print('%.2f' % 3.1415926)
   3.14
   ```

2. 另一种格式化字符串的方法是使用字符串的`format()`方法，它会用传入的参数依次替换字符串内的占位符{0}、{1}……

   ```python
   >>> 'Hello, {0}, 成绩提升了 {1:.1f}%'.format('小明', 17.125)
   'Hello, 小明, 成绩提升了 17.1%'
   ```

3. 最后一种格式化字符串的方法是使用以f开头的字符串，称之为f-string，它和普通字符串不同之处在于，字符串如果包含{xxx}，就会以对应的变量替换.

   ```python
   >>> r = 2.5
   >>> s = 3.14 * r ** 2
   >>> print(f'The area of a circle with radius {r} is {s:.2f}')
   The area of a circle with radius 2.5 is 19.62
   ```

   
   