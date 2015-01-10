package tests;
public class IfEq{

	public static void main(String args[]) {
	    int j;   						// j = [-inf,inf]
	    int k = args.length ; 		    // k = [-inf,inf]
        if (k == 2)
		{ 								// k = [2,2]
	    	j = 1;      
		}				// j = [1,1], k = [2,2]
	    else
		{ //if (k != 2)   // k = [-inf,inf]
			j = 0;
			k = 3;			 //j = [0,0], k = [3,3]
		} 	
		// union j = [0,1], k = [2,3]
	}
}