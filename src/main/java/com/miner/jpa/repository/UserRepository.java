package com.miner.jpa.repository;

import com.miner.jpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 공식 자료 링크
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords


//    User findByName(String name); // 네임이 중복될 경우 오류 발생. 리스트로 받아야함.
    List<User> findByName(String name);
//    Optional<User> findByName(String name);
//    Set<User> findByName(String name);

    // 아래 모두 동일한 쿼리
//    List<User> findByName(String name);
//    List<User> findByNameIs(String name);
//    List<User> findByNameEquals(String name);
//    List<User> findUserByName(String name);
//    List<User> findUserByNameIs(String name);
//    List<User> findUserByNameEquals(String name);


    // Query Subject Keywords
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email); // findByEmail 와 동일
    User findSomethingByEmail(String email); // 이상한 네이밍


    List<User> findFirst1ByName(String name);
    List<User> findTop1ByName(String name);
    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);
    List<User> findLast1ByName(String name); // 의도와 다르게 find 키워드와 동일하게 출력됨. 인식하지 않은 keywords 정의 주의
    List<User> findLast2ByName(String name); // 정의하면 테스트를 꼭 하자
    // orderBy 후 first 로 처리하면 좋아보임.

    // Query. And
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);

    // 숫자, 날짜 비교
    List<User> findByCreateAtAfter(LocalDateTime yesterday); // 양 끝 미포함 <,>
    List<User> findByIdAfter(Long id); // 양 끝 미포함 <,>
    List<User> findByCreateAtGreaterThan(LocalDateTime yesterday); // 양 끝 미포함 <,>
    List<User> findByCreateAtGreaterThanEqual(LocalDateTime yesterday); // 양 끝 포함 <=,>=
    List<User> findByCreateAtBetween(LocalDateTime yesterday,LocalDateTime tomorrow); // 양 끝 포함 <=,>=
    List<User> findByIdBetween(Long id1, Long id2); // 양 끝 포함 <=,>=
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2); // 양 끝 포함 <=,>=

    List<User> findByIdIsNotNull(); // 모든 레코드는 id 를 가지고 있지만 query 의 where 부분 확인
    // where u1_0.id is not null
//    List<User> findByIdIsNotEmpty();

    List<User> findByAddressIsNotEmpty(); // IsNotEmpty 는 잘 사용 안됨.
    // name is not null and name != ''; 이런 의미가 아니다!!


    List<User> findByNameIn(List<String> names);
    // where name in 'List' -> List 의 길이가 너무 길어지면 성능 이슈 발생 가능.
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);

    List<User> findTopByNameOrderByIdDesc(String name);
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);
    List<User> findFirstByName(String name, Sort sort);

    // 페이징
    Page<User> findByName(String name, Pageable pageable);
    // Page -> 응답값, Pageable -> 요청값

    @Query(value = "select * from user limit 1;", nativeQuery = true) // nativeQuery 설정으로 value 쿼리가 그대로 실행됨
    Map<String, Object> findRowRecord(); // ENUM(gender) 의 row 데이터 확인 용도.
}
