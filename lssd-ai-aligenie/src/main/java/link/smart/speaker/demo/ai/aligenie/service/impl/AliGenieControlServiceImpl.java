package link.smart.speaker.demo.ai.aligenie.service.impl;

import link.smart.speaker.demo.ai.aligenie.entity.request.AliGenieRequestPayload;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieControlService;
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
public class AliGenieControlServiceImpl implements AliGenieControlService {

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
    public Result turnOn(AliGenieRequestPayload payload) {
        return deviceService.openPower();
    }

    @Override
    public Result turnOff(AliGenieRequestPayload payload) {
        return deviceService.closePower();
    }

    @Override
    public Result setFanSpeed(AliGenieRequestPayload payload) {
        return deviceService.setSpeed(Integer.valueOf(payload.getValue()));
    }

    @Override
    public Result setTemperature(AliGenieRequestPayload payload) {
        return deviceService.setTemperature(Integer.valueOf(payload.getValue()));
    }

    @Override
    public Result setLocalRecipe(AliGenieRequestPayload payload) {
        return Result.success();
    }

    @Override
    public Result setMode(AliGenieRequestPayload payload) {
        return Result.success();
    }

}
