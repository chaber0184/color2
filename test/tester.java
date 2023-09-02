package test;

public class tester {

	public static void main(String[] args) throws CloneNotSupportedException {
		OrderedLinkedList list = new OrderedLinkedList();
		list.add("red");
		System.out.println(list.toString());
		
		list.remove("red");
		System.out.println(list.toString());
		
		

		System.out.println(list.size());
		System.out.println(list.contains("red"));
		
		Object clone = list.clone();
		System.out.println(clone);
		System.out.println(list.equals(clone));

	}

}
