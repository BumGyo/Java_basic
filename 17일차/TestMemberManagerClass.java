
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
		private Label sbicTitleLabel = 
		new Label("***ȸ�� ���� ����***",Label.CENTER);
		
		// ȸ�� �̸��� �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberNameTF = 
				new TextField("ȸ�� �̸��� �Է�", 30);
		
		// ȸ�� ������ �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberGenderTF =
				new TextField("ȸ�� ������ �Է�", 20);
		
		// ȸ�� ���̸� �Է��� �� �ֵ��� �Է� â�� �����
		private TextField sbicMemberAgeTF = 
				new TextField("ȸ�� ���̸� �Է�", 20);
		
		
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

	}
}
