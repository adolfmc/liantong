package android.support.design.theme;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.p086v7.app.AppCompatViewInflater;
import android.support.p086v7.widget.AppCompatButton;
import android.util.AttributeSet;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    @Override // android.support.p086v7.app.AppCompatViewInflater
    @NonNull
    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }
}
