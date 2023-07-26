package day20180718;

/*
 * AWT 그래픽 라이브러리를 사용해서 화면에 하나의 윈도우(창)를
 * 만듭니다!!
 * 
 * 윈도우를 만들기 위해서 java.awt 패키지에 있는 Frame 클래스를 사용
 */
import java.awt.Frame;
/*
 * 윈도우를 화면에서 사라지게 하는 기능을 갖고 있지 못합니다!!
 * 
 * 이벤트(Event) : 외부 장치로부터 프로그램 안으로 들어오는 입력
 * 
 * 개발자가 윈도우를 화면에서 사라지게 하는 명령어를 작성
 * -> 이벤트 처리 방법
 * 
 * 1) 이벤트의 종류를 파악 : 
 *      윈도우 종료 이벤트 : WindowEvent : java.awt.event 패키지에 존재
 *      -> java.awt 패키지의 자식 패키지 : 
 *           import java.awt.event.WindowEvent; 
 * 
 * 2) 이벤트가 발생할 곳이 어딘지를 파악 : 윈도우 -> Frame 클래스
 * 
 * 3) 윈도우 종료 이벤트가 발생했을 때 처리할 명령어를 작성하기
 *    -> 이벤트 처리기(Event Handler)
 *       -> windowClosing( ) 메서드 안에 작성
 *          -> 이 메서드의 위치는 WindowListener 인터페이스 안에
 *             있거나 WindowAdapter 클래스 안에 있습니다!!
 *             
 * 4) 이벤트가 발생할 곳(윈도우)과 이벤트 처리기인 windowClosing( )
 *    함수를 연결해 주어야 합니다!!
 *    -> 자바 가상 머신이 처리할 명령어의 위치를 알려주는 역할
 *    -> addWindowListener( ) 함수를 호출             
 */
import java.awt.event.*;
/*
 * 학생 성적을 입력 받을 곳은 TextField 클래스를 사용하기
 * -> 사용자로부터 값을 받아올 때 사용하는 컨트롤 또는 컴포넌트
 */
import java.awt.TextField;
/*
 * 화면에 메시지를 출력하고 싶은 경우에는 Label 클래스를 사용하기
 */
import java.awt.Label;
/*
 * 실행 결과 화면의 예)
 * 1. 윈도우를 화면에 출력
 * 2. 윈도우 안에 첫 번째 줄에는 타이틀을 출력 : Label 클래스를 사용
 * 3. 윈도우 안에 두 번째 줄에는 사용자로부터 학생 이름, 국어, 영어,
 *    수학 점수들을 입력받을 수 있는 입력 창을 출력
 *    TextField name = new TextField("홍길동", 20);
 *    20 : 화면에 보여주는 글자 갯수
 *    TextField kor = new TextField("100", 20);
 *    TextField eng = new TextField("100", 20);
 *    TextField math = new TextField("100, 20");
 *    바로 아래에는 저장 버튼 만들기
 *    Button  save = Button("학생 성적 저장하기");
 */
import java.awt.*;

// ArrayList 컬렉션 클래스를 사용하기 위한 import 명령어를 작성
import java.util.ArrayList;

// 새로운 클래스를 만듭니다!! : 역할 : 새로운 학생의 번호를
// 관리해 주는 클래스 : 번호를 생성하고 읽어오는 기능을 갖는 클래스
class MyStudentNoClass {
	// 1. 클래스 변수를 선언 : 모든 객체들이 함께 사용할 수 있는 변수
	private static int s_no = 1;

	// 2. 무조건 새로운 학생의 번호를 만들어주는 기능을 수행하는
	// 함수 만들기
	public static void createNo() {
		System.out.println("새로운 학생의 번호를 만듭니다!!");
		++s_no;
		System.out.println("번호 생성 완료!!");
	}

	// 3. 무조건 현재 마지막 학생의 번호를 읽어오는 기능을 갖는 함수
	public static int readNo() {
		System.out.println("현재 학생의 번호를 읽어옵니다!!");
		return s_no;
	}
}

/*
 * 학생의 이름, 국어 점수, 영어, 수학, 총점, 평균을 관리할 클래스를 새로 만들겠습니다!! -> 순서는 상관없습니다!! -> 중요 :
 * 자료형(문자 또는 숫자), 자릿수 -> 이름 : 문자열 : String m_name; // m_ : 접두사 : member(구성원) 국어
 * 점수 : 정수 : int kor; 영어 점수 : 정수 : int eng; 수학 점수 : 정수 : int math; 총점 : 정수 : int
 * total; 평균 : 실수 : double avg; 학생 번호 보관 변수 : 정수 : int no;
 */
