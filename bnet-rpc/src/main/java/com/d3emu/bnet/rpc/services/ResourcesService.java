package com.d3emu.bnet.rpc.services;

import bnet.protocol.ContentHandleProto.ContentHandle;
import bnet.protocol.resources.v1.ResourceServiceProto.ContentHandleRequest;

import com.d3emu.bnet.rpc.BNetProgramId;
import com.d3emu.bnet.rpc.FourCC;

import com.google.protobuf.ByteString;
import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResourcesService extends bnet.protocol.resources.v1.ResourcesService {

    private static final Logger logger = LoggerFactory.getLogger(ResourcesService.class);

      // TODO: add to config?
    private static final byte[] PFTY_HASH = new byte[] { (byte)0xCF, (byte)0x61, (byte)0xE0, (byte)0x81, (byte)0x09, (byte)0x19, (byte)0xC6, (byte)0xA6, (byte)0xF9, (byte)0xC1, (byte)0xCB, (byte)0x24, (byte)0xB3, (byte)0xC6, (byte)0x9D, (byte)0x03, (byte)0xB0, (byte)0x37, (byte)0x08, (byte)0xEC, (byte)0x16, (byte)0xD9, (byte)0x44, (byte)0x51, (byte)0xC5, (byte)0x1F, (byte)0x90, (byte)0x38, (byte)0xE9, (byte)0x09, (byte)0xA7, (byte)0x5A };

    @Override
    public void getContentHandle(ChannelHandlerContext ctx, ContentHandleRequest request, RpcCallback<ContentHandle> done) {
        logger.debug(request.toString());

        ContentHandle.Builder builder = ContentHandle.newBuilder();
        if (request.getProgram() == BNetProgramId.BNET.getValue()) {
            builder.setRegion(21843);  // US
            builder.setUsage(1885762681);  // pfty

            switch (request.getStream()) {
                case 1634756212:  // apft
                    builder.setHash(ByteString.copyFrom(PFTY_HASH));
                    break;
                default:
                    logger.warn("Unknown Stream: {} ({})", new FourCC(request.getStream()).getString(), request.getStream());
                    builder.setHash(ByteString.EMPTY);
                    break;
            }

            done.run(builder.build());
        } else {
            logger.warn("Unknown Program: {} ({})", new FourCC(request.getProgram()).getString(), request.getProgram());
        }
    }
}
