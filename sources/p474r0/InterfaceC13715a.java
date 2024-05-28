package p474r0;

import android.view.View;
import android.widget.TextView;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: r0.a */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface InterfaceC13715a {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: r0.a$-CC  reason: invalid class name */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static TextView $default$a(InterfaceC13715a interfaceC13715a, View view) {
            if (!(view instanceof TextView)) {
                View findViewById = view.findViewById(16908299);
                if (findViewById instanceof TextView) {
                    return (TextView) findViewById;
                }
                throw new IllegalArgumentException("You must include a TextView with an ID value of android.R.id.message");
            }
            if (view.getId() == -1) {
                view.setId(16908299);
            } else if (view.getId() != 16908299) {
                throw new IllegalArgumentException("You must set the ID value of TextView to android.R.id.message");
            }
            return (TextView) view;
        }
    }

    /* renamed from: a */
    TextView mo125a(View view);

    void cancel();

    void setDuration(int i);

    void setGravity(int i, int i2, int i3);

    void setMargin(float f, float f2);

    void setText(CharSequence charSequence);

    void setView(View view);

    void show();
}
