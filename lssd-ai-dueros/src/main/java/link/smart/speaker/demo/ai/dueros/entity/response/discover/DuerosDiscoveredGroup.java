package link.smart.speaker.demo.ai.dueros.entity.response.discover;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/6/29
 */
@Data
public class DuerosDiscoveredGroup {
    /**
     * 用户用来识别分组的名称,不应包含特殊字符或标点符号，长度不超过20字符。
     */
    String groupName;

    /**
     * 分组所包含设备ID的数组
     * 要求设备ID必须是已经发现的设备中的ID，否则会同步失败，每个分组设备ID数量不超过50
     */
    List<String> applianceIds = new ArrayList<>();
    /**
     * 分组备注信息，不能超过128个字符
     */
    String groupNotes;
    /**
     * 提供给技能使用的分组相关的附加信息的键值对。
     * 该属性的内容不能超过2000字符。而且DuerOS也不了解或使用这些数据。
     */
    Map additionalGroupDetails = new HashMap();


}
