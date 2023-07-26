package day20180716;
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

// Frame 클래스를 상속 받는 자식 윈도우 클래스를 만듭니다!!
// 윈도우 종료 이벤트 처리를 하기 위해서 WindowListener 인터페이스를 구현
class MyFrameWindow extends Frame implements WindowListener {

	// 프로그램의 타이틀을 화면에 보여줄 때 사용할 Label 변수를 생성
	Label titleLabel = new Label("***학생 성적 입력(컬렉션 사용)***"); 
	// -> 위에서 만든 라벨을 윈도우 안에 위쪽 방향에 출력하기
	// -> 위쪽 방향 이름은 "North"
	// -> add( titleLabel, "North");
	
	// 라벨 또는 다른 컴포넌트들(버튼 또는 텍스트 필드 등)의
	// 글자 크기를 바꾸기 위해서 Font 클래스를 사용한 객체를 생성
	Font  font = new Font("궁서체", Font.BOLD, 20);
		
	// 생성자 만들기
	public MyFrameWindow(String title) {
		// String title: 윈도우 좌측 상단에 출력할 문자열
		// super : 부모 클래스의 주소를 보관하는 자동 변수 이름
		// super( ); -> 부모 클래스에 있는 생성자 함수를 호출
		super(title);
		
		// 라벨 컨트롤에 보여질 텍스트의 글꼴을 궁서체로 바꾸고
		// 진하게 그리고 글자 크기는 20으로 바꾸기
		titleLabel.setFont(font);		
		
		// 위에서 만든 라벨을 윈도우 안에 위쪽 방향에 넣기
		add(this.titleLabel, "North");
		
		// 윈도우 종료 이벤트가 발생했을 때 windowClosing( )
		// 함수 안에 있는 명령어가 실행될 수 있도록 해주는 명령어를 작성
		addWindowListener(this);
		// this : 현재 클래스의 주소를 보관하고 있는 내부 변수
		
		// setSize( ) 함수를 호출해서 화면에 보여줄 윈도우의 가로크기와
		// 세로크기를 정해주기
		setSize(500, 500); 
		
		// 윈도우를 화면에 출력하기
		setVisible(true);		
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
