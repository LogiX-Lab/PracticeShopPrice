package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class MarketParallelStream {

	
	List<Shop> shops = Arrays.asList( 
			      new Shop("Best Buy")
			     ,new Shop("London Drug")
			     ,new Shop("Walmart")
			     ,new Shop("Costco")
			     ,new Shop("Amazon")
			     ,new Shop("Alibaba")
			    ,new Shop("JD")

			);



	public List<String> findPricesParallel(String product) {
		List< String> prices = shops.parallelStream().map(
				shop -> {
					System.out.println(Thread.currentThread() + "mapping to getPriceFormat for " + shop.getName());					
					return shop.getPriceFormat(product); }
			)																		
			.collect( Collectors.toList());
		return prices;
		
	}
	
	public List<String> findPricesNoBlock ( String product) {
		
		List<CompletableFuture<String>> priceFutures = 
				  shops.stream()
				  .map( 
						  shop -> CompletableFuture.supplyAsync(
						  		() ->  shop.getPriceFormat(product)
						  )
	                   )  
				  .collect( Collectors.toList());
		
		
		return priceFutures.stream().map( CompletableFuture :: join).collect( toList() );
	}
	
	public static void main(String[] args) {
		MarketParallelStream m = new MarketParallelStream();
		
		long start = System.nanoTime();
		System.out.println( m.findPricesParallel("Iphone"));
		long duration = ( System.nanoTime() - start ) /1000000;
		System.out.println( m.shops.size()+" shops with ParallelStream Time cost: " + duration + " mmsecs"); 

		start = System.nanoTime();
		System.out.println( m.findPricesNoBlock("Iphone"));
		duration = ( System.nanoTime() - start ) /1000000;
		System.out.println( m.shops.size()+" shops with supplyAsync Time cost: " + duration + " mmsecs"); 

	
	}

}
