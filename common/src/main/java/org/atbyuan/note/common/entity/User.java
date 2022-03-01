package org.atbyuan.note.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author atbyuan
 * @date 2021/8/1 15:47
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String mobile;
    private String password;
    private Date createTime;
    private Date updateTime;

}
