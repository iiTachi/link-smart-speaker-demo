package link.smart.speaker.demo.ai.dueros.entity.request;

import link.smart.speaker.demo.ai.dueros.entity.DuerosHeader;
import lombok.Data;

/**
 * DuerOS请求类
 * <br/>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/intro-protocol_markdown">协议地址</a>
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosRequest {
    /**
     * 协议头
     */
    DuerosHeader header;
    /**
     * 协议内容
     */
    DuerosRequestPayload payload;
}
