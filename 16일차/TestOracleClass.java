package day20180801;

/*
 * ����Ŭ �����ͺ��̽��� �����ϴ� ���� ����
 * 
 * 1. ojdbc6.jar ������ ���� ������Ʈ�� �ڹ� ���� �н��� �ִ����� Ȯ��
 * 
 * 2. oracle.jdbc.OracleDriver.class Ŭ������ ����ϱ�
 * 
 * 1) import oracle.jdbc.OracleDriver;
 * 
 */
/*
 * SQL ���� ��ɾ �����ϱ� ���ؼ��� java.sql ��Ű���� ���
 */
import java.sql.*;

public class TestOracleClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// -1. select * from tbl_student where student_gender=?
		String selectSQL2 = "select * from tbl_student where student_gender = '����'";
		
		// sql ��ɾ� �ȿ� ����ǥ(?) ��ȣ�� ������ 
		// Statement �������̽��� ����� �� ����
		// -> PreparedStatement �������̽��� ����ؾ� ��!!
		PreparedStatement preparedStatement = null;
		
		// executeQuery( ) �Լ��� ����� 2���� ǥ ���·� ������ ����
		ResultSet resultSet2 = null;
	
		
		// 0. select * from tbl_student sql ��ɾ ������ ��
		// ����� �������� �غ��ϱ�
		String selectSQL1 = "select * from tbl_student";
		// ���ڿ� select sql ��ɾ ��ü�� ������ �� ����� ����
		Statement statement = null;
		// select sql ��ɾ��� ���� ����� ������ ���� ����
		ResultSet resultSet = null;
	
		// 1. ����Ŭ �翡�� �������ִ� OracleDriver Ŭ������ �޸𸮿�
		// �����ϴ� ��ɾ �ۼ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. OracleDriver Ŭ������ ���������� �޸𸮿� �������
			// ��쿡 ����� ��ɾ �ۼ�
			System.out.println("OracleDriver Ŭ������ �޸𸮿� �������");

			// 3. getConnection( ) �Լ��� ����ؼ�
			// ���α׷����� ����Ŭ �����ͺ��̽��� �α����� ���ִ� �Լ�
			// -> java.sql ��Ű���� �ִ� DriverManager Ŭ������ ���� �ִ�
			// -> static �Լ��̹Ƿ� DriverManager.getConnection(1, 2, 3)
			// -> 1 : ����Ŭ �����ͺ��̽��� �ּ� :
			// -> jdbc:oracle:thin:@localhost:1521:xe
			// -> jdbc: �������� �̸�(��� �̸�)
			// -> oracle: �����ͺ��̽� �̸�
			// -> thin: ������ ����Ŭ ����̹� Ŭ������ ����ϴ� ���
			// -> @localhost : ��ǻ�� �̸�
			// -> 1521 : ����Ŭ �����ͺ��̽� ������ �ý��� ��ġ ��Ʈ ��ȣ
			// -> xe : ����Ŭ �����ͺ��̽� �̸�
			// -> 2. �α��ο� ����� ����� ���� �̸� : test_user1
			// -> 3. �α��ο� ����� test_user1 ������ ���� �ִ� ��й�ȣ : 1234

			Connection refOracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"test_user1", "1234");
			// 4. ����Ŭ �����ͺ��̽��� ���ӿ� ������ ��쿡 ������ ��ɾ
			// �ۼ�
			System.out.println("����Ŭ �����ͺ��̽��� ���� ����!!");
			
			// 41. select * from tbl_student where student_gender=?
			// ��ɾ ��ü�� ������ �� ����� PreparedStatement ��ü�� �����
			//preparedStatement = refOracleConnection.prepareStatement(selectSQL2);
			statement = refOracleConnection.createStatement();
						
			// 42. ����ǥ(?) ��ġ�� ���� �־��ֱ�
			// preparedStatement.setString(1, "����");
			// preparedStatement.setString(1, "����");
			// 1 : ����ǥ ��ġ ��ȣ(0���� �ƴ�: 1����)
							
			// 43. executeQuery( ) �Լ��� ����
			// resultSet2 = preparedStatement.executeQuery();
			resultSet2 = statement.executeQuery(selectSQL2);
						
			// 44. while( ) �ݺ����� ����ؼ� resultSet2 ������
			// ��ƿ� �л� �����͵��� ���� ������ ȭ�鿡 ���
			while(resultSet2.next() == true) {
				// "student_id" : �÷� �̸��� �����ؾ� ��
				int id = resultSet2.getInt("student_id");
				
				System.out.println("111");
				
				String name = resultSet2.getString("student_name");
				String gender = 
						resultSet2.getString("student_gender");
				String schoolName = 
						resultSet2.getString("student_school_name");
				String majorName = 
						resultSet2.getString("student_major_name");
				
				System.out.println("���̵�� "+id);
				System.out.println("�̸��� "+name);
				System.out.println("������ "+gender);
				System.out.println("�б� �̸��� "+schoolName);
				System.out.println("���� �̸��� "+majorName);				
			}
			
			System.out.println("��� ���� �л����� �����͵��� ���");			
			
			// 5. �޸𸮿� Statement ��ü�� �����ϱ�
			// -> refOracleConnection ������ ���� �ִ� createStatement( ) �Լ��� ȣ��
			//statement = refOracleConnection.createStatement();
			
			// 6. executeQuery( ) �Լ��� ȣ���ؼ�
			// Statement �������̽��� ���� �ִ� select sql ��ɾ ����
			// -> ����� ResultSet ������ ��� �ɴϴ�!!
			//resultSet = statement.executeQuery(selectSQL1);
			
			// 7. resultSet ������ ����Ŭ �����ͺ��̽��κ��� ������
			// ���� ������ �˻��ϱ� : next( ) �Լ��� ȣ��
			// -> resultSet ������ ���� �ִ� next( ) �Լ��� ȣ��
			// -> ����Ŭ �����ͺ��̽��κ��� ������ �����Ͱ� ������ ��(true)
			// -> �� ��ȯ �����Ͱ� ������ ����(false) ���� ��ȭ
