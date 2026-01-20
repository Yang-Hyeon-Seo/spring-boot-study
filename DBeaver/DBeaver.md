# DBeaver에서 자주 사용하는 명령어
- 스프링 부트 애플리케이션을 개발할 때 많이 사용하는 SQL문
- MYSQL 데이터베이스가 *컴퓨터에 설치되고 나서 프로세스로 실행되어 있는 것*을 `MySQL 인스턴스`라고 함
- 하나의 인스턴스에는 *논리적으로 독립된 여러 개의 데이터데이스를 생성할 수 있음*
```SQL
-- 데이터베이스 생성
create database mydb;

-- 데이터베이스 삭제. DB 안의 모든 데이터도 삭제됨
drop database mydb;
```
- 스프링 부트 애플리케이션이나 DBeaver가 MySQL 데이터베이스 연결 시 데이터베이스 이름을 지정하지 않았다면 use 라는 SQL 명령어를 사용해 사용할 데이터베이스를 명시해야 함
```SQL
-- mydb 데이터베이스를 선택하고, 그 안에 회원 정보를 저장할 테이블을 생성하는 SQL 예제

-- 사용할 DB 선택
use mydb;

-- 회원 테이블 생성
create table members (
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);

-- 회원 테이블 삭제
drop table members;
```
- 데이터베이스와 테이블을 생성했따면, INSERT 문을 사용해 회원 정보를 테이블에 생성할 수 있음.
```SQL
-- 우리가 작성할 애플리케이션에서 로그인 시 사용할 Seojun Yoon 이라는 회원의 아이디 및 패스워드와 계정 활성화 여부를 입력하는 예시

INSERT INTO members(username, password, enabled) VALUES(
    'SeojunYoon',
    '(비밀번호)',
    True
);
```
- 생성된 회원 정보 확인
```SQL
-- members 회원 테이블 조회
SELECT *
FROM members;
```

- 스프링 부트 애플리케이션을 개발하다 보면 정상적으로 동작하는지 확인하기 위해 데이터베이스에 접속한 후 다양한 SQL문을 실행해 볼 필요가 있음
- DBeaver는 MySQL 데이터베이스 및 JDBC를 지원하는 데이터베이스라면 무엇이든 연결해 SQL문을 테스트할 수 있는 편리한 GUI를 제공함
