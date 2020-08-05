package link.smart.speaker.demo.ai.aligenie.entity.response.discover;

import link.smart.speaker.demo.ai.aligenie.entity.AliGenieHeader;
import lombok.Data;

/**
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieDiscoverResponse {
    AliGenieHeader header;
    AliGenieDiscoverPayload payload;
}