class MyStudentInfoClass {

	private int m_no;
	private String m_name;
	private int m_kor;
	private int m_eng;
	private int m_math;
	private int m_total;
	private double m_avg;

	{
		// 초기화 블럭(Initialization Block)
		// -> 생성자 함수 보다 먼저 실행되는 블럭
		// -> 생성자 함수가 여러 개인 경우에 생성자 함수마다 같은
		// -> 명령어가 있는 경우에 분리할 수 있는 방법

		System.out.println("***초기화 블럭을 실행합니다!!***");

// 학생 번호를 만들어서 m_no 멤버 변수에 저장합니다!!
		System.out.println("학생 번호를 만든 다음에 m_no 멤버 변수에 저장!!");

// 학생 번호는 MyStudentNoClass 클래스에서 따로 관리
// -> 클래스가 갖고 있는 s_no 변수가 갖고 있는 학생 번호를 가져와서
// -> 멤버 변수인 m_no에 저장하기 : 현재 1
// -> readNo( ) 함수를 호출합니다!!
		int currentNo = MyStudentNoClass.readNo();
		m_no = currentNo;
// 두 번째, 세 번째 ... 학생의 번호를 만들기 위해서는 createNo( )
// 함수를 먼저 호출해 주어야 합니다!!
		MyStudentNoClass.createNo();
		System.out.println("학생 번호 저장 완료!!");
	}

	// 기본 생성자
	public MyStudentInfoClass() {

	}

	// 다른 클래스로부터 학생 정보를 받아오는 생성자 함수를 만들기
	public MyStudentInfoClass(String nameValue, int korValue, int engValue, int mathValue) {
		System.out.println("학생 이름을 멤버 변수에 저장합니다");
		m_name = nameValue;
		System.out.println("국어 점수를 멤버 변수에 저장합니다");
		m_kor = korValue;
		System.out.println("영어 점수를 멤버 변수에 저장");
		m_eng = engValue;
		System.out.println("수학 점수를 멤버 변수에 저장");
		m_math = mathValue;
	}

	/*
	 * MyStudentInfoClass 클래스 안으로 오세요!!
	 */
	// 위에서 선언한 멤버 변수 별로 2개의 함수를 각각 만들기
	// 1) 멤버 변수가 갖고 있는 값을 읽어오는 함수 : getter 함수
	// -> public 자료형 get + 멤버변수이름( ) { return 멤버변수이름; }
	// 2) 멤버 변수가 갖고 있는 값을 바꾸어주는 함수 : setter 함수
	// -> public void set + 멤버변수이름(매개변수선언) {
	// 멤버변수이름 = 매개변수이름;
	// }

	// 학생 번호를 읽어오는 함수
	public int getNo() {
		System.out.println("현재 학생 번호를 읽어옵니다!!");
		return m_no;
	}

	// 현재 학생 번호를 바꾸어주는 함수
	public void setNo(int value) {
		System.out.println("현재 학생 번호를 바꿉니다!!");
		m_no = value;
	}

	// 학생 이름을 읽어오는 함수
	public String getName() {
		System.out.println("학생 이름을 읽어옵니다!!");
		return m_name;
	}

	// 학생 이름을 바꾸어주는 함수
	public void setName(String value) {
		System.out.println("학생 이름을 바꿉니다!!");
		m_name = value;
	}

	// 멤버 변수인 m_kor 관련 setter / getter 함수 만들기
	public int getKor() {
		System.out.println("국어 점수를 읽기!!");
		return m_kor;
	}

	public void setKor(int value) {
		System.out.println("국어 점수를 바꾸기!!");
		m_kor = value;
	}

	// 멤버 변수인 m_eng 관련 setter / getter 함수 만들기
	public int getEng() {
		System.out.println("영어 점수를 읽기!!");
		return m_eng;
	}

	public void setEng(int value) {
		System.out.println("영어 점수를 바꾸기!!");
		m_eng = value;
	}

	// 멤버 변수인 m_math 관련 setter / getter 함수 만들기
	public int getMath() {
		System.out.println("수학 점수를 읽기!!");
		return m_math;
	}

	public void setMath(int value) {
		System.out.println("수학 점수를 바꾸기!!");
		m_math = value;
	}

	// 멤버 변수인 m_total 관련 setter / getter 함수 만들기
	public int getTotal() {
		System.out.println("총점 점수를 읽기!!");
		return m_total;
	}

	public void setTotal() {
		System.out.println("총점 점수를 계산하기");
		m_total = getKor() + getEng() + getMath();
	}

