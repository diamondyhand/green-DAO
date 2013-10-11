package de.greenrobot.dao.query;

import java.lang.ref.WeakReference;

import android.os.Process;
import android.util.SparseArray;
import de.greenrobot.dao.AbstractDao;

abstract class AbstractQueryData<T, Q extends AbstractQuery<T>> {
    final String sql;
    final AbstractDao<T, ?> dao;
    final String[] initialValues;
    final SparseArray<WeakReference<Q>> queriesForThreads;

    AbstractQueryData(AbstractDao<T, ?> dao, String sql, String[] initialValues) {
        this.dao = dao;
        this.sql = sql;
        this.initialValues = initialValues;
        queriesForThreads = new SparseArray<WeakReference<Q>>();
    }

    /** Just gets the instance, won't reset anything like initial parameters. */
    Q forCurrentThread() {
        int threadId = Process.myTid();
        Q query;
        synchronized (queriesForThreads) {
            WeakReference<Q> queryRef = queriesForThreads.get(threadId);
            query = queryRef != null ? queryRef.get() : null;
            if (query == null) {
                gc();
                query = createQuery();
                queriesForThreads.put(threadId, new WeakReference<Q>(query));
            } else {
                System.arraycopy(initialValues, 0, query.parameters, 0, initialValues.length);
            }
        }

        return query;
    }

    abstract protected Q createQuery();

    void gc() {
        synchronized (queriesForThreads) {
            int size = queriesForThreads.size();
            for (int i = 0; i < size; i++) {
                if (queriesForThreads.valueAt(i).get() == null) {
                    queriesForThreads.remove(queriesForThreads.keyAt(i));
                }
            }
        }
    }

}
