-- ���� ����� ȸ�� ���̺� create table sql
-- ���̺� �̸��� tbl_member
-- ȸ�� ���̵� ������ �÷� ���� :
-- ȸ�� �̸��� ������ �÷� ���� :
-- ȸ�� ������ ������ �÷� ���� :
-- ȸ�� ���̸� ������ �÷� ���� :
create table tbl_member (
   member_id int primary key,
   member_name varchar2(30) not null,
   member_gender varchar2(10) not null,
   member_age int not null
);

