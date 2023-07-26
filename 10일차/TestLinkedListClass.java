package day20180725;

/*
 * 외부 클래스와 내부 클래스를 만들어서 연결 리스트라고 하는 자료 구조를
 * 표현하기
 */
class MyLinkedListClass {
	
	// 모든 노드 객체 마다 갖는 데이터와 주소를 내부 클래스로 정의
	static class MyNodeClass {
		// 데이터는 정수
		int data;
		// 자기 참조 변수를 선언
		MyNodeClass refNextNode;
	}
	
	// 메모리에 생성되는 첫 번째 노드의 주소만을 보관할 변수
	static MyNodeClass refHeadNode = null;
	
	// 메모리에 생성된 노드의 총 갯수를 보관할 변수
	static int size = 0;
	
	// 사용자로부터 하나의 정수 데이터를 입력받을 때 사용할 스캐너 변수
	static java.util.Scanner scanner = 
			   new java.util.Scanner(System.in); 
	
	/*
	 * 메모리에 새로운 노드 객체를 생성
	 * 사용자로부터 노드 객체에 저장할 정수 데이터를 입력 받기
	 * 아직 연결된 노드 객체가 없으므로 refNextNode 참조 변수에 null 저장
	 * 헤드 노드 변수와 메모리에 생성된 노드 객체를 연결하기
	 */
	public static void createNodeAndLink() {		
		System.out.println("createNodeAndLink() 함수가 호출됨!!");
		
		// 1. 메모리에 새로 만들 노드 객체의 주소를 보관할 변수 선언
		MyNodeClass refNextNode = null;
		
		// 2. 사용자로부터 하나의 정수 입력을 받아서 임시로 보관할
		// 변수 선언
		int tempData = 0;
		
		// 3. new 연산자를 사용해서 메모리에 새로운 노드 객체를 생성
		refNextNode = new MyNodeClass();		
		System.out.println("***메모리에 새로운 노드 객체를 생성***");
		
		// 4. 메모리에 생성된 노드 객체에 저장할 정수 데이터를
		// 사용자로부터 입력 받습니다!!
		System.out.print("데이터를 입력하세요: ");
		tempData = scanner.nextInt();
		// 5. 사용자로부터 입력 받은 정수 데이터를 노드 객체에 저장		
		refNextNode.data = tempData;
		// 6. 다음에 메모리에 새로 만들어지는 노드 객체의 주소를
		// 보관할 참조 변수에 null 값을 저장
		refNextNode.refNextNode = null;
		// 7. 사용자가 입력한 데이터를 화면에 출력
		System.out.println(
				"사용자가 입력한 데이터는 "+refNextNode.data);
		// 8. 객체의 주소(Address)는 hashCode( ) 함수를 호출해서 확인
		System.out.println("새로 만든 노드의 주소는 " + 
		        refNextNode.hashCode());
		
		// 9. 새로 만든 노드와 헤드 노드를 연결해 주어야 합니다!!
		// -> 이유1 : 헤드 노드를 통해서 첫 번째 노드에 접근하기 위함
		// -> 이유2 : 연결 작업을 하지 않으면 첫 번째 노드로 찾아갈 수
		// -> 있는 길(방법)이 없습니다!! -> refNextNode 변수 이름을
		// -> 두 번째 노드, 세 번째 노드 ... 모든 노드에서 함께 사용
		
		// 10. 현재 헤드 노드의 값이 null인지를 검사 : 만약 null이라면
		// -> 메모리에 생성된 노드 객체가 처음 객체
		// -> 만약 null이 아닌 경우에는 첫 번째 노드의 객체 주소를
		// -> 헤드 노드가 갖고 있는 경우가 됩니다!!
		if(refHeadNode == null) {
			System.out.println("현재 헤드 노드와 연결된 노드가 없음");
			System.out.println("방금 만든 노드의 주소를 헤드 노드에 ");
			System.out.println("전달합니다!!");
			refHeadNode = refNextNode;
			System.out.println("헤드 노드와 첫 번째 노드가 연결!!");
		} else {
			System.out.println("헤드 노드가 첫 번째 노드의 주소를 ");
			System.out.println("갖고 있습니다!!");
			// 현재 헤드 노드를 사용해서 찾아갈 수 있는 제일
			// 마지막 노드의 주소를 찾아서 방금 만든 노드 객체의
			// 주소를 저장!!
			System.out.println("마지막 노드 객체의 주소를 찾기!!");
			// 찾을 마지막 노드의 주소를 보관할 변수
			MyNodeClass refLastNode = null;
			// 노드를 찾을 때 사용할 변수가 필요 : 모든 노드의
			// 주소를 임시로 보관할 변수
			MyNodeClass refCurNode = refHeadNode;
			// refCurNode : ref(참조) + Cur(현재) + Node(노드)
			// while 반복문이 시작되기 전에 마지막 노드는
			// 첫 번째 노드라고 가정합니다!!
			refLastNode = refCurNode;
			while(refCurNode != null) {
		System.out.println("노드 탐색 중입니다!!");
		System.out.println("헤드 노드로 찾아갈 수 있는 ");
		System.out.println("가장 마지막 노드를 찾고 있습니다!!");
		System.out.println("지금 찾아간 노드의 주소는 " + 
					refCurNode.hashCode());		
// 다음 노드의 주소로 이동하기 전에 현재 노드의 주소는 마지막 노드의
// 주소를 보관할 변수에 저장 : 이유는 refCurNode 변수는 노드의
// 주소를 바꾸기 때문
		refLastNode = refCurNode;
		refCurNode = refCurNode.refNextNode;		
			}
// while 반복문을 벗어났습니다!! -> refCurNode 변수에 저장된 노드의
// 주소가 null인 경우
System.out.println("마지막 노드의 주소를 찾았습니다!!");
refLastNode.refNextNode = refNextNode;
// 마지막 노드의 연결 노드를 방금 전에 만든 노드의 주소로 지정
		}
		
		
	}
	
}


public class TestLinkedListClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyLinkedListClass.createNodeAndLink();
	
		// 무한 반복문을 만든 다음에 createNodeAndLink()
		// 함수를 계속 실행하게 합니다!!
		// -> 실행 중단은 사용자로부터 숫자 1 또는 2를 입력받아서 처리		
	java.util.Scanner scanner2 = new java.util.Scanner(System.in);		
		// 사용자가 입력한 정수 1 또는 2를 보관할 변수 선언
		int no = 1;		
		while(true) {
			MyLinkedListClass.createNodeAndLink();
			System.out.print("계속하려면 1을 입력하시고, ");
			System.out.print("중단하시려면 2를 입력하세요: ");
			no = scanner2.nextInt();
			if(no == 2) {
				System.out.println("사용자가 2를 입력!!");
				break;
			}
			else if(no == 1) {
				System.out.println("사용자가 1을 입력!!");
				continue;
			}
		}
	}
}


