# yop-opr-showcase
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fyop-platform%2Fyop-java-opr-showcase.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fyop-platform%2Fyop-java-opr-showcase?ref=badge_shield)


YOP OPR 业务，某商城对接易宝收银台的演示

## 项目结构

采用 Springboot + Maven + h2 实现，代码结构如下：

* com.yeepay.demo.dashboard 管理后台
* com.yeepay.yop.showcase.shop 商城
* com.yeepay.yop.sdk.service 对接YOP的业务层代码

## 配置yop相关参数

请在 application.yml 配置yop相关参数及异步通知地址

## 运行项目

### 1.浏览器访问以下地址

http://127.0.0.1:8080/yop-opr-showcase/

### 2.选购商品并加入购物车，如需登录请使用以下账号信息

* 用户名：yiyu
* 密码：yiyu12

### 3.发起支付，跳转到易宝收银台

## 其他注意事项

### 1.异步通知

由于异步通知需要公网IP，因此内网用户可能无法正常接收。
您可以采用 sunny-ngrok 进行内网穿透，客户端下载地址：http://www.ngrok.cc/download.html
使用教程地址http://www.ngrok.cc/_book/start/ngrok_linux.html

#### Mac、Linux、树莓派

> ./sunny clientid 隧道id

#### 启动多个隧道

> ./sunny clientid 隧道id,隧道id

#### 要想后台运行可以使用 setsid 命令

> setsid ./sunny clientid 隧道id &


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fyop-platform%2Fyop-java-opr-showcase.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fyop-platform%2Fyop-java-opr-showcase?ref=badge_large)