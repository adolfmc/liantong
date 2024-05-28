package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.support.annotation.NonNull;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanzhongxinEntity implements Cloneable {
    private boolean chooseGroup1;
    private boolean chooseGroup2;
    private boolean chooseGroup3;
    private int groupChooseNumber;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18461id;
    private String mobile;
    private boolean selected1;
    private boolean selected2;
    private boolean selected3;
    private boolean selected4;
    private boolean selected5;
    private String sign;
    private int signWay;

    public long getId() {
        return this.f18461id;
    }

    public void setId(long j) {
        this.f18461id = j;
    }

    public boolean isChooseGroup1() {
        return this.chooseGroup1;
    }

    public void setChooseGroup1(boolean z) {
        this.chooseGroup1 = z;
    }

    public boolean isChooseGroup2() {
        return this.chooseGroup2;
    }

    public void setChooseGroup2(boolean z) {
        this.chooseGroup2 = z;
    }

    public boolean isChooseGroup3() {
        return this.chooseGroup3;
    }

    public void setChooseGroup3(boolean z) {
        this.chooseGroup3 = z;
    }

    public boolean isSelected1() {
        return this.selected1;
    }

    public void setSelected1(boolean z) {
        this.selected1 = z;
    }

    public boolean isSelected2() {
        return this.selected2;
    }

    public void setSelected2(boolean z) {
        this.selected2 = z;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public int getGroupChooseNumber() {
        return this.groupChooseNumber;
    }

    public void setGroupChooseNumber(int i) {
        this.groupChooseNumber = i;
    }

    public boolean isSelected3() {
        return this.selected3;
    }

    public void setSelected3(boolean z) {
        this.selected3 = z;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public int getSignWay() {
        return this.signWay;
    }

    public void setSignWay(int i) {
        this.signWay = i;
    }

    public boolean isSelected4() {
        return this.selected4;
    }

    public void setSelected4(boolean z) {
        this.selected4 = z;
    }

    public boolean isSelected5() {
        return this.selected5;
    }

    public void setSelected5(boolean z) {
        this.selected5 = z;
    }

    public String toString() {
        return "AnquanzhongxinEntity{id=" + this.f18461id + ", mobile='" + this.mobile + "', chooseGroup1=" + this.chooseGroup1 + ", chooseGroup2=" + this.chooseGroup2 + ", chooseGroup3=" + this.chooseGroup3 + ", selected1=" + this.selected1 + ", selected2=" + this.selected2 + '}';
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    /* renamed from: clone */
    public AnquanzhongxinEntity m24482clone() throws CloneNotSupportedException {
        return (AnquanzhongxinEntity) super.clone();
    }
}
