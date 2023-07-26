package day20180801;

/*
 * 오라클 데이터베이스에 접속하는 예제 구현
 * 
 * 1. ojdbc6.jar 파일이 현재 프로젝트에 자바 빌드 패스에 있는지를 확인
 * 
 * 2. oracle.jdbc.OracleDriver.class 클래스를 사용하기
 * 
 * 1) import oracle.jdbc.OracleDriver;
 * 
 */
/*
 * SQL 관련 명령어를 실행하기 위해서는 java.sql 패키지를 사용
 */
import java.sql.*;

public class TestOracleClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// -1. select * from tbl_student where student_gender=?
		String selectSQL2 = "select * from tbl_student where student_gender = '남자'";
		
		// sql 명령어 안에 물음표(?) 기호가 있으면 
		// Statement 인터페이스를 사용할 수 없음
		// -> PreparedStatement 인터페이스를 사용해야 함!!
		PreparedStatement preparedStatement = null;
		
		// executeQuery( ) 함수의 결과를 2차원 표 형태로 보관할 변수
		ResultSet resultSet2 = null;
	
		
		// 0. select * from tbl_student sql 명령어를 실행할 때
		// 사용할 변수들을 준비하기
		String selectSQL1 = "select * from tbl_student";
		// 문자열 select sql 명령어를 객체로 보관할 때 사용할 변수
		Statement statement = null;
		// select sql 명령어의 실행 결과를 보관할 변수 선언
		ResultSet resultSet = null;
	
		// 1. 오라클 사에서 제공해주는 OracleDriver 클래스를 메모리에
		// 생성하는 명령어를 작성
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. OracleDriver 클래스가 성공적으로 메모리에 만들어진
			// 경우에 실행될 명령어를 작성
			System.out.println("OracleDriver 클래스가 메모리에 만들어짐");

			// 3. getConnection( ) 함수를 사용해서
			// 프로그램에서 오라클 데이터베이스로 로그인을 해주는 함수
			// -> java.sql 패키지에 있는 DriverManager 클래스가 갖고 있는
			// -> static 함수이므로 DriverManager.getConnection(1, 2, 3)
			// -> 1 : 오라클 데이터베이스의 주소 :
			// -> jdbc:oracle:thin:@localhost:1521:xe
			// -> jdbc: 프로토콜 이름(약속 이름)
			// -> oracle: 데이터베이스 이름
			// -> thin: 가벼운 오라클 드라이버 클래스를 사용하는 방법
			// -> @localhost : 컴퓨터 이름
			// -> 1521 : 오라클 데이터베이스 관리자 시스템 설치 포트 번호
			// -> xe : 오라클 데이터베이스 이름
			// -> 2. 로그인에 사용할 사용자 계정 이름 : test_user1
			// -> 3. 로그인에 사용할 test_user1 계정이 갖고 있는 비밀번호 : 1234

			Connection refOracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"test_user1", "1234");
			// 4. 오라클 데이터베이스에 접속에 성공한 경우에 실행할 명령어를
			// 작성
			System.out.println("오라클 데이터베이스에 접속 성공!!");
			
			// 41. select * from tbl_student where student_gender=?
			// 명령어를 객체로 보관할 때 사용할 PreparedStatement 객체를 만들기
			//preparedStatement = refOracleConnection.prepareStatement(selectSQL2);
			statement = refOracleConnection.createStatement();
						
			// 42. 물음표(?) 위치에 값을 넣어주기
			// preparedStatement.setString(1, "남자");
			// preparedStatement.setString(1, "여자");
			// 1 : 물음표 위치 번호(0부터 아님: 1부터)
							
			// 43. executeQuery( ) 함수를 실행
			// resultSet2 = preparedStatement.executeQuery();
			resultSet2 = statement.executeQuery(selectSQL2);
						
			// 44. while( ) 반복문을 사용해서 resultSet2 변수에
			// 담아온 학생 데이터들을 읽은 다음에 화면에 출력
			while(resultSet2.next() == true) {
				// "student_id" : 컬럼 이름과 동일해야 함
				int id = resultSet2.getInt("student_id");
				
				System.out.println("111");
				
				String name = resultSet2.getString("student_name");
				String gender = 
						resultSet2.getString("student_gender");
				String schoolName = 
						resultSet2.getString("student_school_name");
				String majorName = 
						resultSet2.getString("student_major_name");
				
				System.out.println("아이디는 "+id);
				System.out.println("이름은 "+name);
				System.out.println("성별은 "+gender);
				System.out.println("학교 이름은 "+schoolName);
				System.out.println("전공 이름은 "+majorName);				
			}
			
			System.out.println("모든 남자 학생들의 데이터들을 출력");			
			
			// 5. 메모리에 Statement 객체를 생성하기
			// -> refOracleConnection 변수가 갖고 있는 createStatement( ) 함수를 호출
			//statement = refOracleConnection.createStatement();
			
			// 6. executeQuery( ) 함수를 호출해서
			// Statement 인터페이스가 갖고 있던 select sql 명령어를 실행
			// -> 결과는 ResultSet 변수에 담아 옵니다!!
			//resultSet = statement.executeQuery(selectSQL1);
			
			// 7. resultSet 변수가 오라클 데이터베이스로부터 가져온
			// 값의 유무를 검사하기 : next( ) 함수를 호출
			// -> resultSet 변수가 갖고 있는 next( ) 함수를 호출
			// -> 오라클 데이터베이스로부터 가져온 데이터가 있으면 참(true)
			// -> 을 반환 데이터가 없으면 거짓(false) 값을 반화
//			while(resultSet.next() == true) {
//				/*
//				 * resultSet 변수에 담아온 모든 값들을 하나씩
//				 * 읽어와서 임시 변수에 저장하기
//				 * 1. 학생 아이디 값을 읽어와서 임시 변수인 id에 저장
//				 */
//				int id = resultSet.getInt(1);
//				// 1 : create table 명령어에서 컬럼의 순서 번호
//				// 2. 학생 이름 값을 읽어와서 임시 변수인 name에 저장
//				String name = resultSet.getString(2);
//				
//				// 3. 성별을 읽어와서 임시 변수에 저장
//				String gender = resultSet.getString(3);
//				
//				// 4. 학교 이름을 읽어와서 임시 변수에 저장
//				String schoolName = resultSet.getString(4);
//				
//				// 5. 전공 이름을 읽어와서 임시 변수에 저장
//				String majorName = resultSet.getString(5);
//				
//				// 콘솔 화면에 결과를 출력하기
//				System.out.println("아이디는 " + id + " 입니다!!");
//				System.out.println("이름은 "+ name + " 입니다!!");
//				System.out.println("성별은 "+ gender + " 입니다!!");
//				System.out.println("학교 이름은 "+ schoolName);
//				System.out.println("전공 이름은 "+ majorName);
//			}
//			
//System.out.println("모든 학생들의 데이터를 읽어와서 화면 출력 완료");

		} catch (ClassNotFoundException e) {
			System.out.println("OracleDriver 클래스를 찾지 못함");
		} catch (SQLException sqle) {
			System.out.println("오라클 데이터베이스에 접속 실패!!");
		} finally {
			
			// 다 사용한 힙 메모리 공간을 시스템에 돌려주기 : close( )
			try {
				
				if(resultSet2 != null) 
					resultSet2.close();
				
				if(preparedStatement != null) 
					preparedStatement.close();
				
				//if(resultSet != null) resultSet.close();				
				//if(statement != null) statement.close();
				
			} catch(SQLException sqle2) {
				
System.out.println("예외상황이 발생 내용은 "+sqle2.getMessage());
				
			}
			
		}

	}
}
