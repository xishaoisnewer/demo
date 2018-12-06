package com.yooli.demo.dao;

import com.yooli.demo.domain.TblDubboRegDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lx on 2018/10/8.
 */
//@Repository("dubboRegMapper")
@Mapper
public interface TblDubboRegMapper {
    TblDubboRegDto selectByNameAndGroup(HashMap map);

    List<TblDubboRegDto> getRegisters();

    Long insertRegister(TblDubboRegDto regDto);
}
