package link.smart.speaker.demo.ai.dueros.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mylitboy
 * @date 2020/6/23
 */
@Component
@Slf4j
public class DuerosConstants {
    /**
     * 接口URL，设备列表变更通知
     */
    public final static String DUEROS_URL_DEVICE_LIST_SYNC = "https://xiaodu.baidu.com/saiya/smarthome/devicesync";


    /**
     * 子设备品类，灯
     */
    public final static String DUEROS_SUB_DEVICE_LIGHT = "light";

    /**
     * 设备扩展属性--产品ID
     */
    public final static String ADDITIONAL_KEY_PRODUCT_KEY = "ProductKey";
    /**
     * 设备扩展属性--设备来源平台
     */
    public final static String ADDITIONAL_KEY_DEVICE_PLATFORM = "DevicePlatform";


    public static String DUEROS_BOT_ID = "";

    /**
     * 取配置文件中的信息到静态变量中。
     *
     * @param duerosConfig
     */
    @Autowired
    void initConfig(DuerosConfig duerosConfig) {
        DUEROS_BOT_ID = duerosConfig.getNotifyBotId();
        log.info("-------DUEROS_BOT_ID---" + DUEROS_BOT_ID);
    }

}
