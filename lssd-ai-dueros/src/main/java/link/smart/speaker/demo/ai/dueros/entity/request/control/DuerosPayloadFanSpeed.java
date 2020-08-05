package link.smart.speaker.demo.ai.dueros.entity.request.control;

import lombok.Data;

/**
 * 风速控制
 * <br/>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/control-message_markdown#SetFanSpeedRequest">小度协议地址</a>
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosPayloadFanSpeed {
    /**
     * 风速值
     */
    int value;

    /**
     * 风速级别（high,middle,low..）
     */
    String level;
}
