package com.lee.vademovies.util.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lee.vademovies.bean.UserInfo;

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

    public static final String TABLENAME = "USER_INFO";

    /**
     * Properties of entity UserInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property UserId = new Property(0, long.class, "userId", true, "_id");
        public final static Property HeadPic = new Property(1, String.class, "headPic", false, "HEAD_PIC");
        public final static Property NickName = new Property(2, String.class, "nickName", false, "NICK_NAME");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property SessionId = new Property(4, String.class, "sessionId", false, "SESSION_ID");
        public final static Property Sex = new Property(5, int.class, "sex", false, "SEX");
        public final static Property Status = new Property(6, int.class, "status", false, "STATUS");
    }


    public UserInfoDao(DaoConfig config) {
        super(config);
    }

    public UserInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: userId
                "\"HEAD_PIC\" TEXT," + // 1: headPic
                "\"NICK_NAME\" TEXT," + // 2: nickName
                "\"PHONE\" TEXT," + // 3: phone
                "\"SESSION_ID\" TEXT," + // 4: sessionId
                "\"SEX\" INTEGER NOT NULL ," + // 5: sex
                "\"STATUS\" INTEGER NOT NULL );"); // 6: status
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());

        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }

        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(3, nickName);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }

        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(5, sessionId);
        }
        stmt.bindLong(6, entity.getSex());
        stmt.bindLong(7, entity.getStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInfo entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());

        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(2, headPic);
        }

        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(3, nickName);
        }

        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }

        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(5, sessionId);
        }
        stmt.bindLong(6, entity.getSex());
        stmt.bindLong(7, entity.getStatus());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }

    @Override
    public UserInfo readEntity(Cursor cursor, int offset) {
        UserInfo entity = new UserInfo( //
                cursor.getLong(offset + 0), // userId
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // headPic
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // nickName
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sessionId
                cursor.getInt(offset + 5), // sex
                cursor.getInt(offset + 6) // status
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, UserInfo entity, int offset) {
        entity.setUserId(cursor.getLong(offset + 0));
        entity.setHeadPic(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNickName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSessionId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSex(cursor.getInt(offset + 5));
        entity.setStatus(cursor.getInt(offset + 6));
    }

    @Override
    protected final Long updateKeyAfterInsert(UserInfo entity, long rowId) {
        entity.setUserId(rowId);
        return rowId;
    }

    @Override
    public Long getKey(UserInfo entity) {
        if (entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInfo entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

}