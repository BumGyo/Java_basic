package day20180716;

/*
 * 학생 성적을 사용자로부터 입력받아서
 * 컬렉션 변수에 저장합니다.
 * 나중에 컬렉션에 저장된 학생 성적들을 꺼내와서
 * 화면에 출력하는 예제를 만듭니다!!
 * 
 * 프로그램 실행 결과 화면
 * 
 * 1. ***학생 성적 입력 예제(컬렉션 사용)*** : 타이틀 출력
 * 2. 1(메뉴 번호). 입력(등록 또는 저장)
 * 3. 2(메뉴 번호). 출력(컬렉션 변수에 저장된 모든
 *    학생 성적 데이터들을 읽어와서 화면에 출력)
 * 4. 3(메뉴 번호). 프로그램 종료 
 */
// 동적 배열을 사용하려면 java.util 패키지에 있는
// ArrayList 클래스를 사용하시면 됩니다!!
// -> 현재 패키지와 다른 이름을 가진 패키지를 사용하려면
// -> 편하게 클래스 이름을 사용할 때 클래스 이름만 작성
// -> import + 띄어쓰기 + 패키지이름 + 클래스이름;
import java.util.ArrayList;
// 사용자로부터 학생 성적 데이터들을 입력받을 때 사용할
// Scanner 클래스를 포함하기
import java.util.Scanner;

public class TestStudentScoreCollection {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 1. 프로그램에서 사용할 변수들을 선언 + 
		 *    메모리에 생성하는 명령어들을 모아놓기
		 */
		// 1) 사용자 키보드 입력시에 사용할 스캐너 변수
		Scanner inputScanner = new Scanner(System.in);
		
		/*
		 * 2) 사용자가 입력한 학생 성적 데이터들을
		 * 보관할 컬렉션 변수 : 
		 *   사용자가 입력한 학생 성적 데이터들을
		 *   문자열로 보관하기 : String 클래스를 사용
		 */
		ArrayList<String> studentScoreList = 
				new ArrayList<String>();
		// "홍길동,100,100,100"
		/*
		 * 성적을 계산하고 싶은 경우에는
		 *    이름은 String 으로 저장하시고
		 *    국어 점수는 Integer 으로 저장,
		 *    영어 점수는 Integer 으로 저장,
		 *    수학 점수는 Integer 으로 저장
		 *    -> 산술 계산을 위해서는 정수를 메모리 저장
		 */
		// 사용자가 입력한 학생 성적들을 문자열로 보관할
		// 임시 변수를 선언
		String tempInput = ""; 
		// -> 위에서 선언한 studentScoreList 리스트에
		// -> 저장될 값을 보관
		// 반복문 구조는 무한 반복문
		while(true) {
			/*
			 * 1. 먼저 프로그램 제목을 화면에 출력
			 */
System.out.println("***학생 성적 입력(컬렉션 사용)***");
// 2. 메뉴들을 화면에 출력하기
System.out.println("1. 학생 성적 등록(입력 또는 저장)");
System.out.println("2. 모든 학생 성적들을 출력");
System.out.println("3. 프로그램 종료");
// 사용자로부터 입력 받는 메뉴 번호를 문자열로 보관할
// 변수를 선언
System.out.print("1 ~ 3 사이에 있는 메뉴 번호를 입력: ");
String inputMenuNo = inputScanner.nextLine();
// 사용자가 입력한 메뉴 번호에 따라서 명령어를 달리 처리
// -> 문자열 비교는 equals() 함수를 호출
if(inputMenuNo.equals("1")) {
	System.out.println("사용자가 메뉴 번호를 1을 입력");
	System.out.print("학생 이름,국어,영어,수학 점수들을 ");
	System.out.print("차례대로 입력한 후에 엔터를 누르세요: ");
	tempInput = inputScanner.nextLine();
	// add( ) 함수를 호출해서 리스트 변수에 저장하기
	if(tempInput.equals("") == false) {
		studentScoreList.add(tempInput);
System.
out.println
  ("리스트 변수에 새로운 학생 성적을 저장하였습니다!!");
	} // end of if(tempInput.equals("") == false)
} // end of if(inputMenuNo.equals("1"))
else if(inputMenuNo.equals("2")) {
	System.out.println("사용자가 2번 메뉴를 선택");
	System.out.println("리스트 변수가 갖고 있는 ");
	System.out.println("모든 학생 성적들을 읽어와서 ");
	System.out.println("화면에 출력하기!!");
	// 1) 리스트 변수에 저장되어 있는 학생 성적이 있는지 여부를 검사하기
	// -> 리스트 변수가 갖고 있는 size( ) 함수를 호출하면
	// -> 몇 명의 학생 성적이 저장되어 있는지를 알 수 있습니다!!
	int count = studentScoreList.size();
	if(count == 0) {
System.out.
        println("현재 저장된 학생 성적이 없습니다!!");
	}
	else {
System.out.println("현재 저장된 학생 성적 명 수는 " + 
	    count);		

// 현재 리스트 변수에 보관되어 있는 학생 성적 데이터들을 꺼내와서
// 화면에 출력하는 반복문을 작성하기
for(int index = 0; index < count ; index++) {
	// get( ) 함수를 호출해서 리스트 변수가 갖고 있는 학생 성적
	// 문자열을 읽어옵니다!!
	String data = studentScoreList.get(index);
	System.out.println(index + 
		 " 위치에 보관되어 있는 학생 성적은 " + data + " 입니다!!");
} // end of for(int index = 0; index < count ; index++)
	} // end of else
} // end of else if(inputMenuNo.equals("2")) {
else if(inputMenuNo.equals("3")) {
	System.out.println("사용자가 메뉴 번호 3을 입력!!");
	System.out.println("무한 반복문을 벗어납니다!!");
	break;
	// main( ) 함수 전체를 종료하시려면 System 클래스가 갖고 있는
	// exit( ) 함수를 호출 : System.exit(0);
}


		}
		
		
		
		
		
	}

}
