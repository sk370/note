## 认识

> 配套书籍：《ASP.NET Core 技术内幕与项目实战》

### 反编译工具

- `ILSpy`

### .NET

- `.NET`是开发平台。
- `.NET`是`.NET Framework`、`.NET Core`、`Xamarin/Mono`的统称。

  - `.NET Framework`：Windows平台软件开发技术。
  - `.NET Core`：跨平台`.NET`开发技术。为云而生。
  - `Xamarin/Mono`：跨平台开发技术


### Windows Server

- 服务授权成本高。
- `IIS`太吃性能。

### .NET Core的主要问题

- `C#`是`.NET`的主要开发语言。

  - 《`C#`图解教程》
  - 《`C#`入门经典》

- `.NET Core`不是`.NET Framework`的升级版。原来在`.NET Framework`可以运行的程序，直接放在`.NET Core`可能无法直接运行。
- `Mono`也是微软收购的跨平台技术，为什么还要开发`.NET Core`？原因如下：

  - `.NET Framework`是系统级别安装，互相影响。
  - `.NET Framework`程序无法独立部署，需要安装`.NET Framework`环境。
  - `ASP.NET`和`IIS`深度耦合
  - `ASP.NET`资源消耗大
  - 非云原生
  - `.NET Framework`的历史包袱：

    - `ASP.NET MVC`基于`WPF`（拖控件开发）之上，底层还是拖控件，开发复杂、恶心。
    - `ASP.NET`底层不支持很好的单元测试。

  - `Mono`擅长移动端，但不能开发服务端。

- `.NET Core`的优点

  - 支持独立部署。不影响其他应用的运行环境。
  - 彻底模块化
  - 没有历史包袱，运行效率高
  - 不依赖于`IIS`，本身自带一个服务器。
  - 跨平台
  - 符合现代开发理念：依赖注入、单元测试等

- `.NET Core`和`.NET Framework`的不同：

  - 不支持：`ASP.NET WebForms`、`WCF`服务器端、`WF`、`.NET Remoting`、`Appdomain`
  - 部分`Windows-Only`的特性也可以使用`.NET core`开发，但是无法跨平台，如：`WinForm`、`WPF`、注册表操作、`Event Log`、`AD`等。

- `.NET5`之后，微软有意淡化`.NET Core`和`.NET Framework`，微软想让提到`.NET`就想到`.NET Core`。

  - `.NET5`是`.NET Core 3.1`和`.NET Framework 4.8`的下一个版本，形成了统一。可以看作是`.NET Framework 5`，但指代还以变成了`.NET Core`。
  - `.NET5`对应`Visual Studio 2019`
  - `.NET6`对应`Visual Studio 2022`


### .NET Standard

- `.NET Standard`是`.NET Core`和`.NET Framework`的标准。
- `.NET Core`和`.NET Framework`是 `.NET Standard`的实现。
- ![](assets/20230507111400.png)
- `.NET Standard`只是规范，一个`.NET Standard`类库可以被支持其版本的`.NET Framework`、`.NET Core`、`Xamarin`等引用。而`.NET Core`类库、`.NET Framework`类库则不可以。如果编写一个公用的类库，尽量选择`.NET Standard`,并且尽量用低版本。
- ![](assets/20230507112007.png)
- `.NET Framework`支持到`.NET Standard 2.0`为止。

### .NET 5开发工具

- `.NET CLI`:命令行
- `Visual Studio`：`Windows-Only`（推荐）
- `Visual Studio for Mac`
- `Jetbrains Rider`：收费
- `VS Code(Visual Studio Code)`：跨平台

### .NET Core中的SDK和Runtime

根据.Net核心指南,.NET Core由以下项目组成：

- `.NET`运行时：提供类型系统,程序集加载,垃圾收集器，本机互操作和其他基本服务。
- 一组框架库：提供原始数据类型，应用程序组合类型和基本实用程序。
- 一组SDK工具和语言编译器：支持`.NET Core SDK`中提供的基础开发人员体验。
- `dotnet`应用主机：用于启动`.NET Core`应用。它选择运行时并托管运行时，提供程序集加载策略并启动应用程序。同样的主机也用于以相同的方式启动SDK工具。
- SDK是所有需要/使开发`.NET Core`应用程序更容易的东西。例如CLI和编译器。
- 运行时是托管/运行应用程序的"虚拟机"，它抽象了与基本操作系统的所有交互。

运行应用程序只需要后者，但开发应用程序需要前者。

- 软件开发工具包(SDK)包括使用命令行工具和任何编辑器(包括Visual Studio)构建和运行`.NET Core`应用程序所需的一切。
- 在运行时只包括运行现有的`.NET`核心应用所需的资源。
- 运行时包含在SDK中。

### .NET CLI

- `.NET CLI`不需要单独安装
- `dotnet`：查看是否安装`.NET`环境
- `dotnet new console`：在当前文件夹下创建`console`项目，项目默认名称为当前文件夹名。
- `dotnet run`：会自动构建、运行项目
- [.NET CLI | Microsoft Learn](https://learn.microsoft.com/zh-cn/dotnet/core/tools/)

## .NET核心技术

### NuGet