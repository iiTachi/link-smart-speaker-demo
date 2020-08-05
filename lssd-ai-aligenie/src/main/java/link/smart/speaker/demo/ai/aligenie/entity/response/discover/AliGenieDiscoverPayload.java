package link.smart.speaker.demo.ai.aligenie.entity.response.discover;

import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieDevice;
import link.smart.speaker.demo.ai.aligenie.entity.response.AliGenieResponsePayload;
import lombok.Data;

import java.util.List;

/**
 * 发现设备的响应
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieDiscoverPayload extends AliGenieResponsePayload {
    List<AliGenieDevice> devices;
}
