-- insert_student_data.sql
-- student_id  int  primary key
-- student_name varchar2( 30 ) not null
-- student_gender  char( 10 )
-- student_school_name varchar2( 30 )
-- student_major_name varchar2( 30 )
-- ù ��° �л��� ȫ�浿�� �����͸� ���̺� �����ϴ�
-- insert into sql ��ɾ �����
insert into tbl_student 
        (student_id, student_name, student_gender,
         student_school_name, student_major_name)
       values( 1, 'ȫ�浿', '����', '�ڸ��ƴ��б�', '��ǻ��' );

-- �� ��° �л��� ���缮�� �����͸� ���̺� �����ϴ�
-- insert into sql ��ɾ �����
insert into tbl_student 
       (student_id, student_name, student_gender,
        student_school_name, student_major_name)
        values( 2, '���缮', '����', '���ִ��б�', '����' );

-- ��� �÷�(����)�� ���� �����ϴ� ��쿡�� ( �÷��̸� )��
-- ���� ����
insert into tbl_student 
         values (3, '��ȣ��', '����', '�λ���б�', '����');




















