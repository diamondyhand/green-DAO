<#assign toBindType = {"Boolean":"Long", "Byte":"Long", "Short":"Long", "Int":"Long", "Long":"Long", "Float":"Double", "Double":"Double", "String":"String", "ByteArray":"Blob" }>
<#assign toCursorType = {"Boolean":"Short", "Byte":"Short", "Short":"Short", "Int":"Int", "Long":"Long", "Float":"Float", "Double":"Double", "String":"String", "ByteArray":"Blob" }>
package ${entity.javaPackage};


// THIS CODE IS GENERATED, DO NOT EDIT.
/** 
 * Entity mapped to table ${entity.tableName} (schema version ${schema.version}).
*/
public class ${entity.className} {

<#list entity.properties as property>
    private ${property.javaType} ${property.propertyName}; 
</#list>
    
<#list entity.properties as property>
    public ${property.javaType} get${property.propertyName?cap_first}() {
        return ${property.propertyName};
    } 

    public void set${property.propertyName?cap_first}(${property.javaType} ${property.propertyName}) {
        this.${property.propertyName} = ${property.propertyName};
    } 

</#list>
}
