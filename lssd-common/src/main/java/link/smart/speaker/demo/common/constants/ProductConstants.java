package link.smart.speaker.demo.common.constants;

/**
 * AI模块，根据不同的产品类型，进行对应的处理。
 *
 * @author mylitboy
 * @date 2020/6/22
 */
public interface ProductConstants {

    /**
     * 产品类型：抽油烟机
     */
    String PRODUCT_TYPE_CYYJ = "PRODUCT_TYPE_CYYJ";
    /**
     * 产品类型：净水器
     */
    String PRODUCT_TYPE_JSQ = "PRODUCT_TYPE_JSQ";

    /**
     * 设备平台：Mock数据
     */
    String DEVICE_PLATFORM_MOCK = "DEVICE_PLATFORM_MOCK";

    int DEVICE_STATUS_ONLINE = 1;
    int DEVICE_STATUS_OFFLINE = 3;
}
