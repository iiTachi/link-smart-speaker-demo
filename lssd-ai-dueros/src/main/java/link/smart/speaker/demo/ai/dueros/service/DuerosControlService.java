package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;
import link.smart.speaker.demo.common.entity.Result;

/**
 * 控制类消息处理Service
 *
 * @author mylitboy
 * @date 2020/6/10
 */
public interface DuerosControlService extends DuerosService {
    /**
     * 打开操作
     *
     * @param payload
     * @return
     */
    Result turnOn(DuerosRequestPayload payload);

    /**
     * 关闭操作
     *
     * @param payload
     * @return
     */
    Result turnOff(DuerosRequestPayload payload);

    /**
     * 设置风速
     *
     * @param payload
     * @return
     */
    Result setFanSpeed(DuerosRequestPayload payload);

    /**
     * 设置温度
     *
     * @param payload
     * @return
     */
    Result setTemperature(DuerosRequestPayload payload);
}
