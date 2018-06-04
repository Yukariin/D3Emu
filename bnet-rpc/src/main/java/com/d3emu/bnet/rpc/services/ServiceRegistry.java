package com.d3emu.bnet.rpc.services;

import java.util.HashMap;
import java.util.Map;

import com.d3emu.bnet.rpc.Service;

public final class ServiceRegistry {

    private static final Map<Integer, Service> services =  new HashMap<Integer, Service>();

    public static void addService(Service s) {
        services.put(s.getHash(), s);
    }

    public static Service getService(int hash) {
        return services.get(hash);
    }
} 
