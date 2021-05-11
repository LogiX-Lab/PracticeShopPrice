package concurrent;

import java.util.Random;

/**
 * This is a shop
 *
 */
public class Shop0  {
	
	// Name of the Shop
	private String name;
	
	
	public Shop0(String name) {
		super();
		this.name = name;
	}

	// get the price for your producct
	public double getPrice(String product) {
		
		return this.calculatePrice(product);
	}
	
		
	
	public String getName() {
		return name;
	}
	
	
	public String getPriceFormat(String product) {
		System.out.println(Thread.currentThread() + "running getPriceFormat for " + this.getName());
		Random rad = new Random();
				
		return String.format("%s:%.2f", this.name, this.getPrice(product));
			
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
