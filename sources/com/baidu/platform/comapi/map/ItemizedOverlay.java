package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.OverlayItem;
import com.baidu.platform.comapi.util.C3092d;
import com.baidu.platform.comjni.tools.ParcelItem;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ItemizedOverlay<Item extends OverlayItem> extends Overlay implements Comparator<Integer> {

    /* renamed from: a */
    protected ArrayList<OverlayItem> f7568a;

    /* renamed from: b */
    protected ArrayList<Integer> f7569b;

    /* renamed from: c */
    protected Drawable f7570c;

    /* renamed from: d */
    protected MapSurfaceView f7571d;

    /* renamed from: e */
    protected MapTextureView f7572e;

    /* renamed from: f */
    protected boolean f7573f;

    /* renamed from: g */
    protected Drawable f7574g;

    /* renamed from: h */
    protected OverlayItem f7575h;

    /* renamed from: i */
    protected int f7576i = 0;

    /* renamed from: j */
    protected int f7577j;

    public ItemizedOverlay(Drawable drawable, MapSurfaceView mapSurfaceView) {
        this.mType = 27;
        this.f7570c = drawable;
        this.f7568a = new ArrayList<>();
        this.f7569b = new ArrayList<>();
        this.f7571d = mapSurfaceView;
        this.mLayerID = 0L;
    }

    public ItemizedOverlay(Drawable drawable, MapTextureView mapTextureView) {
        this.mType = 27;
        this.f7570c = drawable;
        this.f7568a = new ArrayList<>();
        this.f7569b = new ArrayList<>();
        this.f7572e = mapTextureView;
        this.mLayerID = 0L;
    }

    /* renamed from: a */
    private void m18040a(List<OverlayItem> list, boolean z) {
        m18039a(list, z, false);
    }

    /* renamed from: a */
    private void m18039a(List<OverlayItem> list, boolean z, boolean z2) {
        if (z2) {
            synchronized (this) {
                if (this.f7568a != null) {
                    this.f7568a.clear();
                }
            }
        }
        if (this.mLayerID == 0) {
            if (z) {
                return;
            }
            synchronized (this) {
                if (this.f7568a != null && list != null) {
                    this.f7568a.addAll(list);
                }
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.clear();
        ArrayList arrayList = new ArrayList();
        bundle.putLong("itemaddr", this.mLayerID);
        bundle.putInt("bshow", 1);
        if (z) {
            bundle.putString("extparam", "update");
        }
        for (int i = 0; i < list.size(); i++) {
            OverlayItem overlayItem = list.get(i);
            if (overlayItem.getMarker() == null) {
                overlayItem.setMarker(this.f7570c);
            }
            if (TextUtils.isEmpty(overlayItem.getId())) {
                overlayItem.setId(C3002ae.m17984a());
            }
            ParcelItem parcelItem = new ParcelItem();
            Drawable marker = overlayItem.getMarker();
            byte[] gifData = overlayItem.getGifData();
            if (marker != null || gifData != null) {
                Bundle bundle2 = new Bundle();
                GeoPoint m17988a = overlayItem.getCoordType() == OverlayItem.CoordType.CoordType_BD09LL ? C2998aa.m17988a(overlayItem.getPoint()) : overlayItem.getPoint();
                bundle2.putDouble("x", m17988a.getLongitude());
                bundle2.putDouble("y", m17988a.getLatitude());
                bundle2.putFloat("geoz", overlayItem.getGeoZ());
                bundle2.putInt("indoorpoi", overlayItem.getIndoorPoi());
                bundle2.putInt("showLR", 1);
                bundle2.putInt("iconwidth", 0);
                bundle2.putInt("iconlayer", 1);
                bundle2.putFloat("ax", overlayItem.getAnchorX());
                bundle2.putFloat("ay", overlayItem.getAnchorY());
                bundle2.putInt("bound", overlayItem.getBound());
                bundle2.putInt("level", overlayItem.getLevel());
                bundle2.putInt("mask", overlayItem.getMask());
                bundle2.putString("popname", "" + overlayItem.getId());
                if (gifData != null) {
                    bundle2.putFloat("gifscale", overlayItem.getScale());
                    bundle2.putInt("gifsize", gifData.length);
                    bundle2.putByteArray("imgdata", gifData);
                    bundle2.putInt("imgindex", m18035c());
                } else {
                    Bitmap m17687a = C3092d.m17687a(marker);
                    if (m17687a != null) {
                        bundle2.putInt("imgindex", overlayItem.getResId());
                        bundle2.putInt("imgW", m17687a.getWidth());
                        bundle2.putInt("imgH", m17687a.getHeight());
                        if (z || !m18042a(overlayItem)) {
                            ByteBuffer allocate = ByteBuffer.allocate(m17687a.getWidth() * m17687a.getHeight() * 4);
                            m17687a.copyPixelsToBuffer(allocate);
                            bundle2.putByteArray("imgdata", allocate.array());
                        } else {
                            bundle2.putByteArray("imgdata", null);
                        }
                    }
                }
                String[] m18041a = m18041a(overlayItem.getClickRect());
                if (m18041a != null && m18041a.length > 0) {
                    bundle2.putStringArray("clickrect", m18041a);
                }
                bundle2.putBundle("animate", overlayItem.getAnimate());
                bundle2.putBundle("delay", overlayItem.getDelay());
                parcelItem.setBundle(bundle2);
                arrayList.add(parcelItem);
                if (!z) {
                    this.f7568a.add(overlayItem);
                }
            }
        }
        if (arrayList.size() > 0) {
            ParcelItem[] parcelItemArr = new ParcelItem[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                parcelItemArr[i2] = (ParcelItem) arrayList.get(i2);
            }
            bundle.putParcelableArray("itemdatas", parcelItemArr);
            this.f7571d.getController().getBaseMap().AddItemData(bundle, z2);
        }
        synchronized (this) {
            this.f7573f = true;
        }
    }

    /* renamed from: b */
    private int m18036b(boolean z) {
        ArrayList arrayList;
        if (this.f7568a == null) {
            return 0;
        }
        synchronized (this) {
            if (this.f7568a.size() == 0) {
                return 0;
            }
            synchronized (this) {
                arrayList = new ArrayList(this.f7568a);
            }
            int i = Integer.MIN_VALUE;
            int i2 = Integer.MAX_VALUE;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GeoPoint point = ((OverlayItem) it.next()).getPoint();
                int latitude = (int) (z ? point.getLatitude() : point.getLongitude());
                if (latitude > i) {
                    i = latitude;
                }
                if (latitude < i2) {
                    i2 = latitude;
                }
            }
            return i - i2;
        }
    }

    /* renamed from: c */
    private int m18035c() {
        int i = this.f7577j;
        if (i < Integer.MAX_VALUE) {
            int i2 = i + 1;
            this.f7577j = i2;
            return i2;
        }
        return 0;
    }

    /* renamed from: a */
    protected int m18043a(int i) {
        synchronized (this) {
            if (this.f7568a != null && this.f7568a.size() != 0) {
                return i;
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m18044a() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.f7568a);
        }
        removeAll();
        addItem(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m18038a(boolean z) {
        this.f7573f = z;
    }

    /* renamed from: a */
    protected boolean m18042a(OverlayItem overlayItem) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.f7568a);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            OverlayItem overlayItem2 = (OverlayItem) it.next();
            if (overlayItem.getResId() == -1) {
                return false;
            }
            if (overlayItem2.getResId() != -1 && overlayItem.getResId() == overlayItem2.getResId()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    protected String[] m18041a(ArrayList<Bundle> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        int size = arrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            JSONObject jSONObject = new JSONObject();
            Bundle bundle = arrayList.get(i);
            for (String str : bundle.keySet()) {
                try {
                    jSONObject.put(str, bundle.get(str));
                } catch (JSONException unused) {
                }
            }
            strArr[i] = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
        return strArr;
    }

    public void addItem(OverlayItem overlayItem) {
        if (overlayItem != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(overlayItem);
            addItem(arrayList);
        }
    }

    public void addItem(List<OverlayItem> list) {
        m18039a(list, false, false);
    }

    public void addItemsByReplace(List<OverlayItem> list) {
        m18039a(list, false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m18037b() {
        return this.f7573f;
    }

    @Override // java.util.Comparator
    public int compare(Integer num, Integer num2) {
        GeoPoint point;
        GeoPoint point2;
        synchronized (this) {
            point = this.f7568a.get(num.intValue()).getPoint();
            point2 = this.f7568a.get(num2.intValue()).getPoint();
        }
        if (point.getLatitude() > point2.getLatitude()) {
            return -1;
        }
        if (point.getLatitude() < point2.getLatitude()) {
            return 1;
        }
        if (point.getLongitude() < point2.getLongitude()) {
            return -1;
        }
        return point.getLongitude() == point2.getLongitude() ? 0 : 1;
    }

    public ArrayList<OverlayItem> getAllItem() {
        return this.f7568a;
    }

    public GeoPoint getCenter() {
        int m18043a = m18043a(0);
        if (m18043a == -1) {
            return null;
        }
        return getItem(m18043a).getPoint();
    }

    public final OverlayItem getItem(int i) {
        ArrayList arrayList;
        if (this.f7568a == null) {
            return null;
        }
        synchronized (this) {
            arrayList = new ArrayList(this.f7568a);
        }
        if (arrayList.size() <= i || i < 0) {
            return null;
        }
        return (OverlayItem) arrayList.get(i);
    }

    public int getLatSpanE6() {
        return m18036b(true);
    }

    public int getLonSpanE6() {
        return m18036b(false);
    }

    public int getUpdateType() {
        return this.f7576i;
    }

    public void initLayer() {
        this.mLayerID = this.f7571d.getController().getBaseMap().AddLayer(0, 0, "item");
        if (this.mLayerID == 0) {
            throw new RuntimeException("can not add new layer");
        }
    }

    public boolean onTap(int i) {
        return false;
    }

    public boolean onTap(int i, int i2, GeoPoint geoPoint) {
        return false;
    }

    public boolean onTap(GeoPoint geoPoint, MapSurfaceView mapSurfaceView) {
        return false;
    }

    public boolean removeAll() {
        synchronized (this) {
            if (this.f7568a.isEmpty()) {
                return false;
            }
            if (this.f7571d.getController() != null && this.f7571d.getController().getBaseMap() != null) {
                this.f7571d.getController().getBaseMap().ClearLayer(this.mLayerID);
            }
            synchronized (this) {
                this.f7568a.clear();
                this.f7573f = true;
            }
            return true;
        }
    }

    public boolean removeItem(OverlayItem overlayItem) {
        if (this.mLayerID == 0) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("itemaddr", this.mLayerID);
        if (overlayItem.getId().equals("")) {
            return false;
        }
        bundle.putString("id", overlayItem.getId());
        if (this.f7571d.getController().getBaseMap().RemoveItemData(bundle)) {
            synchronized (this) {
                this.f7568a.remove(overlayItem);
                this.f7573f = true;
            }
            return true;
        }
        return false;
    }

    public boolean removeOneItem(Iterator<OverlayItem> it, OverlayItem overlayItem) {
        if (this.mLayerID == 0) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("itemaddr", this.mLayerID);
        if (overlayItem.getId().equals("")) {
            return false;
        }
        bundle.putString("id", overlayItem.getId());
        if (this.f7571d.getController().getBaseMap().RemoveItemData(bundle)) {
            it.remove();
            synchronized (this) {
                this.f7573f = true;
            }
            return true;
        }
        return false;
    }

    public void setFocus(int i, boolean z) {
        OverlayItem item;
        ArrayList arrayList;
        if (this.f7575h == null || (item = getItem(i)) == null) {
            return;
        }
        if (z) {
            this.f7575h.setGeoPoint(new GeoPoint(item.getPoint().getLatitude(), item.getPoint().getLongitude()));
            synchronized (this) {
                arrayList = new ArrayList(this.f7568a);
            }
            if (arrayList.contains(this.f7575h)) {
                updateItem(this.f7575h);
            } else {
                addItem(this.f7575h);
            }
        } else {
            removeItem(this.f7575h);
        }
        MapSurfaceView mapSurfaceView = this.f7571d;
        if (mapSurfaceView != null) {
            mapSurfaceView.refresh(this);
        }
    }

    public void setFocusMarker(Drawable drawable) {
        this.f7574g = drawable;
        if (this.f7575h == null) {
            this.f7575h = new OverlayItem(null, "", "");
        }
        this.f7575h.setMarker(this.f7574g);
    }

    public void setFocusMarker(Drawable drawable, float f, float f2) {
        this.f7574g = drawable;
        if (this.f7575h == null) {
            this.f7575h = new OverlayItem(null, "", "");
            this.f7575h.setAnchor(f, f2);
        }
        this.f7575h.setMarker(this.f7574g);
    }

    public void setUpdateType(int i) {
        this.f7576i = i;
    }

    public void setmMarker(Drawable drawable) {
        this.f7570c = drawable;
    }

    public synchronized int size() {
        return this.f7568a == null ? 0 : this.f7568a.size();
    }

    public boolean updateItem(OverlayItem overlayItem) {
        ArrayList arrayList;
        boolean z;
        if (overlayItem == null || overlayItem.getId().equals("")) {
            return false;
        }
        synchronized (this) {
            arrayList = new ArrayList(this.f7568a);
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (overlayItem.getId().equals(((OverlayItem) it.next()).getId())) {
                z = true;
                break;
            }
        }
        if (z) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(overlayItem);
            m18040a(arrayList2, true);
            return true;
        }
        return false;
    }

    public boolean updateItem(List<OverlayItem> list) {
        if (list == null) {
            return false;
        }
        m18040a(list, true);
        return true;
    }
}
