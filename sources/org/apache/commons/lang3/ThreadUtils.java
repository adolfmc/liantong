package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ThreadUtils {
    public static final AlwaysTruePredicate ALWAYS_TRUE_PREDICATE = new AlwaysTruePredicate();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ThreadGroupPredicate {
        boolean test(ThreadGroup threadGroup);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ThreadPredicate {
        boolean test(Thread thread);
    }

    public static Thread findThreadById(long j, ThreadGroup threadGroup) {
        Validate.isTrue(threadGroup != null, "The thread group must not be null", new Object[0]);
        Thread findThreadById = findThreadById(j);
        if (findThreadById == null || !threadGroup.equals(findThreadById.getThreadGroup())) {
            return null;
        }
        return findThreadById;
    }

    public static Thread findThreadById(long j, String str) {
        Validate.isTrue(str != null, "The thread group name must not be null", new Object[0]);
        Thread findThreadById = findThreadById(j);
        if (findThreadById == null || findThreadById.getThreadGroup() == null || !findThreadById.getThreadGroup().getName().equals(str)) {
            return null;
        }
        return findThreadById;
    }

    public static Collection<Thread> findThreadsByName(String str, ThreadGroup threadGroup) {
        return findThreads(threadGroup, false, new NamePredicate(str));
    }

    public static Collection<Thread> findThreadsByName(String str, String str2) {
        Validate.isTrue(str != null, "The thread name must not be null", new Object[0]);
        Validate.isTrue(str2 != null, "The thread group name must not be null", new Object[0]);
        Collection<ThreadGroup> findThreadGroups = findThreadGroups(new NamePredicate(str2));
        if (findThreadGroups.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        NamePredicate namePredicate = new NamePredicate(str);
        for (ThreadGroup threadGroup : findThreadGroups) {
            arrayList.addAll(findThreads(threadGroup, false, namePredicate));
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public static Collection<ThreadGroup> findThreadGroupsByName(String str) {
        return findThreadGroups(new NamePredicate(str));
    }

    public static Collection<ThreadGroup> getAllThreadGroups() {
        return findThreadGroups(ALWAYS_TRUE_PREDICATE);
    }

    public static ThreadGroup getSystemThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (threadGroup.getParent() != null) {
            threadGroup = threadGroup.getParent();
        }
        return threadGroup;
    }

    public static Collection<Thread> getAllThreads() {
        return findThreads(ALWAYS_TRUE_PREDICATE);
    }

    public static Collection<Thread> findThreadsByName(String str) {
        return findThreads(new NamePredicate(str));
    }

    public static Thread findThreadById(long j) {
        Collection<Thread> findThreads = findThreads(new ThreadIdPredicate(j));
        if (findThreads.isEmpty()) {
            return null;
        }
        return findThreads.iterator().next();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static final class AlwaysTruePredicate implements ThreadGroupPredicate, ThreadPredicate {
        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return true;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return true;
        }

        private AlwaysTruePredicate() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class NamePredicate implements ThreadGroupPredicate, ThreadPredicate {
        private final String name;

        public NamePredicate(String str) {
            Validate.isTrue(str != null, "The name must not be null", new Object[0]);
            this.name = str;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadGroupPredicate
        public boolean test(ThreadGroup threadGroup) {
            return threadGroup != null && threadGroup.getName().equals(this.name);
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getName().equals(this.name);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ThreadIdPredicate implements ThreadPredicate {
        private final long threadId;

        public ThreadIdPredicate(long j) {
            if (j <= 0) {
                throw new IllegalArgumentException("The thread id must be greater than zero");
            }
            this.threadId = j;
        }

        @Override // org.apache.commons.lang3.ThreadUtils.ThreadPredicate
        public boolean test(Thread thread) {
            return thread != null && thread.getId() == this.threadId;
        }
    }

    public static Collection<Thread> findThreads(ThreadPredicate threadPredicate) {
        return findThreads(getSystemThreadGroup(), true, threadPredicate);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroupPredicate threadGroupPredicate) {
        return findThreadGroups(getSystemThreadGroup(), true, threadGroupPredicate);
    }

    public static Collection<Thread> findThreads(ThreadGroup threadGroup, boolean z, ThreadPredicate threadPredicate) {
        Thread[] threadArr;
        int enumerate;
        Validate.isTrue(threadGroup != null, "The group must not be null", new Object[0]);
        Validate.isTrue(threadPredicate != null, "The predicate must not be null", new Object[0]);
        int activeCount = threadGroup.activeCount();
        while (true) {
            threadArr = new Thread[activeCount + (activeCount / 2) + 1];
            enumerate = threadGroup.enumerate(threadArr, z);
            if (enumerate < threadArr.length) {
                break;
            }
            activeCount = enumerate;
        }
        ArrayList arrayList = new ArrayList(enumerate);
        for (int i = 0; i < enumerate; i++) {
            if (threadPredicate.test(threadArr[i])) {
                arrayList.add(threadArr[i]);
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public static Collection<ThreadGroup> findThreadGroups(ThreadGroup threadGroup, boolean z, ThreadGroupPredicate threadGroupPredicate) {
        ThreadGroup[] threadGroupArr;
        int enumerate;
        Validate.isTrue(threadGroup != null, "The group must not be null", new Object[0]);
        Validate.isTrue(threadGroupPredicate != null, "The predicate must not be null", new Object[0]);
        int activeGroupCount = threadGroup.activeGroupCount();
        while (true) {
            threadGroupArr = new ThreadGroup[activeGroupCount + (activeGroupCount / 2) + 1];
            enumerate = threadGroup.enumerate(threadGroupArr, z);
            if (enumerate < threadGroupArr.length) {
                break;
            }
            activeGroupCount = enumerate;
        }
        ArrayList arrayList = new ArrayList(enumerate);
        for (int i = 0; i < enumerate; i++) {
            if (threadGroupPredicate.test(threadGroupArr[i])) {
                arrayList.add(threadGroupArr[i]);
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }
}
