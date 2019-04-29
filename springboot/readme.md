# 实现情况

## 用spring boot实现点击书籍返回书籍详情页面

访问[http://localhost:8080/brief.html](http://localhost:8080/brief.html) ，可以看到图书浏览界面，点击书籍，spring boot回传图书详情页面

**注：**

**1.接用spring boot 回传HTML页面，不是返回数据**

**2.图书详情数据直接写在DetailController.java中，没有具体列举数据**

## 连接数据库

用JdbcTemplate

添加配置文件 /resources/static/application.properties

设置端口为8011，即访问[http://localhost:8011/brief.html](http://localhost:8011/brief.html)

在DetailController.java中用sql语句从ebook数据库中查找图书详情