package day20180716;

/*
 * �л� ������ ����ڷκ��� �Է¹޾Ƽ�
 * �÷��� ������ �����մϴ�.
 * ���߿� �÷��ǿ� ����� �л� �������� �����ͼ�
 * ȭ�鿡 ����ϴ� ������ ����ϴ�!!
 * 
 * ���α׷� ���� ��� ȭ��
 * 
 * 1. ***�л� ���� �Է� ����(�÷��� ���)*** : Ÿ��Ʋ ���
 * 2. 1(�޴� ��ȣ). �Է�(��� �Ǵ� ����)
 * 3. 2(�޴� ��ȣ). ���(�÷��� ������ ����� ���
 *    �л� ���� �����͵��� �о�ͼ� ȭ�鿡 ���)
 * 4. 3(�޴� ��ȣ). ���α׷� ���� 
 */
// ���� �迭�� ����Ϸ��� java.util ��Ű���� �ִ�
// ArrayList Ŭ������ ����Ͻø� �˴ϴ�!!
// -> ���� ��Ű���� �ٸ� �̸��� ���� ��Ű���� ����Ϸ���
// -> ���ϰ� Ŭ���� �̸��� ����� �� Ŭ���� �̸��� �ۼ�
// -> import + ���� + ��Ű���̸� + Ŭ�����̸�;
import java.util.ArrayList;
// ����ڷκ��� �л� ���� �����͵��� �Է¹��� �� �����
// Scanner Ŭ������ �����ϱ�
import java.util.Scanner;

public class TestStudentScoreCollection {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 1. ���α׷����� ����� �������� ���� + 
		 *    �޸𸮿� �����ϴ� ��ɾ���� ��Ƴ���
		 */
		// 1) ����� Ű���� �Է½ÿ� ����� ��ĳ�� ����
		Scanner inputScanner = new Scanner(System.in);
		
		/*
		 * 2) ����ڰ� �Է��� �л� ���� �����͵���
		 * ������ �÷��� ���� : 
		 *   ����ڰ� �Է��� �л� ���� �����͵���
		 *   ���ڿ��� �����ϱ� : String Ŭ������ ���
		 */
		ArrayList<String> studentScoreList = 
				new ArrayList<String>();
		// "ȫ�浿,100,100,100"
		/*
		 * ������ ����ϰ� ���� ��쿡��
		 *    �̸��� String ���� �����Ͻð�
		 *    ���� ������ Integer ���� ����,
		 *    ���� ������ Integer ���� ����,
		 *    ���� ������ Integer ���� ����
		 *    -> ��� ����� ���ؼ��� ������ �޸� ����
		 */
		// ����ڰ� �Է��� �л� �������� ���ڿ��� ������
		// �ӽ� ������ ����
		String tempInput = ""; 
		// -> ������ ������ studentScoreList ����Ʈ��
		// -> ����� ���� ����
		// �ݺ��� ������ ���� �ݺ���
		while(true) {
			/*
			 * 1. ���� ���α׷� ������ ȭ�鿡 ���
			 */
System.out.println("***�л� ���� �Է�(�÷��� ���)***");
// 2. �޴����� ȭ�鿡 ����ϱ�
System.out.println("1. �л� ���� ���(�Է� �Ǵ� ����)");
System.out.println("2. ��� �л� �������� ���");
System.out.println("3. ���α׷� ����");
// ����ڷκ��� �Է� �޴� �޴� ��ȣ�� ���ڿ��� ������
// ������ ����
System.out.print("1 ~ 3 ���̿� �ִ� �޴� ��ȣ�� �Է�: ");
String inputMenuNo = inputScanner.nextLine();
// ����ڰ� �Է��� �޴� ��ȣ�� ���� ��ɾ �޸� ó��
// -> ���ڿ� �񱳴� equals() �Լ��� ȣ��
if(inputMenuNo.equals("1")) {
	System.out.println("����ڰ� �޴� ��ȣ�� 1�� �Է�");
	System.out.print("�л� �̸�,����,����,���� �������� ");
	System.out.print("���ʴ�� �Է��� �Ŀ� ���͸� ��������: ");
	tempInput = inputScanner.nextLine();
	// add( ) �Լ��� ȣ���ؼ� ����Ʈ ������ �����ϱ�
	if(tempInput.equals("") == false) {
		studentScoreList.add(tempInput);
System.
out.println
  ("����Ʈ ������ ���ο� �л� ������ �����Ͽ����ϴ�!!");
	} // end of if(tempInput.equals("") == false)
} // end of if(inputMenuNo.equals("1"))
else if(inputMenuNo.equals("2")) {
	System.out.println("����ڰ� 2�� �޴��� ����");
	System.out.println("����Ʈ ������ ���� �ִ� ");
	System.out.println("��� �л� �������� �о�ͼ� ");
	System.out.println("ȭ�鿡 ����ϱ�!!");
	// 1) ����Ʈ ������ ����Ǿ� �ִ� �л� ������ �ִ��� ���θ� �˻��ϱ�
	// -> ����Ʈ ������ ���� �ִ� size( ) �Լ��� ȣ���ϸ�
	// -> �� ���� �л� ������ ����Ǿ� �ִ����� �� �� �ֽ��ϴ�!!
	int count = studentScoreList.size();
	if(count == 0) {
System.out.
        println("���� ����� �л� ������ �����ϴ�!!");
	}
	else {
System.out.println("���� ����� �л� ���� �� ���� " + 
	    count);		

// ���� ����Ʈ ������ �����Ǿ� �ִ� �л� ���� �����͵��� �����ͼ�
// ȭ�鿡 ����ϴ� �ݺ����� �ۼ��ϱ�
for(int index = 0; index < count ; index++) {
	// get( ) �Լ��� ȣ���ؼ� ����Ʈ ������ ���� �ִ� �л� ����
	// ���ڿ��� �о�ɴϴ�!!
	String data = studentScoreList.get(index);
	System.out.println(index + 
		 " ��ġ�� �����Ǿ� �ִ� �л� ������ " + data + " �Դϴ�!!");
} // end of for(int index = 0; index < count ; index++)
	} // end of else
} // end of else if(inputMenuNo.equals("2")) {
else if(inputMenuNo.equals("3")) {
	System.out.println("����ڰ� �޴� ��ȣ 3�� �Է�!!");
	System.out.println("���� �ݺ����� ����ϴ�!!");
	break;
	// main( ) �Լ� ��ü�� �����Ͻ÷��� System Ŭ������ ���� �ִ�
	// exit( ) �Լ��� ȣ�� : System.exit(0);
}


		}
		
		
		
		
		
	}

}
