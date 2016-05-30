<#--

Copyright (C) 2011-2016 Markus Junginger, greenrobot (http://greenrobot.org)
                                                                           
This file is part of greenDAO Generator.                                   
                                                                           
greenDAO Generator is free software: you can redistribute it and/or modify 
it under the terms of the GNU General Public License as published by       
the Free Software Foundation, either version 3 of the License, or          
(at your option) any later version.                                        
greenDAO Generator is distributed in the hope that it will be useful,      
but WITHOUT ANY WARRANTY; without even the implied warranty of             
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              
GNU General Public License for more details.                               
                                                                           
You should have received a copy of the GNU General Public License          
along with greenDAO Generator.  If not, see <http://www.gnu.org/licenses/>.

-->
<#-- @ftlvariable name="schema" type="org.greenrobot.greendao.generator.Schema" -->
package ${schema.defaultJavaPackageDao};

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.EncryptedDatabaseOpenHelper;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

<#list schema.entities as entity>
import ${entity.javaPackageDao}.${entity.classNameDao};
</#list>

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version ${schema.version?c}): knows all DAOs.
*/
public class ${schema.prefix}DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = ${schema.version?c};

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
<#list schema.entities as entity>
<#if !entity.skipTableCreation>
        ${entity.classNameDao}.createTable(db, ifNotExists);
</#if>
</#list>
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
<#list schema.entities as entity>
<#if !entity.skipTableCreation>
        ${entity.classNameDao}.dropTable(db, ifExists);
</#if>
</#list>
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

    public ${schema.prefix}DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public ${schema.prefix}DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
<#list schema.entities as entity>
        registerDaoClass(${entity.classNameDao}.class);
</#list>
    }
    
    public ${schema.prefix}DaoSession newSession() {
        return new ${schema.prefix}DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public ${schema.prefix}DaoSession newSession(IdentityScopeType type) {
        return new ${schema.prefix}DaoSession(db, type, daoConfigMap);
    }
    
}
