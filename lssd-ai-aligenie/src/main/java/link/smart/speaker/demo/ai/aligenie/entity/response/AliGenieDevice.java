package link.smart.speaker.demo.ai.aligenie.entity.response;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieDevice {
    /**
     * 设备ID
     */
    String deviceId;
    /**
     * 名称
     */
    String deviceName;
    /**
     * 设备类型
     */
    String deviceType;
    /**
     * 位置
     */
    String zone;
    /**
     * 品牌
     */
    String brand;
    /**
     * 型号
     */
    String model;
    /**
     * 产品icon(https协议的url链接),像素最好160*160
     */
    String icon;
    /**
     * 返回当前设备支持的属性状态列表，产品支持的属性列表
     */
    List<AliGenieProperties> properties;
    /**
     * 产品支持的操作(注：包括支持的查询操作)
     */
    List<String> actions;
    /**
     * 产品扩展属性,为空返回null或者不返回该字段
     */
    Map<String, String> extensions = new HashMap<>();

}
