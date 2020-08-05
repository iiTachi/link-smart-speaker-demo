package link.smart.speaker.demo.ai.dueros.entity.request.control;

import lombok.Data;

/**
 * @author mylitboy
 * @date 2020/7/27
 */
@Data
public class DuerosPayloadMode {

    /**
     * 设备类型
     */
    String deviceType;
    /**
     * 设备模式
     * <p>
     * 与设备类型相关，不同设备类型的模式不同
     * <p>
     * OVEN	--烤箱
     * FERMENTATION：发酵
     * DOUBLE_TUBE：双管
     * THAW：解冻
     * <p>
     * RANGE_HOOD-- 抽油烟机
     * HIGH：高速
     * MIDDLE：中速
     * LOW：低速
     * STIR_FRY：爆炒
     *
     */
    String value;
}
