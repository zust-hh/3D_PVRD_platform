package com.xinmiao.back.mapper;

import com.xinmiao.back.domain.Img;
import com.xinmiao.back.util.MyMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImgMapper extends MyMapper<Img> {
    @Select("select * from img where scene_id=#{id} order by img_pos asc")
    @ResultMap("BaseResultMap")
    public List<Img> selectImgsBySceneId(Integer id);
}