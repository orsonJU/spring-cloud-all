# Spring Cloud Config - Client

## 启动时候获取配置

在config-server中我们可以知道如何配置一个config server，但是config server提供的API并不需要我们自己另外封装代码来调用，可以通过给其他service进行配置，实现自动的加载。

> 注意，想要服务自动获取配置，必须先确认自己的classpath下有spring-cloud-config-client的依赖，否则服务不会去主动获取配置


### 添加配置
```bash
# 某个微服务启动时候需要加载配置，如下的配置会在启动的时候组装url:https://host:port/app1-dev.properties来向config服务器读取配置
spring.application.name=app1
spring.profiles.active=dev


# 对应配置文件中的<application>
spring.cloud.config.name=app1
# 告诉微服务配置中心的地址，默认是http://localhost:8888
spring.cloud.config.uri=http://localhost:8888
# 告诉微服务需要去获取的profile配置是哪个
spring.cloud.config.profile=dev
```

* `spring.application.name`和`spring.cloud.config.name`的区别：前者是自己注册到eureka server上使用到名字，后者是去问config server提供到名字；正常来说两者应该相等，所以可以用下面到写法：

```bash
spring.application.name=app1
spring.cloud.config.name=${spring.application.name}
```

* `spring.profiles.active`和`spring.cloud.config.profile`的区别：前者是spring容器自己初始化bean时候依赖的profile，后者是去问config server提供的profile，正常来说它们也应该一样，可以使用和name相同的写法。



