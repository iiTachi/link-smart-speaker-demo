package link.smart.speaker.demo.oauth;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Oauth2配置
 *
 * @author mylitboy
 * @date 2020/5/29
 */
@Component
@EnableAuthorizationServer
@Slf4j
@Data
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    static List<Map<String, String>> OAUTH2_CLIENTS;

    @Autowired
    void initClient(OauthClients oauthClients){
        OauthServerConfig.OAUTH2_CLIENTS = oauthClients.getClients();
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // code授权添加
        oauthServer.realm("oauth2-resources")
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        ClientDetailsServiceBuilder builder = configurer.inMemory();
        ClientDetailsServiceBuilder.ClientBuilder clientBuilder = null;
        log.info("-------OAUTH2_CLIENTS---" + OAUTH2_CLIENTS);
        // 遍历配置文件中的Client信息
        for (int i = 0; i < OAUTH2_CLIENTS.size(); i++) {
            Map<String, String> client = OAUTH2_CLIENTS.get(i);
            if (i > 0) {
                builder = clientBuilder.and();
            }
            // 配置授权信息
            clientBuilder = builder.withClient(client.get("client-id"))
                    .secret(bCryptPasswordEncoder.encode(client.get("client-secret")))
                    .redirectUris(client.get("redirect-uri"))
                    .scopes("all")
                    .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(1200)
                    .refreshTokenValiditySeconds(50000);
        }
    }

}