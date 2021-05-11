package concurrent;

import concurrent.Discount.Code;

public class Quote {
	private final String shopName;
	private final double price;
	private final Discount.Code disCountCode;
	public Quote(String shopName, double price, Code disCountCode) {
		super();
		this.shopName = shopName;
		this.price = price;
		this.disCountCode = disCountCode;
		System.out.println(Thread.currentThread() + "new Quote " + this.shopName);
		
	}
	public String getShopName() {
		return shopName;
	}
	public double getPrice() {
		return price;
	}
	public Discount.Code getDisCountCode() {
		return disCountCode;
	}
	
	public static Quote parse(String s) {
		String[] ss = s.split(":");
		String shopName = ss[0];
		double price = Double.parseDouble(ss[1]);
		Discount.Code discountCode = Discount.Code.valueOf(ss[2]);
		
		System.out.println(Thread.currentThread() + "running Quote for " + shopName);
		
		return new Quote( shopName, price, discountCode);
	}
	
}
