package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.channel.v1.ChannelInvitationServiceProto.*;
import com.google.protobuf.RpcCallback;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public final class ChannelInvitationService extends bnet.protocol.channel.v1.ChannelInvitationService {

    private static final Logger logger = Logger.getLogger("ChannelInvitationService");

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<NoData> done) {
        logger.info(request.toString());
    }

    @Override
    public void sendInvitation(ChannelHandlerContext ctx, SendInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void acceptInvitation(ChannelHandlerContext ctx, AcceptInvitationRequest request, RpcCallback<AcceptInvitationResponse> done) {

    }

    @Override
    public void declineInvitation(ChannelHandlerContext ctx, DeclineInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void revokeInvitation(ChannelHandlerContext ctx, RevokeInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void suggestInvitation(ChannelHandlerContext ctx, SuggestInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void listChannelCount(ChannelHandlerContext ctx, ListChannelCountRequest request, RpcCallback<ListChannelCountResponse> done) {

    }
}
