package link.smart.speaker.demo.ai.aligenie.entity;

import lombok.Data;

/**
 * 天猫精灵Header类
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@Data
public class AliGenieHeader {
    /**
     * 命名空间
     */
    String namespace;
    /**
     * 指令名称
     */
    String name;
    /**
     * 消息ID
     */
    String messageId;
    /**
     * Payload版本
     */
    int payLoadVersion;
}
