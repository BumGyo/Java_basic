-- 주석(Comment) : sql 명령어에서의 주석 명령어
-- 새로운 학생 테이블을 오라클 데이터베이스에 만들어주는
-- sql 명령어를 입력합니다!!
create   table  tbl_student (
    -- 학생 아이디를 정수로 보관해주는 컬럼(변수) 선언
    student_id  int  primary key,
    -- primary key : 기본 키 명령어(예약어) : 유일한 값만을 저장
    -- 학생 이름을 문자열로 보관해주는 컬럼(변수) 선언
    student_name varchar2( 30 ) not null ,
    -- null : 데이터(값), not : 부정(반대), null 이 아니어야 함
    -- student_name 변수로는 꼭 값이 들어와야 함
    -- 모든 변수에 동시에 값이 들어옴(레코드: Record)
    -- 성별을 보관할 수 있는 변수(컬럼) 선언 
    -- "남자" 또는 "여자"
    student_gender  char( 10 ) , 
    -- 학교 이름을 보관할 수 있는 컬럼(변수) 선언
    student_school_name varchar2( 30 ) ,
    -- 전공 이름을 보관할 수 있는 컬럼(변수) 선언
    student_major_name varchar2( 30 )
);








