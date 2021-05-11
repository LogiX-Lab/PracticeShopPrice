package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ShopAsync {

	Shop shop;

	public ShopAsync(Shop shop) {
		super();
		this.shop = shop;
	}

	public Future<Double> getPriceAsync(String product) {
		/*
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			double price = shop.getPrice(product);
			futurePrice.complete(price);
		}).start();

		return futurePrice;
		*/
		
		return CompletableFuture.supplyAsync( () -> this.shop.getPrice(product));
	}

	public static void main(String[] args) {
		ShopAsync shopa = new ShopAsync(new Shop("Best Buy"));
		
		Future<Double> price = shopa.getPriceAsync("Beef");
		try {
			System.out.println( price.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
