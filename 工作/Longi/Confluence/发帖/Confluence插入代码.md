## 方式一：插入Wiki标记

1. 操作步骤：插入 》 wiki标记 》编写代码 》 插入。
2. 语法格式：
```
{code:title=Bar.java|borderStyle=solid}
// Some comments here
public String getFoo()
{
    return foo;
}
{code} 
```
 3. 图形界面：
	- ![](attachments/Pasted%20image%2020230312144153.png)
4. 代码高亮：
	- ![](attachments/Pasted%20image%2020230312151004.png)
	- ![](attachments/Pasted%20image%2020230312151053.png)
5. 预览效果：
	- ![](attachments/Pasted%20image%2020230312151124.png)

## 方式二：Markwodn转换

1. 编写markdown语法格式的文档内容，如：

```java
public String getFoo()
{
    return foo;
}
```

2. 使用markdown转wiki格式的工具，转换方式多种多样，如：
	- 在线转换：[markdown2confluence (chunpu.github.io)](http://chunpu.github.io/markdown2confluence/browser/)。
	- VSCode编辑器的`Markdown to confluence for VSCode Extension`插件。
	- 各种本地转换工具，如pandoc、npm。
3. 将转换后的wiki内容插入到Confluence文档：
4. 效果预览：
	- 
