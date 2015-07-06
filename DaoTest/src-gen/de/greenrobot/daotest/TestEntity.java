package de.greenrobot.daotest;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TEST_ENTITY".
 */
public class TestEntity {

    private Long id;
    private int simpleInt;
    private Integer simpleInteger;
    /** Not-null value. */
    private String simpleStringNotNull;
    private String simpleString;
    private String indexedString;
    private String indexedStringAscUnique;
    private java.util.Date simpleDate;
    private Boolean simpleBoolean;

    public TestEntity() {
    }

    public TestEntity(Long id) {
        this.id = id;
    }

    public TestEntity(Long id, int simpleInt, Integer simpleInteger, String simpleStringNotNull, String simpleString, String indexedString, String indexedStringAscUnique, java.util.Date simpleDate, Boolean simpleBoolean) {
        this.id = id;
        this.simpleInt = simpleInt;
        this.simpleInteger = simpleInteger;
        this.simpleStringNotNull = simpleStringNotNull;
        this.simpleString = simpleString;
        this.indexedString = indexedString;
        this.indexedStringAscUnique = indexedStringAscUnique;
        this.simpleDate = simpleDate;
        this.simpleBoolean = simpleBoolean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSimpleInt() {
        return simpleInt;
    }

    public void setSimpleInt(int simpleInt) {
        this.simpleInt = simpleInt;
    }

    public Integer getSimpleInteger() {
        return simpleInteger;
    }

    public void setSimpleInteger(Integer simpleInteger) {
        this.simpleInteger = simpleInteger;
    }

    /** Not-null value. */
    public String getSimpleStringNotNull() {
        return simpleStringNotNull;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setSimpleStringNotNull(String simpleStringNotNull) {
        this.simpleStringNotNull = simpleStringNotNull;
    }

    public String getSimpleString() {
        return simpleString;
    }

    public void setSimpleString(String simpleString) {
        this.simpleString = simpleString;
    }

    public String getIndexedString() {
        return indexedString;
    }

    public void setIndexedString(String indexedString) {
        this.indexedString = indexedString;
    }

    public String getIndexedStringAscUnique() {
        return indexedStringAscUnique;
    }

    public void setIndexedStringAscUnique(String indexedStringAscUnique) {
        this.indexedStringAscUnique = indexedStringAscUnique;
    }

    public java.util.Date getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(java.util.Date simpleDate) {
        this.simpleDate = simpleDate;
    }

    public Boolean getSimpleBoolean() {
        return simpleBoolean;
    }

    public void setSimpleBoolean(Boolean simpleBoolean) {
        this.simpleBoolean = simpleBoolean;
    }

}
