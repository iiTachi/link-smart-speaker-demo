package link.smart.speaker.demo.common.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/13
 */
public abstract class AiService {
    public static List<AiService> aiServiceList = new ArrayList<>();

    public AiService() {
        aiServiceList.add(this);
    }

    /**
     * 通知AI平台，设备列表有变更。
     *
     * @param openUids
     */
    public abstract void notifyDeviceListUpdated(List<String> openUids);

    /**
     * 通知AI平台，设备状态变更。
     */
    public abstract void notifyDeviceStateUpdated();
}
