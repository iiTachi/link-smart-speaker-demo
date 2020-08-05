package link.smart.speaker.demo.ai.aligenie.service;

import link.smart.speaker.demo.ai.aligenie.entity.response.discover.AliGenieDiscoverPayload;

/**
 * @author mylitboy
 * @date 2020/6/17
 */
public interface AliGenieDiscoverService extends AliGenieService{

    /**
     * 发现设备
     *
     * @return
     */
    AliGenieDiscoverPayload discover();
}
