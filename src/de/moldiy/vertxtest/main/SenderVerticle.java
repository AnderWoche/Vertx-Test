package de.moldiy.vertxtest.main;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class SenderVerticle extends AbstractVerticle {
	
	int id = 0;
	
	public SenderVerticle() {
		id = (int) (Math.random() * 100);
	}
	
	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
	}
	
	@Override
	public void start() throws Exception {
		final EventBus eventBus = super.vertx.eventBus();
		
		while (true) {
			eventBus.publish("public.test", "Ich bin ein Server mit der ID: " + id);
			
			Thread.sleep(1000);
		}
		
		
	}

}
