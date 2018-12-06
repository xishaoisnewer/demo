package com.yooli.demo.domain;

import java.util.Date;

/**
 * Created by lx on 2018/10/10.
 */
public class UserRegisterTempDto {
    /**
     * 临时表ID
     */
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     *邮件
     */
    private String email;

    /**
     *用户昵称
     */
    private String nickName;

    /**
     *密码
     */
    private String passwd;

    /**
     *注册时间
     */
    private Date registerTime;

    /**
     *角色，参见com.fuscent.core.consts.user.UserRolesType
     */
    private Short roles;

    /**
     * 安全问题
     */
    private String question;

    /**
     * 安全问题回答
     */
    private String answer;

    /**
     * 新浪微博uid
     */
    private String weiboUId;

    /**
     * 新浪微博AccessToken
     */
    private String weiboAccessToken;

    /**
     * 腾讯uid
     */
    private String qqUId;

    /**
     * 腾讯AccessToken
     */
    private String qqAccessToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Short getRoles() {
        return roles;
    }

    public void setRoles(Short roles) {
        this.roles = roles;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getWeiboUId() {
        return weiboUId;
    }

    public void setWeiboUId(String weiboUId) {
        this.weiboUId = weiboUId;
    }

    public String getWeiboAccessToken() {
        return weiboAccessToken;
    }

    public void setWeiboAccessToken(String weiboAccessToken) {
        this.weiboAccessToken = weiboAccessToken;
    }

    public String getQqUId() {
        return qqUId;
    }

    public void setQqUId(String qqUId) {
        this.qqUId = qqUId;
    }

    public String getQqAccessToken() {
        return qqAccessToken;
    }

    public void setQqAccessToken(String qqAccessToken) {
        this.qqAccessToken = qqAccessToken;
    }
}
