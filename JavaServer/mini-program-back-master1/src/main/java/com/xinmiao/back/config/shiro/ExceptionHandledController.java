package com.xinmiao.back.config.shiro;


import com.xinmiao.back.config.status.AuthorStatus;
import com.xinmiao.back.dto.RespJson;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandledController {
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespJson unauthorized() {
        RespJson respJson = new RespJson(null,AuthorStatus.PERMISSION_DENIED.name(),AuthorStatus.PERMISSION_DENIED.getCode());//权限不够
        return respJson;
    }

    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespJson unauthenticated() {
        RespJson respJson = new RespJson(null,AuthorStatus.UNAUTHENTICATED.name(),AuthorStatus.UNAUTHENTICATED.getCode());
        return respJson;
    }
}
