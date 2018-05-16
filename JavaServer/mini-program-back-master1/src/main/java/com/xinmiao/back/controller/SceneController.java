package com.xinmiao.back.controller;

import com.xinmiao.back.domain.Img;
import com.xinmiao.back.domain.Scene;
import com.xinmiao.back.dto.SceneEx;
import com.xinmiao.back.mapper.ImgMapper;
import com.xinmiao.back.mapper.SceneMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/scenes")
public class SceneController {
    @Resource
    SceneMapper sceneMapper;
    @Resource
    ImgMapper imgMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<SceneEx>  getScenesByCompanyId(@RequestParam Integer id){
        List<Scene> scenes = sceneMapper.selectScenesByCompanyId(id);
        List<SceneEx> sceneExes = new ArrayList<SceneEx>();
        for(Scene scene : scenes){
            SceneEx sceneEx = new SceneEx();
            sceneEx.setCompanyId(scene.getCompanyId());
            sceneEx.setSceneName(scene.getSceneName());
            sceneEx.setSceneNum(scene.getSceneNum());
            sceneEx.setSceneId(scene.getSceneId());
            List<Img> imgs = imgMapper.selectImgsBySceneId(scene.getSceneId());
            sceneEx.setImgList(imgs);
            sceneExes.add(sceneEx);
        }
        return sceneExes;
    }
}
