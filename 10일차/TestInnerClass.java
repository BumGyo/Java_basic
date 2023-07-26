package day20180725;

/*
 * 외부 클래스인 MyOuterClass를 먼저 정의합니다!!
 * 
 * 내부 클래스는 MyInnerClass는 외부 클래스 안에서 정의합니다!!
 */
class MyOuterClass {
	
	// 멤버 변수 a를 선언
	private int a = 100;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	// 외부 클래스의 생성자 함수 만들기
	public MyOuterClass() {
		System.out.println("외부 클래스의 기본 생성자 함수");
	}
	
	// 외부 클래스의 생성자 함수 만들기 : 외부 클래스로부터 하나의
	// 정수를 받습니다!!
	public MyOuterClass(int value) {
		System.out.println("외부 클래스의 매개 변수를 갖는 생성자 함수");		
		a = value;
		System.out.println("멤버 변수인 a의 시작값을 바꿈!!");
		System.out.println("멤버 변수 a의 시작값은 " + a);
	}
	
	// 위에서 만든 멤버 변수 a의 값을 반환하는 함수 만들기
	// -> getter 함수 만들기 : 자동으로 만들기
		
	// 내부 클래스 
	class MyInnerClass {
		
		// 생성자 함수를 만듭니다!!
		// 기본 생성자 함수를 만듭니다!!
		public MyInnerClass() {
			System.out.println("내부 클래스에서 정의한 기본 생성자");
			System.out.println("외부 클래스에서 선언한 멤버 변수 a");
			System.out.println("를 사용 가능!!");
			System.out.println("멤버 변수 a의 값은 " + a);
		}
		
		// 외부 클래스로부터 하나의 정수 값을 받는 생성자 함수를 만들기
		public MyInnerClass(int value) {
			System.out.println("내부 클래스에서 정의한 매개 변수를 ");
			System.out.println("갖는 생성자 함수를 만듭니다!!");
			a = value;
			System.out.println("외부 클래스에서 선언한 멤버 변수 a의 ");
			System.out.println("현재 값을 바꿉니다!!");
		}
	}
}
public class TestInnerClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyOuterClass refOuterClass = new MyOuterClass(200);
		
		MyOuterClass.MyInnerClass refInnerClass = 
				          refOuterClass.new MyInnerClass(); 
		
	}

}
