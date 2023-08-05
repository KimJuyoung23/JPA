package com.miner.jpa.repository;

import com.miner.jpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void crudUpdate(){
        // simpleJpaRepository 내용 보면 좋음.
        userRepository.save(new User("david","david@test.com")); // insert query

        User user1 = userRepository.findById(1L).orElseThrow(null);
        user1.setEmail("martin@test.com");
        userRepository.save(user1); // update query

//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);

    }

    @Test
    void crud(){
        // 데이터 입력
        System.out.println("\n=========================== Insert into values ========================\n");
//        userRepository.save(new User());
        userRepository.save(new User("martin","martin@test.com"));
        userRepository.save(new User("sophia","sophia@test.com"));
        userRepository.save(new User("tomas","tomas@test.com"));
        userRepository.save(new User("dennis","dennis@test.com"));
        userRepository.save(new User("martin","martin@naver.com"));

//        System.out.println(">>> "+userRepository.findAll()); // 아래 forEach 으로 보기 이쁘게 출력
//        userRepository.findAll().forEach(System.out::println); // 아래 for 문을 람다식으로 표헌
//        for (User user : userRepository.findAll()){
//            System.out.println(user);
//        }


        System.out.println("\n=========================== findAll ========================\n");
        List<User> users1 = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        users1.forEach(System.out::println);


        System.out.println("\n=========================== findAllById ========================\n");
//        //List<Long> ids = new ArrayList<>(); // 리스트 만들어서 조회할 id 값을 추가하지만, 편하게 아래처림 처리
//        //ids.add(1L); // 1(Long) 을 의미.
//        //ids.add(2L);
//        //List<User> users = userRepository.findAllById(ids)

        List<User> users2 = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));
        users2.forEach(System.out::println);


        System.out.println("\n=========================== saveAll ========================\n");
        User user1 = new User("jack","jack@test.com");
        User user2 = new User("steve","steve@test.com");

        userRepository.saveAll(Lists.newArrayList(user1,user2)); // 리스트로 전달하여 저장 가능
        List<User> users3 = userRepository.findAll();
        users3.forEach(System.out::println);

        System.out.println("\n=========================== save ========================\n");
        User user3 = new User("michael","michael@test.com");

        userRepository.save(user3); // 하나만 저장 가능
        List<User> users4 = userRepository.findAll();
        users4.forEach(System.out::println);


        System.out.println("\n=========================== getOne ========================\n");
        User user4 = userRepository.getOne(1L); // 오류 발생. findById 와 비교.
        // could not initialize proxy [com.miner.jpa.domain.User#1] - no Session
        // org.hibernate.LazyInitializationException: could not initialize proxy [com.miner.jpa.domain.User#1] - no Session
        // 세션 유지를 해야함. @Transactional 어노테이션 추가하여 세션 유지
        System.out.println(user4);

        System.out.println("\n=========================== findById ========================\n");
        Optional<User> user5 = userRepository.findById(1L); // Optional 타입으로 받는게 정상이지만.
        User user6 = userRepository.findById(1L).orElse(null); // 데이터가 없을 경우 orElse 로 처리하여 User 타입으로 데이터 받음

        System.out.println(user5);
        System.out.println(user6);

        System.out.println("\n=========================== flush && andFlush ========================\n");
        // DB 반영 시점을 제어. 영속성 개념에 필요.
        userRepository.save(new User("new martin","new_martin@test.com"));
        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);

        userRepository.saveAndFlush(new User("new martin","new_martin@test.com"));
//        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);

        System.out.println("\n=========================== count ========================\n");
        long count = userRepository.count();// 개수 출력
        System.out.println(count);


        System.out.println("\n=========================== existsById ========================\n");
        boolean exists = userRepository.existsById(1L); // 특이사항은 sql 쿼리를 보면 select count(*) from 함.
        // 자세한건 existsById 를 command + options + 좌클릭하여 추적
        System.out.println(exists); // true

        System.out.println("\n=========================== delete ========================\n");
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new)); //람다식
        userRepository.findAll().forEach(System.out::println);

        System.out.println("\n=========================== deleteAllInBatch ========================\n");
        // deleteAll 보다 최적화
        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(4L, 5L))); // 2L, 3L 삭제
        // select findAllById 에 사용되고 deleteAllInBatch 는  delete where id=? or id=? 식으로 delete 한번만 실행
        // sql 쿼리 개수 : select(findAllById 사용) + delete(deleteAllInBatch)