	// 멤버 변수인 m_avg 관련 setter / getter 함수 만들기
	public double getAvg() {
		System.out.println("평균 점수를 읽기!!");
		return m_avg;
	}

	public void setAvg() {
		System.out.println("평균 점수를 계산하기");
		m_avg = getTotal() / 3.0;
	}
}

// Frame 클래스를 상속 받는 자식 윈도우 클래스를 만듭니다!!
// 윈도우 종료 이벤트 처리를 하기 위해서 WindowListener 인터페이스를 구현
class MyFrameWindow extends Frame implements WindowListener, ActionListener {

	// 사용자가 입력한 학생들의 이름과 국어,영어,수학 점수들을 보관할
	// 변수를 선언
	ArrayList<MyStudentInfoClass> refStudentInfoList = new ArrayList<MyStudentInfoClass>();

	// 프로그램의 타이틀을 화면에 보여줄 때 사용할 Label 변수를 생성
	Label titleLabel = new Label("***학생 성적 입력(컬렉션 사용)***", Label.CENTER);
	// Label.LEFT / Label.RIGHT / Label.CENTER
	// -> 위에서 만든 라벨을 윈도우 안에 위쪽 방향에 출력하기
	// -> 위쪽 방향 이름은 "North"
	// -> add( titleLabel, "North");

	// 라벨 또는 다른 컴포넌트들(버튼 또는 텍스트 필드 등)의
	// 글자 크기를 바꾸기 위해서 Font 클래스를 사용한 객체를 생성
	Font font = new Font("궁서체", Font.BOLD, 20);

	/*
	 * 1. Panel 클래스를 사용해서 컨테이너 객체를 생성하기 용도 : 5개의 컨트롤들을 화면에 출력하기 위함
	 */
	GridLayout gridLayout = new GridLayout(5, 1);
	/*
	 * Panel panel = new Panel(new GridLayout(5, 1));
	 * 
	 */
	Panel panel = new Panel(gridLayout);

	// 학생 이름 입력 창 만들기
	TextField nameTF = new TextField("홍길동", 10);
	// 국어 점수 입력 창 만들기
	TextField korTF = new TextField("70", 10);
	// 영어 점수 입력 창 만들기
	TextField engTF = new TextField("80", 10);
	// 수학 점수 입력 창 만들기
	TextField mathTF = new TextField("90", 10);
	// 저장 버튼 만들기
	Button saveBtn = new Button("학생 정보 저장하기");

	// 생성자 만들기
	public MyFrameWindow(String title) {
		// String title: 윈도우 좌측 상단에 출력할 문자열
		// super : 부모 클래스의 주소를 보관하는 자동 변수 이름
		// super( ); -> 부모 클래스에 있는 생성자 함수를 호출
		super(title);

		// 라벨 컨트롤에 보여질 텍스트의 글꼴을 궁서체로 바꾸고
		// 진하게 그리고 글자 크기는 20으로 바꾸기
		titleLabel.setFont(font);

		// 학생 이름을 입력 받을 입력 창에도 글자 속성을 바꾸기
		nameTF.setFont(font);

		// 국어 점수를 입력 받을 입력 창에도 글자 속성을 바꾸기
		korTF.setFont(font);

		// 영어 점수를 입력 받을 입력 창에도 글자 속성을 바꾸기
		engTF.setFont(font);

		// 수학 점수 입력창의 글자 속성 바꾸기
		mathTF.setFont(font);

		// 학생 성적 정보 저장 버튼의 글자 속성 바꾸기
		saveBtn.setFont(font);

		/*
		 * saveBtn 참조 변수와 actionPerformed( ) 함수를 연결하기 -> addActionListener( ) 함수를 호출
		 */
		saveBtn.addActionListener(this);
		/*
		 * this : 사용자가 버튼을 클릭했을 때 ActionEvent 객체의 주소를 저장
		 */

		/*
		 * 사용자가 텍스트 필드 입력 창에서 엔터 키를 누른 경우에 발생하는 ActionEvent 이벤트를 잡기 위한 명령어를 작성 -> 텍스트 필드
		 * 입력 창과 actionPerformed() 함수를 연결
		 */
		// 사용자가 학생 이름을 입력하고 엔터키를 누른 경우에는
		// 자동으로 actionPerformed() 함수 안으로 이동하게 하기
		nameTF.addActionListener(this);

		korTF.addActionListener(this);
		engTF.addActionListener(this);
		mathTF.addActionListener(this);

		// 판넬 컨테이너에 5개의 컨트롤들을 넣기
		panel.add(nameTF);
		panel.add(korTF);
		panel.add(engTF);
		panel.add(mathTF);
		panel.add(saveBtn);

		// 판넬 컨테이너는 보더 레이아웃 배치 관리자가 갖고 있는
		// 가운데 방향에 넣기
		add(panel, BorderLayout.CENTER);
		// BorderLayout.CENTER : "Center" 문자열 상수 이름

		// 위에서 만든 라벨을 윈도우 안에 위쪽 방향에 넣기
		add(this.titleLabel, "North");

		// 윈도우 종료 이벤트가 발생했을 때 windowClosing( )
		// 함수 안에 있는 명령어가 실행될 수 있도록 해주는 명령어를 작성
		addWindowListener(this);
		// this : 현재 클래스의 주소를 보관하고 있는 내부 변수

		// setSize( ) 함수를 호출해서 화면에 보여줄 윈도우의 가로크기와
		// 세로크기를 정해주기
		// setSize(500, 500);
		pack();

		// 윈도우를 화면에 출력하기
		setVisible(true);
	}

