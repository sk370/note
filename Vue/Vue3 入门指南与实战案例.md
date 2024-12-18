> https://vue3.chengpeiquan.com/

## 前端工程化

### VUE“数据驱动”编程

原生 JavaScript 需要频繁的操作 DOM 才能达到输入内容即时体现在文本 DOM 上面，并且还要考虑 DOM 是否已渲染完毕，否则操作会出错。

如果在一个页面上频繁且大量的操作真实 DOM ，频繁的触发浏览器回流（ Reflow ）与重绘（ Repaint ），会带来很大的性能开销，从而造成页面卡顿，在大型项目的性能上很是致命。

而 Vue 则是通过操作虚拟 DOM （ Virtual DOM ，简称 VDOM ），每一次数据更新都通过 Diff 算法找出需要更新的节点，只更新对应的虚拟 DOM ，再去映射到真实 DOM 上面渲染，以此避免频繁或大量的操作真实 DOM 。

### 框架与类库库的区别

Vue.js 是一个框架，框架除了简化编码过程中的复杂度之外，面对不同的业务需求还提供了通用的解决方案，而这些解决方案，通常是将前端工程化里的很多种技术栈组合起来串成一条条技术链，一环扣一环，串起来就是一个完整的工程化项目。

### 现代化开发概念

#### MPA与SPA

MPA：Multi-Page Application，多页面应用是最传统的网站体验，当一个网站有多个页面时，会对应有多个实际存在的 HTML 文件，访问每一个页面都需要经历一次完整的页面请求过程：

> 从用户点击跳转开始：
>
> - 浏览器打开新的页面
> - 请求【所有】资源
> - 加载 HTML 、CSS 、 JS 、 图片等资源
> - 完成新页面的渲染

SPA:Single-Page Application，单页面应用。不论站点内有多少个页面，在 SPA 项目实际上只有一个 HTML 文件，也就是 index.html 首页文件。

> 从用户点击跳转开始：
>
> - 浏览器通过 `pushState` 等方法更新 URL
> - 请求接口数据（如果有涉及到前后端交互）
> - 通过 JavaScript 处理数据，拼接 HTML 片段
> - 把 HTML 片段渲染到指定位置，完成页面的 “刷新”

SPA 在页面跳转的时候，地址栏也会发生变化，主要有以下两种方式：

1. 通过修改 `Location:hash` 修改 URL 的 Hash 值（也就是 # 号后面部分），例如从 `https://example.com/#/foo` 变成 `https://example.com/#/bar`
2. 通过 `History API` 的 pushState 方法更新 URL ，例如从`https://example.com/foo` 变成 `https://example.com/bar`

这两个方式的共同特点是更新地址栏 URL 的时候，均不会刷新页面，只是单纯的变更地址栏的访问地址，而网页的内容则通过 AJAX 更新，配合起来就形成了一种网页的 “前进 / 后退” 等行为效果。

#### CSR和SSR

|名词|	全称|	中文|
|-|-|-|
|CSR|	Client-Side Rendering|	客户端渲染|
|SSR|	Server-Side Rendering|	服务端渲染|

CSR是一种利用 AJAX 技术，把渲染工作从服务端转移到客户端完成。因此，大部分情况下， CSR 等同于 SPA。

传统的服务端渲染通常由后端开发者一起维护前后端代码，而 SSR 服务端渲染则是交给前端开发者来维护，利用 Node 提供的能力进行同构渲染。

SSR 技术利用的同构渲染方案（ Isomorphic Rendering ），指的是一套代码不仅可以在客户端运行，也可以在服务端运行，在一些合适的时机先由服务端完成渲染（ Server-Side Rendering ）再直出给客户端激活（ Client-Side Hydration ）。

#### Pre-Rendering和SSG

#### ISR和DPR

#### SEO的TKD

网页的 TKD 三要素是指一个网页的三个关键信息，含义如下：

- T ，指 Title ，网站的标题，即网页的 `<title>`网站的标题`</title>` 标签。
- K ，指 Keywords ，网站的关键词，即网页的 `<meta name="Keywords" content="关键词1,关键词2,关键词3" />` 标签。
- D ，指 Description ，网站的描述，即网页的 `<meta name="description" content="网站的描述" />` 标签。

