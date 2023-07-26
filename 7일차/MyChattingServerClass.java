package day20180720;

/*
 * ���� ������ ����� Ŭ������ ����ϴ�!!
 * 
 * 1. ȭ�� �����
 * 
 * 1) ù ��° �ٿ��� Ÿ��Ʋ ����ϱ� : ***ä�� ����***
 * 2) Ŭ���̾�Ʈ�κ��� �Է� ���� �޽������� ȭ�鿡 ������ִ�
 *    TextArea ��� â �����
 * * TextField : �� �ٸ� �Է� + ���
 * * TextArea : ���� �� �Է� + ��� 
 */
/*
 * ����(Socket)�� ����ؼ� Ŭ���̾�Ʈ�� ������ �Ǵ��ϱ�
 * 
 * 1. ������ �Ǵ��� ���� ServerSocket Ŭ������ ���
 * 2. Ŭ���̾�Ʈ�� �Ǵ��� ���� Socket Ŭ������ ���
 * 
 * 3. ���� ServerSocket Ŭ������ ����ؼ� ���� ���α׷��� ���� �����ϱ�
 * -> java.net ��Ű���� �ִ� Ŭ�����̹Ƿ� ����Ͻ÷���
 *    import java.net.ServerSocket; ��ɹ��� �ۼ�
 * 
 * -> ���� ���� ��ü�� �޸𸮿� �����ϱ� :
 *    ServerSocket �޸�_���������̸� = 
 *         new ServerSocket(��Ʈ��ȣ�� ������ �ۼ�);
 * 
 * -> ��Ʈ ��ȣ : �޸𸮿��� ����ǰ� �ִ� ���μ����� �������ִ� ��ȣ
 *    -> 0 ~ 65535 ���̿� �ִ� �������� ���
 *    -> 0 ~ 65535 ���̿� �ִ� ������ 
 *       �ٸ� ���α׷����� ������� ��쿡�� ����� �� �����ϴ�!!
 *       -> ServerSocket refServerSocket = 
 *               new ServerSocket(5000);
 *       -> ���࿡ �ٸ� ���α׷����� 5000 ��Ʈ�� ����ϰ� �ִ� ��쿡��
 *          ���ܻ�Ȳ�� �߻� : Address Already In Use ...
 *          ���α׷��� �ߴܵ˴ϴ�!!
 *          
 *       -> 1. ��� â�� �����Ͻ� ������ ����ϰ� ���� �ʴ� 
 *             ��Ʈ��ȣ�� �˾ƿ��� : ������ �ΰ� Ű + R Ű�� �����ø�
 *             ���� â�� ��µ˴ϴ�!! �Է� â�� cmd ��ɾ �Է��ϰ�
 *             �����մϴ�!!
 *             -> netstat -an : net + stat (���� ��Ʈ��ũ ���¸�
 *                ȭ�鿡 ���)
 *                
 *       -> 2. ServerSocket ���������̸� = new ServerSocket( 0 );
 *          -> ServerSocket ������ �Լ����� ���� 0�� �����ϸ�
 *             ������ �Լ� �ȿ��� ���� ��ǻ���� ��Ʈ��ũ����
 *             ����ϰ� ���� ���� ��Ʈ ��ȣ�� �ϳ��� �����ͼ�
 *             ��ȯ
 *          -> getLocalPort( ) �Լ��� ����ϸ� ��Ʈ ��ȣ��
 *             ������ �� �ֽ��ϴ�!!
 */
/*
 * 1. ���� �����츦 �ϳ� ���� ������ ȭ�鿡 �����츦 ����ϰڽ��ϴ�!! 
 */
import java.awt.*;
import java.awt.event.*;
// ��Ʈ��ũ ���� Ŭ���� �Ǵ� �������̽��� ����ϰ��� �ϴ� ��쿡�� java.net ��Ű���� ����Ʈ
import java.net.*;
// ��Ʈ��ũ���� ������ Ŭ���̾�Ʈ ���� �����͸� �ְ� �޴� ��쿡
// �߻��� �� �ִ� ���ܻ�Ȳ ó�� Ŭ������ ���� �ִ� java.io ��Ű���� ����Ʈ
// java.io : �Է°� ��� Ŭ������ �������̽��� ���� �ִ� ��Ű��
import java.io.*;
/*
 * ģ�� ��ǻ�� �ּҸ� �߸� �Է��� ��쿡�� UnknownHostException
 * ���ܻ�Ȳ�� �߻�
 * 
 */
