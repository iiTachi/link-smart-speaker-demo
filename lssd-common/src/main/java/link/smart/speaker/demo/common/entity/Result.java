package link.smart.speaker.demo.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mylitboy
 * @date 2020/6/13
 */
@Data

public class Result<T> implements Serializable {
    /**
     * 错误码
     */
    private int code;
    /**
     * 结果说明
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 返回成功对象，无Data数据。
     *
     * @return
     */
    public static Result success() {
        return success(new Object());
    }

    /**
     * 返回成功对象(code: 200，message: Success)
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setData(data);
        result.setCode(ErrorCodeInfo.SUCCESS.code);
        result.setMsg("Success");
        return result;
    }

    /**
     * 返回通用错误对象，通用Msg(code: -1，msg: Failed, data: null)
     *
     * @return
     */
    public static Result error() {
        return error("Failed");
    }

    /**
     * 返回通用错误对象(code: -1，data: null)
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ErrorCodeInfo.ERROR.code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回指定错误码的错误
     *
     * @param errorCode
     * @return
     */
    public static Result error(ErrorCodeInfo errorCode) {
        Result result = new Result();
        result.setCode(errorCode.code);
        result.setMsg(errorCode.msg);
        return result;
    }
}
