## 资料

1. 学长的思路：【果园软件工程大作业-demo开发示例】 https://www.bilibili.com/video/BV13i4y1k71a/?share_source=copy_web&vd_source=f42a7d97cc417701cd2e8b0d6ef5bcd3

2. GUI用swing，但是原生的ｓｗｉｎｇ太单一了：[(26条消息) 让你的Java Swing界面变得更好看，这是我用过最好看的皮肤包了？_swing皮肤_android 小白星的博客-CSDN博客](https://blog.csdn.net/qq_45137584/article/details/111411839)

   改写swing：[Swing是一把刀 - Java综合 - Java - ITeye论坛](https://www.iteye.com/topic/699515)

3. 开发方式他要求可以实现版本控制：那我觉得用ｇｉｔ比较好，也体现一个团队合作：

   [(26条消息) 使用git和github进行简单的版本控制_zbeihai的博客-CSDN博客](https://blog.csdn.net/zbeihai/article/details/118142837)

   [如何开始使用 Git 进行版本控制 | w3c笔记 (w3cschool.cn)](https://www.w3cschool.cn/article/23833916.html)

   【最简单的Github管理多人同时开发项目的教程  无需掌握任何命令就能完成版本控制 图形化操作 自动合并代码 太方便了】 https://www.bilibili.com/video/BV1o7411U7j6/?share_source=copy_web&vd_source=f42a7d97cc417701cd2e8b0d6ef5bcd3

4. 文件数据保存：json

5. 生成pdf简历的Java类：[IText使用（超详解） - sudt - 博客园 (cnblogs.com)](https://www.cnblogs.com/fonks/p/15090635.html)



## 软件项目结构



**DAO = Data Access Object = 数据存取对象**

**Service = 服务**

**Controller = 控制器**

**Util = 工具**

**Model = 模型**



首先，一个代码是不是有完善的结构，和是不是有上面这些东西没有什么关系，只是通常来说，我们做一个大项目会把项目分解成很多不不同的模块（Module），然后根据用途和角色，我们对这些模块有一个通用的命名规则，这也就是上面这些[英文单词](https://www.zhihu.com/search?q=英文单词&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})的来历。



所以，请一定记住，项目中是否包含这些模块或者单词，和你的项目结构是否完善一毛钱关系没有。但是当你的项目结构相对完善的时候，你会发现有这样一些角色的存在。



接下来一个个的来详细讨论一下这个东西是如何出现的：

DAO，数据存取对象。通常我们会遇到很多要和[数据库](https://www.zhihu.com/search?q=数据库&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})打交道的场景，如果为每一个场景都去写一些SQL语句，会让我们代码变得很奇怪，我们希望我们的代码比较干净整洁，那么一个很容易想到的方案就是把数据库封装一下，让我们和数据库的交道看起来比较像和一个对象打交道。这个对象通常就是DAO，当我们操作这个对象的时候，这个对象会自动的产生SQL语句来和数据库打交道，而我们只需要和DAO打交道就可以了。

当然，从本质上来说，DAO并不需要和数据库有什么必然的联系，DAO只是数据存取对象的缩写，所以只要是把数据持久化包装成一个对象的访问（读写），这种对象都可以被称之为DAO，譬如，用JSON格式存到硬盘上。



Service，我们有时候会需要一些相对独立，与[业务系统](https://www.zhihu.com/search?q=业务系统&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})没啥关系的功能。但不是所有的功能都可以做成一个服务，服务是一个相对独立的功能模块，完成一些指定的工作，这些工作高度抽象和通用。一个典型的服务像是数据库服务、缓存服务、文件存储服务、[身份验证服务](https://www.zhihu.com/search?q=身份验证服务&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})、[消息队列](https://www.zhihu.com/search?q=消息队列&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})服务等。

[关系型数据库服务](https://www.zhihu.com/search?q=关系型数据库服务&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})可以视为是一个接收SQL语句并给出一个查询结果的服务，我们不必关心服务内部具体是如何处理问题的，我们只需要关注服务给出的接口。

并不是所有的模块都适合做成服务，一个服务首先最重要的是独立性，这个服务必须可以独立的完成指定的工作。复杂的服务可能依赖于一个或者多个更基础的服务，但是服务通常不应当依赖于任何具体的[业务代码](https://www.zhihu.com/search?q=业务代码&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})，服务必须具有高度的抽象性。关系型数据库服务就具有高度的抽象性，事实上只要我们撰写标准的SQL，不论后面是MySQL、SQL Server还是Oracle，他们都会呈现出几乎完全相同的行为。

一个更为简单的服务像是缓存服务，我们把一坨数据放进去，在一段时间内可以快速的获取这坨数据，在一段时间后数据就会消失。

当你的代码需要一个高度抽象高度标准化的功能，而这个功能又不能简单的实现，或者这个功能需要很多资源的配合，例如缓存服务需要内存资源，而数据库服务通常需要磁盘资源，身份验证服务通常需要数据库服务支持。这个时候就可以考虑将这个功能模块做成一个服务。

服务作为基础的部件，我们通常会要求它能够应付各种各样的情况，一个优质的服务通常会有非常高的可用性，因为我们的系统可能会依赖于各种各样的服务，而整个系统的可用性将不可能比其中任何一个服务的可用性更高。

所以服务的特征：抽象、独立、稳定。



评论中提到Java项目中的Service通常是指Business Service，这里也简单说说。

很多时候，我们发现服务的特征对于我们开发一个大型项目的时候很有帮助。就拿独立性来说，关系型数据库服务如SQL Server可以独立发售，独立安装和部署。它可以自行测试自己的接口，如果都达到了预期的效果，并且能够应付各种情况，这个服务就可以作为一个产品独立的出售给我们安装。这意味着关系型数据库服务并不需要配合我们的业务系统一起进行测试和调试，或者作出什么变更。

在完成一个大型的业务系统时，我们发现一些子模块或者子系统也可以像服务一样独立的部署和测试。例如会员系统、支付系统、[订单系统](https://www.zhihu.com/search?q=订单系统&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})等等，他们的业务逻辑可能非常复杂，但是逻辑相对独立，并且高度内聚。如果我们将这些系统分别独立的测试和部署，就可以大大的降低我们的测试、部署和运维的成本。

这些可以独自完成某一方面业务功能，高度内聚，可以独立部署测试的模块，我们可以称之为Business Service，业务服务。它同样具有服务的特征，抽象、独立和稳定。一个会员系统内部的逻辑可能非常复杂（[积分规则](https://www.zhihu.com/search?q=积分规则&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})，分级规则，风险控制，行为数据），但是在其外部，会员的概念可以非常简单。





Util，Util通常来说是我们找不到合适的名字的时候的选择，Util就是工具，在做项目的时候我们总会遇到一些奇奇怪怪的小功能或者重复的代码需要提取。像是[URL编码](https://www.zhihu.com/search?q=URL编码&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})或者解码（当然这个类库通常会提供，不过就以 .NET Framework 为例，提供这个方法的类型名称叫做HttpUtility），或是自创的[加密签名算法](https://www.zhihu.com/search?q=加密签名算法&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})等等。





Model，模型，通常来讲，我们会把模型和另一个东西放在一起来说：View，视图。

模型通常认为是视图的**内核**，何谓之视图？我们正在与之交互的知乎网站的界面就是视图，而模型是指他的内核：数据。

知乎的数据是问题和答案，问题分为标题和描述，答案有内容和作者以及各种状态。知乎有很多个UI，例如移动页面，普通PC页面，手机APP，以及改版前的旧界面，这些被称作不同的视图。而所有这些形态迥异的视图，其内核都是一样的，这个内核我们就称之为模型（Model）。

将Model和View的概念拆分开来，有助于我们关注不同的方面，也可以更有效的分工。有些工程师更关注于内核也就是模型，通常来说，他们被称之为[后端工程师](https://www.zhihu.com/search?q=后端工程师&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})。有些工程师更关注于用户界面的交互和展示，通常来说，他们被称之为[前端工程师](https://www.zhihu.com/search?q=前端工程师&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"answer"%2C"sourceId"%3A157049250})。