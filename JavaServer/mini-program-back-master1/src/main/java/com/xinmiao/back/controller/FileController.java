package com.xinmiao.back.controller;

import com.alibaba.fastjson.JSON;
//import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.xinmiao.back.domain.Company;
import com.xinmiao.back.domain.Img;
import com.xinmiao.back.domain.InApply;
import com.xinmiao.back.domain.Scene;
import com.xinmiao.back.dto.RespJson;
import com.xinmiao.back.mapper.CompanyMapper;
import com.xinmiao.back.mapper.ImgMapper;
import com.xinmiao.back.mapper.InApplyMapper;
import com.xinmiao.back.mapper.SceneMapper;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Resource
    SceneMapper sceneMapper;

    @Resource
    CompanyMapper companyMapper;

    @Resource
    ImgMapper imgMapper;
    @Resource
    InApplyMapper inApplyMapper;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private String fildUpload(@RequestParam(value = "companyAddress") String companyAddress,
                              @RequestParam(value = "companyCeo") String companyCeo,
                              @RequestParam(value = "companyDescription") String companyDescription,
                              @RequestParam(value = "companyName") String companyName,
                              @RequestParam(value = "companyTags") String[] companyTags,
                              @RequestParam(value = "companyType") Integer companyType,
                              @RequestParam(value = "userId") Integer userId,

                              @RequestParam(value = "sceneNames") String[] sceneNames,
                              @RequestParam(value = "bookImage") MultipartFile bookImg,
                              @RequestParam(value = "img1") MultipartFile[] img1,
                              @RequestParam(value = "img2") MultipartFile[] img2,
                              @RequestParam(value = "img3") MultipartFile[] img3,
                              @RequestParam(value = "img4") MultipartFile[] img4,
                              @RequestParam(value = "img5") MultipartFile[] img5,
                              @RequestParam(value = "img6") MultipartFile[] img6,
                              HttpServletRequest request) throws Exception {
        //基本表单
        Company company = new Company();
        company.setCompanyAddress(companyAddress);
        company.setCompanyCeo(companyCeo);
        company.setCompanyDescription(companyDescription);
        company.setCompanyType(companyType);
        company.setCompanyName(companyName);
        String tags = String.join("/",companyTags);
        company.setCompanyTags(tags);
        company.setCompanyUrl("");
        company.setUserId(userId);

        System.out.println(JSON.toJSONString(company));
        System.out.println(JSON.toJSONString(sceneNames));
        String pathRoot = request.getSession().getServletContext().getRealPath("");

        System.out.println(sceneNames[0]);
        System.out.println(bookImg);
        System.out.println(img1);
        System.out.println(img2);
        //插入公司信息
        String bookImgUrl = save(pathRoot, "bookimg", bookImg, -1, -1);
        bookImgUrl = bookImgUrl.substring(bookImgUrl.lastIndexOf("images")-1);
        company.setBookImg(bookImgUrl);
        companyMapper.insert(company);
        InApply inApply = new InApply();
        inApply.setInApplyUserId(company.getUserId());
        inApply.setInApplyStatus("待审核");
        inApplyMapper.insert(inApply);

        for (int i = 0; i < sceneNames.length; i++) {
            Scene scene = new Scene();
            scene.setCompanyId(company.getCompanyId());

            scene.setSceneName(sceneNames[i]);
            scene.setSceneNum(i + 1);
            //插入每个场景
            sceneMapper.insertSelective(scene);
            //插入每个场景的每张图片
            save(pathRoot, "img1", img1[i], 1, scene.getSceneId());
            save(pathRoot, "img2", img2[i], 2, scene.getSceneId());
            save(pathRoot, "img3", img3[i], 3, scene.getSceneId());
            save(pathRoot, "img4", img4[i], 4, scene.getSceneId());
            save(pathRoot, "img5", img5[i], 5, scene.getSceneId());
            save(pathRoot, "img6", img6[i], 6, scene.getSceneId());
        }
        //request.setAttribute("imagesPathList", listImagePath);
        return "success";
    }

    private String save(String pathRoot, String dir, MultipartFile img, Integer pos, Integer sceneId) {
        String dirpath = pathRoot + "images/" + dir + "/";
        String path = null;

        if (!img.isEmpty()) {
           path = dirpath + img.getOriginalFilename();
           File file = new File(path);
           System.out.println(path);
           System.out.println(file.getName());
           if(!file.getParentFile().exists()){
               file.getParentFile().mkdirs();
           }
           try {
               img.transferTo(file);
           }catch (Exception e){
               e.printStackTrace();
           }


           if (!pos.equals(-1)) {
               Img m_img = new Img();
               path = path.substring(path.lastIndexOf("images")-1);
               m_img.setImgUrl(path);
               m_img.setSceneId(sceneId);
               m_img.setImgPos(pos);
               imgMapper.insertSelective(m_img);
           }
        }
        return path;
    }



    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public RespJson test(String name,MultipartFile fil,HttpServletRequest request){
        System.out.println("name " + name);
        String pathRoot = request.getSession().getServletContext().getRealPath("/");

        String filepath = save(pathRoot,"test",fil,-1,-1);
        System.out.println(filepath);

        return new RespJson("","ok",200);
    }
}
