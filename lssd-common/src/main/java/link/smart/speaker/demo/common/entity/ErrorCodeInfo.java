package link.smart.speaker.demo.common.entity;

/**
 * 错误码定义
 *
 * @author mylitboy
 * @date 2020/6/13
 */
public enum ErrorCodeInfo {
    /**
     * 操作成功
     */
    SUCCESS(ErrorCode.SUCCESS, "Success"),
    /**
     * 系统错误
     */
    ERROR(-1, "System Error"),
    /**
     * 设备不在线
     */
    DEVICE_OFFLINE(10001, "Device Offline"),
    /**
     * 设备未发现
     */
    DEVICE_UN_FUND(10002, "Device un fund");

    /**
     * 错误码值
     */
    int code;
    String msg;

    /**
     * 构造方法
     *
     * @param code
     */
    ErrorCodeInfo(int code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param code
     * @param msg
     */
    ErrorCodeInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}