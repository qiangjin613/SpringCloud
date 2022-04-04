# SpringCloud

## 1. 搭建父项目

即外层的 pom.xml 文件，关注 packaging、properties 和 dependencyManagement 三个元素。

注意：

1. packaging 中，值 pom 与 jar、war 的区别；
2. properties 中的“统一管理 jar 包版本”；
3. dependencyManagement 与 dependencies 的区别。

## 2.构建支付模块：Payment

可简单分为以下几步：1）建 module；2）改 pom.xml；3）写 yum/properties；4）主启动；5）业务类。

## 3. 构建消费者模块：Order

构建消费模块，并完成远程调用支付模块的代码示例，使用原始的：RestTemplate 完成。

> RestTemplate 是 Spring3.0 开始支持的一个 HTTP 请求工具，用于进行服务调用。

## 4. 构建公共模块：Common

