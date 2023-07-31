## 项目搭建

后端采用自己搭建的 Spring Boot 项目模板快速构建初始 web 项目；前端采用 Ant Design Pro 脚手架进行搭建。

SpringBoot 项目初始化模板：[https://gitee.com/dream-deeply-tyu/easy-web](https://gitee.com/dream-deeply-tyu/easy-web)

前端代码仓库地址：[https://gitee.com/dream-deeply-tyu/cmapi-frontend](https://gitee.com/dream-deeply-tyu/cmapi-frontend)

## 系统架构图

![API-开放平台架构图](https://cdn.jsdelivr.net/gh/cmty256/imgs-blog@main/project/API-开放平台架构图.61avss2n9ds0.jpg)

## 项目启动

一、进入 redis 目录下 `cmd` 启动：

1. 开启 redis 服务（这是 windows 的命令）

```bash
redis-server.exe  redis.windows.conf
```

2. 双击打开 redis-cli.exe（输入密码，和 `application.yml` 文件中的配置一致）

```bash
auth password
```



二、Nacos 的 bin 目录下静态启动

```bash
startup.cmd -m standalone
```

