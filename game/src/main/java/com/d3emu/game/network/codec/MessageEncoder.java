package com.d3emu.game.network.codec;

import com.d3emu.game.network.messages.GameMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<GameMessage> {

    @Override
    public void encode(ChannelHandlerContext ctx, GameMessage msg, ByteBuf out) throws Exception {
        msg.encode(new BitBuffer(out));
    }
}
