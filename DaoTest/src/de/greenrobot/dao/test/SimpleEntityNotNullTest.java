package de.greenrobot.dao.test;

import java.util.Arrays;

import de.greenrobot.orm.test.AbstractDaoTestLongPk;
import de.greenrobot.testdao.SimpleEntityNotNull;
import de.greenrobot.testdao.SimpleEntityNotNullDao;

public class SimpleEntityNotNullTest extends AbstractDaoTestLongPk<SimpleEntityNotNullDao, SimpleEntityNotNull> {

    public SimpleEntityNotNullTest() {
        super(SimpleEntityNotNullDao.class);
    }

    @Override
    protected SimpleEntityNotNull createEntity(Long key) {
        if (key == null) {
            return null;
        }
        SimpleEntityNotNull entity = new SimpleEntityNotNull();
        entity.setId(key);
        entity.setSimpleBoolean(true);
        entity.setSimpleByte(Byte.MAX_VALUE);
        entity.setSimpleShort(Short.MAX_VALUE);
        entity.setSimpleInt(Integer.MAX_VALUE);
        entity.setSimpleLong(Long.MAX_VALUE);
        entity.setSimpleFloat(Float.MAX_VALUE);
        entity.setSimpleDouble(Double.MAX_VALUE);
        entity.setSimpleString("greenrobot greenDAO");
        byte[] bytes = { 42, -17, 23, 0, 127, -128 };
        entity.setSimpleByteArray(bytes);
        return entity;
    }

    public void testValues() {
        SimpleEntityNotNull entity = createEntity(1l);
        dao.insert(entity);
        SimpleEntityNotNull reloaded = dao.load(1l);
        assertNotSame(entity, reloaded);
        
        assertEquals(entity.getId(), reloaded.getId());
        assertEquals(entity.getSimpleBoolean(), reloaded.getSimpleBoolean());
        assertEquals(entity.getSimpleDouble(), reloaded.getSimpleDouble());
        assertEquals(entity.getSimpleFloat(), reloaded.getSimpleFloat());
        assertEquals(entity.getSimpleLong(), reloaded.getSimpleLong());
        assertEquals(entity.getSimpleByte(), reloaded.getSimpleByte());
        assertEquals(entity.getSimpleInt(), reloaded.getSimpleInt());
        assertEquals(entity.getSimpleShort(), reloaded.getSimpleShort());
        assertEquals(entity.getSimpleBoolean(), reloaded.getSimpleBoolean());
        assertEquals(entity.getSimpleString(), reloaded.getSimpleString());
        assertTrue(Arrays.equals(entity.getSimpleByteArray(), reloaded.getSimpleByteArray()));
    }

}
