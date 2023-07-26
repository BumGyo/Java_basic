package day20180726;

/*
 * 1. ����� ȸ�� ���� ��� �����
 * 2. ����� ȸ���� ���ؼ��� �α��� ��� �����
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*; // ���� �Ǵ� ��Ʈ��ũ ���� Ŭ������ �������̽��� ���
// Properties Ŭ������ ����ϱ� ���� : ���ο� ������ ���� �� ���
// -> �Ӽ� ������ ���� �� ���
// -> �Ӽ� ������ �⺻ ������ "Ű(key)=��(value)" : 
// -> ������ ���(�׷�) : ���� ������ ����
// -> Ű(key) : ���� ���� �̸�(Name) �Ǵ� ����(Alias)
/*
 * ����ڰ� ȸ�� �����ϴ� ������ �Ӽ� ������ ����ϴ�!!
 * -> c ����̺꿡 ���ο� ���丮(����)�� ����ϴ�!!
 *    : c:\\java2_dir
 * -> ����ڰ� �Է��� ���̵� ���� �̸����� �ϴ� ������ ����
 * ��) ����ڰ� ���̵� id1 ��� �Է��� ��� java2_dir ���丮 �ȿ�
 *     Ȯ���ڰ� properties �� �Ӽ� ������ ����ϴ�!!
 *     ��) id1.properties
 * -> id1.properties ���� �ȿ��� ����ڰ� �Է��� ��й�ȣ��
 *    Ű=�� �������� �����ϰڽ��ϴ�!!
 *    ��) ����ڰ� ��й�ȣ�� 1234�� �Է��� ��쿡��
 *        pwd=1234     
 */
import java.util.*;

/*
 * ȭ�� ����
 * 3���� ��ư�� ���鵵�� �ϰڽ��ϴ�!!
 * 1. ù ��° ��ư : ȸ�� ���� ����� ������ ��ư
 * 2. �� ��° ��ư : ȸ���� ���ؼ� �α��� ����� ������ ��ư
 * 3. �� ��° ��ư : ���α׷� ���� ����� ������ ��ư
 * 4. ����ڰ� ù ��° ��ư�� Ŭ���ϸ� ȭ�鿡�� ȸ�� ���� â�� ���
 * 5. ����ڰ� �� ��° ��ư�� Ŭ���ϸ� ȭ�鿡�� ȸ�� �α��� â�� ���
 * 6. ����ڰ� �� ��° ��ư�� Ŭ���ϸ� ���α׷� ��ü�� ���� : 
 *    â�� ������� �մϴ�!! 
 * 
 */
// Frame Ŭ������ ����ؼ� ȭ�鿡 �ϳ��� â�� ���
// �̺�Ʈ ó���� ���ؼ� WindowListener �������̽�(������ ���� �̺�Ʈ), 
// ActionListener �������̽�(��ư Ŭ�� �̺�Ʈ), 
// FocusListener �������̽��� ���(TextField���� ����ڰ� ���𰡸� �Է�)
class MyMemberLoginFrameClass extends Frame implements WindowListener, ActionListener, FocusListener {

	/*
	 * ���� �����쿡 ����� 3���� ��ư�� ���α׷� ����(Ÿ��Ʋ)�� ������ �� ����� ��ü���� �����մϴ�!!
	 */
	// ������ �����ִ� Label ��ü�� ����ϴ�!!
	private Label titleLabel = new Label("***ȸ�� ���� ����***", Label.CENTER);

	// ȸ�� ���� â�� ȭ�鿡 ������ִ� ��ư ��ü�� �����մϴ�!!
	private Button regMemberButton = new Button("ȸ�� ����");
	// reg : registration(���� �Ǵ� ����)

	// �α��� â�� ȭ�鿡 ������ִ� ��ư ��ü�� ����
	private Button loginButton = new Button("�α���");

	// ���α׷� ���� ��ư ��ü�� ����
	private Button exitButton = new Button("���α׷� ����");

