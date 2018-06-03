package com.d3emu.bnet.rpc;

import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;

import io.netty.channel.ChannelHandlerContext;

public abstract class Service {
    
    public static final int INVALID_SERVICE_ID = 255;
    
    private String name;

    private int id;

    private int hash;
    
    public Service(String name) {
        this.name = name;
        this.id = INVALID_SERVICE_ID;
        this.hash = FNV1a.hash32(name);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHash() {
        return hash ;
    }

    public abstract void callMethod(int methodId, ChannelHandlerContext ctx, Message request, RpcCallback<Message> done);
    
    public abstract Message getRequestPrototype(int methodId);

    public abstract Message getResponsePrototype(int methodId);
}
