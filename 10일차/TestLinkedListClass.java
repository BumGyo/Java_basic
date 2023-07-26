package day20180725;

/*
 * �ܺ� Ŭ������ ���� Ŭ������ ���� ���� ����Ʈ��� �ϴ� �ڷ� ������
 * ǥ���ϱ�
 */
class MyLinkedListClass {
	
	// ��� ��� ��ü ���� ���� �����Ϳ� �ּҸ� ���� Ŭ������ ����
	static class MyNodeClass {
		// �����ʹ� ����
		int data;
		// �ڱ� ���� ������ ����
		MyNodeClass refNextNode;
	}
	
	// �޸𸮿� �����Ǵ� ù ��° ����� �ּҸ��� ������ ����
	static MyNodeClass refHeadNode = null;
	
	// �޸𸮿� ������ ����� �� ������ ������ ����
	static int size = 0;
	
	// ����ڷκ��� �ϳ��� ���� �����͸� �Է¹��� �� ����� ��ĳ�� ����
	static java.util.Scanner scanner = 
			   new java.util.Scanner(System.in); 
	
	/*
	 * �޸𸮿� ���ο� ��� ��ü�� ����
	 * ����ڷκ��� ��� ��ü�� ������ ���� �����͸� �Է� �ޱ�
	 * ���� ����� ��� ��ü�� �����Ƿ� refNextNode ���� ������ null ����
	 * ��� ��� ������ �޸𸮿� ������ ��� ��ü�� �����ϱ�
	 */
	public static void createNodeAndLink() {		
		System.out.println("createNodeAndLink() �Լ��� ȣ���!!");
		
		// 1. �޸𸮿� ���� ���� ��� ��ü�� �ּҸ� ������ ���� ����
		MyNodeClass refNextNode = null;
		
		// 2. ����ڷκ��� �ϳ��� ���� �Է��� �޾Ƽ� �ӽ÷� ������
		// ���� ����
		int tempData = 0;
		
		// 3. new �����ڸ� ����ؼ� �޸𸮿� ���ο� ��� ��ü�� ����
		refNextNode = new MyNodeClass();		
		System.out.println("***�޸𸮿� ���ο� ��� ��ü�� ����***");
		
		// 4. �޸𸮿� ������ ��� ��ü�� ������ ���� �����͸�
		// ����ڷκ��� �Է� �޽��ϴ�!!
		System.out.print("�����͸� �Է��ϼ���: ");
		tempData = scanner.nextInt();
		// 5. ����ڷκ��� �Է� ���� ���� �����͸� ��� ��ü�� ����		
		refNextNode.data = tempData;
		// 6. ������ �޸𸮿� ���� ��������� ��� ��ü�� �ּҸ�
		// ������ ���� ������ null ���� ����
		refNextNode.refNextNode = null;
		// 7. ����ڰ� �Է��� �����͸� ȭ�鿡 ���
		System.out.println(
				"����ڰ� �Է��� �����ʹ� "+refNextNode.data);
		// 8. ��ü�� �ּ�(Address)�� hashCode( ) �Լ��� ȣ���ؼ� Ȯ��
		System.out.println("���� ���� ����� �ּҴ� " + 
		        refNextNode.hashCode());
		
		// 9. ���� ���� ���� ��� ��带 ������ �־�� �մϴ�!!
		// -> ����1 : ��� ��带 ���ؼ� ù ��° ��忡 �����ϱ� ����
		// -> ����2 : ���� �۾��� ���� ������ ù ��° ���� ã�ư� ��
		// -> �ִ� ��(���)�� �����ϴ�!! -> refNextNode ���� �̸���
		// -> �� ��° ���, �� ��° ��� ... ��� ��忡�� �Բ� ���
		
		// 10. ���� ��� ����� ���� null������ �˻� : ���� null�̶��
		// -> �޸𸮿� ������ ��� ��ü�� ó�� ��ü
		// -> ���� null�� �ƴ� ��쿡�� ù ��° ����� ��ü �ּҸ�
		// -> ��� ��尡 ���� �ִ� ��찡 �˴ϴ�!!
		if(refHeadNode == null) {
			System.out.println("���� ��� ���� ����� ��尡 ����");
			System.out.println("��� ���� ����� �ּҸ� ��� ��忡 ");
			System.out.println("�����մϴ�!!");
			refHeadNode = refNextNode;
			System.out.println("��� ���� ù ��° ��尡 ����!!");
		} else {
			System.out.println("��� ��尡 ù ��° ����� �ּҸ� ");
			System.out.println("���� �ֽ��ϴ�!!");
			// ���� ��� ��带 ����ؼ� ã�ư� �� �ִ� ����
			// ������ ����� �ּҸ� ã�Ƽ� ��� ���� ��� ��ü��
			// �ּҸ� ����!!
			System.out.println("������ ��� ��ü�� �ּҸ� ã��!!");
			// ã�� ������ ����� �ּҸ� ������ ����
			MyNodeClass refLastNode = null;
			// ��带 ã�� �� ����� ������ �ʿ� : ��� �����
			// �ּҸ� �ӽ÷� ������ ����
			MyNodeClass refCurNode = refHeadNode;
			// refCurNode : ref(����) + Cur(����) + Node(���)
			// while �ݺ����� ���۵Ǳ� ���� ������ ����
			// ù ��° ����� �����մϴ�!!
			refLastNode = refCurNode;
			while(refCurNode != null) {
		System.out.println("��� Ž�� ���Դϴ�!!");
		System.out.println("��� ���� ã�ư� �� �ִ� ");
		System.out.println("���� ������ ��带 ã�� �ֽ��ϴ�!!");
		System.out.println("���� ã�ư� ����� �ּҴ� " + 
					refCurNode.hashCode());		
// ���� ����� �ּҷ� �̵��ϱ� ���� ���� ����� �ּҴ� ������ �����
// �ּҸ� ������ ������ ���� : ������ refCurNode ������ �����
// �ּҸ� �ٲٱ� ����
		refLastNode = refCurNode;
		refCurNode = refCurNode.refNextNode;		
			}
// while �ݺ����� ������ϴ�!! -> refCurNode ������ ����� �����
// �ּҰ� null�� ���
System.out.println("������ ����� �ּҸ� ã�ҽ��ϴ�!!");
refLastNode.refNextNode = refNextNode;
// ������ ����� ���� ��带 ��� ���� ���� ����� �ּҷ� ����
		}
		
		
	}
	
}


public class TestLinkedListClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyLinkedListClass.createNodeAndLink();
	
		// ���� �ݺ����� ���� ������ createNodeAndLink()
		// �Լ��� ��� �����ϰ� �մϴ�!!
		// -> ���� �ߴ��� ����ڷκ��� ���� 1 �Ǵ� 2�� �Է¹޾Ƽ� ó��		
	java.util.Scanner scanner2 = new java.util.Scanner(System.in);		
		// ����ڰ� �Է��� ���� 1 �Ǵ� 2�� ������ ���� ����
		int no = 1;		
		while(true) {
			MyLinkedListClass.createNodeAndLink();
			System.out.print("����Ϸ��� 1�� �Է��Ͻð�, ");
			System.out.print("�ߴ��Ͻ÷��� 2�� �Է��ϼ���: ");
			no = scanner2.nextInt();
			if(no == 2) {
				System.out.println("����ڰ� 2�� �Է�!!");
				break;
			}
			else if(no == 1) {
				System.out.println("����ڰ� 1�� �Է�!!");
				continue;
			}
		}
	}
}


