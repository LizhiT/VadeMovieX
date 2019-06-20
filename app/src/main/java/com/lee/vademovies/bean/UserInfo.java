package com.lee.vademovies.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 11:36
 * Description  :  用户信息
 */

@Entity
public class UserInfo {
    @Id
    private Long id;
    private long birthday;
    private long lastLoginTime;
    private String nickName;
    private String phone;
    private String sex;
    private String headPic;
    private String sessionId;
    private int ttt = 0;

    @Generated(hash = 1367569174)
    public UserInfo(Long id, long birthday, long lastLoginTime, String nickName,
                    String phone, String sex, String headPic, String sessionId, int ttt) {
        this.id = id;
        this.birthday = birthday;
        this.lastLoginTime = lastLoginTime;
        this.nickName = nickName;
        this.phone = phone;
        this.sex = sex;
        this.headPic = headPic;
        this.sessionId = sessionId;
        this.ttt = ttt;
    }

    @Generated(hash = 1818808915)
    public UserInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getBirthday() {
        return this.birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return this.headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getTtt() {
        return this.ttt;
    }

    public void setTtt(int ttt) {
        this.ttt = ttt;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", lastLoginTime=" + lastLoginTime +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", headPic='" + headPic + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", ttt=" + ttt +
                '}';
    }
}
