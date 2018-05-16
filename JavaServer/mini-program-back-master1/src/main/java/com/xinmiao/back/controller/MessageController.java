package com.xinmiao.back.controller;

import com.xinmiao.back.domain.SubComment;
import com.xinmiao.back.dto.Message;
import com.xinmiao.back.mapper.CommentMapper;
import com.xinmiao.back.mapper.SubCommentMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Resource
    SubCommentMapper subCommentMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<SubComment> getMyMessageByName(String name){
        List<SubComment> subCommentList = subCommentMapper.selectSubCommentsByBName(name);
        return subCommentList;
    }

}
