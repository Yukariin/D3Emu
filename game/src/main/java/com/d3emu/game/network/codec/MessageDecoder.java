package com.d3emu.game.network.codec;

import java.util.List;

import com.d3emu.game.network.messages.JoinBNetGameMessage;
import com.d3emu.game.network.messages.SimpleMessage;
import com.d3emu.game.network.messages.VersionsMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageDecoder.class);

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        BitBuffer buf = new BitBuffer(in);
        int opcode = buf.readInt(10);
        LOGGER.debug("Opcode: {}", opcode);

        switch (opcode) {
            case 16:
                out.add(new JoinBNetGameMessage().decode(buf));
                break;
            case 20:
                out.add(new VersionsMessage().decode(buf));
                break;
            default:
                out.add(new SimpleMessage());
                break;
        }
    }
}
