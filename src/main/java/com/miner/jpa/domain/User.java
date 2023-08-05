package com.miner.jpa.domain;

import lombok.*;

import java.nio.file.FileStore;
import java.time.LocalDateTime;

//@Getter
//@Setter
@NoArgsConstructor // 생성자. @Data 와 대부분 같이 쓰임.
@RequiredArgsConstructor //생성자 @NonNull 변수만 필수. @Data 에 포함되어 있는데 주석하면 에러남....(?) AllArgsConstructor 있어서?
@AllArgsConstructor //생성자
//@EqualsAndHashCode // 많이 사용 많이 안함.
@Data // @Getter @Setter @RequiredArgsConstructor(생성자) @ToString @EqualsAndHashCode
@Builder // 생성자와 같이 객체 생성, 필드 값 주입하지만 빌더의 형식으로 수행
public class User {


    // 테스트 진행 단축기 command + shift + t
    //@Getter // 변수별 설정 가능
    //@Setter
    @NonNull
    private String name;
    @NonNull
    private String email;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


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
