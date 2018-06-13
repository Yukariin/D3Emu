package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.presence.v1.PresenceServiceProto.*;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PresenceService extends bnet.protocol.presence.v1.PresenceService {

    private static final Logger logger = LoggerFactory.getLogger(PresenceService.class);

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<NoData> done) {
        logger.debug(request.toString());

        // TODO: implement subscription mechanism

        done.run(NoData.getDefaultInstance());
    }

    @Override
    public void unsubscribe(ChannelHandlerContext ctx, UnsubscribeRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void update(ChannelHandlerContext ctx, UpdateRequest request, RpcCallback<NoData> done) {
        logger.debug(request.toString());

        // TODO: implement subscription mechanism

        done.run(NoData.getDefaultInstance());
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
