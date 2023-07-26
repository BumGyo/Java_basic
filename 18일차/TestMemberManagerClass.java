
/*
 * ȸ�� ���� ������ ����ϴ�!!
 * 
 * 1. ȭ�鿡 ���α׷� ����� ��ư���� ����ϱ�
 */
import java.awt.*;
import java.awt.event.*;
// JOptionPane Ŭ������ ����ϱ� ���ؼ��� javax.swing ��Ű���� �ֽ��ϴ�!!
import javax.swing.JOptionPane;
// �����ͺ��̽� ���õ� Ŭ������ �������̽����� ����ϱ� ����
import java.sql.*;

// ȭ�鿡 �����츦 ����ϰ� ���α׷� ����� ��ư����
// �����ִ� Ŭ������ ����ϴ�!!
class TestMemberManagerFrameClass extends Frame implements WindowListener, ActionListener {

	// ��� ���̾�α� â���� �Բ� ����� �� �ִ�
	// ȸ�� ���̵� ������ִ� Ŭ���� ������ ����
	private static int s_member_id = 1;

	// ����Ŭ �����ͺ��̽� ����(����) ������ �����ϴ�
	// ������ ����
	private static Connection s_refOracleConnection = null;

	// ���� ������ ���� ���� : ���� ����Ŭ �����ͺ��̽���
	// ���� ���̸� ��(rue) ���� ������ �ְ�,
	// ���� ���� �ƴ϶�� ����(false) ���� ���� ������ ����
	private static boolean s_isOracleConnected = false;

	// 1. ���α׷� ������ ȭ�鿡 ������ִ� �� ��ü
	private Label titleLabel = new Label("***ȸ�� ���� ����***", Label.CENTER);

	// 2. �����ͺ��̽��� ȸ�� ������ �����ϴ� ��ư
	private Button saveMemberInfoButton = new Button("ȸ�� ���� ����");

	// 3. �����ͺ��̽��� ����Ǿ� �ִ� ��� ȸ�� ��������
	// ȭ�鿡 �����ִ� ��ư
	private Button showMemberInfoButton = new Button("ȸ�� ���� ���");

	// 4. �����ͺ��̽��� ����Ǿ� �ִ� Ư�� ȸ��
	// ������ �˻��ϴ� ��ư
	private Button searchMemberInfoButton = new Button("ȸ�� ���� �˻�");

	// 5. �����ͺ��̽��� ����Ǿ� �ִ� Ư�� ȸ��
	// ������ �����ϴ� ��ư
	private Button modifyMemberInfoButton = new Button("ȸ�� ���� ����");

	// 6. �����ͺ��̽��� ����Ǿ� �ִ� Ư�� ȸ��
	// ������ �����ϴ� ��ư
	private Button deleteMemberInfoButton = new Button("ȸ�� ���� ����");

	// 7. ���α׷��� �����ϴ� ��ư
	private Button exitProgramButton = new Button("���α׷� ����");

	// 8. ������ ���� 6���� ��ư���� ȭ�鿡 ������ ��
	// ��ư�� ��ġ�� ũ�⸦ �����ִ� GridLayout
	// ��ü�� ����
	private GridLayout gridLayout = new GridLayout(3, 2);

	// 9. ������ ���� GridLayout ��ġ ������ ��ü��
	// ����ϴ� Panel �����̳� ��ü�� ����
	private Panel panel = new Panel(gridLayout);

	// 10. ��Ʈ ��ü�� �����
	private Font font = new Font("�ü�ü", Font.BOLD, 20);

