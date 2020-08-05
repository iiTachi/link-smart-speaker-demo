package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.common.entity.DeviceInfo;
import link.smart.speaker.demo.common.entity.UserInfo;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
public interface DuerosService {
    /**
     * 用户初始化
     *
     * @param user
     */
    void initUser(UserInfo user);

    /**
     * 设备初始化
     *
     * @param device
     */
    void initDevice(DeviceInfo device);
}
