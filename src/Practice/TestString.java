package Practice;

import java.util.Arrays;

public  class TestString {
	public static int mystery(String s)
	{
	    char[] letters = s.toCharArray();
	    int x = 0;
	    for (int i = 0; i < letters.length; i++) {
	        if (letters[i] == ' ') {
	           letters[i] = '_';
	           x++;
	        }
	    }
	    return x;
	}
	public static void main(String args[]) {
		// test case 1
//		String text = "My ";
//		String s2 = "String";
//		text = text + s2;
		//test case 2, printout: My
//		String text = "My ";
//		 text.concat("String");
		//test case3 
//		String s1 = "My String";
//		String text = s1;
		//test case4, compile error
//		String text = new String("My ");
//		text + new String("String");
		//System.out.println(text);
		
		
//		String myword = "I love you!";
//		int x = mystery(myword);
//		System.out.println(x);
//		System.out.println(myword);
		
		
		String s = "%one%%two%%%three%%%%";
	   // String[] x = s.split("%+");
		//String[] x = s.split("[a-z]+");
		//String[] x =s.split("[one,two,three]");
		String[] x= s.split("one|two|three");
		System.out.println(Arrays.toString(x));
	}

}
