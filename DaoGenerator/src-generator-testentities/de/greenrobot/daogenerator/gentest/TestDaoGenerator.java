package de.greenrobot.daogenerator.gentest;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Generates test entities for test project DaoTest.
 *  
 * @author Markus
 */
public class TestDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "de.greenrobot.testdao");
        
        Entity simple = schema.addEntity("SimpleEntity");
        simple.addIdProperty();
        simple.addBooleanProperty("simpleBoolean");
        simple.addByteProperty("simpleByte");
        simple.addShortProperty("simpleShort");
        simple.addIntProperty("simpleInt");
        simple.addLongProperty("simpleLong");
        simple.addFloatProperty("simpleFloat");
        simple.addDoubleProperty("simpleDouble");
        simple.addStringProperty("simpleString");
        simple.addByteArrayProperty("simpleByteArray");

        Entity notNull = schema.addEntity("SimpleEntityNotNull");
        notNull.addIdProperty().notNull();
        notNull.addBooleanProperty("simpleBoolean").notNull();
        notNull.addByteProperty("simpleByte").notNull();
        notNull.addShortProperty("simpleShort").notNull();
        notNull.addIntProperty("simpleInt").notNull();
        notNull.addLongProperty("simpleLong").notNull();
        notNull.addFloatProperty("simpleFloat").notNull();
        notNull.addDoubleProperty("simpleDouble").notNull();
        notNull.addStringProperty("simpleString").notNull();
        notNull.addByteArrayProperty("simpleByteArray").notNull();

        Entity testEntity = schema.addEntity("TestEntity");
        testEntity.addIdProperty();
        testEntity.addIntProperty("simpleInt").notNull();
        testEntity.addIntProperty("simpleInteger");
        testEntity.addStringProperty("simpleStringNotNull").notNull();
        testEntity.addStringProperty("simpleString");
        testEntity.addStringProperty("indexedString").index();
        testEntity.addStringProperty("indexedStringAscUnique").indexAsc(null, true);

        new DaoGenerator().generateAll("../DaoTest/src-gen", schema);
    }

}
