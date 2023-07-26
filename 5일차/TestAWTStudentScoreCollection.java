package day20180718;

/*
 * AWT �׷��� ���̺귯���� ����ؼ� ȭ�鿡 �ϳ��� ������(â)��
 * ����ϴ�!!
 * 
 * �����츦 ����� ���ؼ� java.awt ��Ű���� �ִ� Frame Ŭ������ ���
 */
import java.awt.Frame;
/*
 * �����츦 ȭ�鿡�� ������� �ϴ� ����� ���� ���� ���մϴ�!!
 * 
 * �̺�Ʈ(Event) : �ܺ� ��ġ�κ��� ���α׷� ������ ������ �Է�
 * 
 * �����ڰ� �����츦 ȭ�鿡�� ������� �ϴ� ��ɾ �ۼ�
 * -> �̺�Ʈ ó�� ���
 * 
 * 1) �̺�Ʈ�� ������ �ľ� : 
 *      ������ ���� �̺�Ʈ : WindowEvent : java.awt.event ��Ű���� ����
 *      -> java.awt ��Ű���� �ڽ� ��Ű�� : 
 *           import java.awt.event.WindowEvent; 
 * 
 * 2) �̺�Ʈ�� �߻��� ���� ������� �ľ� : ������ -> Frame Ŭ����
 * 
 * 3) ������ ���� �̺�Ʈ�� �߻����� �� ó���� ��ɾ �ۼ��ϱ�
 *    -> �̺�Ʈ ó����(Event Handler)
 *       -> windowClosing( ) �޼��� �ȿ� �ۼ�
 *          -> �� �޼����� ��ġ�� WindowListener �������̽� �ȿ�
 *             �ְų� WindowAdapter Ŭ���� �ȿ� �ֽ��ϴ�!!
 *             
 * 4) �̺�Ʈ�� �߻��� ��(������)�� �̺�Ʈ ó������ windowClosing( )
 *    �Լ��� ������ �־�� �մϴ�!!
 *    -> �ڹ� ���� �ӽ��� ó���� ��ɾ��� ��ġ�� �˷��ִ� ����
 *    -> addWindowListener( ) �Լ��� ȣ��             
 */
import java.awt.event.*;
/*
 * �л� ������ �Է� ���� ���� TextField Ŭ������ ����ϱ�
 * -> ����ڷκ��� ���� �޾ƿ� �� ����ϴ� ��Ʈ�� �Ǵ� ������Ʈ
 */
import java.awt.TextField;
/*
 * ȭ�鿡 �޽����� ����ϰ� ���� ��쿡�� Label Ŭ������ ����ϱ�
 */
import java.awt.Label;
/*
 * ���� ��� ȭ���� ��)
 * 1. �����츦 ȭ�鿡 ���
 * 2. ������ �ȿ� ù ��° �ٿ��� Ÿ��Ʋ�� ��� : Label Ŭ������ ���
 * 3. ������ �ȿ� �� ��° �ٿ��� ����ڷκ��� �л� �̸�, ����, ����,
 *    ���� �������� �Է¹��� �� �ִ� �Է� â�� ���
 *    TextField name = new TextField("ȫ�浿", 20);
 *    20 : ȭ�鿡 �����ִ� ���� ����
 *    TextField kor = new TextField("100", 20);
 *    TextField eng = new TextField("100", 20);
 *    TextField math = new TextField("100, 20");
 *    �ٷ� �Ʒ����� ���� ��ư �����
 *    Button  save = Button("�л� ���� �����ϱ�");
 */
import java.awt.*;

// ArrayList �÷��� Ŭ������ ����ϱ� ���� import ��ɾ �ۼ�
import java.util.ArrayList;

// ���ο� Ŭ������ ����ϴ�!! : ���� : ���ο� �л��� ��ȣ��
// ������ �ִ� Ŭ���� : ��ȣ�� �����ϰ� �о���� ����� ���� Ŭ����
class MyStudentNoClass {
	// 1. Ŭ���� ������ ���� : ��� ��ü���� �Բ� ����� �� �ִ� ����
	private static int s_no = 1;

	// 2. ������ ���ο� �л��� ��ȣ�� ������ִ� ����� �����ϴ�
	// �Լ� �����
	public static void createNo() {
		System.out.println("���ο� �л��� ��ȣ�� ����ϴ�!!");
		++s_no;
		System.out.println("��ȣ ���� �Ϸ�!!");
	}

