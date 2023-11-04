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
6. list：可变有序列表。**使用`[]`定义。**
   1. `len(list)`：获取list元素的个数。
   2. 索引来访问list中每一个位置的元素.
      - 当索引超出了范围时，Python会报一个IndexError错误。
      - 负数索引可以从后往前获取数据。
   3. `list.append(str)`：添加元素到末尾。
   4. `list.insert(index,str)`：插入到指定索引位置。
   5. `list.pop()`：删除末尾元素。
   6. `list.pop(index)`：删除指定索引的元素。
   7. list的元素数据类型可以不同。
   8. list的元素可以是另一个list。
7. tuple：元组。同list，但初始化后不可修改。**使用`()`定义**。
   1. 能用tuple代替list就尽量用tuple。
   2. 只有1个元素的tuple定义时必须加一个逗号：`t = (1,)`
   3. 使用索引访问数据。
8. dict：字典。**使用`{}`定义。**
   1. 多次对一个key放入value，会更新前值。
      - 使用`dict[key]`访问数据。
   2. 如果key不存在，dict就会报错。
   3. 判断key是否存在：
      - `'key' in dict`：返回布尔值。
      - `dict.get('key', -1)`：不存在时返回指定值或None。
   4. `pop(key)`：删除指定key
   5. dict内部存放的顺序和key放入的顺序是没有关系的。
   6. 对比list：
      - 查找和插入的速度极快，不会随着key的增加而变慢；
      - 需要占用大量的内存，内存浪费多。
   7. dict的key必须是不可变对象。
      - 字符串、整数可以作为key。list是可变的，不能作为key：
9. set：集合。使用`([])`定义。
    1. 不能有重复数据。
        - 重复元素会自动被删除。
    2. `add(key)`方法可以添加元素到set中，可以重复添加，但不会有效果.
    3. `remove(key)`方法可以删除元素。
    4. 存入和取出数据数据顺序不一定一致——无序。
    5. 不同set可以进行布尔运算：如`set1 & set2`、`set1 | set2`


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

### 1.6 条件判断

```python
if <条件判断1>:
    <执行1>
elif <条件判断2>:
    <执行2>
elif <条件判断3>:
    <执行3>
else:
    <执行4>
```

1. 判断条件：非零数值、非空字符串、非空list等，就判断为True，否则为False。

### 1.7 模式匹配

```python
score = 'B'

match score:
    case 'A':
        print('score is A.')
    case 'B':
        print('score is B.')
    case 'C':
        print('score is C.')
    case _: # _表示匹配到其他任何情况
        print('score is ???.')
```

1. 没有匹配到，执行`_`的语句。该句只能放到末尾。
2. 多个条件匹配成立，只执行第一个匹配到的情况。

```python
age = 10

match age:
    case x if x < 11:
        print(f'< 10 years old: {x}')
    case 10:
        print('10 years old.')
    case 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18:
        print('11~18 years old.')
    case 19:
        print('19 years old.')
    case _:
        print('not sure.')
```

1. 第一个`case x if x < 10`表示当`age < 1`0成立时匹配，且赋值给变量x。

### 1.8 循环

1. 种类一：`for x in ...`
2. 种类二：`while`

## 2. 函数

### 2.1 函数定义

1. 定义一个函数要使用def语句，依次写出函数名、括号、括号中的参数和冒号`:`，然后，在缩进块中编写函数体，函数的返回值用`return`语句返回。
2. 如果没有`return`语句，函数执行完毕后也会返回结果，只是结果为`None`。`return None`可以简写为`return`。
3. 空函数：定义一个什么事也不做的空函数，可以用`pass`语句
    - `pass`可以用来作为占位符，比如现在还没想好怎么写函数的代码，就可以先放一个`pass`.
    - `pass`可以用于函数，也可以用于判断。
3. 返回多个值：

    ```python
    import math

    def move(x, y, step, angle=0):
        nx = x + step * math.cos(angle)
        ny = y - step * math.sin(angle)
        return nx, ny
    ```

    1. 返回多个值，其实本质是返回一个tuple。

### 2.2 函数参数

1. 默认参数

    ```python
    def power(x, n=2):
        s = 1
        while n > 0:
            n = n - 1
            s = s * x
        return s
    ```

    1. 定义函数时，必填参数必须在前。
    2. 默认参数必须指向不变对象！如，使用lits作为默认参数，多次调用后，默认参数值可能发生变化。

2. 可变参数：

    ```python
    def calc(*numbers):
        sum = 0
        for n in numbers:
            sum = sum + n * n
        return sum
    ```

    


