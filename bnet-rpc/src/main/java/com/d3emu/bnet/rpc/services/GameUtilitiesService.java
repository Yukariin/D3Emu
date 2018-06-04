package com.d3emu.bnet.rpc.services;

import java.util.logging.Logger;

import bnet.protocol.game_utilities.v1.GameUtilitiesServiceProto.*;
import bnet.protocol.RpcProto.NoData;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public final class GameUtilitiesService extends bnet.protocol.game_utilities.v1.GameUtilitiesService {

    private static final Logger logger = Logger.getLogger("ConnectionService");

    public final void processClientRequest(ChannelHandlerContext ctx, ClientRequest request, RpcCallback<ClientResponse> done) {
        logger.info(request.toString());
    }

    public final void presenceChannelCreated(ChannelHandlerContext ctx, PresenceChannelCreatedRequest request, RpcCallback<NoData> done) {}

    public final void getPlayerVariables(ChannelHandlerContext ctx, GetPlayerVariablesRequest request, RpcCallback<GetPlayerVariablesResponse> done) {}

    public final void processServerRequest(ChannelHandlerContext ctx, ServerRequest request, RpcCallback<ServerResponse> done) {}

    public final void onGameAccountOnline(ChannelHandlerContext ctx, GameAccountOnlineNotification request) {}

    public final void onGameAccountOffline(ChannelHandlerContext ctx, GameAccountOfflineNotification request) {}

    public final void getAchievementsFile(ChannelHandlerContext ctx, GetAchievementsFileRequest request, RpcCallback<GetAchievementsFileResponse> done) {}

    public final void getAllValuesForAttribute(ChannelHandlerContext ctx, GetAllValuesForAttributeRequest request,RpcCallback<GetAllValuesForAttributeResponse> done) {}
}
