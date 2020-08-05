package link.smart.speaker.demo.ai.dueros.entity.notify;

import lombok.Data;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/23
 */
@Data
public class DuerosDeviceSyncRequest {
    /**
     * 技能ID
     */
    String botId;
    /**
     * 技能本次请求logId
     */
    String logId;
    /**
     * 需要同步的用户openUid信息
     * openUid不超过5个
     */
    List openUids;
}
