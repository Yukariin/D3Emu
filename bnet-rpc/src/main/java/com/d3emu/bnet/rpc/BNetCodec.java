package com.d3emu.bnet.rpc;

import java.util.List;

import bnet.protocol.RpcProto.Header;

import com.google.protobuf.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.MessageToMessageCodec;

import static io.netty.buffer.Unpooled.*;

public class BNetCodec extends MessageToMessageCodec<BinaryWebSocketFrame, BNetPacket> {

    @Override
    public void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame frame, List<Object> out) throws Exception {
        if (frame.content().readableBytes() < 2) {
            return;
        }

        int headerSize = frame.content().readUnsignedShort();
        if (frame.content().readableBytes() < headerSize) {
            return;
        }
        byte[] headerBuf = new byte[headerSize];
        frame.content().readBytes(headerBuf);
        Header header = Header.parseFrom(headerBuf);

        int payloadSize = header.hasSize() ? header.getSize() : frame.content().readableBytes();
        if (frame.content().readableBytes() < payloadSize) {
            return;
        }
        byte[] payload = new byte[payloadSize];
        frame.content().readBytes(payload);
        
        out.add(new BNetPacket(header, payload));
    }

    @Override
    public void encode(ChannelHandlerContext ctx, BNetPacket msg, List<Object> out) throws Exception {
        if (!(msg.getPayload() instanceof Message)) {
            return;
        }

        Header header = msg.getHeader();
        Message payload = (Message) msg.getPayload();
        int headerSize = header.getSerializedSize();
        int payloadSize = payload.getSerializedSize();

        ByteBuf packet = buffer(2 + headerSize + payloadSize);
        packet.writeShort(headerSize);
        packet.writeBytes(header.toByteArray());
        packet.writeBytes(payload.toByteArray());

        out.add(new BinaryWebSocketFrame(packet));
    }
}
