package link.smart.speaker.demo.ai.aligenie.constants;

/**
 * 天猫精灵常量类
 *
 * @author mylitboy
 * @date 2020/6/9
 */
public interface AliGenieConstants {
    /**
     * 属性名--系统开关
     */
    String ATTR_SYSTEM_POWER = "powerstate";
    /**
     * 命名空间--发现
     */
    String NAMESPACE_DISCOVER = "AliGenie.Iot.Device.Discovery";
    /**
     * 指令名--发现指令响应
     */
    String NAME_DISCOVER_RESPONSE = "DiscoveryDevicesResponse";

    /**
     * 设备扩展属性--产品ID
     */
    String EXTENSION_KEY_PRODUCT_KEY = "ProductKey";
    /**
     * 设备扩展属性--设备来源平台
     */
    String EXTENSION_KEY_DEVICE_PLATFORM = "DevicePlatform";
}
