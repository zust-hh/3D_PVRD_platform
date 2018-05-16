package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.Scene;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SceneMapper extends MyMapper<Scene> {
    @Select("select * from scene where company_id=#{id}")
    @ResultMap("BaseResultMap")
    public List<Scene> selectScenesByCompanyId(Integer id);
}