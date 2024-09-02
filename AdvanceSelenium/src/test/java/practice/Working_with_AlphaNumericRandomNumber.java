package practice;

public class Working_with_AlphaNumericRandomNumber {

	public static void main(String[] args) {
  int j=20;
  
  //Choose a character random from this string
  String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYX0123456789abcdefghijklmnopqrstuvwxyz";
  
  //create StringBuffer Size of AlphaNumbericString
  StringBuilder sb=new StringBuilder(j);
  
  for (int i=0; i<j; i++) {
	  //Generate a random number between 0 to AlphaNumbericString Variable length
	  int index=(int)(alphaNumericString.length()*Math.random());
	  
	  //add Character one by one in end of Sb
	  sb.append(alphaNumericString.charAt(index));
	  }
   System.out.println(sb);
   System.out.println(sb.length());
	}

}
