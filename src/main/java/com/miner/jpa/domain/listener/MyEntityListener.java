package com.miner.jpa.domain.listener;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setCreateAt(LocalDateTime.now());
            ((Auditable) o).setUpdateAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdateAt(LocalDateTime.now());
        }
    }
}
