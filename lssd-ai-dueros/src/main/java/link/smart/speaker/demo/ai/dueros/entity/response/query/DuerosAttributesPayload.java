package link.smart.speaker.demo.ai.dueros.entity.response.query;

import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import link.smart.speaker.demo.ai.dueros.entity.response.discover.DuerosDeviceAttribute;
import lombok.Data;

import java.util.List;

/**
 * @author mylitboy
 * @date 2020/6/22
 */
@Data
public class DuerosAttributesPayload extends DuerosResponsePayload {
    List<DuerosDeviceAttribute> attributes;
}
