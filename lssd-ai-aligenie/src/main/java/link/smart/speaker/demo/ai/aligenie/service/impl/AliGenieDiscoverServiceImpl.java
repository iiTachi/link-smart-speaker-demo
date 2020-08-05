package link.smart.speaker.demo.ai.aligenie.service.impl;

import link.smart.speaker.demo.ai.aligenie.constants.AliGenieConstants;
import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieDevice;
import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieProperties;
import link.smart.speaker.demo.ai.aligenie.entity.response.discover.AliGenieDiscoverPayload;
import link.smart.speaker.demo.ai.aligenie.service.AliGenieDiscoverService;
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
 * @date 2020/6/17
 */
@Service
@Slf4j
public class AliGenieDiscoverServiceImpl implements AliGenieDiscoverService {

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
    public AliGenieDiscoverPayload discover() {
        // TODO 暂时使用固定的用户信息，待OAuth对接后，切换新的。
        List<DeviceInfo> deviceList = deviceService.queryDeviceList();
        log.info("AliGenie discover deviceList:" + deviceList);
        List<AliGenieDevice> discoveredDevices = turnCommonDeviceInfoToAliGenieDevice(deviceList);
        AliGenieDiscoverPayload payload = new AliGenieDiscoverPayload();
        payload.setDevices(discoveredDevices);
        log.info("AliGenie payload:" + payload);
        return payload;
    }

    /**
     * 通用设备信息转换为AliGenie设备
     *
     * @param deviceList
     * @return
     */
    private List<AliGenieDevice> turnCommonDeviceInfoToAliGenieDevice(List<DeviceInfo> deviceList) {
        List<AliGenieDevice> discoveredDevices = new ArrayList<>();

        for (DeviceInfo device : deviceList) {
            AliGenieDevice aliGenieDevice = new AliGenieDevice();
            aliGenieDevice.setDeviceId(device.getDeviceId());
            aliGenieDevice.setDeviceName(device.getNickName() != null ? device.getNickName() : device.getProductName());
            // 设置基本信息
            aliGenieDevice.setModel(device.getModel());
            aliGenieDevice.setBrand(device.getManufacturerName());

            List<AliGenieProperties> properties = new ArrayList<>();
            // 设备在线离线状态 online(在线)，offline(离线)
            AliGenieProperties onlineStateProp = new AliGenieProperties();
            onlineStateProp.setName("onlinestate");
            onlineStateProp.setValue(device.getStatus() == ProductConstants.DEVICE_STATUS_ONLINE ? "online" :
                    (device.getStatus() == ProductConstants.DEVICE_STATUS_OFFLINE ? "offline" : "null"));
            properties.add(onlineStateProp);

            aliGenieDevice.setProperties(properties);

            Map<String, String> extensions = new HashMap<>(2);
            extensions.put(AliGenieConstants.EXTENSION_KEY_PRODUCT_KEY, device.getProductKey());
            extensions.put(AliGenieConstants.EXTENSION_KEY_DEVICE_PLATFORM, device.getDevicePlatform());
            aliGenieDevice.setExtensions(extensions);

            switch (device.getProductType()) {
                case ProductConstants.PRODUCT_TYPE_CYYJ:
                    // 抽油烟机
                    aliGenieDevice.setDeviceType("kitchenventilator");
                    aliGenieDevice.setZone("厨房");
                    aliGenieDevice.setActions(Arrays.asList("TurnOn", "TurnOff", "Query", "QueryWindspeed", "QueryWindSpeed"));
                    discoveredDevices.add(aliGenieDevice);
                    break;
                case ProductConstants.PRODUCT_TYPE_JSQ:
                    // 净水器品类
                    aliGenieDevice.setDeviceType("waterpurifier");
                    aliGenieDevice.setZone("厨房");
                    aliGenieDevice.setActions(Arrays.asList("SetTemperature", "QueryTemperature"));
                    discoveredDevices.add(aliGenieDevice);
                    break;
                default:
                    break;
            }

        }
        return discoveredDevices;
    }

}
