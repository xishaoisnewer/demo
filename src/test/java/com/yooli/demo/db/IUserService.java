package com.yooli.demo.db;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shaoxi.zhu
 * Date: 2018/11/15
 * Time: 5:13 PM
 */
@Transactional(propagation = Propagation.REQUIRED)

public interface IUserService {
}
