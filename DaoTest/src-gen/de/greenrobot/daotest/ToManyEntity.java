package de.greenrobot.daotest;

import java.util.List;
import de.greenrobot.daotest.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table TO_MANY_ENTITY (schema version 1).
 */
public class ToManyEntity {

    private Long id;

    /** Used to resolve relations */
    private DaoSession daoSession;

    private List<ToManyTargetEntity> toManyTargetEntity;
    private List<ToManyTargetEntity> ToManyDescList;
    public ToManyEntity() {
    }

    public ToManyEntity(Long id) {
        this.id = id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<ToManyTargetEntity> getToManyTargetEntity() {
        if (toManyTargetEntity == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ToManyTargetEntityDao dao = daoSession.getToManyTargetEntityDao();
            toManyTargetEntity = dao._queryToManyEntity_ToManyTargetEntity(id);
        }
        return toManyTargetEntity;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetToManyTargetEntity() {
        toManyTargetEntity = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<ToManyTargetEntity> getToManyDescList() {
        if (ToManyDescList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ToManyTargetEntityDao dao = daoSession.getToManyTargetEntityDao();
            ToManyDescList = dao._queryToManyEntity_ToManyDescList(id);
        }
        return ToManyDescList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetToManyDescList() {
        ToManyDescList = null;
    }

}
