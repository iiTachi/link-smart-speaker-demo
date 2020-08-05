package link.smart.speaker.demo.ai.aligenie.service;

import link.smart.speaker.demo.ai.aligenie.entity.request.AliGenieRequestPayload;
import link.smart.speaker.demo.common.entity.Result;

/**
 * @author mylitboy
 * @date 2020/6/17
 */
public interface AliGenieControlService extends AliGenieService {
    /**
     * 打开
     *
     * @param payload
     * @return
     */
    Result turnOn(AliGenieRequestPayload payload);

    /**
     * 关闭
     *
     * @param payload
     * @return
     */
    Result turnOff(AliGenieRequestPayload payload);

    /**
     * 风速调节
     *
     * @param payload
     * @return
     */
    Result setFanSpeed(AliGenieRequestPayload payload);

    /**
     * 设置温度
     *
     * @param payload
     * @return
     */
    Result setTemperature(AliGenieRequestPayload payload);

    /**
     * 本地食谱
     *
     * @param payload
     * @return
     */
    Result setLocalRecipe(AliGenieRequestPayload payload);

    /**
     * 模式设置
     *
     * @param payload
     * @return
     */
    Result setMode(AliGenieRequestPayload payload);
}
