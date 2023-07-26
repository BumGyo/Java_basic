package day20180716;
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

// Frame Ŭ������ ��� �޴� �ڽ� ������ Ŭ������ ����ϴ�!!
// ������ ���� �̺�Ʈ ó���� �ϱ� ���ؼ� WindowListener �������̽��� ����
class MyFrameWindow extends Frame implements WindowListener {

	// ���α׷��� Ÿ��Ʋ�� ȭ�鿡 ������ �� ����� Label ������ ����
	Label titleLabel = new Label("***�л� ���� �Է�(�÷��� ���)***"); 
	// -> ������ ���� ���� ������ �ȿ� ���� ���⿡ ����ϱ�
	// -> ���� ���� �̸��� "North"
	// -> add( titleLabel, "North");
	
	// �� �Ǵ� �ٸ� ������Ʈ��(��ư �Ǵ� �ؽ�Ʈ �ʵ� ��)��
	// ���� ũ�⸦ �ٲٱ� ���ؼ� Font Ŭ������ ����� ��ü�� ����
	Font  font = new Font("�ü�ü", Font.BOLD, 20);
		
	// ������ �����
	public MyFrameWindow(String title) {
		// String title: ������ ���� ��ܿ� ����� ���ڿ�
		// super : �θ� Ŭ������ �ּҸ� �����ϴ� �ڵ� ���� �̸�
		// super( ); -> �θ� Ŭ������ �ִ� ������ �Լ��� ȣ��
		super(title);
		
		// �� ��Ʈ�ѿ� ������ �ؽ�Ʈ�� �۲��� �ü�ü�� �ٲٰ�
		// ���ϰ� �׸��� ���� ũ��� 20���� �ٲٱ�
		titleLabel.setFont(font);		
		
		// ������ ���� ���� ������ �ȿ� ���� ���⿡ �ֱ�
		add(this.titleLabel, "North");
		
		// ������ ���� �̺�Ʈ�� �߻����� �� windowClosing( )
		// �Լ� �ȿ� �ִ� ��ɾ ����� �� �ֵ��� ���ִ� ��ɾ �ۼ�
		addWindowListener(this);
		// this : ���� Ŭ������ �ּҸ� �����ϰ� �ִ� ���� ����
		
		// setSize( ) �Լ��� ȣ���ؼ� ȭ�鿡 ������ �������� ����ũ���
		// ����ũ�⸦ �����ֱ�
		setSize(500, 500); 
		
		// �����츦 ȭ�鿡 ����ϱ�
		setVisible(true);		
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
