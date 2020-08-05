package link.smart.speaker.demo.ai.aligenie.entity.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieRequestPayload {
    String accessToken;
    String deviceId;
    String deviceType;
    String attribute;
    String value;
    Map<String, String> extensions = new HashMap<>();
}
