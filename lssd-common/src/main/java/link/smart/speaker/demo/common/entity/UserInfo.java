package link.smart.speaker.demo.common.entity;

import lombok.Data;

/**
 * 通用用户信息
 *
 * @author mylitboy
 * @date 2020/6/9
 */
@Data
public class UserInfo {
    /**
     * AI方即智能音箱方 UserID
     */
    String openUid;
    /**
     * OAuth2方即厂家方 UserID
     */
    String userId;
}
