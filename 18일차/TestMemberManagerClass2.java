
/*
 * 회원 관리 예제를 만듭니다!!
 * 
 * 1. 화면에 프로그램 제목과 버튼들을 출력하기
 */
import java.awt.*;
import java.awt.event.*;
// JOptionPane 클래스를 사용하기 위해서는 javax.swing 패키지에 있습니다!!
import javax.swing.JOptionPane;
// 데이터베이스 관련된 클래스와 인터페이스들을 사용하기 위함
import java.sql.*;

// 화면에 윈도우를 출력하고 프로그램 제목과 버튼들을
// 보여주는 클래스를 만듭니다!!
class TestMemberManagerFrameClass2 extends Frame implements WindowListener, ActionListener {

	// 모든 다이얼로그 창에서 함께 사용할 수 있는
	// 회원 아이디를 만들어주는 클래스 변수를 선언
	private static int s_member_id = 1;
	
	// 오라클 데이터베이스 접속(연결) 정보를 유지하는
	// 변수를 선언
	private static Connection 
	                 s_refOracleConnection = null;
	
	// 상태 변수를 새로 선언 : 현재 오라클 데이터베이스에
	// 접속 중이면 참(rue) 값을 가지고 있고,
	// 접속 중이 아니라면 거짓(false) 값을 갖는 변수를 선언
	private static boolean s_isOracleConnected = false;
			
	// 1. 프로그램 제목을 화면에 출력해주는 라벨 객체
	private Label titleLabel = new Label("***회원 관리 예제***", Label.CENTER);

	// 2. 데이터베이스에 회원 정보를 저장하는 버튼
	private Button saveMemberInfoButton = new Button("회원 정보 저장");

	// 3. 데이터베이스에 저장되어 있는 모든 회원 정보들을
	// 화면에 보여주는 버튼
	private Button showMemberInfoButton = new Button("회원 정보 출력");

	// 4. 데이터베이스에 저장되어 있는 특정 회원
	// 정보를 검색하는 버튼
	private Button searchMemberInfoButton = new Button("회원 정보 검색");

	// 5. 데이터베이스에 저장되어 있는 특정 회원
	// 정보를 수정하는 버튼
	private Button modifyMemberInfoButton = new Button("회원 정보 수정");

	// 6. 데이터베이스에 저장되어 있는 특정 회원
	// 정보를 삭제하는 버튼
	private Button deleteMemberInfoButton = new Button("회원 정보 삭제");

	// 7. 프로그램을 종료하는 버튼
	private Button exitProgramButton = new Button("프로그램 종료");

	// 8. 위에서 만든 6개의 버튼들을 화면에 보여줄 때
	// 버튼의 위치와 크기를 정해주는 GridLayout
	// 객체를 생성
	private GridLayout gridLayout = new GridLayout(3, 2);

	// 9. 위에서 만든 GridLayout 배치 관리자 객체를
	// 사용하는 Panel 컨테이너 객체를 생성
	private Panel panel = new Panel(gridLayout);

	// 10. 폰트 객체를 만들기
	private Font font = new Font("궁서체", Font.BOLD, 20);

	// 생성자 함수 만들기
	public TestMemberManagerFrameClass2() {
		// TODO Auto-generated constructor stub
		this("회원 관리 예제");
	}

