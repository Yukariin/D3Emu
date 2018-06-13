package com.d3emu.bnet.rpc.services;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import bnet.protocol.game_utilities.v1.GameUtilitiesServiceProto.*;
import bnet.protocol.notification.v1.NotificationListener;
import bnet.protocol.notification.v1.NotificationTypesProto.Notification;
import bnet.protocol.AttributeProto.*;
import bnet.protocol.EntityProto.EntityId;
import bnet.protocol.RpcProto.NoData;

import com.google.protobuf.ByteString;
import com.google.protobuf.RpcCallback;

import D3.Client.SettingsProto.*;
import D3.GameMessage.GameMessageProto.*;
import D3.Notification.NotificationProto.*;
import D3.OnlineService.OnlineServiceProto.*;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GameUtilitiesService extends bnet.protocol.game_utilities.v1.GameUtilitiesService {

    private static final Logger logger = LoggerFactory.getLogger(GameUtilitiesService.class);

    ScheduledThreadPoolExecutor initialLoginThreadPool = new ScheduledThreadPoolExecutor(2);

    public final void processClientRequest(ChannelHandlerContext ctx, ClientRequest request, RpcCallback<ClientResponse> done) {
        logger.debug(request.toString());

        ClientResponse.Builder builder = ClientResponse.newBuilder();
        Attribute.Builder attr = Attribute.newBuilder();

        int messageId = (int)request.getAttribute(1).getValue().getIntValue();
        switch (messageId) {
            case 0:  // HeroDigestListRequest -> HeroDigestListResponse???
                ByteString digest = onHeroDigestListRequest(ctx, request.getAttribute(2).getValue().getMessageValue());
                attr.setValue(Variant.newBuilder().setMessageValue(digest));
                break;
            case 1:  // HeroCreateParams -> CreateHeroResponse
                ByteString hero = onHeroCreateParams(ctx, request.getAttribute(2).getValue().getMessageValue());
                attr.setValue(Variant.newBuilder().setMessageValue(hero));
                break;
            case 6:  // InitialLoginDataRequest -> InitialLoginDataQueuedResponse
                ByteString loginData = onInitialLoginDataRequest(ctx, request.getAttribute(2).getValue().getMessageValue());
                attr.setValue(Variant.newBuilder().setMessageValue(loginData));
                break;
            case 7:  // CancelLoginDataRequest -> Empty Message
                // TODO: implement cancel mechanism
                attr.setValue(Variant.newBuilder().setMessageValue(ByteString.EMPTY));
                break;
            case 16:  // GetAccountPrefs -> (D3.Client.)Preferences
                ByteString prefs = onGetAccountPrefs(ctx);
                attr.setValue(Variant.newBuilder().setMessageValue(prefs));
                break;
            case 32:  // ??? -> ???
            default:
                logger.warn("Unknown CustomMessageId {}: {}",
                            messageId,
                            request.getAttributeCount() > 2 ? request.getAttribute(2).getValue().toString() : "No CustomMessage?");
                break;
        }
        

        if (attr.hasValue()) {
            attr.setName("CustomMessage");
            builder.addAttribute(attr);
        }

        done.run(builder.build());
    }

    public final void presenceChannelCreated(ChannelHandlerContext ctx, PresenceChannelCreatedRequest request, RpcCallback<NoData> done) {}

    public final void getPlayerVariables(ChannelHandlerContext ctx, GetPlayerVariablesRequest request, RpcCallback<GetPlayerVariablesResponse> done) {}

    public final void processServerRequest(ChannelHandlerContext ctx, ServerRequest request, RpcCallback<ServerResponse> done) {}

    public final void onGameAccountOnline(ChannelHandlerContext ctx, GameAccountOnlineNotification request) {}

    public final void onGameAccountOffline(ChannelHandlerContext ctx, GameAccountOfflineNotification request) {}

    public final void getAchievementsFile(ChannelHandlerContext ctx, GetAchievementsFileRequest request, RpcCallback<GetAchievementsFileResponse> done) {}

    public final void getAllValuesForAttribute(ChannelHandlerContext ctx, GetAllValuesForAttributeRequest request,RpcCallback<GetAllValuesForAttributeResponse> done) {}

    private ByteString onHeroDigestListRequest(ChannelHandlerContext ctx, ByteString data) {
        try {
            HeroDigestListRequest req = HeroDigestListRequest.parseFrom(data);
            logger.debug(req.toString());
        } catch (Exception e) {
            logger.warn("Invalid CustomMessage received", e);
            return ByteString.EMPTY;
        }

        HeroDigestListResponse.Builder res = HeroDigestListResponse.newBuilder();
        // FIXME: get real digest

        return res.build().toByteString();
    }

    private ByteString onHeroCreateParams(ChannelHandlerContext ctx, ByteString data) {
        try {
            HeroCreateParams req = HeroCreateParams.parseFrom(data);
            logger.debug(req.toString());
        } catch (Exception e) {
            logger.warn("Invalid CustomMessage received", e);
            return ByteString.EMPTY;
        }

        CreateHeroResponse.Builder res = CreateHeroResponse.newBuilder();
        res.setHeroId(123);  // FIXME: properly create hero

        return res.build().toByteString();
    }

    private ByteString onInitialLoginDataRequest(ChannelHandlerContext ctx, ByteString data) {
        try {
            InitialLoginDataRequest req = InitialLoginDataRequest.parseFrom(data);
            logger.debug(req.toString());
        } catch (Exception e) {
            logger.warn("Invalid CustomMessage received", e);
            return ByteString.EMPTY;
        }

        // TODO: implement cancel mechanism (store future?)
        initialLoginThreadPool.schedule(new InitialLoginTask(ctx), 2000L, TimeUnit.MILLISECONDS);

        InitialLoginDataQueuedResponse.Builder res = InitialLoginDataQueuedResponse.newBuilder();
        res.setServiceId(1)  // TODO: fix service id?
           .setTimeoutTickInterval(2000);

        return res.build().toByteString();
    }
    
    private ByteString onGetAccountPrefs(ChannelHandlerContext ctx) {
        Preferences.Builder res = Preferences.newBuilder();

        // TODO: set real prefs

        return res.build().toByteString();
    }

    private class InitialLoginTask implements Runnable {
    
        private ChannelHandlerContext ctx;
        
        public InitialLoginTask(ChannelHandlerContext ctx) { this.ctx = ctx; }

        public void run() {
            logger.trace("run()");

            InitialLoginDataResponse.Builder res = InitialLoginDataResponse.newBuilder();
            res.setErrorCode(0)
               .setServiceId(1);  // TODO: fix service id?
            // TODO: set initial login data

            Notification.Builder builder = Notification.newBuilder();
            builder.setSenderId(EntityId.newBuilder().setHigh(0).setLow(0));  // Server Id
            builder.setTargetAccountId(EntityId.newBuilder().setLow(1).setHigh(0x100000000000000L));  // FIXME: properly set BNet account id
            builder.setTargetId(EntityId.newBuilder().setLow(1).setHigh(0x200000100004433L));  // FIXME: properly set D3 account id

            builder.setType("D3.NotificationMessage");
            Attribute.Builder messageId = Attribute.newBuilder();
            messageId.setName("D3.NotificationMessage.MessageId")
                     .setValue(Variant.newBuilder().setIntValue(1));  // InitialLoginDataResponse
            Attribute.Builder payload = Attribute.newBuilder();
            payload.setName("D3.NotificationMessage.Payload")
                   .setValue(Variant.newBuilder().setMessageValue(res.build().toByteString()));
            builder.addAttribute(messageId);
            builder.addAttribute(payload);

            NotificationListener.newStub().onNotificationReceived(ctx, builder.build());
        }
    }
}
