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
