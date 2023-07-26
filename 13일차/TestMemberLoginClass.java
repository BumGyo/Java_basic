package day20180726;

/*
 * 1. 사용자 회원 가입 기능 만들기
 * 2. 사용자 회원에 한해서만 로그인 기능 만들기
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*; // 파일 또는 네트워크 관련 클래스와 인터페이스를 사용
// Properties 클래스를 사용하기 위함 : 새로운 파일을 만들 때 사용
// -> 속성 파일을 만들 때 사용
// -> 속성 파일은 기본 구조를 "키(key)=값(value)" : 
// -> 데이터 덩어리(그룹) : 제일 가벼운 파일
// -> 키(key) : 값에 대한 이름(Name) 또는 별명(Alias)
/*
 * 사용자가 회원 가입하는 시점에 속성 파일을 만듭니다!!
 * -> c 드라이브에 새로운 디렉토리(폴더)를 만듭니다!!
 *    : c:\\java2_dir
 * -> 사용자가 입력한 아이디를 파일 이름으로 하는 파일을 생성
 * 예) 사용자가 아이디를 id1 라고 입력한 경우 java2_dir 디렉토리 안에
 *     확장자가 properties 인 속성 파일을 만듭니다!!
 *     예) id1.properties
 * -> id1.properties 파일 안에는 사용자가 입력한 비밀번호를
 *    키=값 형식으로 저장하겠습니다!!
 *    예) 사용자가 비밀번호를 1234로 입력한 경우에는
 *        pwd=1234     
 */
import java.util.*;

/*
 * 화면 구성
 * 3개의 버튼을 만들도록 하겠습니다!!
 * 1. 첫 번째 버튼 : 회원 가입 기능을 구현할 버튼
 * 2. 두 번째 버튼 : 회원에 한해서 로그인 기능을 구현할 버튼
 * 3. 세 번째 버튼 : 프로그램 종료 기능을 구현할 버튼
 * 4. 사용자가 첫 번째 버튼을 클릭하면 화면에는 회원 가입 창이 출력
 * 5. 사용자가 두 번째 버튼을 클릭하면 화면에는 회원 로그인 창이 출력
 * 6. 사용자가 세 번째 버튼을 클릭하면 프로그램 전체를 종료 : 
 *    창을 사라지게 합니다!! 
 * 
 */
// Frame 클래스를 사용해서 화면에 하나의 창을 출력
// 이벤트 처리를 위해서 WindowListener 인터페이스(윈도우 종료 이벤트), 
// ActionListener 인터페이스(버튼 클릭 이벤트), 
// FocusListener 인터페이스를 사용(TextField에서 사용자가 무언가를 입력)
class MyMemberLoginFrameClass extends Frame implements WindowListener, ActionListener, FocusListener {

	/*
	 * 메인 윈도우에 출력할 3개의 버튼과 프로그램 제목(타이틀)을 보여줄 때 사용할 객체들을 생성합니다!!
	 */
	// 제목을 보여주는 Label 객체를 만듭니다!!
	private Label titleLabel = new Label("***회원 관리 예제***", Label.CENTER);

	// 회원 가입 창을 화면에 출력해주는 버튼 객체를 생성합니다!!
	private Button regMemberButton = new Button("회원 가입");
	// reg : registration(저장 또는 가입)

	// 로그인 창을 화면에 출력해주는 버튼 객체를 생성
	private Button loginButton = new Button("로그인");

	// 프로그램 종료 버튼 객체를 생성
	private Button exitButton = new Button("프로그램 종료");

	/*
	 * 위에서 만든 라벨 객체는 BorderLayout 배치 관리자의 위쪽 방향에 출력
	 * 
	 * 3개의 버튼은 가운데 위치에 출력 : Panel 컨테이너와 GridLayout 격자 모양을 만들어주는 배치 관리자를 사용 -> 1행 3열
	 * 표 구조를 만든 다음에 표 안에 3개의 버튼을 차례대로 넣겠습니다!!
	 */
	private GridLayout gridLayout = new GridLayout(1, 3);
	private Panel panel = new Panel(gridLayout);

	// 글자 크기를 바꿀 때 사용할 Font 객체를 생성
	private Font font = new Font("궁서체", Font.BOLD, 20);

