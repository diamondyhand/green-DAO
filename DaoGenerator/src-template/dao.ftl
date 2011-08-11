<#assign toBindType = {"Boolean":"Long", "Byte":"Long", "Short":"Long", "Int":"Long", "Long":"Long", "Float":"Double", "Double":"Double", "String":"String", "ByteArray":"Blob", "Date": "Long" }>
<#assign toCursorType = {"Boolean":"Short", "Byte":"Short", "Short":"Short", "Int":"Int", "Long":"Long", "Float":"Float", "Double":"Double", "String":"String", "ByteArray":"Blob", "Date": "Long"  }>
package ${entity.javaPackageDao};

<#if entity.toOneRelations?has_content>
import java.util.List;
import java.util.ArrayList;
</#if>
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.IdentityScope;
import de.greenrobot.dao.Property;
<#if entity.toOneRelations?has_content>
import de.greenrobot.dao.SqlUtils;
</#if>

import ${entity.javaPackage}.${entity.className};
<#if entity.protobuf>
import ${entity.javaPackage}.${entity.className}.Builder;
</#if>

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ${entity.tableName} (schema version ${schema.version}).
*/
public class ${entity.classNameDao} extends AbstractDao<${entity.className}, ${entity.pkType}> {

    public static final String TABLENAME = "${entity.tableName}";

    public static class Properties {
<#list entity.properties as property>
        public final static Property ${property.propertyName?cap_first} = new Property(${property_index}, ${property.javaType}.class, "${property.propertyName}", ${property.primaryKey?string}, "${property.columnName}");
</#list>
    };

<#if entity.active>
    private DaoSession daoSession;

    public ${entity.classNameDao}(SQLiteDatabase db, DaoSession daoSession, IdentityScope<${entity.pkType}, ${entity.className}> identityScope) {
        super(db, identityScope);
        this.daoSession = daoSession;
    }

<#else>    
</#if>
    public ${entity.classNameDao}(SQLiteDatabase db) {
        super(db);
    }
    
