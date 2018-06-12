package com.d3emu.bnet.rpc.services;

import bnet.protocol.RoleTypesProto.Role;
import bnet.protocol.RpcProto.NoData;
import bnet.protocol.user_manager.v1.UserManagerServiceProto.*;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UserManagerService extends bnet.protocol.user_manager.v1.UserManagerService {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerService.class);

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<SubscribeResponse> done) {
        logger.debug(request.toString());

        // TODO: implement subscription mechanism

        SubscribeResponse.Builder builder = SubscribeResponse.newBuilder();
        builder.addRole(Role.newBuilder().setId(1).setName("battle_tag_block"))
               .addRole(Role.newBuilder().setId(2).setName("real_id_block"));

        done.run(builder.build());
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
