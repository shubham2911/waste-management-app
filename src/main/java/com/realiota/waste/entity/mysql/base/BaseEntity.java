package com.realiota.waste.entity.mysql.base;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3649285369162252355L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive = Boolean.valueOf(true);

    @Version
    @Column(nullable = false, name = "record_version_number")
    private Integer recordVersionNumber;

    @Column(name = "created_timestamp", nullable = false)
    private Long createdTimestamp;

    @Column(name = "updated_timestamp", nullable = false)
    private Long updatedTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getRecordVersionNumber() {
        return recordVersionNumber;
    }

    public void setRecordVersionNumber(Integer recordVersionNumber) {
        this.recordVersionNumber = recordVersionNumber;
    }

    public Long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Long getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Long updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    @PrePersist
    protected void onCreate() {
        updatedTimestamp = createdTimestamp = DateTime.now().getMillis();
        this.isActive = Boolean.valueOf(null == this.isActive ? true : this.isActive.booleanValue());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTimestamp = DateTime.now().getMillis();
    }
}