	// main( ) 함수로부터 하나의 문자열을 받는 생성자 함수 만들기
	public TestMemberManagerFrameClass2(String title) {
		super(title);

		// 모든 라벨과 버튼들에 폰트를 적용
		this.titleLabel.setFont(font);
		this.saveMemberInfoButton.setFont(font);
		this.searchMemberInfoButton.setFont(font);
		this.deleteMemberInfoButton.setFont(font);
		this.modifyMemberInfoButton.setFont(font);
		this.exitProgramButton.setFont(font);
		this.showMemberInfoButton.setFont(font);

		// 6개의 버튼들을 판넬 컨테이너에 넣기
		this.panel.add(this.saveMemberInfoButton);
		this.panel.add(this.showMemberInfoButton);
		this.panel.add(this.searchMemberInfoButton);
		this.panel.add(this.modifyMemberInfoButton);
		this.panel.add(this.deleteMemberInfoButton);
		this.panel.add(this.exitProgramButton);

		// 판넬 컨테이너는 보더 레아아웃 배치 관리자의
		// 가운데 위치에 넣기
		this.add(this.panel, BorderLayout.CENTER);

		// 프로그램 제목은 위쪽 상단에 출력
		this.add(this.titleLabel, BorderLayout.NORTH);

		// 6개의 버튼 마다 명령 문자열을 새로 지정합니다.
		// -> setActionCommand("서로_다른_명령_문자열");
		this.saveMemberInfoButton.setActionCommand("save");
		this.showMemberInfoButton.setActionCommand("show");
		this.searchMemberInfoButton.setActionCommand("search");
		this.modifyMemberInfoButton.setActionCommand("modify");
		this.deleteMemberInfoButton.setActionCommand("delete");
		this.exitProgramButton.setActionCommand("exit");

// 6개의 버튼 클릭 이벤트 처리를 위한 addActionListener() 함수 호출
		this.saveMemberInfoButton.addActionListener(this);
		this.showMemberInfoButton.addActionListener(this);
		this.searchMemberInfoButton.addActionListener(this);
		this.modifyMemberInfoButton.addActionListener(this);
		this.deleteMemberInfoButton.addActionListener(this);
		this.exitProgramButton.addActionListener(this);

// 윈도우 종료 이벤트 처리를 위한 
// addWindowListener() 함수 호출
		this.addWindowListener(this);

// pack( ) 함수를 호출하기
		this.pack();

// 윈도우를 화면에 출력하기
		this.setVisible(true);
	}

	// 6개의 버튼 별로 사용자가 클릭하는 경우에
	// 서로 다른 기능을 구현할 수 있도록 하기

	// 5개의 버튼 별로 내부 클래스를 만들어서 사용하기

	// 1) 사용자가 회원 정보 저장 버튼을 클릭했을 때만
	// 사용할 내부 클래스
	static class SaveButtonInnerClass extends Dialog implements WindowListener, ActionListener {

		// 다이얼로그 타이틀을 출력할 라벨 객체 만들기
		private Label sbicTitleLabel = 
		new Label("***회원 정보 저장***",Label.CENTER);
		
		// 회원 이름을 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberNameTF = 
				new TextField("회원 이름을 입력", 30);
		
		// 회원 성별을 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberGenderTF =
				new TextField("회원 성별을 입력", 20);
		
		// 회원 나이를 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberAgeTF = 
				new TextField("회원 나이를 입력", 20);
		
		// 저장 버튼을 만듭니다!!
		private Button sbicMemberSaveButton =
				new Button("회원 정보 저장(등록)");
		
		// 판넬 컨테이너 객체를 만듭니다!!
		// -> 용도 : 3개의 입력 창을 넣습니다!!
		private Panel sbicMemberPanel = 
					new Panel(new GridLayout(1, 3)); 
		// GridLayout : 표 구조를 만들어주는 배치 관리자
		// GridLayout(행의 갯수, 열의 갯수)
		
		// 폰트 객체를 만듭니다.
		private Font sbicFont = 
				   new Font("궁서체",Font.BOLD,15);
		
