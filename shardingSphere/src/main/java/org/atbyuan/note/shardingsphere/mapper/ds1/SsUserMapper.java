package org.atbyuan.note.shardingsphere.mapper.ds1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.atbyuan.note.common.entity.SsUser;

import java.util.List;

/**
 * @author atbyuan
 * @since 2021/9/4 20:34
 **/
@Mapper
public interface SsUserMapper extends BaseMapper<SsUser> {

    List<SsUser> findByEmail(@Param("email") String email);

}