package de.greenrobot.daotest;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table EXTENDS_IMPLEMENTS_ENTITY.
 */
public class ExtendsImplementsEntity extends TestSuperclass  implements TestInterface, java.io.Serializable {

    private Long id;
    private String text;

    public ExtendsImplementsEntity() {
    }

    public ExtendsImplementsEntity(Long id) {
        this.id = id;
    }

    public ExtendsImplementsEntity(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
