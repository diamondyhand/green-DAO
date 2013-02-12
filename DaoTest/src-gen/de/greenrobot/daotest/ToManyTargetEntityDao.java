package de.greenrobot.daotest;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import de.greenrobot.daotest.ToManyTargetEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TO_MANY_TARGET_ENTITY.
*/
public class ToManyTargetEntityDao extends AbstractDao<ToManyTargetEntity, Long> {

    public static final String TABLENAME = "TO_MANY_TARGET_ENTITY";

    /**
     * Properties of entity ToManyTargetEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ToManyId = new Property(0, Long.class, "toManyId", false, "TO_MANY_ID");
        public final static Property ToManyIdDesc = new Property(1, Long.class, "toManyIdDesc", false, "TO_MANY_ID_DESC");
        public final static Property Id = new Property(2, Long.class, "id", true, "_id");
        public final static Property TargetJoinProperty = new Property(3, String.class, "targetJoinProperty", false, "TARGET_JOIN_PROPERTY");
    };

    private Query<ToManyTargetEntity> toManyEntity_ToManyTargetEntityListQuery;
    private Query<ToManyTargetEntity> toManyEntity_ToManyDescListQuery;
    private Query<ToManyTargetEntity> toManyEntity_ToManyByJoinPropertyQuery;
    private Query<ToManyTargetEntity> toManyEntity_ToManyJoinTwoQuery;

    public ToManyTargetEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ToManyTargetEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TO_MANY_TARGET_ENTITY' (" + //
                "'TO_MANY_ID' INTEGER," + // 0: toManyId
                "'TO_MANY_ID_DESC' INTEGER," + // 1: toManyIdDesc
                "'_id' INTEGER PRIMARY KEY ," + // 2: id
                "'TARGET_JOIN_PROPERTY' TEXT);"); // 3: targetJoinProperty
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TO_MANY_TARGET_ENTITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ToManyTargetEntity entity) {
        stmt.clearBindings();
 
        Long toManyId = entity.getToManyId();
        if (toManyId != null) {
            stmt.bindLong(1, toManyId);
        }
 
        Long toManyIdDesc = entity.getToManyIdDesc();
        if (toManyIdDesc != null) {
            stmt.bindLong(2, toManyIdDesc);
        }
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(3, id);
        }
 
        String targetJoinProperty = entity.getTargetJoinProperty();
        if (targetJoinProperty != null) {
            stmt.bindString(4, targetJoinProperty);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2);
    }    

    /** @inheritdoc */
    @Override
    public ToManyTargetEntity readEntity(Cursor cursor, int offset) {
        ToManyTargetEntity entity = new ToManyTargetEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // toManyId
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // toManyIdDesc
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // targetJoinProperty
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ToManyTargetEntity entity, int offset) {
        entity.setToManyId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setToManyIdDesc(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTargetJoinProperty(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ToManyTargetEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ToManyTargetEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "toManyTargetEntityList" to-many relationship of ToManyEntity. */
    public synchronized List<ToManyTargetEntity> _queryToManyEntity_ToManyTargetEntityList(Long toManyId) {
        if (toManyEntity_ToManyTargetEntityListQuery == null) {
            QueryBuilder<ToManyTargetEntity> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.ToManyId.eq(toManyId));
            queryBuilder.orderRaw("_id ASC");
            toManyEntity_ToManyTargetEntityListQuery = queryBuilder.build();
        } else {
            toManyEntity_ToManyTargetEntityListQuery.setParameter(0, toManyId);
        }
        return toManyEntity_ToManyTargetEntityListQuery.list();
    }

    /** Internal query to resolve the "ToManyDescList" to-many relationship of ToManyEntity. */
    public synchronized List<ToManyTargetEntity> _queryToManyEntity_ToManyDescList(Long toManyIdDesc) {
        if (toManyEntity_ToManyDescListQuery == null) {
            QueryBuilder<ToManyTargetEntity> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.ToManyIdDesc.eq(toManyIdDesc));
            queryBuilder.orderRaw("_id DESC");
            toManyEntity_ToManyDescListQuery = queryBuilder.build();
        } else {
            toManyEntity_ToManyDescListQuery.setParameter(0, toManyIdDesc);
        }
        return toManyEntity_ToManyDescListQuery.list();
    }

    /** Internal query to resolve the "ToManyByJoinProperty" to-many relationship of ToManyEntity. */
    public synchronized List<ToManyTargetEntity> _queryToManyEntity_ToManyByJoinProperty(String targetJoinProperty) {
        if (toManyEntity_ToManyByJoinPropertyQuery == null) {
            QueryBuilder<ToManyTargetEntity> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.TargetJoinProperty.eq(targetJoinProperty));
            queryBuilder.orderRaw("_id ASC");
            toManyEntity_ToManyByJoinPropertyQuery = queryBuilder.build();
        } else {
            toManyEntity_ToManyByJoinPropertyQuery.setParameter(0, targetJoinProperty);
        }
        return toManyEntity_ToManyByJoinPropertyQuery.list();
    }

    /** Internal query to resolve the "ToManyJoinTwo" to-many relationship of ToManyEntity. */
    public synchronized List<ToManyTargetEntity> _queryToManyEntity_ToManyJoinTwo(Long toManyId, String targetJoinProperty) {
        if (toManyEntity_ToManyJoinTwoQuery == null) {
            QueryBuilder<ToManyTargetEntity> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.ToManyId.eq(toManyId));
            queryBuilder.where(Properties.TargetJoinProperty.eq(targetJoinProperty));
            queryBuilder.orderRaw("TARGET_JOIN_PROPERTY DESC,_id DESC");
            toManyEntity_ToManyJoinTwoQuery = queryBuilder.build();
        } else {
            toManyEntity_ToManyJoinTwoQuery.setParameter(0, toManyId);
            toManyEntity_ToManyJoinTwoQuery.setParameter(1, targetJoinProperty);
        }
        return toManyEntity_ToManyJoinTwoQuery.list();
    }

}
