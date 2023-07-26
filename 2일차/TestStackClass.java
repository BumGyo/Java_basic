/*
 * ����(Stack) �ڷ� ������ �����ϱ�
 * 1. ũ�Ⱑ ������ ���� �迭�� �̿�
 * 2. ũ�Ⱑ ������ ���� �迭�� �̿�
 */
import java.util.*;
public class TestStackClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 1. Scanner Ŭ������ ����ؼ�
		 *    �迭�� ������ ������ ����ڷκ��� �Է�
		 *    �ޱ�
		 *    1) ��� ��� : java.util ��Ű���� �ֽ��ϴ�.
		 *    Scanner ���������̸� = 
		 *       new Scanner(Ű�����Է�: System.in);
		 */
System.out.println("Ű���� �Է¿� ����� ��ĳ�� ��ü�� �����ϱ�");
Scanner refScanner = new Scanner(System.in);
// System.in : static �����ν� �޸𸮿� �ִ� Ű������
// �ּҸ� ���� �ִ� �ڵ� ����(���� ����)
// new Scanner(�Է� �ҽ��� ����);
// 1. ���� �迭�� �����ϱ� : �ִ�10���� �������� ����
int iarray[] = new int[10];
// 2. ���� �迭�� �����ϱ� : �ִ� ������ ������ �޸� ������
ArrayList<Integer> refIntList = 
                       new ArrayList<Integer>();

// 3. iarray �迭�� ����Ǵ� ���� ������ ��������
// ��ġ�� ������ top ������ �����ϰ� -1�� �ʱ�ȭ
int top1 = -1;
// 4. ���� �迭 refIntList�� ����Ǵ� ���� ������ ��������
// ��ġ�� ������ top ������ �����ϰ� -1�� �ʱ�ȭ
int top2 = -1;
// 5. ���� �迭�� ����ؼ� �ִ� 10���� ������
// ����ڷκ��� �Է� �޾Ƽ� �迭�� �����ϴ� �ݺ�����
// �ۼ��ϱ�
// for(int i = 0; i < iarray.length; i++) {
for(int i = 0; i <= 10; i++) {
	System.out.print("���� �迭�� iarray�� "+i+
			 " ��ġ�� ������ ������ �Է��ϼ���: ");
	
	// ����ڰ� ������ �Է��� �� �ֵ����ϱ� ���ؼ�
	// Scanner Ŭ������ ���� �ִ� nextInt( ) �Լ��� ȣ��
	int value = refScanner.nextInt();
	
	// ���� �޸𸮿� �����͸� �����ϴ� ���� : push()
	/*
	 * top ������ ���� ���� ���� �˰����� �޶���
	 * -> -1 -> 0���� ����� �ּž� �մϴ�!! -> ++ ������
	 */
	top1++; // -1 -> 0
	/*
	 * ������ top ������ ���� �迭�� �� ��ȣ�� 
	 * ����� �� �ִ����� �˻��ϱ�
	 */
	if(top1 >= 0 && top1 < iarray.length) {
System.out.
      println("����ڷκ��� �Է� ���� ���� �迭�� ����");
iarray[top1] = value;
	}
	else {
System.out.println("���� �����÷ο� ������ �߻�!!");
System.out.
 println
("���� top�� ���� �迭�� ���� �ε��� ��ġ ������ "
		+ "�ʰ��߽��ϴ�!!");
	break; // for �ݺ����� Ż���ϱ�
	} // end of else
} // end of for

/*
 * ���� �ڷ� �������� �����͸� �������� ���� : pop
 * ���� ���� top1�� ��ġ���� �ϳ��� �����͸� �����ɴϴ�!!
 * -> ���� top1�� ��ġ�� �迭�� �ε��� ������ �ִ����� �˻�
 */
top1 = 9;
for(int i = 0; i <= 10; i++) {
	System.out.println
	  ("���� �迭 iarray["+top1+"] ��ġ���� �ϳ��� ");
	System.out.println("������ �����ͼ� ȭ�鿡 ���");
	if(top1 >= 0 && top1 < iarray.length) {
int value = iarray[top1];
System.out.println("������ ������ "+value+" �Դϴ�!!");
// ���� ���� ������ �������� �� ��ȣ�� �̵�(����)
--top1;
	}
	else {
System.out.println("���� ����÷ο� ����!!");
System.out.
    println("���ÿ��� �����͸� ������ �� �����ϴ�!!");
break;
	}
}


	} // end of main
} // end of class


