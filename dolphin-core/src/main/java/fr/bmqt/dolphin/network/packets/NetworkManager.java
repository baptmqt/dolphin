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
package fr.bmqt.dolphin.network.packets;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.SocketAddress;

/**
 * @author Baptiste MAQUET on 12/11/2020
 * @project dolphin-parent
 */
@Getter
@RequiredArgsConstructor
public class NetworkManager extends SimpleChannelInboundHandler<Packet<?>> {

    public static final AttributeKey<ConnectionState> PROTOCOL_ATTRIBUTE_KEY = AttributeKey.<ConnectionState>valueOf("protocol");

    protected final PacketDirection direction;

    protected Channel channel;

    protected SocketAddress socketAddress;

    protected INetHandler packetListener;

    protected boolean isEncrypted;
    protected boolean disconnected;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();
        socketAddress = channel.remoteAddress();
        setConnectionState(ConnectionState.HANDSHAKING);
    }

    public void setConnectionState(ConnectionState state){
        channel.attr(PROTOCOL_ATTRIBUTE_KEY).set(state);
        channel.config().setAutoRead(true);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet<?> msg) throws Exception {

    }
}
