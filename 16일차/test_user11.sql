insert into tbl_student values(2, '유재석', '남자', '코리아대학교', '컴퓨터');
insert into tbl_student values(3, '성춘향', '여자', '코리아대학교', '컴퓨터');
insert into tbl_student values(4, '최영희', '여자', '코리아대학교', '컴퓨터');
insert into tbl_student values(5, '김영희', '여자', '코리아대학교', '컴퓨터');
insert into tbl_student values(6, '이주일', '남자', '코리아대학교', '컴퓨터');
commit;
-- 총 6건
select count(*) from tbl_student;
select * from tbl_student where student_gender = '남자';
-- 총3건 : 홍길동, 유재석, 이주일
select * from tbl_student where student_gender = '여자';
-- 총3건 : 성춘향, 최영희, 김영희

select * from tbl_student where student_gender='남자';



