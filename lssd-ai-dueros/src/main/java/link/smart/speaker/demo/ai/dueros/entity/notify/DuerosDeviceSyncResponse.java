package link.smart.speaker.demo.ai.dueros.entity.notify;

import lombok.Data;

/**
 * @author mylitboy
 * @date 2020/6/23
 */
@Data
public class DuerosDeviceSyncResponse<T> {
    /**
     * 错误码
     */
    private int status;
    /**
     * 结果说明
     */
    private String msg;
    /**
     * 请求消息中的logid
     */
    private String logId;
    /**
     * 响应数据
     */
    private T data;

}
