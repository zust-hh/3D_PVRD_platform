package com.xinmiao.back.domain;

import javax.persistence.*;
import java.util.List;

public class Scene {
    @Id
    @Column(name = "scene_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sceneId;

    @Column(name = "scene_name")
    private String sceneName;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "scene_num")
    private Integer sceneNum;

    /**
     * @return scene_id
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * @param sceneId
     */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * @return scene_name
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * @param sceneName
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    /**
     * @return company_id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * @return scene_num
     */
    public Integer getSceneNum() {
        return sceneNum;
    }

    /**
     * @param sceneNum
     */
    public void setSceneNum(Integer sceneNum) {
        this.sceneNum = sceneNum;
    }
}