	/*
	 * 메인 윈도우의 위치를 변경해주는 함수를 만들기 -> 이 함수를 사용하지 않으면 왼쪽 상단 끝 x 좌표는 0, y 좌표는 0 위치에 출력
	 * -> x = 0, y = 0 -> 현재 화면의 가운데 위치로 바꾸고자 합니다!!
	 * 
	 * -> 매개 변수가 필요 : 메인 윈도우의 가로 크기 : int width 메인 윈도우의 세로 크기 : int height
	 * 
	 * -> 반환형 : Point 클래스를 사용 : x좌표 y좌표 값을 멤버변수로 갖고 있는 클래스
	 */
	public static Point getMyWindowPoint(int width, int height) {
		Point resultPoint = null;

		/*
		 * 1. 현재 화면의 전체 크기를 구하기 : 해상도 구하기 -> 구한 결과는 Dimension 클래스 : 사각형 윈도우의 가로 크기와 세로
		 * 크기를 갖는 클래스 -> 가로 크기 : int width -> 세로 크기 : int height
		 */
		Dimension refScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

		System.out.println("화면 전체 크기를 구했습니다!!");
		System.out.println("가로 크기는 " + refScreenSize.width);
		System.out.println("세로 크기는 " + refScreenSize.height);

		/*
		 * 화면에 출력할 윈도우의 왼쪽 상단 모서리의 좌표를 구하는 공식
		 */
		int x = 0;
		int y = 0;

		x = refScreenSize.width / 2 - width / 2;
		y = refScreenSize.height / 2 - height / 2;

		System.out.println("화면에 출력할 윈도우의 왼쪽 상단 ");
		System.out.println("모서리의 x 좌표는 " + x);
		System.out.println("모서리의 y 좌표는 " + y);

		// 위에서 구한 x,y 좌표 값을 Point 클래스에 전달하기
		// -> new Point(x, y) 명령어를 작성
		resultPoint = new Point(x, y);

		// 함수를 호출한 클래스로 x좌표와 y좌표 값을 전달하기
		return resultPoint;
	} // end of method

	// 화면에 윈도우를 출력해주는 생성자 함수를 만듭니다!!
	public MyMemberLoginFrameClass(String title) {
		// super : 부모 클래스의 주소를 갖고 있는 클래스 자동 변수
		// -> super( ) : 부모 클래스에 있는 생성자 함수를 호출
		super(title);

		// 위에서 만든 폰트 객체를 라벨과 3개의 버튼에 적용하기
		titleLabel.setFont(font);
		regMemberButton.setFont(font);
		loginButton.setFont(font);
		exitButton.setFont(font);

		// 3개의 버튼은 차례대로 판넬 컨테이너에 넣기
		panel.add(regMemberButton);
		panel.add(loginButton);
		panel.add(exitButton);

		// 판넬 컨테이너는 메인 윈도우의 가운데 위치에 넣기
		add(panel, BorderLayout.CENTER);

		// 타이틀을 보여주는 라벨은 메인 윈도우의 위쪽 방향에 넣기
		add(titleLabel, BorderLayout.NORTH);

		// 이벤트 처리 명령어들을 작성하기
		this.addWindowListener(this);

		// 버튼은 명령어를 구분할 수 있는 문자열을 새로 만든 다음에
		// 각각의 버튼에 문자열을 저장하기
		/*
		 * 일반적인 방식은 사용자가 클릭한 버튼의 주소를 읽어오기
		 * 
		 */
		// 버튼 객체 별로 갖고 있는 setActionCommand( ) 함수를 호출해서
		// 버튼 마다 새로운 문자열 값을 지정하기
		// -> 사용자가 클릭한 버튼을 구분할 때 사용할 값
		regMemberButton.setActionCommand("reg");
		loginButton.setActionCommand("log");
		exitButton.setActionCommand("ext");

		// 3개의 버튼 별로 addActionListener( ) 함수를 호출하기
		regMemberButton.addActionListener(this);
		loginButton.addActionListener(this);
		exitButton.addActionListener(this);

		// 위에서 만든 getMyWindowPoint( ) 함수를 호출해서
		// -> 화면에 출력할 윈도우의 왼쪽 상단 좌표 값을 구하기
		// 1) pack( ) 함수를 먼저 호출해야 합니다!! : 윈도우의
		// 크기를 구하기 위함
		this.pack();

		System.out.println("화면에 출력할 윈도우의 크기를 출력");
		System.out.println("윈도우의 가로크기는 " + getWidth());
		System.out.println("윈도우의 세로크기는 " + getHeight());

		// 위에서 만들었던 getMyWindowPoint( ) 함수를 호출
		Point refPoint = getMyWindowPoint(getWidth(), getHeight());

		// setLocation( ) 함수를 호출해야 윈도우의 왼쪽 상단
		// 모서리의 좌표 값을 바꿀 수 있습니다!!
		this.setLocation(refPoint);

		// setResizable( ) 함수를 호출해서 윈도우의 크기를
		// 바꾸지 못하도록 하기
		this.setResizable(false);

		// 윈도우를 화면에 출력하기
		this.setVisible(true);
	} // end of 생성자 함수

