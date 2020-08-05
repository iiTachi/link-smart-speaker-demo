package link.smart.speaker.demo.ai.dueros.entity.request;

import link.smart.speaker.demo.ai.dueros.entity.request.control.DuerosPayloadFanSpeed;
import link.smart.speaker.demo.ai.dueros.entity.request.control.DuerosPayloadMode;
import link.smart.speaker.demo.ai.dueros.entity.request.control.DuerosPayloadTargetTemperature;
import lombok.Data;

/**
 * DuerOS协议 Payload部分
 * <br/>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/control-message_markdown#SetFanSpeedRequest">小度协议地址</a>
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosRequestPayload {
    /**
     * 用户Token
     */
    String accessToken;
    /**
     * 百度用户ID
     */
    String openUid;
    /**
     * 设备信息
     */
    DuerosAppliance appliance;
    /**
     * 风速信息
     * <br/>
     * 在控制消息中使用，如：用户说，风速调到二档时，会附带该内容。
     */
    DuerosPayloadFanSpeed fanSpeed;
    /**
     * 温度信息
     */
    DuerosPayloadTargetTemperature targetTemperature;
    /**
     * 模式
     */
    DuerosPayloadMode mode;
    /**
     * 设备的子功能
     * <br/>
     * 在控制消息中使用，如：打开冰箱的照明灯，会附带该内容
     */
    String function;
}