    public ${entity.classNameDao}(SQLiteDatabase db, IdentityScope<${entity.pkType}, ${entity.className}> identityScope) {
        super(db, identityScope);
    }
    
    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "${entity.tableName} (" + //
<#list entity.properties as property>
                "${property.columnName} ${property.columnType}<#if property.constraints??> ${property.constraints} </#if><#if property_has_next>," +<#else>);";</#if> // ${property_index}
</#list>
<#if entity.indexes?has_content >
        // Add Indexes
<#list entity.indexes as index>
        sql += "CREATE <#if index.unique>UNIQUE </#if>INDEX ${index.name} ON ${entity.tableName}" +
                " (<#list index.properties 
as property>${property.columnName}<#if property_has_next>,</#if></#list>);";
</#list>
</#if>         
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "${entity.tableName}";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ${entity.className} entity) {
        stmt.clearBindings();
<#list entity.properties as property>
<#if property.notNull || entity.protobuf>
<#if entity.protobuf>
        if(entity.has${property.propertyName?cap_first}()) {
    </#if>        stmt.bind${toBindType[property.propertyType]}(${property_index + 1}, entity.get${property.propertyName?cap_first}()<#if
     property.propertyType == "Boolean"> ? 1l: 0l</#if><#if property.propertyType == "Date">.getTime()</#if>);
<#if entity.protobuf>
        }
</#if>
<#else> <#-- nullable, non-protobuff -->
        ${property.javaType} ${property.propertyName} = entity.get${property.propertyName?cap_first}();
        if (${property.propertyName} != null) {
            stmt.bind${toBindType[property.propertyType]}(${property_index + 1}, ${property.propertyName}<#if
 property.propertyType == "Boolean"> ? 1l: 0l</#if><#if property.propertyType == "Date">.getTime()</#if>);
        }
</#if>
</#list>
    }

<#if entity.active>
    @Override
    protected void attachEntity(Long key, RelationEntity entity) {
        super.attachEntity(key, entity);
        entity.__setDaoSession(daoSession);
    }

</#if>
    /** @inheritdoc */
    @Override
    public ${entity.pkType} readPkFrom(Cursor cursor, int offset) {
<#if entity.pkProperty??>
        return <#if !entity.pkProperty.notNull>cursor.isNull(offset + ${entity.pkProperty.ordinal}) ? null : </#if><#if
            entity.pkProperty.propertyType == "Byte">(byte) </#if><#if
            entity.pkProperty.propertyType == "Date">new java.util.Date(</#if>cursor.get${toCursorType[entity.pkProperty.propertyType]}(offset + ${entity.pkProperty.ordinal})<#if
            entity.pkProperty.propertyType == "Boolean"> != 0</#if><#if
            entity.pkProperty.propertyType == "Date">)</#if>;
<#else>
        return null;
</#if>  
    }    

    /** @inheritdoc */
    @Override
    public ${entity.className} readFrom(Cursor cursor, int offset) {
<#if entity.protobuf>
        Builder builder = ${entity.className}.newBuilder();
<#list entity.properties as property>
<#if !property.notNull>
        if (!cursor.isNull(offset + ${property_index})) {
    </#if>        builder.set${property.propertyName?cap_first}(cursor.get${toCursorType[property.propertyType]}(offset + ${property_index}));
<#if !property.notNull>
        }
</#if>        
</#list>        
        return builder.build();
<#elseif entity.constructors>
<#--
############################## readFrom non-protobuff, constructor ############################## 
-->
        ${entity.className} entity = new ${entity.className}( //
<#list entity.properties as property>
            <#if !property.notNull>cursor.isNull(offset + ${property_index}) ? null : </#if><#if
            property.propertyType == "Byte">(byte) </#if><#if
            property.propertyType == "Date">new java.util.Date(</#if>cursor.get${toCursorType[property.propertyType]}(offset + ${property_index})<#if
            property.propertyType == "Boolean"> != 0</#if><#if
            property.propertyType == "Date">)</#if><#if property_has_next>,</#if> // ${property.propertyName}
</#list>        
        );
        return entity;
<#else>
<#--
############################## readFrom non-protobuff, setters ############################## 
-->
        ${entity.className} entity = new ${entity.className}();
        readFrom(cursor, entity, offset);
        return entity;
</#if>
    }
     
    /** @inheritdoc */
    @Override
    public void readFrom(Cursor cursor, ${entity.className} entity, int offset) {
<#if entity.protobuf>
        throw new UnsupportedOperationException("Protobuf objects cannot be modified");
<#else> 
<#list entity.properties as property>
        entity.set${property.propertyName?cap_first}(<#if !property.notNull>cursor.isNull(offset + ${property_index}) ? null : </#if><#if
            property.propertyType == "Byte">(byte) </#if><#if
            property.propertyType == "Date">new java.util.Date(</#if>cursor.get${toCursorType[property.propertyType]}(offset + ${property_index})<#if
            property.propertyType == "Boolean"> != 0</#if><#if
            property.propertyType == "Date">)</#if>);
</#list>
</#if>
     }
    
    @Override
    protected ${entity.pkType} updateKeyAfterInsert(${entity.className} entity, long rowId) {
<#if entity.protobuf>
        // Do nothing: Cannot update protobuf entities after insert
        return null;
<#else>
<#if entity.pkProperty?? && entity.pkProperty.propertyType == "Long">
        entity.set${entity.pkProperty.propertyName?cap_first}(rowId);
        return rowId;
<#else>
        // TODO XXX Only Long PKs are supported currently
        return null;
</#if>
</#if>
    }
    
    /** @inheritdoc */
    @Override
    public ${entity.pkType} getPrimaryKeyValue(${entity.className} entity) {
<#if entity.pkProperty??>
        if(entity != null) {
            return entity.get${entity.pkProperty.propertyName?cap_first}();
        } else {
            return null;
        }
<#else>
        return null;
</#if>    
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return ${(!entity.protobuf)?string};
    }
    
<#if entity.toOneRelations?has_content>
    <#include "dao-deep.ftl">
</#if>
}
