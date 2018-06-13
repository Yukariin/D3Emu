package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.notification.v1.NotificationServiceProto.*;
import bnet.protocol.notification.v1.NotificationTypesProto.Notification;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NotificationService extends bnet.protocol.notification.v1.NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Override
    public void sendNotification(ChannelHandlerContext ctx, Notification request, RpcCallback<NoData> done) {
        logger.debug(request.toString());

        // TODO: implement subscription mechanism

        done.run(NoData.getDefaultInstance());
    }

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
    public void publish(ChannelHandlerContext ctx, PublishRequest request, RpcCallback<NoData> done) {

    }
}
