package link.smart.speaker.demo.ai.dueros.service;

import link.smart.speaker.demo.common.DeviceInfo;
import link.smart.speaker.demo.common.UserInfo;

/**
 * @author mylitboy
 * @date 2020/6/10
 */
public interface DuerosService {
    void initUser(UserInfo user);

    void initDevice(DeviceInfo device);
}
