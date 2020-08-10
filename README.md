# link-smart-speaker-demo

## 介绍

该项目是基于`SpringBoot`实现的，小度音箱DuerOS、天猫精灵AliGenie 智能家居 技能对接demo，模拟OAuth2、设备云功能。

> 注意：  
> 该项目，只是对接了DuerOS及AliGenie的智能家居技能。

## 项目模块说明

| 模块名 | 模块描述 |
| ---- | ---- |
|lssd-ai-aligenie|AI天猫精灵模块|
|lssd-ai-dueros|AI小度音箱模块|
|lssd-common|公共模块|
|lssd-device-mock|设备Mock数据模块|
|lssd-oauth|认证模块|
|lssd-starter|启动模块项目启动入口|

## 使用说明

> 注意：  
> 项目使用了Lombok，建议在IDE中安装Lombok插件。

### 1. 修改application.yml配置文件中，OAuth2的Client信息

```yaml
## OAuth2 配置
oauth2:
  # TODO DuerOS 授权配置
  clients[0]:
    client-id: DuerOSClientId
    client-secret: DuerOSClientSecret
    redirect-uri: https://xiaodu.baidu.com/saiya/auth/....
  # TODO AliGenie 授权配置
  clients[1]:
    client-id: AliGenieClientId
    client-secret: AliGenieClientSecret
    redirect-uri: https://open.bot.tmall.com/oauth/callback
```
**OAuth2相关地址信息：**

- 授权地址： https://xxx.xxx.xxx:10030/oauth/authorize
- Token地址： https://xxx.xxx.xxx:10030/oauth/token
- ClientId：`DuerOSClientId`/`AliGenieClientId`
- ClientSecret：`DuerOSClientSecret`/`AliGenieClientSecret`

**设备云/网关地址信息：**

- 小度设备云地址： https://xxx.xxx.xxx:10030/dueros
> 该地址在`lssd-ai-dueros`模块的`DuerosController`类中配置
- 天猫精灵网关地址： https://xxx.xxx.xxx:10030/aligenie
> 该地址在`lssd-ai-aligenie`模块的`AliGenieController`类中配置

**DuerOS平台上填写方法如下图：**

![在这里插入图片描述](https://images.gitee.com/uploads/images/2020/0805/182255_5137b392_753460.png)

**AliGenie平台上填写方法如下图：**

![在这里插入图片描述](https://images.gitee.com/uploads/images/2020/0805/180745_4f52160c_753460.png)


### 2. 替换https.jks证书

由于DuerOS及AliGenie平台要求，必须使用https证书。

> 如果是非jks格式的证书，可以使用OpenSSL工具合成，也可在线合成JKS。
>
> 在线合成参考地址：  
> https://www.myssl.cn/tools/merge-jks-cert.html  
> https://csr.chinassl.net/convert-ssl.html


### 3. 修改application.yml配置文件中，https证书信息** 

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

### 4. 修改application.yml配置文件中，AI模块信息

```
#AI模块配置
ai:
  dueros:
    notify-bot-id: xxxxxxxxx-xxxxxx-xxxxxxx-xxxx-xxxxxx
    # 智能家居协议，技能ID
    # 用于有设备列表更新时，通知小度
```

### 5. 项目运行

> 这是一个SpringBoot项目，可以参照网上的打包部署方法。

#### IDE中运行

运行`lssd-starter`模块的`link.smart.speaker.demo.LinkSmartSpeakerDemoApplication`类启动项目。

#### Maven打包运行

```bash
# Maven打包
cd ./link-smart-speaker-demo
mvn install

# 打包完，jar包在`/lssd-starter/target/`目录中。
cd ./lssd-starter/target/
java -jar lssd-starter-0.0.1.jar
```

### 6. 其他 

- 部署配置完成后，点击小度配置中的授权，使用`admin`&`admin`登录进行授权。
- 端口号在代码中（Application类）https跳转http的时候也写了，如果需要修改也一并修改。
- 代码中，方便DEMO，写了简单的OAuth功能，不需要可以删除。
- 该项目中，使用了抽油烟机和净水器品类为示例。
