package com.yooli.demo.service;

import com.yooli.demo.domain.DubboRequestForm;
import com.yooli.demo.domain.ResultVo;
import com.yooli.demo.domain.TblDubboRegDto;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lx on 2018/10/10.
 */
public interface UserService {
    ResultVo openAccount(String group,String bankName);

    List<TblDubboRegDto> getRegisters();
}
