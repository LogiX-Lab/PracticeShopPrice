package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Market {

	
	List<Shop> shops = Arrays.asList( new Shop("Best Buy"), new Shop("London Drug"), new Shop("Walmart"), new Shop("Costco"));
	
	
	public List<String> findPrices(String product) {
		
		
		List< CompletableFuture<String>> futurePrices = shops.parallelStream().map(
					shop -> CompletableFuture.supplyAsync(
							() -> shop.getPriceFormat(product)
							)
				).map( futurePriceStr -> futurePriceStr.thenApply(Quote:: parse)
							.thenCombine(
									CompletableFuture.supplyAsync(
											() ->  new ExchangeService().getRate(Money.USD, Money.EUR) ), 
											(quote, rate ) -> new Quote(quote.getShopName(), quote.getPrice() * rate, quote.getDisCountCode() )
											)
				
						)
				.map ( 
					futureQuote -> futureQuote.thenCompose(
							quote -> CompletableFuture.supplyAsync(
									() -> Discount.applyDiscount(quote)
									)
							)	
				)																		
				.collect( Collectors.toList());
		
		
		return futurePrices.parallelStream().map(
					CompletableFuture::join
				).collect(Collectors.toList());
		
	}
	
	public static void main(String[] args) {
		Market m = new Market();
		
		long start = System.nanoTime();
		
		System.out.println( m.findPrices("Iphone"));

		long duration = ( System.nanoTime() - start ) /1000000;
		System.out.println( "Time cost: " + duration + " mmsecs"); 
	}

}
