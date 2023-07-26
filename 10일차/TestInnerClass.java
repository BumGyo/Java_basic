package day20180725;

/*
 * �ܺ� Ŭ������ MyOuterClass�� ���� �����մϴ�!!
 * 
 * ���� Ŭ������ MyInnerClass�� �ܺ� Ŭ���� �ȿ��� �����մϴ�!!
 */
class MyOuterClass {
	
	// ��� ���� a�� ����
	private int a = 100;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	// �ܺ� Ŭ������ ������ �Լ� �����
	public MyOuterClass() {
		System.out.println("�ܺ� Ŭ������ �⺻ ������ �Լ�");
	}
	
	// �ܺ� Ŭ������ ������ �Լ� ����� : �ܺ� Ŭ�����κ��� �ϳ���
	// ������ �޽��ϴ�!!
	public MyOuterClass(int value) {
		System.out.println("�ܺ� Ŭ������ �Ű� ������ ���� ������ �Լ�");		
		a = value;
		System.out.println("��� ������ a�� ���۰��� �ٲ�!!");
		System.out.println("��� ���� a�� ���۰��� " + a);
	}
	
	// ������ ���� ��� ���� a�� ���� ��ȯ�ϴ� �Լ� �����
	// -> getter �Լ� ����� : �ڵ����� �����
		
	// ���� Ŭ���� 
	class MyInnerClass {
		
		// ������ �Լ��� ����ϴ�!!
		// �⺻ ������ �Լ��� ����ϴ�!!
		public MyInnerClass() {
			System.out.println("���� Ŭ�������� ������ �⺻ ������");
			System.out.println("�ܺ� Ŭ�������� ������ ��� ���� a");
			System.out.println("�� ��� ����!!");
			System.out.println("��� ���� a�� ���� " + a);
		}
		
		// �ܺ� Ŭ�����κ��� �ϳ��� ���� ���� �޴� ������ �Լ��� �����
		public MyInnerClass(int value) {
			System.out.println("���� Ŭ�������� ������ �Ű� ������ ");
			System.out.println("���� ������ �Լ��� ����ϴ�!!");
			a = value;
			System.out.println("�ܺ� Ŭ�������� ������ ��� ���� a�� ");
			System.out.println("���� ���� �ٲߴϴ�!!");
		}
	}
}
public class TestInnerClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyOuterClass refOuterClass = new MyOuterClass(200);
		
		MyOuterClass.MyInnerClass refInnerClass = 
				          refOuterClass.new MyInnerClass(); 
		
	}

}
