package com.yooli.demo.manager.mapperWraper;

import com.yooli.demo.domain.TblDubboRegDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lx on 2018/10/10.
 */
public interface IUserManager {
    TblDubboRegDto selectByNameAndGroup(String name,String group);
}