	// static ���� ���� ���� ����
	// ����Ŭ �����ͺ��̽��� �����ؼ� ����Ŭ �����ͺ��̽���
	// �α����� �õ��ϴ� ��ɾ �ۼ�
	static {
		System.out.println("***static ��***");

		// ����Ŭ �����ͺ��̽��� ����(�α���)�� �õ��ϱ�
		// 1. ����Ŭ ����̹� Ŭ������ �޸𸮿� ����
		// 2. ����Ŭ �����ͺ��̽��� ����(����) �õ��ϱ�
		try {
			System.out.println("1. ����Ŭ ����̹� Ŭ������ �޸𸮿� ����");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("2. ����Ŭ ����̹� Ŭ������ �޸𸮿� �����߽��ϴ�!!");
			System.out.println("3. ����Ŭ �����ͺ��̽��� ����(����)�� �õ��ϱ�");
			s_refOracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test_user1",
					"1234");
			System.out.println("4. ����Ŭ �����ͺ��̽� ����(����) ����!!");
			s_isOracleConnected = true;
		} catch (ClassNotFoundException cnfe) {
// ����Ŭ ����̹� Ŭ������ ã�� ���� ��쿡�� �����
// ��ɾ �ۼ�
			System.out.println("����Ŭ ����̹� Ŭ���� ã�� ����");
		} catch (SQLException sqle) {
// ����Ŭ �����ͺ��̽��� ���ӿ� ������ ��쿡 ������ ��ɾ�			
			System.out.println("����Ŭ �����ͺ��̽��� ���� ����");
			System.out.println("������ " + sqle.getMessage());
		}
	} // end of static { }

	// ����Ŭ �����ͺ��̽��� ���ο� ȸ�� ������ �������ִ�
	// �Լ��� ����ϴ�!!
	/*
	 * �ʿ��� ȸ�� ���� : �̸�(���ڿ�: String), ����(���ڿ�: String), ����(���� : int) -> �Ű� ������ ���� ->
	 * (String nameValue, String genderValue, int ageValue)
	 * 
	 * ��ȯ�� : boolean : ���������� ����Ŭ�����ͺ��̽��� ȸ�� ������ ����Ǹ� ��(true)�� ��ȯ ������ ���� ��쿡��
	 * ����(false)�� ��ȯ
	 */
	public static boolean insertMethod(String nameValue, String genderValue, int ageValue) {
		boolean result = false;
		// insert sql ��ɾ ���ڿ��� �����ϴ� ���� ����
		String insertSql = "insert into tbl_member values(?,?,?,?)";
		// insert into ���ڿ� ��ɾ ���� �޸𸮿� ����
		// -> ���ڿ� �����͸� ��üȭ
		// -> PreparedStatement �������̽��� ���
		PreparedStatement preparedStatement = null;
		// insert into sql ��ɾ��� ���� ����� ������ ����
		// -> �����ͺ��̽��� �߰��� ���ڵ�(��)�� ����
		int resultInsertSql = 0;

		try {

			// 1. ����Ŭ �����ͺ��̽��� ���� ������
			// ���θ� ���� ������ ���ؼ� �����ϱ�
			if (s_isOracleConnected == false) {
				System.out.println("����Ŭ �����ͺ��̽��� ���� ���� �ƴ�");
				System.out.println("���� �Լ��� �����ϱ�!!");
				return false;
			}
			System.out.println("���� ����Ŭ �����ͺ��̽��� ���� ��");
// �޸𸮿� PreparedStatement ������ �����ϱ�
// -> ���ؼ� ������ ���� �ִ� prepareStatement( ) �Լ��� ȣ��
			preparedStatement = s_refOracleConnection.prepareStatement(insertSql);
// insert into ��ɾ �־��� ����ǥ(?) ��ġ�� ������
// ���ʴ�� �־� �ּž� �մϴ�!!
// -> ���� : create table tbl_member : �÷��� ������ ��ġ
// -> ����ǥ(?) ��ȣ�� ��ġ ��ȣ�� ù ��°�� 1, �� ��°�� 2, �� ��°�� 3, �� ��°�� 4
// -> ȸ�� ���̵� ���� ���� �̹Ƿ� preparedStatement
// -> ������ ���� �ִ� setInt(����ǥ�� ��ġ ��ȣ, ���̵�);
			preparedStatement.setInt(1, s_member_id);
// -> ȸ�� �̸��� ���ڿ� ������ -> setString(��ġ ��ȣ, �̸�)
			preparedStatement.setString(2, nameValue);
// -> ȸ�� ������ ���ڿ� ������ -> setString(��ġ ��ȣ, ����)
			preparedStatement.setString(3, genderValue);
// -> ȸ�� ���̴� ������ ������ -> setInt(��ġ ��ȣ, ����)
			preparedStatement.setInt(4, ageValue);
// insert into sql ��ɾ��� ������ executeUpdate() �Լ��� ȣ��
			resultInsertSql = preparedStatement.executeUpdate();
// ������(������)���� �����ͺ��̽��� ���ο� ȸ�� ������
// ����Ǿ������� �˻��ϱ� -> �ϳ��� ���ڵ常 ����
			if (resultInsertSql == 1) {
				result = true;
				System.out.println("���������� ȸ�� ������ �����");
				// ������ �����ͺ��̽��� ����� ȸ�� ���̵� �����
				++s_member_id;
			} else {
				System.out.println("ȸ�� ���� ���� ����!!");
			}
		} catch (SQLException sqle) {
			System.out.println("sql ���� ���ܻ�Ȳ �߻�!!");
			System.out.println("������ " + sqle.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException sqle2) {
				System.out.println("�޸� û�� �߿� ���ܻ�Ȳ �߻�");
				System.out.println("������ " + sqle2.getMessage());
			}
		}

		return result;
	}

	// ������ �Լ� �����
	public TestMemberManagerFrameClass() {
		// TODO Auto-generated constructor stub
		this("ȸ�� ���� ����");
	}

	// main( ) �Լ��κ��� �ϳ��� ���ڿ��� �޴� ������ �Լ� �����
	public TestMemberManagerFrameClass(String title) {
		super(title);

		// ��� �󺧰� ��ư�鿡 ��Ʈ�� ����
		this.titleLabel.setFont(font);
		this.saveMemberInfoButton.setFont(font);
		this.searchMemberInfoButton.setFont(font);
		this.deleteMemberInfoButton.setFont(font);
		this.modifyMemberInfoButton.setFont(font);
		this.exitProgramButton.setFont(font);
		this.showMemberInfoButton.setFont(font);

		// 6���� ��ư���� �ǳ� �����̳ʿ� �ֱ�
		this.panel.add(this.saveMemberInfoButton);
		this.panel.add(this.showMemberInfoButton);
		this.panel.add(this.searchMemberInfoButton);
		this.panel.add(this.modifyMemberInfoButton);
		this.panel.add(this.deleteMemberInfoButton);
		this.panel.add(this.exitProgramButton);

		// �ǳ� �����̳ʴ� ���� ���ƾƿ� ��ġ ��������
		// ��� ��ġ�� �ֱ�
		this.add(this.panel, BorderLayout.CENTER);

		// ���α׷� ������ ���� ��ܿ� ���
		this.add(this.titleLabel, BorderLayout.NORTH);

		// 6���� ��ư ���� ��� ���ڿ��� ���� �����մϴ�.
		// -> setActionCommand("����_�ٸ�_���_���ڿ�");
		this.saveMemberInfoButton.setActionCommand("save");
		this.showMemberInfoButton.setActionCommand("show");
		this.searchMemberInfoButton.setActionCommand("search");
		this.modifyMemberInfoButton.setActionCommand("modify");
		this.deleteMemberInfoButton.setActionCommand("delete");
		this.exitProgramButton.setActionCommand("exit");

// 6���� ��ư Ŭ�� �̺�Ʈ ó���� ���� addActionListener() �Լ� ȣ��
		this.saveMemberInfoButton.addActionListener(this);
		this.showMemberInfoButton.addActionListener(this);
		this.searchMemberInfoButton.addActionListener(this);
		this.modifyMemberInfoButton.addActionListener(this);
		this.deleteMemberInfoButton.addActionListener(this);
		this.exitProgramButton.addActionListener(this);

// ������ ���� �̺�Ʈ ó���� ���� 
// addWindowListener() �Լ� ȣ��
		this.addWindowListener(this);

// pack( ) �Լ��� ȣ���ϱ�
		this.pack();

// �����츦 ȭ�鿡 ����ϱ�
		this.setVisible(true);
	}

	// 6���� ��ư ���� ����ڰ� Ŭ���ϴ� ��쿡
	// ���� �ٸ� ����� ������ �� �ֵ��� �ϱ�

	// 5���� ��ư ���� ���� Ŭ������ ���� ����ϱ�

	// 1) ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
	static class SaveButtonInnerClass extends Dialog implements WindowListener, ActionListener {

		// ���̾�α� Ÿ��Ʋ�� ����� �� ��ü �����
		private Label sbicTitleLabel = new Label("***ȸ�� ���� ����***", Label.CENTER);

		// ȸ�� �̸��� �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberNameTF = new TextField("ȸ�� �̸��� �Է�", 30);

		// ȸ�� ������ �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberGenderTF = new TextField("ȸ�� ������ �Է�", 20);

		// ȸ�� ���̸� �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberAgeTF = new TextField("ȸ�� ���̸� �Է�", 20);

		// ���� ��ư�� ����ϴ�!!
		private Button sbicMemberSaveButton = new Button("ȸ�� ���� ����(���)");

		// �ǳ� �����̳� ��ü�� ����ϴ�!!
		// -> �뵵 : 3���� �Է� â�� �ֽ��ϴ�!!
		private Panel sbicMemberPanel = new Panel(new GridLayout(1, 3));
		// GridLayout : ǥ ������ ������ִ� ��ġ ������
		// GridLayout(���� ����, ���� ����)

		// ��Ʈ ��ü�� ����ϴ�.
		private Font sbicFont = new Font("�ü�ü", Font.BOLD, 15);

		// ������ �Լ��� ����ϴ�.
		public SaveButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			// ������ ���� �ǳ� ��ü�� 3���� �Է� â��
			// ���ʴ�� �ֽ��ϴ�!!
			// 1. ���� ���ʿ��� ȸ�� �̸��� �Է��ϴ� â��
			// ��µǵ��� �մϴ�
			this.sbicMemberPanel.add(this.sbicMemberNameTF);
			// 2. �߰� ��ġ���� ȸ���� ������ �Է��ϴ� â�� ���
			this.sbicMemberPanel.add(this.sbicMemberGenderTF);
			// 3. ���� �����ʿ��� ȸ���� ���̸� �Է��ϴ�
			// â�� ���
			this.sbicMemberPanel.add(this.sbicMemberAgeTF);
			// 4. ��Ʈ ��ü�� ��� ��ü�� ���(����)
			this.sbicMemberAgeTF.setFont(sbicFont);
			this.sbicMemberGenderTF.setFont(sbicFont);
			this.sbicMemberNameTF.setFont(sbicFont);
			this.sbicMemberSaveButton.setFont(sbicFont);
			this.sbicTitleLabel.setFont(sbicFont);
			// 5. �ǳ� ��ü�� ���̾�α� â�� ���
			// ��ġ�� ���
			this.add(this.sbicMemberPanel, BorderLayout.CENTER);
			// 6. ��(Ÿ��Ʋ ��� �޽���)�� ���̾�α�
			// â�� ���� ��ġ�� ���
			this.add(this.sbicTitleLabel, BorderLayout.NORTH);
			// 7. ���� ��ư�� ���̾�α� â�� �Ʒ��� ��ġ�� ���
			this.add(this.sbicMemberSaveButton, BorderLayout.SOUTH);
			// 8. ������(���̾�α�) ���� �̺�Ʈ ó��
			this.addWindowListener(this);
			// 9. ���� ��ư Ŭ�� �̺�Ʈ ó�� ���
			this.sbicMemberSaveButton.addActionListener(this);
			// 10. ���̾�α��� �⺻ ũ�⸦ �����ϱ�
			this.setSize(500, 500);
			// 11. ���̾�α׸� ȭ�鿡 ����ϱ�
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
// ����ڰ� ���� ��ư�� Ŭ���ϴ� ��쿡�� ����� ��ɾ �ۼ�
			System.out.println("����ڰ� ��ư�� Ŭ��!");
			Object refObject = e.getSource();
			if (refObject instanceof Button) {
				Button refButton = (Button) refObject;
				if (refButton == sbicMemberSaveButton) {
					System.out.println("���� ��ư�� Ŭ��!!");

					// ����ڰ� �Է��� �̸��� ����, ���̸� ��������
					String nameValue = sbicMemberNameTF.getText().trim();
					String genderValue = sbicMemberGenderTF.getText().trim();
					String ageValue = sbicMemberAgeTF.getText().trim();

					// ����ڰ� ���� �Է��ߴ��� ���θ� �˻��ϱ�
					if (nameValue != null && genderValue != null && ageValue != null) {
						if (nameValue.length() > 0 && genderValue.length() > 0 && ageValue.length() > 0) {
// ���̴� ������ ��ȯ�� ������ �ӽ� ������ �����ϱ�
							int iageValue = Integer.parseInt(ageValue);
// insertMethod( ) �Լ��� ȣ���ϱ�
							boolean resultInsertMethod = insertMethod(nameValue, genderValue, iageValue);
// insertMethod( ) �Լ��� ���� ��� ���� �˻��ϱ�
							if (resultInsertMethod == true) {
								System.out.println("insertMethod() ���� ����!!");
							} else {
								System.out.println("insertMethod() ���� ����!!");
							}
						} else {
							System.out.println("����ڰ� �Է����� �ʾҽ��ϴ�!!");
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

	// 2) ����ڰ� ȸ�� ���� �˻� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
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

	// 3) ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
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

	// 4) ����ڰ� ȸ�� ���� ��� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
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

	// 5) ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
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

	// ���� �����쿡�� ����ڰ� ��ư�� Ŭ���ϴ� ��쿡
	// ������ ��ɾ���� �����ؼ� �ۼ��ϱ�
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		Object refObject = arg0.getSource();

		if (refObject instanceof Button) {

			Button refClickedButton = (Button) refObject;

			String actionCommand = refClickedButton.getActionCommand();

			actionCommand = actionCommand.toLowerCase();

			// ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ���� ���
			if (actionCommand.equals("save")) {
				SaveButtonInnerClass refSaveButtonInnerClass;
				refSaveButtonInnerClass = new SaveButtonInnerClass(this, "ȸ�� ���� ���� ���̾�α�", true);
			}
			// ����ڰ� ȸ�� ���� �˻� ��ư�� Ŭ���� ���
			else if (actionCommand.equals("search")) {
				SearchButtonInnerClass refSearchButtonInnerClass;
				refSearchButtonInnerClass = new SearchButtonInnerClass(this, "ȸ�� ���� �˻� ���̾�α�", true);
			}
			// ����ڰ� ȸ�� ���� ��� ��ư�� Ŭ���� ���
			else if (actionCommand.equals("show")) {
				ShowButtonInnerClass refShowButtonInnerClass;
				refShowButtonInnerClass = new ShowButtonInnerClass(this, "ȸ�� ���� ��� ���̾�α�", true);
			}
			// ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ���� ���
			else if (actionCommand.equals("modify")) {
				ModifyButtonInnerClass refModifyButtonInnerClass;
				refModifyButtonInnerClass = new ModifyButtonInnerClass(this, "ȸ�� ���� ���� ���̾�α�", true);
			}
			// ����ڰ� ȸ�� ���� ���� ��ư�� Ŭ���� ���
			else if (actionCommand.equals("delete")) {
				DeleteButtonInnerClass refDeleteButtonInnerClass;
				refDeleteButtonInnerClass = new DeleteButtonInnerClass(this, "ȸ�� ���� ���� ���̾�α�", true);
			}
			// ����ڰ� ���α׷� ���� ��ư�� Ŭ���� ���
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
		refFrame = new TestMemberManagerFrameClass("ȸ�� ���� ����");
	}
}