这三个要素标签都位于 HTML 文件的 `<head />` 标签内。

### 前端的扩展

### 服务端开发

nodejs使得前端变为全栈。

Node 本身是一个 JavaScript 的运行时，由于提供了 HTTP 模块 可以启动一个本地 HTTP 服务，当 Node 项目部署到服务器上时，就可以运行一个可对外访问的公网服务。

但 Node 的原生服务端开发成本比较高，因此在 GitHub 开源社区也诞生了很多更方便的、开箱即用、功能全面的服务端框架，根据它们的特点，可以简单归类如下：

以 Express 、 Koa 、 Fastify 为代表的轻量级服务端框架。这一类框架的特点是 “短平快” ，对于服务端需求不高，只是跑一些小项目的话，开箱即用非常地方便，比如 Build 了一个 Vue 项目，然后提供一个读取静态目录的服务来访问它。

但是 “短平快” 框架带来了一些团队协作上的弊端，如果缺少一些架构设计的能力，很容易把一个服务端搭的很乱以至于难以维护，比如项目的目录结构、代码的分层设计等等，每个创建项目的人都有自己的想法和个人喜好，就很难做到统一管理。

因此在这些框架的基础上，又诞生了以 Nest （底层基于 Express ，可切换为 Fastify ）、 Egg （基于 Koa ）为代表的基于 MVC 架构的企业级服务端框架，这一类框架的特点是基于底层服务进行了更进一步的架构设计并实现了代码分层，还自带了很多开箱即用的 Building Blocks ，例如 TypeORM 、WebSockets 、Swagger 等等，同样也是开箱即用，对大型项目的开发更加友好。

#### App开发

常规的 Native App 原生开发需要配备两条技术线的支持：使用 Java / Kotlin 语言开发 Android 版本，使用 Objective-C / Swift 语言开发 iOS 版本。

Hybrid App 的出现，使得前端开发者也可以使用 JavaScript / TypeScript 来编写混合 App ，最大的特色就是一套代码可以运行到多个平台，这是因为整个 App 只有一个基座，里面的 App 页面都是使用 UI WebView 来渲染的 Web 界面。

构建 Hybrid App 的时候还可以顺带输出一个 Web App 版本，也就是让这个 App 在被用户下载前，也有一模一样的网页版可以体验。

基于 Vue 的 uni-app 、基于 React 的 React Native 。

#### 桌面程序开发

以前要开发一个 Windows 桌面程序，需要用上 QT / WPF / WinForm 等技术栈，还要学习 C++ / C# 之类的语言。

Electron / Tauri 等技术栈的出现，使得前端也可以构建桌面给程序。其中 Electron 的成熟度最高、生态最完善、最被广泛使用。

#### 应用脚本开发

同桌面程序，只是桌面脚本有GUI。

Pkg工具 ，它可以把 Node 项目打包为一个可执行文件，可以让没有node环境的情况下运行脚本。

### 实践工程化的流程

|常用方案	|Runtime|	构建工具|	前端框架|
|-|-|-|-|
|方案一	|Node|	Webpack	|Vue|
|方案二	|Node|	Vite|	Vue|

### Nodejs

Node.js （简称 Node ） 是一个基于 Chrome V8 引擎构建的 JS 运行时（ JavaScript Runtime ）。

Runtime ，可以叫它 “运行时” 或者 “运行时环境” ，这个概念是指，项目的代码在哪里运行，哪里就是运行时。

传统的 JavaScript 只能跑在浏览器上，每个浏览器都为 JS 提供了一个运行时环境，可以简单地把浏览器当成一个 Runtime。

Node 就是一个让 JS 可以脱离浏览器运行的环境，当然，这里并不是说 Node 就是浏览器，没有 Window 、没有 Document 、没有 DOM 、没有 Web API ，没有 UI 界面，同时提供了很多浏览器做不到的能力，比如和操作系统的交互。

### 工程化构建工具