package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class CombiningEvaluator extends Evaluator {
    final List<Evaluator> evaluators;

    CombiningEvaluator() {
        this.evaluators = new ArrayList();
    }

    CombiningEvaluator(Collection<Evaluator> collection) {
        this();
        this.evaluators.addAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Evaluator rightMostEvaluator() {
        if (this.evaluators.size() > 0) {
            List<Evaluator> list = this.evaluators;
            return list.get(list.size() - 1);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceRightMostEvaluator(Evaluator evaluator) {
        List<Evaluator> list = this.evaluators;
        list.set(list.size() - 1, evaluator);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class And extends CombiningEvaluator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public And(Collection<Evaluator> collection) {
            super(collection);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public And(Evaluator... evaluatorArr) {
            this(Arrays.asList(evaluatorArr));
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            for (int i = 0; i < this.evaluators.size(); i++) {
                if (!this.evaluators.get(i).matches(element, element2)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return StringUtil.join(this.evaluators, " ");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.jsoup.select.CombiningEvaluator$Or */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class C13539Or extends CombiningEvaluator {
        C13539Or(Collection<Evaluator> collection) {
            if (collection.size() > 1) {
                this.evaluators.add(new And(collection));
            } else {
                this.evaluators.addAll(collection);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C13539Or() {
        }

        public void add(Evaluator evaluator) {
            this.evaluators.add(evaluator);
        }

        @Override // org.jsoup.select.Evaluator
        public boolean matches(Element element, Element element2) {
            for (int i = 0; i < this.evaluators.size(); i++) {
                if (this.evaluators.get(i).matches(element, element2)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":or%s", this.evaluators);
        }
    }
}
