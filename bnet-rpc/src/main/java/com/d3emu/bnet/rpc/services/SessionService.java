package com.d3emu.bnet.rpc.services;

import bnet.protocol.account.v1.AccountTypesProto.*;
import bnet.protocol.session.v1.SessionListener;
import bnet.protocol.session.v1.SessionServiceProto.*;
import bnet.protocol.RpcProto.NoData;

import com.d3emu.bnet.rpc.BNetProgramId;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SessionService extends bnet.protocol.session.v1.SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    public final void createSession(ChannelHandlerContext ctx, CreateSessionRequest request, RpcCallback<CreateSessionResponse> done) {
        logger.debug(request.toString());

        String session = "A7B5C8B0593FFEC100000000000BCABD";  // FIXME: properly generate session
        // TODO: store and expire generated sessions?
        CreateSessionResponse.Builder builder = CreateSessionResponse.newBuilder();
        builder.setSessionId(session);

        done.run(builder.build());
        
        SessionCreatedNotification.Builder n = SessionCreatedNotification.newBuilder();
        n.setIdentity(request.getIdentity())
         .setReason(0)
         .setSessionId(session);

        SessionListener.newStub().onSessionCreated(ctx, n.build());
    }

    public final void destroySession(ChannelHandlerContext ctx, DestroySessionRequest request, RpcCallback<NoData> done) {}

    public final void updateSession(ChannelHandlerContext ctx, UpdateSessionRequest request, RpcCallback<NoData> done) {}

    public final void getSessionCapacity(ChannelHandlerContext ctx, GetSessionCapacityRequest request, RpcCallback<GetSessionCapacityResponse> done) {}

    public final void getSessionsByBenefactor(ChannelHandlerContext ctx, GetSessionsByBenefactorRequest request, RpcCallback<GetSessionsByBenefactorResponse> done) {}

    public final void markSessionsAlive(ChannelHandlerContext ctx, MarkSessionsAliveRequest request, RpcCallback<MarkSessionsAliveResponse> done) {}

    public final void getSession(ChannelHandlerContext ctx, GetSessionRequest request, RpcCallback<GetSessionResponse> done) {}
}
