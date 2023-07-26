/*
 * 스택(Stack) 자료 구조를 구현하기
 * 1. 크기가 고정인 정적 배열을 이용
 * 2. 크기가 가변인 동적 배열을 이용
 */
import java.util.*;
public class TestStackClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 1. Scanner 클래스를 사용해서
		 *    배열에 저장할 정수를 사용자로부터 입력
		 *    받기
		 *    1) 사용 방법 : java.util 패키지에 있습니다.
		 *    Scanner 참조변수이름 = 
		 *       new Scanner(키보드입력: System.in);
		 */
System.out.println("키보드 입력에 사용할 스캐너 객체를 생성하기");
Scanner refScanner = new Scanner(System.in);
// System.in : static 변수로써 메모리에 있는 키보드의
// 주소를 갖고 있는 자동 변수(내부 변수)
// new Scanner(입력 소스를 지정);
// 1. 정적 배열을 생성하기 : 최대10개의 정수만을 보관
int iarray[] = new int[10];
// 2. 동적 배열을 생성하기 : 최대 데이터 갯수는 메모리 내에서
ArrayList<Integer> refIntList = 
                       new ArrayList<Integer>();

// 3. iarray 배열에 저장되는 가장 마지막 데이터의
// 위치를 보관할 top 변수를 선언하고 -1로 초기화
int top1 = -1;
// 4. 동적 배열 refIntList에 저장되는 가장 마지막 데이터의
// 위치를 보관할 top 변수를 선언하고 -1로 초기화
int top2 = -1;
// 5. 정적 배열을 사용해서 최대 10개의 정수를
// 사용자로부터 입력 받아서 배열에 저장하는 반복문을
// 작성하기
// for(int i = 0; i < iarray.length; i++) {
for(int i = 0; i <= 10; i++) {
	System.out.print("정적 배열인 iarray의 "+i+
			 " 위치에 저장할 정수를 입력하세요: ");
	
	// 사용자가 정수를 입력할 수 있도록하기 위해서
	// Scanner 클래스가 갖고 있는 nextInt( ) 함수를 호출
	int value = refScanner.nextInt();
	
	// 스택 메모리에 데이터를 저장하는 연산 : push()
	/*
	 * top 변수의 시작 값에 따라서 알고리즘이 달라짐
	 * -> -1 -> 0으로 만들어 주셔야 합니다!! -> ++ 연산자
	 */
	top1++; // -1 -> 0
	/*
	 * 증가한 top 변수의 값이 배열의 방 번호로 
	 * 사용할 수 있는지를 검사하기
	 */
	if(top1 >= 0 && top1 < iarray.length) {
System.out.
      println("사용자로부터 입력 받은 값을 배열에 저장");
iarray[top1] = value;
	}
	else {
System.out.println("스택 오버플로우 현상이 발생!!");
System.out.
 println
("변수 top의 값의 배열의 실제 인덱스 위치 범위를 "
		+ "초과했습니다!!");
	break; // for 반복문을 탈출하기
	} // end of else
} // end of for

/*
 * 스택 자료 구조에서 데이터를 꺼내오는 연산 : pop
 * 현재 변수 top1의 위치에서 하나의 데이터를 꺼내옵니다!!
 * -> 변수 top1의 위치가 배열의 인덱스 범위에 있는지를 검사
 */
top1 = 9;
for(int i = 0; i <= 10; i++) {
	System.out.println
	  ("정적 배열 iarray["+top1+"] 위치에서 하나의 ");
	System.out.println("정수를 꺼내와서 화면에 출력");
	if(top1 >= 0 && top1 < iarray.length) {
int value = iarray[top1];
System.out.println("꺼내온 정수는 "+value+" 입니다!!");
// 다음 번에 꺼내올 데이터의 방 번호로 이동(감소)
--top1;
	}
	else {
System.out.println("스택 언더플로우 현상!!");
System.out.
    println("스택에서 데이터를 꺼내올 수 없습니다!!");
break;
	}
}


	} // end of main
} // end of class


