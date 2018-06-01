package com.db;


import java.sql.Timestamp;

/**
 * @author vipul pachauri
 */
public abstract class BaseProductInfo<T> {

    private Long creationDate;

    private Long modificationDate;

    BaseProductInfo(Long creationDate,Long modificationDate){
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    protected BaseProductInfo() {
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public BaseProductInfo setCreationDate() {
        this.creationDate = new Timestamp(System.currentTimeMillis()).getTime();
        return this;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public BaseProductInfo setModificationDate() {
        this.modificationDate = this.creationDate = new Timestamp(System.currentTimeMillis()).getTime();
        return this;
    }

}