	/*
	 * ������ ���� �� ��ü�� BorderLayout ��ġ �������� ���� ���⿡ ���
	 * 
	 * 3���� ��ư�� ��� ��ġ�� ��� : Panel �����̳ʿ� GridLayout ���� ����� ������ִ� ��ġ �����ڸ� ��� -> 1�� 3��
	 * ǥ ������ ���� ������ ǥ �ȿ� 3���� ��ư�� ���ʴ�� �ְڽ��ϴ�!!
	 */
	private GridLayout gridLayout = new GridLayout(1, 3);
	private Panel panel = new Panel(gridLayout);

	// ���� ũ�⸦ �ٲ� �� ����� Font ��ü�� ����
	private Font font = new Font("�ü�ü", Font.BOLD, 20);

	/*
	 * ���� �������� ��ġ�� �������ִ� �Լ��� ����� -> �� �Լ��� ������� ������ ���� ��� �� x ��ǥ�� 0, y ��ǥ�� 0 ��ġ�� ���
	 * -> x = 0, y = 0 -> ���� ȭ���� ��� ��ġ�� �ٲٰ��� �մϴ�!!
	 * 
	 * -> �Ű� ������ �ʿ� : ���� �������� ���� ũ�� : int width ���� �������� ���� ũ�� : int height
	 * 
	 * -> ��ȯ�� : Point Ŭ������ ��� : x��ǥ y��ǥ ���� ��������� ���� �ִ� Ŭ����
	 */
	public static Point getMyWindowPoint(int width, int height) {
		Point resultPoint = null;

		/*
		 * 1. ���� ȭ���� ��ü ũ�⸦ ���ϱ� : �ػ� ���ϱ� -> ���� ����� Dimension Ŭ���� : �簢�� �������� ���� ũ��� ����
		 * ũ�⸦ ���� Ŭ���� -> ���� ũ�� : int width -> ���� ũ�� : int height
		 */
		Dimension refScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

		System.out.println("ȭ�� ��ü ũ�⸦ ���߽��ϴ�!!");
		System.out.println("���� ũ��� " + refScreenSize.width);
		System.out.println("���� ũ��� " + refScreenSize.height);

		/*
		 * ȭ�鿡 ����� �������� ���� ��� �𼭸��� ��ǥ�� ���ϴ� ����
		 */
		int x = 0;
		int y = 0;

		x = refScreenSize.width / 2 - width / 2;
		y = refScreenSize.height / 2 - height / 2;

		System.out.println("ȭ�鿡 ����� �������� ���� ��� ");
		System.out.println("�𼭸��� x ��ǥ�� " + x);
		System.out.println("�𼭸��� y ��ǥ�� " + y);

		// ������ ���� x,y ��ǥ ���� Point Ŭ������ �����ϱ�
		// -> new Point(x, y) ��ɾ �ۼ�
		resultPoint = new Point(x, y);

		// �Լ��� ȣ���� Ŭ������ x��ǥ�� y��ǥ ���� �����ϱ�
		return resultPoint;
	} // end of method

