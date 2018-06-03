package com.d3emu.bnet.rpc.services; 

import java.util.logging.Logger;

import bnet.protocol.account.v1.AccountServiceProto.*;
import bnet.protocol.account.v1.AccountTypesProto.*;
import bnet.protocol.RpcProto.NoData;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public final class AccountService extends bnet.protocol.account.v1.AccountService {

    private static final Logger logger = Logger.getLogger("AccountService");

    public final void getGameAccountBlob(ChannelHandlerContext ctx, GameAccountHandle request, RpcCallback<GameAccountBlob> done) {}

    public final void getAccount(ChannelHandlerContext ctx, GetAccountRequest request, RpcCallback<GetAccountResponse> done) {}

    public final void createGameAccount(ChannelHandlerContext ctx, CreateGameAccountRequest request, RpcCallback<GameAccountHandle> done) {}

    public final void isIgrAddress(ChannelHandlerContext ctx, IsIgrAddressRequest request, RpcCallback<NoData> done) {}

    public final void cacheExpire(ChannelHandlerContext ctx, CacheExpireRequest request) {}

    public final void credentialUpdate(ChannelHandlerContext ctx, CredentialUpdateRequest request, RpcCallback<CredentialUpdateResponse> done) {}

    public final void subscribe(ChannelHandlerContext ctx, SubscriptionUpdateRequest request, RpcCallback<SubscriptionUpdateResponse> done) {}

    public final void unsubscribe(ChannelHandlerContext ctx, SubscriptionUpdateRequest request, RpcCallback<NoData> done) {}

    public final void getAccountState(ChannelHandlerContext ctx, GetAccountStateRequest request, RpcCallback<GetAccountStateResponse> done) {
        logger.info(request.toString());
    }

    public final void getGameAccountState(ChannelHandlerContext ctx, GetGameAccountStateRequest request, RpcCallback<GetGameAccountStateResponse> done) {}

    public final void getLicenses(ChannelHandlerContext ctx, GetLicensesRequest request, RpcCallback<GetLicensesResponse> done) {}

    public final void getGameTimeRemainingInfo(ChannelHandlerContext ctx, GetGameTimeRemainingInfoRequest request, RpcCallback<GetGameTimeRemainingInfoResponse> done) {}

    public final void getGameSessionInfo(ChannelHandlerContext ctx, GetGameSessionInfoRequest request, RpcCallback<GetGameSessionInfoResponse> done) {}

    public final void getCAISInfo(ChannelHandlerContext ctx, GetCAISInfoRequest request, RpcCallback<GetCAISInfoResponse> done) {}

    public final void forwardCacheExpire(ChannelHandlerContext ctx, ForwardCacheExpireRequest request, RpcCallback<NoData> done) {}

    public final void getAuthorizedData(ChannelHandlerContext ctx, GetAuthorizedDataRequest request, RpcCallback<GetAuthorizedDataResponse> done) {}

    public final void accountFlagUpdate(ChannelHandlerContext ctx, AccountFlagUpdateRequest request) {}

    public final void gameAccountFlagUpdate(ChannelHandlerContext ctx, GameAccountFlagUpdateRequest request) {}

    public final void updateParentalControlsAndCAIS(ChannelHandlerContext ctx, UpdateParentalControlsAndCAISRequest request, RpcCallback<NoData> done) {}

    public final void createGameAccount2(ChannelHandlerContext ctx, CreateGameAccountRequest request, RpcCallback<CreateGameAccountResponse> done) {}

    public final void getGameAccount(ChannelHandlerContext ctx, GetGameAccountRequest request, RpcCallback<GetGameAccountResponse> done) {}

    public final void queueDeductRecord(ChannelHandlerContext ctx, QueueDeductRecordRequest request, RpcCallback<NoData> done) {}
}
