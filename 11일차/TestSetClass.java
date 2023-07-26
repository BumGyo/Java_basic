package day20180726;

/*
 * HashSet 클래스를 사용해서 여러 개의 데이터들을 메모리에 저장하고 사용
 * 
 * 1. 특징 : 
 *     데이터의 입력 순서를 보장하지 않음(데이터의 위치가 랜덤)
 *     입력했던 순서와 출력 순서는 다를 수 있습니다!!
 *     데이터의 중복을 허용하지 않습니다!!
 *       -> 같은 이름을 한 번만 메모리에 저장할 수 있습니다!!
 */
// HashSet 클래스를 사용하기 위해서 import 명령어를 작성
import java.util.HashSet;
/*
 * 
 * Set 계열의 클래스를 사용할 때에 주의 사항
 * -> 데이터를 메모리에 저장할 때는 add( ) 함수를 호출
 * -> 메모리에서 데이터를 꺼내올 때는 get( ) 함수를 사용할 수 없음
 *    -> 해결 : 반복자(Iterator)를 사용 : 반복 객체(Iteration Object)
 *       -> 자동으로 메모리에서 데이터를 하나씩 가져오는 객체
 *       
 * 예) int iarray [ ] = new int[ 5 ];
 *     for(int index = 0; index < iarray.length; index++)
 *        System.out.println(iarray[index]);
 *        
 *     향상된 for 반복문을 사용하기 : JDK 1.5부터 사용 가능
 *     for( 배열에 저장되어 있는 데이터 자료형 + 값을 보관할 변수 이름 
 *          + 콜론(:) + 배열이름 )
 *        // 위처럼 작성하면 배열에서 첫 번째 값 부터 차례대로 가져옵니다!!
 *        // 변수에 저장 -> 위치 번호 이동(바꾸는 것)이 자동
 *        // -> int index = 0; index < 배열이름.length; index++
 *        
 * 예) // 반복자 객체를 사용해서 메모리에 저장되어 있는 데이터를
 *     // 가져와야 합니다!! -> Iterator 인터페이스를 사용
 *     Iterator<메모리에 저장되어 있는 데이터의 자료형>
 *        참조_변수이름 = HashSet_객체_참조변수이름.iterator( );
 *        -> 참조_변수이름 바로 사용
 *    
 *     Iterator 참조_변수이름2 = HashSet_객체_참조변수이름.iterator( );
 *     -> (형 변환)참조_변수이름2
 *     
 *     while( 참조_변수이름.hasNext( )  == true ) {
 *     
 *        // hasNext( ) 함수의 기능 : 메모리에 데이터가 있는지를 판단
 *        // -> 데이터가 있으면 참(true)을 반환
 *        // -> 데이터가 없으면 거짓(false)을 반환
 *        
 *        // next( ) 함수를 호출해야 메모리에 보관되어 있는
 *        // 데이터를 가져올 수 있습니다!!
 *        String value = 참조_변수이름.next( );
 *        
 *        System.out.println("메모리에서 가져온 문자열은 "+value);
 *     
 *     }
 */
// 반복자를 사용하기 위해서 java.util 패키지에 있는 Iterator 인터페이스를
// 수입하기
import java.util.Iterator;

public class TestSetClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 사람 이름(문자열)을 여러 개 보관할 수 있는 
		 * HashSet 객체를 메모리에 생성하기
		 */
		HashSet<String> refHashSet = new HashSet<String>();
		
		/*
		 * 사용자로부터 메모리에 저장할 사람 이름을 입력 받도록
		 * 하겠습니다!!
		 * 1) java.util.Scanner 클래스를 사용해서 스캐너 객체를 생성
		 */
		java.util.Scanner inputScanner = 
						new java.util.Scanner(System.in);
		
		/*
		 * 사용자가 입력한 사람 이름을 임시로 보관할 변수 선언
		 */
		String tempName = "";
		
		/*
		 * 무한 반복문 구조를 만들어서 사용자로부터 사람 이름을
		 * 입력 받고 HashSet 메모리에 사람 이름을 저장하고
		 * 정상적으로 저장되었는지 여부를 검사합니다!!
		 * 계속해서 사람 이름을 사용자로부터 입력받을지 여부를
		 * 다시 사용자에게 물어봅니다!! -> 사용자로부터 y 또는 n
		 * 문자를 입력 받습니다!! -> 사용자가 n을 입력하면 사람
		 * 이름 입력을 중단합니다!!
		 */
		char y_n = ' ';
		
		while(true) {
			
			System.out.print("사람 이름을 입력하고 엔터를 누르세요: ");
			tempName = inputScanner.nextLine();
			
			boolean chk = refHashSet.add(tempName);
			if(chk == true) {
				System.out.println("사용자가 입력한 사람 이름이 ");
				System.out.println("HashSet 메모리에 저장됨!!");
			} else {
				System.out.println("이전에 입력한 사람 이름이므로 ");
				System.out.println("HashSet 메모리에 저장되지 않음!");
			}
			
			System.out.print("계속하려면 y를 입력하시고, ");
			System.out.print("중단하시려면 n을 입력하세요: ");
			
			tempName = inputScanner.nextLine();
			y_n = tempName.charAt(0);
			if(y_n == 'y' || y_n == 'Y') {
				System.out.println("계속해서 다른 사람의 이름을 ");
				System.out.println("입력 받기 위해서 위로 이동");
				continue;
			} else if(y_n == 'n' || y_n == 'N') {
				System.out.println("사람 이름 입력을 중단합니다!!");
				break;
			} else {
				// 사용자가 다른 문자를 입력한 경우
				while(true) {
					System.out.print("무조건 y 또는 n을 입력하세요: ");
					tempName = inputScanner.nextLine();
					y_n = tempName.charAt(0);
					if(y_n == 'y' || y_n == 'Y' || y_n == 'n' || 
					   y_n == 'N') {
						System.out.println("입력 정상!!");
						break;
					}
				} // end of while(true)
				if(y_n == 'y' || y_n == 'Y') continue;
				if(y_n == 'n' || y_n == 'N') break;
			}
		}		
	}
}






