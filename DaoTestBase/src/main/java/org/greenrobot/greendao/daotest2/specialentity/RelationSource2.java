package org.greenrobot.greendao.daotest2.specialentity;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import org.greenrobot.greendao.daotest2.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

import org.greenrobot.greendao.daotest2.ToManyTarget2;
import org.greenrobot.greendao.daotest2.dao.ToManyTarget2Dao;
import org.greenrobot.greendao.daotest2.specialdao.RelationSource2Dao;
import org.greenrobot.greendao.daotest2.to1_specialdao.ToOneTarget2Dao;
import org.greenrobot.greendao.daotest2.to1_specialentity.ToOneTarget2;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "RELATION_SOURCE2".
 */
@Entity(active = true)
public class RelationSource2 {

    @Id
    private Long id;
    private Long toOneId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient RelationSource2Dao myDao;

    @ToOne(joinProperty = "toOneId")
    private ToOneTarget2 toOneTarget2;

    @Generated
    private transient Long toOneTarget2__resolvedKey;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "fkId")
    })
    private List<ToManyTarget2> toManyTarget2List;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public RelationSource2() {
    }

    public RelationSource2(Long id) {
        this.id = id;
    }

    @Generated
    public RelationSource2(Long id, Long toOneId) {
        this.id = id;
        this.toOneId = toOneId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRelationSource2Dao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToOneId() {
        return toOneId;
    }

    public void setToOneId(Long toOneId) {
        this.toOneId = toOneId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public ToOneTarget2 getToOneTarget2() {
        Long __key = this.toOneId;
        if (toOneTarget2__resolvedKey == null || !toOneTarget2__resolvedKey.equals(__key)) {
            __throwIfDetached();
            ToOneTarget2Dao targetDao = daoSession.getToOneTarget2Dao();
            ToOneTarget2 toOneTarget2New = targetDao.load(__key);
            synchronized (this) {
                toOneTarget2 = toOneTarget2New;
            	toOneTarget2__resolvedKey = __key;
            }
        }
        return toOneTarget2;
    }

    @Generated
    public void setToOneTarget2(ToOneTarget2 toOneTarget2) {
        synchronized (this) {
            this.toOneTarget2 = toOneTarget2;
            toOneId = toOneTarget2 == null ? null : toOneTarget2.getId();
            toOneTarget2__resolvedKey = toOneId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<ToManyTarget2> getToManyTarget2List() {
        if (toManyTarget2List == null) {
            __throwIfDetached();
            ToManyTarget2Dao targetDao = daoSession.getToManyTarget2Dao();
            List<ToManyTarget2> toManyTarget2ListNew = targetDao._queryRelationSource2_ToManyTarget2List(id);
            synchronized (this) {
                if(toManyTarget2List == null) {
                    toManyTarget2List = toManyTarget2ListNew;
                }
            }
        }
        return toManyTarget2List;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetToManyTarget2List() {
        toManyTarget2List = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
