package tests;
public class TableSwitch{

	public static void main(String args[]) {
	    int j = 0;
		int i = 2;
		switch(i){
            case 1: {
                j = 1;  //i=bottom,j=[1,1]
                break;
                    }
            case 2: {
                j = 2;  //i=[2,2],j=[2,2]
                break;
                    }
			case 3: {
                j = 3;  //i=bottom,j=[3,3]
                break;
                    }
             default: {
                break;  //j=[0,0]
                      }
		} //i = [2,2], j =[0,3]
	}
}