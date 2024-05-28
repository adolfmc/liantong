package com.baidu.p120ar;

import com.baidu.p120ar.capture.CaptureARProxy;
import com.baidu.p120ar.capture.ICapture;
import com.baidu.p120ar.cloud.CloudIRARProxy;
import com.baidu.p120ar.cloud.ICloudIR;
import com.baidu.p120ar.face.FaceARProxy;
import com.baidu.p120ar.face.IFace;
import com.baidu.p120ar.marker.IMarker;
import com.baidu.p120ar.marker.MarkerARProxy;
import com.baidu.p120ar.recg.IOnDeviceIR;
import com.baidu.p120ar.recg.OnDeviceIRARProxy;
import com.baidu.p120ar.track2d.ITrack2D;
import com.baidu.p120ar.track2d.Track2DARProxy;
import com.baidu.p120ar.track3d.ITrack3D;
import com.baidu.p120ar.track3d.Track3DARProxy;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ARProxyManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARProxyManager {
    private static final String TAG = "ARProxyManager";
    private HashMap<String, AbstractARProxy> mARProxyMap = new HashMap<>();

    public IFace getFaceAR() {
        return (IFace) getProxy("com.baidu.ar.face.FaceAR");
    }

    public ICapture getCaptureAR() {
        return (ICapture) getProxy("com.baidu.ar.capture.FamilyWithChildAR");
    }

    public ITrack2D getTrack2DAR() {
        return (ITrack2D) getProxy("com.baidu.ar.track2d.Track2DAR");
    }

    public ITrack3D getTrack3DAR() {
        return (ITrack3D) getProxy("com.baidu.ar.track3d.Track3DAR");
    }

    public ICloudIR getCloudIRAR() {
        return (ICloudIR) getProxy("com.baidu.ar.cloud.CloudAR");
    }

    public IMarker getMarkerAR() {
        return (IMarker) getProxy("com.baidu.ar.vps.marker.MarkerAR");
    }

    public IOnDeviceIR getOnDeviceIRAR() {
        return (IOnDeviceIR) getProxy("com.baidu.ar.recg.RecgAR");
    }

    private AbstractARProxy getProxy(String str) {
        if (hasProxy(str)) {
            if (this.mARProxyMap == null) {
                this.mARProxyMap = new HashMap<>();
            }
            AbstractARProxy abstractARProxy = this.mARProxyMap.get(str);
            if (abstractARProxy == null) {
                abstractARProxy = createProxy(str);
            }
            if (abstractARProxy != null) {
                this.mARProxyMap.put(str, abstractARProxy);
            }
            return abstractARProxy;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasProxy(String str) {
        return "com.baidu.ar.face.FaceAR".equals(str) || "com.baidu.ar.track2d.Track2DAR".equals(str) || "com.baidu.ar.track3d.Track3DAR".equals(str) || "com.baidu.ar.cloud.CloudAR".equals(str) || "com.baidu.ar.recg.RecgAR".equals(str) || "com.baidu.ar.vps.marker.MarkerAR".equals(str) || "com.baidu.ar.capture.FamilyWithChildAR".equals(str);
    }

    private AbstractARProxy createProxy(String str) {
        if ("com.baidu.ar.face.FaceAR".equals(str)) {
            return new FaceARProxy();
        }
        if ("com.baidu.ar.track2d.Track2DAR".equals(str)) {
            return new Track2DARProxy();
        }
        if ("com.baidu.ar.track3d.Track3DAR".equals(str)) {
            return new Track3DARProxy();
        }
        if ("com.baidu.ar.cloud.CloudAR".equals(str)) {
            return new CloudIRARProxy();
        }
        if ("com.baidu.ar.recg.RecgAR".equals(str)) {
            return new OnDeviceIRARProxy();
        }
        if ("com.baidu.ar.capture.FamilyWithChildAR".equals(str)) {
            return new CaptureARProxy();
        }
        if ("com.baidu.ar.vps.marker.MarkerAR".equals(str)) {
            return new MarkerARProxy();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bindARAbility(AbstractAR abstractAR, String str) {
        AbstractARProxy proxy = getProxy(str);
        if (proxy != null) {
            proxy.setARCase(abstractAR);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unbindARAbility(String str) {
        AbstractARProxy abstractARProxy;
        HashMap<String, AbstractARProxy> hashMap = this.mARProxyMap;
        if (hashMap == null || (abstractARProxy = hashMap.get(str)) == null) {
            return;
        }
        abstractARProxy.setARCase(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unbindAllARAbility() {
        HashMap<String, AbstractARProxy> hashMap = this.mARProxyMap;
        if (hashMap != null) {
            for (AbstractARProxy abstractARProxy : hashMap.values()) {
                abstractARProxy.setARCase(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        unbindAllARAbility();
        HashMap<String, AbstractARProxy> hashMap = this.mARProxyMap;
        if (hashMap != null) {
            for (Map.Entry<String, AbstractARProxy> entry : hashMap.entrySet()) {
                entry.getValue().release();
            }
            this.mARProxyMap.clear();
            this.mARProxyMap = null;
        }
    }
}
