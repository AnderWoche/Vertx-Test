package de.moldiy.vertxtest.main;

import com.hazelcast.config.Config;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Main {
	
	public static void main(String[] agrs) {
		
		Config config = new Config();
		
		config.getGroupConfig().setName("my-cluster-name");
		
		HazelcastClusterManager clusterManager = new HazelcastClusterManager(config);
		
		VertxOptions options = new VertxOptions();
		options.setClusterManager(clusterManager);
		
		Vertx.clusteredVertx(options, resultHandler -> {
			if(resultHandler.succeeded()) {
				
				System.out.println("SUCCEESS");
				
//				resultHandler.result().deployVerticle(new SenderVerticle());
//				resultHandler.result().deployVerticle(new ReciveVerticle());
				
			} else {
				System.out.println(resultHandler.cause());
			}
		});
		
	}

}
