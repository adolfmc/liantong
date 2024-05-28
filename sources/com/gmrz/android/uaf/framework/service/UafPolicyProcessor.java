package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import com.fido.uaf.ver0100.types.MatchCriteria;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.utils.Logger;
import com.utils.AAID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafPolicyProcessor {
    private static final String TAG = "UafPolicyProcessor";
    private List<Authenticator> eligibleAuths;
    private int iCurrComb;
    private int nCriterion;
    private final List<List<MatchCriteria>> policyAccepted;
    private final List<MatchCriteria> policyDisallowed;
    private final List<List<AcceptedAuthnr>> retAcceptedAuthnrs = new ArrayList();
    private final AuthenticatorManager uafTokenManager;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class AcceptedAuthnr {
        public final int index;
        public List<String> keyIDList;

        public AcceptedAuthnr(int i, List<String> list) {
            this.index = i;
            this.keyIDList = list;
        }
    }

    public UafPolicyProcessor(List<List<MatchCriteria>> list, List<MatchCriteria> list2, List<Authenticator> list3, Activity activity) {
        this.policyAccepted = list;
        this.policyDisallowed = list2;
        this.eligibleAuths = list3;
        this.uafTokenManager = AuthenticatorManager.instance(activity);
    }

    public List<List<AcceptedAuthnr>> getAcceptedAuthnrs() {
        int size = this.policyAccepted.size();
        int i = 0;
        while (true) {
            this.iCurrComb = i;
            int i2 = this.iCurrComb;
            if (i2 < size) {
                this.nCriterion = this.policyAccepted.get(i2).size();
                getNextCombination(new ArrayList<>(), this.nCriterion);
                i = this.iCurrComb + 1;
            } else {
                return this.retAcceptedAuthnrs;
            }
        }
    }

    public List<List<AcceptedAuthnr>> getAllAuthnrs() {
        int size = this.eligibleAuths.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new AcceptedAuthnr(i, arrayList));
            this.retAcceptedAuthnrs.add(arrayList2);
        }
        return this.retAcceptedAuthnrs;
    }

    public List<Authenticator> getEligibleAuthnrs() {
        int size = this.policyDisallowed.size();
        Iterator<Authenticator> it = this.eligibleAuths.iterator();
        while (it.hasNext()) {
            Authenticator next = it.next();
            if (next.isEnabled()) {
                int i = 0;
                while (true) {
                    if (i < size) {
                        if (matchAuthnrCriteria(next, this.policyDisallowed.get(i), null)) {
                            it.remove();
                            String str = next.getAuthnrInfo().aaid;
                            if (AAID.GESTURE_ECC.name.equals(str) || AAID.GESTURE_GM.name.equals(str)) {
                                Logger.m15889i(TAG, "Gesture ECC or GM algorithm authenticators matched. judge user already registered");
                                this.eligibleAuths = new ArrayList();
                            }
                        } else {
                            i++;
                        }
                    }
                }
            } else {
                it.remove();
            }
        }
        return this.eligibleAuths;
    }

    private void getNextCombination(ArrayList<AcceptedAuthnr> arrayList, int i) {
        if (i == 0) {
            if (isCombinationIncluded(arrayList)) {
                return;
            }
            this.retAcceptedAuthnrs.add(arrayList);
            return;
        }
        int size = this.eligibleAuths.size();
        for (int i2 = 0; i2 < size; i2++) {
            Authenticator authenticator = this.eligibleAuths.get(i2);
            MatchCriteria matchCriteria = this.policyAccepted.get(this.iCurrComb).get(this.nCriterion - i);
            ArrayList arrayList2 = new ArrayList();
            if (!isIncluded(arrayList, i2) && matchAuthnrCriteria(authenticator, matchCriteria, arrayList2)) {
                ArrayList<AcceptedAuthnr> arrayList3 = new ArrayList<>(arrayList);
                arrayList3.add(new AcceptedAuthnr(i2, arrayList2));
                getNextCombination(arrayList3, i - 1);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x002e, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isCombinationIncluded(java.util.ArrayList<com.gmrz.android.uaf.framework.service.UafPolicyProcessor.AcceptedAuthnr> r7) {
        /*
            r6 = this;
            java.util.List<java.util.List<com.gmrz.android.uaf.framework.service.UafPolicyProcessor$AcceptedAuthnr>> r0 = r6.retAcceptedAuthnrs
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L31
            java.lang.Object r1 = r0.next()
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r3 = r7.iterator()
        L17:
            boolean r4 = r3.hasNext()
            r5 = 1
            if (r4 == 0) goto L2d
            java.lang.Object r4 = r3.next()
            com.gmrz.android.uaf.framework.service.UafPolicyProcessor$AcceptedAuthnr r4 = (com.gmrz.android.uaf.framework.service.UafPolicyProcessor.AcceptedAuthnr) r4
            int r4 = r4.index
            boolean r4 = r6.isIncluded(r1, r4)
            if (r4 != 0) goto L17
            goto L2e
        L2d:
            r2 = r5
        L2e:
            if (r2 == 0) goto L6
            return r5
        L31:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.uaf.framework.service.UafPolicyProcessor.isCombinationIncluded(java.util.ArrayList):boolean");
    }

    private boolean isIncluded(List<AcceptedAuthnr> list, int i) {
        for (AcceptedAuthnr acceptedAuthnr : list) {
            if (acceptedAuthnr.index == i) {
                return true;
            }
        }
        return false;
    }

    private boolean matchAuthnrCriteria(Authenticator authenticator, MatchCriteria matchCriteria, List<String> list) {
        List arrayList;
        boolean z;
        boolean z2;
        AuthenticatorInfo authnrInfo = authenticator.getAuthnrInfo();
        if (matchCriteria.exts != null) {
            for (Extension extension : matchCriteria.exts) {
                if (extension.fail_if_unknown) {
                    Iterator<String> it = authnrInfo.supportedExtensionIDs.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().equals(extension.f10152id)) {
                                z2 = true;
                                break;
                            }
                        } else {
                            z2 = false;
                            break;
                        }
                    }
                    if (!z2) {
                        return false;
                    }
                }
            }
        }
        if (matchCriteria.aaid != null && matchCriteria.aaid.size() > 0) {
            Logger.m15895d(TAG, "Matching aaid " + authenticator.mAuthnrInfo.aaid + " and matchCriteria aaid " + matchCriteria.aaid);
            if (!matchAAID(matchCriteria.aaid, authenticator.mAuthnrInfo.aaid)) {
                return false;
            }
            if (matchCriteria.keyIDs != null && matchCriteria.keyIDs.size() > 0) {
                Iterator<String> it2 = matchCriteria.keyIDs.iterator();
                while (true) {
                    Iterator<String> it3 = it2;
                    if (!it3.hasNext()) {
                        z = false;
                        break;
                    }
                    String next = it3.next();
                    if (authenticator.isKeyIDRegistered(next)) {
                        if (list != null) {
                            list.add(next);
                        }
                        z = true;
                    }
                }
                if (!z) {
                    return false;
                }
            }
        } else if (matchCriteria.authenticationAlgorithms == null || matchCriteria.authenticationAlgorithms.size() <= 0 || matchCriteria.assertionSchemes == null || matchCriteria.assertionSchemes.size() <= 0 || !matchCriteria.authenticationAlgorithms.contains(Short.valueOf(authenticator.mAuthnrInfo.authenticationAlgorithm)) || !matchCriteria.assertionSchemes.contains(authenticator.mAuthnrInfo.assertionScheme)) {
            return false;
        } else {
            if (matchCriteria.vendorID != null && matchCriteria.vendorID.size() > 0) {
                if (!matchCriteria.vendorID.contains(authenticator.mAuthnrInfo.aaid.split("#")[0])) {
                    return false;
                }
            }
            if (matchCriteria.userVerification != 0 && matchCriteria.userVerification != authnrInfo.userVerification && ((authnrInfo.userVerification & 1024) != 0 || (matchCriteria.userVerification & 1024) != 0 || (authnrInfo.userVerification & matchCriteria.userVerification) == 0)) {
                return false;
            }
            if (matchCriteria.keyProtection != 0 && (matchCriteria.keyProtection & authnrInfo.keyProtection) == 0) {
                return false;
            }
            if (matchCriteria.matcherProtection != 0 && (matchCriteria.matcherProtection & authnrInfo.matcherProtection) == 0) {
                return false;
            }
            if (matchCriteria.tcDisplay != 0 && (matchCriteria.tcDisplay & authnrInfo.tcDisplay) == 0) {
                return false;
            }
        }
        if (matchCriteria.attestationTypes != null && matchCriteria.attestationTypes.size() > 0) {
            arrayList = matchCriteria.attestationTypes;
        } else {
            arrayList = new ArrayList();
            List list2 = arrayList;
            list2.add((short) 15879);
            list2.add((short) 15880);
        }
        if (authenticator.mAuthnrInfo.attestationTypes == null || authenticator.mAuthnrInfo.attestationTypes.size() == 0) {
            Logger.m15895d(TAG, "AttestationType not defined in AuthenticatorInfo for aaid " + authenticator.mAuthnrInfo.aaid);
            return false;
        }
        Iterator it4 = arrayList.iterator();
        boolean z3 = false;
        do {
            Iterator it5 = it4;
            if (!it5.hasNext()) {
                break;
            }
            Object next2 = it5.next();
            Iterator<Short> it6 = authenticator.mAuthnrInfo.attestationTypes.iterator();
            while (true) {
                if (it6.hasNext()) {
                    Short next3 = it6.next();
                    if (((Short) next2).shortValue() == next3.shortValue()) {
                        authenticator.setAttestationType(next3.shortValue());
                        z3 = true;
                        continue;
                        break;
                    }
                }
            }
        } while (!z3);
        if (z3) {
            return matchCriteria.attachmentHint == 0 || (matchCriteria.attachmentHint & authnrInfo.attachmentHint) != 0;
        }
        return false;
    }

    private boolean matchAAID(List<String> list, String str) {
        for (String str2 : list) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
