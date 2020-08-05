package link.smart.speaker.demo.ai.aligenie;

import link.smart.speaker.demo.ai.aligenie.constants.AliGenieConstants;
import link.smart.speaker.demo.ai.aligenie.entity.AliGenieHeader;
import link.smart.speaker.demo.ai.aligenie.entity.request.AliGenieRequest;
import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieProperties;
import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieResponse;
import link.smart.speaker.demo.ai.aligenie.entity.response.control.AliGenieControlResponsePayload;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieControlService;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieDiscoverService;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieQueryService;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.ErrorCode;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/5
 */
@RestController
@Slf4j
public class AliGenieController {

    @Autowired
    AliGenieDiscoverService discoverService;
    @Autowired
    AliGenieControlService controlService;
    @Autowired
    AliGenieQueryService queryService;

    @PostMapping("/aligenie")
    public AliGenieResponse aliGenie(@RequestBody AliGenieRequest aliGenieRequest, HttpServletRequest request) {
        log.info("----------------------- AliGenie IN -----------------------");
        // 请求的数据
        log.info("AliGenie Request：" + aliGenieRequest);

        UserInfo user = new UserInfo();
        user.setOpenUid(aliGenieRequest.getPayload().getAccessToken());

        AliGenieResponse response = new AliGenieResponse();
        AliGenieHeader header = new AliGenieHeader();
        header.setMessageId(aliGenieRequest.getHeader().getMessageId());
        header.setNamespace(aliGenieRequest.getHeader().getNamespace());
        header.setPayLoadVersion(aliGenieRequest.getHeader().getPayLoadVersion());
        response.setHeader(header);

        switch (aliGenieRequest.getHeader().getNamespace()) {
            case "AliGenie.Iot.Device.Discovery":
                discoverService.initUser(user);
                switch (aliGenieRequest.getHeader().getName()) {
                    case "DiscoveryDevices":
                        header.setName("DiscoveryDevicesResponse");
                        response.setPayload(discoverService.discover());
                    default:
                        break;
                }
                break;
            default:
                DeviceInfo device = DeviceInfo.initById(aliGenieRequest.getPayload().getDeviceId().split("__")[0]);
                // 在附加信息中，保存ProductKey
                device.setProductKey(aliGenieRequest.getPayload().getExtensions().get(AliGenieConstants.EXTENSION_KEY_PRODUCT_KEY));
                device.setDevicePlatform(aliGenieRequest.getPayload().getExtensions().get(AliGenieConstants.EXTENSION_KEY_DEVICE_PLATFORM));

                Result controlResult = Result.success();
                switch (aliGenieRequest.getHeader().getNamespace()) {
                    case "AliGenie.Iot.Device.Control":
                        // 控制操作
                        controlService.initDevice(device);

                        controlResult = analyzeControl(aliGenieRequest, header);
                        break;
                    case "AliGenie.Iot.Device.Query":
                        // 查询操作
                        queryService.initDevice(device);

                        List<AliGenieProperties> propertiesList = analyzeQuery(aliGenieRequest, header);
                        response.setProperties(propertiesList);
                        break;
                    default:
                        break;
                }
                switch (controlResult.getCode()) {
                    case ErrorCode.SUCCESS:
                        AliGenieControlResponsePayload payload = new AliGenieControlResponsePayload();
                        payload.setDeviceId(aliGenieRequest.getPayload().getDeviceId());
                        response.setPayload(payload);
                        break;
                    case ErrorCode.DEVICE_OFFLINE:
                        header.setName("TargetOfflineError");
                        break;
                    default:
                        header.setName("ErrorResponse");
                        break;
                }
        }

        log.info("----------------------- AliGenie OUT -----------------------");
        return response;
    }

    /**
     * 解析控制命令
     *
     * @param aliGenieRequest
     * @param header
     * @return
     */
    private Result analyzeControl(AliGenieRequest aliGenieRequest, AliGenieHeader header) {
        Result controlResult = Result.success();

        switch (aliGenieRequest.getHeader().getName()) {
            case "TurnOn":
                header.setName("TurnOnResponse");
                // 打开操作
                if (AliGenieConstants.ATTR_SYSTEM_POWER.equals(aliGenieRequest.getPayload().getAttribute())) {
                    // 打开操作
                    controlResult = controlService.turnOn(aliGenieRequest.getPayload());
                }
                break;
            case "TurnOff":
                header.setName("TurnOffResponse");
                // 关闭操作
                controlResult = controlService.turnOff(aliGenieRequest.getPayload());
                break;
            case "AdjustUpWindSpeed":
                // 风速
                header.setName("AdjustUpWindSpeedResponse");
                controlResult = controlService.setFanSpeed(aliGenieRequest.getPayload());
                break;
            case "AdjustDownWindSpeed":
                header.setName("AdjustDownWindSpeedResponse");
                // 风速
                controlResult = controlService.setFanSpeed(aliGenieRequest.getPayload());
                break;
            case "SetWindSpeed":
                header.setName("SetWindSpeedResponse");
                // 风速
                controlResult = controlService.setFanSpeed(aliGenieRequest.getPayload());
                break;
            case "SetTemperature":
                header.setName("SetTemperatureResponse");
                // 温度
                controlResult = controlService.setTemperature(aliGenieRequest.getPayload());
                break;
            case "SetLocalRecipe":
                header.setName("SetLocalRecipeResponse");
                // 本地食谱 烤箱上烤模式
                controlResult = controlService.setLocalRecipe(aliGenieRequest.getPayload());
                break;
            case "SetMode":
                header.setName("SetModeResponse");
                // 模式设置 烤箱发酵模式
                controlResult = controlService.setMode(aliGenieRequest.getPayload());
                break;
            default:
                break;
        }
        return controlResult;
    }

    /**
     * 解析查询命令
     *
     * @param aliGenieRequest
     * @param header
     * @return
     */
    private List<AliGenieProperties> analyzeQuery(AliGenieRequest aliGenieRequest, AliGenieHeader header) {
        List<AliGenieProperties> propertiesList = new ArrayList<>();
        switch (aliGenieRequest.getHeader().getName()) {
            case "Query":
                header.setName("QueryResponse");
                // 查询操作
                Result queryResult = queryService.queryFanSpeed(aliGenieRequest.getPayload());

                AliGenieProperties aliGenieProperties = new AliGenieProperties();
                aliGenieProperties.setName("windspeed");
                aliGenieProperties.setValue(queryResult.getData().toString());
                propertiesList.add(aliGenieProperties);
                break;
            default:
                break;
        }
        return propertiesList;
    }
}
