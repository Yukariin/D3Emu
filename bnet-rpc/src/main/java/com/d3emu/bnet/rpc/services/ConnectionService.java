package com.d3emu.bnet.rpc.services; 

import java.util.logging.Logger;
import java.time.Instant;

import bnet.protocol.connection.v1.ConnectionServiceProto.*;
import bnet.protocol.RpcProto.NoData;
import bnet.protocol.RpcProto.ProcessId;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public final class ConnectionService extends bnet.protocol.connection.v1.ConnectionService {

    private static final Logger logger = Logger.getLogger("ConnectionService");

    public final void connect(ChannelHandlerContext ctx, ConnectRequest request, RpcCallback<ConnectResponse> done) {
        logger.info(request.toString());
        ConnectResponse.Builder builder = ConnectResponse.newBuilder();

        builder.setServerId(ProcessId.newBuilder().setLabel(12345).setEpoch(1527969963));  // FIXME: properly generate server id and start time
        builder.setServerTime(Instant.now().toEpochMilli());

        // TODO: add connection metering handlers

        if (request.getUseBindlessRpc()) {
            builder.setUseBindlessRpc(true);
            // TODO: add bindable services?
        }

        done.run(builder.build());
    }
    
    public final void bind(ChannelHandlerContext ctx, BindRequest request, RpcCallback<BindResponse> done) {
        
    }

    public final void echo(ChannelHandlerContext ctx, EchoRequest request, RpcCallback<EchoResponse> done) {
        
    }

    public final void forceDisconnect(ChannelHandlerContext ctx, DisconnectNotification request) {
        
    }

    public final void keepAlive(ChannelHandlerContext ctx, NoData request) {
        // Nothing to do?
    }

    public final void encrypt(ChannelHandlerContext ctx, EncryptRequest request, RpcCallback<NoData> done) {
        
    }

    public final void requestDisconnect(ChannelHandlerContext ctx, DisconnectRequest request) {
        logger.info(request.toString());
        // TODO: proper disconnection procedure?
        ctx.disconnect();
    }
}
