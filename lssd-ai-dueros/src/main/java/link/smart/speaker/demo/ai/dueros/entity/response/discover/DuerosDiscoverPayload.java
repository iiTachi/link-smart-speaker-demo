package link.smart.speaker.demo.ai.dueros.entity.response.discover;

import link.smart.speaker.demo.ai.dueros.entity.response.DuerosResponsePayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 发现设备的Payload
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DuerosDiscoverPayload extends DuerosResponsePayload {
    /**
     * 发现的设备列表
     */
    List<DuerosDevice> discoveredAppliances;
    /**
     * 发现的分组列表
     */
    List<DuerosDiscoveredGroup> discoveredGroups;
}
