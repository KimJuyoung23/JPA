package com.miner.jpa.domain;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    //toString 테스트
    @Test
    void test(){
        User user = new User();
        user.setEmail("test@test.com");
        user.setName("tomas");

        System.out.println(">>> "+user); // 아래와 같음
        System.out.println(">>> "+user.toString()); // toString을 오버라이드 해야함.


        // 생성자
        // @NoArgsConstructor (기본 생성자)
        User user1 = new User();

        // @RequiredArgsConstructor --> NonNull 변수만 주입
        User user2 = new User("tomas","tomas@test.com");

        // @AllArgsConstructor
//        User user3 = new User("tester", "test@test.com", LocalDateTime.now(), LocalDateTime.now());

        // Builder 사용 예시
        User user4 = User.builder()
                .name("martin")
                .email("martin@test.com")
                .build();

    }

}