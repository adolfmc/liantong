package szcom.googlecode.mp4parser.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class LazyList<E> extends AbstractList<E> {
    private static final Logger LOG = Logger.getLogger(LazyList.class);
    Iterator<E> elementSource;
    List<E> underlying;

    public LazyList(List<E> list, Iterator<E> it) {
        this.underlying = list;
        this.elementSource = it;
    }

    private void blowup() {
        LOG.logDebug("blowup running");
        while (this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        if (this.underlying.size() > i) {
            return this.underlying.get(i);
        }
        if (this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
            return get(i);
        }
        throw new NoSuchElementException();
    }

    public List<E> getUnderlying() {
        return this.underlying;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: szcom.googlecode.mp4parser.util.LazyList.1
            int pos = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.pos < LazyList.this.underlying.size() || LazyList.this.elementSource.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.pos >= LazyList.this.underlying.size()) {
                    LazyList.this.underlying.add(LazyList.this.elementSource.next());
                    return (E) next();
                }
                List<E> list = LazyList.this.underlying;
                int i = this.pos;
                this.pos = i + 1;
                return list.get(i);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        LOG.logDebug("potentially expensive size() call");
        blowup();
        return this.underlying.size();
    }
}
