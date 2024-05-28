package com.sinovatech.unicom.separatemodule.gamecenter.view;

import android.os.Bundle;
import android.support.p083v4.app.Fragment;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCentersDataManager;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KingKongTwoFragment extends Fragment {
    public NBSTraceUnit _nbs_trace;
    private GameCentersActivity activityContext;
    private GameCenterAdapter gameAdapter;
    private ArrayList<GamesEntity.GamesDataEntity> list;
    private GameCentersDataManager manager;
    private RecyclerView reclKingKong;

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493126, viewGroup, false);
        initView(inflate);
        initKingKongCanche();
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment");
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initKingKongCanche() {
        String gameKingKongDistrict = CacheDataCenter.getInstance().getGameKingKongDistrict();
        if (TextUtils.isEmpty(gameKingKongDistrict)) {
            return;
        }
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameKingKongDistrict);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setTitle(optJSONObject.optString("title"));
                    gamesDataEntity.setAndroid_version(optJSONObject.optString("android_version"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setIcon(optJSONObject.optString("icon"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
                if (gamesEntity.getData() != null) {
                    this.list.clear();
                    for (int i2 = 5; i2 < gamesEntity.getData().size(); i2++) {
                        this.list.add(gamesEntity.getData().get(i2));
                    }
                    this.gameAdapter.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(View view) {
        this.list = new ArrayList<>();
        this.manager = GameCentersDataManager.getInstance();
        this.reclKingKong = (RecyclerView) view.findViewById(2131298261);
        this.reclKingKong.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.gameAdapter = new GameCenterAdapter(getContext(), this.list, 2131493214);
        this.reclKingKong.setAdapter(this.gameAdapter);
        this.gameAdapter.setListener(new C87691());
        intiData();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C87691 implements GameCenterAdapter.GameClickedListener {
        C87691() {
        }

        @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter.GameClickedListener
        public void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
            IntentManager.generateIntentAndGo(KingKongTwoFragment.this.getActivity(), gamesDataEntity.getUrl(), "", true, "get");
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("p2", "40526");
                hashMap.put("p3", "游戏专区App客户端");
                hashMap.put("p5", "10");
                hashMap.put("p25", gamesDataEntity.getTitle());
                hashMap.put("p32", "Android");
                hashMap.put("p34", KingKongTwoFragment.this.getActivity().getString(2131886969).split("@")[1]);
                GameCentersDataManager.getInstance().commonLog((AppCompatActivity) KingKongTwoFragment.this.getActivity(), hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.-$$Lambda$KingKongTwoFragment$1$rsYa0NFblhplxQUSanaLQWpJ-ew
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        UIUtils.logD("游戏日志结果-->", (String) obj);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void intiData() {
        this.manager.postKingKongDistrict(getActivity()).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.KingKongTwoFragment.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull GamesEntity gamesEntity) {
                if (gamesEntity.getData() != null) {
                    KingKongTwoFragment.this.list.clear();
                    for (int i = 5; i < gamesEntity.getData().size(); i++) {
                        KingKongTwoFragment.this.list.add(gamesEntity.getData().get(i));
                    }
                    KingKongTwoFragment.this.gameAdapter.notifyDataSetChanged();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
                KingKongTwoFragment.this.initKingKongCanche();
            }
        });
    }
}
