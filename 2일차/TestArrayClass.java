/*
 * 예제 제목 : 최대 10개의 정수를 보관할 수 있는 배열을
 *  만들고 사용하는 예제
 */
// 컬렉션에서 제공해주는 리스트 인터페이스를 사용
import java.util.List;
// ArrayList 클래스를 사용
import java.util.ArrayList; // 배열 + 가변 크기

public class TestArrayClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 모든 컬렉션 관련된 인터페이스와
		 * 클래스들은 일반화(Generic) 개념을 구현
		 * -> 모든 종류의 데이터들을 다 보관(담을 수 있음)
		 * -> 선언할 때 메모리에 저장할 데이터를 지정
		 * 예) List<String> : 문자열 만을 보관
		 * 예) List<Integer> : Wrapper 클래스 :
		 *     포장지 역할을 하는 클래스 :
		 *     정수형 데이터를 외부로부터 보호
		 *     Integer -> 클래스 이름
		 *     int -> 기본 자료형 이름
		 * List<Object> 참조_변수이름;     
		 */
		// 문자열을 보관할 수 있는 동적 배열을
		// 참조할 수 있는 변수를 선언
		List<String> refStringList;		
		// new 연산자를 사용해서 기본적으로 10개의
		// 문자열을 저장할 수 있는 배열을 생성
		refStringList = new ArrayList<String>();		
		// 참조 변수의 선언과 메모리 공간을 만드는
		// 부분을 동시에 하기
		List<String> refStringList2 = 
					new ArrayList<String>();		
		/*
		 * add( ) 함수를 호출해서 동적 배열에 값을 저장
		 */
		refStringList.add("first");
		refStringList.add("second");
		refStringList.add("third");
		
		/*
		 * 동적 배열이 갖고 있는 값에 접근할 때는
		 * get( ) 함수를 호출하시면 됩니다!!
		 * -> get ( 방 번호 ); 
		 * 
		 */
		// size( ) 함수를 호출하시면 add( ) 함수를
		// 호출한 횟수를 알려줍니다!! -> 데이터 갯수
		int count = refStringList.size();
System.out.
  println("동적 배열에 있는 데이터의 갯수는 " + count);
if(count > 0) {
  System.out.println("현재 동적 배열에는 데이터가 있음");
  // 동적 배열에 보관되어 있는 데이터에 접근 : 
  // get(방번호)
  // -> 방 번호 : 0부터 시작 size() - 1 까지
  for(int i = 0; i < count; i++) {
	  System.out.
	    println(
	     "동적 배열 refStringList["+i+
	       "] 위치에 있는 값은 "+refStringList.get(i));
  }
}
else {
  System.out.println("현재 동적 배열에는 데이터가 없음");
}

		
		
		
		int iarray[];
		
		iarray = new int[10];
		
		for(int index = 0; index < iarray.length; 
				index++)
			// sysout : 템플릿(Template) 기능을 사용
			// sysout + Ctrl 키 + Space 키를 누르시면
			// 자동으로 System.out.println(); 명령어가
			// 삽입됩니다!!
			System.out.
println
("배열 iarray["+index+"] 방에 보관되어 있는 정수는 " + 
         iarray[index]);
	}
}