	/*
	 * 사용자가 회원 가입 버튼을 클릭하는 경우에만 사용할 클래스를 만들기
	 */
	static class MyRegMemberDialogClass extends Dialog implements WindowListener, ActionListener {

		// 윈도우의 제목을 출력할 라벨 객체를 만듭니다!!
		private Label dlgTitleLabel = new Label("회원 가입 창", Label.CENTER);

		// 사용자가 아이디를 입력할 수 있도록 TextField 객체를 만들기
		private TextField idTF = new TextField("아이디를 입력하세요...", 30);

		// 사용자가 비밀번호를 입력할 수 있도록 TextField 객체를 만들기
		private TextField pwdTF = new TextField("비밀번호를 입력하세요...", 30);

		// 저장 버튼을 만듭니다!! -> 사용자가 클릭하면
		// 사용자가 입력한 아이디를 가지고 속성 파일을 만들 예정
		private Button saveButton = new Button("저장");

		/*
		 * 라벨은 맨 위에 출력 : BorderLayout.NORTH 아이디와 비밀번호 입력 창은 판넬 컨테이너에 넣은 다음에 보더 레이아웃의 가운데
		 * 위치에 출력 : BorderLayout.CENTER 저장 버튼의 위치는 아래 쪽 : BorderLayout.SOUTH
		 */

		// 폰트 객체를 생성합니다!! -> 글자 크기는 15로 설정
		private Font dlgFont = new Font("궁서체", Font.BOLD, 15);

		private Panel dlgPanel = new Panel(new GridLayout(1, 2));

		// 속성 파일을 만들 때 사용할 Properties 객체를 생성
		private Properties dlgProperties = new Properties();

		// 새로 만들 속성 파일의 전체 경로(절대 경로)를 상수로 정의
		public static final String FILE_PATH = "c:/java2_dir/";
		// "c:\\java2_dir\\"
		// -> c:/java2_dir/사용자가입력한아이디.properties

		// 생성자 함수를 만들어서 Dialog 클래스의 부모 클래스를
		// 지정해 주어야 합니다!!
		public MyRegMemberDialogClass(Frame parent, String title, boolean modal) {
			super(parent, title, modal);
			/*
			 * parent : Dialog 클래스의 부모 윈도우(창) title : Dialog 창의 왼쪽 상단에 출력할 문자열 modal : 모달
			 * 여부를 갖는 매개 변수
			 * 
			 * 윈도우(창) 실행 순서는 부모 윈도우(Frame) -> 자식 윈도우(Dialog)
			 * 
			 * modal 매개 변수는 메인 윈도로부터 참(true) 또는 거짓(false) 값 중에서 하나를 전달 받을 예정
			 * 
			 * 만약 참을 받으면 모달 창이 되어서 다시 부모 창으로 이동하기 위해서는 현재 화면에 출력되고 있는 자식 창을 닫아야 합니다!!
			 * 
			 * 만약 거짓을 받으면 모달 리스 창이 되어서 현재 자식 창을 출력한 상태에서 자유롭게 부모 창으로 이동할 수 있습니다!!
			 */

			// 새로 만든 판넬 컨테이너 객체에 아이디 입력 창과
			// 비밀 번호 입력 창을 차례대로 넣기
			dlgPanel.add(idTF);
			dlgPanel.add(pwdTF);

			// 판넬 컨테이너의 위치는 보더 레이아웃의 가운데 위치
			add(dlgPanel, BorderLayout.CENTER);

			// 타이틀을 맨 위에 출력
			add(dlgTitleLabel, BorderLayout.NORTH);

			// 저장 버튼은 아래 쪽에 출력
			add(saveButton, BorderLayout.SOUTH);

			dlgTitleLabel.setFont(dlgFont);
			idTF.setFont(dlgFont);
			pwdTF.setFont(dlgFont);
			saveButton.setFont(dlgFont);

			// 이벤트 : 윈도우 종료 이벤트
			this.addWindowListener(this);

			// 버튼 클릭 이벤트
			saveButton.addActionListener(this);

			// 다이얼로그 창의 크기를 가로 크기는 300, 세로 크기는
			// 300으로 설정하기
			setSize(300, 300);
			setVisible(true);
		}