	// 3. ������ ���� ������ �л��� ��ȣ�� �о���� ����� ���� �Լ�
	public static int readNo() {
		System.out.println("���� �л��� ��ȣ�� �о�ɴϴ�!!");
		return s_no;
	}
}

/*
 * �л��� �̸�, ���� ����, ����, ����, ����, ����� ������ Ŭ������ ���� ����ڽ��ϴ�!! -> ������ ��������ϴ�!! -> �߿� :
 * �ڷ���(���� �Ǵ� ����), �ڸ��� -> �̸� : ���ڿ� : String m_name; // m_ : ���λ� : member(������) ����
 * ���� : ���� : int kor; ���� ���� : ���� : int eng; ���� ���� : ���� : int math; ���� : ���� : int
 * total; ��� : �Ǽ� : double avg; �л� ��ȣ ���� ���� : ���� : int no;
 */
class MyStudentInfoClass {

	private int m_no;
	private String m_name;
	private int m_kor;
	private int m_eng;
	private int m_math;
	private int m_total;
	private double m_avg;

	{
		// �ʱ�ȭ ��(Initialization Block)
		// -> ������ �Լ� ���� ���� ����Ǵ� ��
		// -> ������ �Լ��� ���� ���� ��쿡 ������ �Լ����� ����
		// -> ��ɾ �ִ� ��쿡 �и��� �� �ִ� ���

		System.out.println("***�ʱ�ȭ ���� �����մϴ�!!***");

// �л� ��ȣ�� ���� m_no ��� ������ �����մϴ�!!
		System.out.println("�л� ��ȣ�� ���� ������ m_no ��� ������ ����!!");

// �л� ��ȣ�� MyStudentNoClass Ŭ�������� ���� ����
// -> Ŭ������ ���� �ִ� s_no ������ ���� �ִ� �л� ��ȣ�� �����ͼ�
// -> ��� ������ m_no�� �����ϱ� : ���� 1
// -> readNo( ) �Լ��� ȣ���մϴ�!!
		int currentNo = MyStudentNoClass.readNo();
		m_no = currentNo;
// �� ��°, �� ��° ... �л��� ��ȣ�� ����� ���ؼ��� createNo( )
// �Լ��� ���� ȣ���� �־�� �մϴ�!!
		MyStudentNoClass.createNo();
		System.out.println("�л� ��ȣ ���� �Ϸ�!!");
	}

	// �⺻ ������
	public MyStudentInfoClass() {

	}

	// �ٸ� Ŭ�����κ��� �л� ������ �޾ƿ��� ������ �Լ��� �����
	public MyStudentInfoClass(String nameValue, int korValue, int engValue, int mathValue) {
		System.out.println("�л� �̸��� ��� ������ �����մϴ�");
		m_name = nameValue;
		System.out.println("���� ������ ��� ������ �����մϴ�");
		m_kor = korValue;
		System.out.println("���� ������ ��� ������ ����");
		m_eng = engValue;
		System.out.println("���� ������ ��� ������ ����");
		m_math = mathValue;
	}

	/*
	 * MyStudentInfoClass Ŭ���� ������ ������!!
	 */
	// ������ ������ ��� ���� ���� 2���� �Լ��� ���� �����
	// 1) ��� ������ ���� �ִ� ���� �о���� �Լ� : getter �Լ�
	// -> public �ڷ��� get + ��������̸�( ) { return ��������̸�; }
	// 2) ��� ������ ���� �ִ� ���� �ٲپ��ִ� �Լ� : setter �Լ�
	// -> public void set + ��������̸�(�Ű���������) {
	// ��������̸� = �Ű������̸�;
	// }

	// �л� ��ȣ�� �о���� �Լ�
	public int getNo() {
		System.out.println("���� �л� ��ȣ�� �о�ɴϴ�!!");
		return m_no;
	}

	// ���� �л� ��ȣ�� �ٲپ��ִ� �Լ�
	public void setNo(int value) {
		System.out.println("���� �л� ��ȣ�� �ٲߴϴ�!!");
		m_no = value;
	}

	// �л� �̸��� �о���� �Լ�
	public String getName() {
		System.out.println("�л� �̸��� �о�ɴϴ�!!");
		return m_name;
	}