//        userRepository.deleteAllInBatch(); // <-- delete 쿼리 하나로 끝
        userRepository.findAll().forEach(System.out::println);


        System.out.println("\n=========================== deleteAll ========================\n");
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(2L,3L))); // 2L, 3L 삭제
        // findAllById 에 사용되는 select + deleteAll 한번더 데이터 유무 확인 후 deleteAll 는 개별로 delete 문이 실행
        // sql 쿼리 개수 : select(findAllById 사용) + select(deleteAllInBatch) + delete(deleteAllInBatch)*개수
        userRepository.findAll().forEach(System.out::println);

        System.out.println("\n=========================== deleteAll ========================\n");
        userRepository.deleteAll(); // 모든 데이터를 하나씩 삭제함.
        userRepository.findAll().forEach(System.out::println);


    }

    @Test
    void crudPage(){
        userRepository.save(new User("martin","martin@test.com"));
        userRepository.save(new User("sophia","sophia@test.com"));
        userRepository.save(new User("tomas","tomas@test.com"));
        userRepository.save(new User("dennis","dennis@test.com"));
        userRepository.save(new User("martin","martin@naver.com"));

        Page<User> users = userRepository.findAll(PageRequest.of(1,3)); // 0페이지부터 시작
        System.out.println(users);
        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements()); // 총 레코드 개수
        System.out.println("totalPage : " + users.getTotalPages()); // 페이지 개수
        System.out.println("numberOfElements : " + users.getNumberOfElements()); // 가져온 개수. 레코드 숫자
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);

    }

    @Test
    void queryByExample(){ // --> 아무것도 출력이 안되지만..
        // Example 한계점으로 String 위주로 사용됨. 많이 쓰이지 않음.
        System.out.println("\n=========================== insert into values ========================\n");
        userRepository.save(new User("martin","martin@test.com"));
        userRepository.save(new User("sophia","sophia@test.com"));
        userRepository.save(new User("tomas","tomas@test.com"));
        userRepository.save(new User("dennis","dennis@test.com"));
        userRepository.save(new User("martin","martin@naver.com"));


        System.out.println("\n=========================== 1. ExampleMatcher ========================\n");
        ExampleMatcher matcher1 = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith()); // "name" ignore, "email" 의 "endsWith" 부분
        Example<User> example1 = Example.of(new User("ma","naver.com"), matcher1); // probe 는 가짜 데이터? 느낌
        userRepository.findAll(example1).forEach(System.out::println); // "name"은 무시하고 "email" 끝 부분(endsWith)만 Matche 된 레코드만 출력

        System.out.println("\n=========================== 2. without ExampleMatcher ========================\n");

        Example<User> example2 = Example.of(new User("ma","naver.com")); // ExampleMatcher 없어서 아무것도 출력 안됨.
        userRepository.findAll(example2).forEach(System.out::println);

        System.out.println("\n=========================== 3. without ExampleMatcher 정확하게 명시 ========================\n");

        Example<User> example3 = Example.of(new User("martin","martin@test.com")); // 정확하게 동일한 레코드만 출력
        userRepository.findAll(example3).forEach(System.out::println);

        System.out.println("\n=========================== 4. ExampleMatcher 실사용방법 ========================\n");
        User user4 = new User();
        user4.setEmail("test");
        ExampleMatcher matcher4 = ExampleMatcher.matching().withMatcher("email",contains()); //contains 양방향 검색
        Example<User> example4 = Example.of(user4, matcher4);
        userRepository.findAll(example4).forEach(System.out::println);

    }


}