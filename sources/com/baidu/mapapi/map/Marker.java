package com.baidu.mapapi.map;

import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Marker extends Overlay {

    /* renamed from: A */
    InfoWindow.InterfaceC2745a f6214A;

    /* renamed from: a */
    LatLng f6219a;

    /* renamed from: b */
    BitmapDescriptor f6220b;

    /* renamed from: c */
    float f6221c;

    /* renamed from: d */
    float f6222d;

    /* renamed from: e */
    boolean f6223e;

    /* renamed from: f */
    boolean f6224f;

    /* renamed from: g */
    float f6225g;

    /* renamed from: h */
    String f6226h;

    /* renamed from: i */
    int f6227i;

    /* renamed from: l */
    float f6230l;

    /* renamed from: m */
    int f6231m;

    /* renamed from: r */
    int f6236r;

    /* renamed from: s */
    ArrayList<BitmapDescriptor> f6237s;

    /* renamed from: u */
    Animation f6239u;

    /* renamed from: y */
    Point f6243y;

    /* renamed from: z */
    InfoWindow f6244z;

    /* renamed from: j */
    boolean f6228j = false;

    /* renamed from: k */
    boolean f6229k = false;

    /* renamed from: n */
    boolean f6232n = false;

    /* renamed from: o */
    boolean f6233o = true;

    /* renamed from: p */
    boolean f6234p = false;

    /* renamed from: q */
    boolean f6235q = false;

    /* renamed from: t */
    int f6238t = 20;

    /* renamed from: v */
    float f6240v = 1.0f;

    /* renamed from: w */
    float f6241w = 1.0f;

    /* renamed from: x */
    float f6242x = 1.0f;

    /* renamed from: B */
    boolean f6215B = false;

    /* renamed from: C */
    int f6216C = Integer.MAX_VALUE;

    /* renamed from: D */
    int f6217D = 4;

    /* renamed from: E */
    int f6218E = 22;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Marker() {
        this.type = EnumC2933i.marker;
    }

    /* renamed from: a */
    private void m18896a(InfoWindow infoWindow, InfoWindow infoWindow2) {
        infoWindow.f6115b = infoWindow2.getBitmapDescriptor();
        infoWindow.f6117d = infoWindow2.getPosition();
        infoWindow.f6114a = infoWindow2.getTag();
        infoWindow.f6116c = infoWindow2.getView();
        infoWindow.f6120g = infoWindow2.getYOffset();
        infoWindow.f6124k = infoWindow2.f6124k;
        infoWindow.f6118e = infoWindow2.f6118e;
    }

    /* renamed from: a */
    private void m18895a(ArrayList<BitmapDescriptor> arrayList, Bundle bundle) {
        int i;
        ArrayList arrayList2 = new ArrayList();
        Iterator<BitmapDescriptor> it = arrayList.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ParcelItem parcelItem = new ParcelItem();
            Bundle bundle2 = new Bundle();
            Bitmap bitmap = it.next().f5990a;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            bundle2.putByteArray("image_data", array);
            bundle2.putInt("image_width", bitmap.getWidth());
            bundle2.putInt("image_height", bitmap.getHeight());
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            if (messageDigest != null) {
                messageDigest.update(array, 0, array.length);
                byte[] digest = messageDigest.digest();
                StringBuilder sb = new StringBuilder("");
                while (i < digest.length) {
                    sb.append(Integer.toString((digest[i] & 255) + 256, 16).substring(1));
                    i++;
                }
                bundle2.putString("image_hashcode", sb.toString());
            }
            parcelItem.setBundle(bundle2);
            arrayList2.add(parcelItem);
        }
        if (arrayList2.size() > 0) {
            ParcelItem[] parcelItemArr = new ParcelItem[arrayList2.size()];
            while (i < arrayList2.size()) {
                parcelItemArr[i] = (ParcelItem) arrayList2.get(i);
                i++;
            }
            bundle.putParcelableArray("icons", parcelItemArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.Overlay
    /* renamed from: a */
    public Bundle mo18867a(Bundle bundle) {
        super.mo18867a(bundle);
        Bundle bundle2 = new Bundle();
        BitmapDescriptor bitmapDescriptor = this.f6220b;
        if (bitmapDescriptor != null) {
            bundle.putBundle("image_info", bitmapDescriptor.m18976b());
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f6219a);
        bundle.putInt("animatetype", this.f6231m);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("perspective", this.f6223e ? 1 : 0);
        bundle.putFloat("anchor_x", this.f6221c);
        bundle.putFloat("anchor_y", this.f6222d);
        bundle.putFloat("rotate", this.f6225g);
        bundle.putInt("y_offset", this.f6227i);
        bundle.putInt("isflat", this.f6228j ? 1 : 0);
        bundle.putInt("istop", this.f6229k ? 1 : 0);
        bundle.putInt("period", this.f6238t);
        bundle.putFloat("alpha", this.f6230l);
        bundle.putInt("m_height", this.f6236r);
        bundle.putFloat("scaleX", this.f6240v);
        bundle.putFloat("scaleY", this.f6241w);
        bundle.putInt("isClickable", this.f6233o ? 1 : 0);
        bundle.putInt("priority", this.f6216C);
        bundle.putInt("isJoinCollision", this.f6234p ? 1 : 0);
        bundle.putInt("isForceDisplay", this.f6235q ? 1 : 0);
        bundle.putInt("startLevel", this.f6217D);
        bundle.putInt("endLevel", this.f6218E);
        Point point = this.f6243y;
        if (point != null) {
            bundle.putInt("fix_x", point.x);
            bundle.putInt("fix_y", this.f6243y.y);
        }
        bundle.putInt("isfixed", this.f6232n ? 1 : 0);
        ArrayList<BitmapDescriptor> arrayList = this.f6237s;
        if (arrayList != null && arrayList.size() > 0) {
            m18895a(this.f6237s, bundle);
        }
        bundle2.putBundle("param", bundle);
        return bundle;
    }

    public void cancelAnimation() {
        Animation animation = this.f6239u;
        if (animation != null) {
            animation.bdAnimation.mo18504b();
        }
    }

    public float getAlpha() {
        return this.f6230l;
    }

    public float getAnchorX() {
        return this.f6221c;
    }

    public float getAnchorY() {
        return this.f6222d;
    }

    public int getEndLevel() {
        return this.f6218E;
    }

    public Point getFixedPosition() {
        return this.f6243y;
    }

    public BitmapDescriptor getIcon() {
        return this.f6220b;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f6237s;
    }

    public String getId() {
        return this.f6297F;
    }

    public InfoWindow getInfoWindow() {
        return this.f6244z;
    }

    public int getPeriod() {
        return this.f6238t;
    }

    public LatLng getPosition() {
        return this.f6219a;
    }

    public int getPriority() {
        return this.f6216C;
    }

    public float getRotate() {
        return this.f6225g;
    }

    public float getScale() {
        return this.f6242x;
    }

    public float getScaleX() {
        return this.f6240v;
    }

    public float getScaleY() {
        return this.f6241w;
    }

    public int getStartLevel() {
        return this.f6217D;
    }

    public String getTitle() {
        return this.f6226h;
    }

    public int getYOffset() {
        return this.f6227i;
    }

    public void hideInfoWindow() {
        InfoWindow.InterfaceC2745a interfaceC2745a = this.f6214A;
        if (interfaceC2745a != null) {
            interfaceC2745a.mo18794a(this.f6244z);
            this.f6215B = false;
        }
        this.f6244z = null;
    }

    public boolean isClickable() {
        return this.f6233o;
    }

    public boolean isDraggable() {
        return this.f6224f;
    }

    public boolean isFixed() {
        return this.f6232n;
    }

    public boolean isFlat() {
        return this.f6228j;
    }

    public boolean isForceDisplay() {
        return this.f6235q;
    }

    public boolean isInfoWindowEnabled() {
        return this.f6215B;
    }

    public boolean isJoinCollision() {
        return this.f6234p;
    }

    public boolean isPerspective() {
        return this.f6223e;
    }

    public void setAlpha(float f) {
        if (f < 0.0f || f > 1.0d) {
            this.f6230l = 1.0f;
            return;
        }
        this.f6230l = f;
        this.listener.mo18796c(this);
    }

    public void setAnchor(float f, float f2) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        this.f6221c = f;
        this.f6222d = f2;
        this.listener.mo18796c(this);
    }

    public void setAnimateType(int i) {
        this.f6231m = i;
        this.listener.mo18796c(this);
    }

    public void setAnimation(Animation animation) {
        if (animation != null) {
            this.f6239u = animation;
            this.f6239u.bdAnimation.mo18506a(this, animation);
        }
    }

    public void setAnimation(Animation animation, TypeEvaluator typeEvaluator) {
        if (animation != null) {
            this.f6239u = animation;
            this.f6239u.bdAnimation.mo18510a(typeEvaluator);
            this.f6239u.bdAnimation.mo18506a(this, animation);
        }
    }

    public void setClickable(boolean z) {
        this.f6233o = z;
        this.listener.mo18796c(this);
    }

    public void setDraggable(boolean z) {
        this.f6224f = z;
        this.listener.mo18796c(this);
    }

    public void setEndLevel(int i) {
        this.f6218E = i;
        this.listener.mo18796c(this);
    }

    public void setFixedScreenPosition(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("BDMapSDKException: the screenPosition can not be null");
        }
        this.f6243y = point;
        this.f6232n = true;
        this.listener.mo18796c(this);
    }

    public void setFlat(boolean z) {
        this.f6228j = z;
        this.listener.mo18796c(this);
    }

    public void setForceDisplay(boolean z) {
        this.f6235q = z;
        this.listener.mo18796c(this);
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's icon can not be null");
        }
        this.f6220b = bitmapDescriptor;
        this.listener.mo18796c(this);
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        BitmapDescriptor bitmapDescriptor;
        if (arrayList == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's icons can not be null");
        }
        if (arrayList.size() == 0) {
            return;
        }
        if (arrayList.size() == 1) {
            bitmapDescriptor = arrayList.get(0);
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) == null || arrayList.get(i).f5990a == null) {
                    return;
                }
            }
            this.f6237s = (ArrayList) arrayList.clone();
            bitmapDescriptor = null;
        }
        this.f6220b = bitmapDescriptor;
        this.listener.mo18796c(this);
    }

    public void setJoinCollision(boolean z) {
        this.f6234p = z;
        this.listener.mo18796c(this);
    }

    public void setPeriod(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("BDMapSDKException: marker's period must be greater than zero ");
        }
        this.f6238t = i;
        this.listener.mo18796c(this);
    }

    public void setPerspective(boolean z) {
        this.f6223e = z;
        this.listener.mo18796c(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's position can not be null");
        }
        this.f6219a = latLng;
        this.listener.mo18796c(this);
    }

    public void setPositionWithInfoWindow(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: marker's position can not be null");
        }
        this.f6219a = latLng;
        this.listener.mo18796c(this);
        InfoWindow infoWindow = this.f6244z;
        if (infoWindow != null) {
            infoWindow.setPosition(latLng);
        }
    }

    public void setPriority(int i) {
        this.f6216C = i;
        this.listener.mo18796c(this);
    }

    public void setRotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f6225g = f % 360.0f;
        this.listener.mo18796c(this);
    }

    public void setScale(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.f6240v = f;
        this.f6241w = f;
        this.listener.mo18796c(this);
    }

    public void setScaleX(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.f6240v = f;
        this.listener.mo18796c(this);
    }

    public void setScaleY(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.f6241w = f;
        this.listener.mo18796c(this);
    }

    public void setStartLevel(int i) {
        this.f6217D = i;
        this.listener.mo18796c(this);
    }

    public void setTitle(String str) {
        this.f6226h = str;
    }

    public void setToTop() {
        this.f6229k = true;
        this.listener.mo18796c(this);
    }

    public void setYOffset(int i) {
        this.f6227i = i;
        this.listener.mo18796c(this);
    }

    public void showInfoWindow(InfoWindow infoWindow) {
        if (infoWindow == null) {
            throw new IllegalArgumentException("BDMapSDKException: the InfoWindow can not be null");
        }
        InfoWindow infoWindow2 = this.f6244z;
        if (infoWindow2 == null) {
            this.f6244z = infoWindow;
        } else {
            InfoWindow.InterfaceC2745a interfaceC2745a = this.f6214A;
            if (interfaceC2745a != null) {
                interfaceC2745a.mo18794a(infoWindow2);
            }
            m18896a(this.f6244z, infoWindow);
        }
        InfoWindow.InterfaceC2745a interfaceC2745a2 = this.f6214A;
        if (interfaceC2745a2 != null) {
            interfaceC2745a2.mo18793b(this.f6244z);
            this.f6215B = true;
        }
    }

    public void showSmoothMoveInfoWindow(InfoWindow infoWindow) {
        if (infoWindow == null) {
            return;
        }
        if (!infoWindow.f6124k) {
            throw new IllegalArgumentException("BDMapSDKException: the SmoothMoveInfoWindow must build with View");
        }
        if (infoWindow.f6116c == null) {
            throw new IllegalArgumentException("BDMapSDKException: the SmoothMoveInfoWindow's View can not be null");
        }
        InfoWindow infoWindow2 = this.f6244z;
        if (infoWindow2 == null) {
            this.f6244z = infoWindow;
        } else {
            m18896a(infoWindow2, infoWindow);
        }
        InfoWindow infoWindow3 = this.f6244z;
        infoWindow3.f6123j = true;
        InfoWindow.InterfaceC2745a interfaceC2745a = this.f6214A;
        if (interfaceC2745a != null) {
            interfaceC2745a.mo18793b(infoWindow3);
            this.f6215B = true;
        }
    }

    public void startAnimation() {
        Animation animation = this.f6239u;
        if (animation != null) {
            animation.bdAnimation.mo18514a();
        }
    }

    public void updateInfoWindowBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        InfoWindow infoWindow = this.f6244z;
        if (infoWindow == null || infoWindow.f6125l) {
            return;
        }
        this.f6244z.setBitmapDescriptor(bitmapDescriptor);
    }

    public void updateInfoWindowPosition(LatLng latLng) {
        InfoWindow infoWindow = this.f6244z;
        if (infoWindow != null) {
            infoWindow.setPosition(latLng);
        }
    }

    public void updateInfoWindowView(View view) {
        InfoWindow infoWindow = this.f6244z;
        if (infoWindow == null || !infoWindow.f6124k) {
            return;
        }
        this.f6244z.setView(view);
    }

    public void updateInfoWindowYOffset(int i) {
        InfoWindow infoWindow = this.f6244z;
        if (infoWindow != null) {
            infoWindow.setYOffset(i);
        }
    }
}
