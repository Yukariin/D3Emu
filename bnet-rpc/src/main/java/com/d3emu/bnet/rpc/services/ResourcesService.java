package com.d3emu.bnet.rpc.services;

import bnet.protocol.ContentHandleProto.ContentHandle;
import bnet.protocol.resources.v1.ResourceServiceProto.ContentHandleRequest;
import com.google.protobuf.RpcCallback;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public final class ResourcesService extends bnet.protocol.resources.v1.ResourcesService {

    private static final Logger logger = Logger.getLogger("ResourcesService");

    @Override
    public void getContentHandle(ChannelHandlerContext ctx, ContentHandleRequest request, RpcCallback<ContentHandle> done) {
        logger.info(request.toString());
    }
}
