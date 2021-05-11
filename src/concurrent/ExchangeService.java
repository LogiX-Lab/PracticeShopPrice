package concurrent;

public class ExchangeService {
		
	
	public double getRate(Money from, Money to ) {
		Shop.delay(1200);
		System.out.println(Thread.currentThread() + "running getRate");
		
		return 0.80;
	}
}
