package day20180808;
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

		String select = "select nvl(max(member_id)+1, 1) from tbl_member";
		Statement s = null;
		ResultSet rs = null;
		
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
			s = s_refOracleConnection.createStatement();
			rs = s.executeQuery(select);
			if(rs.next() == true) {
				int v = rs.getInt(1);
				s_member_id = v;
			}
		} catch (ClassNotFoundException cnfe) {
// ����Ŭ ����̹� Ŭ������ ã�� ���� ��쿡�� �����
// ��ɾ �ۼ�
			System.out.println("����Ŭ ����̹� Ŭ���� ã�� ����");
		} catch (SQLException sqle) {
// ����Ŭ �����ͺ��̽��� ���ӿ� ������ ��쿡 ������ ��ɾ�			
			System.out.println("����Ŭ �����ͺ��̽��� ���� ����");
			System.out.println("������ " + sqle.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(s != null) s.close();
			} catch(SQLException sqle) {
				System.out.println("why: "+sqle.getMessage());
			}
		}
	} // end of static { }
	
	public static int deleteMethod(int idValue) {
		
		int result = 0;
		String sql = "delete from tbl_member where member_id = ?";
		PreparedStatement ps = null;
		
		try {
			
			if (s_isOracleConnected == false) {
				System.out.println("����Ŭ �����ͺ��̽��� ���� ���� �ƴ�");
				System.out.println("���� �Լ��� �����ϱ�!!");
				return 0;
			}
			
			ps = s_refOracleConnection.prepareStatement(sql);
			ps.setInt(1, idValue);
			result = ps.executeUpdate();
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} finally {
			try {
				if(ps != null) ps.close();
			} catch(SQLException sqle2) {
				System.out.println(sqle2.getMessage());
			}
		}
		
		return result;
	}

	public static int modifyMethod(int idValue, String nameValue, String genderValue, int ageValue) {
		int result = 0;
		String sql = "update tbl_member set member_name=?, member_gender=?, member_age=? where member_id=?";
		PreparedStatement ps = null;
		
		try {
			
			if (s_isOracleConnected == false) {
				System.out.println("����Ŭ �����ͺ��̽��� ���� ���� �ƴ�");
				System.out.println("���� �Լ��� �����ϱ�!!");
				return 0;
			}
			
			ps = s_refOracleConnection.prepareStatement(sql);
			ps.setString(1, nameValue);
			ps.setString(2, genderValue);
			ps.setInt(3, ageValue);
			ps.setInt(4, idValue);
			
			result = ps.executeUpdate();
			
		} catch(SQLException sqle) {
			
			System.out.println(sqle.getMessage());
			
		} finally {
			try {
				if(ps != null) ps.close();
			} catch(SQLException sqle2) {
				System.out.println(sqle2.getMessage());
			}
		}
		
		return result;
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

		System.out.println("s_member_id: " + s_member_id);
		
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
	
	public static String selectMethod(int kind, String value) {
		StringBuilder sb = new StringBuilder("");
		
		switch(kind)
		{
		case 1:	// �̸� �˻�
		{
			String sql = "select * from tbl_member where member_name LIKE '%' || ? || '%'";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			
			if(s_isOracleConnected == false) return "";
			
			try {
				
				ps = s_refOracleConnection.prepareStatement(sql);
				ps.setString(1, value);
				rs = ps.executeQuery();
				while(rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String gender = rs.getString(3);
					int age = rs.getInt(4);
					sb.append(id+":"+name+":"+gender+":"+age+"\r\n");
				}
				
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
				} catch(SQLException sqle2) {
					System.out.println(sqle2.getMessage());
				}
			}
			
		}
			break;
			
		case 2:	// ���� �˻�
		{
			String sql = "select * from tbl_member where member_gender LIKE '%' || ? || '%'";
			
			PreparedStatement ps = null;
			ResultSet rs = null;			
			
			if(s_isOracleConnected == false) return "";
			
			try {
				
				ps = s_refOracleConnection.prepareStatement(sql);
				ps.setString(1, value);
				rs = ps.executeQuery();
				while(rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String gender = rs.getString(3);
					int age = rs.getInt(4);
					sb.append(id+":"+name+":"+gender+":"+age+"\r\n");
				}
				
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
				} catch(SQLException sqle2) {
					System.out.println(sqle2.getMessage());
				}
			}
		}
			break;
			
		case 3:	// ���� �˻�
		{
			String sql = "select * from tbl_member where member_age=?";
			PreparedStatement ps = null;
			ResultSet rs = null;			
			
			if(s_isOracleConnected == false) return "";
			
			try {
				
				ps = s_refOracleConnection.prepareStatement(sql);
				ps.setString(1, value);
				rs = ps.executeQuery();
				while(rs.next())
				{
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String gender = rs.getString(3);
					int age = rs.getInt(4);
					sb.append(id+":"+name+":"+gender+":"+age+"\r\n");
				}
				
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			} finally {
				try {
					if(rs != null) rs.close();
					if(ps != null) ps.close();
				} catch(SQLException sqle2) {
					System.out.println(sqle2.getMessage());
				}
			}
		}
			break;
		}
		
		return sb.toString();
	}
	
	public static String selectMethod(int id) {
		String res = "";
		String sql = "select member_name, member_gender, member_age from tbl_member where member_id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			if(s_isOracleConnected == false) 
				return "";
			
			ps = s_refOracleConnection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next() == true)
			{
				String name = rs.getString(1);
				String gender = rs.getString(2);
				int age = rs.getInt(3);
				res = name+":"+gender+":"+age;
			}
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		
		
		return res;
	}
	
	public static boolean isDigit(String value) {
		boolean result = true;
		
		if(value == null || value.length() == 0) return false;
		
		for(int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			if(!Character.isDigit(ch)) {
				result = false;
				break;
			}
		}
		
		return result;
	}
	
	public static String selectAllMethod() {
		StringBuilder sb = new StringBuilder("");
		String selectAll = "select * from tbl_member";
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			if(s_isOracleConnected == false) {
				return "";
			}
			
			statement = s_refOracleConnection.createStatement();
			resultSet = statement.executeQuery(selectAll);
			
			while(resultSet.next() == true)
			{
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				int age = resultSet.getInt(4);
				
				sb.append(id+":"+name+":"+gender+":"+age+"\r\n");
			}
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
		
		
		return sb.toString();
	}

	public static boolean selectAllMethod(Choice refChoice) {
		//StringBuilder sb = new StringBuilder("");
		String selectAll = "select * from tbl_member";
		Statement statement = null;
		ResultSet resultSet = null;
		boolean result = false;
		
		if(refChoice == null) return false;
		
		if(refChoice.getItemCount() > 0)
			refChoice.removeAll();
		
		try {
			if(s_isOracleConnected == false) {
				return false;
			}
			
			statement = s_refOracleConnection.createStatement();
			resultSet = statement.executeQuery(selectAll);
			
			while(resultSet.next() == true)
			{
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String gender = resultSet.getString(3);
				int age = resultSet.getInt(4);
				
				result = true;
				
				refChoice.add(id+":"+name+":"+gender+":"+age);
				
				// sb.append(id+":"+name+":"+gender+":"+age);
			}
			
		} catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(statement != null) statement.close();
			} catch(SQLException sqle) {
				System.out.println(sqle.getMessage());
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
			
			this.sbicMemberGenderTF.selectAll();
			this.sbicMemberAgeTF.selectAll();
			this.sbicMemberNameTF.selectAll();
			
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
		 * �˻� �� + List + �˻� ��ư + �˻� ��� â
		 */
		private Label sbic3TitleLabel = new Label("***ȸ�� ���� �˻�***",Label.CENTER);
		
		private Choice sbic3Choice = new Choice();
		
		//private List sbic3List = new List(4, false);
		private TextField sbic3TextField = new TextField("�˻� Ű���� �Է�", 10);	
		private Button sbic3Button = new Button("�˻�");
		
		private Panel sbic3Panel1 = new Panel(new GridLayout(1, 3));
		
		private TextArea sbic3TextArea = new TextArea("", 10, 30);	
		private Panel sbic3Panel2 = new Panel();
		
		private Panel sbic3Panel3 = new Panel(new GridLayout(2, 1));
		
		private String itemArray[] = {"�˻��� ������ �����ϼ���","�̸�","����","����"};
		
		public SearchButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			this.add(this.sbic3TitleLabel,BorderLayout.NORTH);
			
			sbic3Panel1.add(this.sbic3Choice);
			//sbic3Panel1.add(this.sbic3List);
			sbic3Panel1.add(this.sbic3TextField);
			sbic3Panel1.add(this.sbic3Button);
			
			this.sbic3Button.setActionCommand("find");
			
			sbic3Panel2.add(sbic3TextArea);
			
			sbic3Panel3.add(sbic3Panel1);
			sbic3Panel3.add(sbic3Panel2);
			
			this.add(this.sbic3Panel3);
			
			for(int i = 0; i < itemArray.length; i++)
				sbic3Choice.add(itemArray[i]);
			
			sbic3Choice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					Object item = e.getItem();
					System.out.println("item: "+item);
					String sitem = (String)item;
					System.out.println("sitem: "+sitem);
					
					for(int i = 0; i < itemArray.length; i++) {
						if(itemArray[i].equals(sitem))
						{
							System.out.println("equals()");
							System.out.println("i: "+i);
							if(i == 0) {
								sbic3TextField.setText("");
								sbic3TextArea.setText("");								
							}
							else {
								sbic3TextField.setFocusable(true);
								sbic3TextField.requestFocus();
								sbic3TextField.selectAll();
								break;
							}
						}
					}
				}
			});
			
			this.sbic3Button.addActionListener(this);
			
