package link.smart.speaker.demo.ai.dueros;

import link.smart.speaker.demo.ai.dueros.entity.DuerosHeader;
import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequest;
import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponse;
import link.smart.speaker.demo.ai.dueros.service.ControlService;
import link.smart.speaker.demo.ai.dueros.service.DiscoverService;
import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.common.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DuerOS 对接Controller类
 *
 * @author mylitboy
 * @date 2020/5/29
 */
@RestController
@Slf4j
public class DuerosController {

    @Autowired
    DiscoverService discoverService;

    @Autowired
    ControlService controlService;

    @PostMapping("/dueros")
    public DuerosResponse dueros(@RequestBody DuerosRequest duerosRequest) {
        log.info("----------------------- DuerOS IN -----------------------");
        // 请求的数据
        System.out.println("DuerOS Request：" + duerosRequest);

        // TODO 判断用户token。
        // dueros用户ID：duerosRequest.getPayload().getOpenUid();
        // OAuth用户Token：duerosRequest.getPayload().getAccessToken();
        UserInfo user = new UserInfo();
        user.setOpenUid(duerosRequest.getPayload().getOpenUid());

        DuerosResponse response = new DuerosResponse();
        DuerosHeader header = new DuerosHeader();
        header.setMessageId(duerosRequest.getHeader().getMessageId());
        header.setNamespace(duerosRequest.getHeader().getNamespace());
        header.setPayloadVersion(duerosRequest.getHeader().getPayloadVersion());
        response.setHeader(header);

        switch (duerosRequest.getHeader().getNamespace()) {
            case "DuerOS.ConnectedHome.Discovery":
                discoverService.initUser(user);
                // 发现设备
                // TODO 获取用户信息。
                // TODO 根据用户信息，获取用户列表。
                switch (duerosRequest.getHeader().getName()) {
                    case "DiscoverAppliancesRequest":
                        header.setName("DiscoverAppliancesResponse");
                        response.setPayload(discoverService.discover(duerosRequest));
                        log.info("DuerOS response:" + response);
                        break;
                    default:
                        break;
                }
                break;
            case "DuerOS.ConnectedHome.Control":
                // 控制设备
                // 获取设备信息
                // 设备ID：duerosRequest.getPayload().getAppliance().getApplianceId();
                DeviceInfo device = DeviceInfo.initById(duerosRequest.getPayload().getAppliance().getApplianceId());
                controlService.initDevice(device);
                switch (duerosRequest.getHeader().getName()) {
                    case "TurnOnRequest":
                        header.setName("TurnOnConfirmation");
                        // 打开操作
                        controlService.turnOn(duerosRequest.getPayload());
                        break;
                    case "TurnOffRequest":
                        header.setName("TurnOffConfirmation");
                        // 关闭操作
                        controlService.turnOff(duerosRequest.getPayload());
                        break;
                    case "SetFanSpeedRequest":
                        header.setName("SetFanSpeedConfirmation");
                        // 风速
                        controlService.setFanSpeed(duerosRequest.getPayload());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        log.info("----------------------- DuerOS OUT -----------------------");
        return response;
    }
}
