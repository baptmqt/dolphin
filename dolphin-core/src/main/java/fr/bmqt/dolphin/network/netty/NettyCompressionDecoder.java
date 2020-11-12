package fr.bmqt.dolphin.network.netty;

import fr.bmqt.dolphin.network.packets.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.zip.Inflater;

/*
 * MIT License
 *
 * Copyright (c) 2020.  Baptiste MAQUET
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@RequiredArgsConstructor
@Setter
@Getter
public class NettyCompressionDecoder extends ByteToMessageDecoder {

    private final Inflater inflater = new Inflater();
    private int compressionThreshold;

    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> objects) throws Exception {
        if (buf.readableBytes() == 0)
            return;

        PacketBuffer packetBuffer = new PacketBuffer(buf);
        int length = packetBuffer.readVarIntFromBuffer();

        if (length == 0) {
            objects.add(packetBuffer.readBytes(packetBuffer.readableBytes()));
            return;
        }

        if (length < compressionThreshold)
            throw new DecoderException("Badly compressed packet - size of " + length + " is below server threshold of " + compressionThreshold);
        if (length > 2097152)
            throw new DecoderException("Badly compressed packet - size of " + length + " is larger than protocol maximum of " + 2097152);


        byte[] compressed = new byte[packetBuffer.readableBytes()];
        packetBuffer.readBytes(compressed);
        inflater.setInput(compressed);
        byte[] uncompressed = new byte[length];
        inflater.inflate(uncompressed);
        objects.add(Unpooled.wrappedBuffer(uncompressed));
        inflater.reset();


    }

}
