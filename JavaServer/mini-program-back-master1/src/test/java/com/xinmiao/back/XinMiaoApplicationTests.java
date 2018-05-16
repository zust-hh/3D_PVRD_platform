package com.xinmiao.back;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.xinmiao.back.domain.Company;
import com.xinmiao.back.mapper.CompanyMapper;
import com.xinmiao.back.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XinMiaoApplicationTests {
    @Autowired
    CompanyMapper companyMapper;
    @Test
    public void test(){
        List<Company> companyList = companyMapper.selectCompanys(1);
        System.out.println(JSON.toJSONString(companyList));
    }
}
