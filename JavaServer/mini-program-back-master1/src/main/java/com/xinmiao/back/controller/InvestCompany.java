package com.xinmiao.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/investCompany")
public class InvestCompany {

    @GetMapping("/getInvestCompanyList")
    public List<InvestCompany>  investCompanyList(){
        return null;
    }

    @GetMapping("/getInvestCompanyDetail")
    public InvestCompany  investCompanyDetail(Integer id){
        return null;
    }


}

