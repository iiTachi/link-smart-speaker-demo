package link.smart.speaker.demo.common.agent;

import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import link.smart.speaker.demo.common.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DeviceService的代理类
 *
 * @author mylitboy
 * @date 2020/7/16
 */
@Service(value = "deviceService")
@Slf4j
public class AgentDeviceServiceImpl implements DeviceService {

    public static Map<String, DeviceService> deviceServiceMap = new HashMap<>();

    /**
     * AI侧过来的用户信息
     */
    private UserInfo user;
    /**
     * AI侧过来的设备信息
     */
    private DeviceInfo device;
    /**
     * 控制、查询命令中的设备服务
     */
    private DeviceService deviceService;

    /**
     * 初始化信息
     *
     * @param user
     * @return
     */
    @Override
    public DeviceService initUser(UserInfo user) {
        this.user = user;
        return this;
    }

    /**
     * 初始化信息
     *
     * @param device
     * @return
     */
    @Override
    public DeviceService initDevice(DeviceInfo device) {
        this.device = device;
        // 判断设备平台，根据不同的平台，调用不同的命令。
        if (device.getDevicePlatform() != null) {
            deviceService = deviceServiceMap.get(device.getDevicePlatform());
            deviceService.initDevice(device);
        }
        return this;
    }

    /**
     * 根据用户Token，获取设备列表
     *
     * @return
     */
    @Override
    public List<DeviceInfo> queryDeviceList() {
        List<DeviceInfo> deviceList = new ArrayList<>();
        Iterator<Map.Entry<String, DeviceService>> deviceServiceSet = deviceServiceMap.entrySet().iterator();
        while (deviceServiceSet.hasNext()) {
            DeviceService deviceService = deviceServiceSet.next().getValue();
            deviceList.addAll(deviceService.queryDeviceList());
        }
        return deviceList;
    }

    /**
     * 开机
     *
     * @return
     */
    @Override
    public Result openPower() {
        return deviceService.openPower();
    }

    /**
     * 关机
     *
     * @return
     */
    @Override
    public Result closePower() {
        return deviceService.closePower();
    }

    /**
     * 开灯
     *
     * @return
     */
    @Override
    public Result openLightPower() {
        return deviceService.openLightPower();
    }

    /**
     * 关灯
     *
     * @return
     */
    @Override
    public Result closeLightPower() {
        return deviceService.closeLightPower();
    }

    /**
     * 风速档位设置
     * 0-停止  1-低档  2-中档  3-高档
     *
     * @param speed
     * @return
     */
    @Override
    public Result setSpeed(int speed) {
        return deviceService.setSpeed(speed);
    }

    /**
     * 查询风速
     *
     * @return
     */
    @Override
    public Result querySpeed() {
        return deviceService.querySpeed();
    }

    /**
     * 设置温度
     *
     * @param temperatureValue
     * @return
     */
    @Override
    public Result setTemperature(int temperatureValue) {
        return deviceService.setTemperature(temperatureValue);
    }

    /**
     * 设置出水量
     *
     * @param waterOutValue
     * @return
     */
    @Override
    public Result setWaterOut(int waterOutValue) {
        return deviceService.setWaterOut(waterOutValue);
    }

    /**
     * 查询温度
     *
     * @return
     */
    @Override
    public Result queryTemperature() {
        return deviceService.queryTemperature();
    }
}
