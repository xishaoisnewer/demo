package com.yooli.demo.service.impl;

import com.yooli.demo.consts.RandomNum;
import com.yooli.demo.dao.TblDubboRegMapper;
import com.yooli.demo.domain.*;
import com.yooli.demo.manager.rpc.DubboInterfaceService;
import com.yooli.demo.manager.mapperWraper.IUserManager;
import com.yooli.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lx on 2018/10/10.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    IUserManager userManager;

    @Autowired
    DubboInterfaceService dubboInterfaceServiceImpl;

    @Autowired
    TblDubboRegMapper dubboRegMapper;

    public final static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    public ResultVo openAccount(String group,String bankName){
        ResultVo resultVo=new ResultVo();
        TblDubboRegDto tblDubboRegDto= userManager.selectByNameAndGroup("ums",group);
        DubboRequestForm form=new DubboRequestForm();
        form.setTblDubboRegDto(tblDubboRegDto);
        form.setInterfaceName("com.yooli.ums.api.baseinfo.facade.IUserRegisterTempFacade");
        form.setMethod("addMobileRegisterTempUser");
        List<DubboRequestParam> params=new ArrayList<DubboRequestParam>();
        DubboRequestParam param1=new DubboRequestParam();
        UserRegisterTempDto registerTempDto=new UserRegisterTempDto();
        registerTempDto.setMobile(RandomNum.getTel());
        param1.setParamType("com.yooli.ums.api.baseinfo.dto.UserRegisterTempDto");
        param1.setParamVale(registerTempDto);
        params.add(param1);
        form.setParams(params);
        try {
            resultVo = dubboInterfaceServiceImpl.genericInvokeMethod(form);
        }catch (Exception e){
            resultVo.setMsg("01-ums-addMobileRegisterTempUser fail");
            resultVo.setCode("02");
        }
        return resultVo;
    }

    public List<TblDubboRegDto> getRegisters(){
         List<TblDubboRegDto> list = dubboRegMapper.getRegisters();
        return list;

    }
}
