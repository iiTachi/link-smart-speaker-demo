package link.smart.speaker.demo.device;

import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.common.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备控制接口
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@Service
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
    List<DeviceInfo> queryDeviceList();

    /**
     * 开机
     *
     * @return
     */
    boolean openPower();

    /**
     * 关机
     *
     * @return
     */
    boolean closePower();

    /**
     * 开灯
     *
     * @return
     */
    boolean openLightPower();

    /**
     * 关灯
     *
     * @return
     */
    boolean closeLightPower();

    /**
     * 风速档位设置
     * 0-停止  1-低档  2-中档  3-高档
     *
     * @param speed
     * @return
     */
    boolean setSpeed(int speed);

}
