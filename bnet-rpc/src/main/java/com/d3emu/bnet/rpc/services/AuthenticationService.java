package com.d3emu.bnet.rpc.services;

import java.lang.Deprecated;

import bnet.protocol.authentication.v1.AuthenticationListener;
import bnet.protocol.authentication.v1.AuthenticationServiceProto.*;
import bnet.protocol.challenge.v1.ChallengeListener;
import bnet.protocol.challenge.v1.ChallengeServiceProto.ChallengeExternalRequest;
import bnet.protocol.EntityProto.EntityId;
import bnet.protocol.RpcProto.NoData;

import com.d3emu.bnet.rpc.Config;

import com.google.protobuf.ByteString;
import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AuthenticationService extends bnet.protocol.authentication.v1.AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public final void logon(ChannelHandlerContext ctx, LogonRequest request, RpcCallback<NoData> done) {
        logger.debug(request.toString());

        if (!request.getProgram().equals("D3")) {
            logger.error("Unsupported program detected!");
            // TODO: return proper error code
        }
        if (!request.getPlatform().equals("Win")) {
            logger.error("Unsupported platform detected!");
            // TODO: return proper error code
        }
        if (!request.getLocale().equals("enUS")) {
            logger.error("Unsupported locale detected!");
            // TODO: return proper error code
        }

        done.run(NoData.getDefaultInstance());

        if (request.getWebClientVerification()) {
            // TODO: add cached_web_credentials handling

            // TODO: add logon queue with notifications?
            ChallengeExternalRequest.Builder builder = ChallengeExternalRequest.newBuilder();
            builder.setPayloadType("web_auth_url");
            builder.setPayload(ByteString.copyFromUtf8(String.format("https://%s:%d/bnet/login", Config.WEB_AUTH_ADDRESS, Config.WEB_AUTH_PORT)));

            ChallengeListener.newStub().onExternalChallenge(ctx, builder.build());
        }
    }

    @Deprecated
    public final void moduleNotify(ChannelHandlerContext ctx, ModuleNotification request, RpcCallback<NoData> done) {}

    @Deprecated
    public final void moduleMessage(ChannelHandlerContext ctx, ModuleMessageRequest request, RpcCallback<NoData> done) {}

    @Deprecated
    public final void selectGameAccountDEPRECATED(ChannelHandlerContext ctx, EntityId request, RpcCallback<NoData> done) {}

    public final void generateSSOToken(ChannelHandlerContext ctx, GenerateSSOTokenRequest request, RpcCallback<GenerateSSOTokenResponse> done) {}

    @Deprecated
    public final void selectGameAccount(ChannelHandlerContext ctx, SelectGameAccountRequest request, RpcCallback<NoData> done) {}

    public final void verifyWebCredentials(ChannelHandlerContext ctx, VerifyWebCredentialsRequest request, RpcCallback<NoData> done) {
        logger.debug(request.toString());

        // FIXME: add web token verification

        done.run(NoData.getDefaultInstance());

        LogonResult.Builder builder = LogonResult.newBuilder();
        builder.setErrorCode(0);
        builder.setAccountId(EntityId.newBuilder().setLow(1).setHigh(0x100000000000000L));  // FIXME: properly set BNet account id
        builder.addGameAccountId(EntityId.newBuilder().setLow(1).setHigh(0x200000100004433L));  // FIXME: properly set D3 account id
        // TODO: set web session key?
        AuthenticationListener.newStub().onLogonComplete(ctx, builder.build());
    }

    public final void generateWebCredentials(ChannelHandlerContext ctx, GenerateWebCredentialsRequest request, RpcCallback<GenerateWebCredentialsResponse> done) {}
}