		// 생성자 함수를 만듭니다.
		public SaveButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			// 위에서 만든 판넬 객체에 3개의 입력 창을
			// 차례대로 넣습니다!!
			// 1. 제일 왼쪽에는 회원 이름을 입력하는 창이
			// 출력되도록 합니다
			this.sbicMemberPanel.
			       add(this.sbicMemberNameTF);
			// 2. 중간 위치에는 회원의 성별을 입력하는 창을 출력
			this.sbicMemberPanel.
					add(this.sbicMemberGenderTF);
			// 3. 제일 오른쪽에는 회원의 나이를 입력하는
			// 창을 출력
			this.sbicMemberPanel.
					add(this.sbicMemberAgeTF);
			// 4. 폰트 객체를 모든 객체에 사용(적용)
			this.sbicMemberAgeTF.setFont(sbicFont);
			this.sbicMemberGenderTF.setFont(sbicFont);
			this.sbicMemberNameTF.setFont(sbicFont);
			this.sbicMemberSaveButton.setFont(sbicFont);
			this.sbicTitleLabel.setFont(sbicFont);			
			// 5. 판넬 객체를 다이얼로그 창의 가운데
			// 위치에 출력
			this.add(this.sbicMemberPanel, 
					BorderLayout.CENTER);
			// 6. 라벨(타이틀 출력 메시지)은 다이얼로그
			// 창의 위쪽 위치에 출력
			this.add(this.sbicTitleLabel, 
					BorderLayout.NORTH);
			// 7. 저장 버튼은 다이얼로그 창의 아래쪽 위치에 출력
			this.add(this.sbicMemberSaveButton, 
					BorderLayout.SOUTH);
			// 8. 윈도우(다이얼로그) 종료 이벤트 처리
			this.addWindowListener(this);
			// 9. 저장 버튼 클릭 이벤트 처리 등록
			this.sbicMemberSaveButton.
			       addActionListener(this);
			// 10. 다이얼로그의 기본 크기를 지정하기
			this.setSize(500, 500);
			// 11. 다이얼로그를 화면에 출력하기
			this.setVisible(true);			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	// 2) 사용자가 회원 정보 검색 버튼을 클릭했을 때만
	// 사용할 내부 클래스
	static class SearchButtonInnerClass extends Dialog implements WindowListener, ActionListener {

		public SearchButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// 3) 사용자가 회원 정보 수정 버튼을 클릭했을 때만
	// 사용할 내부 클래스
	static class ModifyButtonInnerClass extends Dialog implements WindowListener, ActionListener {

		public ModifyButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// 4) 사용자가 회원 정보 출력 버튼을 클릭했을 때만
	// 사용할 내부 클래스
	static class ShowButtonInnerClass extends Dialog implements WindowListener, ActionListener {
		public ShowButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}
	}

	// 5) 사용자가 회원 정보 삭제 버튼을 클릭했을 때만
	// 사용할 내부 클래스
	static class DeleteButtonInnerClass extends Dialog implements WindowListener, ActionListener {
		public DeleteButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}
	}

	// 메인 윈도우에서 사용자가 버튼을 클릭하는 경우에
	// 실행할 명령어들을 구별해서 작성하기
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		Object refObject = arg0.getSource();

		if (refObject instanceof Button) {

			Button refClickedButton = (Button) refObject;

			String actionCommand = refClickedButton.getActionCommand();

			actionCommand = actionCommand.toLowerCase();

			// 사용자가 회원 정보 저장 버튼을 클릭한 경우
			if (actionCommand.equals("save")) {
				SaveButtonInnerClass refSaveButtonInnerClass;
				refSaveButtonInnerClass = new SaveButtonInnerClass(this, "회원 정보 저장 다이얼로그", true);
			}
			// 사용자가 회원 정보 검색 버튼을 클릭한 경우
			else if (actionCommand.equals("search")) {
				SearchButtonInnerClass refSearchButtonInnerClass;
				refSearchButtonInnerClass = new SearchButtonInnerClass(this, "회원 정보 검색 다이얼로그", true);
			}
			// 사용자가 회원 정보 출력 버튼을 클릭한 경우
			else if (actionCommand.equals("show")) {
				ShowButtonInnerClass refShowButtonInnerClass;
				refShowButtonInnerClass = new ShowButtonInnerClass(this, "회원 정보 출력 다이얼로그", true);
			}
			// 사용자가 회원 정보 수정 버튼을 클릭한 경우
			else if (actionCommand.equals("modify")) {
				ModifyButtonInnerClass refModifyButtonInnerClass;
				refModifyButtonInnerClass = new ModifyButtonInnerClass(this, "회원 정보 수정 다이얼로그", true);
			}
			// 사용자가 회원 정보 삭제 버튼을 클릭한 경우
			else if (actionCommand.equals("delete")) {
				DeleteButtonInnerClass refDeleteButtonInnerClass;
				refDeleteButtonInnerClass = new DeleteButtonInnerClass(this, "회원 정보 삭제 다이얼로그", true);
			}
			// 사용자가 프로그램 종료 버튼을 클릭한 경우
			else if (actionCommand.equals("exit")) {
				System.exit(0);
			}

		}

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
public class TestMemberManagerClass2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
TestMemberManagerFrameClass2 refFrame;
refFrame = 
   new TestMemberManagerFrameClass2("회원 관리 예제");
	}
}






