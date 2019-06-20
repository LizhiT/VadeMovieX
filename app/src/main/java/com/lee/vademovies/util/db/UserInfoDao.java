package com.lee.vademovies.util.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lee.vademovies.bean.UserInfo;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 11:38
 * Description  :  DAO for table "USER_INFO".
 */
public class UserInfoDao extends AbstractDao<UserInfo, Long> {

    public static final String TABLENAME = "USER_INFO_BEAN";

    public UserInfoDao(DaoConfig config) {
        super(config);
    }

    public UserInfoDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Properties of entity UserInfoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Birthday = new Property(1, long.class, "birthday", false, "BIRTHDAY");
        public final static Property LastLoginTime = new Property(2, long.class, "lastLoginTime", false, "LAST_LOGIN_TIME");
        public final static Property NickName = new Property(3, String.class, "nickName", false, "NICK_NAME");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Sex = new Property(5, String.class, "sex", false, "SEX");
        public final static Property HeadPic = new Property(6, String.class, "headPic", false, "HEAD_PIC");
        public final static Property SessionId = new Property(7, String.class, "sessionId", false, "SESSION_ID");
        public final static Property Ttt = new Property(8, int.class, "ttt", false, "TTT");
    }


    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"BIRTHDAY\" INTEGER NOT NULL ," + // 1: birthday
                "\"LAST_LOGIN_TIME\" INTEGER NOT NULL ," + // 2: lastLoginTime
                "\"NICK_NAME\" TEXT," + // 3: nickName
                "\"PHONE\" TEXT," + // 4: phone
                "\"SEX\" TEXT," + // 5: sex
                "\"HEAD_PIC\" TEXT," + // 6: headPic
                "\"SESSION_ID\" TEXT," + // 7: sessionId
                "\"TTT\" INTEGER NOT NULL );"); // 8: ttt
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getBirthday());
        stmt.bindLong(3, entity.getLastLoginTime());

        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }

        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(6, sex);
        }

        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(7, headPic);
        }

        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(8, sessionId);
        }
        stmt.bindLong(9, entity.getTtt());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getBirthday());
        stmt.bindLong(3, entity.getLastLoginTime());

        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(4, nickName);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }

        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(6, sex);
        }

        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(7, headPic);
        }

        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(8, sessionId);
        }
        stmt.bindLong(9, entity.getTtt());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getLong(offset + 1), // birthday
                cursor.getLong(offset + 2), // lastLoginTime
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nickName
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // sex
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // headPic
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // sessionId
                cursor.getInt(offset + 8) // ttt
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBirthday(cursor.getLong(offset + 1));
        entity.setLastLoginTime(cursor.getLong(offset + 2));
        entity.setNickName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSex(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setHeadPic(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSessionId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTtt(cursor.getInt(offset + 8));
    }

    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(UserInfo entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}
