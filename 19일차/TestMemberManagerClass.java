
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

	// 모든 다이얼로그 창에서 함께 사용할 수 있는
	// 회원 아이디를 만들어주는 클래스 변수를 선언
	private static int s_member_id = 1;

	// 오라클 데이터베이스 접속(연결) 정보를 유지하는
	// 변수를 선언
	private static Connection s_refOracleConnection = null;

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

	// static 블럭을 만들어서 제일 먼저
	// 오라클 데이터베이스에 접속해서 오라클 데이터베이스로
	// 로그인을 시도하는 명령어를 작성
	static {
		System.out.println("***static 블럭***");

		// 오라클 데이터베이스에 접속(로그인)을 시도하기
		// 1. 오라클 드라이버 클래스를 메모리에 생성
		// 2. 오라클 데이터베이스로 연결(접속) 시도하기
		try {
			System.out.println("1. 오라클 드라이버 클래스를 메모리에 생성");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("2. 오라클 드라이버 클래스를 메모리에 생성했습니다!!");
			System.out.println("3. 오라클 데이터베이스에 접속(연결)을 시도하기");
			s_refOracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test_user1","1234");
			System.out.println("4. 오라클 데이터베이스 접속(연결) 성공!!");
			s_isOracleConnected = true;
		} catch (ClassNotFoundException cnfe) {
// 오라클 드라이버 클래스를 찾지 못한 경우에만 실행될
// 명령어를 작성
			System.out.println("오라클 드라이버 클래스 찾기 실패");
		} catch (SQLException sqle) {
// 오라클 데이터베이스에 접속에 실패한 경우에 실행할 명령어			
			System.out.println("오라클 데이터베이스에 접속 실패");
			System.out.println("이유는 " + sqle.getMessage());
		}
	} // end of static { }

	/*
	 * 현재 tbl_member 테이블에 저장되어 있는 모든 레코드들을 읽어와서
	 * 문자열로 반환해주는 함수 -> 문자열 값을
	 * ShowInnerButtonClass 내부 클래스에서 사용하기 위함
	 */
	public static String selectAllMethod() {
		// StringBuilder 클래스를 사용하기 : append( ) 함수를 사용하기 위해
		// String 클래스 -> String 변수이름 = "";
		StringBuilder sb = new StringBuilder("");
		// select * from tbl_member 명령어를 문자열로 보관할 변수 선언
		String selectAllSql = "select * from tbl_member";
		// Statement 인터페이스를 사용해서 문자열 select sql 명령어를 보관
		Statement statement = null;
		// -> select sql 명령어에 물음표 기호가 없기 때문에
		// select sql 명령어의 실행 결과를 표 형태로 보관할 변수 선언
		ResultSet resultSet = null;
		
		
		try {
			
			// 1. 오라클 데이터베이스에 접속 중인지 여부를 검사
			if(s_isOracleConnected == false)
			{
				System.out.println("오라클 데이터베이스에 접속 중이 아님");
				return "";
			}
			
			// 2. 오라클 접속 변수를 사용해서 Statement 객체를 만들기
			statement = s_refOracleConnection.createStatement();
			
			// 3. executeQuery( ) 함수를 호출해서 select sql 명령어를 실행
			resultSet = statement.executeQuery(selectAllSql);
			
			// 4. while 반복문을 사용해서 표 구조를 갖고 있는 변수
			// resultSet가 갖고 있는 모든 데이터들을 행 단위로 읽어와서
			// StringBuilder 변수에 append( ) 함수를 호출하기
			while(resultSet.next() == true)
			{
				/*
				 * next( ) 함수 : 포인터 변수 : 표 구조의 첫 번째 행의
				 * 주소로 이동해 주는 함수
				 * 표 구조에서 데이터를 가져오는 함수는 get + 자료형( ) 
				 */
				// 1. 첫 번째 값은 회원 아이디를 가져와서 임시 변수에 저장
				int idValue = resultSet.getInt(1);
				// 1 : create table 명령어에서 작성했던 컬럼 순서 번호
				// 2. 회원 이름을 가져와서 임시 변수에 저장
				String nameValue = resultSet.getString(2);
				// 3. 성별을 가져와서 임시 변수에 저장
				String genderValue = resultSet.getString(3);
				// 4. 나이를 가져와서 임시 변수에 저장
				int ageValue = resultSet.getInt(4);
				
				// 5. 위에서 임시 변수에 보관되어 있는 모든 값들을
				// 문자열로 만든 후에 StringBuilder 변수에 추가하기(append() 함수를 호출)
				String strResult = 
			idValue+","+nameValue+","+genderValue+","+ageValue+"\r\n";
			// "\r\n" : TextArea 컴포넌트의 줄 바꿈 기호
			// \r : carriage return : 커서의 위치를 현재 줄에서 
			// 맨 왼쪽으로 이동
				
				// 6. 위에서 만든 문자열을 StringBuilder 변수에 추가하기
				sb.append(strResult);							
			}
						
		} catch(SQLException sqle) {
			System.out.println("sql 명령어 실행시에 예외상황이 발생!!");
			System.out.println("내용은 "+sqle.getMessage());
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
			} catch(SQLException sqle2) {
				System.out.println("예외상황 발생!!");
				System.out.println("내용은 "+sqle2.getMessage());
			}
		}
				
		return sb.toString();
	}
	
	// 오라클 데이터베이스에 새로운 회원 정보를 저장해주는
	// 함수를 만듭니다!!
	/*
	 * 필요한 회원 정보 : 이름(문자열: String), 성별(문자열: String), 나이(정수 : int) -> 매개 변수로 선언 ->
	 * (String nameValue, String genderValue, int ageValue)
	 * 
	 * 반환형 : boolean : 정상적으로 오라클데이터베이스에 회원 정보가 저장되면 참(true)을 반환 문제가 생긴 경우에는
	 * 거짓(false)을 반환
	 */
	public static boolean insertMethod(String nameValue, String genderValue, int ageValue) {
		boolean result = false;
		// insert sql 명령어를 문자열로 보관하는 변수 선언
		String insertSql = "insert into tbl_member values(?,?,?,?)";
		// insert into 문자열 명령어를 먼저 메모리에 저장
		// -> 문자열 데이터를 객체화
		// -> PreparedStatement 인터페이스를 사용
		PreparedStatement preparedStatement = null;
		// insert into sql 명령어의 실행 결과를 보관할 변수
		// -> 데이터베이스에 추가된 레코드(행)의 갯수
		int resultInsertSql = 0;

		try {

			// 1. 오라클 데이터베이스에 접속 중인지
			// 여부를 상태 변수를 통해서 점검하기
			if (s_isOracleConnected == false) {
				System.out.println("오라클 데이터베이스에 접속 중이 아님");
				System.out.println("현재 함수를 종료하기!!");
				return false;
			}
			System.out.println("현재 오라클 데이터베이스에 접속 중");
// 메모리에 PreparedStatement 변수를 생성하기
// -> 컨넥션 변수가 갖고 있는 prepareStatement( ) 함수를 호출
			preparedStatement = s_refOracleConnection.prepareStatement(insertSql);
// insert into 명령어에 있었던 물음표(?) 위치에 값들을
// 차례대로 넣어 주셔야 합니다!!
// -> 순서 : create table tbl_member : 컬럼의 순서와 일치
// -> 물음표(?) 기호의 위치 번호는 첫 번째가 1, 두 번째가 2, 세 번째가 3, 네 번째가 4
// -> 회원 아이디 값은 정수 이므로 preparedStatement
// -> 변수가 갖고 있는 setInt(물음표의 위치 번호, 아이디값);
			preparedStatement.setInt(1, s_member_id);
// -> 회원 이름은 문자열 데이터 -> setString(위치 번호, 이름)
			preparedStatement.setString(2, nameValue);
// -> 회원 성별도 문자열 데이터 -> setString(위치 번호, 성별)
			preparedStatement.setString(3, genderValue);
// -> 회원 나이는 정수형 데이터 -> setInt(위치 번호, 나이)
			preparedStatement.setInt(4, ageValue);
// insert into sql 명령어의 실행은 executeUpdate() 함수를 호출
			resultInsertSql = preparedStatement.executeUpdate();
// 성공적(정상적)으로 데이터베이스에 새로운 회원 정보가
// 저장되었는지를 검사하기 -> 하나의 레코드만 저장
			if (resultInsertSql == 1) {
				result = true;
				System.out.println("성공적으로 회원 정보가 저장됨");
				// 다음에 데이터베이스에 저장될 회원 아이디를 만들기
				++s_member_id;
			} else {
				System.out.println("회원 정보 저장 실패!!");
			}
		} catch (SQLException sqle) {
			System.out.println("sql 관련 예외상황 발생!!");
			System.out.println("내용은 " + sqle.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqle2) {
				System.out.println("메모리 청소 중에 예외상황 발생");
				System.out.println("내용은 " + sqle2.getMessage());
			}
		}

		return result;
	}

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
		private Label sbicTitleLabel = new Label("***회원 정보 저장***", Label.CENTER);

		// 회원 이름을 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberNameTF = new TextField("회원 이름을 입력", 30);

		// 회원 성별을 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberGenderTF = new TextField("회원 성별을 입력", 20);

		// 회원 나이를 입력할 수 있도록 입력 창을 만들기
		private TextField sbicMemberAgeTF = new TextField("회원 나이를 입력", 20);

		// 저장 버튼을 만듭니다!!
		private Button sbicMemberSaveButton = new Button("회원 정보 저장(등록)");

		// 판넬 컨테이너 객체를 만듭니다!!
		// -> 용도 : 3개의 입력 창을 넣습니다!!
		private Panel sbicMemberPanel = new Panel(new GridLayout(1, 3));
		// GridLayout : 표 구조를 만들어주는 배치 관리자
		// GridLayout(행의 갯수, 열의 갯수)

		// 폰트 객체를 만듭니다.
		private Font sbicFont = new Font("궁서체", Font.BOLD, 15);

		// 생성자 함수를 만듭니다.
		public SaveButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			// 위에서 만든 판넬 객체에 3개의 입력 창을
			// 차례대로 넣습니다!!
			// 1. 제일 왼쪽에는 회원 이름을 입력하는 창이
			// 출력되도록 합니다
			this.sbicMemberPanel.add(this.sbicMemberNameTF);
			// 2. 중간 위치에는 회원의 성별을 입력하는 창을 출력
			this.sbicMemberPanel.add(this.sbicMemberGenderTF);
			// 3. 제일 오른쪽에는 회원의 나이를 입력하는
			// 창을 출력
			this.sbicMemberPanel.add(this.sbicMemberAgeTF);
			// 4. 폰트 객체를 모든 객체에 사용(적용)
			this.sbicMemberAgeTF.setFont(sbicFont);
			this.sbicMemberGenderTF.setFont(sbicFont);
			this.sbicMemberNameTF.setFont(sbicFont);
			this.sbicMemberSaveButton.setFont(sbicFont);
			this.sbicTitleLabel.setFont(sbicFont);
			// 5. 판넬 객체를 다이얼로그 창의 가운데
			// 위치에 출력
			this.add(this.sbicMemberPanel, BorderLayout.CENTER);
			// 6. 라벨(타이틀 출력 메시지)은 다이얼로그
			// 창의 위쪽 위치에 출력
			this.add(this.sbicTitleLabel, BorderLayout.NORTH);
			// 7. 저장 버튼은 다이얼로그 창의 아래쪽 위치에 출력
			this.add(this.sbicMemberSaveButton, BorderLayout.SOUTH);
			// 8. 윈도우(다이얼로그) 종료 이벤트 처리
			this.addWindowListener(this);
			// 9. 저장 버튼 클릭 이벤트 처리 등록
			this.sbicMemberSaveButton.addActionListener(this);
			// 10. 다이얼로그의 기본 크기를 지정하기
			this.setSize(500, 500);
			// 11. 다이얼로그를 화면에 출력하기
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
// 사용자가 저장 버튼을 클릭하는 경우에만 실행될 명령어를 작성
			System.out.println("사용자가 버튼을 클릭!");
			Object refObject = e.getSource();
			if (refObject instanceof Button) {
				Button refButton = (Button) refObject;
				if (refButton == sbicMemberSaveButton) {
					System.out.println("저장 버튼을 클릭!!");

					// 사용자가 입력한 이름과 성별, 나이를 가져오기
					String nameValue = sbicMemberNameTF.getText().trim();
					String genderValue = sbicMemberGenderTF.getText().trim();
					String ageValue = sbicMemberAgeTF.getText().trim();

					// 사용자가 값을 입력했는지 여부를 검사하기
					if (nameValue != null && genderValue != null && ageValue != null) {
						if (nameValue.length() > 0 && genderValue.length() > 0 && ageValue.length() > 0) {
// 나이는 정수로 변환한 다음에 임시 변수에 저장하기
							int iageValue = Integer.parseInt(ageValue);
// insertMethod( ) 함수를 호출하기
							boolean resultInsertMethod = insertMethod(nameValue, genderValue, iageValue);
// insertMethod( ) 함수가 만든 결과 값을 검사하기
							if (resultInsertMethod == true) {
								System.out.println("insertMethod() 실행 성공!!");
							} else {
								System.out.println("insertMethod() 실행 실패!!");
							}
						} else {
							System.out.println("사용자가 입력하지 않았습니다!!");
						}
					}
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
			Window window = arg0.getWindow();
			window.setVisible(false);
			window.dispose();
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

		/*
		 * 검색 키워드를 사용자가 입력하도록 합니다. -> TextField
		 * 검색 주제를 사용자가 선택하도록 합니다. -> 
		 *   회원 이름/회원 성별/회원 나이 -> Choice 컴포넌트를 사용
		 *   -> 하나의 문자열만을 화면에 출력해 주는 컴포넌트
		 *   -> 사용자가 하나의 문자열을 선택할 수 있습니다.
		 * 
		 */
		// 1. 제목을 출력해주는 라벨 객체를 만들기
		private Label sbic3TitleLabel = new Label("***회원 검색 화면***", Label.CENTER);
		
		// 2. 검색 주제(회원이름/회원성별/회원나이/선택)를 문자열 배열로
		// 보관하기
		private String strArray[] = 
			{"검색 주제를 선택","회원이름","회원성별","회원나이"};
		
		// 3. 위에서 만든 문자열 배열을 갖는 Choice 컴포넌트 객체를 만들기
		private Choice sbic3Choice = new Choice();
		
		// 4. 검색 키워드를 입력할 창을 만듭니다!!
		private TextField  sbic3TextField = 
				new TextField("검색 키워드를 입력하세요", 40);
		
		// 5. 검색 버튼 객체를 만들기
		private Button sbic3Button = new Button("검색");
		
		// 6. 폰트 객체 만들기
		private Font  sbic3Font = new Font("궁서체",Font.BOLD,20);
		
		// 7. 검색 결과를 화면에 출력해주는 객체를 만들기
		private TextArea sbic3TextArea = new TextArea("", 10, 20);
		// -> 10 : 행의 갯수, 20 : 열의 갯수
		
		/*
		 * 화면 배치
		 * 
		 * 1. 맨 위에는 제목을 출력 : sbic3TitleLabel
		 * 2. 가운데 위치에는 검색 결과를 출력 : sbic3TextArea
		 * 3. 아래 위치에는 sbic3Choice(검색 주제를 출력)
		 *    sbic3TextField(검색 키워드를 사용자가 입력)
		 *    sbic3Button(검색 버튼)
		 * 
		 */
		public SearchButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			// 맨 아래 위치(BorderLayout.SOUTH)에 사용할 판넬 객체를 만들기
			Panel panel = new Panel();
			// 판넬 객체 안에는 초이스 컴포넌트, 텍스트필드, 검색 버튼을 넣습니다.
			panel.add(sbic3Choice);
			panel.add(sbic3TextField);
			panel.add(sbic3Button);
			add(panel, BorderLayout.SOUTH);
			
			add(sbic3TitleLabel, BorderLayout.NORTH);
			add(sbic3TextArea, BorderLayout.CENTER);
			
			// 초이스 컴포넌트에 검색 주제 문자열을 넣습니다!!
			for(int i = 0; i < strArray.length; i++)
			{
				sbic3Choice.add(strArray[i]);
			}
			
			// 초이스 컴포넌트를 사용자가 선택한 경우에 발생할
			// ItemEvent 타입의 이벤트를 처리하기 위한 명령어
			sbic3Choice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					System.out.println("사용자가 검색 주제를 선택!!");					
				}
			});
			
			// 사용자가 버튼을 클릭할 경우에만 발생하는 ActionEvent 이벤트를
			// 처리하기 위한 명령어
			sbic3Button.addActionListener(this);
			
			// 윈도우 종료 이벤트를 처리하기 위한 명령어
			this.addWindowListener(this);
			
			this.setSize(500, 500);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// 사용자가 검색 버튼을 클릭하는 경우에만 실행할 명령어를 작성
			Object refObject = e.getSource();
			
			if(refObject instanceof Button) {
				
				Button refButton = (Button)refObject;
				
				if(refButton == sbic3Button) {
					
					System.out.println("사용자가 검색 버튼을 클릭함");
					
// 1. 사용자가 선택한 검색 주제 문자열 값을 가져와야 합니다!!
String itemValue = sbic3Choice.getSelectedItem();

// 2. 사용자가 "검색 주제를 선택" 문자열을 선택한 경우에는
// 검색 결과 창에 출력된 모든 내용을 다 지우고
// 사용자가 입력한 검색 키워드도 다 지우도록 하겠습니다!!
if(itemValue.equals(strArray[0]) == true) {
	// 사용자가 검색 주제를 선택 문자열을 선택한 경우
	System.out.println("사용자가 검색 주제를 선택!!");
	// setText( ) 함수를 호출해서 검색 결과 창에 출력된 모든 내용들을 지우기
	sbic3TextArea.selectAll();
	sbic3TextArea.setText("");	
}
else {
	// 사용자가 회원이름/회원성별/회원나이 중에서 하나의 문자열을 선택한 경우
	System.out.println("사용자가 회원이름/회원성별/회원나이 중에서 ");
	System.out.println("하나를 선택!!");
}
					
					
					
// 2. 사용자가 입력한 검색 키워드를 가져와야 합니다!!					
					
					
				}
				
			}
			
			
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
			Window refWindow = e.getWindow();
			refWindow.setVisible(false);
			refWindow.dispose();
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
		
		/*
		 * tbl_member 테이블에 보관되어 있는 모든 데이터들을 읽어와서
		 * 화면에 출력해주는 기능을 구현하기
		 * -> 테이블에 여러 개의 레코드(행 또는 줄)들이 저장될 수 있으므로
		 *    TextArea  컴포넌트를 사용해야 합니다!!       
		 */
		private TextArea sbic2TextArea = new TextArea("", 10, 30);
		// 10 : 행(가로 줄 수), 30 : 열(세로 칸 수)
		
		// 타이틀을 화면에 보여주는 라벨 객체를 만듭니다!!
		private Label  sbic2TitleLabel = 
				new Label("***모든 데이터 출력***", Label.CENTER);
		
		// 버튼 객체를 만듭니다!!
		private Button sbic2ShowAllButton = new Button("모든 테이터 출력");
		
		// 폰트 객체를 만듭니다!!
		private Font sbic2Font = new Font("궁서체",Font.BOLD, 15);
		
		public ShowButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			sbic2TextArea.setFont(sbic2Font);
			sbic2ShowAllButton.setFont(sbic2Font);
			sbic2TitleLabel.setFont(sbic2Font);
			
			add(sbic2TitleLabel, BorderLayout.NORTH);
			add(sbic2TextArea);
			add(sbic2ShowAllButton, BorderLayout.SOUTH);
			
			this.addWindowListener(this);
			this.sbic2ShowAllButton.addActionListener(this);
			
			this.setSize(500, 500);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// 사용자가 버튼을 클릭했을 때만 실행할 수 있는 명령어들을 작성
			Object refObject = e.getSource();
			if(refObject instanceof Button) {
				Button refButton = (Button)refObject;
				if(refButton == sbic2ShowAllButton) {
					System.out.println("사용자가 버튼을 클릭!!");
					
					// 방금 만든 selectAllMethod( ) 함수를 호출
					String resultSelectAllMethod;
										
					resultSelectAllMethod = selectAllMethod();
					
					if(resultSelectAllMethod == null ||
					   resultSelectAllMethod.length() == 0) {
						System.out.println("현재 등록된 회원이 없습니다!!");
					} else {
						// TextArea 컴포넌트에 문자열을 출력하기
						sbic2TextArea.setText(resultSelectAllMethod);
					}
				}
			}
			
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		// 사용자가 윈도우 종료 버튼을 클릭했을 때만 실행될 수 있는 명령어들을 작성
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			// 1. 다이얼로그 창만 종료하기
			// 1) 다이얼로그 창의 주소를 알아오기
			Window refWindow = e.getWindow();
			// 2) setVisible(false); 함수를 호출해서 출력하지 않기
			refWindow.setVisible(false);
			// 3) 메모리에 있는 다이얼로그 창을 시스템에 반환하기
			// -> dispose( ) 함수를 호출하기
			refWindow.dispose();
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
		TestMemberManagerFrameClass refFrame;
		refFrame = new TestMemberManagerFrameClass("회원 관리 예제");
	}
}