	// ȭ�鿡 �����츦 ������ִ� ������ �Լ��� ����ϴ�!!
	public MyMemberLoginFrameClass(String title) {
		// super : �θ� Ŭ������ �ּҸ� ���� �ִ� Ŭ���� �ڵ� ����
		// -> super( ) : �θ� Ŭ������ �ִ� ������ �Լ��� ȣ��
		super(title);

		// ������ ���� ��Ʈ ��ü�� �󺧰� 3���� ��ư�� �����ϱ�
		titleLabel.setFont(font);
		regMemberButton.setFont(font);
		loginButton.setFont(font);
		exitButton.setFont(font);

		// 3���� ��ư�� ���ʴ�� �ǳ� �����̳ʿ� �ֱ�
		panel.add(regMemberButton);
		panel.add(loginButton);
		panel.add(exitButton);

		// �ǳ� �����̳ʴ� ���� �������� ��� ��ġ�� �ֱ�
		add(panel, BorderLayout.CENTER);

		// Ÿ��Ʋ�� �����ִ� ���� ���� �������� ���� ���⿡ �ֱ�
		add(titleLabel, BorderLayout.NORTH);

		// �̺�Ʈ ó�� ��ɾ���� �ۼ��ϱ�
		this.addWindowListener(this);

		// ��ư�� ��ɾ ������ �� �ִ� ���ڿ��� ���� ���� ������
		// ������ ��ư�� ���ڿ��� �����ϱ�
		/*
		 * �Ϲ����� ����� ����ڰ� Ŭ���� ��ư�� �ּҸ� �о����
		 * 
		 */
		// ��ư ��ü ���� ���� �ִ� setActionCommand( ) �Լ��� ȣ���ؼ�
		// ��ư ���� ���ο� ���ڿ� ���� �����ϱ�
		// -> ����ڰ� Ŭ���� ��ư�� ������ �� ����� ��
		regMemberButton.setActionCommand("reg");
		loginButton.setActionCommand("log");
		exitButton.setActionCommand("ext");

		// 3���� ��ư ���� addActionListener( ) �Լ��� ȣ���ϱ�
		regMemberButton.addActionListener(this);
		loginButton.addActionListener(this);
		exitButton.addActionListener(this);

		// ������ ���� getMyWindowPoint( ) �Լ��� ȣ���ؼ�
		// -> ȭ�鿡 ����� �������� ���� ��� ��ǥ ���� ���ϱ�
		// 1) pack( ) �Լ��� ���� ȣ���ؾ� �մϴ�!! : ��������
		// ũ�⸦ ���ϱ� ����
		this.pack();

		System.out.println("ȭ�鿡 ����� �������� ũ�⸦ ���");
		System.out.println("�������� ����ũ��� " + getWidth());
		System.out.println("�������� ����ũ��� " + getHeight());

		// ������ ������� getMyWindowPoint( ) �Լ��� ȣ��
		Point refPoint = getMyWindowPoint(getWidth(), getHeight());

		// setLocation( ) �Լ��� ȣ���ؾ� �������� ���� ���
		// �𼭸��� ��ǥ ���� �ٲ� �� �ֽ��ϴ�!!
		this.setLocation(refPoint);

		// setResizable( ) �Լ��� ȣ���ؼ� �������� ũ�⸦
		// �ٲ��� ���ϵ��� �ϱ�
		this.setResizable(false);

		// �����츦 ȭ�鿡 ����ϱ�
		this.setVisible(true);
	} // end of ������ �Լ�

	/*
	 * ����ڰ� ȸ�� ���� ��ư�� Ŭ���ϴ� ��쿡�� ����� Ŭ������ �����
	 */
	static class MyRegMemberDialogClass extends Dialog implements WindowListener, ActionListener {

		// �������� ������ ����� �� ��ü�� ����ϴ�!!
		private Label dlgTitleLabel = new Label("ȸ�� ���� â", Label.CENTER);

		// ����ڰ� ���̵� �Է��� �� �ֵ��� TextField ��ü�� �����
		private TextField idTF = new TextField("���̵� �Է��ϼ���...", 30);

		// ����ڰ� ��й�ȣ�� �Է��� �� �ֵ��� TextField ��ü�� �����
		private TextField pwdTF = new TextField("��й�ȣ�� �Է��ϼ���...", 30);

		// ���� ��ư�� ����ϴ�!! -> ����ڰ� Ŭ���ϸ�
		// ����ڰ� �Է��� ���̵� ������ �Ӽ� ������ ���� ����
		private Button saveButton = new Button("����");

		/*
		 * ���� �� ���� ��� : BorderLayout.NORTH ���̵�� ��й�ȣ �Է� â�� �ǳ� �����̳ʿ� ���� ������ ���� ���̾ƿ��� ���
		 * ��ġ�� ��� : BorderLayout.CENTER ���� ��ư�� ��ġ�� �Ʒ� �� : BorderLayout.SOUTH
		 */

