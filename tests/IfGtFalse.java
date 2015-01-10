package tests;
public class IfGtFalse{

	public static void main(String args[]) {
	    int j;   		// j = [-inf,inf]
	    int k = args.length ; 		    // k = [-inf,inf]
        if (k > 2)
		{ 				// k = [-inf,inf] join [3,inf] = [3,inf]
	    	j = 1;      
		}				// j = [1,1], k = [3,inf]
	    else
		{ //if (k <= 2)   // k = [-inf,inf] join [-inf,2] = [-inf,2]
			j = 0;
						 //j = [0,0], k = [-inf,2]
		} 	
		// union j = [0,1], k = [-inf,inf]
	}
}