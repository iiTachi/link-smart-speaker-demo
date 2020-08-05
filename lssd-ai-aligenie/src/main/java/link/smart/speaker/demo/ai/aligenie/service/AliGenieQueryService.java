package link.smart.speaker.demo.ai.aligenie.service;

import link.smart.speaker.demo.ai.aligenie.entity.request.AliGenieRequestPayload;
import link.smart.speaker.demo.common.entity.Result;

/**
 * @author mylitboy
 * @date 2020/6/17
 */
public interface AliGenieQueryService extends AliGenieService{
    /**
     * 查询风速
     *
     * @param payload
     * @return
     */
    Result queryFanSpeed(AliGenieRequestPayload payload);
}
