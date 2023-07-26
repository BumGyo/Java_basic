package day20180726;

/*
 * HashSet Ŭ������ ����ؼ� ���� ���� �����͵��� �޸𸮿� �����ϰ� ���
 * 
 * 1. Ư¡ : 
 *     �������� �Է� ������ �������� ����(�������� ��ġ�� ����)
 *     �Է��ߴ� ������ ��� ������ �ٸ� �� �ֽ��ϴ�!!
 *     �������� �ߺ��� ������� �ʽ��ϴ�!!
 *       -> ���� �̸��� �� ���� �޸𸮿� ������ �� �ֽ��ϴ�!!
 */
// HashSet Ŭ������ ����ϱ� ���ؼ� import ��ɾ �ۼ�
import java.util.HashSet;
/*
 * 
 * Set �迭�� Ŭ������ ����� ���� ���� ����
 * -> �����͸� �޸𸮿� ������ ���� add( ) �Լ��� ȣ��
 * -> �޸𸮿��� �����͸� ������ ���� get( ) �Լ��� ����� �� ����
 *    -> �ذ� : �ݺ���(Iterator)�� ��� : �ݺ� ��ü(Iteration Object)
 *       -> �ڵ����� �޸𸮿��� �����͸� �ϳ��� �������� ��ü
 *       
 * ��) int iarray [ ] = new int[ 5 ];
 *     for(int index = 0; index < iarray.length; index++)
 *        System.out.println(iarray[index]);
 *        
 *     ���� for �ݺ����� ����ϱ� : JDK 1.5���� ��� ����
 *     for( �迭�� ����Ǿ� �ִ� ������ �ڷ��� + ���� ������ ���� �̸� 
 *          + �ݷ�(:) + �迭�̸� )
 *        // ��ó�� �ۼ��ϸ� �迭���� ù ��° �� ���� ���ʴ�� �����ɴϴ�!!
 *        // ������ ���� -> ��ġ ��ȣ �̵�(�ٲٴ� ��)�� �ڵ�
 *        // -> int index = 0; index < �迭�̸�.length; index++
 *        
 * ��) // �ݺ��� ��ü�� ����ؼ� �޸𸮿� ����Ǿ� �ִ� �����͸�
 *     // �����;� �մϴ�!! -> Iterator �������̽��� ���
 *     Iterator<�޸𸮿� ����Ǿ� �ִ� �������� �ڷ���>
 *        ����_�����̸� = HashSet_��ü_���������̸�.iterator( );
 *        -> ����_�����̸� �ٷ� ���
 *    
 *     Iterator ����_�����̸�2 = HashSet_��ü_���������̸�.iterator( );
 *     -> (�� ��ȯ)����_�����̸�2
 *     
 *     while( ����_�����̸�.hasNext( )  == true ) {
 *     
 *        // hasNext( ) �Լ��� ��� : �޸𸮿� �����Ͱ� �ִ����� �Ǵ�
 *        // -> �����Ͱ� ������ ��(true)�� ��ȯ
 *        // -> �����Ͱ� ������ ����(false)�� ��ȯ
 *        
 *        // next( ) �Լ��� ȣ���ؾ� �޸𸮿� �����Ǿ� �ִ�
 *        // �����͸� ������ �� �ֽ��ϴ�!!
 *        String value = ����_�����̸�.next( );
 *        
 *        System.out.println("�޸𸮿��� ������ ���ڿ��� "+value);
 *     
 *     }
 */
// �ݺ��ڸ� ����ϱ� ���ؼ� java.util ��Ű���� �ִ� Iterator �������̽���
// �����ϱ�
import java.util.Iterator;

public class TestSetClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * ��� �̸�(���ڿ�)�� ���� �� ������ �� �ִ� 
		 * HashSet ��ü�� �޸𸮿� �����ϱ�
		 */
		HashSet<String> refHashSet = new HashSet<String>();
		
		/*
		 * ����ڷκ��� �޸𸮿� ������ ��� �̸��� �Է� �޵���
		 * �ϰڽ��ϴ�!!
		 * 1) java.util.Scanner Ŭ������ ����ؼ� ��ĳ�� ��ü�� ����
		 */
		java.util.Scanner inputScanner = 
						new java.util.Scanner(System.in);
		
		/*
		 * ����ڰ� �Է��� ��� �̸��� �ӽ÷� ������ ���� ����
		 */
		String tempName = "";
		
		/*
		 * ���� �ݺ��� ������ ���� ����ڷκ��� ��� �̸���
		 * �Է� �ް� HashSet �޸𸮿� ��� �̸��� �����ϰ�
		 * ���������� ����Ǿ����� ���θ� �˻��մϴ�!!
		 * ����ؼ� ��� �̸��� ����ڷκ��� �Է¹����� ���θ�
		 * �ٽ� ����ڿ��� ����ϴ�!! -> ����ڷκ��� y �Ǵ� n
		 * ���ڸ� �Է� �޽��ϴ�!! -> ����ڰ� n�� �Է��ϸ� ���
		 * �̸� �Է��� �ߴ��մϴ�!!
		 */
		char y_n = ' ';
		
		while(true) {
			
			System.out.print("��� �̸��� �Է��ϰ� ���͸� ��������: ");
			tempName = inputScanner.nextLine();
			
			boolean chk = refHashSet.add(tempName);
			if(chk == true) {
				System.out.println("����ڰ� �Է��� ��� �̸��� ");
				System.out.println("HashSet �޸𸮿� �����!!");
			} else {
				System.out.println("������ �Է��� ��� �̸��̹Ƿ� ");
				System.out.println("HashSet �޸𸮿� ������� ����!");
			}
			
			System.out.print("����Ϸ��� y�� �Է��Ͻð�, ");
			System.out.print("�ߴ��Ͻ÷��� n�� �Է��ϼ���: ");
			
			tempName = inputScanner.nextLine();
			y_n = tempName.charAt(0);
			if(y_n == 'y' || y_n == 'Y') {
				System.out.println("����ؼ� �ٸ� ����� �̸��� ");
				System.out.println("�Է� �ޱ� ���ؼ� ���� �̵�");
				continue;
			} else if(y_n == 'n' || y_n == 'N') {
				System.out.println("��� �̸� �Է��� �ߴ��մϴ�!!");
				break;
			} else {
				// ����ڰ� �ٸ� ���ڸ� �Է��� ���
				while(true) {
					System.out.print("������ y �Ǵ� n�� �Է��ϼ���: ");
					tempName = inputScanner.nextLine();
					y_n = tempName.charAt(0);
					if(y_n == 'y' || y_n == 'Y' || y_n == 'n' || 
					   y_n == 'N') {
						System.out.println("�Է� ����!!");
						break;
					}
				} // end of while(true)
				if(y_n == 'y' || y_n == 'Y') continue;
				if(y_n == 'n' || y_n == 'N') break;
			}
		}		
	}
}