// ȭ�鿡 �ϳ��� �����츦 ������ ������ Ŭ������ ��� �޴� Ŭ���� �����
class MyChattingServerFrameClass 
         extends Frame 
           implements WindowListener, ActionListener, FocusListener {

	// 0. ���� ���� Ŭ������ ����ؼ� Ŭ���̾�Ʈ ��ǻ�Ϳ���
	// ���� ���α׷��� �ν��� �� �ִ� ��Ʈ ��ȣ�� �������ִ� ��ü�� ����
	private ServerSocket refServerSocket = null;
	
	// 00. ServerSocket Ŭ�������� �������� ��ȯ���ִ� ��Ʈ ��ȣ��
	// ������ ������ ����
	private int portNo;
	
	// 000. Ŭ���̾�Ʈ�� ����(����)�� �� ��쿡 Ŭ���̾�Ʈ�� ����
	// ��ü�� �ּҸ� ������ �� �ִ� ������ ����
	private Socket refClientSocket = null;
	
	// 1. Ÿ��Ʋ�� ȭ�鿡 ������ �� ����� �� ��ü�� �����
	private Label titleLabel = 
			  new Label("***Chatting Server Window***",Label.CENTER);
	
	// 2. Ŭ���̾�Ʈ���� ���� �޽����� ȭ�鿡 ������ �� �����
	// TextArea ��ü�� �����
	private TextArea msgsTextArea = new TextArea(10, 50);
	// 10 : ���� �� �� (���� ����)
	// 50 : ���� ĭ �� (���� ����)
	
	// 3. �� ��ü�� �ؽ�Ʈ �Ʒ��� ��ü�� ������ �ؽ�Ʈ�� ���� �����
	// �ٲٱ� ���ؼ� ����� Font ��ü�� �����
	private Font font = new Font("�ü�ü", 
			              Font.BOLD + Font.ITALIC, 20);
	
	// 4. ������ �Լ��� �����
	public MyChattingServerFrameClass(String title) {
		super(title);
				
		/*
		 * Ŭ���̾�Ʈ���� ������ ������ �õ��� �� ����� ��ü�� ����� 
		 */
		try {			
			System.out.println("���� ���� ��ü�� �޸𸮿� �����ϱ�");
			
			refServerSocket = new ServerSocket(0);
			
			System.out.println("���ʿ��� ���� ���� ��ü�� ");
			System.out.println("���������� ����������ϴ�!!");
			
			// ServerSocket Ŭ������ ���� �ִ� getLocalPort()
			// �Լ��� ȣ���ؼ� �ü���κ��� �޾ƿ� ��Ʈ ��ȣ��
			// ��������
			portNo = refServerSocket.getLocalPort();
			
System.out.println("�ü���κ��� ������ ��Ʈ ��ȣ�� " + portNo);
			
		} catch(IOException ioexception) {
			
			System.out.println("��Ʈ��ũ ���� ���ܻ�Ȳ �߻�!!");
			System.out.println("������ " + ioexception.getMessage());
			ioexception.printStackTrace();
		}
		
		
		// 5. ������ ���� ��Ʈ ��ü�� �� ��ü�� �����ϱ�
		this.titleLabel.setFont(font);
		
		// 6. ������ ���� ��Ʈ ��ü�� �ؽ�Ʈ �Ʒ��� ��ü�� �����ϱ�
		this.msgsTextArea.setFont(font);
		
		// 7. �� ��ü�� ������ �ȿ��� ���� ���⿡ ���
		this.add(this.titleLabel, BorderLayout.NORTH);
		
		// 8. �ؽ�Ʈ �Ʒ��� ��ü�� ������ �ȿ��� ��� ���⿡ ���
		this.add(this.msgsTextArea, BorderLayout.CENTER);
		
		// 9. ����ڰ� ������ ���� �̺�Ʈ�� �߻���Ű��
		// �ڵ����� windowClosing( ) �Լ��� ȣ���ϱ� ���� ��ɹ� �ۼ�
		this.addWindowListener(this);
		
		// 10. �������� ũ�⸦ ȭ�� ��ü ũ��� �ٲٱ�
		/*
		 * 1) ���� ��ǻ�Ϳ� �����Ǿ� �ִ� �ػ� ũ�⸦ �������� 
		 * -> java.awt ��Ű���� �ִ� Toolkit Ŭ������ ���
		 *    -> getDefaultToolkit( ) �Լ��� ȣ��
		 *       -> getScreenSize( ) �Լ��� ȣ��       
		 *          -> Dimension Ÿ������ �ػ� ũ�⸦ ���α׷�����
		 *             ������ �ݴϴ�!!
		 * -> Dimension Ŭ���� : 
		 *     �������� ���� ũ��� ���� ũ�⸦ ���� �ִ� Ŭ����
		 *     ���� ũ��� width ��� ������ ����˴ϴ�!!
		 *     ���� ũ��� height ��� ������ ����˴ϴ�!!     
		 */
System.out.println("***���� �������� �ػ� ũ�⸦ �����ͼ� ");
System.out.println("�ӽ� ������ �����ϱ�***");
Dimension screenSizeDimension = 
               Toolkit.getDefaultToolkit().getScreenSize();
// setSize( ) �Լ��� ȣ���ؼ� ���� �޸𸮿� ������� �ִ� ��������
// ���� ũ��� ���� ũ�⸦ �ٲߴϴ�!!
this.setSize(screenSizeDimension.width, screenSizeDimension.height);

// setResizable( ) �Լ��� ȣ���ؼ� ����ڰ� ���Ƿ� �������� ���� ũ���
// ���� ũ�⸦ �ٲ��� ���ϵ��� �ϱ�
this.setResizable(false);

// �޸𸮿� ������ �����츦 ȭ�鿡 ����ϱ�
// -> setVisible( true );
this.setVisible(true);
		
	}
	
	
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

public class MyChattingServerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
MyChattingServerFrameClass 
      refMyChattingServerFrameClass 
= new MyChattingServerFrameClass("My First Chatting Server Window");
	}

}




