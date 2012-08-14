package de.greenrobot.daotest.async;

import java.util.concurrent.Callable;

import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.async.AsyncDaoException;
import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.daotest.SimpleEntity;

public class BasicAsyncTest extends AbstractAsyncTest {

    Thread txThread;

    public void testWaitForCompletionNoOps() {
        assertTrue(asyncSession.isCompleted());
        assertTrue(asyncSession.waitForCompletion(1));
        asyncSession.waitForCompletion();
    }

    public void testAsyncInsert() {
        SimpleEntity entity = new SimpleEntity();
        entity.setSimpleString("heho");
        AsyncOperation operation = asyncSession.insert(entity);
        assertWaitForCompletion1Sec();
        SimpleEntity entity2 = daoSession.load(SimpleEntity.class, entity.getId());
        assertNotNull(entity2);
        assertEquals("heho", entity2.getSimpleString());
        assertFalse(operation.isFailed());
        assertSingleOperationCompleted(operation);
    }

    public void testAsyncUpdate() {
        SimpleEntity entity = new SimpleEntity();
        entity.setSimpleString("heho");
        daoSession.insert(entity);
        entity.setSimpleString("updated");
        AsyncOperation operation = asyncSession.update(entity);
        assertWaitForCompletion1Sec();
        daoSession.clear();
        SimpleEntity entity2 = daoSession.load(SimpleEntity.class, entity.getId());
        assertNotNull(entity2);
        assertEquals("updated", entity2.getSimpleString());
        assertFalse(operation.isFailed());
        assertSingleOperationCompleted(operation);
    }

    public void testOperationGetResult() {
        SimpleEntity entity = new SimpleEntity();
        entity.setSimpleString("heho");
        daoSession.insert(entity);
        daoSession.clear();
        
        AsyncOperation operation = asyncSession.load(SimpleEntity.class, entity.getId());
        SimpleEntity result = (SimpleEntity) operation.getResult();
        assertTrue(operation.isCompleted());
        assertTrue(operation.isCompletedSucessfully());
        assertNotNull(result);
        assertNotSame(entity, result);
        assertEquals(entity.getId(),result.getId());
        assertEquals(entity.getSimpleString(),result.getSimpleString());
    }
    
    public void testOperationGetResultException() {
        SimpleEntity entity = new SimpleEntity();
        daoSession.insert(entity);
        AsyncOperation operation = asyncSession.insert(entity);
        try{
            operation.getResult();
            fail("getResult should have thrown");
        } catch(AsyncDaoException expected) {
            //OK
        }
        assertTrue( operation.isCompleted());
        assertFalse( operation.isCompletedSucessfully());
        assertTrue(operation.isFailed());
    }
    
    public void testAsyncException() {
        SimpleEntity entity = new SimpleEntity();
        daoSession.insert(entity);
        AsyncOperation operation = asyncSession.insert(entity);
        assertWaitForCompletion1Sec();
        assertSingleOperationCompleted(operation);

        assertTrue(operation.isFailed());
        assertNotNull(operation.getThrowable());
    }

    public void testAsyncOperationWaitMillis() {
        AsyncOperation operation = asyncSession.insert(new SimpleEntity());
        assertTrue(asyncSession.waitForCompletion(1000));
        assertSingleOperationCompleted(operation);
    }

    public void testAsyncOperationWait() {
        AsyncOperation operation = asyncSession.insert(new SimpleEntity());
        asyncSession.waitForCompletion();
        assertSingleOperationCompleted(operation);
    }

    public void testAsyncRunInTx() {
        AsyncOperation operation = asyncSession.runInTx(new Runnable() {

            @Override
            public void run() {
                txThread = Thread.currentThread();
            }
        });
        assertWaitForCompletion1Sec();
        assertSingleOperationCompleted(operation);
        assertNotNull(txThread);
        assertFalse(Thread.currentThread().equals(txThread));
    }

    public void testAsynCallInTx() {
        AsyncOperation operation = asyncSession.callInTx(new Callable<String>() {

            @Override
            public String call() throws Exception {
                txThread = Thread.currentThread();
                return "OK";
            }
        });
        assertEquals("OK", operation.waitForCompletion());
        assertNotNull(txThread);
        assertFalse(Thread.currentThread().equals(txThread));
    }

}