	/*
	 * 새로 추가한 ActionListener 인터페이스에서 갖고 있었던 actionPerformed( ) 추상 메서드를 구현하기
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		Object refEventSource = actionEvent.getSource();

		if (refEventSource instanceof Button) {
			System.out.println("버튼에서 ActionEvent 이벤트가 발생");
// 사용자가 버튼을 클릭하는 경우에만 발생

// refEventSource 참조 변수의 클래스 타입이 Object
// -> Button 클래스가 갖고 있는 함수들을 접근 -> 형 변환을 하셔야 합니다!!
			Button refClickedButton = (Button) refEventSource;
// -> Button 클래스가 갖고 있는 모든 함수들을 호출하기 위함

// 여러 개의 버튼 객체 중에서 특정 버튼 객체인지를 검사하기
// == 동등 비교 연산자 : 10 == 20, 참조변수이름1 == 참조변수이름2(주소)
			if (refClickedButton == saveBtn) {
				System.out.println("사용자가 저장 버튼을 클릭했습니다!!");

				/*
				 * 사용자가 입력한 학생 이름과 국어, 영어, 수학 점수들을 가져오기 모든 사용자가 입력한 값들은 TextField 입력 창에서 가져와야
				 * 합니다!! getText( ) 함수를 호출하면 사용자가 입력한 모든 값들을 가져옵니다. -> 무조건 String(문자열)로 값을 반환
				 */
				// 사용자가 입력할 학생 이름을 가져와서 임시 변수에 저장하기
				String tempNameValue = nameTF.getText().trim();
				/*
				 * trim( ) 함수를 호출하면 사용자가 입력한 이름의 왼쪽과 오른쪽에 있는 공백 문자(사용자가 스페이스를 누른 경우 값) 를 모두
				 * 지워줍니다!! 예) "홍길동" 예) "   홍길동    " & "홍길동(찾고 싶은 학생 이름)"
				 */
				// 사용자가 입력한 국어 점수를 가져와서 문자열 형태로 임시 변수에
				// 저장하기
				String tempKorValue = korTF.getText().trim();
				// 사용자가 입력한 영어 점수를 가져와서 문자열 형태로 임시 변수에 저장
				String tempEngValue = engTF.getText().trim();
				// 사용자가 입력한 수학 점수를 가져와서 임시 변수에 저장하기
				String tempMathValue = mathTF.getText().trim();

				// 사용자가 입력을 하지 않은 경우도 검사해야 합니다!!
				// -> ""(빈 문자열 : empty string)
				if (tempNameValue.equals("")) {
					System.out.println("사용자가 학생 이름을 입력하지 않음!!");
					// 프로그램에서 강제로 사용자가 학생 이름을 입력할 수 있도록
					// 커서를 nameTF 텍스트 필드 창으로 이동시키기
					nameTF.setFocusable(true); // 마우스 커서를 받을 준비하기
					nameTF.requestFocus(); // 마우스 커서의 위치를 이동시키기
					return; // 현재 actionPerformed( ) 함수를 탈출하기
				}

				// 사용자가 국어 점수를 입력하지 않은 경우에도 마우스 커서를 강제로
				// 국어 점수 입력 창으로 이동시키기
				if (tempKorValue.equals("")) {
					System.out.println("사용자가 국어 점수를 입력하지 않음!!");
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				}

				// 영어 점수 입력 유무를 검사해서 입력하지 않은 경우에는 강제로
				// 영어 점수를 입력할 수 있는 입력 창으로 이동시키기
				if (tempEngValue.equals("")) {
					System.out.println("사용자가 영어 점수를 입력하지 않음");
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}

				// 수학 점수 입력 유무 검사 + 강제로 마우스 커서 위치 이동
				if (tempMathValue.equals("")) {
					System.out.println("사용자가 수학 점수를 입력하지 않음");
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}

