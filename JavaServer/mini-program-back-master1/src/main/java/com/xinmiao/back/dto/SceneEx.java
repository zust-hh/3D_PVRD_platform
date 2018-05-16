package com.xinmiao.back.dto;

import com.xinmiao.back.domain.Img;
import com.xinmiao.back.domain.Scene;

import java.util.List;

public class SceneEx extends Scene {
    List<Img> imgList;

    public List<Img> getImgList() {
        return imgList;
    }

    public void setImgList(List<Img> imgList) {
        this.imgList = imgList;
    }
}
