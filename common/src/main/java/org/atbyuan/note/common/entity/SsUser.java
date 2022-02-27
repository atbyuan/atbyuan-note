package org.atbyuan.note.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author atbyuan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SsUser {

    /**
     * 用户id
     */
    private Object id;

    /**
     * 昵称
     */
    private String userName;

    private String password;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态：1启用，0禁用
     */
    private Object status;

    /**
     * 密码是否需要重置 0不需要重置 1需要重置
     */
    private Integer resetStatus;

    private String creator;
    /**
     * 创建时间
     */
    private Timestamp createTime;

    private String modifier;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    private Integer deleted;
}
