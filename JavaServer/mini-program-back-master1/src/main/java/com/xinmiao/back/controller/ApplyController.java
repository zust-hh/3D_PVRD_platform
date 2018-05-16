package com.xinmiao.back.controller;

import com.xinmiao.back.domain.Apply;
import com.xinmiao.back.dto.RespJson;
import com.xinmiao.back.mapper.ApplyMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/apply")
public class ApplyController {
    @Resource
    ApplyMapper applyMapper;


    @PostMapping(value = "")
    public Boolean apply(@RequestBody Apply apply){
        return false;
    }

    @RequestMapping(value = "/getApplyList",method = RequestMethod.GET)
    public List<String> applyList(@RequestParam String name){
        return applyMapper.selectBy2Name(name);
    }

    @PostMapping("/agree")
    public Boolean agreeApply(Integer id){
        return false;
    }

    @PostMapping("/disagree")
    public Boolean disagreeApply(Integer id){
        return false;
    }

    @GetMapping("/isApplying")
    public RespJson isApplying(Integer userId){
        return null;
    }
}
