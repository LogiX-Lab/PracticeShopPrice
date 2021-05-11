package concurrent;

public enum Money {
	
	USD("USD"), EUR("EUR");
	
	private final String code ;

	private Money(String code) {
		this.code = code;
	}
	
	
	
}
