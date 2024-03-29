package com.lee.vademovies.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created :  LiZhIX
 * Date :  2019/6/21 11:11
 * Description  :
 */

@Entity
public class UserInfo {
    @Id
    long userId;
    String headPic;
    String nickName;
    String phone;
    @Property(nameInDb = "SESSION_ID")
    String sessionId;
    int sex;

    int status;//记录本地用户登录状态，用于直接登录和退出,1:登录，0：未登录或退出

    @Generated(hash = 836882664)
    public UserInfo(long userId, String headPic, String nickName, String phone,
            String sessionId, int sex, int status) {
        this.userId = userId;
        this.headPic = headPic;
        this.nickName = nickName;
        this.phone = phone;
        this.sessionId = sessionId;
        this.sex = sex;
        this.status = status;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return this.headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
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

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
