# Spring Cloud Config

Spring Cloud Config在分布式系统中，对于服务端和客户端的配置外部化的能力。


## 命名规则

所有交给spring cloud config进行管理的配置文件，都必须以下面的形式进行命名：

`<application>-<profile>.properties` or `<application>-<profile>.yml`

假设git repo上面的master分支上有下面的文件，按照列出来的顺序：
1. app1-dev.properties
2. app1-dev.yml
3. app2-dev.properties
4. app3.properties
5. app3.yml
6. app-name-dev.yml

> 其中第4、5和6是不符合命令规则但，我们后续再讨论它们。


## 基本的API

这里的API提供了获取某个指定配置的能力，上面我们已经了解了spring cloud config对于文件命名的基本规则，那么我们就可以根据规则来获取自己想要的配置文件了。
```bash
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
例子：
```bash
# 获取master分支上的app1-dev的配置，但是是获取.properties还是获取.yml？这里会按照顺序匹配到第一个名字的文件，所以是properties
curl https://host:port/app1/dev

# 获取app1-dev.properties文件，发现哪怕改写成app1-dev.yml也只能获取到位置第一个的app1-dev.properties
curl https://host:port/app1-dev.properties

# 获取master分支上的app2-dev.*配置文件
curl https://host:port/app2/dev/master

# 获取prod分支上的app2-dev.properties配置
curl https://host:port/prod/app2-dev.properties
```

### 其他微服务如何获取配置？
从上面的例子，我们知道如何通过手工的操作去调用API来获取到我们自己想要的数据，但是如果某个微服务需要获取配置的话，是如何配置呢？

> 要知道如何配置，我们必须要明白下面的映射关系:

```bash
spring.application.name -> application
spring.profiles.active -> profile
```

例子：
```bash
# 某个微服务启动时候需要加载配置，如下的配置会在启动的时候组装url:https://host:port/app1-dev.properties来向config服务器读取配置
spring.application.name=app1
spring.profiles.active=dev
```


