package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequest;
import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
public interface DuerosDiscoverService extends DuerosService {


    /**
     * 发现设备
     *
     * @param duerosRequest
     * @return
     */
    DuerosResponsePayload discover(DuerosRequest duerosRequest);
}
