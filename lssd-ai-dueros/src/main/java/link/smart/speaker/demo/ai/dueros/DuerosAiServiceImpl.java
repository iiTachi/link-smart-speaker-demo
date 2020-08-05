package link.smart.speaker.demo.ai.dueros;

import link.smart.speaker.demo.ai.dueros.constants.DuerosConstants;
import link.smart.speaker.demo.ai.dueros.entity.notify.DuerosDeviceSyncRequest;
import link.smart.speaker.demo.ai.dueros.entity.notify.DuerosDeviceSyncResponse;
import link.smart.speaker.demo.common.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/13
 */
@Service(value = "duerosAiService")
@Slf4j
public class DuerosAiServiceImpl extends AiService {

    @Override
    public void notifyDeviceListUpdated(List<String> openUids) {
        // 推送给AI音箱接口。
        final String uri = DuerosConstants.DUEROS_URL_DEVICE_LIST_SYNC;
        RestTemplate restTemplate = new RestTemplate();
        DuerosDeviceSyncRequest deviceSyncRequest = new DuerosDeviceSyncRequest();
        deviceSyncRequest.setBotId(DuerosConstants.DUEROS_BOT_ID);
        deviceSyncRequest.setLogId(String.valueOf(System.currentTimeMillis()));
        deviceSyncRequest.setOpenUids(openUids);
        Object response = restTemplate.postForObject(uri, deviceSyncRequest, DuerosDeviceSyncResponse.class);
        log.info("Response:" + response);
    }

    @Override
    public void notifyDeviceStateUpdated() {
        // 推送给AI音箱接口。
        log.info("in notifyDeviceStateUpdated");
    }
}
