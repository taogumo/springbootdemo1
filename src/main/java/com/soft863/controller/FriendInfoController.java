package com.soft863.controller;

import com.alibaba.fastjson.JSON;
import com.soft863.entity.FriendInfo;
import com.soft863.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-08 11:25
 **/
@Controller
@RequestMapping(value = "/friend")
public class FriendInfoController {
    @Autowired
    private IFriendService friendService;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public String getAll() {
        List<FriendInfo> friendInfoList = friendService.selectAll ();
        String result = JSON.toJSONString (friendInfoList);
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(FriendInfo friendInfo) {
        int res = friendService.insert (friendInfo);
        String result = JSON.toJSONString (res);
        return result;
    }
}
