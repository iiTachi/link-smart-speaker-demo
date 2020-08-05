package link.smart.speaker.demo.ai.dueros.entity.request;

import lombok.Data;

import java.util.Map;

/**
 * 设备信息
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosAppliance {
    /**
     * 设备或场景相关的附加信息
     */
    Map<String, String> additionalApplianceDetails;
    /**
     * 对应的设备ID
     */
    String applianceId;
}
