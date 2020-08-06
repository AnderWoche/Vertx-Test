package de.moldiy.vertxtest.netty;

public class NettyMain {

	public static void main(String[] args) {
		try {
			new DiscardServer(8693).run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
