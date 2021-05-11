package concurrent;

public class Discount {
		public enum Code {
			NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
			
			private final int percentage ;

			private Code(int percentage) {
				this.percentage = percentage;
			}
			
			
		
		}
		
		public static String applyDiscount( Quote quote) {
			System.out.println(Thread.currentThread() + "running applyDiscount for " + quote.getShopName());			
			return quote.getShopName() + " price is " + Discount.apply( quote.getPrice(), quote.getDisCountCode()) ;
		}
		
		private static double apply( double price, Code code) {
			 Shop.delay(1000);
			 return  price*  ( 100 - code.percentage) / 100;
		}
}
