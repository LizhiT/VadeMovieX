package com.lee.vademovies.util.db;

import com.lee.vademovies.bean.UserInfo;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 11:38
 * Description  :
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userInfoDaoConfig;

    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(UserInfo.class, userInfoDao);
    }

    public void clear() {
        userInfoDaoConfig.clearIdentityScope();
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}
