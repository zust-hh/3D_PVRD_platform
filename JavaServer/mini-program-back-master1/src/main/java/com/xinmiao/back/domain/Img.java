package com.xinmiao.back.domain;

import javax.persistence.*;

public class Img {
    @Id
    @Column(name = "img_id")
    private Integer imgId;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "img_pos")
    private Integer imgPos;

    @Column(name = "scene_id")
    private Integer sceneId;

    /**
     * @return img_id
     */
    public Integer getImgId() {
        return imgId;
    }

    /**
     * @param imgId
     */
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    /**
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * @return img_pos
     */
    public Integer getImgPos() {
        return imgPos;
    }

    /**
     * @param imgPos
     */
    public void setImgPos(Integer imgPos) {
        this.imgPos = imgPos;
    }

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
}