		// 사용자가 saveButton 버튼을 눌렀을 때 실행할 명령어들을
		// 입력
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 1. 사용자가 클릭한 버튼의 주소를 가져오기
			Object refObject = e.getSource();

			// 2. 버튼 유무를 판단하기
			if (refObject instanceof Button) {
				System.out.println("사용자가 버튼을 클릭!!");
				// 3. 형 변환 연산자를 사용해서
				// Object 클래스 형의 객체를 Button 클래스 형으로
				// 바꿉니다!!
				Button refButton = (Button) refObject;

				// 4. 버튼 구별하기
				if (refButton == saveButton) {
					System.out.println("저장 버튼을 클릭!!");

					/*
					 * 1. 사용자가 입력한 아이디 값을 가져오기
					 */
					String idValue = idTF.getText().trim();
					// 예) "id1"
					// 예) " id1"

					// 2. 사용자가 아이디 값을 입력했는지 여부를
					// 검사하기 -> 파일 이름으로 사용하기 위함
					if (idValue.length() == 0) {
						System.out.println("사용자가 입력한 아이디 값이 없습니다!!");
						idTF.setFocusable(true);
						idTF.requestFocus();
						return;
					} // end of if(idValue.length() == 0)

					// 3. 사용자가 입력한 비밀번호를 가져오기
					String pwdValue = pwdTF.getText().trim();
					if (pwdValue.length() == 0) {
						System.out.println("사용자가 비밀번호를 입력하지 않았습니다!!");
						pwdTF.setFocusable(true);
						pwdTF.requestFocus();
						return;
					} // end of if(pwdValue.length() == 0)

// 사용자가 입력한 아이디와 비밀번호를 콘솔 화면에 출력하기
					System.out.println("사용자가 입력한 아이디는 " + idValue + " 입니다.");
					System.out.println("사용자가 입력한 비밀번호는 " + pwdValue + " 입니다.");

// 사용자가 입력한 아이디를 사용해서 File 객체를 만들기
// File 클래스를 사용해서 파일 변수를 선언
					File file = null;
// 파일을 새로 생성해서 파일 안에 사용자가 입력한 비밀번호를 저장하기
					FileOutputStream fos = null;
// Stream -> Character 단위로 바꾸어주는 OutputStreamWriter 클래스를 사용
					OutputStreamWriter osw = null;
// 성능 개선을 하기 위해서 BufferedWriter 클래스를 사용하기
					BufferedWriter bw = null;
					try {
						// 사용자가 입력한 아이디를 사용해서
						// c:/java2_dir/사용자가입력한아이디.properties 파일을 만들기
						file = new File(FILE_PATH + idValue + ".properties");

						// 기존에 파일이 있는 경우에는 다시 파일을 만들지 않습니다!!
						// -> exists( ) 함수를 사용하면 파일이 존재하면 참(true)을 반환
						// -> 파일이 없으면 거짓(false)을 반환
						boolean resultChkFile = file.exists();
						if (resultChkFile == true) {
							System.out.println("파일이 존재하므로 만들지 않습니다!!");
						} else {
							System.out.println("파일이 없으므로 새로 만듭니다!!");

							// createNewFile( ) 함수를 호출해서 파일을 만듭니다!!
							boolean resultNewFile = file.createNewFile();
							if (resultNewFile == true) {
								System.out.println("파일 만들기 성공!!");
								fos = new FileOutputStream(file);
								osw = new OutputStreamWriter(fos);
								bw = new BufferedWriter(osw);

								/*
								 * 1. 프로퍼티스 파일에 비밀번호를 저장하기 Properties 클래스가 갖고 있는 setProperty( ) 함수를 호출 예)
								 * setProperty(키, 값); 키 : 개발자가 정한 값의 또 다른 이름 -> pwd 값 : 사용자가 입력한 비밀번호
								 */
								dlgProperties.setProperty("pwd", pwdValue);

								/*
								 * store( ) 함수를 호출해야 비밀번호가 파일에 저장됩니다!! -> dlgProperties.store(파일 객체 이름, null);
								 * -> null : 주석(Comment)을 삽입하지 않음
								 */
								dlgProperties.store(bw, null);

								System.out.println("새로운 파일을 만들고 ");
								System.out.println("파일 안에 비밀번호를 저장함!!");

							} else {
								System.out.println("파일 만들기 실패!!");
							}
						}
					} catch (IOException ioe) {
						System.out.println("네트워크 관련 예외상황이 발생!!");
						System.out.println("내용은 " + ioe.getMessage());
						ioe.printStackTrace();
					} catch (Exception e2) {
						System.out.println("다른 예외상황이 발생!!");
						System.out.println("내용은 " + e2.getMessage());
						e2.printStackTrace();
					} finally {
						try {

							if (bw != null)
								bw.close();

							if (osw != null)
								osw.close();

							if (fos != null)
								fos.close();

						} catch (IOException e3) {
							System.out.println("메모리 해제시에 예외상황이 발생!!");
							System.out.println("내용은 " + e3.getMessage());
							e3.printStackTrace();
						}
					}

				} // end of if(refButton == saveButton)
			} // end of if(refObject instanceof Button)
		} // end of actionPerformed(ActionEvent)

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			Window window = e.getWindow();
			window.setVisible(false);
			window.dispose();
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

