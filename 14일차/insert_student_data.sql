-- insert_student_data.sql
-- student_id  int  primary key
-- student_name varchar2( 30 ) not null
-- student_gender  char( 10 )
-- student_school_name varchar2( 30 )
-- student_major_name varchar2( 30 )
-- 첫 번째 학생인 홍길동의 데이터를 테이블에 저장하는
-- insert into sql 명령어를 만들기
insert into tbl_student 
        (student_id, student_name, student_gender,
         student_school_name, student_major_name)
       values( 1, '홍길동', '남자', '코리아대학교', '컴퓨터' );

-- 두 번째 학생인 유재석의 데이터를 테이블에 저장하는
-- insert into sql 명령어를 만들기
insert into tbl_student 
       (student_id, student_name, student_gender,
        student_school_name, student_major_name)
        values( 2, '유재석', '남자', '제주대학교', '물리' );

-- 모든 컬럼(변수)에 값을 저장하는 경우에는 ( 컬럼이름 )은
-- 생략 가능
insert into tbl_student 
         values (3, '강호동', '남자', '부산대학교', '영문');




















