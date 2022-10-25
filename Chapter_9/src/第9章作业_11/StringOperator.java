package µÚ9ÕÂ×÷Òµ_11;

public class StringOperator {	
		String process(String s) {
		char[] c = new char[s.length()];
		if((s.length())%2 == 0) {
			for(int i = 0; i < s.length(); i += 2) {
				c[i] = s.charAt(i + 1);
				c[i + 1] = s.charAt(i);			
			}	
		}
		else {
			for(int i = 0; i < s.length() - 1; i += 2) {
				c[i] = s.charAt(i + 1);
				c[i + 1] = s.charAt(i);			
			}
			c[s.length() - 1] = s.charAt(s.length() - 1);		
		}	
		String str = new String(c);
		return str;
	}	
}
