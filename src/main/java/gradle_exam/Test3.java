package gradle_exam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yy");
		
		String d = sd.format(date);
		System.out.println(sd.format(date));
		
		System.out.println(String.format("E0"+d+"001"));
		
		String tfempno = String.format("E0"+d+"011");
		System.out.println(tfempno);
		
		String d2 = tfempno.substring(4);
		System.out.println(Integer.parseInt(d2));
	}

}
