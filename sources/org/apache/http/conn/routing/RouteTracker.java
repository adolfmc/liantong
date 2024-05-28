package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.RouteInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class RouteTracker implements RouteInfo, Cloneable {
    private boolean connected;
    private RouteInfo.LayerType layered;
    private final InetAddress localAddress;
    private HttpHost[] proxyChain;
    private boolean secure;
    private final HttpHost targetHost;
    private RouteInfo.TunnelType tunnelled;

    public RouteTracker(HttpHost httpHost, InetAddress inetAddress) {
        if (httpHost == null) {
            throw new IllegalArgumentException("Target host may not be null.");
        }
        this.targetHost = httpHost;
        this.localAddress = inetAddress;
        this.tunnelled = RouteInfo.TunnelType.PLAIN;
        this.layered = RouteInfo.LayerType.PLAIN;
    }

    public RouteTracker(HttpRoute httpRoute) {
        this(httpRoute.getTargetHost(), httpRoute.getLocalAddress());
    }

    public final void connectTarget(boolean z) {
        if (this.connected) {
            throw new IllegalStateException("Already connected.");
        }
        this.connected = true;
        this.secure = z;
    }

    public final void connectProxy(HttpHost httpHost, boolean z) {
        if (httpHost == null) {
            throw new IllegalArgumentException("Proxy host may not be null.");
        }
        if (this.connected) {
            throw new IllegalStateException("Already connected.");
        }
        this.connected = true;
        this.proxyChain = new HttpHost[]{httpHost};
        this.secure = z;
    }

    public final void tunnelTarget(boolean z) {
        if (!this.connected) {
            throw new IllegalStateException("No tunnel unless connected.");
        }
        if (this.proxyChain == null) {
            throw new IllegalStateException("No tunnel without proxy.");
        }
        this.tunnelled = RouteInfo.TunnelType.TUNNELLED;
        this.secure = z;
    }

    public final void tunnelProxy(HttpHost httpHost, boolean z) {
        if (httpHost == null) {
            throw new IllegalArgumentException("Proxy host may not be null.");
        }
        if (!this.connected) {
            throw new IllegalStateException("No tunnel unless connected.");
        }
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr == null) {
            throw new IllegalStateException("No proxy tunnel without proxy.");
        }
        int length = httpHostArr.length + 1;
        HttpHost[] httpHostArr2 = new HttpHost[length];
        System.arraycopy(httpHostArr, 0, httpHostArr2, 0, httpHostArr.length);
        httpHostArr2[length - 1] = httpHost;
        this.proxyChain = httpHostArr2;
        this.secure = z;
    }

    public final void layerProtocol(boolean z) {
        if (!this.connected) {
            throw new IllegalStateException("No layered protocol unless connected.");
        }
        this.layered = RouteInfo.LayerType.LAYERED;
        this.secure = z;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getTargetHost() {
        return this.targetHost;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final InetAddress getLocalAddress() {
        return this.localAddress;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final int getHopCount() {
        if (!this.connected) {
            return 0;
        }
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr == null) {
            return 1;
        }
        return 1 + httpHostArr.length;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getHopTarget(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Hop index must not be negative: " + i);
        }
        int hopCount = getHopCount();
        if (i >= hopCount) {
            throw new IllegalArgumentException("Hop index " + i + " exceeds tracked route length " + hopCount + ".");
        }
        if (i < hopCount - 1) {
            return this.proxyChain[i];
        }
        return this.targetHost;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final HttpHost getProxyHost() {
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr == null) {
            return null;
        }
        return httpHostArr[0];
    }

    public final boolean isConnected() {
        return this.connected;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.TunnelType getTunnelType() {
        return this.tunnelled;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isTunnelled() {
        return this.tunnelled == RouteInfo.TunnelType.TUNNELLED;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final RouteInfo.LayerType getLayerType() {
        return this.layered;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isLayered() {
        return this.layered == RouteInfo.LayerType.LAYERED;
    }

    @Override // org.apache.http.conn.routing.RouteInfo
    public final boolean isSecure() {
        return this.secure;
    }

    public final HttpRoute toRoute() {
        if (this.connected) {
            return new HttpRoute(this.targetHost, this.localAddress, this.proxyChain, this.secure, this.tunnelled, this.layered);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        int i = 0;
        if (obj instanceof RouteTracker) {
            RouteTracker routeTracker = (RouteTracker) obj;
            boolean equals = this.targetHost.equals(routeTracker.targetHost);
            InetAddress inetAddress = this.localAddress;
            InetAddress inetAddress2 = routeTracker.localAddress;
            boolean z2 = equals & (inetAddress == inetAddress2 || (inetAddress != null && inetAddress.equals(inetAddress2)));
            HttpHost[] httpHostArr = this.proxyChain;
            HttpHost[] httpHostArr2 = routeTracker.proxyChain;
            boolean z3 = z2 & (httpHostArr == httpHostArr2 || !(httpHostArr == null || httpHostArr2 == null || httpHostArr.length != httpHostArr2.length));
            if (this.connected != routeTracker.connected || this.secure != routeTracker.secure || this.tunnelled != routeTracker.tunnelled || this.layered != routeTracker.layered) {
                z = false;
            }
            boolean z4 = z & z3;
            if (z4 && httpHostArr != null) {
                while (z4) {
                    HttpHost[] httpHostArr3 = this.proxyChain;
                    if (i >= httpHostArr3.length) {
                        break;
                    }
                    z4 = httpHostArr3[i].equals(routeTracker.proxyChain[i]);
                    i++;
                }
            }
            return z4;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.targetHost.hashCode();
        InetAddress inetAddress = this.localAddress;
        if (inetAddress != null) {
            hashCode ^= inetAddress.hashCode();
        }
        HttpHost[] httpHostArr = this.proxyChain;
        if (httpHostArr != null) {
            hashCode ^= httpHostArr.length;
            int i = 0;
            while (true) {
                HttpHost[] httpHostArr2 = this.proxyChain;
                if (i >= httpHostArr2.length) {
                    break;
                }
                hashCode ^= httpHostArr2[i].hashCode();
                i++;
            }
        }
        if (this.connected) {
            hashCode ^= 286331153;
        }
        if (this.secure) {
            hashCode ^= 572662306;
        }
        return (hashCode ^ this.tunnelled.hashCode()) ^ this.layered.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((getHopCount() * 30) + 50);
        sb.append("RouteTracker[");
        InetAddress inetAddress = this.localAddress;
        if (inetAddress != null) {
            sb.append(inetAddress);
            sb.append("->");
        }
        sb.append('{');
        if (this.connected) {
            sb.append('c');
        }
        if (this.tunnelled == RouteInfo.TunnelType.TUNNELLED) {
            sb.append('t');
        }
        if (this.layered == RouteInfo.LayerType.LAYERED) {
            sb.append('l');
        }
        if (this.secure) {
            sb.append('s');
        }
        sb.append("}->");
        if (this.proxyChain != null) {
            int i = 0;
            while (true) {
                HttpHost[] httpHostArr = this.proxyChain;
                if (i >= httpHostArr.length) {
                    break;
                }
                sb.append(httpHostArr[i]);
                sb.append("->");
                i++;
            }
        }
        sb.append(this.targetHost);
        sb.append(']');
        return sb.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
