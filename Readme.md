1.考试系统有3个jsp页面:
regist.jsp:注册页面，用于用户注册，对于用户在表单输入的考号与密码会进行逻辑判断及通过后台验证考号是否存在，符合规则的考号与密码才能被创建。
index.jsp:登录页面，用于用户登录，对于用户在表单输入的考号与密码会通过后台验证是否正确，正确后才能登录。
examination.jsp:考试页面，若用户不登录直接访问考试页会被后台过滤器拦截而重定向到登录页面。
2.后台使用三层架构:
实现了访问数据库、注册、登录、验证注册与登录、考号与密码回显、过滤器、考试功能。
com.exam.dao:数据访问层

com.exam.domain:封装注册用户与考试用户的类

com.exam.filter:过滤器

com.exam.service:数据服务层，验证用户名是否存在

com.exam.servlet:表示层的servlet

com.exam.utils:jdbc的帮助类