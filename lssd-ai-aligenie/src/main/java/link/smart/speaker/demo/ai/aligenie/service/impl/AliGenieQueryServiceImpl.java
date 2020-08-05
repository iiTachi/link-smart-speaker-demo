package link.smart.speaker.demo.ai.aligenie.service.impl;

import link.smart.speaker.demo.ai.aligenie.entity.request.AliGenieRequestPayload;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieQueryService;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mylitboy
 * @date 2020/6/17
 */
@Service
public class AliGenieQueryServiceImpl implements AliGenieQueryService {
    @Autowired
    DeviceService deviceService;

    @Override
    public void initUser(UserInfo user) {
        deviceService.initUser(user);
    }

    @Override
    public void initDevice(DeviceInfo device) {
        deviceService.initDevice(device);
    }

    @Override
    public Result queryFanSpeed(AliGenieRequestPayload payload) {
        return deviceService.querySpeed();
    }
}
