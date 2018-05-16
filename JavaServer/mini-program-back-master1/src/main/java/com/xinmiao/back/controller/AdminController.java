package com.xinmiao.back.controller;

import com.xinmiao.back.domain.Company;
import com.xinmiao.back.domain.InApply;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/getAllCompany")
    public List<Object> allCompany(){
        return null;
    }

    @PostMapping("")
    public Boolean inApply(@RequestBody Company company){
        return false;
    }


}
