package link.smart.speaker.demo.device.mock;

import link.smart.speaker.demo.common.agent.AgentDeviceServiceImpl;
import link.smart.speaker.demo.common.constants.ProductConstants;
import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mylitboy
 * @date 2020/7/16
 */
@Service(value = "mockDeviceService")
@Slf4j
public class MockDeviceServiceImpl implements DeviceService {

    public MockDeviceServiceImpl() {
        // 注册到DeviceService代理。
        AgentDeviceServiceImpl.deviceServiceMap.put(ProductConstants.DEVICE_PLATFORM_MOCK, this);
    }

    @Override
    public DeviceService initUser(UserInfo user) {
        return this;
    }

    @Override
    public DeviceService initDevice(DeviceInfo device) {
        return this;
    }

    @Override
    public List<DeviceInfo> queryDeviceList() {
        // TODO 根据用户查询设备列表
        List<DeviceInfo> deviceList = new ArrayList<>();
        int mockCount = 3;
        for (int i = 1; i <= mockCount; i++) {
            DeviceInfo device = new DeviceInfo();
            device.setDeviceId("DeviceCYYJ00" + i);
            device.setNickName("虚拟抽油烟机" + i);
            device.setDeviceDescription("设备描述...");
            device.setDevicePlatform(ProductConstants.DEVICE_PLATFORM_MOCK);
            device.setManufacturerName("厂家名称");
            device.setProductKey("CYYJProductKey");
            device.setProductType(ProductConstants.PRODUCT_TYPE_CYYJ);
            device.setProductName("虚拟抽油烟机");
            device.setModel("DevModCYYJ0" + i);
            device.setVersion("1.0.0");
            deviceList.add(device);
        }
        mockCount = 2;
        for (int i = 1; i <= mockCount; i++) {
            DeviceInfo device = new DeviceInfo();
            device.setDeviceId("DeviceJSQ00" + i);
            device.setNickName("虚拟净水器" + i);
            device.setDeviceDescription("设备描述...");
            device.setDevicePlatform(ProductConstants.DEVICE_PLATFORM_MOCK);
            device.setManufacturerName("厂家名称");
            device.setProductKey("JSQProductKey");
            device.setProductType(ProductConstants.PRODUCT_TYPE_JSQ);
            device.setProductName("虚拟净水器");
            device.setModel("DevModJSQ0" + i);
            device.setVersion("1.0.0");
            deviceList.add(device);
        }
        return deviceList;
    }

}
