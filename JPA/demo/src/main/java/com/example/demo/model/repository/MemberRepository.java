package com.example.demo.model.repository;

import com.example.demo.model.Member;
import com.example.demo.model.MemberStats;
import com.example.demo.model.MemberStatsNative;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // JpaRepository를 상속받은 인터페이스를 정의하면 다음과 같은 메서드가 제공됨
    // public List<Member> findById(Long id); //id를 사용해 회원을 조회함
    // public List<Member> findAll(); // 모든 회원 목록을 조회합니다
    //public Member save(Member member); // 회원 정보를 데이터베이스에 저장(생성 or 업데이트)하고 저장된 정보 반환
    // public void delete(Member member); // 회원 정보를 데이터베이스에서 삭제
    // public Boolean existsById(Long id); // id를 사용해 회원 정보가 존재하는지 여부 확인
    // public long count(); // 데이터베이스에 저장된 회원 개수 반환

    // 메서드 이름으로 쿼리하기
    // WHERE절 이하의 조건들에 대해서 JPA 에서 정의한 메서드 명명 규칙을 사용해 특정 컬럼을 비교 검색할 수 있음 - JPA Query Method

    // 검색 명명 규칙
    //findBy + 컬럼 이름
    // 아이디로 검색하는 것이 아니라면 여러 개가 조회될 수 있기 때문에 List 타입으로 반환해야 함
    List<Member> findAllByName(String name);
    List<Member> findByName(String name);
    List<Member> findByNameIs(String name);
    List<Member> findByNameEquals(String name);

    // find 이외에도 get, query, read, search 사용 가능
    List<Member> getByName(String name);
    List<Member> queryByName(String name);
    List<Member> readByName(String name);
    List<Member> searchByName(String name);

    // 두 가지 이상의 조건 검색
    // JPA에서는 컬럼 이름을 AND, OR로 연결해 복수의 컬럼으로 검색하는 것을 지원함

    // 이름과 이메일을 AND 조건으로 조회
    List<Member> findByNameAndEmail(String name, String email);

    // 이름과 이메일을 OR 조건으로 조회
    List<Member> findByNameOrEmail(String name, String email);

    // 이름의 시작으로 회원 조회
    List<Member> findByNameStartingWith(String name);

    // 이름의 마지막으로 회원 조회
    List<Member> findByNameEndingWith(String name);

    // 이름을 포함하는 회원 조회
    List<Member> findByNameContaining(String name);

    // 이름을 포함하는 회원 조회로 LIKE 검색을 위한 와일드카드 %를 매개변수에 포함
    // ex) findByNameLike("%윤%")
    List<Member> findByNameLike(String name);

    // 크기 비교
    // 나이 정보가 존재하지 않는 회원 조회
    List<Member> findByAgeIsNull();

    // 나이 정보가 존재하는 회원 조회
    List<Member> findByAgeIsNotNull();

    // 매개변수로 전달된 나이로 회원 조회
    List<Member> findByAgeIs(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 많은 회원 조회
    List<Member> findByAgeGreaterThan(Integer age);
    List<Member> findByAgeAfter(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 많거나 같은 회원 조회
    List<Member> findByAgeGreaterThanEqual(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 적은 회원 조회
    List<Member> findByAgeLessThan(Integer age);
    List<Member> findByAgeBefore(Integer age);

    // 매개변수로 전달된 나이보다 나이가 더 적거나 같은 회원 조회
    List<Member> findByAgeLessThanEqual(Integer age);

    // 매개변수로 전달된 나이를 포함해 그 사이 나이의 회원 조회
    List<Member> findByAgeBetween(Integer min, Integer max);

    // SQL문을 함수로 옮겨오기
    List<Member> findByNameIsAndEmailOrAgeIsGreaterThan(String name, String email, Integer age);

    // 정렬 순서
    // 이름순으로 조회
    List<Member> findByOrderByNameAsc();

    // 이름의 역순으로 조회
    List<Member> findByOrderByNameDesc();

    // 이름순으로 조회하고 이름이 같은 경우에는 나이의 역순으로 조회
    List<Member> findByOrderByNameAscAgeDesc();

    // 이름의 일부로 검색하고 그 결과는 이름순으로 조회
    List<Member> findByNameContainingOrderByNameAsc(String name);
    // 컬럼 검색 조건과 정렬조건 등이 복합적으로 사용되면 메서드의 이름이 길어지고 가독성이 떨어짐
    // 이럴 때 Sort객체를 만들어 사용할 수 있음

    // 나이순으로 정렬하고 나이가 가장 적은 한 명을 조회
    Member findFirstByOrderByAgeAsc();
    Member findTopByOrderByAgeAsc();

    // 나이순으로 정렬하고 나이가 가장 적은 두 명을 조회
    List<Member> findFirst2ByOrderByAgeAsc();
    List<Member> findTop2ByOrderByAgeAsc();

    // Sort 객체
    // Sort sort = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("age"));
    List<Member> findByNameContaining(String name, Sort sort);

    // 페이지(프론트에서 여러 페이지에 걸쳐 보여주는 경우)
    // Pageable 객체
    // Pageable pageable = PageRequest.of(0, 20, Sort.by(DESC, "name"));
    Page<Member> findByNameContaining(String name, Pageable pageable);

    // 이메일을 사용해 회원 삭제
    @Transactional
    int deleteByEmail(String email);

    // 이름을 사용해 회원 삭제
    @Transactional
    int deleteByName(String name);

    // JPQL
    @Query("SELECT m FROM Member m WHERE m.name = :name")
    List<Member> findMember(@Param("name") String name);

    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.email = :email")
    List<Member> findMember(@Param("name") String name, @Param("email") String email);

    @Query("""
        SELECT m.name, m.email, COUNT(a.id) as count
            FROM Member m LEFT JOIN Article a On a.member = m
            GROUP BY m ORDER BY count DESC
    """)
    List<Object[]> getMemberStatsObject();

    // DTO - MemberStats 객체
    @Query("""
        SELECT new com.example.demo.model.MemberStats(m.name, m.email, COUNT(a.id) as count)
        FROM Member m LEFT JOIN Article a On a.member = m
        GROUP BY m ORDER BY count DESC
    """)
    List<MemberStats> getMemberStats();

    @Modifying
    @Query("UPDATE Member m SET m.age = :age")
    @Transactional
    int setMemberAge(Integer age);

    //SQL문 그대로 사용하기
    @Query(value= """
        SELECT m.name, m.email, count(a.id) AS count
            FROM member m LEFT JOIN article a ON m.id = a.member_id
            GROUP BY m.id ORDER BY count DESC
    """, nativeQuery = true)
    List<Object[]> getMemberStatsNativeObjects();

    // ModelStatsNative.java 인터페이스
    // 네이티브 SQL에서는 객체를 생성할 수 없기 때문에 조회한 컬럼을 반환하고 대신 JPA가 이것과 인터페이스를 사용해 객체를 만들어 줌
    @Query(value= """
        SELECT m.name, m.email, count(a.id) AS count
            FROM member m LEFT JOIN article a ON m.id = a.member_id
            GROUP BY m.id ORDER BY count DESC
    """, nativeQuery = true)
    List<MemberStatsNative> getMemberStatsNative();
}
