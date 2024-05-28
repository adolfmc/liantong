package p472q0;

import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import p474r0.InterfaceC13715a;

/* renamed from: q0.j */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class C13685j extends Toast implements InterfaceC13715a {

    /* renamed from: a */
    public TextView f27535a;

    public C13685j(Application application) {
        super(application);
    }

    @Override // p474r0.InterfaceC13715a
    /* renamed from: a */
    public /* synthetic */ TextView mo125a(View view) {
        return InterfaceC13715a.CC.$default$a(this, view);
    }

    @Override // android.widget.Toast, p474r0.InterfaceC13715a
    public final void setText(CharSequence charSequence) {
        super.setText(charSequence);
        TextView textView = this.f27535a;
        if (textView == null) {
            return;
        }
        textView.setText(charSequence);
    }

    @Override // android.widget.Toast, p474r0.InterfaceC13715a
    public final void setView(View view) {
        super.setView(view);
        this.f27535a = view == null ? null : mo125a(view);
    }
}
