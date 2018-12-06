package com.yooli.demo.controller;

import com.yooli.demo.consts.EnvBankGroupDef;
import com.yooli.demo.consts.EnvGroupDef;
import com.yooli.demo.domain.ResultVo;
import com.yooli.demo.domain.TblDubboRegDto;
import com.yooli.demo.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


/**
 * Created by lx on 2018/9/28.
 */
@Controller
@RequestMapping("/userManager")
public class UserManagerController {
    private static Logger logger= LoggerFactory.getLogger(UserManagerController.class);

    @Autowired(required = true)
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String userManageGroup(Model model,HttpServletRequest servletRequest){
        model.addAttribute("group", EnvGroupDef.groupMap);
        model.addAttribute("bankName", EnvBankGroupDef.getEnumValueMap());
        return "user";
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public ResultVo userManageAdd(Model model,String group){
        ResultVo resultVo= userService.openAccount(group,"招商银行");
        return resultVo;
    }

    @RequestMapping(value = "/getRegisters")
    @ResponseBody
    public List<TblDubboRegDto> getRegisters(){
        return userService.getRegisters();

    }

}
