package com.d3emu.bnet.rpc;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import java.io.File;

public class ChildHandler extends ChannelInitializer<SocketChannel> {

    File cert = new File(Config.CERTIFICATE_PATH);
    File key = new File(Config.KEY_PATH);
    SslContextBuilder builder = SslContextBuilder.forServer(cert, key);

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();

        // SSL handshake
        SslContext ctx = builder.build();
        p.addLast(ctx.newHandler(socketChannel.alloc()));

        // WebSocket handshake and frames
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new WebSocketServerProtocolHandler("/", "v1.rpc.battle.net"));

        p.addLast(new BNetCodec());

        // Main handler
        p.addLast(new ConnectionHandler());
    }
}
