package link.smart.speaker.demo.ai.dueros.service.impl;

import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequest;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.Attribute;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDevice;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDiscoverPayload;
import link.smart.speaker.demo.ai.dueros.service.DiscoverService;
import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.device.DeviceService;
import link.smart.speaker.demo.common.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
@Service
@Slf4j
public class DiscoverServiceImpl implements DiscoverService {

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
    public DuerosResponsePayload discover(DuerosRequest duerosRequest) {
        // TODO 暂时使用固定的用户信息，待OAuth对接后，切换新的。
        List<DeviceInfo> deviceList = deviceService.queryDeviceList();
        log.info("DuerOS discover deviceList:" + deviceList);
        List<DuerosDevice> discoveredAppliances = turnCommonDeviceInfoToDuerosDevice(deviceList);
        DuerosDiscoverPayload payload = new DuerosDiscoverPayload();
        payload.setDiscoveredAppliances(discoveredAppliances);

        List discoveredGroups = new ArrayList<>();
        payload.setDiscoveredGroups(discoveredGroups);
        log.info("DuerOS payload:" + payload);
        return payload;
    }

    /**
     * 通用设备信息转小度设备
     *
     * @param deviceList
     * @return
     */
    private List<DuerosDevice> turnCommonDeviceInfoToDuerosDevice(List<DeviceInfo> deviceList) {

        List<DuerosDevice> discoveredAppliances = new ArrayList<>();

        for (DeviceInfo device : deviceList) {
            DuerosDevice duerosDevice = new DuerosDevice();

            // 设置基本信息
            // 设备ID
            duerosDevice.setApplianceId(device.getDeviceId());
            // 产品描述
            duerosDevice.setFriendlyDescription(device.getDeviceDescription());
            // 设备名称
            duerosDevice.setFriendlyName(device.getNickName());
            // 厂商名称
            duerosDevice.setManufacturerName(device.getManufacturerName());
            // 型号
            duerosDevice.setModelName(device.getModel());
            // 版本
            duerosDevice.setVersion(device.getVersion());
            // 是否可达
            duerosDevice.setReachable(true);

            // TODO 设置小度专用类设备数据。
            // 设备支持的操作。
            duerosDevice.setActions(Arrays.asList("turnOn", "turnOff", "setFanSpeed", "incrementFanSpeed", "decrementFanSpeed", "getRunningStatus"));

            // 设备类型。模块及子模块
            duerosDevice.setApplianceTypes(Arrays.asList("SWITCH", "RANGE_HOOD", "LIGHT"));

            // 设备属性列表
            List<Attribute> duerosDeviceattributes = new ArrayList<>();
            // 必选属性name
            Attribute duerosDeviceAttribute01 = new Attribute();
            duerosDeviceAttribute01.setName("name");
            duerosDeviceAttribute01.setValue(device.getNickName());
            duerosDeviceAttribute01.setTimestampOfSample(System.currentTimeMillis());
            duerosDeviceAttribute01.setUncertaintyInMilliseconds(0);
            duerosDeviceattributes.add(duerosDeviceAttribute01);

            // 必选属性connectivity
            Attribute duerosDeviceAttribute02 = new Attribute();
            duerosDeviceAttribute02.setName("connectivity");
            duerosDeviceAttribute02.setValue("REACHABLE");
            duerosDeviceAttribute02.setTimestampOfSample(System.currentTimeMillis());
            duerosDeviceAttribute02.setUncertaintyInMilliseconds(0);
            duerosDeviceattributes.add(duerosDeviceAttribute02);

            // 扩展属性
            Attribute powerStateAttr = new Attribute();
            powerStateAttr.setName("powerState");
            powerStateAttr.setValue("ON");
            powerStateAttr.setScale("");
            powerStateAttr.setTimestampOfSample(System.currentTimeMillis());
            powerStateAttr.setUncertaintyInMilliseconds(0);
            duerosDeviceattributes.add(powerStateAttr);

            // 扩展属性
            Attribute turnOnStateAttr = new Attribute();
            turnOnStateAttr.setName("turnOnState");
            turnOnStateAttr.setValue("ON");
            turnOnStateAttr.setScale("");
            turnOnStateAttr.setTimestampOfSample(System.currentTimeMillis());
            turnOnStateAttr.setUncertaintyInMilliseconds(10);
            duerosDeviceattributes.add(turnOnStateAttr);

            // 扩展属性
            Attribute fanSpeedAttr = new Attribute();
            fanSpeedAttr.setName("fanSpeed");
            fanSpeedAttr.setValue(2);
            fanSpeedAttr.setScale("");
            fanSpeedAttr.setTimestampOfSample(System.currentTimeMillis());
            fanSpeedAttr.setUncertaintyInMilliseconds(10);
            fanSpeedAttr.setLegalValue("[0, 10]");
            duerosDeviceattributes.add(fanSpeedAttr);

            Map<String, String> additionalApplianceDetails = new HashMap<>();
            additionalApplianceDetails.put("extraDetail1", "no extra data");
            additionalApplianceDetails.put("extraDetail2", "no extra data more");
            duerosDevice.setAdditionalApplianceDetails(additionalApplianceDetails);

            duerosDevice.setAttributes(duerosDeviceattributes);
            discoveredAppliances.add(duerosDevice);
        }
        return discoveredAppliances;
    }

}
