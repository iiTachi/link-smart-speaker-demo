package link.smart.speaker.demo.ai.dueros;

import link.smart.speaker.demo.ai.dueros.constants.DuerosConstants;
import link.smart.speaker.demo.ai.dueros.entity.DuerosHeader;
import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequest;
import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponse;
import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDeviceAttribute;
import link.smart.speaker.demo.ai.dueros.entity.response.query.DuerosAttributesPayload;
import link.smart.speaker.demo.ai.dueros.entity.response.query.DuerosRunningStatePayload;
import link.smart.speaker.demo.ai.dueros.entity.response.query.DuerosTemperatureReadingPayload;
import link.smart.speaker.demo.ai.dueros.service.DuerosControlService;
import link.smart.speaker.demo.ai.dueros.service.DuerosDiscoverService;
import link.smart.speaker.demo.ai.dueros.service.DuerosQueryService;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.ErrorCode;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    DuerosDiscoverService duerosDiscoverService;

    @Autowired
    DuerosControlService duerosControlService;

    @Autowired
    DuerosQueryService duerosQueryService;

    @PostMapping("/dueros")
    public DuerosResponse dueros(@RequestBody DuerosRequest duerosRequest) {
        log.info("----------------------- DuerOS IN -----------------------");
        // 请求的数据
        log.info("DuerOS Request：" + duerosRequest);

        // TODO 判断用户token。
        String token = duerosRequest.getPayload().getAccessToken();
        log.info("User Token：" + token);

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
                duerosDiscoverService.initUser(user);
                // 发现设备
                // TODO 获取用户信息。
                // TODO 根据用户信息，获取用户列表。
                switch (duerosRequest.getHeader().getName()) {
                    case "DiscoverAppliancesRequest":
                        header.setName("DiscoverAppliancesResponse");
                        response.setPayload(duerosDiscoverService.discover(duerosRequest));
                        log.info("DuerOS response:" + response);
                        break;
                    default:
                        break;
                }
                break;
            default:
                // 获取设备信息
                DeviceInfo device = DeviceInfo.initById(duerosRequest.getPayload().getAppliance().getApplianceId().split("__")[0]);
                // 在附加信息中，保存ProductKey
                device.setProductKey(duerosRequest.getPayload().getAppliance().getAdditionalApplianceDetails().get(DuerosConstants.ADDITIONAL_KEY_PRODUCT_KEY));
                device.setDevicePlatform(duerosRequest.getPayload().getAppliance().getAdditionalApplianceDetails().get(DuerosConstants.ADDITIONAL_KEY_DEVICE_PLATFORM));

                Result controlResult = Result.success();
                switch (duerosRequest.getHeader().getNamespace()) {
                    case "DuerOS.ConnectedHome.Control":
                        // 控制设备
                        duerosControlService.initDevice(device);

                        controlResult = analyzeControl(duerosRequest, header);
                        break;
                    case "DuerOS.ConnectedHome.Query":
                        // 查询设备
                        duerosQueryService.initDevice(device);

                        DuerosResponsePayload payload = analyzeQuery(duerosRequest, header);
                        response.setPayload(payload);
                        break;
                    default:
                        break;
                }
                switch (controlResult.getCode()) {
                    case ErrorCode.DEVICE_OFFLINE:
                        header.setName("TargetOfflineError");
                        break;
                    case ErrorCode.SUCCESS:
                    default:
                        break;
                }
                break;
        }
        log.info("----------------------- DuerOS OUT -----------------------");
        return response;
    }

    /**
     * 解析控制命令
     *
     * @param duerosRequest
     * @param header
     * @return
     */
    private Result analyzeControl(DuerosRequest duerosRequest, DuerosHeader header) {
        Result controlResult = Result.success();
        switch (duerosRequest.getHeader().getName()) {
            case "TurnOnRequest":
                header.setName("TurnOnConfirmation");
                // 打开操作
                controlResult = duerosControlService.turnOn(duerosRequest.getPayload());
                break;
            case "TurnOffRequest":
                header.setName("TurnOffConfirmation");
                // 关闭操作
                controlResult = duerosControlService.turnOff(duerosRequest.getPayload());
                break;
            case "SetFanSpeedRequest":
                header.setName("SetFanSpeedConfirmation");
                // 风速
                controlResult = duerosControlService.setFanSpeed(duerosRequest.getPayload());
                break;
            case "SetTemperatureRequest":
                header.setName("SetTemperatureConfirmation");
                // 温度
                controlResult = duerosControlService.setTemperature(duerosRequest.getPayload());
                break;
            case "StartUpRequest":
                // 启动
                // 启动烤箱、启动蒸箱
                header.setName("StartUpConfirmation");
                break;
            case "PauseRequest":
                // 暂停
                // 暂停烤箱，暂停蒸箱
                header.setName("PauseConfirmation");
                break;
            case "SetModeRequest":
                // 模式
                // 烤箱上烤模式
                header.setName("SetModeConfirmation");
                break;
            default:
                break;
        }
        return controlResult;
    }

    /**
     * 解析查询命令
     *
     * @param duerosRequest
     * @param header
     * @return
     */
    private DuerosResponsePayload analyzeQuery(DuerosRequest duerosRequest, DuerosHeader header) {
        Result controlResult = Result.success();
        switch (duerosRequest.getHeader().getName()) {
            case "GetFanSpeedRequest":
                header.setName("GetFanSpeedResponse");
                // 查询风速
                controlResult = duerosQueryService.queryFanSpeed(duerosRequest.getPayload());
                if (controlResult.getCode() == ErrorCode.SUCCESS) {
                    DuerosAttributesPayload attributesPayload = new DuerosAttributesPayload();
                    List<DuerosDeviceAttribute> duerosDeviceAttributeList = new ArrayList<>();
                    DuerosDeviceAttribute duerosDeviceAttribute = new DuerosDeviceAttribute();
                    duerosDeviceAttribute.setName("fanSpeed");
                    duerosDeviceAttribute.setValue(controlResult.getData());
                    duerosDeviceAttribute.setTimestampOfSample(System.currentTimeMillis());
                    duerosDeviceAttribute.setUncertaintyInMilliseconds(10);
                    duerosDeviceAttribute.setScale("");
                    duerosDeviceAttributeList.add(duerosDeviceAttribute);
                    attributesPayload.setAttributes(duerosDeviceAttributeList);
                    return attributesPayload;
                }
                break;
            case "GetRunningStatusRequest":
                header.setName("GetRunningStatusResponse");
                DuerosRunningStatePayload payload = new DuerosRunningStatePayload();
                Map<String, String> runningState = new HashMap<>(1);
                runningState.put("value", "working");
                payload.setRunningState(runningState);
                return payload;
            case "GetTemperatureReadingRequest":
                // 查询温度
                header.setName("GetTemperatureReadingResponse");
                controlResult = duerosQueryService.queryTemperature(duerosRequest.getPayload());
                if (controlResult.getCode() == ErrorCode.SUCCESS) {
                    DuerosTemperatureReadingPayload temperatureReadingPayload = new DuerosTemperatureReadingPayload();
                    Map<String, Object> temperatureReading = new HashMap<>(2);
                    temperatureReading.put("value", controlResult.getData());
                    temperatureReading.put("scale", "CELSIUS");
                    temperatureReadingPayload.setTemperatureReading(temperatureReading);
                    return temperatureReadingPayload;
                }
                break;
            default:
                break;
        }
        return null;
    }
}
