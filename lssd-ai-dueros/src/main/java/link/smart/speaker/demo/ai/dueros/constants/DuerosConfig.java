package link.smart.speaker.demo.ai.dueros.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author mylitboy
 * @date 2020/7/22
 */
@Component
@Configuration
@Data
public class DuerosConfig {

    /**
     * 智能家居协议，技能ID
     * <p>
     * 用于有设备列表更新时，通知小度
     */
    @Value("${ai.dueros.notify-bot-id}")
    String notifyBotId;
}