//			sbic3List.add("�̸� �˻�");
//			sbic3List.add("���� �˻�");
//			sbic3List.add("���� �˻�");
//			
//			this.sbic3List.addItemListener(new ItemListener() {
//				
//				@Override
//				public void itemStateChanged(ItemEvent e) {
//					// TODO Auto-generated method stub
//					
//					Object item = e.getItem();
//					
//					System.out.println("item: " + item);			
//					
//				}
//			});
			
			this.addWindowListener(this);
			
			//this.setSize(500, 500);
			this.pack();
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object refObject = e.getSource();
			if(refObject instanceof Button)
			{
				System.out.println("Button");
				
				Button refButton = (Button)refObject;
				String cmd = refButton.getActionCommand();
				cmd = cmd.toLowerCase();
				
				System.out.println("cmd: "+cmd);
				
				String v = sbic3TextField.getText().trim();
				if(v == null || v.length() == 0) {
					return;
				}
				
				if(cmd.equals("find"))
				{				
					String selectedItem = sbic3Choice.getSelectedItem();
					for(int i = 0; i < itemArray.length; i++) {
						if(selectedItem.equals(itemArray[i]) == true)
						{
							String s = selectMethod(i, v);
							sbic3TextArea.setText(s);
							break;
						}
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

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			Window window = e.getWindow();
			window.setVisible(false);
			window.dispose();
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

		/*
		 * ���� ȭ�� �����
		 * 
		 * 1. Ÿ��Ʋ ���
		 * 2. ���̽� ������Ʈ
		 * 3. ���� ��ư
		 */
		
		private Label mbicTitleLabel = new Label("***ȸ�� ���� ����***", Label.CENTER);
		
		private Choice mbicChoice = new Choice(); 
		
		private Label     mbicMemberIdLabel = new Label("");
		private TextField mbicMemberNameTextField = new TextField("", 20);
		private TextField mbicMemberGenderTextField = new TextField("", 5);
		private TextField mbicMemberAgeTextField = new TextField("", 5);
		private Button    mbicButton = new Button("����");
		
		private Panel mbicMainPanel = new Panel(new GridLayout(2, 1));
		private Panel mbicPanel  = new Panel();
		private Panel mbicPanel2 = new Panel(new GridLayout(2,2)); 
		
		public ModifyButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			boolean resultSelect = selectAllMethod(mbicChoice);
			if(resultSelect == false) mbicChoice.add("��ϵ� ȸ�� ������ ����");
			
			if(mbicChoice.getItemCount() > 0) {
				String item = mbicChoice.getItem(0);
				char ch = item.charAt(0);
				if(Character.isDigit(ch)) {
					
					String []item_s = item.split(":");
					
					if(item_s.length != 4) {
						return;
					}
					
					mbicMemberIdLabel.setText(item_s[0]);
					mbicMemberNameTextField.setText(item_s[1]);
					mbicMemberGenderTextField.setText(item_s[2]);
					mbicMemberAgeTextField.setText(item_s[3]);
					
				}
				else {
					mbicMemberIdLabel.setText("");
					mbicMemberNameTextField.setText("");
					mbicMemberGenderTextField.setText("");
					mbicMemberAgeTextField.setText("");
				}
			}
			
			mbicPanel.add(mbicChoice);
			
			mbicPanel2.add(mbicMemberIdLabel);
			mbicPanel2.add(mbicMemberNameTextField);
			mbicPanel2.add(mbicMemberGenderTextField);
			mbicPanel2.add(mbicMemberAgeTextField);
			
			mbicMainPanel.add(mbicPanel);
			mbicMainPanel.add(mbicPanel2);
			
			this.add(mbicMainPanel);
			this.add(this.mbicTitleLabel, BorderLayout.NORTH);
			this.add(mbicButton, BorderLayout.SOUTH);
			
			this.addWindowListener(this);
			this.mbicButton.addActionListener(this);
			
			
			mbicChoice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					
					if(mbicChoice.getItemCount() == 1) return;
					
					String v = (String)e.getItem();
					
					String [ ] vs = v.split(":");
					
					for(int i = 0; i < vs.length; i++) {
						if(i == 0) mbicMemberIdLabel.setText(vs[0]);
						if(i == 1) mbicMemberNameTextField.setText(vs[1]);
						if(i == 2) mbicMemberGenderTextField.setText(vs[2]);
						if(i == 3) mbicMemberAgeTextField.setText(vs[3]);
					}
				
					mbicMemberNameTextField.selectAll();
					mbicMemberGenderTextField.selectAll();
					mbicMemberAgeTextField.selectAll();
				}
			});
			
			this.setSize(500, 500);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String sidValue 	= mbicMemberIdLabel.getText().trim();
			String nameValue 	= mbicMemberNameTextField.getText().trim();
			String genderValue 	= mbicMemberGenderTextField.getText().trim();
			String sageValue  	= mbicMemberAgeTextField.getText().trim();
			
			if(sidValue == null || sidValue.length() == 0) {
				return;
			}
			
			if(nameValue == null || nameValue.length() == 0) {
				return;
			}
			
			if(genderValue == null || genderValue.length() == 0) {
				return;
			}
			
			if(sageValue == null || sageValue.length() == 0) {
				return;
			}
			
			
			int idValue = Integer.parseInt(sidValue);
			int ageValue = Integer.parseInt(sageValue);
			
			int ires = modifyMethod(idValue, nameValue, genderValue, ageValue);
			if(ires == 1) {
				System.out.println("���� ����");
				
				String res = selectMethod(idValue);
				String [] res_s = res.split(":");
				mbicMemberNameTextField.setText(res_s[0]);
				mbicMemberGenderTextField.setText(res_s[1]);
				mbicMemberAgeTextField.setText(res_s[2]);
				
				boolean resultSelect = selectAllMethod(mbicChoice);
				if(resultSelect == false) mbicChoice.add("��ϵ� ȸ�� ������ ����");
			}
			else {
				System.out.println("���� ����");
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

	// 4) ����ڰ� ȸ�� ���� ��� ��ư�� Ŭ������ ����
	// ����� ���� Ŭ����
	static class ShowButtonInnerClass extends Dialog implements WindowListener, ActionListener {
		
		private Label sbic2TitleLabel = new Label("***��� ȸ�� ���� ���***", Label.CENTER);
		
		private TextArea sbic2TextArea = new TextArea("", 10, 30);
		
		private Font sbic2Font = new Font("�ü�ü",Font.BOLD,15);
				
		public ShowButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			this.add(this.sbic2TitleLabel, BorderLayout.NORTH);
			this.add(sbic2TextArea);
			
			sbic2TextArea.setFont(sbic2Font);
			sbic2TitleLabel.setFont(sbic2Font);
			
			String v = selectAllMethod();
			if(v == null ||v.equals("")) {
				sbic2TextArea.setText("����");
			}
			else {
				sbic2TextArea.setText(v);
			}
			
			this.addWindowListener(this);
			
			this.setSize(500, 500);
			this.setVisible(true);
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
			Window window = e.getWindow();
			window.setVisible(false);
			window.dispose();
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
		
		private Label dbicTitleLabel = new Label("***ȸ�� ���� ����***", Label.CENTER);
		
		private Choice dbicChoice = new Choice(); 
		
		private Label     dbicMemberIdLabel = new Label("");
		private TextField dbicMemberNameTextField = new TextField("", 20);
		private TextField dbicMemberGenderTextField = new TextField("", 5);
		private TextField dbicMemberAgeTextField = new TextField("", 5);
		private Button    dbicButton = new Button("����");
		
		private Panel dbicMainPanel = new Panel(new GridLayout(2, 1));
		private Panel dbicPanel  = new Panel();
		private Panel dbicPanel2 = new Panel(new GridLayout(2,2)); 
				
		public DeleteButtonInnerClass(Frame parentFrame, String title, boolean modal) {
			super(parentFrame, title, modal);
			
			boolean resultSelect = selectAllMethod(dbicChoice);
			if(resultSelect == false) dbicChoice.add("��ϵ� ȸ�� ������ ����");
			
			if(dbicChoice.getItemCount() > 0) {
				String item = dbicChoice.getItem(0);
				char ch = item.charAt(0);
				if(Character.isDigit(ch)) {
					
					String []item_s = item.split(":");
					
					if(item_s.length != 4) {
						return;
					}
					
					dbicMemberIdLabel.setText(item_s[0]);
					dbicMemberNameTextField.setText(item_s[1]);
					dbicMemberGenderTextField.setText(item_s[2]);
					dbicMemberAgeTextField.setText(item_s[3]);
					
				}
				else {
					dbicMemberIdLabel.setText("");
					dbicMemberNameTextField.setText("");
					dbicMemberGenderTextField.setText("");
					dbicMemberAgeTextField.setText("");
				}
			}
			
			dbicPanel.add(dbicChoice);
			
			dbicPanel2.add(dbicMemberIdLabel);
			dbicPanel2.add(dbicMemberNameTextField);
			dbicPanel2.add(dbicMemberGenderTextField);
			dbicPanel2.add(dbicMemberAgeTextField);
			
			dbicMainPanel.add(dbicPanel);
			dbicMainPanel.add(dbicPanel2);
			
			this.add(dbicMainPanel);
			this.add(dbicTitleLabel, BorderLayout.NORTH);
			this.add(dbicButton, BorderLayout.SOUTH);
			
			this.addWindowListener(this);
			this.dbicButton.addActionListener(this);
			
			
			dbicChoice.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
			
					System.out.println("bbbb");
					
					if(dbicChoice.getItemCount() == 1) return;
					
					String v = (String)e.getItem();
					
					String [ ] vs = v.split(":");
					
					for(int i = 0; i < vs.length; i++) {
						if(i == 0) dbicMemberIdLabel.setText(vs[0]);
						if(i == 1) dbicMemberNameTextField.setText(vs[1]);
						if(i == 2) dbicMemberGenderTextField.setText(vs[2]);
						if(i == 3) dbicMemberAgeTextField.setText(vs[3]);
					}
				
					dbicMemberNameTextField.selectAll();
					dbicMemberGenderTextField.selectAll();
					dbicMemberAgeTextField.selectAll();
				}
			});
			
			this.setSize(500, 500);
			this.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String idValue = dbicMemberIdLabel.getText().trim();
			
			if(idValue == null || idValue.length() == 0) {
				return;
			}
			
			boolean b = isDigit(idValue);
			if(b == false) {
				return;
			}
			
			System.out.println("b: "+b);
			
			int ires = deleteMethod(Integer.parseInt(idValue));
			if(ires == 1) {
				System.out.println(idValue + " ���� ����");
				boolean resultSelect = selectAllMethod(dbicChoice);
				if(resultSelect == false) dbicChoice.add("��ϵ� ȸ�� ������ ����");
			}
			else {
				System.out.println(idValue + " ���� ����");
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
			Window w = e.getWindow();
			w.setVisible(false);
			w.dispose();
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

public class TestMemberManagerClass_20180807 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMemberManagerFrameClass refFrame;
		refFrame = new TestMemberManagerFrameClass("ȸ�� ���� ����");
	}
}
