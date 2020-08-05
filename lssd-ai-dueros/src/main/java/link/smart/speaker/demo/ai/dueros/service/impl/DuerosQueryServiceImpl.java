package link.smart.speaker.demo.ai.dueros.service.impl;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;
import link.smart.speaker.demo.ai.dueros.service.DuerosQueryService;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mylitboy
 * @date 2020/6/13
 */
@Service
@Slf4j
public class DuerosQueryServiceImpl implements DuerosQueryService {
    @Autowired
    DeviceService deviceService;

    @Override
    public Result queryFanSpeed(DuerosRequestPayload payload) {
        return deviceService.querySpeed();
    }

    @Override
    public Result queryTemperature(DuerosRequestPayload payload) {
        return deviceService.queryTemperature();
    }

    @Override
    public void initUser(UserInfo user) {

    }

    @Override
    public void initDevice(DeviceInfo device) {

    }
}
