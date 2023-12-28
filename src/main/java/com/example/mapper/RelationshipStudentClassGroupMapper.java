package com.example.mapper;

import com.example.entity.RelationshipStudentClassGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sending
 * @since 2023-12-28
 */
public interface RelationshipStudentClassGroupMapper extends MPJBaseMapper<RelationshipStudentClassGroup> {
// 自定义针对复合主键的查询方法
//RelationshipStudentClassGroup selectByCompositeKey(@Param("studentId") int studentId, @Param("groupId")  int groupId);

}
