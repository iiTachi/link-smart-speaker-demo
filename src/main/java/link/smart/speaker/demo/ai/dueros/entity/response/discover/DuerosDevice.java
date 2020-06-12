package link.smart.speaker.demo.ai.dueros.entity.response.discover;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 设备云帐户的设备、场景
 * <br/>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/discovery-message_markdown#DiscoverAppliancesResponse">协议地址</a>
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosDevice {
    /**
     * 支持的设备、场景类型
     */
    List<String> actions;

    /**
     * 设备支持的操作类型数组
     */
    List<String> applianceTypes;
    /**
     * 提供给设备云使用，存放设备或场景相关的附加信息，是键值对
     * <p>
     * 该属性的内容不能超过5000字节。
     * 如：extraDetail1:abc；extraDetail2:123
     */
    Map<String, String> additionalApplianceDetails;
    /**
     * 设备相关的描述，描述内容提需要提及设备厂商，使用场景及连接方式
     * <p>
     * 长度不超过128个字符
     */
    String friendlyDescription;
    /**
     * 用户用来识别设备的名称
     */
    String friendlyName;
    /**
     * 设备当前是否能够到达。
     * <br/>
     * true表示设备当前可以到达，false表示当前设备不能到达。
     */
    boolean isReachable;
    /**
     * 设备厂商的名字
     */
    String manufacturerName;
    /**
     * 设备型号名称
     */
    String modelName;
    /**
     * 供应商提供的设备版本
     */
    String version;
    /**
     * 设备标识符
     * <br/>
     * 标识符在用户拥有的所有设备上必须是唯一的。<br/>
     * 此外，标识符需要在同一设备的多个发现请求之间保持一致。
     */
    String applianceId;
    /**
     * 设备的属性信息
     * <p>
     * 当设备没有属性信息时，协议中不需要传入该字段。
     * 每个设备允许同步的最大的属性数量是10
     */
    List<Attribute> attributes;
}
