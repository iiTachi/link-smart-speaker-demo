package link.smart.speaker.demo.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author mylitboy
 * @date 2020/7/22
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oauth2")
public class OauthClients {
    private List<Map<String, String>> clients;
}
