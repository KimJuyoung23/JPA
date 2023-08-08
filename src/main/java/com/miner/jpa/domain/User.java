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
@Table(name = "user") // 보통 table 은 대문사 사용. ex) USER
@EntityListeners(AuditingEntityListener.class) // LocalDateTime 위해 적용
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 테스트 진행 단축기 command + shift + t
    //@Getter // 변수별 설정 가능
    //@Setter
    @NonNull
    private String name;
    @NonNull
    private String email;

    @CreatedDate
    @Column(name = "create_at",updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;


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
