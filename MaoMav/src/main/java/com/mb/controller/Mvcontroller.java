package com.mb.controller;

import com.google.gson.Gson;
import com.mb.service.srviceimp.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maobo on 2016/10/16.
 */
@Controller
@RequestMapping("/mvc")
public class Mvcontroller {

    @Resource
   private UserService userServiceImp;

        @RequestMapping("/hello")
        public String hello(){
            return "hello";
        }

    //pass the parameters to front-end using ajax
    @RequestMapping("/getajax")
    public void getPerson(@RequestParam(value="name")String name,@RequestParam(value="pwd")String pwd, PrintWriter pw){
        List<String> list = new ArrayList<String>(5);
        list.add(name);
        list.add(pwd);
        Gson gson = new Gson();
        String js = gson.toJson(list);
        userServiceImp.findUser();
        pw.write(js);
        pw.close();
    }


    @RequestMapping("/name")
    public String sayHello(){
        return "name";
    }

}
