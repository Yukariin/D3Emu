package com.d3emu.bnet.rpc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import bnet.protocol.RpcProto.Header;
import bnet.protocol.RpcProto.NoData;

import com.d3emu.bnet.rpc.services.*;

import com.google.protobuf.*;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

public class ConnectionHandler extends SimpleChannelInboundHandler<BNetPacket> {

    private static final Logger logger = Logger.getLogger("ConnectionHandler");

    private static final int REQUEST_SERVICE_ID = 0;

    private static final int RESPONSE_SERVICE_ID = 254;

    private AtomicInteger requestToken = new AtomicInteger();

    @Override
    public void channelRead0(ChannelHandlerContext ctx, BNetPacket msg) throws Exception {
        Header header = msg.getHeader();
        byte[] payload = (byte[]) msg.getPayload();
        printHeader(header);

        if (msg.getHeader().getServiceId() == RESPONSE_SERVICE_ID) {
            // FIXME: add proper response handling
        } else {
            Service s = ServiceRegistry.getService(header.getServiceHash());
            if (s != null) {
                Message proto = s.getRequestPrototype(header.getMethodId());
                Message message = proto.getParserForType().parseFrom(payload);

                s.callMethod(header.getMethodId(), ctx, message,
                    (Message m) -> { sendResponse(ctx, header.getToken(), m); }
                );
            } else {
                logger.severe(String.format(
                    "Client Requested an Unsupported (Service id: %d, hash: 0x%04X  Method id: %d)",
                    header.getServiceId(), header.getServiceHash(), header.getMethodId()
                ));
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(Level.SEVERE, cause.getMessage(), cause);

        ctx.close();
    }

    protected void printHeader(Header h) {
        String text = String.format("Packet received: Header = [ ServiceId: %d, ServiceHash: 0x%04X, MethodId: %d, Token: %d, Size: %d, Status: %d ]",
            h.getServiceId(),
            h.getServiceHash(),
            h.getMethodId(),
            h.getToken(),
            h.getSize(),
            h.getStatus()
        );
        
        logger.info(text);
    }

    public void sendRequest(ChannelHandlerContext ctx, int serviceHash, int methodId, Message request, RpcCallback done) {
        // FIXME: store callback
        sendRequest(ctx, serviceHash, methodId, request);
    }

    public void sendRequest(ChannelHandlerContext ctx, int serviceHash, int methodId, Message request) {
        sendRequest(ctx, serviceHash, methodId, requestToken.incrementAndGet(), request);
    }

    public static void sendRequest(ChannelHandlerContext ctx, int serviceHash, int methodId, int token, Message request) {
        Header.Builder builder = Header.newBuilder();
        builder.setServiceId(REQUEST_SERVICE_ID);
        builder.setServiceHash(serviceHash);
        builder.setMethodId(methodId);
        builder.setToken(token);
        builder.setSize(request.getSerializedSize());

        ctx.channel().writeAndFlush(new BNetPacket(builder.build(), request));
    }

    public static void sendResponse(ChannelHandlerContext ctx, int token, Message response) {
        Header.Builder builder = Header.newBuilder();
        builder.setServiceId(RESPONSE_SERVICE_ID);
        builder.setToken(token);
        builder.setSize(response.getSerializedSize());

        ctx.channel().writeAndFlush(new BNetPacket(builder.build(), response));
    }

    public static void sendResponse(ChannelHandlerContext ctx, int token, int status) {
        Header.Builder builder = Header.newBuilder();
        builder.setServiceId(RESPONSE_SERVICE_ID);
        builder.setToken(token);
        builder.setStatus(status);

        ctx.channel().writeAndFlush(new BNetPacket(builder.build(), NoData.getDefaultInstance()));
    }

    public static Header createHeader(int serviceId, int methodId, int objectId, int token, int size) {
        Header.Builder builder = Header.newBuilder();
        builder.setServiceId(serviceId);
        builder.setMethodId(methodId);
        if (objectId != 0) {
            builder.setObjectId(objectId);
        }
        builder.setToken(token);
        builder.setSize(size);
        
        return builder.build();
    }
}
