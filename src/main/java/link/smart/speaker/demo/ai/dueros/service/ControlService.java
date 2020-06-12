package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;

/**
 * 控制类消息处理Service
 *
 * @author mylitboy
 * @date 2020/6/10
 */
public interface ControlService extends DuerosService {
    /**
     * 打开操作
     *
     * @param payload
     * @return
     */
    boolean turnOn(DuerosRequestPayload payload);

    /**
     * 关闭操作
     *
     * @param payload
     * @return
     */
    boolean turnOff(DuerosRequestPayload payload);

    /**
     * 设置风速
     *
     * @param payload
     * @return
     */
    boolean setFanSpeed(DuerosRequestPayload payload);
}
