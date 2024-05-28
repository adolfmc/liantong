package com.baidu.p120ar.filter;

import android.text.TextUtils;
import com.baidu.p120ar.DefaultParams;
import com.baidu.p120ar.arrender.FilterData;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.filter.ARFilterManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARFilterManager {
    private static final String FILTER_TYPE_CHEEKS = "cheeks";
    private static final String FILTER_TYPE_HIGHLIGHT = "highlight";
    private static final String FILTER_TYPE_LIPS = "lips";
    private static final String FILTER_TYPE_LIPS_MASK = "lips_mask";
    private static final String TAG = "ARFilterManager";
    private IARRenderer mARRenderer;
    private String mCasePath;
    private DefaultParams mDefaultParams;
    private FilterStateListener mFilterStateListener;
    private HashMap<FilterNode, Boolean> mPipelineStates = new HashMap<>();
    private HashMap<FilterNode, Boolean> mAbilityStates = new HashMap<>();
    private HashMap<FilterNode, Boolean> mFilterStates = new HashMap<>();
    private KeepBeautyState mKeepBeautyState = new KeepBeautyState();

    public ARFilterManager(DefaultParams defaultParams) {
        this.mDefaultParams = defaultParams;
        HashMap<FilterNode, Boolean> hashMap = this.mPipelineStates;
        if (hashMap != null) {
            hashMap.put(FilterNode.lutFilter, false);
            this.mPipelineStates.put(FilterNode.skinFilter, false);
            this.mPipelineStates.put(FilterNode.faceFilter, false);
            this.mPipelineStates.put(FilterNode.makeupFilter, false);
        }
        HashMap<FilterNode, Boolean> hashMap2 = this.mAbilityStates;
        if (hashMap2 != null) {
            hashMap2.put(FilterNode.lutFilter, true);
            this.mAbilityStates.put(FilterNode.skinFilter, Boolean.valueOf(this.mDefaultParams.isUseBeautyFilter()));
            this.mAbilityStates.put(FilterNode.faceFilter, Boolean.valueOf(this.mDefaultParams.isUseFaceFilter()));
            this.mAbilityStates.put(FilterNode.makeupFilter, Boolean.valueOf(this.mDefaultParams.isUseMakeupFilter()));
        }
    }

    public void setARRenderer(IARRenderer iARRenderer) {
        this.mARRenderer = iARRenderer;
    }

    public void setFilterStateListener(FilterStateListener filterStateListener) {
        this.mFilterStateListener = filterStateListener;
    }

    public void setCasePath(String str) {
        this.mCasePath = str;
    }

    public synchronized void onPipelineCreate() {
        if (this.mPipelineStates != null) {
            this.mPipelineStates.put(FilterNode.lutFilter, true);
            this.mPipelineStates.put(FilterNode.skinFilter, true);
            this.mPipelineStates.put(FilterNode.faceFilter, true);
            this.mPipelineStates.put(FilterNode.makeupFilter, true);
            ARLog.m20421d("ARFilterManager", "onPipelineCreate mPipelineStates = " + this.mPipelineStates.toString());
        }
        callbackFilterStates();
    }

    public synchronized void onPipelineChanged(List<String> list) {
        if (list == null) {
            return;
        }
        if (this.mPipelineStates != null) {
            this.mPipelineStates.put(FilterNode.lutFilter, Boolean.valueOf(list.contains(FilterNode.lutFilter.getNodeName())));
            this.mPipelineStates.put(FilterNode.skinFilter, Boolean.valueOf(list.contains(FilterNode.skinFilter.getNodeName())));
            this.mPipelineStates.put(FilterNode.faceFilter, Boolean.valueOf(list.contains(FilterNode.faceFilter.getNodeName())));
            this.mPipelineStates.put(FilterNode.makeupFilter, Boolean.valueOf(list.contains(FilterNode.makeupFilter.getNodeName())));
            ARLog.m20421d("ARFilterManager", "onPipelineChanged mPipelineStates = " + this.mPipelineStates.toString());
        }
        callbackFilterStates();
    }

    public synchronized void updateAbilityState(FilterNode filterNode, boolean z) {
        ARLog.m20421d("ARFilterManager", "updateAbilityState filerName = " + filterNode + " && state = " + z);
        if (filterNode != null && this.mAbilityStates != null && this.mAbilityStates.containsKey(filterNode)) {
            this.mAbilityStates.put(filterNode, Boolean.valueOf(z));
        }
    }

    public void callbackFilterStates() {
        HashMap<FilterNode, Boolean> hashMap = this.mFilterStates;
        if (hashMap != null && this.mPipelineStates != null && this.mAbilityStates != null) {
            boolean z = true;
            hashMap.put(FilterNode.lutFilter, Boolean.valueOf(this.mPipelineStates.get(FilterNode.lutFilter).booleanValue() && this.mAbilityStates.get(FilterNode.lutFilter).booleanValue()));
            this.mFilterStates.put(FilterNode.skinFilter, Boolean.valueOf(this.mPipelineStates.get(FilterNode.skinFilter).booleanValue() && this.mAbilityStates.get(FilterNode.skinFilter).booleanValue()));
            this.mFilterStates.put(FilterNode.faceFilter, Boolean.valueOf(this.mPipelineStates.get(FilterNode.faceFilter).booleanValue() && this.mAbilityStates.get(FilterNode.faceFilter).booleanValue()));
            HashMap<FilterNode, Boolean> hashMap2 = this.mFilterStates;
            FilterNode filterNode = FilterNode.makeupFilter;
            if (!this.mPipelineStates.get(FilterNode.makeupFilter).booleanValue() || !this.mAbilityStates.get(FilterNode.makeupFilter).booleanValue()) {
                z = false;
            }
            hashMap2.put(filterNode, Boolean.valueOf(z));
            ARLog.m20421d("ARFilterManager", "callbackFilterStates mFilterStates = " + this.mFilterStates.toString() + " && mCasePath = " + this.mCasePath);
        }
        FilterStateListener filterStateListener = this.mFilterStateListener;
        if (filterStateListener != null) {
            filterStateListener.onFilterStateChanged(this.mFilterStates, this.mCasePath);
        }
    }

    public void updateFilter(FilterParam filterParam, Object obj) {
        String str;
        boolean z;
        if (filterParam == null) {
            return;
        }
        String str2 = null;
        FilterNode filterNode = filterParam.getFilterNode();
        if (FilterNode.makeupFilter.equals(filterNode)) {
            str2 = getFilterExtraStr(obj);
            if (obj instanceof String) {
                sendMakeupStatistic(filterParam.getParamName());
            } else if (obj instanceof Integer) {
                str = str2;
                z = true;
                updateFilter(filterNode.getNodeName(), filterParam.getParamName(), obj, str, z);
            }
            str = str2;
            z = false;
            updateFilter(filterNode.getNodeName(), filterParam.getParamName(), obj, str, z);
        }
        if (FilterNode.advanceBeautyFilter.equals(filterNode) && (obj instanceof Float)) {
            str = "/intensity";
            z = false;
            updateFilter(filterNode.getNodeName(), filterParam.getParamName(), obj, str, z);
        }
        str = str2;
        z = false;
        updateFilter(filterNode.getNodeName(), filterParam.getParamName(), obj, str, z);
    }

    public void updateFilter(String str, String str2, Object obj) {
        String str3;
        boolean z;
        if (FilterNode.makeupFilter.getNodeName().equals(str)) {
            if (obj instanceof String) {
                sendMakeupStatistic(str2);
            } else if (obj instanceof Integer) {
                str3 = null;
                z = true;
            }
            str3 = null;
            z = false;
        } else {
            if (FilterNode.advanceBeautyFilter.getNodeName().equals(str) && (obj instanceof Float)) {
                str3 = "/intensity";
                z = false;
            }
            str3 = null;
            z = false;
        }
        updateFilter(str, str2, obj, str3, z);
    }

    public void updateFilter(String str, String str2, Object obj, String str3, boolean z) {
        FilterData filterData = new FilterData();
        filterData.setAbilityName(getAbilityName(str));
        filterData.setFilterName(str);
        filterData.setAdjustValue(obj);
        filterData.setControllData(z);
        if (TextUtils.isEmpty(str3)) {
            filterData.setAdjustKey(str2);
        } else {
            filterData.setAdjustKey(str2 + str3);
        }
        IARRenderer iARRenderer = this.mARRenderer;
        if (iARRenderer != null) {
            iARRenderer.updateFilterData(filterData);
        }
    }

    public String updateFilterCase(String str) {
        if (this.mARRenderer != null) {
            String renderPipeline = this.mDefaultParams.getRenderPipeline();
            if (!TextUtils.isEmpty(renderPipeline) && renderPipeline.contains(FilterNode.highlightFilter.getNodeName())) {
                ARLog.m20421d("ARFilterManager", "updateFilterCase: getRenderPipeline " + renderPipeline);
                this.mKeepBeautyState.changeFilterName(str);
            }
            return this.mARRenderer.updateFilterCase(str);
        }
        return null;
    }

    public void setFilterEnable(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        FilterData filterData = new FilterData();
        filterData.setControllData(true);
        filterData.setAbilityName(getAbilityName(str));
        filterData.setFilterName(str);
        filterData.setAdjustKey("is_enable");
        if (z) {
            filterData.setAdjustValue(1);
        } else {
            filterData.setAdjustValue(0);
        }
        IARRenderer iARRenderer = this.mARRenderer;
        if (iARRenderer != null) {
            iARRenderer.updateFilterData(filterData);
        }
    }

    public void restoreBeautyState() {
        if (this.mKeepBeautyState.isUseFilter()) {
            setFilterEnable(this.mKeepBeautyState.getFilterNodeName(), true);
        }
    }

    public void clearCaseLutFilter() {
        IARRenderer iARRenderer = this.mARRenderer;
        if (iARRenderer != null) {
            iARRenderer.clearCaseLutFilter();
        }
    }

    public void resetAllFilter() {
        updateAllFilter(0);
    }

    public void clearAllFilter() {
        updateAllFilter(1);
    }

    private void updateAllFilter(int i) {
        if (this.mARRenderer != null) {
            FilterData filterData = new FilterData();
            filterData.setAbilityName("ability_common_filter");
            filterData.setFilterName(FilterNode.faceFilter.getNodeName());
            filterData.setAdjustKey("clearAllKnead");
            filterData.setAdjustValue(Integer.valueOf(i));
            this.mARRenderer.updateFilterData(filterData);
        }
    }

    public synchronized void release() {
        this.mDefaultParams = null;
        this.mARRenderer = null;
        this.mKeepBeautyState = null;
        this.mFilterStateListener = null;
        if (this.mPipelineStates != null) {
            this.mPipelineStates.clear();
            this.mPipelineStates = null;
        }
        if (this.mAbilityStates != null) {
            this.mAbilityStates.clear();
            this.mAbilityStates = null;
        }
        if (this.mFilterStates != null) {
            this.mFilterStates.clear();
            this.mFilterStates = null;
        }
    }

    private String getFilterExtraStr(Object obj) {
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return "/opacity";
        }
        if (obj instanceof String) {
            return "/texture_path";
        }
        if (obj instanceof Integer) {
            return "/is_enable";
        }
        return null;
    }

    private String getAbilityName(String str) {
        return FilterNode.faceFilter.getNodeName().equals(str) ? "ability_face_filter" : FilterNode.makeupFilter.getNodeName().equals(str) ? "ability_makeup_filter" : "ability_common_filter";
    }

    private void sendMakeupStatistic(String str) {
        char c;
        String str2;
        int hashCode = str.hashCode();
        if (hashCode == -1361525779) {
            if (str.equals("cheeks")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == -681210700) {
            if (str.equals("highlight")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 3321920) {
            if (hashCode == 379533195 && str.equals("lips_mask")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("lips")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
                str2 = "event_makeup_lipstick_enter";
                break;
            case 2:
                str2 = "event_makeup_blusher_enter";
                break;
            case 3:
                str2 = "event_makeup_highlight_enter";
                break;
            default:
                str2 = null;
                break;
        }
        if (str2 != null) {
            StatisticApi.onEvent(str2);
        }
    }
}
