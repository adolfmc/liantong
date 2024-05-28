package io.objectbox.relation;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.InternalAccess;
import io.objectbox.annotation.apihint.Beta;
import io.objectbox.annotation.apihint.Experimental;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbDetachedException;
import io.objectbox.internal.IdGetter;
import io.objectbox.internal.ReflectionCache;
import io.objectbox.internal.ToManyGetter;
import io.objectbox.internal.ToOneGetter;
import io.objectbox.query.QueryFilter;
import io.objectbox.relation.ListFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ToMany<TARGET> implements Serializable, List<TARGET> {
    private static final Integer ONE = 1;
    private static final long serialVersionUID = 2367317778240689006L;
    private transient BoxStore boxStore;
    private transient Comparator<TARGET> comparator;
    private List<TARGET> entities;
    private Map<TARGET, Boolean> entitiesAdded;
    private Map<TARGET, Boolean> entitiesRemoved;
    List<TARGET> entitiesToPut;
    List<TARGET> entitiesToRemoveFromDb;
    private final Object entity;
    private transient Box entityBox;
    private Map<TARGET, Integer> entityCounts;
    private ListFactory listFactory;
    private final RelationInfo<Object, TARGET> relationInfo;
    private transient boolean removeFromTargetBox;
    private volatile transient Box<TARGET> targetBox;

    public ToMany(Object obj, RelationInfo<? extends Object, TARGET> relationInfo) {
        if (obj == null) {
            throw new IllegalArgumentException("No source entity given (null)");
        }
        if (relationInfo == null) {
            throw new IllegalArgumentException("No relation info given (null)");
        }
        this.entity = obj;
        this.relationInfo = relationInfo;
    }

    @Experimental
    public void setListFactory(ListFactory listFactory) {
        if (listFactory == null) {
            throw new IllegalArgumentException("ListFactory is null");
        }
        this.listFactory = listFactory;
    }

    @Experimental
    public void setComparator(Comparator<TARGET> comparator) {
        this.comparator = comparator;
    }

    @Experimental
    public synchronized void setRemoveFromTargetBox(boolean z) {
        this.removeFromTargetBox = z;
    }

    public ListFactory getListFactory() {
        if (this.listFactory == null) {
            synchronized (this) {
                if (this.listFactory == null) {
                    this.listFactory = new ListFactory.CopyOnWriteArrayListFactory();
                }
            }
        }
        return this.listFactory;
    }

    private void ensureBoxes() {
        if (this.targetBox == null) {
            try {
                this.boxStore = (BoxStore) ReflectionCache.getInstance().getField(this.entity.getClass(), "__boxStore").get(this.entity);
                if (this.boxStore == null) {
                    throw new DbDetachedException("Cannot resolve relation for detached entities, call box.attach(entity) beforehand.");
                }
                this.entityBox = this.boxStore.boxFor(this.relationInfo.sourceInfo.getEntityClass());
                this.targetBox = this.boxStore.boxFor(this.relationInfo.targetInfo.getEntityClass());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void ensureEntitiesWithTrackingLists() {
        ensureEntities();
        if (this.entitiesAdded == null) {
            synchronized (this) {
                if (this.entitiesAdded == null) {
                    this.entitiesAdded = new LinkedHashMap();
                    this.entitiesRemoved = new LinkedHashMap();
                    this.entityCounts = new HashMap();
                    for (TARGET target : this.entities) {
                        Integer put = this.entityCounts.put(target, ONE);
                        if (put != null) {
                            this.entityCounts.put(target, Integer.valueOf(put.intValue() + 1));
                        }
                    }
                }
            }
        }
    }

    private void ensureEntities() {
        List<TARGET> internalGetRelationEntities;
        if (this.entities == null) {
            long id = this.relationInfo.sourceInfo.getIdGetter().getId(this.entity);
            if (id == 0) {
                synchronized (this) {
                    if (this.entities == null) {
                        this.entities = getListFactory().createList();
                    }
                }
                return;
            }
            ensureBoxes();
            int i = this.relationInfo.relationId;
            if (i != 0) {
                internalGetRelationEntities = this.targetBox.internalGetRelationEntities(this.relationInfo.sourceInfo.getEntityId(), i, id, false);
            } else if (this.relationInfo.targetIdProperty != null) {
                internalGetRelationEntities = this.targetBox.internalGetBacklinkEntities(this.relationInfo.targetInfo.getEntityId(), this.relationInfo.targetIdProperty, id);
            } else {
                internalGetRelationEntities = this.targetBox.internalGetRelationEntities(this.relationInfo.targetInfo.getEntityId(), this.relationInfo.targetRelationId, id, true);
            }
            Comparator<TARGET> comparator = this.comparator;
            if (comparator != null) {
                Collections.sort(internalGetRelationEntities, comparator);
            }
            synchronized (this) {
                if (this.entities == null) {
                    this.entities = internalGetRelationEntities;
                }
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean add(TARGET target) {
        trackAdd((ToMany<TARGET>) target);
        return this.entities.add(target);
    }

    private void trackAdd(TARGET target) {
        ensureEntitiesWithTrackingLists();
        Integer put = this.entityCounts.put(target, ONE);
        if (put != null) {
            this.entityCounts.put(target, Integer.valueOf(put.intValue() + 1));
        }
        this.entitiesAdded.put(target, Boolean.TRUE);
        this.entitiesRemoved.remove(target);
    }

    private void trackAdd(Collection<? extends TARGET> collection) {
        ensureEntitiesWithTrackingLists();
        for (TARGET target : collection) {
            trackAdd((ToMany<TARGET>) target);
        }
    }

    private void trackRemove(TARGET target) {
        ensureEntitiesWithTrackingLists();
        Integer remove = this.entityCounts.remove(target);
        if (remove != null) {
            if (remove.intValue() == 1) {
                this.entityCounts.remove(target);
                this.entitiesAdded.remove(target);
                this.entitiesRemoved.put(target, Boolean.TRUE);
            } else if (remove.intValue() > 1) {
                this.entityCounts.put(target, Integer.valueOf(remove.intValue() - 1));
            } else {
                throw new IllegalStateException("Illegal count: " + remove);
            }
        }
    }

    @Override // java.util.List
    public synchronized void add(int i, TARGET target) {
        trackAdd((ToMany<TARGET>) target);
        this.entities.add(i, target);
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean addAll(Collection<? extends TARGET> collection) {
        trackAdd((Collection) collection);
        return this.entities.addAll(collection);
    }

    @Override // java.util.List
    public synchronized boolean addAll(int i, Collection<? extends TARGET> collection) {
        trackAdd((Collection) collection);
        return this.entities.addAll(i, collection);
    }

    @Override // java.util.List, java.util.Collection
    public synchronized void clear() {
        ensureEntitiesWithTrackingLists();
        List<TARGET> list = this.entities;
        if (list != null) {
            for (TARGET target : list) {
                this.entitiesRemoved.put(target, Boolean.TRUE);
            }
            list.clear();
        }
        Map<TARGET, Boolean> map = this.entitiesAdded;
        if (map != null) {
            map.clear();
        }
        Map<TARGET, Integer> map2 = this.entityCounts;
        if (map2 != null) {
            map2.clear();
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        ensureEntities();
        return this.entities.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        ensureEntities();
        return this.entities.containsAll(collection);
    }

    @Override // java.util.List
    public TARGET get(int i) {
        ensureEntities();
        return this.entities.get(i);
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        ensureEntities();
        return this.entities.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        ensureEntities();
        return this.entities.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    @Nonnull
    public Iterator<TARGET> iterator() {
        ensureEntities();
        return this.entities.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ensureEntities();
        return this.entities.lastIndexOf(obj);
    }

    @Override // java.util.List
    @Nonnull
    public ListIterator<TARGET> listIterator() {
        ensureEntities();
        return this.entities.listIterator();
    }

    @Override // java.util.List
    @Nonnull
    public ListIterator<TARGET> listIterator(int i) {
        ensureEntities();
        return this.entities.listIterator(i);
    }

    @Override // java.util.List
    public synchronized TARGET remove(int i) {
        TARGET remove;
        ensureEntitiesWithTrackingLists();
        remove = this.entities.remove(i);
        trackRemove(remove);
        return remove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public synchronized boolean remove(Object obj) {
        boolean remove;
        ensureEntitiesWithTrackingLists();
        remove = this.entities.remove(obj);
        if (remove) {
            trackRemove(obj);
        }
        return remove;
    }

    public synchronized TARGET removeById(long j) {
        ensureEntities();
        int size = this.entities.size();
        IdGetter<TARGET> idGetter = this.relationInfo.targetInfo.getIdGetter();
        for (int i = 0; i < size; i++) {
            TARGET target = this.entities.get(i);
            if (idGetter.getId(target) == j) {
                TARGET remove = remove(i);
                if (remove == target) {
                    return target;
                }
                throw new IllegalStateException("Mismatch: " + remove + " vs. " + target);
            }
        }
        return null;
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean removeAll(Collection<?> collection) {
        boolean z;
        z = false;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection
    public synchronized boolean retainAll(Collection<?> collection) {
        boolean z;
        ensureEntitiesWithTrackingLists();
        z = false;
        ArrayList arrayList = null;
        for (TARGET target : this.entities) {
            if (!collection.contains(target)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(target);
                z = true;
            }
        }
        if (arrayList != null) {
            removeAll(arrayList);
        }
        return z;
    }

    @Override // java.util.List
    public synchronized TARGET set(int i, TARGET target) {
        TARGET target2;
        ensureEntitiesWithTrackingLists();
        target2 = this.entities.set(i, target);
        trackRemove(target2);
        trackAdd((ToMany<TARGET>) target);
        return target2;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        ensureEntities();
        return this.entities.size();
    }

    @Override // java.util.List
    @Nonnull
    public List<TARGET> subList(int i, int i2) {
        ensureEntities();
        return this.entities.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    @Nonnull
    public Object[] toArray() {
        ensureEntities();
        return this.entities.toArray();
    }

    @Override // java.util.List, java.util.Collection
    @Nonnull
    public <T> T[] toArray(T[] tArr) {
        ensureEntities();
        return (T[]) this.entities.toArray(tArr);
    }

    public synchronized void reset() {
        this.entities = null;
        this.entitiesAdded = null;
        this.entitiesRemoved = null;
        this.entitiesToRemoveFromDb = null;
        this.entitiesToPut = null;
        this.entityCounts = null;
    }

    public boolean isResolved() {
        return this.entities != null;
    }

    public int getAddCount() {
        Map<TARGET, Boolean> map = this.entitiesAdded;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public int getRemoveCount() {
        Map<TARGET, Boolean> map = this.entitiesRemoved;
        if (map != null) {
            return map.size();
        }
        return 0;
    }

    public void sortById() {
        ensureEntities();
        Collections.sort(this.entities, new Comparator<TARGET>() { // from class: io.objectbox.relation.ToMany.1
            IdGetter<TARGET> idGetter;

            {
                this.idGetter = ToMany.this.relationInfo.targetInfo.getIdGetter();
            }

            @Override // java.util.Comparator
            public int compare(TARGET target, TARGET target2) {
                long id = this.idGetter.getId(target);
                long id2 = this.idGetter.getId(target2);
                if (id == 0) {
                    id = Long.MAX_VALUE;
                }
                if (id2 == 0) {
                    id2 = Long.MAX_VALUE;
                }
                int i = ((id - id2) > 0L ? 1 : ((id - id2) == 0L ? 0 : -1));
                if (i < 0) {
                    return -1;
                }
                return i > 0 ? 1 : 0;
            }
        });
    }

    public void applyChangesToDb() {
        if (this.relationInfo.sourceInfo.getIdGetter().getId(this.entity) == 0) {
            throw new IllegalStateException("The source entity was not yet persisted (no ID), use box.put() on it instead");
        }
        try {
            ensureBoxes();
            if (internalCheckApplyToDbRequired()) {
                this.boxStore.runInTx(new Runnable() { // from class: io.objectbox.relation.ToMany.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ToMany.this.internalApplyToDb(InternalAccess.getActiveTxCursor(ToMany.this.entityBox), InternalAccess.getActiveTxCursor(ToMany.this.targetBox));
                    }
                });
            }
        } catch (DbDetachedException unused) {
            throw new IllegalStateException("The source entity was not yet persisted, use box.put() on it instead");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    public boolean hasA(QueryFilter<TARGET> queryFilter) {
        for (Object obj : toArray()) {
            if (queryFilter.keep(obj)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    public boolean hasAll(QueryFilter<TARGET> queryFilter) {
        Object[] array = toArray();
        if (array.length == 0) {
            return false;
        }
        for (Object obj : array) {
            if (!queryFilter.keep(obj)) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public TARGET getById(long j) {
        ensureEntities();
        Object[] array = this.entities.toArray();
        IdGetter<TARGET> idGetter = this.relationInfo.targetInfo.getIdGetter();
        for (Object obj : array) {
            TARGET target = (TARGET) obj;
            if (idGetter.getId(target) == j) {
                return target;
            }
        }
        return null;
    }

    @Beta
    public int indexOfId(long j) {
        ensureEntities();
        Object[] array = this.entities.toArray();
        IdGetter<TARGET> idGetter = this.relationInfo.targetInfo.getIdGetter();
        int i = 0;
        for (Object obj : array) {
            if (idGetter.getId(obj) == j) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean hasPendingDbChanges() {
        Map<TARGET, Boolean> map = this.entitiesAdded;
        if (map == null || map.isEmpty()) {
            Map<TARGET, Boolean> map2 = this.entitiesRemoved;
            return (map2 == null || map2.isEmpty()) ? false : true;
        }
        return true;
    }

    @Internal
    public boolean internalCheckApplyToDbRequired() {
        if (hasPendingDbChanges()) {
            synchronized (this) {
                if (this.entitiesToPut == null) {
                    this.entitiesToPut = new ArrayList();
                    this.entitiesToRemoveFromDb = new ArrayList();
                }
            }
            if (this.relationInfo.relationId != 0) {
                return true;
            }
            long id = this.relationInfo.sourceInfo.getIdGetter().getId(this.entity);
            if (id == 0) {
                throw new IllegalStateException("Source entity has no ID (should have been put before)");
            }
            IdGetter<TARGET> idGetter = this.relationInfo.targetInfo.getIdGetter();
            Map<TARGET, Boolean> map = this.entitiesAdded;
            Map<TARGET, Boolean> map2 = this.entitiesRemoved;
            if (this.relationInfo.targetRelationId != 0) {
                return prepareToManyBacklinkEntitiesForDb(id, idGetter, map, map2);
            }
            return prepareToOneBacklinkEntitiesForDb(id, idGetter, map, map2);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean prepareToManyBacklinkEntitiesForDb(long j, IdGetter<TARGET> idGetter, @Nullable Map<TARGET, Boolean> map, @Nullable Map<TARGET, Boolean> map2) {
        boolean z;
        ToManyGetter<Object> toManyGetter = this.relationInfo.backlinkToManyGetter;
        synchronized (this) {
            if (map != null) {
                try {
                    if (!map.isEmpty()) {
                        for (TARGET target : map.keySet()) {
                            ToMany toMany = (ToMany) toManyGetter.getToMany(target);
                            if (toMany == 0) {
                                throw new IllegalStateException("The ToMany property for " + this.relationInfo.targetInfo.getEntityName() + " is null");
                            } else if (toMany.getById(j) == null) {
                                toMany.add(this.entity);
                                this.entitiesToPut.add(target);
                            } else if (idGetter.getId(target) == 0) {
                                this.entitiesToPut.add(target);
                            }
                        }
                        map.clear();
                    }
                } finally {
                }
            }
            if (map2 != null) {
                for (TARGET target2 : map2.keySet()) {
                    ToMany toMany2 = (ToMany) toManyGetter.getToMany(target2);
                    if (toMany2.getById(j) != null) {
                        toMany2.removeById(j);
                        if (idGetter.getId(target2) != 0) {
                            if (this.removeFromTargetBox) {
                                this.entitiesToRemoveFromDb.add(target2);
                            } else {
                                this.entitiesToPut.add(target2);
                            }
                        }
                    }
                }
                map2.clear();
            }
            z = (this.entitiesToPut.isEmpty() && this.entitiesToRemoveFromDb.isEmpty()) ? false : true;
        }
        return z;
    }

    private boolean prepareToOneBacklinkEntitiesForDb(long j, IdGetter<TARGET> idGetter, @Nullable Map<TARGET, Boolean> map, @Nullable Map<TARGET, Boolean> map2) {
        boolean z;
        ToOneGetter<Object> toOneGetter = this.relationInfo.backlinkToOneGetter;
        synchronized (this) {
            if (map != null) {
                try {
                    if (!map.isEmpty()) {
                        for (TARGET target : map.keySet()) {
                            ToOne<TARGET> toOne = toOneGetter.getToOne(target);
                            if (toOne == null) {
                                throw new IllegalStateException("The ToOne property for " + this.relationInfo.targetInfo.getEntityName() + "." + this.relationInfo.targetIdProperty.name + " is null");
                            } else if (toOne.getTargetId() != j) {
                                toOne.setTarget((TARGET) this.entity);
                                this.entitiesToPut.add(target);
                            } else if (idGetter.getId(target) == 0) {
                                this.entitiesToPut.add(target);
                            }
                        }
                        map.clear();
                    }
                } finally {
                }
            }
            if (map2 != null) {
                for (TARGET target2 : map2.keySet()) {
                    ToOne<TARGET> toOne2 = toOneGetter.getToOne(target2);
                    if (toOne2.getTargetId() == j) {
                        toOne2.setTarget(null);
                        if (idGetter.getId(target2) != 0) {
                            if (this.removeFromTargetBox) {
                                this.entitiesToRemoveFromDb.add(target2);
                            } else {
                                this.entitiesToPut.add(target2);
                            }
                        }
                    }
                }
                map2.clear();
            }
            z = (this.entitiesToPut.isEmpty() && this.entitiesToRemoveFromDb.isEmpty()) ? false : true;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Internal
    public void internalApplyToDb(Cursor cursor, Cursor<TARGET> cursor2) {
        Object[] objArr;
        Object[] objArr2;
        ArrayList arrayList;
        Object[] objArr3;
        Object[] array;
        boolean z = this.relationInfo.relationId != 0;
        IdGetter<TARGET> idGetter = this.relationInfo.targetInfo.getIdGetter();
        synchronized (this) {
            objArr = null;
            if (z) {
                for (TARGET target : this.entitiesAdded.keySet()) {
                    if (idGetter.getId(target) == 0) {
                        this.entitiesToPut.add(target);
                    }
                }
                if (this.removeFromTargetBox) {
                    this.entitiesToRemoveFromDb.addAll(this.entitiesRemoved.keySet());
                }
                if (this.entitiesAdded.isEmpty()) {
                    objArr2 = null;
                } else {
                    objArr2 = this.entitiesAdded.keySet().toArray();
                    this.entitiesAdded.clear();
                }
                if (this.entitiesRemoved.isEmpty()) {
                    arrayList = null;
                    objArr3 = objArr2;
                } else {
                    arrayList = new ArrayList(this.entitiesRemoved.keySet());
                    this.entitiesRemoved.clear();
                    objArr3 = objArr2;
                }
            } else {
                arrayList = null;
                objArr3 = null;
            }
            array = this.entitiesToRemoveFromDb.isEmpty() ? null : this.entitiesToRemoveFromDb.toArray();
            this.entitiesToRemoveFromDb.clear();
            if (!this.entitiesToPut.isEmpty()) {
                objArr = this.entitiesToPut.toArray();
            }
            this.entitiesToPut.clear();
        }
        if (array != null) {
            for (Object obj : array) {
                long id = idGetter.getId((TARGET) obj);
                if (id != 0) {
                    cursor2.deleteEntity(id);
                }
            }
        }
        if (objArr != null) {
            for (Object obj2 : objArr) {
                cursor2.put(obj2);
            }
        }
        if (z) {
            long id2 = this.relationInfo.sourceInfo.getIdGetter().getId(this.entity);
            if (id2 == 0) {
                throw new IllegalStateException("Source entity has no ID (should have been put before)");
            }
            if (arrayList != null) {
                removeStandaloneRelations(cursor, id2, arrayList, idGetter);
            }
            if (objArr3 != null) {
                addStandaloneRelations(cursor, id2, objArr3, idGetter, false);
            }
        }
    }

    private void removeStandaloneRelations(Cursor cursor, long j, List<TARGET> list, IdGetter<TARGET> idGetter) {
        Iterator<TARGET> it = list.iterator();
        while (it.hasNext()) {
            if (idGetter.getId(it.next()) == 0) {
                it.remove();
            }
        }
        int size = list.size();
        if (size > 0) {
            long[] jArr = new long[size];
            for (int i = 0; i < size; i++) {
                jArr[i] = idGetter.getId(list.get(i));
            }
            cursor.modifyRelations(this.relationInfo.relationId, j, jArr, true);
        }
    }

    private void addStandaloneRelations(Cursor cursor, long j, @Nullable TARGET[] targetArr, IdGetter<TARGET> idGetter, boolean z) {
        int length = targetArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            long id = idGetter.getId(targetArr[i]);
            if (id == 0) {
                throw new IllegalStateException("Target entity has no ID (should have been put before)");
            }
            jArr[i] = id;
        }
        cursor.modifyRelations(this.relationInfo.relationId, j, jArr, z);
    }

    Object getEntity() {
        return this.entity;
    }
}
