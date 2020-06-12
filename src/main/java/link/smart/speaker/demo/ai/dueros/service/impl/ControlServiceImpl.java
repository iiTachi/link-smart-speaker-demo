package link.smart.speaker.demo.ai.dueros.service.impl;

import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequestPayload;
import link.smart.speaker.demo.ai.dueros.service.ControlService;
import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.device.DeviceService;
import link.smart.speaker.demo.common.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
@Service
@Slf4j
public class ControlServiceImpl implements ControlService {
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
    public boolean turnOn(DuerosRequestPayload payload) {
        if (payload.getFunction() == null) {
            // 打开集成灶
            return deviceService.openPower();
        } else if ("light".equals(payload.getFunction())) {
            // 打开照明
            return deviceService.openLightPower();
        }
        return false;
    }

    @Override
    public boolean turnOff(DuerosRequestPayload payload) {
        if (payload.getFunction() == null) {
            // 关闭集成灶
            return deviceService.closePower();
        } else if ("light".equals(payload.getFunction())) {
            // 关闭照明
            return deviceService.closeLightPower();
        }
        return false;
    }

    @Override
    public boolean setFanSpeed(DuerosRequestPayload payload) {
        int speedValue = payload.getFanSpeed().getValue();
        String speedLevel = payload.getFanSpeed().getLevel();
        if (speedLevel != null && !"null".equals(speedLevel)) {
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
                    speedValue = 0;
                    break;
            }
        }

        return deviceService.setSpeed(speedValue);
    }
}
