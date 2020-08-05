package link.smart.speaker.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * 程序入口类
 *
 * @author mylitboy
 * @date 2020/6/5
 */
@SpringBootApplication
@EnableConfigurationProperties
public class LinkSmartSpeakerDemoApplication {

    /**
     * 程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LinkSmartSpeakerDemoApplication.class, args);
    }

    /**
     * 配置https转http
     *
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        // TODO 配置http10031端口，调到https的10030端口
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(10031);
        connector.setSecure(false);
        connector.setRedirectPort(10030);
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
