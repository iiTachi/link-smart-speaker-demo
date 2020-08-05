package link.smart.speaker.demo.ai.aligenie.entity.response;

import link.smart.speaker.demo.ai.aligenie.entity.AliGenieHeader;
import lombok.Data;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/17
 */
@Data
public class AliGenieResponse {
    AliGenieHeader header;
    AliGenieResponsePayload payload;
    List<AliGenieProperties> properties;
}
