/*
 * ���� ���� : �ִ� 10���� ������ ������ �� �ִ� �迭��
 *  ����� ����ϴ� ����
 */
// �÷��ǿ��� �������ִ� ����Ʈ �������̽��� ���
import java.util.List;
// ArrayList Ŭ������ ���
import java.util.ArrayList; // �迭 + ���� ũ��

public class TestArrayClass {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * ��� �÷��� ���õ� �������̽���
		 * Ŭ�������� �Ϲ�ȭ(Generic) ������ ����
		 * -> ��� ������ �����͵��� �� ����(���� �� ����)
		 * -> ������ �� �޸𸮿� ������ �����͸� ����
		 * ��) List<String> : ���ڿ� ���� ����
		 * ��) List<Integer> : Wrapper Ŭ���� :
		 *     ������ ������ �ϴ� Ŭ���� :
		 *     ������ �����͸� �ܺηκ��� ��ȣ
		 *     Integer -> Ŭ���� �̸�
		 *     int -> �⺻ �ڷ��� �̸�
		 * List<Object> ����_�����̸�;     
		 */
		// ���ڿ��� ������ �� �ִ� ���� �迭��
		// ������ �� �ִ� ������ ����
		List<String> refStringList;		
		// new �����ڸ� ����ؼ� �⺻������ 10����
		// ���ڿ��� ������ �� �ִ� �迭�� ����
		refStringList = new ArrayList<String>();		
		// ���� ������ ����� �޸� ������ �����
		// �κ��� ���ÿ� �ϱ�
		List<String> refStringList2 = 
					new ArrayList<String>();		
		/*
		 * add( ) �Լ��� ȣ���ؼ� ���� �迭�� ���� ����
		 */
		refStringList.add("first");
		refStringList.add("second");
		refStringList.add("third");
		
		/*
		 * ���� �迭�� ���� �ִ� ���� ������ ����
		 * get( ) �Լ��� ȣ���Ͻø� �˴ϴ�!!
		 * -> get ( �� ��ȣ ); 
		 * 
		 */
		// size( ) �Լ��� ȣ���Ͻø� add( ) �Լ���
		// ȣ���� Ƚ���� �˷��ݴϴ�!! -> ������ ����
		int count = refStringList.size();
System.out.
  println("���� �迭�� �ִ� �������� ������ " + count);
if(count > 0) {
  System.out.println("���� ���� �迭���� �����Ͱ� ����");
  // ���� �迭�� �����Ǿ� �ִ� �����Ϳ� ���� : 
  // get(���ȣ)
  // -> �� ��ȣ : 0���� ���� size() - 1 ����
  for(int i = 0; i < count; i++) {
	  System.out.
	    println(
	     "���� �迭 refStringList["+i+
	       "] ��ġ�� �ִ� ���� "+refStringList.get(i));
  }
}
else {
  System.out.println("���� ���� �迭���� �����Ͱ� ����");
}

		
		
		
		int iarray[];
		
		iarray = new int[10];
		
		for(int index = 0; index < iarray.length; 
				index++)
			// sysout : ���ø�(Template) ����� ���
			// sysout + Ctrl Ű + Space Ű�� �����ø�
			// �ڵ����� System.out.println(); ��ɾ
			// ���Ե˴ϴ�!!
			System.out.
println
("�迭 iarray["+index+"] �濡 �����Ǿ� �ִ� ������ " + 
         iarray[index]);
	}
}
