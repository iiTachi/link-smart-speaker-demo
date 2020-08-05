package link.smart.speaker.demo.ai.dueros.entity.response.discover;

import lombok.Data;

/**
 * 设备的属性信息
 * <br/>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/attributes.md">设备属性协议地址</a>
 * <p>
 * <a href="https://dueros.baidu.com/didp/doc/dueros-bot-platform/dbp-smart-home/protocol/attributes-report.md">属性上报协议地址</a>
 *
 * @author mylitboy
 * @date 2020/6/2
 */
@Data
public class DuerosDeviceAttribute {
    /**
     * 属性名称
     * <p>
     * 支持数字、字母和下划线，长度不能超过128个字符。
     */
    String name;
    /**
     * 属性值
     */
    Object value;
    /**
     * 属性值的单位名称
     * <p>
     * 支持数字、字母和下划线，长度不能超过128个字符
     */
    String scale = "";
    /**
     * 属性值取样的时间戳，单位是秒
     */
    long timestampOfSample;
    /**
     * 属性值取样的时间误差，单位是ms。
     * <p>
     * 如果设备使用的是轮询时间间隔的取样方式，那么uncertaintyInMilliseconds就等于时间间隔
     */
    int uncertaintyInMilliseconds;
    /**
     * 属性取值的合法范围，是字符串类型。
     * <p>
     * 字符串中包含的值，可以是单个值：
     * "INTEGER"，表示合法值是整数；
     * "DOUBLE"，表示合法值是浮点数；
     * "STRING"，表示合法值是字符串；
     * "BOOLEAN"，表示合法值是布尔值；
     * "OBJECT"，表示合法值是json对象；
     * <p>
     * 可以是集合： "(A1, B1, C1, D1)"，表示值可以取这些字符串；
     * 也可以是数字范围："[from: to]"，表示合法值是处于对应的数值范围内。
     */
    String legalValue;

    public DuerosDeviceAttribute() {
    }

    public DuerosDeviceAttribute(String name, Object value) {
        this.name = name;
        this.value = value;
        this.timestampOfSample = System.currentTimeMillis();
        this.uncertaintyInMilliseconds = 10;
    }

    public DuerosDeviceAttribute(String name, Object value, String legalValue) {
        this.name = name;
        this.value = value;
        this.legalValue = legalValue;
    }

    public DuerosDeviceAttribute(String name, Object value, int uncertaintyInMilliseconds) {
        this.name = name;
        this.value = value;
        this.timestampOfSample = System.currentTimeMillis();
        this.uncertaintyInMilliseconds = uncertaintyInMilliseconds;
    }

    public DuerosDeviceAttribute(String name, Object value, long timestampOfSample, int uncertaintyInMilliseconds) {
        this.name = name;
        this.value = value;
        this.timestampOfSample = timestampOfSample;
        this.uncertaintyInMilliseconds = uncertaintyInMilliseconds;
    }

    public DuerosDeviceAttribute(String name, Object value, String scale, long timestampOfSample, int uncertaintyInMilliseconds) {
        this.name = name;
        this.value = value;
        this.scale = scale;
        this.timestampOfSample = timestampOfSample;
        this.uncertaintyInMilliseconds = uncertaintyInMilliseconds;
    }

}
