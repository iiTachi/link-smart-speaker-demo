package link.smart.speaker.demo.ai.dueros.service.impl;

import link.smart.speaker.demo.ai.dueros.constants.DuerosConstants;
import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;
import link.smart.speaker.demo.ai.dueros.service.DuerosControlService;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
@Service
@Slf4j
public class DuerosControlServiceImpl implements DuerosControlService {
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
    public Result turnOn(DuerosRequestPayload payload) {
        if (payload.getFunction() == null) {
            // 打开设备
            return deviceService.openPower();
        } else if (DuerosConstants.DUEROS_SUB_DEVICE_LIGHT.equals(payload.getFunction())) {
            // 打开照明
            return deviceService.openLightPower();
        }
        return Result.error();
    }

    @Override
    public Result turnOff(DuerosRequestPayload payload) {
        if (payload.getFunction() == null) {
            // 关闭设备
            return deviceService.closePower();
        } else if (DuerosConstants.DUEROS_SUB_DEVICE_LIGHT.equals(payload.getFunction())) {
            // 关闭照明
            return deviceService.closeLightPower();
        }
        return Result.error();
    }

    @Override
    public Result setFanSpeed(DuerosRequestPayload payload) {
        int speedValue = payload.getFanSpeed().getValue();
        String speedLevel = payload.getFanSpeed().getLevel();
        if (speedLevel != null) {
            switch (speedLevel) {
                case "high":
                    speedValue = 3;
                    break;
                case "middle":
                    speedValue = 2;
                    break;
                case "low":
                    speedValue = 1;
                    break;
                default:
                    break;
            }
        }
        return deviceService.setSpeed(speedValue);
    }

    @Override
    public Result setTemperature(DuerosRequestPayload payload) {
        int temperatureValue = payload.getTargetTemperature().getValue();
        return deviceService.setTemperature(temperatureValue);
    }
}
