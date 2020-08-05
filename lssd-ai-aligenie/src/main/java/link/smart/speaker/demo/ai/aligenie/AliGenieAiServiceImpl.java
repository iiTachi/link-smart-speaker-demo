package link.smart.speaker.demo.ai.aligenie;

import link.smart.speaker.demo.common.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/7/16
 */
@Service(value = "aligenieAiService")
@Slf4j
public class AliGenieAiServiceImpl extends AiService {
    @Override
    public void notifyDeviceListUpdated(List<String> openUids) {
        log.info("in AliGenie AiService notifyDeviceListUpdated");
    }

    @Override
    public void notifyDeviceStateUpdated() {
        log.info("in AliGenie AiService notifyDeviceStateUpdated");
    }
}
