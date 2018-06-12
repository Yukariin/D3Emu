package com.d3emu.bnet.rpc.services;

import java.util.logging.Logger;

import bnet.protocol.account.v1.AccountTypesProto.*;
import bnet.protocol.session.v1.SessionListener;
import bnet.protocol.session.v1.SessionServiceProto.*;
import bnet.protocol.RpcProto.NoData;

import com.d3emu.bnet.rpc.BNetProgramId;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public final class SessionService extends bnet.protocol.session.v1.SessionService {

    private static final Logger logger = Logger.getLogger("SessionService");

    public final void createSession(ChannelHandlerContext ctx, CreateSessionRequest request, RpcCallback<CreateSessionResponse> done) {
        logger.info(request.toString());

        String session = "A7B5C8B0593FFEC100000000000BCABD";  // FIXME: properly generate session
        // TODO: store and expire generated sessions?
        CreateSessionResponse.Builder builder = CreateSessionResponse.newBuilder();
        builder.setSessionId(session);

        done.run(builder.build());
        
        SessionCreatedNotification.Builder n = SessionCreatedNotification.newBuilder();
        AccountId.Builder a = AccountId.newBuilder();
        GameAccountHandle.Builder ga = GameAccountHandle.newBuilder();
        a.setId(1);  // FIXME: properly set BNet account id
        ga.setId(1)  // FIXME: properly set D3 account id
          .setProgram(BNetProgramId.DIABLO3.getValue())
          .setRegion(1);  // US
        n.setIdentity(Identity.newBuilder().setAccount(a).setGameAccount(ga));
        n.setReason(0).setSessionId(session);
        SessionListener.newStub().onSessionCreated(ctx, n.build());
    }

    public final void destroySession(ChannelHandlerContext ctx, DestroySessionRequest request, RpcCallback<NoData> done) {}

    public final void updateSession(ChannelHandlerContext ctx, UpdateSessionRequest request, RpcCallback<NoData> done) {}

    public final void getSessionCapacity(ChannelHandlerContext ctx, GetSessionCapacityRequest request, RpcCallback<GetSessionCapacityResponse> done) {}

    public final void getSessionsByBenefactor(ChannelHandlerContext ctx, GetSessionsByBenefactorRequest request, RpcCallback<GetSessionsByBenefactorResponse> done) {}

    public final void markSessionsAlive(ChannelHandlerContext ctx, MarkSessionsAliveRequest request, RpcCallback<MarkSessionsAliveResponse> done) {}

    public final void getSession(ChannelHandlerContext ctx, GetSessionRequest request, RpcCallback<GetSessionResponse> done) {}
}
