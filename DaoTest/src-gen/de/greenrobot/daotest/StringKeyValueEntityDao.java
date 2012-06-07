package de.greenrobot.daotest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import de.greenrobot.daotest.StringKeyValueEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STRING_KEY_VALUE_ENTITY.
*/
public class StringKeyValueEntityDao extends AbstractDao<StringKeyValueEntity, String> {

    public static final String TABLENAME = "STRING_KEY_VALUE_ENTITY";

    /**
     * Properties of entity StringKeyValueEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Key = new Property(0, String.class, "key", true, "KEY");
        public final static Property Value = new Property(1, String.class, "value", false, "VALUE");
    };


    public StringKeyValueEntityDao(DaoConfig config) {
        super(config);
    }
    
    public StringKeyValueEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'STRING_KEY_VALUE_ENTITY' (" + //
                "'KEY' TEXT PRIMARY KEY NOT NULL ," + // 0: key
                "'VALUE' TEXT);"; // 1: value
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STRING_KEY_VALUE_ENTITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, StringKeyValueEntity entity) {
        stmt.clearBindings();
 
        String key = entity.getKey();
        if (key != null) {
            stmt.bindString(1, key);
        }
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public StringKeyValueEntity readEntity(Cursor cursor, int offset) {
        StringKeyValueEntity entity = new StringKeyValueEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // key
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // value
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, StringKeyValueEntity entity, int offset) {
        entity.setKey(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(StringKeyValueEntity entity, long rowId) {
        return entity.getKey();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(StringKeyValueEntity entity) {
        if(entity != null) {
            return entity.getKey();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
