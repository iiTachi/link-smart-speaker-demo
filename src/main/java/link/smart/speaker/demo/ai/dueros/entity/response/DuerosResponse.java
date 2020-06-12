package link.smart.speaker.demo.ai.dueros.entity.response;

import link.smart.speaker.demo.ai.dueros.entity.DuerosHeader;
import lombok.Data;

/**
 * DuerOS响应类
 *
 * @author mylitboy
 * @date 2020/6/8
 */
@Data
public class DuerosResponse {

    /**
     * 响应header
     */
    DuerosHeader header;
    /**
     * 响应Payload
     */
    DuerosResponsePayload payload;
}
