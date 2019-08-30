package com.d3emu.game.network;

import com.d3emu.game.ThreadPoolManager;
import com.d3emu.game.network.messages.GameMessage;
import com.d3emu.game.tasks.RouteTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ConnectionHandler extends SimpleChannelInboundHandler<GameMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, GameMessage msg) throws Exception {
        LOGGER.trace(msg.toString());

        ThreadPoolManager.getInstance().executePacket(new RouteTask(ctx, msg));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error(cause.getMessage(), cause);

        ctx.close();
    }
}
