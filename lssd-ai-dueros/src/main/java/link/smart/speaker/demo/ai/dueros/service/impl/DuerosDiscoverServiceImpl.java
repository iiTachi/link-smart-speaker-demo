package link.smart.speaker.demo.ai.dueros.service.impl;

import link.smart.speaker.demo.ai.dueros.constants.DuerosConstants;
import link.smart.speaker.demo.ai.dueros.entity.request.DuerosRequest;
import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDevice;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDeviceAttribute;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDiscoverPayload;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDiscoveredGroup;
import link.smart.speaker.demo.ai.dueros.service.DuerosDiscoverService;
import link.smart.speaker.demo.common.constants.ProductConstants;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
@Service
@Slf4j
public class DuerosDiscoverServiceImpl implements DuerosDiscoverService {

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
        DuerosDiscoverPayload payload = new DuerosDiscoverPayload();
        // TODO 暂时使用固定的用户信息，待OAuth对接后，切换新的。
        List<DeviceInfo> deviceList = deviceService.queryDeviceList();
        List<DuerosDevice> discoveredAppliances = turnCommonDeviceInfoToDuerosDevice(deviceList);

        // TODO 添加Mock场景列表数据。
        // discoveredAppliances.addAll(mockSceneList());
        payload.setDiscoveredAppliances(discoveredAppliances);

        // TODO 添加Mock分组列表数据。
        // payload.setDiscoveredGroups(mockGroupList(deviceList));
        return payload;
    }

    /**
     * Mock设备分组
     *
     * @param deviceList
     * @return
     */
    private List<DuerosDiscoveredGroup> mockGroupList(List<DeviceInfo> deviceList) {
        List<DuerosDiscoveredGroup> discoveredGroups = new ArrayList<>();
        DuerosDiscoveredGroup discoveredGroup = new DuerosDiscoveredGroup();
        discoveredGroup.setGroupName("厨房");
        for (DeviceInfo device : deviceList) {
            discoveredGroup.getApplianceIds().add(device.getDeviceId());
        }
        discoveredGroup.setGroupNotes("厨房的分组控制");
        discoveredGroups.add(discoveredGroup);
        return discoveredGroups;
    }

    /**
     * Mock场景数据
     *
     * @return
     */
    private List<DuerosDevice> mockSceneList() {
        List<DuerosDevice> discoveredAppliances = new ArrayList<>();
        DuerosDevice duerosDevice = new DuerosDevice();

        duerosDevice.setApplianceId("scene00" + new Random().nextInt(9));
        duerosDevice.setFriendlyDescription("回家模式描述");
        duerosDevice.setFriendlyName("回家模式");
        duerosDevice.setManufacturerName("品牌");
        duerosDevice.setModelName("BackHome");
        duerosDevice.setVersion("1.0");
        duerosDevice.setReachable(true);
        duerosDevice.setActions(Arrays.asList("turnOn", "turnOff"));
        duerosDevice.setApplianceTypes(Arrays.asList("SCENE_TRIGGER"));
        // 设备属性列表
        List<DuerosDeviceAttribute> duerosDeviceAttributes = new ArrayList<>();
        // 必选属性name
        DuerosDeviceAttribute duerosDeviceAttribute01 = new DuerosDeviceAttribute();
        duerosDeviceAttribute01.setName("name");
        duerosDeviceAttribute01.setValue("回家模式");
        duerosDeviceAttribute01.setScale("");
        duerosDeviceAttribute01.setTimestampOfSample(System.currentTimeMillis());
        duerosDeviceAttribute01.setUncertaintyInMilliseconds(10);
        duerosDeviceAttributes.add(duerosDeviceAttribute01);

        DuerosDeviceAttribute duerosDeviceAttribute02 = new DuerosDeviceAttribute();
        duerosDeviceAttribute02.setName("turnOnState");
        duerosDeviceAttribute02.setValue("ON");
        duerosDeviceAttribute01.setScale("");
        duerosDeviceAttribute02.setTimestampOfSample(System.currentTimeMillis());
        duerosDeviceAttribute02.setUncertaintyInMilliseconds(0);
        duerosDeviceAttributes.add(duerosDeviceAttribute02);
        duerosDevice.setAttributes(duerosDeviceAttributes);
        discoveredAppliances.add(duerosDevice);
        return discoveredAppliances;
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
            duerosDevice.setFriendlyName(device.getNickName() != null ? device.getNickName() : device.getProductName());
            // 厂商名称
            duerosDevice.setManufacturerName(device.getManufacturerName());
            // 型号
            duerosDevice.setModelName(device.getModel());
            // 版本
            duerosDevice.setVersion(device.getVersion());

            // 判断是否在线，非在线均为不可控。
            boolean ifDeviceOnline = device.getStatus() == ProductConstants.DEVICE_STATUS_ONLINE;
            // 是否可达
            duerosDevice.setReachable(ifDeviceOnline);

            // 扩展数据：设备的附加数据，小度不解析，会在向设备云的请求中附带上。
            // 可用来保存，ProductKey，对接的平台，等等信息。
            Map<String, String> additionalApplianceDetails = new HashMap<>(2);
            additionalApplianceDetails.put(DuerosConstants.ADDITIONAL_KEY_PRODUCT_KEY, device.getProductKey());
            additionalApplianceDetails.put(DuerosConstants.ADDITIONAL_KEY_DEVICE_PLATFORM, device.getDevicePlatform());
            duerosDevice.setAdditionalApplianceDetails(additionalApplianceDetails);

            // 设备属性列表
            List<DuerosDeviceAttribute> deviceAttributes = new ArrayList<>();
            // 必选属性connectivity
            deviceAttributes.add(new DuerosDeviceAttribute("connectivity", ifDeviceOnline ? "REACHABLE" : "UNREACHABLE"));
            // 必选属性name
            deviceAttributes.add(new DuerosDeviceAttribute("name", duerosDevice.getFriendlyName()));

            // TODO 设置小度专用类设备数据。
            switch (device.getProductType()) {
                case ProductConstants.PRODUCT_TYPE_CYYJ:
                    // 抽油烟机品类
                    // 设备支持的操作。
                    duerosDevice.setActions(Arrays.asList("turnOn", "turnOff", "setFanSpeed", "incrementFanSpeed", "decrementFanSpeed", "getFanSpeed", "getRunningStatus"));
                    // 设备类型。模块及子模块
                    duerosDevice.setApplianceTypes(Arrays.asList("RANGE_HOOD"));

                    // 扩展属性
                    deviceAttributes.add(new DuerosDeviceAttribute("powerState", "ON"));

                    duerosDevice.setAttributes(deviceAttributes);
                    discoveredAppliances.add(duerosDevice);
                    break;
                case ProductConstants.PRODUCT_TYPE_JSQ:
                    // 净水器品类
                    // 设备支持的操作。
                    duerosDevice.setActions(Arrays.asList("setTemperature", "getTemperatureReading", "setWaterLevel"));
                    // 设备类型。模块及子模块
                    duerosDevice.setApplianceTypes(Arrays.asList("WATER_PURIFIER"));

                    // 扩展属性
                    duerosDevice.setAttributes(deviceAttributes);
                    discoveredAppliances.add(duerosDevice);
                    break;
                default:
                    break;
            }
        }
        return discoveredAppliances;
    }

}
