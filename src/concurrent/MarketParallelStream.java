package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MarketParallelStream {

	
	List<Shop> shops = Arrays.asList( new Shop("Best Buy"), new Shop("London Drug"), new Shop("Walmart"), new Shop("Costco"));
	
	
	public List<String> findPrices(String product) {
		List< String> prices = shops.parallelStream().map(
				shop -> shop.getPriceFormat(product)
			)																		
			.collect( Collectors.toList());
		return prices;

		
	}
	
	public static void main(String[] args) {
		MarketParallelStream m = new MarketParallelStream();
		
		long start = System.nanoTime();
		
		System.out.println( m.findPrices("Iphone"));

		long duration = ( System.nanoTime() - start ) /1000000;
		System.out.println( "Time cost: " + duration + " mmsecs"); 
	}

}
