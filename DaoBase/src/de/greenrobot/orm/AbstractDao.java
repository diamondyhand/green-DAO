package de.greenrobot.orm;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Base class for all DAOs. Implements some operations.
 * 
 * @author Markus
 * 
 * @param <T>
 *            Entity type
 * @param <K>
 *            Primary key type; use Void if entity does not have one
 */
public abstract class AbstractDao<T, K> {
    protected final SQLiteDatabase db;

    protected SQLiteStatement insertStatement;
    protected SQLiteStatement insertOrReplaceStatement;

    private volatile String selectAll;
    private volatile String selectByKey;
    private volatile String selectByRowId;

    public AbstractDao(SQLiteDatabase db) {
        this.db = db;
    }

    protected SQLiteStatement getInsertStatement() {
        if (insertStatement == null) {
            String sql = createSqlForInsert("INSERT INTO ");
            insertStatement = db.compileStatement(sql);
        }
        return insertStatement;
    }

    protected SQLiteStatement getInsertOrReplaceStatement() {
        if (insertOrReplaceStatement == null) {
            String sql = createSqlForInsert("INSERT OR REPLACE INTO ");
            insertOrReplaceStatement = db.compileStatement(sql);
        }
        return insertOrReplaceStatement;
    }

    private String createSqlForInsert(String insertInto) {
        StringBuilder builder = new StringBuilder();
        builder.append(insertInto).append(getTablename());
        builder.append(" (").append(getAllColumnsSql());
        builder.append(") VALUES (").append(getValuePlaceholders()).append(")");
        String sql = builder.toString();
        return sql;
    }

    /** ends with an space to simplify appending to this string. */
    protected String getSelectAll() {
        if (selectAll == null) {
            selectAll = "SELECT " + getAllColumnsSql()+ " FROM " + getTablename()  + " ";
        }
        return selectAll;
    }

    public T load(K key) {
        if (selectByKey == null) {
            selectByKey = getSelectAll() + "WHERE " + getPkColumn() + "=?";
        }
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(selectByKey, keyArray);
        return readUniqueAndCloseCursor(cursor);
    }

    public T loadByRowId(long rowId) {
        if (selectByRowId == null) {
            selectByRowId = getSelectAll() + "WHERE ROWID=?";
        }
        String[] idArray = new String[] { Long.toString(rowId) };
        Cursor cursor = db.rawQuery(selectByRowId, idArray);
        return readUniqueAndCloseCursor(cursor);
    }

    protected T readUniqueAndCloseCursor(Cursor cursor) {
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return readFrom(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<T> loadAll() {
        Cursor cursor = db.rawQuery(getSelectAll(), null);
        return readAllAndCloseCursor(cursor);
    }

    protected List<T> readAllAndCloseCursor(Cursor cursor) {
        try {
            return readAllFrom(cursor);
        } finally {
            cursor.close();
        }
    }

    public void insertInTx(List<T> list) {
        db.beginTransaction();
        try {
            for (T entity : list) {
                insert(entity);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /** Insert an entity into the table associated with a concrete DAO. */
    public long insert(T entity) {
        SQLiteStatement stmt = getInsertStatement();
        synchronized (stmt) {
            bindValues(stmt, entity);
            long rowId = stmt.executeInsert();
            updateKeyAfterInsert(entity, rowId);
            return rowId;
        }
    }

    /** Insert an entity into the table associated with a concrete DAO. */
    public long insertOrReplace(T entity) {
        SQLiteStatement stmt = getInsertOrReplaceStatement();
        synchronized (stmt) {
            bindValues(stmt, entity);
            long rowId = stmt.executeInsert();
            // TODO entity.setId(rowId);
            return rowId;
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    protected List<T> readAllFrom(Cursor cursor) {
        List<T> list = new ArrayList<T>();
        if (cursor.moveToFirst()) {
            do {
                list.add(readFrom(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }

    /** SUBJECT TO CHANGE: A raw-style query where you can pass any WHERE clause and arguments. */
    public List<T> query(String where, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(getSelectAll() + where, selectionArgs);
        return readAllAndCloseCursor(cursor);
    }

    public boolean delete(K key) {
        String[] idArray = new String[] { key.toString() };
        String pkColumn = getPkColumn();
        if (pkColumn == null) {
            throw new SQLException(getTablename() + " does not have a single-column primary key");
        }
        int affectedRows = db.delete(getTablename(), pkColumn + "==?", idArray);
        return affectedRows >= 1;
    }

    public long count() {
        return DatabaseUtils.queryNumEntries(db, getTablename());
    }

    /** Reads the values from the current position of the given cursor and returns a new ImageTO object. */
    abstract public T readFrom(Cursor cursor);

    /** Binds the entity's values to the statement. Make sure to synchronize the statement outside of the method. */
    abstract protected void bindValues(SQLiteStatement stmt, T entity);

    abstract protected String getTablename();

    abstract protected String getValuePlaceholders();

    abstract protected String getAllColumnsSql();

    abstract protected String getPkColumnsSql();

    abstract protected String getNonPkColumnsSql();

    abstract protected void updateKeyAfterInsert(T entity, long rowId);

    /** If there is a single PK column, this method will return it. If not, it must return null. */
    abstract protected String getPkColumn();

}
