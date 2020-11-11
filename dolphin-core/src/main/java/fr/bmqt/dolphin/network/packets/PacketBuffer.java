package fr.bmqt.dolphin.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Baptiste MAQUET on 11/11/2020
 * @project dolphin-parent
 */
public class PacketBuffer extends ByteBufProvider {

    public PacketBuffer(ByteBuf wrapped) {
        super(wrapped);
    }

    public PacketBuffer writeByteArray(byte[] array) {
        this.writeVarIntToBuffer(array.length);
        this.writeBytes(array);
        return this;
    }

    public byte[] readByteArray() {
        return this.readByteArray(this.readableBytes());
    }

    public byte[] readByteArray(int maxLength) {
        int i = this.readVarIntFromBuffer();
        if (i > maxLength)
            throw new DecoderException("ByteArray with size " + i + " is bigger than allowed " + maxLength);
        else {
            byte[] abyte = new byte[i];
            this.readBytes(abyte);
            return abyte;
        }
    }

    /**
     * Writes an array of VarInts to the buffer, prefixed by the length of the array (as a VarInt).
     */
    public PacketBuffer writeVarIntArray(int[] array) {
        this.writeVarIntToBuffer(array.length);
        for (int i : array)
            this.writeVarIntToBuffer(i);
        return this;
    }

    public int[] readVarIntArray() {
        return this.readVarIntArray(this.readableBytes());
    }

    public int[] readVarIntArray(int maxLength) {
        int i = this.readVarIntFromBuffer();
        if (i > maxLength)
            throw new DecoderException("VarIntArray with size " + i + " is bigger than allowed " + maxLength);
        else {
            int[] a = new int[i];
            for (int j = 0; j < a.length; ++j)
                a[j] = this.readVarIntFromBuffer();
            return a;
        }
    }

    /**
     * Writes an array of longs to the buffer, prefixed by the length of the array (as a VarInt).
     */
    public PacketBuffer writeLongArray(long[] array) {
        this.writeVarIntToBuffer(array.length);
        for (long i : array)
            this.writeLong(i);
        return this;
    }

    /**
     * Reads a length-prefixed array of longs from the buffer.
     */
    public long[] readLongArray(long[] array) {
        return this.readLongArray(array, this.readableBytes() / 8);
    }

    public long[] readLongArray(long[] array, int length) {
        int i = this.readVarIntFromBuffer();
        if (array == null || array.length != i) {
            if (i > length)
                throw new DecoderException("LongArray with size " + i + " is bigger than allowed " + length);
            array = new long[i];
        }
        for (int j = 0; j < array.length; ++j)
            array[j] = this.readLong();
        return array;
    }

    public <T extends Enum<T>> T readEnumValue(Class<T> enumClass) {
        return (T) ((Enum[]) enumClass.getEnumConstants())[this.readVarIntFromBuffer()];
    }

    public PacketBuffer writeEnumValue(Enum<?> value) {
        return this.writeVarIntToBuffer(value.ordinal());
    }

    /**
     * Reads a compressed int from the buffer. To do so it maximally reads 5 byte-sized chunks whose most significant
     * bit dictates whether another byte should be read.
     */
    public int readVarIntFromBuffer() {
        int i = 0;
        int j = 0;
        while (true) {
            byte b = this.readByte();
            i |= (b & 127) << j++ * 7;
            if (j > 5)
                throw new RuntimeException("VarInt too big");
            if ((b & 128) != 128)
                break;
        }
        return i;
    }

    public long readVarLong() {
        long i = 0L;
        int j = 0;
        while (true) {
            byte b = this.readByte();
            i |= (long) (b & 127) << j++ * 7;
            if (j > 10)
                throw new RuntimeException("VarLong too big");
            if ((b & 128) != 128)
                break;
        }
        return i;
    }

    public PacketBuffer writeUuid(UUID uuid) {
        this.writeLong(uuid.getMostSignificantBits());
        this.writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public UUID readUuid() {
        return new UUID(this.readLong(), this.readLong());
    }

    /**
     * Writes a compressed int to the buffer. The smallest number of bytes to fit the passed int will be written. Of
     * each such byte only 7 bits will be used to describe the actual value since its most significant bit dictates
     * whether the next byte is part of that same int. Micro-optimization for int values that are expected to have
     * values below 128.
     */
    public PacketBuffer writeVarIntToBuffer(int input) {
        while ((input & -128) != 0) {
            this.writeByte(input & 127 | 128);
            input >>>= 7;
        }
        this.writeByte(input);
        return this;
    }

    public PacketBuffer writeVarLong(long value) {
        while ((value & -128L) != 0L) {
            this.writeByte((int) (value & 127L) | 128);
            value >>>= 7;
        }
        this.writeByte((int) value);
        return this;
    }

    /**
     * Reads a string from this buffer. Expected parameter is maximum allowed string length. Will throw IOException if
     * string length exceeds this value!
     */
    public String readStringFromBuffer(int maxLength) {
        int i = this.readVarIntFromBuffer();
        if (i > maxLength * 4)
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + i + " > " + maxLength * 4 + ")");
        else if (i < 0)
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        else {
            String s = this.toString(this.readerIndex(), i, StandardCharsets.UTF_8);
            this.readerIndex(this.readerIndex() + i);
            if (s.length() > maxLength)
                throw new DecoderException("The received string length is longer than maximum allowed (" + i + " > " + maxLength + ")");
            else
                return s;
        }
    }

    public PacketBuffer writeString(String string) {
        byte[] abyte = string.getBytes(StandardCharsets.UTF_8);
        if (abyte.length > 32767)
            throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + 32767 + ")");
        else {
            this.writeVarIntToBuffer(abyte.length);
            this.writeBytes(abyte);
            return this;
        }
    }

}
