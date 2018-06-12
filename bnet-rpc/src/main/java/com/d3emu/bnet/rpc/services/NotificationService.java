package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.notification.v1.NotificationServiceProto.*;
import bnet.protocol.notification.v1.NotificationTypesProto.Notification;
import com.google.protobuf.RpcCallback;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public final class NotificationService extends bnet.protocol.notification.v1.NotificationService {

    private static final Logger logger = Logger.getLogger("NotificationService");

    @Override
    public void sendNotification(ChannelHandlerContext ctx, Notification request, RpcCallback<NoData> done) {
        logger.info(request.toString());
    }

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void unsubscribe(ChannelHandlerContext ctx, UnsubscribeRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void publish(ChannelHandlerContext ctx, PublishRequest request, RpcCallback<NoData> done) {

    }
}
