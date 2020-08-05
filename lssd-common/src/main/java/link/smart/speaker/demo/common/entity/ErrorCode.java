
package link.smart.speaker.demo.common.entity;

/**
 * 错误码定义
 *
 * @author mylitboy
 * @date 2020/6/13
 */
public interface ErrorCode {
    /**
     * 操作成功
     */
    int SUCCESS = 200;
    /**
     * 系统错误
     */
    int ERROR = -1;
    /**
     * 设备不在线
     */
    int DEVICE_OFFLINE = 10001;
    /**
     * 设备未发现
     */
    int DEVICE_UN_FUND = 10002;

}
