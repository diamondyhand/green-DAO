package de.greenrobot.dao.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;

import de.greenrobot.dao.test.SimpleEntity;

// THIS CODE IS GENERATED, DO NOT EDIT.
/** 
 * DAO for table SIMPLE_ENTITY (schema version 1).
*/
public class SimpleEntityDao extends AbstractDao<SimpleEntity, Long> {

    public static final String TABLENAME = "SIMPLE_ENTITY";

    public static class Properties {
        public final static Property Id = new Property(0, "id", true, "_id");
        public final static Property SimpleBoolean = new Property(1, "simpleBoolean", false, "SIMPLE_BOOLEAN");
        public final static Property SimpleByte = new Property(2, "simpleByte", false, "SIMPLE_BYTE");
        public final static Property SimpleShort = new Property(3, "simpleShort", false, "SIMPLE_SHORT");
        public final static Property SimpleInt = new Property(4, "simpleInt", false, "SIMPLE_INT");
        public final static Property SimpleLong = new Property(5, "simpleLong", false, "SIMPLE_LONG");
        public final static Property SimpleFloat = new Property(6, "simpleFloat", false, "SIMPLE_FLOAT");
        public final static Property SimpleDouble = new Property(7, "simpleDouble", false, "SIMPLE_DOUBLE");
        public final static Property SimpleString = new Property(8, "simpleString", false, "SIMPLE_STRING");
        public final static Property SimpleByteArray = new Property(9, "simpleByteArray", false, "SIMPLE_BYTE_ARRAY");
    };

    public SimpleEntityDao(SQLiteDatabase db) {
        super(db);
    }
    
    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "SIMPLE_ENTITY (" + //
                "_id INTEGER PRIMARY KEY ," + // 0
                "SIMPLE_BOOLEAN INTEGER," + // 1
                "SIMPLE_BYTE INTEGER," + // 2
                "SIMPLE_SHORT INTEGER," + // 3
                "SIMPLE_INT INTEGER," + // 4
                "SIMPLE_LONG INTEGER," + // 5
                "SIMPLE_FLOAT REAL," + // 6
                "SIMPLE_DOUBLE REAL," + // 7
                "SIMPLE_STRING TEXT," + // 8
                "SIMPLE_BYTE_ARRAY BLOB);"; // 9
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "SIMPLE_ENTITY";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    public String getTablename() {
        return TABLENAME;
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SimpleEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Boolean simpleBoolean = entity.getSimpleBoolean();
        if (simpleBoolean != null) {
            stmt.bindLong(2, simpleBoolean ? 1l: 0l);
        }
 
        Byte simpleByte = entity.getSimpleByte();
        if (simpleByte != null) {
            stmt.bindLong(3, simpleByte);
        }
 
        Short simpleShort = entity.getSimpleShort();
        if (simpleShort != null) {
            stmt.bindLong(4, simpleShort);
        }
 
        Integer simpleInt = entity.getSimpleInt();
        if (simpleInt != null) {
            stmt.bindLong(5, simpleInt);
        }
 
        Long simpleLong = entity.getSimpleLong();
        if (simpleLong != null) {
            stmt.bindLong(6, simpleLong);
        }
 
        Float simpleFloat = entity.getSimpleFloat();
        if (simpleFloat != null) {
            stmt.bindDouble(7, simpleFloat);
        }
 
        Double simpleDouble = entity.getSimpleDouble();
        if (simpleDouble != null) {
            stmt.bindDouble(8, simpleDouble);
        }
 
        String simpleString = entity.getSimpleString();
        if (simpleString != null) {
            stmt.bindString(9, simpleString);
        }
 
        byte[] simpleByteArray = entity.getSimpleByteArray();
        if (simpleByteArray != null) {
            stmt.bindBlob(10, simpleByteArray);
        }
    }

    /** @inheritdoc */
    @Override
    public SimpleEntity readFrom(Cursor cursor) {
        SimpleEntity entity = new SimpleEntity( //
            cursor.isNull(0) ? null : cursor.getLong(0), // id
            cursor.isNull(1) ? null : cursor.getShort(1) != 0, // simpleBoolean
            cursor.isNull(2) ? null : (byte) cursor.getShort(2), // simpleByte
            cursor.isNull(3) ? null : cursor.getShort(3), // simpleShort
            cursor.isNull(4) ? null : cursor.getInt(4), // simpleInt
            cursor.isNull(5) ? null : cursor.getLong(5), // simpleLong
            cursor.isNull(6) ? null : cursor.getFloat(6), // simpleFloat
            cursor.isNull(7) ? null : cursor.getDouble(7), // simpleDouble
            cursor.isNull(8) ? null : cursor.getString(8), // simpleString
            cursor.isNull(9) ? null : cursor.getBlob(9) // simpleByteArray
        );
        return entity;
    }
    
    /** @inheritdoc */
    @Override
    public void readFrom(Cursor cursor, SimpleEntity entity) {
        entity.setId(cursor.isNull(0) ? null : cursor.getLong(0));
        entity.setSimpleBoolean(cursor.isNull(1) ? null : cursor.getShort(1) != 0);
        entity.setSimpleByte(cursor.isNull(2) ? null : (byte) cursor.getShort(2));
        entity.setSimpleShort(cursor.isNull(3) ? null : cursor.getShort(3));
        entity.setSimpleInt(cursor.isNull(4) ? null : cursor.getInt(4));
        entity.setSimpleLong(cursor.isNull(5) ? null : cursor.getLong(5));
        entity.setSimpleFloat(cursor.isNull(6) ? null : cursor.getFloat(6));
        entity.setSimpleDouble(cursor.isNull(7) ? null : cursor.getDouble(7));
        entity.setSimpleString(cursor.isNull(8) ? null : cursor.getString(8));
        entity.setSimpleByteArray(cursor.isNull(9) ? null : cursor.getBlob(9));
     }
    
    @Override
    protected void updateKeyAfterInsert(SimpleEntity entity, long rowId) {
        entity.setId(rowId);
    }
    
    /** @inheritdoc */
    @Override
    public Long getPrimaryKeyValue(SimpleEntity entity) {
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
