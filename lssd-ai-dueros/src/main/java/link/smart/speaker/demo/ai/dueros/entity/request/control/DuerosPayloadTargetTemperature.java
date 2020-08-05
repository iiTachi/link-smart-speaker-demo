package link.smart.speaker.demo.ai.dueros.entity.request.control;

import lombok.Data;

/**
 * @author mylitboy
 * @date 2020/6/22
 */
@Data
public class DuerosPayloadTargetTemperature {
    /**
     * 温度值
     */
    int value;

    /**
     * 温度计量单位：CELSIUS(摄氏温度)和FAHRENHEIT(华氏温度)；默认使用CELSIUS
     */
    String scale;
}
