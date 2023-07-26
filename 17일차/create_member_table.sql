-- 새로 만드실 회원 테이블 create table sql
-- 테이블 이름은 tbl_member
-- 회원 아이디를 보관할 컬럼 선언 :
-- 회원 이름을 보관할 컬럼 선언 :
-- 회원 성별을 보관할 컬럼 선언 :
-- 회원 나이를 보관할 컬럼 선언 :
create table tbl_member (
   member_id int primary key,
   member_name varchar2(30) not null,
   member_gender varchar2(10) not null,
   member_age int not null
);