	// �л� �̸��� �ٲپ��ִ� �Լ�
	public void setName(String value) {
		System.out.println("�л� �̸��� �ٲߴϴ�!!");
		m_name = value;
	}

	// ��� ������ m_kor ���� setter / getter �Լ� �����
	public int getKor() {
		System.out.println("���� ������ �б�!!");
		return m_kor;
	}

	public void setKor(int value) {
		System.out.println("���� ������ �ٲٱ�!!");
		m_kor = value;
	}

	// ��� ������ m_eng ���� setter / getter �Լ� �����
	public int getEng() {
		System.out.println("���� ������ �б�!!");
		return m_eng;
	}

	public void setEng(int value) {
		System.out.println("���� ������ �ٲٱ�!!");
		m_eng = value;
	}

	// ��� ������ m_math ���� setter / getter �Լ� �����
	public int getMath() {
		System.out.println("���� ������ �б�!!");
		return m_math;
	}

	public void setMath(int value) {
		System.out.println("���� ������ �ٲٱ�!!");
		m_math = value;
	}

	// ��� ������ m_total ���� setter / getter �Լ� �����
	public int getTotal() {
		System.out.println("���� ������ �б�!!");
		return m_total;
	}

	public void setTotal() {
		System.out.println("���� ������ ����ϱ�");
		m_total = getKor() + getEng() + getMath();
	}

	// ��� ������ m_avg ���� setter / getter �Լ� �����
	public double getAvg() {
		System.out.println("��� ������ �б�!!");
		return m_avg;
	}

	public void setAvg() {
		System.out.println("��� ������ ����ϱ�");
		m_avg = getTotal() / 3.0;
	}
}

// Frame Ŭ������ ��� �޴� �ڽ� ������ Ŭ������ ����ϴ�!!
// ������ ���� �̺�Ʈ ó���� �ϱ� ���ؼ� WindowListener �������̽��� ����
class MyFrameWindow extends Frame implements WindowListener, ActionListener {

	// ����ڰ� �Է��� �л����� �̸��� ����,����,���� �������� ������
	// ������ ����
	ArrayList<MyStudentInfoClass> refStudentInfoList = new ArrayList<MyStudentInfoClass>();

	// ���α׷��� Ÿ��Ʋ�� ȭ�鿡 ������ �� ����� Label ������ ����
	Label titleLabel = new Label("***�л� ���� �Է�(�÷��� ���)***", Label.CENTER);
	// Label.LEFT / Label.RIGHT / Label.CENTER
	// -> ������ ���� ���� ������ �ȿ� ���� ���⿡ ����ϱ�
	// -> ���� ���� �̸��� "North"
	// -> add( titleLabel, "North");

	// �� �Ǵ� �ٸ� ������Ʈ��(��ư �Ǵ� �ؽ�Ʈ �ʵ� ��)��
	// ���� ũ�⸦ �ٲٱ� ���ؼ� Font Ŭ������ ����� ��ü�� ����
	Font font = new Font("�ü�ü", Font.BOLD, 20);

	/*
	 * 1. Panel Ŭ������ ����ؼ� �����̳� ��ü�� �����ϱ� �뵵 : 5���� ��Ʈ�ѵ��� ȭ�鿡 ����ϱ� ����
	 */
	GridLayout gridLayout = new GridLayout(5, 1);
	/*
	 * Panel panel = new Panel(new GridLayout(5, 1));
	 * 
	 */
	Panel panel = new Panel(gridLayout);

	// �л� �̸� �Է� â �����
	TextField nameTF = new TextField("ȫ�浿", 10);
	// ���� ���� �Է� â �����
	TextField korTF = new TextField("70", 10);
	// ���� ���� �Է� â �����
	TextField engTF = new TextField("80", 10);
	// ���� ���� �Է� â �����
	TextField mathTF = new TextField("90", 10);
	// ���� ��ư �����
	Button saveBtn = new Button("�л� ���� �����ϱ�");

