package de.moldiy.vertxtest.netty;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("JOINT");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("DISCONECET");
		super.handlerRemoved(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = (ByteBuf) msg;
		try {
			System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
//	        while (in.isReadable()) { // (1)
//	            System.out.print((char) in.readByte());
//	            System.out.flush();
//	        }
		} finally {
			ReferenceCountUtil.release(msg);
		}

//		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		if (cause instanceof IOException) {
			System.out.println("[SERVER] ERROR: " + cause.getMessage());

		}
//		super.exceptionCaught(ctx, cause);
	}

}
