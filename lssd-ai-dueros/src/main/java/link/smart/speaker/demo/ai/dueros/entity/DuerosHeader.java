package link.smart.speaker.demo.ai.dueros.entity;

import lombok.Data;

/**
 * DuerOS协议 头部类
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosHeader {
    /**
     * 命名空间
     */
    String namespace;
    /**
     * 指令名
     */
    String name;
    /**
     * 消息ID
     */
    String messageId;
    /**
     * Payload版本号
     */
    String payloadVersion;
}
