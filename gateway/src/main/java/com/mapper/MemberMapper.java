package com.mapper;

import com.domain.Member;

/**
* @Author: 沈佳栋
* @Description: TODO
* @DateTime: 2023/10/9 19:32
**/
public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}