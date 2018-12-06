package com.yooli.demo.manager.rpc;

import com.yooli.demo.domain.DubboRequestForm;
import com.yooli.demo.domain.ResultVo;

/**
 * Created by lx on 2018/10/9.
 */
public interface DubboInterfaceService {
    public ResultVo genericInvokeMethod(DubboRequestForm form) throws Exception;
}
