package com.miner.jpa.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass // 상속받는 클래스에서 Entity 의 컬럼으로 편입시킴
// 상속받는 클래스의 @Data 에 오류 발생. 상속받는 클래스에서 처리. @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true)
@EntityListeners(value = AutoCloseable.class)
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;

}
