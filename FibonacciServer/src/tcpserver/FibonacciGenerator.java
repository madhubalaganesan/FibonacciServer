package tcpserver;

import java.util.ArrayList;

public class FibonacciGenerator {
	String reply = "";
	ArrayList<Integer> fibarr = new ArrayList<>();
	public String execute(int num) {
		try {
			for(int i=1; i<=num; i++)
			{
				fibarr.add(fibonacci(i));
			}
			System.out.println("fib arr values:::"+fibarr);
			reply = fibarr.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
	public static int fibonacci(int number){
		if(number == 1 || number == 2){
			return 1; 
		} 
		return fibonacci(number-1) + fibonacci(number -2); 
		}
}




	

