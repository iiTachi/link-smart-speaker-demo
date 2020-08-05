package link.smart.speaker.demo.common.service;

import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.Result;
import link.smart.speaker.demo.common.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备控制接口
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@Service("BaseDeviceService")
public interface DeviceService {
    /**
     * 初始化信息
     *
     * @param user
     * @return
     */
    DeviceService initUser(UserInfo user);

    /**
     * 初始化信息
     *
     * @param device
     * @return
     */
    DeviceService initDevice(DeviceInfo device);

    /**
     * 根据用户Token，获取设备列表
     *
     * @return
     */
    default List<DeviceInfo> queryDeviceList() {
        return new ArrayList<>();
    }

    /**
     * 开机
     *
     * @return
     */
    default Result openPower() {
        // TODO 控制设备
        return Result.success();
    }

    /**
     * 关机
     *
     * @return
     */
    default Result closePower() {
        return Result.success();
    }

    /**
     * 开灯
     *
     * @return
     */
    default Result openLightPower() {
        return Result.success();
    }

    /**
     * 关灯
     *
     * @return
     */
    default Result closeLightPower() {
        return Result.success();
    }

    /**
     * 风速档位设置
     * 0-停止  1-低档  2-中档  3-高档
     *
     * @param speed
     * @return
     */
    default Result setSpeed(int speed) {
        return Result.success();
    }

    /**
     * 查询风速
     *
     * @return
     */
    default Result querySpeed() {
        return Result.success(2);
    }

    /**
     * 设置温度
     *
     * @param temperatureValue
     * @return
     */
    default Result setTemperature(int temperatureValue) {
        return Result.success();
    }

    /**
     * 设置出水量
     *
     * @param waterOutValue
     * @return
     */
    default Result setWaterOut(int waterOutValue) {
        return Result.success();
    }

    /**
     * 查询温度
     *
     * @return
     */
    default Result queryTemperature() {
        return Result.success();
    }
}
