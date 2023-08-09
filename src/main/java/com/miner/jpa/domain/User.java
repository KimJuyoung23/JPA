package com.miner.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

//@Getter
//@Setter
@NoArgsConstructor // 생성자. @Data 와 대부분 같이 쓰임.
@RequiredArgsConstructor //생성자 @NonNull 변수만 필수. @Data 에 포함되어 있는데 주석하면 에러남....(?) AllArgsConstructor 있어서?
@AllArgsConstructor //생성자
//@EqualsAndHashCode // 지금은 넘어가자..
@Data // @Getter @Setter @RequiredArgsConstructor(생성자) @ToString @EqualsAndHashCode
@Builder // 생성자와 같이 객체 생성, 필드 값 주입하지만 빌더의 형식으로 수행
@Entity // pk key 필수
@Table(name = "user", indexes = {@Index(columnList = "name")},uniqueConstraints = {@UniqueConstraint(columnNames = "email")}) // 사용 예시
// 보통 table 은 대문사 사용. ex) USER. @Entity 옵션
// uniqueConstraints 여기서 설정은 name 과 email 등 복합컬럼 유니크 설정할때 쓰임. 단일 컬럼 유니크는 각 컬럼에서 설정.
@EntityListeners(AuditingEntityListener.class) // LocalDateTime 위해 적용
public class User {
    @Id // @Entity 기본
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @Entity 기본
    private long id;
    // GenerationType.IDENTITY --> 주로 mysql 사용 많이 함


    // 테스트 진행 단축기 command + shift + t
    //@Getter // 변수별 설정 가능
    //@Setter
    @NonNull
    private String name;
    @NonNull
    @Column(unique = true) // 단일 컬럼 유니크는 각 컬럼에서 설정
    private String email;

    @CreatedDate
    @Column(name = "create_at",updatable = false) // @Entity 옵션
    private LocalDateTime createAt;

//    @Enumerated(value = EnumType.ORDINAL) // 기본값. 오류 발생 가능성 다수. enum 의 값이 DB에 숫자로 0,1,2 ~ 으로 저장됨
    @Enumerated(value = EnumType.STRING) // enum 오류 예방. enum 의 값이 DB에 String 그대로 저장
    private Gender gender;

    @LastModifiedDate
    @Column(name = "update_at",insertable = false)
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    @Transient // DB 반영 안되는 컬럼. 비밀번호 중복 테스트 할때 사용됨.
    private String testData;

    // toString 오버라이드 해도 되지만. 어노테이션 @ToString 혹은 @Data 사용
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", createAt=" + createAt +
//                ", updateAt=" + updateAt +
//                '}';
//    }
}
