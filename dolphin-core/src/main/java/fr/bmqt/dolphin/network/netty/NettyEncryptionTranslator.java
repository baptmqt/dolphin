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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class NettyEncryptionTranslator {
    private final Cipher cipher;
    private byte[] inputBuffer = new byte[0];
    private byte[] outputBuffer = new byte[0];


    private byte[] bufToBytes(ByteBuf buf) {
        int i = buf.readableBytes();

        if (inputBuffer.length < i) {
            inputBuffer = new byte[i];
        }

        buf.readBytes(inputBuffer, 0, i);
        return inputBuffer;
    }

    protected ByteBuf decipher(ChannelHandlerContext ctx, ByteBuf buffer) throws ShortBufferException {
        int i = buffer.readableBytes();
        byte[] abyte = bufToBytes(buffer);
        ByteBuf bytebuf = ctx.alloc().heapBuffer(cipher.getOutputSize(i));
        bytebuf.writerIndex(cipher.update(abyte, 0, i, bytebuf.array(), bytebuf.arrayOffset()));
        return bytebuf;
    }

    protected void cipher(ByteBuf in, ByteBuf out) throws ShortBufferException {
        int i = in.readableBytes();
        byte[] abyte = bufToBytes(in);
        int j = cipher.getOutputSize(i);

        if (outputBuffer.length < j)
            outputBuffer = new byte[j];


        out.writeBytes(outputBuffer, 0, cipher.update(abyte, 0, i, outputBuffer));
    }
}
