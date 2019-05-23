# web-ebook

## 电子书网站

## 前端开发

### **version 0.1**

静态页面（html+css）

* 主页（登录、查询）
* 图书浏览（多种图书、简单信息）
* 图书详情 （一本书、详细介绍）

brief.html和detail.html有两种样式（css1.css、css2.css）

可以在两个html文件中的`<head>`中修改要使用的样式即两个不同的css文件

### **version 0.2**

增加js

* 主页使用bootstrap模板，修改css样式
* 主页增加登录功能
* 图书浏览页增加event，当鼠标悬停在图书名字上时，该书文字变成红色，鼠标离开时变回黑色

### **version 1.0**

第一次迭代 *文件在 /vue目录下*

* 前端框架 - Vue
* 使用element-ui
* 前端页面基本实现
* 重点实现图书浏览页面
* 购物车、订单、登录页面只有大概内容
* 数据写在前端，没有实现数据分离

## 后端开发

- ### **version 2.0**


利用spring boot开发后端   *文件在 /springboot目录下*

前端使用使用vue框架之前的html、css和js文件，只涉及图书浏览界面

* 点击图书时，后端返回相应的详情页面
* 数据直接写在Controller中

### **version 2.1**

spring boot + JdbcTemplete 连接mysql数据库

* 图书详情页的数据通过sql语句从数据库中查询得到

### version 2.2

第二次迭代

- 前后端集成
- 实现普通用户所有功能

## 代码重构