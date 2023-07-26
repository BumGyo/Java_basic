
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
class TestMemberManagerFrameClass extends Frame implements WindowListener, ActionListener {

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
	public TestMemberManagerFrameClass() {
		// TODO Auto-generated constructor stub
		this("회원 관리 예제");
	}

	// main( ) 함수로부터 하나의 문자열을 받는 생성자 함수 만들기
	public TestMemberManagerFrameClass(String title) {
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
		
		
		public SaveButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
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

public class TestMemberManagerClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
