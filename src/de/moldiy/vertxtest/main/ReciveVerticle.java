package de.moldiy.vertxtest.main;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class ReciveVerticle extends AbstractVerticle {

	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
	}
	
	@Override
	public void start() throws Exception {
		final EventBus eventBus = vertx.eventBus();
		eventBus.consumer("public.test", receivedMessage -> {
			System.out.println("Received message: " + receivedMessage.body());
		});

		super.start();
	}

}
