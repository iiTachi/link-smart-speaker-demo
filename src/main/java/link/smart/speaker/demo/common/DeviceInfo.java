package link.smart.speaker.demo.common;

import lombok.Data;

/**
 * 设备信息
 *
 * @author mylitboy
 * @date 2020/6/9
 */
@Data
public class DeviceInfo {
    /**
     * 设备ID
     */
    String deviceId;
    /**
     * 产品Key
     */
    String productKey;
    /**
     * 厂商名称
     */
    String manufacturerName;
    /**
     * 产品名称
     */
    String productName;
    /**
     * 设备描述
     * 小度：设备相关的描述，描述内容提需要提及设备厂商，使用场景及连接方式，长度不超过128个字符。
     */
    String deviceDescription;
    /**
     * 设备别名
     */
    String nickName;
    /**
     * 设备型号
     */
    String model;
    /**
     * 固件版本
     */
    String version;

    /**
     * 根据ID初始化
     *
     * @param deviceId
     * @return
     */
    public static DeviceInfo initById(String deviceId) {
        DeviceInfo device = new DeviceInfo();
        device.setDeviceId(deviceId);
        return device;
    }
}
