package tests;
public class LookupSwitch{

	public static void main(String args[]) {
	    int j = 0;
		int i = 2;
		switch(i){
            case 2: {
                j = 2;  //j=[2,2], i = [2,2]
                break;
                    }
            case 7: {
                j = 7;  //j=[7,7], i = bottom
                break;
                    }
             default: {
                break;  //j=[0,0], i = [2,2]
                      }
		} //i = [2,2], j =[0,7]
	}
}