	// ������ �����
	public MyFrameWindow(String title) {
		// String title: ������ ���� ��ܿ� ����� ���ڿ�
		// super : �θ� Ŭ������ �ּҸ� �����ϴ� �ڵ� ���� �̸�
		// super( ); -> �θ� Ŭ������ �ִ� ������ �Լ��� ȣ��
		super(title);

		// �� ��Ʈ�ѿ� ������ �ؽ�Ʈ�� �۲��� �ü�ü�� �ٲٰ�
		// ���ϰ� �׸��� ���� ũ��� 20���� �ٲٱ�
		titleLabel.setFont(font);

		// �л� �̸��� �Է� ���� �Է� â���� ���� �Ӽ��� �ٲٱ�
		nameTF.setFont(font);

		// ���� ������ �Է� ���� �Է� â���� ���� �Ӽ��� �ٲٱ�
		korTF.setFont(font);

		// ���� ������ �Է� ���� �Է� â���� ���� �Ӽ��� �ٲٱ�
		engTF.setFont(font);

		// ���� ���� �Է�â�� ���� �Ӽ� �ٲٱ�
		mathTF.setFont(font);

		// �л� ���� ���� ���� ��ư�� ���� �Ӽ� �ٲٱ�
		saveBtn.setFont(font);

		/*
		 * saveBtn ���� ������ actionPerformed( ) �Լ��� �����ϱ� -> addActionListener( ) �Լ��� ȣ��
		 */
		saveBtn.addActionListener(this);
		/*
		 * this : ����ڰ� ��ư�� Ŭ������ �� ActionEvent ��ü�� �ּҸ� ����
		 */

		/*
		 * ����ڰ� �ؽ�Ʈ �ʵ� �Է� â���� ���� Ű�� ���� ��쿡 �߻��ϴ� ActionEvent �̺�Ʈ�� ��� ���� ��ɾ �ۼ� -> �ؽ�Ʈ �ʵ�
		 * �Է� â�� actionPerformed() �Լ��� ����
		 */
		// ����ڰ� �л� �̸��� �Է��ϰ� ����Ű�� ���� ��쿡��
		// �ڵ����� actionPerformed() �Լ� ������ �̵��ϰ� �ϱ�
		nameTF.addActionListener(this);

		korTF.addActionListener(this);
		engTF.addActionListener(this);
		mathTF.addActionListener(this);

		// �ǳ� �����̳ʿ� 5���� ��Ʈ�ѵ��� �ֱ�
		panel.add(nameTF);
		panel.add(korTF);
		panel.add(engTF);
		panel.add(mathTF);
		panel.add(saveBtn);

		// �ǳ� �����̳ʴ� ���� ���̾ƿ� ��ġ �����ڰ� ���� �ִ�
		// ��� ���⿡ �ֱ�
		add(panel, BorderLayout.CENTER);
		// BorderLayout.CENTER : "Center" ���ڿ� ��� �̸�

		// ������ ���� ���� ������ �ȿ� ���� ���⿡ �ֱ�
		add(this.titleLabel, "North");

		// ������ ���� �̺�Ʈ�� �߻����� �� windowClosing( )
		// �Լ� �ȿ� �ִ� ��ɾ ����� �� �ֵ��� ���ִ� ��ɾ �ۼ�
		addWindowListener(this);
		// this : ���� Ŭ������ �ּҸ� �����ϰ� �ִ� ���� ����

		// setSize( ) �Լ��� ȣ���ؼ� ȭ�鿡 ������ �������� ����ũ���
		// ����ũ�⸦ �����ֱ�
		// setSize(500, 500);
		pack();

		// �����츦 ȭ�鿡 ����ϱ�
		setVisible(true);
	}

