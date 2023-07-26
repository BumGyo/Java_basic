package day20180724;

/*
 * ������ ť �ڷ� ������ Ŭ������ �����
 */
class MyQueueClass {
	// 1. ���� �迭�� �޸𸮿� �����մϴ�!!
	// 1) �迭�� ũ�⸦ ����� �����ؼ� ���߿� �ٲ� �� ���� ��ġ������
	// ������ �� �ֵ��� �մϴ�.
	public static final int ARRAY_SIZE = 10;
	
	// 2) �ִ� 10���� ������ ������ �� �ִ� �迭�� �޸𸮿� �����մϴ�!!
	private int iarray [ ] = new int[ARRAY_SIZE];
	
	// 3) �Ӹ� ������ �����ϰ� -1�� �ʱ�ȭ �մϴ�!!
	// -> iarray �迭�� ����Ǿ� �ִ� ������ ������ ���� ����� ����
	private int front = -1;
	
	// 4) ���� ������ �����ϰ� -1�� �ʱ�ȭ �մϴ�!!
	// -> �迭 iarray�� ���ο� ���� �����͸� ������ ���� ����� ����
	// -> ���ο� ���� �������� �迭�� ��ġ(�� ��ȣ)�� �����ϴ�!!
	private int rear = -1;
	
	/*
	 * 5) ���ο� ���� �����͸� iarray �迭�� ������ �� �߻��� �� �ִ�
	 * ���� ��Ȳ�� ����ؾ� �մϴ�!!
	 * -> �迭�� ũ��(�迭�� ������ �� �ִ� �������� �ִ� ���� - 1)
	 *    -> ���� rear(���� ����)�� ���� 
	 *       �迭�� ������ �� �ִ� �������� �ִ� ���� - 1�� ���� ���
	 *       �迭�� ���ο� �����͸� ������ �� ���� ���� 
	 *       
	 * -> if ( rear == (�迭�� ũ�� - 1) ) {
	 *       // Queue Overflow ����
	 *    }
	 *    else {
	 *        // ���� ���� ������ �ϳ� �����մϴ�!!
	 *        rear++;
	 *        // �ϳ� ������ ���� ���� ��ġ�� ���ο� �����͸� �����ϱ�
	 *        iarray[rear] = ���ο����;
	 *    }
	 */
	// ť �����÷ο� ���¸� �˻����ִ� �Լ��� ���� ����ϴ�!!
	/*
	 * 1) �Լ� �̸� : isQueueOverflow( )
	 * 2) ��ȯ�� : �� �� : boolean
	 * 3) �˰��� : ���� ���� ������ rear�� �˻��մϴ�!!
	 *    ���� rear ���� �迭�� ũ�� - 1 �� ������ ��(true)�� ��ȯ�ϰ�
	 *    �׿��� ��쿡�� ����(false)�� ��ȯ�մϴ�!!
	 */
	public boolean isQueueOverflow() {
		// ��(true) �Ǵ� ����(false)�� ������ ���� ����
		boolean result;		
		if(rear == (ARRAY_SIZE - 1)) {
			result = true;
		} else {
			result = false;
		}		
		return result;
	}
	/*
	 * 3�� �����ڸ� ����Ͻø� ��ɾ �� �����ϰ� �ۼ��� �� �ֽ��ϴ�.
	 * return rear == (ARRAY_SIZE - 1) ? true : false;
	 */
	
	/*
	 * ���� ť�� ����ִ����� �Ǵ����ִ� �Լ��� ���� ����ϴ�!!
	 * 1) �Լ� �̸� : isQueueEmpty( ) �Ǵ� isQueueUnderflow( )
	 * 2) ��ȯ�� : �� �� : boolean
	 * 3) �˰��� : 
	 *     �Ӹ� ������ ���� ������ ���� ���� ���� �ִ� ���´�
	 *     �迭 iarray���� ������ �����Ͱ� ���� ����!!
	 *     ��(true)�� ��ȯ
	 *     �迭 iarray���� ������ �����Ͱ� �ִ� ���´�
	 *     �Ӹ� ������ ���� ������ �ٸ� ���� ���� �ִ� ����
	 *     ����(false)�� ��ȯ 
	 */
	public boolean isQueueEmpty() {
		boolean result;
		if(front == rear) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	public boolean isQueueEmpty2() {
		return front == rear ? true : false;
	}
	
	/*
	 * main( ) �Լ��κ��� �Ǵ� �ٸ� �Լ��κ��� �ϳ��� ���� �����͸�
	 * �޾ƿͼ� iarray �迭�� �����ϴ� �Լ��� ����ϴ�!!
	 * 1) �Լ� �̸� : insertQueue( )
	 * 2) �Ű� ���� : �ϳ��� ������ ������ ���� : int data
	 * 3) ��ȯ�� : ���� : void
	 * 4) �˰��� : 
	 *     ���� Queue �޸𸮿� �����͸� ������ �� �ִ��� ���θ�
	 *     isQueueOverflow( ) �Լ��� ȣ���ؼ� �˻��մϴ�!!
	 *     -> true�� ��쿡�� ȭ�鿡 �����͸� ������ �� �����ϴ� ���
	 *         �޽����� ����ϰ� �Լ��� ����
	 *     -> false�� ��쿡�� ���� ������ �ϳ� �����ϰ�
	 *        ���� ���� ��ġ�� ���ο� �����͸� �����մϴ�!! 
	 */
	public void insertQueue(int data) {
		System.out.println("insertQueue(int) �Լ��� ȣ��Ǿ����ϴ�");
		boolean result = isQueueOverflow();
		if(result == true) {
			System.out.println("���� ť �޸� ������ Overflow");
			System.out.println("���ο� �����͸� ������ �� �����ϴ�");
			return;
		} else {
			System.out.println("���� ť �޸� ������ �����͸� ");
			System.out.println("������ �� �ִ� �����Դϴ�!!");
			iarray[++rear] = data;
			System.out.println("�迭 iarray�� ���ο� �����͸� ����");
			System.out.println("���� ���� ���� rear�� ���� "+rear);
		}
	}
}


public class TestMyQueueClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
