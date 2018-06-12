package com.d3emu.bnet.rpc.services;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.friends.v1.FriendsServiceProto.*;
import bnet.protocol.friends.v1.FriendsTypesProto.SubscribeResponse;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FriendsService extends bnet.protocol.friends.v1.FriendsService {

    private static final Logger logger = LoggerFactory.getLogger(FriendsService.class);

    @Override
    public void subscribe(ChannelHandlerContext ctx, SubscribeRequest request, RpcCallback<SubscribeResponse> done) {
        logger.debug(request.toString());
    }

    @Override
    public void sendInvitation(ChannelHandlerContext ctx, SendInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void acceptInvitation(ChannelHandlerContext ctx, AcceptInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void revokeInvitation(ChannelHandlerContext ctx, RevokeInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Deprecated
    @Override
    public void declineInvitation(ChannelHandlerContext ctx, DeclineInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void ignoreInvitation(ChannelHandlerContext ctx, IgnoreInvitationRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void removeFriend(ChannelHandlerContext ctx, RemoveFriendRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void viewFriends(ChannelHandlerContext ctx, ViewFriendsRequest request, RpcCallback<ViewFriendsResponse> done) {

    }

    @Override
    public void updateFriendState(ChannelHandlerContext ctx, UpdateFriendStateRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void unsubscribe(ChannelHandlerContext ctx, UnsubscribeRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void revokeAllInvitations(ChannelHandlerContext ctx, RevokeAllInvitationsRequest request, RpcCallback<NoData> done) {

    }

    @Override
    public void getFriendList(ChannelHandlerContext ctx, GetFriendListRequest request, RpcCallback<GetFriendListResponse> done) {

    }

    @Override
    public void createFriendship(ChannelHandlerContext ctx, CreateFriendshipRequest request, RpcCallback<NoData> done) {

    }

    @Deprecated
    @Override
    public void setAttribute(ChannelHandlerContext ctx, SetAttributeRequest request, RpcCallback<NoData> done) {

    }
}
