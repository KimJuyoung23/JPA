package com.miner.jpa.domain;

import com.miner.jpa.domain.listener.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = MyEntityListener.class)
//@EntityListeners(value = AuditingEntityListener.class) // BaseEntity 에서 적용
public class Book extends BaseEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    // BaseEntity 에서 적용
//    @Column(name = "create_at")
//    @CreatedDate // Entity Listener 의 AuditingEntityListener 기능
//    private LocalDateTime createAt;
//
//    @Column(name = "update_at")
//    @LastModifiedDate // Entity Listener 의 AuditingEntityListener 기능
//    private LocalDateTime updateAt;


    // Auditable, MyEntityListener 사용으로 대체
//    @PrePersist
//    public void prePersist(){
//        this.createAt = LocalDateTime.now();
//        this.updateAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.updateAt = LocalDateTime.now();
//    }
}
