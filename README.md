# link-smart-speaker-demo

#### 介绍

小度音箱DuerOS对接demo，模拟OAuth2、设备云功能。

> 注意：  
> 该项目，只是对接了DuerOS的智能家居技能。

#### 使用说明

> 注意：  
> 项目使用了Lombok，建议在IDE中安装Lombok插件。

1. 修改application.yml配置文件中，OAuth2的Client信息。

```yaml
## OAuth2 配置
oauth2:
  # TODO DuerOS 授权配置
  clients[0]:
    client-id: DuerOSClientId
    client-secret: DuerOSClientSecret
    redirect-uri: https://xiaodu.baidu.com/saiya/auth/....
    scope: all
```

2. 替换https.jks证书。

> 如果是非jks格式的证书，可以使用OpenSSL工具合成，也可在线合成JKS。
> 
> 在线合成参考地址：  
> https://www.myssl.cn/tools/merge-jks-cert.html  
> https://csr.chinassl.net/convert-ssl.html


3. 修改application.yml配置文件中，https证书信息。

```
# 服务配置
server:
  port: 10030
  # TODO SSL证书配置
  ssl:
    key-store: classpath:https.jks    # 配置SSL证书地址
    key-store-password: keyPassword     # jks证书密钥
    key-store-type: JKS             # 类型
    key-alias: keyAlias           # 证书alias
```

4. 其他

- 端口号在代码中，Application类中，https跳转http的时候也写了，如果需要修改也一并修改。
- 代码中，方便DEMO，写了简单的OAuth功能，不需要可以删除。

