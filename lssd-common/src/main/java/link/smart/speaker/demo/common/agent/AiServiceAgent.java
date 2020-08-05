package link.smart.speaker.demo.common.agent;

import link.smart.speaker.demo.common.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/7/16
 */
@Service("aiService")
@Slf4j
public class AiServiceAgent extends AiService {

    public AiServiceAgent() {
        for (int i = aiServiceList.size() - 1; i >= 0; i--) {
            AiService aiService = aiServiceList.get(i);
            if (aiService instanceof AiServiceAgent) {
                aiServiceList.remove(aiService);
            }
        }
    }

    @Override
    public void notifyDeviceListUpdated(List<String> openUids) {
        // TODO 根据设备ID/用户ID，查询各AI平台的OpenID。
        // 判断发送给哪些平台，哪些设备/用户更新数据。
        for (AiService aiService : aiServiceList) {
            aiService.notifyDeviceListUpdated(openUids);
        }
    }

    @Override
    public void notifyDeviceStateUpdated() {
        for (AiService aiService : aiServiceList) {
            aiService.notifyDeviceStateUpdated();
        }
    }
}