	/*
	 * ���� �߰��� ActionListener �������̽����� ���� �־��� actionPerformed( ) �߻� �޼��带 �����ϱ�
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		Object refEventSource = actionEvent.getSource();

		if (refEventSource instanceof Button) {
			System.out.println("��ư���� ActionEvent �̺�Ʈ�� �߻�");
// ����ڰ� ��ư�� Ŭ���ϴ� ��쿡�� �߻�

// refEventSource ���� ������ Ŭ���� Ÿ���� Object
// -> Button Ŭ������ ���� �ִ� �Լ����� ���� -> �� ��ȯ�� �ϼž� �մϴ�!!
			Button refClickedButton = (Button) refEventSource;
// -> Button Ŭ������ ���� �ִ� ��� �Լ����� ȣ���ϱ� ����

// ���� ���� ��ư ��ü �߿��� Ư�� ��ư ��ü������ �˻��ϱ�
// == ���� �� ������ : 10 == 20, ���������̸�1 == ���������̸�2(�ּ�)
			if (refClickedButton == saveBtn) {
				System.out.println("����ڰ� ���� ��ư�� Ŭ���߽��ϴ�!!");

				/*
				 * ����ڰ� �Է��� �л� �̸��� ����, ����, ���� �������� �������� ��� ����ڰ� �Է��� ������ TextField �Է� â���� �����;�
				 * �մϴ�!! getText( ) �Լ��� ȣ���ϸ� ����ڰ� �Է��� ��� ������ �����ɴϴ�. -> ������ String(���ڿ�)�� ���� ��ȯ
				 */
				// ����ڰ� �Է��� �л� �̸��� �����ͼ� �ӽ� ������ �����ϱ�
				String tempNameValue = nameTF.getText().trim();
				/*
				 * trim( ) �Լ��� ȣ���ϸ� ����ڰ� �Է��� �̸��� ���ʰ� �����ʿ� �ִ� ���� ����(����ڰ� �����̽��� ���� ��� ��) �� ���
				 * �����ݴϴ�!! ��) "ȫ�浿" ��) "   ȫ�浿    " & "ȫ�浿(ã�� ���� �л� �̸�)"
				 */
				// ����ڰ� �Է��� ���� ������ �����ͼ� ���ڿ� ���·� �ӽ� ������
				// �����ϱ�
				String tempKorValue = korTF.getText().trim();
				// ����ڰ� �Է��� ���� ������ �����ͼ� ���ڿ� ���·� �ӽ� ������ ����
				String tempEngValue = engTF.getText().trim();
				// ����ڰ� �Է��� ���� ������ �����ͼ� �ӽ� ������ �����ϱ�
				String tempMathValue = mathTF.getText().trim();

				// ����ڰ� �Է��� ���� ���� ��쵵 �˻��ؾ� �մϴ�!!
				// -> ""(�� ���ڿ� : empty string)
				if (tempNameValue.equals("")) {
					System.out.println("����ڰ� �л� �̸��� �Է����� ����!!");
					// ���α׷����� ������ ����ڰ� �л� �̸��� �Է��� �� �ֵ���
					// Ŀ���� nameTF �ؽ�Ʈ �ʵ� â���� �̵���Ű��
					nameTF.setFocusable(true); // ���콺 Ŀ���� ���� �غ��ϱ�
					nameTF.requestFocus(); // ���콺 Ŀ���� ��ġ�� �̵���Ű��
					return; // ���� actionPerformed( ) �Լ��� Ż���ϱ�
				}

				// ����ڰ� ���� ������ �Է����� ���� ��쿡�� ���콺 Ŀ���� ������
				// ���� ���� �Է� â���� �̵���Ű��
				if (tempKorValue.equals("")) {
					System.out.println("����ڰ� ���� ������ �Է����� ����!!");
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				}

				// ���� ���� �Է� ������ �˻��ؼ� �Է����� ���� ��쿡�� ������
				// ���� ������ �Է��� �� �ִ� �Է� â���� �̵���Ű��
				if (tempEngValue.equals("")) {
					System.out.println("����ڰ� ���� ������ �Է����� ����");
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}

				// ���� ���� �Է� ���� �˻� + ������ ���콺 Ŀ�� ��ġ �̵�
				if (tempMathValue.equals("")) {
					System.out.println("����ڰ� ���� ������ �Է����� ����");
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}

				// ����ڰ� �Է��� �л� �̸��� ����, ����, ���� �������� �ܼ�
				// ȭ�鿡 ����ϱ�
				System.out.println("�л� �̸��� " + tempNameValue);
				System.out.println("���� ������ " + tempKorValue);
				System.out.println("���� ������ " + tempEngValue);
				System.out.println("���� ������ " + tempMathValue);

				// ����ڰ� �Է��� �л� �̸�, ����, ����, ���� ������ ����
				// ��ü�� �޸𸮿� �����ϰ� �ӽ� ������ �����ϱ�
				// -> �迭 ����Ʈ ������ ������ ��ü�� �����
				MyStudentInfoClass refNewStudentInfo = new MyStudentInfoClass(tempNameValue,
						Integer.parseInt(tempKorValue), Integer.parseInt(tempEngValue),
						Integer.parseInt(tempMathValue));

