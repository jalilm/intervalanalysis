package tests;
public class IfGt{

	public static void main(String args[]) {
	    int j;   		// j = [-inf,inf]
	    int k = 5; 		// k = [5,5]
        if (k > 2)
		{ 				// k = [5,5] join [3,inf] = [5,5]
	    	j = 1;      
		}				// j = [1,1], k = [5,5]
	    else
		{ //if (k <= 2)   // k = [5,5] join [-inf,2] = bottom
			j = 0;
						 //j = [0,0], k = bottom
		} 	
		// union j = [0,1], k = [5,5]
	}
}