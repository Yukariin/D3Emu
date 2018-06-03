package com.d3emu.bnet.rpc;

public final class FNV1a {

    private static final int FNV1_32_INIT = 0x811c9dc5;

    private static final int FNV1_PRIME_32 = 16777619;

    public static int hash32(String str) {
        return hash32(str.getBytes());
    }
    
    public static int hash32(byte[] data) {
        return hash32(data, data.length);
    }

    public static int hash32(byte[] data, int length) {
        int hash = FNV1_32_INIT;
        for (int i = 0; i < length; i++) {
            hash ^= (data[i] & 0xff);
            hash *= FNV1_PRIME_32;
        }

        return hash;
    }
};
