insert into tbl_student values(2, '���缮', '����', '�ڸ��ƴ��б�', '��ǻ��');
insert into tbl_student values(3, '������', '����', '�ڸ��ƴ��б�', '��ǻ��');
insert into tbl_student values(4, '�ֿ���', '����', '�ڸ��ƴ��б�', '��ǻ��');
insert into tbl_student values(5, '�迵��', '����', '�ڸ��ƴ��б�', '��ǻ��');
insert into tbl_student values(6, '������', '����', '�ڸ��ƴ��б�', '��ǻ��');
commit;
-- �� 6��
select count(*) from tbl_student;
select * from tbl_student where student_gender = '����';
-- ��3�� : ȫ�浿, ���缮, ������
select * from tbl_student where student_gender = '����';
-- ��3�� : ������, �ֿ���, �迵��

select * from tbl_student where student_gender='����';