	/*
	 * 사용자가 로그인 버튼을 클릭하는 경우에만 사용할 클래스를 만들기
	 */
	static class MyLoginMemberDialogClass 
	                extends Dialog 
	                implements WindowListener, ActionListener {
		
		/*
		 * 1. 회원에 한해서만 로그인 할 수 있는 기능 구현하기
		 * 
		 * 1) 화면 만들기
		 * 
		 * 가. 타이틀을 화면에 보여주는 Label 객체 만들기
		 * 나. 판넬 컨네이터 객체를 만들기 (그리드 레이이웃 배치 관리자)
		 * 다. 아이디 입력 창 객체 만들기
		 * 라. 비밀번호 입력 창 객체 만들기
		 * 마. 로그인 버튼 만들기
		 */
		private Label dlgLoginTitleLabel = 
				        new Label("***로그인 다이얼로그***", 
				        		Label.CENTER);
		
		// 2개의 입력 창을 갖는 판넬 컨테이너 객체를 만들기
		private Panel dlgLoginPanel = 
							new Panel(new GridLayout(1, 2));
		// -> 1행 2열 표 구조를 갖는 판넬 객체 만들기
		
		// 사용자가 아이디를 입력할 수 있도록 입력 창 객체를 만들기
		private TextField dlgLoginIdTF = 
				  new TextField("아이디를 입력하세요...", 20);
		
		// 사용자가 비밀번호를 입력할 수 있도록 입력 창 객체를 만들기
		private TextField dlgLoginPwdTF = 
					new TextField("비밀번호를 입력하세요...", 20);
		
		// 로그인 버튼 만들기
		private Button  dlgLoginLoginBtn = new Button("로그인");
		
		// 폰트 객체 만들기
		private Font  dlgLoginFont = 
				        new Font("궁서체", Font.BOLD, 20);
		
		// 파일 전체 경로를 상수로 저장하기
		public static final String FILE_PATH2 = 
					"c:/java2_dir/";
		
		// 프로퍼티스 객체를 만들기
		private Properties dlgLoginProperties = new Properties();

		// 생성자 함수를 꼭 넣어주세요!!
		// -> 부모 객체, 타이틀, 모달 여부를 설정해야 하기 때문입니다!!
		public MyLoginMemberDialogClass
		          (Frame parent, String title, boolean modal) {
			super(parent, title, modal);
			
			// 1. 화면에 출력되는 모든 객체들에 폰트를 적용하기
			this.dlgLoginTitleLabel.setFont(this.dlgLoginFont);
			this.dlgLoginIdTF.setFont(this.dlgLoginFont);
			this.dlgLoginPwdTF.setFont(this.dlgLoginFont);
			this.dlgLoginLoginBtn.setFont(this.dlgLoginFont);
			
			// 2. 판넬 컨테이너에 아이디, 비밀번호 입력 창을 넣기
			this.dlgLoginPanel.add(this.dlgLoginIdTF);
			this.dlgLoginPanel.add(this.dlgLoginPwdTF);
			
			// 3. 판넬 컨테이너는 보더레이아웃의 가운데 위치에 넣기
			this.add(this.dlgLoginPanel, BorderLayout.CENTER);
			// 4. 타이틀은 보더레이아웃의 위쪽에 넣기
			this.add(this.dlgLoginTitleLabel, BorderLayout.NORTH);
			// 5. 로그인 버튼은 보더레이아웃의 아래쪽에 넣기
			this.add(this.dlgLoginLoginBtn, BorderLayout.SOUTH);
			
			// 6. 다이얼로그 종료 이벤트 처리
			this.addWindowListener(this);
			
			// 7. 사용자가 버튼을 클릭한 경우의 이벤트 처리
			this.dlgLoginLoginBtn.addActionListener(this);
			
			// 8. selectAll( ) 함수를 호출해서
			// 사용자가 아이디를 입력하기 편하게 하기
			// -> TextField("아이디를 입력하세요...", 20);
			this.dlgLoginIdTF.selectAll();
			this.dlgLoginPwdTF.selectAll();
			
			// 9. 다이얼로그의 크기를 변경하기
			this.setSize(300, 300);
			
			// 10. 다이얼로그를 화면에 출력하기
			this.setVisible(true);
		}
		
		
		// 사용자가 로그인 버튼을 눌렀을 때 실행할 명령어들을 작성하기
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object refObject = e.getSource();
			
			if(refObject instanceof Button) {
				
				Button refButton = (Button)refObject;
				
				if(refButton == dlgLoginLoginBtn) {
					
					System.out.println("사용자가 로그인 버튼을 클릭");					
					
					String idValue = dlgLoginIdTF.getText().trim();
					String pwdValue = dlgLoginPwdTF.getText().trim();
					
					if(idValue.length() == 0) {
System.out.println("아이디를 꼭!! 입력하세요!!");						
dlgLoginIdTF.setFocusable(true);
dlgLoginIdTF.requestFocus();
return;
					} // end of if(idValue.length() == 0) {
					
					if(pwdValue.length() == 0) {
System.out.println("비밀번호를 꼭!! 입력하세요!!");						
/*
 * javax.swing 패키지에는 다이얼로그를 출력해주는 클래스 :
 * JOptionPane 클래스 : showMessageDialog( ) 함수를 호출
 */
javax.swing.JOptionPane.
   showMessageDialog(this, 
     "비밀번호를 입력하세요!!", 
     "다이얼로그 창", javax.swing.JOptionPane.YES_OPTION);
dlgLoginPwdTF.setFocusable(true);
dlgLoginPwdTF.requestFocus();
return;
					} // end of if(pwdValue.length() == 0)
					
// 사용자가 입력한 아이디와 비밀번호를 확인하기 위해서 콘솔 창에 출력
System.out.println("사용자가 입력한 아이디는 " + idValue);					
System.out.println("사용자가 입력한 비밀번호는 " + pwdValue);

/*
 * 1. c:/java2_dir/ 디렉토리에 사용자가 입력한 아이디 이름을 갖는
 *    프로퍼티스 파일이 있는지 여부를 검사하기
 */
File file = null;
FileInputStream fis = null;
InputStreamReader isr = null;
BufferedReader br = null;
try {
	file = new File(FILE_PATH2+idValue+".properties");
	boolean chkFile = file.exists();
	if(chkFile == false) {
		System.out.println("회원 가입을 하지 않았습니다!!");
		System.out.println("회원 가입 후에 다시 시도해 보세요!!");
		return;
	}
	else {
		System.out.println("회원 가입을 하셨습니다!!");
		/*
		 * 프로퍼티스 파일에서 비밀번호를 읽어오기
		 */
		fis = new FileInputStream(file);
		isr = new InputStreamReader(fis);
		br  = new BufferedReader(isr);
		
		/*
		 * Properties 클래스가 갖고 있는 load( ) 함수를 호출해서
		 * 프로그램에서 프로퍼티스 파일의 주소로 이동
		 */
		dlgLoginProperties.load(br);
		
		/*
		 * 비밀번호 값을 가져오려면 getProperty( ) 함수를 호출
		 * -> getProperty(키);
		 */
		String cmpPwd = dlgLoginProperties.getProperty("pwd");
		/*
		 * 사용자가 입력한 비밀번호와 일치하는지를 비교하기
		 */
		if(cmpPwd.equals(pwdValue) == true) {
			System.out.println("로그인 성공!!");
		} else {
			System.out.println("비밀번호가 다릅니다!!");
			System.out.println("로그인 할 수 없습니다!!");
		}
		
	}
} catch(FileNotFoundException fnfe) {
	System.out.println("속성 파일 찾기 실패!!");
} catch(IOException ioe2) {
	System.out.println("네트워크 관련 예외상황 발생!!");
	System.out.println("내용은 "+ioe2.getMessage());
} catch(Exception e2) {
	System.out.println("다른 예외상황 발생!!");
	System.out.println("내용은 "+e2.getMessage());
} finally {
	try {
		if(br != null) br.close();
		if(isr != null) isr.close();
		if(fis != null) fis.close();
	} catch(IOException ioe3) {
		System.out.println("메모리 해제시에 예외상황 발생!!");
		System.out.println("내용은 "+ioe3.getMessage());
	}
}

					
				} // end of if(refButton == dlgLoginLoginBtn)				
			} // end of instanceof Button
		} // end of actionPerformed()

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			Window window = e.getWindow();
			// 화면에서 창을 사라지게 하기
			window.setVisible(false);
			// dispose( ) 함수를 호출하면 메모리에 있는 창을 삭제
			// -> 메모리 해제
			window.dispose();
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
	
	
	@Override
	public void focusGained(FocusEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	// 메인 윈도우의 버튼 클릭 이벤트 처리 함수
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 1. 사용자가 클릭한 버튼 객체의 주소를 가져오기
		Object refObject = e.getSource();

		// 2. 버튼인지를 구별하기
		if (refObject instanceof Button) {

			// 3. 형 변환 연산자를 사용해서 Button 클래스로 바꾸기
			Button refButton = (Button) refObject;

			// 4. 버튼 구별하기 : setActionCommand("이름")
			// -> "이름"을 읽어올 때에는 getActionCommand( ) 함수를 호출
			String commandValue = refButton.getActionCommand();

			// 5. 사용자가 회원가입 버튼을 클릭했는지 여부를 검사
			if (commandValue.equals("reg") == true) {
				System.out.println("사용자가 회원 가입 버튼을 클릭!!");
				MyRegMemberDialogClass refMyRegMemberDialogClass;
				refMyRegMemberDialogClass = new MyRegMemberDialogClass(this, "회원가입 다이얼로그 창", true);
			}
			else if(commandValue.equals("log") == true) {
				System.out.println("사용자가 로그인 버튼을 클릭!!");
MyLoginMemberDialogClass refMyLoginMemberDialogClass;
refMyLoginMemberDialogClass = 
  new MyLoginMemberDialogClass(this, "회원 로그인 다이얼로그", true);
			}
			else if(commandValue.equals("ext") == true) {
System.exit(0);				
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
		System.out.println("메인 윈도우를 종료합니다!!");
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

public class TestMemberLoginClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyMemberLoginFrameClass refMyMemberLoginFrameClass;
		refMyMemberLoginFrameClass = new MyMemberLoginFrameClass("회원 관리 윈도우");
	}
}
