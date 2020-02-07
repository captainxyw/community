# Captainxyw社区

## 项目介绍
这是一个问题、资料和资源的论坛。现有功能提问、回复、通知。

## 功能模块
### 登录模块
使用GitHub的GitHub OAuth实现第三方登录功能，获取用户在GitHub社区中的信息，生成系统中的用户信息，包括名称、头像等。
### 问题模块
使用Markdown编辑器Editor.md，实现发布问题的功能，问题中涉及到Markdown编辑器对图片的处理，图片资源上传至Ucloud云服务。
### 回复模块
可以对文章进行回复，也可以对回复进行二级回复。更多嵌套的回复都以二级回复形式展示。
### 通知模块
回复都会对被回复的文章或者回复的作者进行通知。
### 搜索模块
可以对论坛中的问题标题或者内容进行搜索。
### 日志模块
项目运行情况都会记录在项目下的logs中的community.log文件中。

## 项目技术架构
### 后端技术：
* Spring Boot
* Spring MVC
* MyBatis
* H2 
* Flyway 
* Git/GitHub
* Maven
* Resulful
* Lombok
### 前端技术：
* HTML
* CSS
* Bootstrap
* Thymleaf
### 容器:
* Tomcat 8
### Markdown编辑器：
* Editor.md

## 系统效果图 
- 使用GitHub OAuth进行登录
![登录界面](https://raw.githubusercontent.com/captainxyw/community/master/img/login-page.png "登录界面")
- 分页查看论坛中的所有问题
![问题列表界面](https://raw.githubusercontent.com/captainxyw/community/master/img/question-list-page.png "问题列表界面")
- 查看问题详情
![问题详情界面](https://raw.githubusercontent.com/captainxyw/community/master/img/question-browse-page.png "问题详情界面")
- 使用Markdown语法发布问题
![问题发布界面](https://raw.githubusercontent.com/captainxyw/community/master/img/question-release-page.png "问题发布界面")
- 查看系统通知（评论相关）
![通知界面](https://raw.githubusercontent.com/captainxyw/community/master/img/notification-page.png "通知界面")
- 评论界面，可以进行二级评论
![评论界面](https://raw.githubusercontent.com/captainxyw/community/master/img/comment-page.png "评论界面")



## 资料
[Spring 文档](https://spring.io/guides)

[Spring Web](https://spring.io/guides/gs/serving-web-content/)

[es](https://elasticsearch.cn/explore)

[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)

[Bootstrap](https://v3.bootcss.com/getting-started/)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)

## 工具
[Git](https://git-scm.com/download)

[Visual Paradigm](https://www.visual-paradigm.com/cn/)

[Flyway](https://flywaydb.org/getstarted/firststeps/maven)

[Lombok](https://projectlombok.org/)


## 脚本
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
```sql
create table USER
(
	ID int auto_increment primary key not null,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);
```