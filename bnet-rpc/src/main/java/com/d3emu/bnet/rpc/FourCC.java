package com.d3emu.bnet.rpc;

import java.lang.StringBuilder;

public class FourCC {

    private int value;

    public FourCC() {}

    public FourCC(int value) {
        this.value = value;
    }

    public FourCC(String stringValue) {
        setString(stringValue);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getString() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 24; i >= 0; i -= 8) {
            char c = (char)(value >> i & 255);
            if (c != '\0')
                sb.append(c);
        }
        return sb.toString();
    }

    public void setString(String str) {
        value = 0;
        int num = 0;
        while (num < str.length() && num < 4) {
            value = (value << 8 | (int)str.charAt(num));
            num++;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!FourCC.class.isAssignableFrom(obj.getClass()))
            return false;

        final FourCC other = (FourCC) obj;
        if (this.value != other.value)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return getString();
    }
}
