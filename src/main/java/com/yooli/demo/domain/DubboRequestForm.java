package com.yooli.demo.domain;

import java.util.List;

/**
 * Created by lx on 2018/10/9.
 */
public class DubboRequestForm {
    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public TblDubboRegDto getTblDubboRegDto() {
        return tblDubboRegDto;
    }

    public void setTblDubboRegDto(TblDubboRegDto tblDubboRegDto) {
        this.tblDubboRegDto = tblDubboRegDto;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<DubboRequestParam> getParams() {
        return params;
    }

    public void setParams(List<DubboRequestParam> params) {
        this.params = params;
    }

    private String interfaceName;
    private TblDubboRegDto tblDubboRegDto;
    private String method;
    private List<DubboRequestParam> params;
}
