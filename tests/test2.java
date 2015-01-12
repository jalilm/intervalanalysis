package tests;

public class test2 {
	
	public static void main(String args[]) {
		foo(Integer.parseInt(args[0]));
	}
	
	public static void foo(int k) {
		int i;
		if (k > 2) {
			i = 3;
		} else {
			i = -3;
		}
	}
	
}