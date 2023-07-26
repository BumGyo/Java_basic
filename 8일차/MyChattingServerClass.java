package day20180720;

/*
 * 서버 역할을 담당할 클래스를 만듭니다!!
 * 
 * 1. 화면 만들기
 * 
 * 1) 첫 번째 줄에는 타이틀 출력하기 : ***채팅 서버***
 * 2) 클라이언트로부터 입력 받은 메시지들을 화면에 출력해주는
 *    TextArea 출력 창 만들기
 * * TextField : 한 줄만 입력 + 출력
 * * TextArea : 여러 줄 입력 + 출력 
 */
/*
 * 소켓(Socket)을 사용해서 클라이언트와 서버를 판단하기
 * 
 * 1. 서버를 판단할 때는 ServerSocket 클래스를 사용
 * 2. 클라이언트를 판단할 때는 Socket 클래스를 사용
 * 
 * 3. 먼저 ServerSocket 클래스를 사용해서 서버 프로그램을 먼저 구동하기
 * -> java.net 패키지에 있는 클래스이므로 사용하시려면
 *    import java.net.ServerSocket; 명령문을 작성
 * 
 * -> 서버 소켓 객체를 메모리에 생성하기 :
 *    ServerSocket 메모리_참조변수이름 = 
 *         new ServerSocket(포트번호를 정수로 작성);
 * 
 * -> 포트 번호 : 메모리에서 실행되고 있는 프로세스를 구별해주는 번호
 *    -> 0 ~ 65535 사이에 있는 정수만을 사용
 *    -> 0 ~ 65535 사이에 있는 정수를 
 *       다른 프로그램에서 사용중인 경우에는 사용할 수 없습니다!!
 *       -> ServerSocket refServerSocket = 
 *               new ServerSocket(5000);
 *       -> 만약에 다른 프로그램에서 5000 포트를 사용하고 있는 경우에는
 *          예외상황이 발생 : Address Already In Use ...
 *          프로그램이 중단됩니다!!
 *          
 *       -> 1. 명령 창을 실행하신 다음에 사용하고 있지 않는 
 *             포트번호를 알아오기 : 윈도우 로고 키 + R 키를 누르시면
 *             실행 창이 출력됩니다!! 입력 창에 cmd 명령어를 입력하고
 *             실행합니다!!
 *             -> netstat -an : net + stat (현재 네트워크 상태를
 *                화면에 출력)
 *                
 *       -> 2. ServerSocket 참조변수이름 = new ServerSocket( 0 );
 *          -> ServerSocket 생성자 함수에게 정수 0을 전달하면
 *             생성자 함수 안에서 현재 컴퓨터의 네트워크에서
 *             사용하고 있지 않은 포트 번호를 하나를 가져와서
 *             반환
 *          -> getLocalPort( ) 함수를 사용하면 포트 번호를
 *             가져올 수 있습니다!!
 */
/*
 * 1. 먼저 윈도우를 하나 만든 다음에 화면에 윈도우를 출력하겠습니다!! 
 */
import java.awt.*;
import java.awt.event.*;
// 네트워크 관련 클래스 또는 인터페이스를 사용하고자 하는 경우에는 java.net 패키지를 임포트
import java.net.*;
// 네트워크에서 서버와 클라이언트 간에 데이터를 주고 받는 경우에
// 발생할 수 있는 예외상황 처리 클래스를 갖고 있는 java.io 패키지를 임포트
// java.io : 입력과 출력 클래스와 인터페이스를 갖고 있는 패키지
import java.io.*;
/*
 * 친구 컴퓨터 주소를 잘못 입력한 경우에는 UnknownHostException
 * 예외상황이 발생
 * 
 */
