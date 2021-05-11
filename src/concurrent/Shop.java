package concurrent;

import java.util.Random;

public class Shop  {
	
	private String name;
	
	
	public Shop(String name) {
		super();
		this.name = name;
	}

	public double getPrice(String product) {
		
		return this.calculatePrice(product);
	}
	
		
	
	public String getName() {
		return name;
	}
	
	
	public String getPriceFormat(String product) {
		System.out.println(Thread.currentThread() + "running getPriceFormat for " + this.getName());
		Random rad = new Random();
		
		Discount.Code code = Discount.Code.values() [  rad.nextInt((Discount.Code.values().length)) ] ;
		
		return String.format("%s:%.2f:%s", this.name, this.getPrice(product), code);
			
	}
		

	private double calculatePrice(String product) {
		delay(1000);
		Random rand = new Random();

		return Math.abs(rand.nextDouble()) * product.charAt(0) + product.charAt(1);
	}
	
	public static void delay (long d) {
		
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
