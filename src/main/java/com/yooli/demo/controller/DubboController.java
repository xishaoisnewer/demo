package com.yooli.demo.controller;

import com.yooli.demo.domain.DubboRequestForm;
import com.yooli.demo.domain.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lx on 2018/10/10.
 */
@Controller
@RequestMapping(value = "/dubbo")
public class DubboController {
    private final Logger logger= LoggerFactory.getLogger(DubboController.class);

/*    @Autowired
    TblDubboRegService dubboRegServiceImpl;*/

    @RequestMapping(value = "/genericInvoke",method = RequestMethod.POST)
    @ResponseBody
    public String genericInvokeDubboMethod(String group,String bankName){
        String a="";
        return  a;
    }

    private ResultVo commonCheck(DubboRequestForm param){
        ResultVo resultVo=new ResultVo();
        resultVo.setCode("00");
        resultVo.setMsg("success");
        if (StringUtils.isEmpty(param.getTblDubboRegDto().getName())){
            resultVo.setCode("01");
            resultVo.setMsg("applicationname is null");
            return resultVo;
        }
        else if (StringUtils.isEmpty(param.getTblDubboRegDto().getIp())){
            resultVo.setCode("01");
            resultVo.setMsg("ip is null");
            return resultVo;
        }
        else if (StringUtils.isEmpty(param.getTblDubboRegDto().getVersion())){
            resultVo.setCode("01");
            resultVo.setMsg("dubbo version is null");
            return resultVo;
        }
        else if (StringUtils.isEmpty(param.getInterfaceName())){
            resultVo.setCode("01");
            resultVo.setMsg("dubbo interface is null");
            return resultVo;
        }
        return resultVo;
    }
}
