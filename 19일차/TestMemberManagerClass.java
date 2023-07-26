
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
			s_refOracleConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test_user1","1234");
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

	/*
	 * ���� tbl_member ���̺� ����Ǿ� �ִ� ��� ���ڵ���� �о�ͼ�
	 * ���ڿ��� ��ȯ���ִ� �Լ� -> ���ڿ� ����
	 * ShowInnerButtonClass ���� Ŭ�������� ����ϱ� ����
	 */
	public static String selectAllMethod() {
		// StringBuilder Ŭ������ ����ϱ� : append( ) �Լ��� ����ϱ� ����
		// String Ŭ���� -> String �����̸� = "";
		StringBuilder sb = new StringBuilder("");
		// select * from tbl_member ��ɾ ���ڿ��� ������ ���� ����
		String selectAllSql = "select * from tbl_member";
		// Statement �������̽��� ����ؼ� ���ڿ� select sql ��ɾ ����
		Statement statement = null;
		// -> select sql ��ɾ ����ǥ ��ȣ�� ���� ������
		// select sql ��ɾ��� ���� ����� ǥ ���·� ������ ���� ����
		ResultSet resultSet = null;
		
		
		try {
			
			// 1. ����Ŭ �����ͺ��̽��� ���� ������ ���θ� �˻�
			if(s_isOracleConnected == false)
			{
				System.out.println("����Ŭ �����ͺ��̽��� ���� ���� �ƴ�");
				return "";
			}
			
			// 2. ����Ŭ ���� ������ ����ؼ� Statement ��ü�� �����
			statement = s_refOracleConnection.createStatement();
			
			// 3. executeQuery( ) �Լ��� ȣ���ؼ� select sql ��ɾ ����
			resultSet = statement.executeQuery(selectAllSql);
			
			// 4. while �ݺ����� ����ؼ� ǥ ������ ���� �ִ� ����
			// resultSet�� ���� �ִ� ��� �����͵��� �� ������ �о�ͼ�
			// StringBuilder ������ append( ) �Լ��� ȣ���ϱ�
			while(resultSet.next() == true)
			{
				/*
				 * next( ) �Լ� : ������ ���� : ǥ ������ ù ��° ����
				 * �ּҷ� �̵��� �ִ� �Լ�
				 * ǥ �������� �����͸� �������� �Լ��� get + �ڷ���( ) 
				 */
				// 1. ù ��° ���� ȸ�� ���̵� �����ͼ� �ӽ� ������ ����
				int idValue = resultSet.getInt(1);
				// 1 : create table ��ɾ�� �ۼ��ߴ� �÷� ���� ��ȣ
				// 2. ȸ�� �̸��� �����ͼ� �ӽ� ������ ����
				String nameValue = resultSet.getString(2);
				// 3. ������ �����ͼ� �ӽ� ������ ����
				String genderValue = resultSet.getString(3);
				// 4. ���̸� �����ͼ� �ӽ� ������ ����
				int ageValue = resultSet.getInt(4);
				
				// 5. ������ �ӽ� ������ �����Ǿ� �ִ� ��� ������
				// ���ڿ��� ���� �Ŀ� StringBuilder ������ �߰��ϱ�(append() �Լ��� ȣ��)
				String strResult = 
			idValue+","+nameValue+","+genderValue+","+ageValue+"\r\n";
			// "\r\n" : TextArea ������Ʈ�� �� �ٲ� ��ȣ
			// \r : carriage return : Ŀ���� ��ġ�� ���� �ٿ��� 
			// �� �������� �̵�
				
				// 6. ������ ���� ���ڿ��� StringBuilder ������ �߰��ϱ�
				sb.append(strResult);							
			}
						
		} catch(SQLException sqle) {
			System.out.println("sql ��ɾ� ����ÿ� ���ܻ�Ȳ�� �߻�!!");
			System.out.println("������ "+sqle.getMessage());
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
			} catch(SQLException sqle2) {
				System.out.println("���ܻ�Ȳ �߻�!!");
				System.out.println("������ "+sqle2.getMessage());
			}
		}
				
		return sb.toString();
	}
	
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

		/*
		 * �˻� Ű���带 ����ڰ� �Է��ϵ��� �մϴ�. -> TextField
		 * �˻� ������ ����ڰ� �����ϵ��� �մϴ�. -> 
		 *   ȸ�� �̸�/ȸ�� ����/ȸ�� ���� -> Choice ������Ʈ�� ���
		 *   -> �ϳ��� ���ڿ����� ȭ�鿡 ����� �ִ� ������Ʈ
		 *   -> ����ڰ� �ϳ��� ���ڿ��� ������ �� �ֽ��ϴ�.
		 * 
		 */
		// 1. ������ ������ִ� �� ��ü�� �����
		private Label sbic3TitleLabel = new Label("***ȸ�� �˻� ȭ��***", Label.CENTER);
		
		// 2. �˻� ����(ȸ���̸�/ȸ������/ȸ������/����)�� ���ڿ� �迭��
		// �����ϱ�
		private String strArray[] = 
			{"�˻� ������ ����","ȸ���̸�","ȸ������","ȸ������"};
		
		// 3. ������ ���� ���ڿ� �迭�� ���� Choice ������Ʈ ��ü�� �����
		private Choice sbic3Choice = new Choice();
		
		// 4. �˻� Ű���带 �Է��� â�� ����ϴ�!!
		private TextField  sbic3TextField = 
				new TextField("�˻� Ű���带 �Է��ϼ���", 40);
		
		// 5. �˻� ��ư ��ü�� �����
		private Button sbic3Button = new Button("�˻�");
		
		// 6. ��Ʈ ��ü �����
		private Font  sbic3Font = new Font("�ü�ü",Font.BOLD,20);
		
		// 7. �˻� ����� ȭ�鿡 ������ִ� ��ü�� �����
		private TextArea sbic3TextArea = new TextArea("", 10, 20);
		// -> 10 : ���� ����, 20 : ���� ����
		
		/*
		 * ȭ�� ��ġ
		 * 
		 * 1. �� ������ ������ ��� : sbic3TitleLabel
		 * 2. ��� ��ġ���� �˻� ����� ��� : sbic3TextArea
		 * 3. �Ʒ� ��ġ���� sbic3Choice(�˻� ������ ���)
		 *    sbic3TextField(�˻� Ű���带 ����ڰ� �Է�)
		 *    sbic3Button(�˻� ��ư)
		 * 
		 */
		public SearchButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			// �� �Ʒ� ��ġ(BorderLayout.SOUTH)�� ����� �ǳ� ��ü�� �����
			Panel panel = new Panel();
			// �ǳ� ��ü �ȿ��� ���̽� ������Ʈ, �ؽ�Ʈ�ʵ�, �˻� ��ư�� �ֽ��ϴ�.
			panel.add(sbic3Choice);
			panel.add(sbic3TextField);
			panel.add(sbic3Button);
			add(panel, BorderLayout.SOUTH);
			
			add(sbic3TitleLabel, BorderLayout.NORTH);
			add(sbic3TextArea, BorderLayout.CENTER);
			
			// ���̽� ������Ʈ�� �˻� ���� ���ڿ��� �ֽ��ϴ�!!
			for(int i = 0; i < strArray.length; i++)
			{
				sbic3Choice.add(strArray[i]);
			}
			
			// ���̽� ������Ʈ�� ����ڰ� ������ ��쿡 �߻���
			// ItemEvent Ÿ���� �̺�Ʈ�� ó���ϱ� ���� ��ɾ�
			sbic3Choice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					System.out.println("����ڰ� �˻� ������ ����!!");					
				}
			});
			
			// ����ڰ� ��ư�� Ŭ���� ��쿡�� �߻��ϴ� ActionEvent �̺�Ʈ��
			// ó���ϱ� ���� ��ɾ�
			sbic3Button.addActionListener(this);
			
			// ������ ���� �̺�Ʈ�� ó���ϱ� ���� ��ɾ�
			this.addWindowListener(this);
			
			this.setSize(500, 500);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			// ����ڰ� �˻� ��ư�� Ŭ���ϴ� ��쿡�� ������ ��ɾ �ۼ�
			Object refObject = e.getSource();
			
			if(refObject instanceof Button) {
				
				Button refButton = (Button)refObject;
				
				if(refButton == sbic3Button) {
					
					System.out.println("����ڰ� �˻� ��ư�� Ŭ����");
					
// 1. ����ڰ� ������ �˻� ���� ���ڿ� ���� �����;� �մϴ�!!
String itemValue = sbic3Choice.getSelectedItem();

// 2. ����ڰ� "�˻� ������ ����" ���ڿ��� ������ ��쿡��
// �˻� ��� â�� ��µ� ��� ������ �� �����
// ����ڰ� �Է��� �˻� Ű���嵵 �� ���쵵�� �ϰڽ��ϴ�!!
if(itemValue.equals(strArray[0]) == true) {
	// ����ڰ� �˻� ������ ���� ���ڿ��� ������ ���
	System.out.println("����ڰ� �˻� ������ ����!!");
	// setText( ) �Լ��� ȣ���ؼ� �˻� ��� â�� ��µ� ��� ������� �����
	sbic3TextArea.selectAll();
	sbic3TextArea.setText("");	
}
else {
	// ����ڰ� ȸ���̸�/ȸ������/ȸ������ �߿��� �ϳ��� ���ڿ��� ������ ���
	System.out.println("����ڰ� ȸ���̸�/ȸ������/ȸ������ �߿��� ");
	System.out.println("�ϳ��� ����!!");
}
					
					
					
// 2. ����ڰ� �Է��� �˻� Ű���带 �����;� �մϴ�!!					
					
					
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
		
		/*
		 * tbl_member ���̺� �����Ǿ� �ִ� ��� �����͵��� �о�ͼ�
		 * ȭ�鿡 ������ִ� ����� �����ϱ�
		 * -> ���̺� ���� ���� ���ڵ�(�� �Ǵ� ��)���� ����� �� �����Ƿ�
		 *    TextArea  ������Ʈ�� ����ؾ� �մϴ�!!       
		 */
		private TextArea sbic2TextArea = new TextArea("", 10, 30);
		// 10 : ��(���� �� ��), 30 : ��(���� ĭ ��)
		
		// Ÿ��Ʋ�� ȭ�鿡 �����ִ� �� ��ü�� ����ϴ�!!
		private Label  sbic2TitleLabel = 
				new Label("***��� ������ ���***", Label.CENTER);
		
		// ��ư ��ü�� ����ϴ�!!
		private Button sbic2ShowAllButton = new Button("��� ������ ���");
		
		// ��Ʈ ��ü�� ����ϴ�!!
		private Font sbic2Font = new Font("�ü�ü",Font.BOLD, 15);
		
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
			
			// ����ڰ� ��ư�� Ŭ������ ���� ������ �� �ִ� ��ɾ���� �ۼ�
			Object refObject = e.getSource();
			if(refObject instanceof Button) {
				Button refButton = (Button)refObject;
				if(refButton == sbic2ShowAllButton) {
					System.out.println("����ڰ� ��ư�� Ŭ��!!");
					
					// ��� ���� selectAllMethod( ) �Լ��� ȣ��
					String resultSelectAllMethod;
										
					resultSelectAllMethod = selectAllMethod();
					
					if(resultSelectAllMethod == null ||
					   resultSelectAllMethod.length() == 0) {
						System.out.println("���� ��ϵ� ȸ���� �����ϴ�!!");
					} else {
						// TextArea ������Ʈ�� ���ڿ��� ����ϱ�
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

		// ����ڰ� ������ ���� ��ư�� Ŭ������ ���� ����� �� �ִ� ��ɾ���� �ۼ�
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			// 1. ���̾�α� â�� �����ϱ�
			// 1) ���̾�α� â�� �ּҸ� �˾ƿ���
			Window refWindow = e.getWindow();
			// 2) setVisible(false); �Լ��� ȣ���ؼ� ������� �ʱ�
			refWindow.setVisible(false);
			// 3) �޸𸮿� �ִ� ���̾�α� â�� �ý��ۿ� ��ȯ�ϱ�
			// -> dispose( ) �Լ��� ȣ���ϱ�
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
