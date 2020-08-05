package link.smart.speaker.demo.ai.dueros.entity.response.query;

import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import lombok.Data;

import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/6/13
 */
@Data
public class DuerosRunningStatePayload extends DuerosResponsePayload {
    Map<String,String> runningState;
}
