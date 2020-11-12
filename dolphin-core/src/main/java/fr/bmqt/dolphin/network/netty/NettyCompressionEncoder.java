package fr.bmqt.dolphin.network.netty;
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

import fr.bmqt.dolphin.network.packets.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.zip.Deflater;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@RequiredArgsConstructor
@Getter
@Setter
public class NettyCompressionEncoder extends MessageToByteEncoder<ByteBuf> {

    private final byte[] buffer = new byte[8192];
    private final Deflater deflater = new Deflater();
    private int compressionThreshold;

    protected void encode(ChannelHandlerContext ctx, ByteBuf bufIn, ByteBuf bufOut) throws Exception {
        int length = bufIn.readableBytes();
        PacketBuffer packetbuffer = new PacketBuffer(bufOut);

        if (length < compressionThreshold) {
            packetbuffer.writeVarIntToBuffer(0);
            packetbuffer.writeBytes(bufIn);
            return;
        }

        byte[] data = new byte[length];
        bufIn.readBytes(data);
        packetbuffer.writeVarIntToBuffer(data.length);
        deflater.setInput(data, 0, length);
        deflater.finish();

        while (!deflater.finished())
            packetbuffer.writeBytes(buffer, 0, deflater.deflate(buffer));

        deflater.reset();
    }
}
