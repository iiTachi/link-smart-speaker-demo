package link.smart.speaker.demo.device.mock;

import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.common.UserInfo;
import link.smart.speaker.demo.device.DeviceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock设备数据
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    UserInfo user;
    DeviceInfo device;

    @Override
    public DeviceService initUser(UserInfo user) {
        this.user = user;
        return this;
    }

    @Override
    public DeviceService initDevice(DeviceInfo device) {
        this.device = device;
        return this;
    }

    @Override
    public List<DeviceInfo> queryDeviceList() {
        // TODO 根据用户查询设备列表
        List<DeviceInfo> deviceList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            DeviceInfo device = new DeviceInfo();
            device.setDeviceId("Device00" + i);
            device.setNickName("家里的设备" + i);
            device.setDeviceDescription("设备描述...");
            device.setManufacturerName("厂家名称");
            device.setProductKey("ProductKey");
            device.setProductName("产品名称");
            device.setModel("DevMod0" + i);
            device.setVersion("1.0.0");
            deviceList.add(device);
        }
        return deviceList;
    }

    @Override
    public boolean openPower() {
        // TODO 控制设备
        return true;
    }

    @Override
    public boolean closePower() {
        return true;
    }

    @Override
    public boolean openLightPower() {
        return true;
    }

    @Override
    public boolean closeLightPower() {
        return true;
    }

    @Override
    public boolean setSpeed(int speed) {
        return true;
    }
}
