package tests;

public class test1 {

	public static void main(String args[]) {
		foo();
	}
	
	public static void foo() {
		int k = 5;
		int j = 5 - k;
		
		if ( k > j)
			k = 4 - 1;
		
		if (j < k)
			j = j - 4;
		
		if (k >= j)
			k = 3 - j;
		
		if( k <= j)
			k = k -j;
		
		int m = k - j;
		
		if ( m < 0)
			m = 0;
			

	}

}