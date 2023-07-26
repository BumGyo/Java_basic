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
	 * ���� �����쿡 ����� 3���� ��ư�� ���α׷� ����(Ÿ��Ʋ)��
	 * ������ �� ����� ��ü���� �����մϴ�!!
	 */
	// ������ �����ִ� Label ��ü�� ����ϴ�!!
	private Label titleLabel = 
			 new Label("***ȸ�� ���� ����***", Label.CENTER);
	
	// ȸ�� ���� â�� ȭ�鿡 ������ִ� ��ư ��ü�� �����մϴ�!!
	private Button regMemberButton = new Button("ȸ�� ����");
	// reg : registration(���� �Ǵ� ����)
	
	// �α��� â�� ȭ�鿡 ������ִ� ��ư ��ü�� ����
	private Button  loginButton = new Button("�α���");
	
	// ���α׷� ���� ��ư ��ü�� ����
	private Button  exitButton = new Button("���α׷� ����");
	
	/*
	 * ������ ���� �� ��ü�� BorderLayout ��ġ �������� ���� ���⿡
	 * ���
	 * 
	 * 3���� ��ư�� ��� ��ġ�� ��� : Panel �����̳ʿ�
	 * GridLayout ���� ����� ������ִ� ��ġ �����ڸ� ���
	 * -> 1�� 3�� ǥ ������ ���� ������ ǥ �ȿ� 3���� ��ư��
	 *    ���ʴ�� �ְڽ��ϴ�!!
	 */
	private GridLayout gridLayout = new GridLayout(1, 3);
	private Panel panel = new Panel(gridLayout);
	
	// ���� ũ�⸦ �ٲ� �� ����� Font ��ü�� ����
	private Font font = new Font("�ü�ü", Font.BOLD, 20);
	
	/*
	 * ���� �������� ��ġ�� �������ִ� �Լ��� �����
	 * -> �� �Լ��� ������� ������ ���� ��� �� x ��ǥ�� 0, 
	 *    y ��ǥ�� 0 ��ġ�� ���
	 * -> x = 0, y = 0 -> ���� ȭ���� ��� ��ġ�� �ٲٰ��� �մϴ�!!
	 * 
	 * -> �Ű� ������ �ʿ� : ���� �������� ���� ũ�� : int width
	 *    ���� �������� ���� ũ�� : int height
	 *    
	 * -> ��ȯ�� : Point Ŭ������ ��� : x��ǥ y��ǥ ���� ���������
	 *    ���� �ִ� Ŭ����
	 */
	public static Point getMyWindowPoint(int width, int height) {
		Point resultPoint = null;
		
		/*
		 * 1. ���� ȭ���� ��ü ũ�⸦ ���ϱ� : �ػ� ���ϱ�
		 *    -> ���� ����� Dimension Ŭ���� : 
		 *       �簢�� �������� ���� ũ��� ���� ũ�⸦ ���� Ŭ����
		 *    -> ���� ũ�� : int width
		 *    -> ���� ũ�� : int height
		 */
		Dimension refScreenSize = 
				   Toolkit.getDefaultToolkit().getScreenSize();
		
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
	 * ����ڰ� ȸ�� ���� ��ư�� Ŭ���ϴ� ��쿡�� 
	 *                                     ����� Ŭ������ �����
	 */
	static class MyRegMemberDialogClass 
	                extends Dialog 
	                implements WindowListener, ActionListener {
		
		// �������� ������ ����� �� ��ü�� ����ϴ�!!
		private Label dlgTitleLabel = 
				        new Label("ȸ�� ���� â", Label.CENTER);
		
		// ����ڰ� ���̵� �Է��� �� �ֵ��� TextField ��ü�� �����
		private TextField idTF = 
					    new TextField("���̵� �Է��ϼ���...", 
					    		  30);
		
		// ����ڰ� ��й�ȣ�� �Է��� �� �ֵ��� TextField ��ü�� �����
		private TextField pwdTF = 
						new TextField("��й�ȣ�� �Է��ϼ���...", 30);

		// ���� ��ư�� ����ϴ�!! -> ����ڰ� Ŭ���ϸ�
		// ����ڰ� �Է��� ���̵� ������ �Ӽ� ������ ���� ����
		private Button saveButton = new Button("����");
		
		/*
		 * ���� �� ���� ��� : BorderLayout.NORTH
		 * ���̵�� ��й�ȣ �Է� â�� �ǳ� �����̳ʿ� ���� ������
		 * ���� ���̾ƿ��� ��� ��ġ�� ��� : BorderLayout.CENTER
		 * ���� ��ư�� ��ġ�� �Ʒ� �� : BorderLayout.SOUTH
		 */
		
		// ��Ʈ ��ü�� �����մϴ�!! -> ���� ũ��� 15�� ����
		private Font dlgFont = new Font("�ü�ü", Font.BOLD, 15);
		
		private Panel dlgPanel = 
				         new Panel(new GridLayout(1, 2));
		
		// �Ӽ� ������ ���� �� ����� Properties ��ü�� ����
		private Properties dlgProperties = new Properties();
		
		// ���� ���� �Ӽ� ������ ��ü ���(���� ���)�� ����� ����
		public static final String FILE_PATH = 
				 "c:/java2_dir/";
		// "c:\\java2_dir\\"
		// -> c:/java2_dir/����ڰ��Է��Ѿ��̵�.properties
		
		// ������ �Լ��� ���� Dialog Ŭ������ �θ� Ŭ������
		// ������ �־�� �մϴ�!!
		public MyRegMemberDialogClass
		       (Frame parent, String title, boolean modal) {
			super(parent, title, modal);
			/*
			 * parent : Dialog Ŭ������ �θ� ������(â)
			 * title : Dialog â�� ���� ��ܿ� ����� ���ڿ�
			 * modal : ��� ���θ� ���� �Ű� ���� 
			 * 
			 * ������(â) ���� ������ 
			 *   �θ� ������(Frame) -> �ڽ� ������(Dialog)
			 *   
			 * modal �Ű� ������ ���� �����κ��� 
			 *   ��(true)  �Ǵ� ����(false) �� �߿��� �ϳ���
			 *   ���� ���� ����
			 *   
			 *   ���� ���� ������ ��� â�� �Ǿ �ٽ� �θ�
			 *   â���� �̵��ϱ� ���ؼ��� ���� ȭ�鿡 ��µǰ� �ִ�
			 *   �ڽ� â�� �ݾƾ� �մϴ�!!
			 *   
			 *   ���� ������ ������ ��� ���� â�� �Ǿ
			 *   ���� �ڽ� â�� ����� ���¿��� �����Ӱ� �θ� â����
			 *   �̵��� �� �ֽ��ϴ�!!
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
refMyMemberLoginFrameClass = 
			new MyMemberLoginFrameClass("ȸ�� ���� ������");	
	}
}



