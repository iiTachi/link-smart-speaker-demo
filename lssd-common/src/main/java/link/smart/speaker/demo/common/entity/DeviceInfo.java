package link.smart.speaker.demo.common.entity;

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
     * 设备平台
     * 标识从哪个平台发现的设备
     */
    String devicePlatform;
    /**
     * 设备ID
     */
    String deviceId;
    /**
     * 产品Key
     */
    String productKey;
    /**
     * 产品类型
     */
    String productType;
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
     * 品类图片
     */
    String categoryImage;

    /**
     * 设备状态
     * <p>
     * 0：未激活 1：在线 3：离线 8：禁用
     */
    int status;

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
