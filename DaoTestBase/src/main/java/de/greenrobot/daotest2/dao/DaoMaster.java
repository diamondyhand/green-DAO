package de.greenrobot.daotest2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.database.StandardDatabase;
import de.greenrobot.dao.database.Database;
import de.greenrobot.dao.database.EncryptedDatabaseOpenHelper;
import de.greenrobot.dao.database.DatabaseOpenHelper;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import de.greenrobot.daotest2.dao.KeepEntityDao;
import de.greenrobot.daotest2.dao.ToManyTarget2Dao;
import de.greenrobot.daotest2.to1_specialdao.ToOneTarget2Dao;
import de.greenrobot.daotest2.specialdao.RelationSource2Dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        KeepEntityDao.createTable(db, ifNotExists);
        ToManyTarget2Dao.createTable(db, ifNotExists);
        ToOneTarget2Dao.createTable(db, ifNotExists);
        RelationSource2Dao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        KeepEntityDao.dropTable(db, ifExists);
        ToManyTarget2Dao.dropTable(db, ifExists);
        ToOneTarget2Dao.dropTable(db, ifExists);
        RelationSource2Dao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }
        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public static abstract class EncryptedOpenHelper extends EncryptedDatabaseOpenHelper {
        public EncryptedOpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public EncryptedOpenHelper(Context context, String name, Object cursorFactory, boolean loadNativeLibs) {
            super(context, name, cursorFactory, SCHEMA_VERSION, loadNativeLibs);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class EncryptedDevOpenHelper extends EncryptedOpenHelper {
        public EncryptedDevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public EncryptedDevOpenHelper(Context context, String name, Object cursorFactory, boolean loadNativeLibs) {
            super(context, name, cursorFactory, loadNativeLibs);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(KeepEntityDao.class);
        registerDaoClass(ToManyTarget2Dao.class);
        registerDaoClass(ToOneTarget2Dao.class);
        registerDaoClass(RelationSource2Dao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
