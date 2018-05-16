package com.xinmiao.back.controller;

import com.xinmiao.back.domain.InApply;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//入住申请
@RestController
@RequestMapping("/api/inApply")
public class InApplyController {
    @RequestMapping("")
    public Boolean inApply(@RequestBody  InApply inApply){

        return false;
    }

    @GetMapping("/getInapplyList")
    public List<InApply> inapplyList(){
        return null;
    }

    @PostMapping("/agree")
    public Boolean agreeInApply(Integer id){
        return false;
    }

    @PostMapping("/disagree")
    public Boolean disagreeInApply(Integer id){
        return false;
    }
}