//			while(resultSet.next() == true) {
//				/*
//				 * resultSet ������ ��ƿ� ��� ������ �ϳ���
//				 * �о�ͼ� �ӽ� ������ �����ϱ�
//				 * 1. �л� ���̵� ���� �о�ͼ� �ӽ� ������ id�� ����
//				 */
//				int id = resultSet.getInt(1);
//				// 1 : create table ��ɾ�� �÷��� ���� ��ȣ
//				// 2. �л� �̸� ���� �о�ͼ� �ӽ� ������ name�� ����
//				String name = resultSet.getString(2);
//				
//				// 3. ������ �о�ͼ� �ӽ� ������ ����
//				String gender = resultSet.getString(3);
//				
//				// 4. �б� �̸��� �о�ͼ� �ӽ� ������ ����
//				String schoolName = resultSet.getString(4);
//				
//				// 5. ���� �̸��� �о�ͼ� �ӽ� ������ ����
//				String majorName = resultSet.getString(5);
//				
//				// �ܼ� ȭ�鿡 ����� ����ϱ�
//				System.out.println("���̵�� " + id + " �Դϴ�!!");
//				System.out.println("�̸��� "+ name + " �Դϴ�!!");
//				System.out.println("������ "+ gender + " �Դϴ�!!");
//				System.out.println("�б� �̸��� "+ schoolName);
//				System.out.println("���� �̸��� "+ majorName);
//			}
//			
//System.out.println("��� �л����� �����͸� �о�ͼ� ȭ�� ��� �Ϸ�");

		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver Ŭ������ ã�� ����");
		} catch (SQLException sqle) {
			System.out.println("����Ŭ �����ͺ��̽��� ���� ����!!");
		} finally {
			
			// �� ����� �� �޸� ������ �ý��ۿ� �����ֱ� : close( )
			try {
				
				if(resultSet2 != null) 
					resultSet2.close();
				
				if(preparedStatement != null) 
					preparedStatement.close();
				
				//if(resultSet != null) resultSet.close();				
				//if(statement != null) statement.close();
				
			} catch(SQLException sqle2) {
				
System.out.println("���ܻ�Ȳ�� �߻� ������ "+sqle2.getMessage());
				
			}
			
		}

	}
}
