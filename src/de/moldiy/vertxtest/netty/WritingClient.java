package de.moldiy.vertxtest.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class WritingClient {

	public static void main(String[] agrs) {
		try {
			new WritingClient(8693).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int port;

	public WritingClient(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap(); // (1)
			b.group(workerGroup); // (2)
			b.channel(NioSocketChannel.class); // (3)
			b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new SpamClientHandler());

				}
			});

			// Start the client.
			ChannelFuture f = b.connect("localhost", port).sync(); // (5)

			Channel channel = f.channel();

			System.out.println("CLIENT: CONNECTETT");

//			while(true) {

			String toSend = "Jaaa das ist meine String!";

			ByteBuf buf = channel.alloc().buffer(toSend.length());

			buf.writeCharSequence(toSend, CharsetUtil.US_ASCII);

			channel.writeAndFlush(buf);
			System.out.println("WRITE");

			Thread.sleep(1000);
//			}

//			channel.close();

			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

}
