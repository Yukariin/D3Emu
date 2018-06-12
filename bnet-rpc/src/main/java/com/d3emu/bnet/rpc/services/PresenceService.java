package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.presence.v1.PresenceServiceProto.*;
import com.google.protobuf.RpcCallback;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public final class PresenceService extends bnet.protocol.presence.v1.PresenceService {

    private static final Logger logger = Logger.getLogger("PresenceService");

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<NoData> done) {
        logger.info(request.toString());
    }

    @Override
    public void unsubscribe(ChannelHandlerContext ctx, UnsubscribeRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void update(ChannelHandlerContext ctx, UpdateRequest request, RpcCallback<NoData> done) {
        logger.info(request.toString());
    }

    @Override
    public void query(ChannelHandlerContext ctx, QueryRequest request, RpcCallback<QueryResponse> done) {

    }

    @Override
    public void ownership(ChannelHandlerContext ctx, OwnershipRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void subscribeNotification(ChannelHandlerContext ctx, SubscribeNotificationRequest request, RpcCallback<NoData> done) {

    }
}
