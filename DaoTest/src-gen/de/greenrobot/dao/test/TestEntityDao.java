package de.greenrobot.dao.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;

import de.greenrobot.dao.test.TestEntity;

// THIS CODE IS GENERATED, DO NOT EDIT.
/** 
 * DAO for table TEST_ENTITY (schema version 1).
*/
public class TestEntityDao extends AbstractDao<TestEntity, Long> {

    public static final String TABLENAME = "TEST_ENTITY";

    public static class Properties {
        public final static Property Id = new Property(0, "id", true, "_id");
        public final static Property SimpleInt = new Property(1, "simpleInt", false, "SIMPLE_INT");
        public final static Property SimpleInteger = new Property(2, "simpleInteger", false, "SIMPLE_INTEGER");
        public final static Property SimpleStringNotNull = new Property(3, "simpleStringNotNull", false, "SIMPLE_STRING_NOT_NULL");
        public final static Property SimpleString = new Property(4, "simpleString", false, "SIMPLE_STRING");
        public final static Property IndexedString = new Property(5, "indexedString", false, "INDEXED_STRING");
        public final static Property IndexedStringAscUnique = new Property(6, "indexedStringAscUnique", false, "INDEXED_STRING_ASC_UNIQUE");
    };

    public TestEntityDao(SQLiteDatabase db) {
        super(db);
    }
    
    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "TEST_ENTITY (" + //
                "_id INTEGER PRIMARY KEY ," + // 0
                "SIMPLE_INT INTEGER NOT NULL ," + // 1
                "SIMPLE_INTEGER INTEGER," + // 2
                "SIMPLE_STRING_NOT_NULL TEXT NOT NULL ," + // 3
                "SIMPLE_STRING TEXT," + // 4
                "INDEXED_STRING TEXT," + // 5
                "INDEXED_STRING_ASC_UNIQUE TEXT);"; // 6
        // Add Indexes
        sql += "CREATE INDEX IDX_TEST_ENTITY_INDEXED_STRING ON TEST_ENTITY" +
                " (INDEXED_STRING);";
        sql += "CREATE UNIQUE INDEX IDX_TEST_ENTITY_INDEXED_STRING_ASC_UNIQUE ON TEST_ENTITY" +
                " (INDEXED_STRING_ASC_UNIQUE);";
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "TEST_ENTITY";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, TestEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSimpleInt());
 
        Integer simpleInteger = entity.getSimpleInteger();
        if (simpleInteger != null) {
            stmt.bindLong(3, simpleInteger);
        }
        stmt.bindString(4, entity.getSimpleStringNotNull());
 
        String simpleString = entity.getSimpleString();
        if (simpleString != null) {
            stmt.bindString(5, simpleString);
        }
 
        String indexedString = entity.getIndexedString();
        if (indexedString != null) {
            stmt.bindString(6, indexedString);
        }
 
        String indexedStringAscUnique = entity.getIndexedStringAscUnique();
        if (indexedStringAscUnique != null) {
            stmt.bindString(7, indexedStringAscUnique);
        }
    }

    /** @inheritdoc */
    @Override
    public TestEntity readFrom(Cursor cursor, int offset) {
        TestEntity entity = new TestEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // simpleInt
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // simpleInteger
            cursor.getString(offset + 3), // simpleStringNotNull
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // simpleString
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // indexedString
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // indexedStringAscUnique
        );
        return entity;
    }
    
    /** @inheritdoc */
    @Override
    public void readFrom(Cursor cursor, TestEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSimpleInt(cursor.getInt(offset + 1));
        entity.setSimpleInteger(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setSimpleStringNotNull(cursor.getString(offset + 3));
        entity.setSimpleString(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIndexedString(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIndexedStringAscUnique(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected void updateKeyAfterInsert(TestEntity entity, long rowId) {
        entity.setId(rowId);
    }
    
    /** @inheritdoc */
    @Override
    public Long getPrimaryKeyValue(TestEntity entity) {
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
    
}
