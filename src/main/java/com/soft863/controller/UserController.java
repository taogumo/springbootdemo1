package com.soft863.controller;

import com.alibaba.fastjson.JSON;
import com.soft863.common.ResultInfo;
import com.soft863.entity.User;
import com.soft863.service.IUserService;
import com.soft863.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-12 10:55
 **/
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;


    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser() {
        User user = userService.getUserByID (2);
        return user.toString ();
    }

    @GetMapping("/home")
    public String homePage1() {
        return "main";
    }

    @PostMapping("/home1")
    public String homePage2() {
        return "main";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String homePage() {
        return "main";
    }


  /*  @GetMapping("/send/{message}")
    @ResponseBody
    public String send(@PathVariable String message) {
        kafkaTemplate.send ("testwcj", message);
        return "success";
    }


    @KafkaListener(topics = "testwcj")
    public void onMessage(String message) {
        System.out.println ("收到消息****：" + message);
        Jedis jedis = RedisUtil.getInstance ();
        if (message.startsWith ("first")) {
            jedis.set ("loginfirst", message);
        } else if (message.startsWith ("end")) {
            jedis.set ("loginend", message);
        }
    }*/

    @RequestMapping(value = "/log11", method = RequestMethod.POST)
    public String login51(User user) {
//        kafkaTemplate.send ("testwcj", "first:" + user.toString ());
        ResultInfo resultInfo = null;
        try {
            User userResult = userService.logUser (user);
            if (userResult != null) {
                resultInfo = new ResultInfo (true, userResult, "成功");
            } else {
                resultInfo = new ResultInfo (false, null, "未找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            resultInfo = new ResultInfo (false, null, e.toString ());

        }
        String result = JSON.toJSONString (resultInfo);
//        kafkaTemplate.send ("testwcj", "end:" + result.toString ());
        System.out.println (result);

        return "login1.html";
    }

    @RequestMapping(value = "/log12", method = RequestMethod.POST)
    public ModelAndView login12(User user) {
//        kafkaTemplate.send ("testwcj", "first:" + user.toString ());
        ResultInfo resultInfo = null;
        try {
            User userResult = userService.logUser (user);
            if (userResult != null) {
                resultInfo = new ResultInfo (true, userResult, "成功");
            } else {
                resultInfo = new ResultInfo (false, null, "未找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            resultInfo = new ResultInfo (false, null, e.toString ());

        }
        String result = JSON.toJSONString (resultInfo);
//        kafkaTemplate.send ("testwcj", "end:" + result.toString ());
        System.out.println (result);
        ModelAndView modelAndView = new ModelAndView ();
        modelAndView.setViewName ("login1");
        modelAndView.addObject ("msg","执行成功");
        return modelAndView;
    }

    @RequestMapping(value = "/log1", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo login1(User user) {
        ResultInfo resultInfo = null;
        try {
            User userResult = userService.logUser (user);
            if (userResult != null) {
                resultInfo = new ResultInfo (true, userResult, "成功");
            } else {
                resultInfo = new ResultInfo (false, null, "未找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            resultInfo = new ResultInfo (false, null, e.toString ());

        }
        String result = JSON.toJSONString (resultInfo);
        System.out.println (result);
        return resultInfo;
    }




    @GetMapping("/queryAll")
    public String queryAll(Model model) {
        List<User> userList = userService.getAllUsers ();
        model.addAttribute ("title", "测试标题");
        model.addAttribute ("user", userList.get (0));
        model.addAttribute ("userList", userList);
        return "result";
    }

    @RequestMapping("/query/{id}")
    public String queryUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserByID (id);
        model.addAttribute ("title", "用户信息");
        model.addAttribute ("user", user);
        return "detail";
    }

    @RequestMapping("/query1/{id}")
    public String queryUser1(@PathVariable("id") int id, Model model) {
        User user = userService.getUserByID1 (id);
        model.addAttribute ("title", "用户信息");
        model.addAttribute ("user", user);
        return "detail";
    }

}
