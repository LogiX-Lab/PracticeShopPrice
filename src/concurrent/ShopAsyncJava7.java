package concurrent;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ShopAsyncJava7 {

	Shop shop;
	ExecutorService executor = Executors.newFixedThreadPool(10);
    CompletionService<Double> executorCompletionService= new ExecutorCompletionService<>(executor);

	public ShopAsyncJava7(Shop shop) {
		super();
		this.shop = shop;
	}

	public Future<Double> getPriceAsync(String product) {

		return executor.submit( () -> {
			return shop.getPrice(product);
		});
		
		
	}
	
	public Future<Double> getPriceAsync2(String product) {

		return executorCompletionService.submit( () -> {
			return shop.getPrice(product);
		});
		
		
	}	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ShopAsyncJava7 shopa = new ShopAsyncJava7(new Shop("Best Buy"));
		
		Future<Double> price = shopa.getPriceAsync("Samsung Phone");
		try {
			System.out.println( price.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		shopa.getPriceAsync2("Apple Phone");
		
		Future<Double> price2 = shopa.executorCompletionService.take();
		System.out.println(price2.get());

	}

}
