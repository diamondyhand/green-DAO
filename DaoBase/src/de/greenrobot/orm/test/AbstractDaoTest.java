package de.greenrobot.orm.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import junit.framework.TestCase;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.orm.AbstractDao;
import de.greenrobot.orm.UnitTestDaoAccess;

/**
 * Only for single-PK entities.
 * 
 * @author Markus
 * 
 * @param <D>
 *            DAO class
 * @param <T>
 *            Entity type of the DAO
 * @param <K>
 *            Key type of the DAO
 */
public abstract class AbstractDaoTest<D extends AbstractDao<T, K>, T, K> extends TestCase {

    protected D dao;
    protected SQLiteDatabase db;
    protected Set<K> usedPks;
    protected Random random;
    private final Class<D> daoClass;
    private UnitTestDaoAccess<T, K> daoAccess;

    public AbstractDaoTest(Class<D> daoClass) {
        random = new Random();
        this.daoClass = daoClass;
    }

    @Override
    protected void setUp() {
        usedPks = new HashSet<K>();
        db = SQLiteDatabase.create(null);
        try {
            Constructor<D> constructor = daoClass.getConstructor(SQLiteDatabase.class);
            dao = constructor.newInstance(db);

            Method createTableMethod = daoClass.getMethod("createTable", SQLiteDatabase.class, boolean.class);
            createTableMethod.invoke(null, db, false);
        } catch (Exception e) {
            throw new RuntimeException("Could not prepare DAO Test", e);
        }
        daoAccess = new UnitTestDaoAccess<T, K>(dao);
    }

    @Override
    protected void tearDown() throws Exception {
        db.close();
    }

    public void testInsertAndLoad() {
        K pk = nextPk();
        T entity = createEntity(pk);
        dao.insert(entity);
        T entity2 = dao.load(pk);
        assertNotNull(entity2);
        assertNotSame(entity, entity2); // Unless we'll cache stuff one day
        assertEquals(daoAccess.getPrimaryKeyValue(entity), daoAccess.getPrimaryKeyValue(entity2));
    }

    public void testInsertTwice() {
        K pk = nextPk();
        T entity = createEntity(pk);
        dao.insert(entity);
        try {
            dao.insert(entity);
            fail("Inserting twice should not work");
        } catch (SQLException expected) {
            // OK
        }
    }

    public void testInsertOrReplaceTwice() {
        K pk = nextPk();
        T entity = createEntity(pk);
        long rowId1 = dao.insert(entity);
        long rowId2 = dao.insertOrReplace(entity);
        assertEquals(rowId1, rowId2);
    }

    public void testDelete() {
        K pk = nextPk();
        assertFalse(dao.deleteByKey(pk));
        T entity = createEntity(pk);
        dao.insert(entity);
        assertNotNull(dao.load(pk));
        assertTrue(dao.deleteByKey(pk));
        assertNull(dao.load(pk));
    }

    public void testRowId() {
        T entity1 = createEntity(nextPk());
        T entity2 = createEntity(nextPk());
        long rowId1 = dao.insert(entity1);
        long rowId2 = dao.insert(entity2);
        assertTrue(rowId1 != rowId2);
    }

    public void testLoadAll() {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < 15; i++) {
            T entity = createEntity(nextPk());
            list.add(entity);
        }
        dao.insertInTx(list);
        List<T> loaded = dao.loadAll();
        assertEquals(list.size(), loaded.size());
    }

    public void testQuery() {
        dao.insert(createEntity(nextPk()));
        K pkForQuery = nextPk();
        dao.insert(createEntity(pkForQuery));
        dao.insert(createEntity(nextPk()));

        String where = "WHERE " + dao.getPkColumns()[0] + "=?";
        List<T> list = dao.query(where, pkForQuery.toString());
        assertEquals(1, list.size());
        assertEquals(pkForQuery, daoAccess.getPrimaryKeyValue(list.get(0)));
    }

    protected K nextPk() {
        for (int i = 0; i < 100000; i++) {
            K pk = createRandomPk();
            if (usedPks.add(pk)) {
                return pk;
            }
        }
        throw new IllegalStateException("Could not find a new PK");
    }

    /** K does not have to be collision free, check nextPk for collision free PKs. */
    protected abstract K createRandomPk();

    protected abstract T createEntity(K key);

}