// 화면에 하나의 윈도우를 보여줄 프레임 클래스를 상속 받는 클래스 만들기
class MyChattingServerFrameClass 
         extends Frame 
           implements WindowListener, ActionListener, FocusListener {

	// 0. 서버 소켓 클래스를 사용해서 클라이언트 컴퓨터에서
	// 서버 프로그램을 인식할 수 있는 포트 번호를 생성해주는 객체를 선언
	private ServerSocket refServerSocket = null;
	
	// 00. ServerSocket 클래스에서 동적으로 반환해주는 포트 번호를
	// 보관할 변수를 선언
	private int portNo;
	
	// 000. 클라이언트와 접속(연결)이 된 경우에 클라이언트의 소켓
	// 객체의 주소를 보관할 수 있는 변수를 선언
	private Socket refClientSocket = null;
	
	// 1. 타이틀을 화면에 보여줄 때 사용할 라벨 객체를 만들기
	private Label titleLabel = 
			  new Label("***Chatting Server Window***",Label.CENTER);
	
	// 2. 클라이언트에서 보낸 메시지를 화면에 보여줄 때 사용할
	// TextArea 객체를 만들기
	private TextArea msgsTextArea = new TextArea(10, 50);
	// 10 : 가로 줄 수 (행의 갯수)
	// 50 : 세로 칸 수 (열의 갯수)
	
	// 3. 라벨 객체와 텍스트 아레아 객체에 보여줄 텍스트의 글자 모양을
	// 바꾸기 위해서 사용할 Font 객체를 만들기
	private Font font = new Font("궁서체", 
			              Font.BOLD + Font.ITALIC, 20);
	
	// 4. 생성자 함수를 만들기
	public MyChattingServerFrameClass(String title) {
		super(title);
				
		/*
		 * 클라이언트에서 서버에 연결을 시도할 때 사용할 객체를 만들기 
		 */
		try {			
			System.out.println("서버 소켓 객체를 메모리에 생성하기");
			
			refServerSocket = new ServerSocket(0);
			
			System.out.println("위쪽에서 서버 소켓 객체가 ");
			System.out.println("정상적으로 만들어졌습니다!!");
			
			// ServerSocket 클래스가 갖고 있는 getLocalPort()
			// 함수를 호출해서 운영체제로부터 받아온 포트 번호를
			// 가져오기
			portNo = refServerSocket.getLocalPort();
			
System.out.println("운영체제로부터 가져온 포트 번호는 " + portNo);
			
		} catch(IOException ioexception) {
			
			System.out.println("네트워크 관련 예외상황 발생!!");
			System.out.println("내용은 " + ioexception.getMessage());
			ioexception.printStackTrace();
		}
		
		
		// 5. 위에서 만든 폰트 객체를 라벨 객체에 적용하기
		this.titleLabel.setFont(font);
		
		// 6. 위에서 만든 폰트 객체를 텍스트 아래아 객체에 적용하기
		this.msgsTextArea.setFont(font);
		
		// 7. 라벨 객체는 윈도우 안에서 위쪽 방향에 출력
		this.add(this.titleLabel, BorderLayout.NORTH);
		
		// 8. 텍스트 아래아 객체는 윈도우 안에서 가운데 방향에 출력
		this.add(this.msgsTextArea, BorderLayout.CENTER);
		
		// 9. 사용자가 윈도우 종료 이벤트를 발생시키면
		// 자동으로 windowClosing( ) 함수를 호출하기 위한 명령문 작성
		this.addWindowListener(this);
		
		// 10. 윈도우의 크기를 화면 전체 크기로 바꾸기
		/*
		 * 1) 현재 컴퓨터에 설정되어 있는 해상도 크기를 가져오기 
		 * -> java.awt 패키지에 있는 Toolkit 클래스를 사용
		 *    -> getDefaultToolkit( ) 함수를 호출
		 *       -> getScreenSize( ) 함수를 호출       
		 *          -> Dimension 타입으로 해상도 크기를 프로그램으로
		 *             전달해 줍니다!!
		 * -> Dimension 클래스 : 
		 *     윈도우의 가로 크기와 세로 크기를 갖고 있는 클래스
		 *     가로 크기는 width 멤버 변수에 저장됩니다!!
		 *     세로 크기는 height 멤버 변수에 저장됩니다!!     
		 */
System.out.println("***현재 윈도우의 해상도 크기를 가져와서 ");
System.out.println("임시 변수에 저장하기***");
Dimension screenSizeDimension = 
               Toolkit.getDefaultToolkit().getScreenSize();
// setSize( ) 함수를 호출해서 현재 메모리에 만들어져 있는 윈도우의
// 가로 크기와 세로 크기를 바꿉니다!!
this.setSize(screenSizeDimension.width, screenSizeDimension.height);

// setResizable( ) 함수를 호출해서 사용자가 임의로 윈도우의 가로 크기와
// 세로 크기를 바꾸지 못하도록 하기
this.setResizable(false);

// 메모리에 생성된 윈도우를 화면에 출력하기
// -> setVisible( true );
this.setVisible(true);
		
	}
	
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
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
	
}

public class MyChattingServerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
MyChattingServerFrameClass 
      refMyChattingServerFrameClass 
= new MyChattingServerFrameClass("My First Chatting Server Window");
	}

}




