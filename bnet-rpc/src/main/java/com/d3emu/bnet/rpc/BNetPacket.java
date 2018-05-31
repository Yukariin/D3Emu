package com.d3emu.bnet.rpc;

import bnet.protocol.RpcProto.Header;

public class BNetPacket {

    private Header header;

    private Object payload;
    
    public BNetPacket() {}
    
    public BNetPacket(Header h, Object p) {
        header = h;
        payload = p;
    }

    public Header getHeader() {
        return header;
    }

    public Object getPayload() {
        return payload;
    }
}
