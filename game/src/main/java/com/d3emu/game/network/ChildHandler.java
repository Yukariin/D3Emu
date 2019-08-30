package com.d3emu.game.network;

import com.d3emu.game.network.codec.MessageDecoder;
import com.d3emu.game.network.codec.MessageEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class ChildHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();

        p.addLast(new LengthFieldBasedFrameDecoder(0x8000, 1, 3, -4, 4));
        p.addLast(new LengthFieldPrepender(4, true));

        p.addLast(new MessageDecoder());
        p.addLast(new MessageEncoder());

        p.addLast(new ConnectionHandler());
    }
}
