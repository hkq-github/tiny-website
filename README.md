## TinyWebsite项目综述

### Contents
* [文件](#文件)
* [项目介绍](#项目介绍)
  * [功能介绍](#功能介绍)
  * [部分界面截图](#部分界面截图)
* [在线演示](#在线演示)
* [怎样部署](#怎样部署)
  * [在Windows部署](#在Windows部署)
  * [在Linux服务器部署](#在Linux服务器部署)
* [快速了解项目](#快速了解项目)
  * [导入源代码](#导入源代码)
  * [项目结构](#项目结构)
    * [MVC](#MVC)
    * [与MVC相关的包](#与MVC相关的包)
  * [其他一些类](#其他一些类)


### 文件
在Github中包含以下文件：
* `Documents`：项目文档
* `TinyWebsite`：项目源程序，可在Eclipse下导入
* `TinyWebsite.war`  ：TinyWebsite项目打包后文件。可以复制到tomcat部署
* `create_db.sql` ：建立数据库sql文件
* `README.md` 

### 项目介绍

#### 功能介绍
* 用户注册、登录、自动登录、注销
* 用户修改个人信息、密码
* 统计在线人数
* 管理员登录、注销
* 管理员根据用户名、邮箱、昵称搜索用户；分页显示搜索结果
* 管理员操作用户：重置密码、冻结账户、删除账户

#### 部分界面截图
![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/login.jpg)

![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/home.jpg)

![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/admin_searchuser.jpg)

![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/manage_user.jpg)

### 在线演示
已经将项目部署到服务器，点击[www.tinyweb.site](www.tinyweb.,site) 或[106.12.4.118](http://106.12.4.118/login)在线演示 。

### 怎样部署

#### 在Windows部署
* 下载文件`TinyWebsite.war`和   `create_db.sql` 
* **建立数据库**

  登录mysql后运行 `mysql> source create_db.sql文件路径` 
  ![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/建立数据库.jpg)

* **复制`TinyWebsite.war`到tomcat** 安装目录下。 开启tomcat服务，目的是让tomcat解压`TinyWebsite.war` 
* **修改数据库配置：**

  关闭 tomcat服务，进入`tomcat安装目录\webapps\TinyWebsite\WEB-INF\classes\com\hkq\dao` ，修改`db-config.properties`如下： 

  ```
  jdbc-driver=com.mysql.jdbc.Driver
  jdbc-url=jdbc:mysql://localhost:3306/TinyDatabase?useSSL=false
  jdbc-name=数据库用户
  jdbc-pass=用户密码
  ```

* **测试：** 
开启tomcat服务，在浏览器中输入`http://localhost:8080/TinyWebsite/login` 即可看到项目登录界面。

#### 在Linux服务器部署[To Do]

### 快速了解项目

#### 导入源代码
在Eclipse中依次选择`File -> Import -> Existing Project into Workspace` 选中`TinyWebsite`文件夹导入。

若需要运行项目，参考 在Windows部署 创建数据库并修改`db_condb-config.properties`

#### 项目结构

##### MVC
![](https://github.com/hkq-github/TinyWebsite/blob/master/Documents/imgs/readme_md/mvc.jpg)

##### 与MVC相关的包
```
Controller层：
src/
├── com.hkq.controller.admin
├── com.hkq.controller.user

Model层：
src
├── com.hkq.model
├── com.hkq.dao
├── com.hkq.services

View层：
WebContent/WEB-INF
├──	admin						管理员相关的界面
├──	client						用户相关的界面
├── error.jsp					错误处理界面
├── style.jsp					页面中的Css样式
```

#### 其他一些类
```
其他：
src/
├──	com.hkq.filter 						过滤器	
├──		LoginFilter.java				登录过滤器
├──		PriviligeFilter.java			权限过滤器
├──		CharacterEncodingFilter.java	 字符编码转换过滤器
├──	
├──	com.hkq.listener					监听器
├──		OnlineUserCount.java			在线人数统计监听器
├──	
├──	com.hkq.wrapper						包装器
├──		MyCharacterEncodingRequest.java	  字符编码包装器，与监听器配合使用，统一全站字符
├──	
├── com.hkq.util						工具类
├──		CookieSessionParam.java			统一了加入Session和Cookie的参数名和值
├──		FormParam.java				    统一View向Controller传递的参数名和值
├──		DigestUtils.java				MD5 or SHA加密工具类
├──		Privilege.java					权限类，根据用户类型和请求的servlet路径判断是否允许访问
├──		UserPaging.java					User分页对象
├──		ValidateUserInfo.java			对用户信息如用户名、密码、邮箱等等验证
```