				// ������ ����ؼ� m_total ������ �����ϱ�
				// -> setTotal( ) �Լ��� ȣ��
				refNewStudentInfo.setTotal();

				// ����� ����ϱ� ���ؼ� setAvg() �Լ��� ȣ���ϱ�
				refNewStudentInfo.setAvg();

				// �ܼ� ȭ�鿡 ������ ����� ����ϱ�
				System.out.println("������ " + refNewStudentInfo.getTotal());
				System.out.printf("����� %.2f\n", refNewStudentInfo.getAvg());

				// ���� ���� �л� ��ü�� �迭 ����Ʈ ������ �ֱ� : add( ) �Լ��� ȣ��
				refStudentInfoList.add(refNewStudentInfo);

				System.out.println("�迭 ����Ʈ ������ ���ο� �л� ������ ����");

				// �迭 ����Ʈ ������ ���� �ִ� size( ) �Լ��� ȣ���ؼ�
				// ������� �迭 ����Ʈ ������ ����� �л� ���� �ܼ� ȭ�鿡 ���
				System.out.println("�迭 ����Ʈ ������ ����� �л� ���� " + refStudentInfoList.size() + " �� �Դϴ�!!");

			}

		} else if (refEventSource instanceof TextField) {
			System.out.println("TextField���� ActionEvent �̺�Ʈ�� �߻�");
// TextField �Է� â���� ���𰡸� �Է��Ͻð� ���� Ű�� ������ ��� 	

			TextField refTextField = (TextField) refEventSource;

// ����ڰ� ����Ű�� ���� �Է� â�� �л� �̸� �Է� â������ �Ǵ��ϱ�
			if (refTextField == nameTF) {
				System.out.println("����ڰ� �л� �̸��� �Է��ϰ� ���͸� ����!!");

				// ����ڰ� �Է��� �л� �̸��� �˻��ϱ�
				String value = nameTF.getText().trim();
				if (value.equals("")) {
					System.out.println("����ڰ� �л� �̸��� �Է����� ����");
					nameTF.setFocusable(true);
					nameTF.requestFocus();
					return;
				} // end of if(value.equals(""))
				else {
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				}
			} // end of if(refTextField == nameTF) {
			else if (refTextField == korTF) {
				System.out.println("����ڰ� ���� ������ �Է��ϰ� ���͸� ����!!");
				String value = korTF.getText().trim();
				if (value.equals("")) {
					System.out.println("���� ������ �Է����� ����");
					System.out.println("������ ���콺 Ŀ���� ���� �Է� â���� �̵�");
					korTF.setFocusable(true);
					korTF.requestFocus();
					return;
				} // end of if(value.equals("")) {
				else {
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}
			} // end of else if(refTextField == korTF) {
			else if (refTextField == engTF) {
				System.out.println("���� ������ �Է��ϰ� ���͸� ����!!");
				String value = engTF.getText().trim();
				if (value.equals("")) {
					System.out.println("���� ������ �Է����� ����");
					engTF.setFocusable(true);
					engTF.requestFocus();
					return;
				}
				else {
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}
			} else if (refTextField == mathTF) {
				System.out.println("���� ���� �Է� â���� ���͸� ����!!");
				String value = mathTF.getText().trim();
				if (value.length() == 0) {
					// length() �Լ��� ����ڰ� �Է��� ���� ������ ���� ������ ��ȯ
					System.out.println("���� ������ �Է����� ����");
					mathTF.setFocusable(true);
					mathTF.requestFocus();
					return;
				}
			} else {
				System.out.println("�𸣴� �Է� â�Դϴ�!!");
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
		System.out.println("����ڰ� ������ ���� ��ư�� Ŭ��!!");
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
	// �������̽� �ȿ��� �߻� �޼��带 ���� �ֽ��ϴ�!!
	// WindowListener �������̽� �ȿ��� 7���� �߻� �޼��带 ���� �ֽ��ϴ�.
	// ��� �޴� �ڽ� Ŭ���������� �߻� �޼��带 �����ؾ� �մϴ�!!

}

public class TestAWTStudentScoreCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

// ������ ���� MyFrameWindow Ŭ������ �޸𸮿� �����ϱ�
		MyFrameWindow refMyWindow = new MyFrameWindow("MY FIRST WINDOW");

	}

}
