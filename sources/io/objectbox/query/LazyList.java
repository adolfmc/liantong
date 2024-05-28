package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.exception.DbException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class LazyList<E> implements List<E> {
    private final Box<E> box;
    private final List<E> entities;
    private volatile int loadedCount;
    private final long[] objectIds;
    final int size;

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class LazyIterator implements ListIterator<E> {
        private int index;

        public LazyIterator(int i) {
            this.index = i;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public E previous() {
            int i = this.index;
            if (i <= 0) {
                throw new NoSuchElementException();
            }
            this.index = i - 1;
            return (E) LazyList.this.get(this.index);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.index < LazyList.this.size;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            if (this.index >= LazyList.this.size) {
                throw new NoSuchElementException();
            }
            E e = (E) LazyList.this.get(this.index);
            this.index++;
            return e;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LazyList(Box<E> box, long[] jArr, boolean z) {
        if (box == null || jArr == null) {
            throw new NullPointerException("Illegal null parameters passed");
        }
        this.box = box;
        this.objectIds = jArr;
        this.size = jArr.length;
        if (z) {
            this.entities = new ArrayList(this.size);
            for (int i = 0; i < this.size; i++) {
                this.entities.add(null);
            }
            return;
        }
        this.entities = null;
    }

    public void loadRemaining() {
        if (this.loadedCount != this.size) {
            checkCached();
            this.box.getStore().runInReadTx(new Runnable() { // from class: io.objectbox.query.LazyList.1
                @Override // java.lang.Runnable
                public void run() {
                    for (int i = 0; i < LazyList.this.size; i++) {
                        LazyList.this.get(i);
                    }
                }
            });
        }
    }

    protected void checkCached() {
        if (this.entities == null) {
            throw new DbException("This operation only works with cached lazy lists");
        }
    }

    public E peek(int i) {
        List<E> list = this.entities;
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    public int getLoadedCount() {
        return this.loadedCount;
    }

    public boolean isLoadedCompletely() {
        return this.loadedCount == this.size;
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        loadRemaining();
        return this.entities.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        loadRemaining();
        return this.entities.containsAll(collection);
    }

    @Override // java.util.List
    public E get(int i) {
        E e;
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException("Illegal cursor location " + i);
        }
        List<E> list = this.entities;
        if (list != null) {
            E e2 = list.get(i);
            if (e2 == null) {
                e2 = this.box.get(this.objectIds[i]);
                synchronized (this) {
                    E e3 = this.entities.get(i);
                    if (e3 == null) {
                        this.entities.set(i, e2);
                        this.loadedCount++;
                    } else {
                        e2 = e3;
                    }
                }
            }
            return e2;
        }
        synchronized (this) {
            e = this.box.get(this.objectIds[i]);
        }
        return e;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        loadRemaining();
        return this.entities.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new LazyIterator(0);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        loadRemaining();
        return this.entities.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new LazyIterator(0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new LazyIterator(i);
    }

    @Override // java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        checkCached();
        for (int i3 = i; i3 < i2; i3++) {
            get(i3);
        }
        return this.entities.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        loadRemaining();
        return this.entities.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        loadRemaining();
        return (T[]) this.entities.toArray(tArr);
    }
}
