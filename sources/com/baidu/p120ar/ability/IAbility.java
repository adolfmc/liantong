package com.baidu.p120ar.ability;

import com.baidu.p120ar.ARProxyManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ability.IAbility */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAbility {
    boolean addAbility(String str, String str2);

    boolean addAbility(String str, String str2, String str3);

    boolean addAbility(String str, List<String> list);

    boolean adjustAbility(AbilityType abilityType, HashMap<String, Object> hashMap);

    boolean adjustAbility(String str, HashMap<String, Object> hashMap);

    ARProxyManager getARProxyManager();

    List<String> getActiveAbilities();

    Map<String, Object> getGradingInfo();

    List<String> getSupportedAbilities();

    boolean isAbilityActive(AbilityType abilityType);

    boolean isAbilityActive(String str);

    boolean isAbilitySupported(String str);

    void setMdlModelPath(String str);

    boolean startAbility(AbilityType abilityType, HashMap<String, Object> hashMap);

    boolean startAbility(String str, HashMap<String, Object> hashMap);

    boolean stopAbility(AbilityType abilityType);

    boolean stopAbility(String str);
}