		// ��Ʈ ��ü�� �����մϴ�!! -> ���� ũ��� 15�� ����
		private Font dlgFont = new Font("�ü�ü", Font.BOLD, 15);

		private Panel dlgPanel = new Panel(new GridLayout(1, 2));

		// �Ӽ� ������ ���� �� ����� Properties ��ü�� ����
		private Properties dlgProperties = new Properties();

		// ���� ���� �Ӽ� ������ ��ü ���(���� ���)�� ����� ����
		public static final String FILE_PATH = "c:/java2_dir/";
		// "c:\\java2_dir\\"
		// -> c:/java2_dir/����ڰ��Է��Ѿ��̵�.properties

		// ������ �Լ��� ���� Dialog Ŭ������ �θ� Ŭ������
		// ������ �־�� �մϴ�!!
		public MyRegMemberDialogClass(Frame parent, String title, boolean modal) {
			super(parent, title, modal);
			/*
			 * parent : Dialog Ŭ������ �θ� ������(â) title : Dialog â�� ���� ��ܿ� ����� ���ڿ� modal : ���
			 * ���θ� ���� �Ű� ����
			 * 
			 * ������(â) ���� ������ �θ� ������(Frame) -> �ڽ� ������(Dialog)
			 * 
			 * modal �Ű� ������ ���� �����κ��� ��(true) �Ǵ� ����(false) �� �߿��� �ϳ��� ���� ���� ����
			 * 
			 * ���� ���� ������ ��� â�� �Ǿ �ٽ� �θ� â���� �̵��ϱ� ���ؼ��� ���� ȭ�鿡 ��µǰ� �ִ� �ڽ� â�� �ݾƾ� �մϴ�!!
			 * 
			 * ���� ������ ������ ��� ���� â�� �Ǿ ���� �ڽ� â�� ����� ���¿��� �����Ӱ� �θ� â���� �̵��� �� �ֽ��ϴ�!!
			 */

			// ���� ���� �ǳ� �����̳� ��ü�� ���̵� �Է� â��
			// ��� ��ȣ �Է� â�� ���ʴ�� �ֱ�
			dlgPanel.add(idTF);
			dlgPanel.add(pwdTF);

			// �ǳ� �����̳��� ��ġ�� ���� ���̾ƿ��� ��� ��ġ
			add(dlgPanel, BorderLayout.CENTER);

			// Ÿ��Ʋ�� �� ���� ���
			add(dlgTitleLabel, BorderLayout.NORTH);

			// ���� ��ư�� �Ʒ� �ʿ� ���
			add(saveButton, BorderLayout.SOUTH);

			dlgTitleLabel.setFont(dlgFont);
			idTF.setFont(dlgFont);
			pwdTF.setFont(dlgFont);
			saveButton.setFont(dlgFont);

			// �̺�Ʈ : ������ ���� �̺�Ʈ
			this.addWindowListener(this);

			// ��ư Ŭ�� �̺�Ʈ
			saveButton.addActionListener(this);

			// ���̾�α� â�� ũ�⸦ ���� ũ��� 300, ���� ũ���
			// 300���� �����ϱ�
			setSize(300, 300);
			setVisible(true);
		}

		// ����ڰ� saveButton ��ư�� ������ �� ������ ��ɾ����
		// �Է�
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 1. ����ڰ� Ŭ���� ��ư�� �ּҸ� ��������
			Object refObject = e.getSource();

