package com.db;


/**
 * @author vipul pachauri
 */
public  class BaseProductInfo<T> {

    private Long creationDate;

    private Long modificationDate;

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Long modificationDate) {
        this.modificationDate = modificationDate;
    }

}
