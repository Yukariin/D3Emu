package com.d3emu.bnet.rpc.services; 

import java.lang.Deprecated;
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

    @Deprecated
    public final void credentialUpdate(ChannelHandlerContext ctx, CredentialUpdateRequest request, RpcCallback<CredentialUpdateResponse> done) {}

    public final void subscribe(ChannelHandlerContext ctx, SubscriptionUpdateRequest request, RpcCallback<SubscriptionUpdateResponse> done) {}

    public final void unsubscribe(ChannelHandlerContext ctx, SubscriptionUpdateRequest request, RpcCallback<NoData> done) {}

    public final void getAccountState(ChannelHandlerContext ctx, GetAccountStateRequest request, RpcCallback<GetAccountStateResponse> done) {
        logger.info(request.toString());

        GetAccountStateResponse.Builder builder = GetAccountStateResponse.newBuilder();
        if (request.getOptions().getFieldAccountLevelInfo()) {
            AccountLevelInfo.Builder level = AccountLevelInfo.newBuilder();
            level.addLicenses(AccountLicense.newBuilder().setId(100))
                .addLicenses(AccountLicense.newBuilder().setId(110))  // WoW?
                .addLicenses(AccountLicense.newBuilder().setId(111))
                .addLicenses(AccountLicense.newBuilder().setId(168))  // D3(Vanilla)???
                .addLicenses(AccountLicense.newBuilder().setId(213))
                .addLicenses(AccountLicense.newBuilder().setId(249))  // WoW?
                .addLicenses(AccountLicense.newBuilder().setId(263))  // D3(RoS)???
                .addLicenses(AccountLicense.newBuilder().setId(265))  // WTCG
                .addLicenses(AccountLicense.newBuilder().setId(268))
                .addLicenses(AccountLicense.newBuilder().setId(274))
                .addLicenses(AccountLicense.newBuilder().setId(384))
                .addLicenses(AccountLicense.newBuilder().setId(434))
                .addLicenses(AccountLicense.newBuilder().setId(440))
                .addLicenses(AccountLicense.newBuilder().setId(10107))  // Hero?
                .addLicenses(AccountLicense.newBuilder().setId(10431))
                .addLicenses(AccountLicense.newBuilder().setId(10432))  // Hero?
                .addLicenses(AccountLicense.newBuilder().setId(10442))  // WTCG?
                .addLicenses(AccountLicense.newBuilder().setId(10686))  // WTCG?
                .addLicenses(AccountLicense.newBuilder().setId(10856))  // Hero?
                .addLicenses(AccountLicense.newBuilder().setId(10869))  // Hero?
                .addLicenses(AccountLicense.newBuilder().setId(16062))
                .addLicenses(AccountLicense.newBuilder().setId(16064));
            level.setPreferredRegion(1)  // US
                .setBattleTag("Tag#1")
                .setAccountPaidAny(true)
                .setHeadlessAccount(false);
            builder.setState(AccountState.newBuilder().setAccountLevelInfo(level));
            builder.setTags(AccountFieldTags.newBuilder().setAccountLevelInfoTag(0xDE80C3B7));
        }

        done.run(builder.build());
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
