package com.yooli.demo.manager.mapperWraper.impl;

import com.yooli.demo.dao.TblDubboRegMapper;
import com.yooli.demo.domain.TblDubboRegDto;
import com.yooli.demo.manager.mapperWraper.IUserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lx on 2018/10/10.
 */
@Service
public class UserManagerImpl implements IUserManager {
    public static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    TblDubboRegMapper dubboRegMapper;

    public TblDubboRegDto selectByNameAndGroup(String name, String group) {
        TblDubboRegDto tblDubboRegDto = new TblDubboRegDto();
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("groupp", group);
        tblDubboRegDto = dubboRegMapper.selectByNameAndGroup(map);
        return tblDubboRegDto;
    }
}
