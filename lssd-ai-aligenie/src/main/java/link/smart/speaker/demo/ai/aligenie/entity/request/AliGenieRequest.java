package link.smart.speaker.demo.ai.aligenie.entity.request;

import link.smart.speaker.demo.ai.aligenie.entity.AliGenieHeader;
import lombok.Data;

/**
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieRequest {
    AliGenieHeader header;
    AliGenieRequestPayload payload;
}
