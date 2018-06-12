package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.user_manager.v1.UserManagerServiceProto.*;
import com.google.protobuf.RpcCallback;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public final class UserManagerService extends bnet.protocol.user_manager.v1.UserManagerService {

    private static final Logger logger = Logger.getLogger("UserManagerService");

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<SubscribeResponse> done) {
        logger.info(request.toString());
    }

    @Override
    public void addRecentPlayers(ChannelHandlerContext ctx, AddRecentPlayersRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void clearRecentPlayers(ChannelHandlerContext ctx, ClearRecentPlayersRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void blockPlayer(ChannelHandlerContext ctx, BlockPlayerRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void unblockPlayer(ChannelHandlerContext ctx, UnblockPlayerRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void blockPlayerForSession(ChannelHandlerContext ctx, BlockPlayerRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void unsubscribe(ChannelHandlerContext ctx, UnsubscribeRequest request, RpcCallback<NoData> done) {

    }
}
