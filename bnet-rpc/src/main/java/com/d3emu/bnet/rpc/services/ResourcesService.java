package com.d3emu.bnet.rpc.services;

import bnet.protocol.ContentHandleProto.ContentHandle;
import bnet.protocol.resources.v1.ResourceServiceProto.ContentHandleRequest;

import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResourcesService extends bnet.protocol.resources.v1.ResourcesService {

    private static final Logger logger = LoggerFactory.getLogger(ResourcesService.class);

    @Override
    public void getContentHandle(ChannelHandlerContext ctx, ContentHandleRequest request, RpcCallback<ContentHandle> done) {
        logger.debug(request.toString());
    }
}
