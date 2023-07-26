package day20180724;

/*
 * 나만의 큐 자료 구조를 클래스로 만들기
 */
class MyQueueClass {
	// 1. 정적 배열을 메모리에 생성합니다!!
	// 1) 배열의 크기를 상수로 정의해서 나중에 바꿀 때 정의 위치에서만
	// 수정할 수 있도록 합니다.
	public static final int ARRAY_SIZE = 10;
	
	// 2) 최대 10개의 정수를 보관할 수 있는 배열을 메모리에 생성합니다!!
	private int iarray [ ] = new int[ARRAY_SIZE];
	
	// 3) 머리 변수를 선언하고 -1로 초기화 합니다!!
	// -> iarray 배열에 저장되어 있는 정수를 꺼내올 때만 사용할 변수
	private int front = -1;
	
	// 4) 꼬리 변수를 선언하고 -1로 초기화 합니다!!
	// -> 배열 iarray에 새로운 정수 데이터를 저장할 때만 사용할 변수
	// -> 새로운 정수 데이터의 배열의 위치(방 번호)를 가집니다!!
	private int rear = -1;
	
	/*
	 * 5) 새로운 정수 데이터를 iarray 배열에 저장할 때 발생할 수 있는
	 * 예외 상황을 고민해야 합니다!!
	 * -> 배열의 크기(배열에 저장할 수 있는 데이터의 최대 갯수 - 1)
	 *    -> 현재 rear(꼬리 변수)의 값이 
	 *       배열에 저장할 수 있는 데이터의 최대 갯수 - 1와 같은 경우
	 *       배열에 새로운 데이터를 저장할 수 없는 상태 
	 *       
	 * -> if ( rear == (배열의 크기 - 1) ) {
	 *       // Queue Overflow 상태
	 *    }
	 *    else {
	 *        // 먼저 꼬리 변수를 하나 증가합니다!!
	 *        rear++;
	 *        // 하나 증가한 꼬리 변수 위치에 새로운 데이터를 저장하기
	 *        iarray[rear] = 새로운데이터;
	 *    }
	 */
	// 큐 오버플로우 상태를 검사해주는 함수를 새로 만듭니다!!
	/*
	 * 1) 함수 이름 : isQueueOverflow( )
	 * 2) 반환형 : 논리 값 : boolean
	 * 3) 알고리즘 : 현재 꼬리 변수인 rear를 검사합니다!!
	 *    변수 rear 값이 배열의 크기 - 1 과 같으면 참(true)을 반환하고
	 *    그외의 경우에는 거짓(false)을 반환합니다!!
	 */
	public boolean isQueueOverflow() {
		// 참(true) 또는 거짓(false)을 보관할 변수 선언
		boolean result;		
		if(rear == (ARRAY_SIZE - 1)) {
			result = true;
		} else {
			result = false;
		}		
		return result;
	}
	/*
	 * 3항 연산자를 사용하시면 명령어를 더 간략하게 작성할 수 있습니다.
	 * return rear == (ARRAY_SIZE - 1) ? true : false;
	 */
	
	/*
	 * 현재 큐가 비어있는지를 판단해주는 함수를 새로 만듭니다!!
	 * 1) 함수 이름 : isQueueEmpty( ) 또는 isQueueUnderflow( )
	 * 2) 반환형 : 논리 값 : boolean
	 * 3) 알고리즘 : 
	 *     머리 변수와 꼬리 변수가 같은 값을 갖고 있는 상태는
	 *     배열 iarray에서 꺼내올 데이터가 없는 상태!!
	 *     참(true)을 반환
	 *     배열 iarray에서 꺼내올 데이터가 있는 상태는
	 *     머리 변수와 꼬리 변수가 다른 값을 갖고 있는 상태
	 *     거짓(false)을 반환 
	 */
	public boolean isQueueEmpty() {
		boolean result;
		if(front == rear) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	public boolean isQueueEmpty2() {
		return front == rear ? true : false;
	}
	
	/*
	 * main( ) 함수로부터 또는 다른 함수로부터 하나의 정수 데이터를
	 * 받아와서 iarray 배열에 저장하는 함수를 만듭니다!!
	 * 1) 함수 이름 : insertQueue( )
	 * 2) 매개 변수 : 하나의 정수를 보관할 변수 : int data
	 * 3) 반환형 : 없음 : void
	 * 4) 알고리즘 : 
	 *     현재 Queue 메모리에 데이터를 저장할 수 있는지 여부를
	 *     isQueueOverflow( ) 함수를 호출해서 검사합니다!!
	 *     -> true인 경우에는 화면에 데이터를 저장할 수 없습니다 라는
	 *         메시지를 출력하고 함수를 종료
	 *     -> false인 경우에는 꼬리 변수를 하나 증가하고
	 *        꼬리 변수 위치에 새로운 데이터를 저장합니다!! 
	 */
	public void insertQueue(int data) {
		System.out.println("insertQueue(int) 함수가 호출되었습니다");
		boolean result = isQueueOverflow();
		if(result == true) {
			System.out.println("현재 큐 메모리 공간은 Overflow");
			System.out.println("새로운 데이터를 저장할 수 없습니다");
			return;
		} else {
			System.out.println("현재 큐 메모리 공간에 데이터를 ");
			System.out.println("저장할 수 있는 상태입니다!!");
			iarray[++rear] = data;
			System.out.println("배열 iarray에 새로운 데이터를 저장");
			System.out.println("현재 꼬리 변수 rear의 값은 "+rear);
		}
	}
}


public class TestMyQueueClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
