package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;
import link.smart.speaker.demo.common.entity.Result;

/**
 * 控制类消息处理Service
 *
 * @author mylitboy
 * @date 2020/6/10
 */
public interface DuerosQueryService extends DuerosService {
    /**
     * 查询风速
     *
     * @param payload
     * @return
     */
    Result queryFanSpeed(DuerosRequestPayload payload);

    /**
     * 查询温度
     *
     * @param payload
     * @return
     */
    Result queryTemperature(DuerosRequestPayload payload);
}
