package com.d3emu.bnet.rpc.services;

import java.util.logging.Logger;

import bnet.protocol.RpcProto.NoData;
import bnet.protocol.session.v1.SessionServiceProto.*;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public final class SessionService extends bnet.protocol.session.v1.SessionService {

    private static final Logger logger = Logger.getLogger("SessionService");

    public final void createSession(ChannelHandlerContext ctx, CreateSessionRequest request, RpcCallback<CreateSessionResponse> done) {}

    public final void destroySession(ChannelHandlerContext ctx, DestroySessionRequest request, RpcCallback<NoData> done) {}

    public final void updateSession(ChannelHandlerContext ctx, UpdateSessionRequest request, RpcCallback<NoData> done) {}

    public final void getSessionCapacity(ChannelHandlerContext ctx, GetSessionCapacityRequest request, RpcCallback<GetSessionCapacityResponse> done) {}

    public final void getSessionsByBenefactor(ChannelHandlerContext ctx, GetSessionsByBenefactorRequest request, RpcCallback<GetSessionsByBenefactorResponse> done) {}

    public final void markSessionsAlive(ChannelHandlerContext ctx, MarkSessionsAliveRequest request, RpcCallback<MarkSessionsAliveResponse> done) {}

    public final void getSession(ChannelHandlerContext ctx, GetSessionRequest request, RpcCallback<GetSessionResponse> done) {}
}