				// 사용자가 입력한 학생 이름과 국어, 영어, 수학 점수들을 콘솔
				// 화면에 출력하기
				System.out.println("학생 이름은 " + tempNameValue);
				System.out.println("국어 점수는 " + tempKorValue);
				System.out.println("영어 점수는 " + tempEngValue);
				System.out.println("수학 점수는 " + tempMathValue);

				// 사용자가 입력한 학생 이름, 국어, 영어, 수학 점수를 갖는
				// 객체를 메모리에 생성하고 임시 변수에 저장하기
				// -> 배열 리스트 변수에 저장할 객체를 만들기
				MyStudentInfoClass refNewStudentInfo = new MyStudentInfoClass(tempNameValue,
						Integer.parseInt(tempKorValue), Integer.parseInt(tempEngValue),
						Integer.parseInt(tempMathValue));

				// 총점을 계산해서 m_total 변수에 저장하기
				// -> setTotal( ) 함수를 호출
				refNewStudentInfo.setTotal();

				// 평균을 계산하기 위해서 setAvg() 함수를 호출하기
				refNewStudentInfo.setAvg();

				// 콘솔 화면에 총점과 평균을 출력하기
				System.out.println("총점은 " + refNewStudentInfo.getTotal());
				System.out.printf("평균은 %.2f\n", refNewStudentInfo.getAvg());

				// 새로 만든 학생 객체를 배열 리스트 변수에 넣기 : add( ) 함수를 호출
				refStudentInfoList.add(refNewStudentInfo);

				System.out.println("배열 리스트 변수에 새로운 학생 정보를 저장");

				// 배열 리스트 변수가 갖고 있는 size( ) 함수를 호출해서
				// 현재까지 배열 리스트 변수에 저장된 학생 수를 콘솔 화면에 출력
				System.out.println("배열 리스트 변수에 저장된 학생 수는 " + refStudentInfoList.size() + " 명 입니다!!");

			}

		} else if (refEventSource instanceof TextField) {
			System.out.println("TextField에서 ActionEvent 이벤트가 발생");
// TextField 입력 창에서 무언가를 입력하시고 엔터 키를 누르는 경우 	

			TextField refTextField = (TextField) refEventSource;

// 사용자가 엔터키를 누른 입력 창이 학생 이름 입력 창인지를 판단하기
			if (refTextField == nameTF) {
				System.out.println("사용자가 학생 이름을 입력하고 엔터를 누름!!");

				// 사용자가 입력한 학생 이름을 검사하기
				String value = nameTF.getText().trim();
				if (value.equals("")) {
					System.out.println("사용자가 학생 이름을 입력하지 않음");
					nameTF.setFocusable(true);
					nameTF.requestFocus();
					return;
				} // end of if(value.equals(""))
				else {
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				}
			} // end of if(refTextField == nameTF) {
			else if (refTextField == korTF) {
				System.out.println("사용자가 국어 점수를 입력하고 엔터를 누름!!");
				String value = korTF.getText().trim();
				if (value.equals("")) {
					System.out.println("국어 점수를 입력하지 않음");
					System.out.println("강제로 마우스 커서를 국어 입력 창으로 이동");
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				} // end of if(value.equals("")) {
				else {
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}
			} // end of else if(refTextField == korTF) {
			else if (refTextField == engTF) {
				System.out.println("영어 점수를 입력하고 엔터를 누름!!");
				String value = engTF.getText().trim();
				if (value.equals("")) {
					System.out.println("영어 점수를 입력하지 않음");
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}
				else {
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}
			} else if (refTextField == mathTF) {
				System.out.println("수학 점수 입력 창에서 엔터를 누름!!");
				String value = mathTF.getText().trim();
				if (value.length() == 0) {
					// length() 함수는 사용자가 입력한 수학 점수의 숫자 갯수를 반환
					System.out.println("수학 점수를 입력하지 않음");
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}
			} else {
				System.out.println("모르는 입력 창입니다!!");
			}

		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("사용자가 윈도우 종료 버튼을 클릭!!");
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	// 인터페이스 안에는 추상 메서드를 갖고 있습니다!!
	// WindowListener 인터페이스 안에는 7개의 추상 메서드를 갖고 있습니다.
	// 상속 받는 자식 클래스에서는 추상 메서드를 구현해야 합니다!!

}

public class TestAWTStudentScoreCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

// 위에서 만든 MyFrameWindow 클래스를 메모리에 생성하기
		MyFrameWindow refMyWindow = new MyFrameWindow("MY FIRST WINDOW");

	}

}
