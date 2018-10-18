package com.d3emu.game.network.codec;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitBuffer {

    private static final Logger logger = LoggerFactory.getLogger(BitBuffer.class);

    private ByteBuf buffer;

    private int readBuffer;
    private int readCount;

    private int writeBuffer;
    private int writeCount;

    public BitBuffer(ByteBuf buf) {
        buffer = buf;
        readBuffer = 0;
        readCount = 0;
    }

    public int readInt(int length) {
        if (length < 0 || length > 32) {
            throw new IllegalArgumentException("length: " + length + " (expected: 0-32)");
        }

        int result = 0;
        while (length > 0) {
            if (readCount == 0) {
                readBuffer = buffer.readByte();
                readCount = 8;
            }
            if (readCount >= length) {
                int mask = (1 << length) - 1;
                result <<= length;
                result |= (readBuffer >> (8 - readCount)) & mask;
                readCount -= length;
                length = 0;
            } else {
                int mask = (1 << readCount) - 1;
                result <<= readCount;
                result |= (readBuffer >> (8 - readCount)) & mask;
                length -= readCount;
                readBuffer = 0;
                readCount = 0;
            }
        }

        return result;
    }

    public void writeInt(int length, int value) {
        if (length < 0 || length > 32) {
            throw new IllegalArgumentException("length: " + length + " (expected: 0-32)");
        }

//         while (length > 0) {
//             int off = bitPos & 7;
//             int count = 8 - off;
//             if (count > length)
//                 count = length;
//             int mask = (1 << count) - 1;
//             buffer[bitPos >> 3] = (byte)((buffer[bitPos >> 3] & (~(mask << off))) | (((value >> (length - count)) & mask) << off));
//             length -= count;
//             bitPos += count;
//             if (bitPos > bitLen)
//                 bitLen = bitPos;
//         }
    }

    public long readInt64(int length) {
        if (length < 0 || length > 64) {
            throw new IllegalArgumentException("length: " + length + " (expected: 0-64)");
        }

        int count = length >= 32 ? 32 : length;
        long result = readInt(count);
        count = length - count;
        if (count > 0)
            result = (result << count) | readInt(count);

        return result;
    }

    public void writeInt64(int length, long value) {
        if (length < 0 || length > 64) {
            throw new IllegalArgumentException("length: " + length + " (expected: 0-64)");
        }

        if (length <= 32) {
            writeInt(length, (int) value);
        } else {
            int count = length - 32;
            writeInt(32, (int) (value >> count));
            writeInt(count, (int) value);
        }
    }

    public float readFloat() {
        int bits = readInt(32);
        return Float.intBitsToFloat(bits);
    }

    public void writeFloat(float value) {
        int bits = Float.floatToRawIntBits(value);
        writeInt(32, bits);
    }

    public String readCharArray(int sizeBits) {
        int size = readInt(sizeBits);
        String result = buffer.readCharSequence(size, StandardCharsets.UTF_8).toString();
        readBuffer = 0;
        readCount = 0;

        return result;
    }

    public void writeCharArray(int sizeBits, String value) {
//         byte[] result = value.getBytes();
//         writeInt(sizeBits, result.length);
//         bitPos = (bitPos + 7) & (~7);
//         System.arraycopy(result, 0, result, bitPos >> 3, result.length);
//         bitPos += result.length * 8;
//         if (bitPos > bitLen)
//             bitLen = bitPos;
    }

    public byte[] readBlob(int sizeBits) {
        int size = readInt(sizeBits);
        byte[] result = new byte[size];
        buffer.readBytes(result);
        readBuffer = 0;
        readCount = 0;

        return result;
    }

    public void writeBlob(int sizeBits, byte[] data) {
//         writeInt(sizeBits, data.length);
//         bitPos = (bitPos + 7) & (~7);
//         System.arraycopy(data, 0, buffer, bitPos >> 3, data.length);
//         bitPos += data.length * 8;
//         if (bitPos > bitLen)
//             bitLen = bitPos;
    }

    public boolean readBoolean() {
        return readInt(1) != 0;
    }

    public void writeBoolean(boolean value) {
        writeInt(1, value ? 1 : 0);
    }

    public static int getBitCount(int value) {
        int x = value;
        int count = 0;
        while (x > 0) {
            x >>= 1;
            count++;
        }

        return count;
    }
}