			// 2. ��ư ������ �Ǵ��ϱ�
			if (refObject instanceof Button) {
				System.out.println("����ڰ� ��ư�� Ŭ��!!");
				// 3. �� ��ȯ �����ڸ� ����ؼ�
				// Object Ŭ���� ���� ��ü�� Button Ŭ���� ������
				// �ٲߴϴ�!!
				Button refButton = (Button) refObject;

				// 4. ��ư �����ϱ�
				if (refButton == saveButton) {
					System.out.println("���� ��ư�� Ŭ��!!");

					/*
					 * 1. ����ڰ� �Է��� ���̵� ���� ��������
					 */
					String idValue = idTF.getText().trim();
					// ��) "id1"
					// ��) " id1"

					// 2. ����ڰ� ���̵� ���� �Է��ߴ��� ���θ�
					// �˻��ϱ� -> ���� �̸����� ����ϱ� ����
					if (idValue.length() == 0) {
						System.out.println("����ڰ� �Է��� ���̵� ���� �����ϴ�!!");
						idTF.setFocusable(true);
						idTF.requestFocus();
						return;
					} // end of if(idValue.length() == 0)

					// 3. ����ڰ� �Է��� ��й�ȣ�� ��������
					String pwdValue = pwdTF.getText().trim();
					if (pwdValue.length() == 0) {
						System.out.println("����ڰ� ��й�ȣ�� �Է����� �ʾҽ��ϴ�!!");
						pwdTF.setFocusable(true);
						pwdTF.requestFocus();
						return;
					} // end of if(pwdValue.length() == 0)

// ����ڰ� �Է��� ���̵�� ��й�ȣ�� �ܼ� ȭ�鿡 ����ϱ�
					System.out.println("����ڰ� �Է��� ���̵�� " + idValue + " �Դϴ�.");
					System.out.println("����ڰ� �Է��� ��й�ȣ�� " + pwdValue + " �Դϴ�.");

// ����ڰ� �Է��� ���̵� ����ؼ� File ��ü�� �����
// File Ŭ������ ����ؼ� ���� ������ ����
					File file = null;
// ������ ���� �����ؼ� ���� �ȿ� ����ڰ� �Է��� ��й�ȣ�� �����ϱ�
					FileOutputStream fos = null;
// Stream -> Character ������ �ٲپ��ִ� OutputStreamWriter Ŭ������ ���
					OutputStreamWriter osw = null;
// ���� ������ �ϱ� ���ؼ� BufferedWriter Ŭ������ ����ϱ�
					BufferedWriter bw = null;
					try {
						// ����ڰ� �Է��� ���̵� ����ؼ�
						// c:/java2_dir/����ڰ��Է��Ѿ��̵�.properties ������ �����
						file = new File(FILE_PATH + idValue + ".properties");

						// ������ ������ �ִ� ��쿡�� �ٽ� ������ ������ �ʽ��ϴ�!!
						// -> exists( ) �Լ��� ����ϸ� ������ �����ϸ� ��(true)�� ��ȯ
						// -> ������ ������ ����(false)�� ��ȯ
						boolean resultChkFile = file.exists();
						if (resultChkFile == true) {
							System.out.println("������ �����ϹǷ� ������ �ʽ��ϴ�!!");
						} else {
							System.out.println("������ �����Ƿ� ���� ����ϴ�!!");

							// createNewFile( ) �Լ��� ȣ���ؼ� ������ ����ϴ�!!
							boolean resultNewFile = file.createNewFile();
							if (resultNewFile == true) {
								System.out.println("���� ����� ����!!");
								fos = new FileOutputStream(file);
								osw = new OutputStreamWriter(fos);
								bw = new BufferedWriter(osw);

								/*
								 * 1. ������Ƽ�� ���Ͽ� ��й�ȣ�� �����ϱ� Properties Ŭ������ ���� �ִ� setProperty( ) �Լ��� ȣ�� ��)
								 * setProperty(Ű, ��); Ű : �����ڰ� ���� ���� �� �ٸ� �̸� -> pwd �� : ����ڰ� �Է��� ��й�ȣ
								 */
								dlgProperties.setProperty("pwd", pwdValue);

								/*
								 * store( ) �Լ��� ȣ���ؾ� ��й�ȣ�� ���Ͽ� ����˴ϴ�!! -> dlgProperties.store(���� ��ü �̸�, null);
								 * -> null : �ּ�(Comment)�� �������� ����
								 */
								dlgProperties.store(bw, null);

								System.out.println("���ο� ������ ����� ");
								System.out.println("���� �ȿ� ��й�ȣ�� ������!!");

							} else {
								System.out.println("���� ����� ����!!");
							}
						}
					} catch (IOException ioe) {
						System.out.println("��Ʈ��ũ ���� ���ܻ�Ȳ�� �߻�!!");
						System.out.println("������ " + ioe.getMessage());
						ioe.printStackTrace();
					} catch (Exception e2) {
						System.out.println("�ٸ� ���ܻ�Ȳ�� �߻�!!");
						System.out.println("������ " + e2.getMessage());
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
							System.out.println("�޸� �����ÿ� ���ܻ�Ȳ�� �߻�!!");
							System.out.println("������ " + e3.getMessage());
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
	 * ����ڰ� �α��� ��ư�� Ŭ���ϴ� ��쿡�� ����� Ŭ������ �����
	 */
	static class MyLoginMemberDialogClass 
	                extends Dialog 
	                implements WindowListener, ActionListener {
		
		/*
		 * 1. ȸ���� ���ؼ��� �α��� �� �� �ִ� ��� �����ϱ�
		 * 
		 * 1) ȭ�� �����
		 * 
		 * ��. Ÿ��Ʋ�� ȭ�鿡 �����ִ� Label ��ü �����
		 * ��. �ǳ� �������� ��ü�� ����� (�׸��� �����̿� ��ġ ������)
		 * ��. ���̵� �Է� â ��ü �����
		 * ��. ��й�ȣ �Է� â ��ü �����
		 * ��. �α��� ��ư �����
		 */
		private Label dlgLoginTitleLabel = 
				        new Label("***�α��� ���̾�α�***", 
				        		Label.CENTER);
		
		// 2���� �Է� â�� ���� �ǳ� �����̳� ��ü�� �����
		private Panel dlgLoginPanel = 
							new Panel(new GridLayout(1, 2));
		// -> 1�� 2�� ǥ ������ ���� �ǳ� ��ü �����
		
		// ����ڰ� ���̵� �Է��� �� �ֵ��� �Է� â ��ü�� �����
		private TextField dlgLoginIdTF = 
				  new TextField("���̵� �Է��ϼ���...", 20);
		
		// ����ڰ� ��й�ȣ�� �Է��� �� �ֵ��� �Է� â ��ü�� �����
		private TextField dlgLoginPwdTF = 
					new TextField("��й�ȣ�� �Է��ϼ���...", 20);
		
		// �α��� ��ư �����
		private Button  dlgLoginLoginBtn = new Button("�α���");
		
		// ��Ʈ ��ü �����
		private Font  dlgLoginFont = 
				        new Font("�ü�ü", Font.BOLD, 20);
		
		// ���� ��ü ��θ� ����� �����ϱ�
		public static final String FILE_PATH2 = 
					"c:/java2_dir/";
		
		// ������Ƽ�� ��ü�� �����
		private Properties dlgLoginProperties = new Properties();

		// ������ �Լ��� �� �־��ּ���!!
		// -> �θ� ��ü, Ÿ��Ʋ, ��� ���θ� �����ؾ� �ϱ� �����Դϴ�!!
		public MyLoginMemberDialogClass
		          (Frame parent, String title, boolean modal) {
			super(parent, title, modal);
			
			// 1. ȭ�鿡 ��µǴ� ��� ��ü�鿡 ��Ʈ�� �����ϱ�
			this.dlgLoginTitleLabel.setFont(this.dlgLoginFont);
			this.dlgLoginIdTF.setFont(this.dlgLoginFont);
			this.dlgLoginPwdTF.setFont(this.dlgLoginFont);
			this.dlgLoginLoginBtn.setFont(this.dlgLoginFont);
			
			// 2. �ǳ� �����̳ʿ� ���̵�, ��й�ȣ �Է� â�� �ֱ�
			this.dlgLoginPanel.add(this.dlgLoginIdTF);
			this.dlgLoginPanel.add(this.dlgLoginPwdTF);
			
			// 3. �ǳ� �����̳ʴ� �������̾ƿ��� ��� ��ġ�� �ֱ�
			this.add(this.dlgLoginPanel, BorderLayout.CENTER);
			// 4. Ÿ��Ʋ�� �������̾ƿ��� ���ʿ� �ֱ�
			this.add(this.dlgLoginTitleLabel, BorderLayout.NORTH);
			// 5. �α��� ��ư�� �������̾ƿ��� �Ʒ��ʿ� �ֱ�
			this.add(this.dlgLoginLoginBtn, BorderLayout.SOUTH);
			
			// 6. ���̾�α� ���� �̺�Ʈ ó��
			this.addWindowListener(this);
			
			// 7. ����ڰ� ��ư�� Ŭ���� ����� �̺�Ʈ ó��
			this.dlgLoginLoginBtn.addActionListener(this);
			
			// 8. selectAll( ) �Լ��� ȣ���ؼ�
			// ����ڰ� ���̵� �Է��ϱ� ���ϰ� �ϱ�
			// -> TextField("���̵� �Է��ϼ���...", 20);
			this.dlgLoginIdTF.selectAll();
			this.dlgLoginPwdTF.selectAll();
			
			// 9. ���̾�α��� ũ�⸦ �����ϱ�
			this.setSize(300, 300);
			
			// 10. ���̾�α׸� ȭ�鿡 ����ϱ�
			this.setVisible(true);
		}
		
		
		// ����ڰ� �α��� ��ư�� ������ �� ������ ��ɾ���� �ۼ��ϱ�
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object refObject = e.getSource();
			
			if(refObject instanceof Button) {
				
				Button refButton = (Button)refObject;
				
				if(refButton == dlgLoginLoginBtn) {
					
					System.out.println("����ڰ� �α��� ��ư�� Ŭ��");					
					
					String idValue = dlgLoginIdTF.getText().trim();
					String pwdValue = dlgLoginPwdTF.getText().trim();
					
					if(idValue.length() == 0) {
System.out.println("���̵� ��!! �Է��ϼ���!!");						
dlgLoginIdTF.setFocusable(true);
dlgLoginIdTF.requestFocus();
return;
					} // end of if(idValue.length() == 0) {
					
					if(pwdValue.length() == 0) {
System.out.println("��й�ȣ�� ��!! �Է��ϼ���!!");						
/*
 * javax.swing ��Ű������ ���̾�α׸� ������ִ� Ŭ���� :
 * JOptionPane Ŭ���� : showMessageDialog( ) �Լ��� ȣ��
 */
javax.swing.JOptionPane.
   showMessageDialog(this, 
     "��й�ȣ�� �Է��ϼ���!!", 
     "���̾�α� â", javax.swing.JOptionPane.YES_OPTION);
dlgLoginPwdTF.setFocusable(true);
dlgLoginPwdTF.requestFocus();
return;
					} // end of if(pwdValue.length() == 0)
					
// ����ڰ� �Է��� ���̵�� ��й�ȣ�� Ȯ���ϱ� ���ؼ� �ܼ� â�� ���
System.out.println("����ڰ� �Է��� ���̵�� " + idValue);					
System.out.println("����ڰ� �Է��� ��й�ȣ�� " + pwdValue);

/*
 * 1. c:/java2_dir/ ���丮�� ����ڰ� �Է��� ���̵� �̸��� ����
 *    ������Ƽ�� ������ �ִ��� ���θ� �˻��ϱ�
 */
File file = null;
FileInputStream fis = null;
InputStreamReader isr = null;
BufferedReader br = null;
try {
	file = new File(FILE_PATH2+idValue+".properties");
	boolean chkFile = file.exists();
	if(chkFile == false) {
		System.out.println("ȸ�� ������ ���� �ʾҽ��ϴ�!!");
		System.out.println("ȸ�� ���� �Ŀ� �ٽ� �õ��� ������!!");
		return;
	}
	else {
		System.out.println("ȸ�� ������ �ϼ̽��ϴ�!!");
		/*
		 * ������Ƽ�� ���Ͽ��� ��й�ȣ�� �о����
		 */
		fis = new FileInputStream(file);
		isr = new InputStreamReader(fis);
		br  = new BufferedReader(isr);
		
		/*
		 * Properties Ŭ������ ���� �ִ� load( ) �Լ��� ȣ���ؼ�
		 * ���α׷����� ������Ƽ�� ������ �ּҷ� �̵�
		 */
		dlgLoginProperties.load(br);
		
		/*
		 * ��й�ȣ ���� ���������� getProperty( ) �Լ��� ȣ��
		 * -> getProperty(Ű);
		 */
		String cmpPwd = dlgLoginProperties.getProperty("pwd");
		/*
		 * ����ڰ� �Է��� ��й�ȣ�� ��ġ�ϴ����� ���ϱ�
		 */
		if(cmpPwd.equals(pwdValue) == true) {
			System.out.println("�α��� ����!!");
		} else {
			System.out.println("��й�ȣ�� �ٸ��ϴ�!!");
			System.out.println("�α��� �� �� �����ϴ�!!");
		}
		
	}
} catch(FileNotFoundException fnfe) {
	System.out.println("�Ӽ� ���� ã�� ����!!");
} catch(IOException ioe2) {
	System.out.println("��Ʈ��ũ ���� ���ܻ�Ȳ �߻�!!");
	System.out.println("������ "+ioe2.getMessage());
} catch(Exception e2) {
	System.out.println("�ٸ� ���ܻ�Ȳ �߻�!!");
	System.out.println("������ "+e2.getMessage());
} finally {
	try {
		if(br != null) br.close();
		if(isr != null) isr.close();
		if(fis != null) fis.close();
	} catch(IOException ioe3) {
		System.out.println("�޸� �����ÿ� ���ܻ�Ȳ �߻�!!");
		System.out.println("������ "+ioe3.getMessage());
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
			// ȭ�鿡�� â�� ������� �ϱ�
			window.setVisible(false);
			// dispose( ) �Լ��� ȣ���ϸ� �޸𸮿� �ִ� â�� ����
			// -> �޸� ����
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

	// ���� �������� ��ư Ŭ�� �̺�Ʈ ó�� �Լ�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// 1. ����ڰ� Ŭ���� ��ư ��ü�� �ּҸ� ��������
		Object refObject = e.getSource();

		// 2. ��ư������ �����ϱ�
		if (refObject instanceof Button) {

			// 3. �� ��ȯ �����ڸ� ����ؼ� Button Ŭ������ �ٲٱ�
			Button refButton = (Button) refObject;

			// 4. ��ư �����ϱ� : setActionCommand("�̸�")
			// -> "�̸�"�� �о�� ������ getActionCommand( ) �Լ��� ȣ��
			String commandValue = refButton.getActionCommand();

			// 5. ����ڰ� ȸ������ ��ư�� Ŭ���ߴ��� ���θ� �˻�
			if (commandValue.equals("reg") == true) {
				System.out.println("����ڰ� ȸ�� ���� ��ư�� Ŭ��!!");
				MyRegMemberDialogClass refMyRegMemberDialogClass;
				refMyRegMemberDialogClass = new MyRegMemberDialogClass(this, "ȸ������ ���̾�α� â", true);
			}
			else if(commandValue.equals("log") == true) {
				System.out.println("����ڰ� �α��� ��ư�� Ŭ��!!");
MyLoginMemberDialogClass refMyLoginMemberDialogClass;
refMyLoginMemberDialogClass = 
  new MyLoginMemberDialogClass(this, "ȸ�� �α��� ���̾�α�", true);
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
		System.out.println("���� �����츦 �����մϴ�!!");
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
		refMyMemberLoginFrameClass = new MyMemberLoginFrameClass("ȸ�� ���� ������");
	}
}
