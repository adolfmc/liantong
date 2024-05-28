package com.mob.tools.gui;

import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CachePoolInner<K, V> {
    private int capacity;
    private OnRemoveListener<K, V> listener;
    private LinkedList<CachePoolInner<K, V>.Node<K, V>> pool = new LinkedList<>();
    private int poolSize;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnRemoveListener<K, V> {
        void onRemove(K k, V v);
    }

    public CachePoolInner(int i) {
        this.capacity = i;
    }

    public void setOnRemoveListener(OnRemoveListener<K, V> onRemoveListener) {
        this.listener = onRemoveListener;
    }

    public synchronized boolean put(K k, V v, int i) {
        if (this.pool == null || this.capacity <= 0) {
            return false;
        }
        CachePoolInner<K, V>.Node<K, V> node = new Node<>();
        node.key = k;
        node.value = v;
        ((Node) node).cacheTime = System.currentTimeMillis();
        ((Node) node).size = i;
        this.pool.add(0, node);
        this.poolSize += i;
        while (this.poolSize > this.capacity) {
            CachePoolInner<K, V>.Node<K, V> removeLast = this.pool.removeLast();
            if (removeLast != null) {
                this.poolSize -= ((Node) removeLast).size;
                if (this.listener != null) {
                    this.listener.onRemove(removeLast.key, removeLast.value);
                }
            }
        }
        return true;
    }

    public synchronized boolean put(K k, V v) {
        return put(k, v, 1);
    }

    public synchronized V get(K k) {
        CachePoolInner<K, V>.Node<K, V> node;
        if (this.pool != null && this.capacity > 0) {
            while (this.poolSize > this.capacity) {
                CachePoolInner<K, V>.Node<K, V> removeLast = this.pool.removeLast();
                if (removeLast != null) {
                    this.poolSize -= ((Node) removeLast).size;
                    if (this.listener != null) {
                        this.listener.onRemove(removeLast.key, removeLast.value);
                    }
                }
            }
            Iterator<CachePoolInner<K, V>.Node<K, V>> it = this.pool.iterator();
            while (true) {
                if (!it.hasNext()) {
                    node = null;
                    break;
                }
                node = it.next();
                if (node != null && ((k == null && node.key == null) || (k != null && k.equals(node.key)))) {
                    break;
                }
            }
            if (node != null) {
                this.pool.set(0, node);
                ((Node) node).cacheTime = System.currentTimeMillis();
                return node.value;
            }
        }
        return null;
    }

    public synchronized void clear() {
        if (this.pool != null && this.capacity > 0) {
            if (this.listener == null) {
                this.pool.clear();
            } else {
                while (this.pool.size() > 0) {
                    CachePoolInner<K, V>.Node<K, V> removeLast = this.pool.removeLast();
                    if (removeLast != null) {
                        this.poolSize -= ((Node) removeLast).size;
                        if (this.listener != null) {
                            this.listener.onRemove(removeLast.key, removeLast.value);
                        }
                    }
                }
            }
            this.poolSize = 0;
        }
    }

    public synchronized void trimBeforeTime(long j) {
        if (this.pool != null && this.capacity > 0) {
            int size = this.pool.size() - 1;
            while (size >= 0) {
                if (((Node) this.pool.get(size)).cacheTime < j) {
                    CachePoolInner<K, V>.Node<K, V> remove = this.pool.remove(size);
                    if (remove != null) {
                        this.poolSize -= ((Node) remove).size;
                        if (this.listener != null) {
                            this.listener.onRemove(remove.key, remove.value);
                        }
                    }
                } else {
                    size--;
                }
            }
            while (this.poolSize > this.capacity) {
                CachePoolInner<K, V>.Node<K, V> removeLast = this.pool.removeLast();
                if (removeLast != null) {
                    this.poolSize -= ((Node) removeLast).size;
                    if (this.listener != null) {
                        this.listener.onRemove(removeLast.key, removeLast.value);
                    }
                }
            }
        }
    }

    public synchronized int size() {
        return this.poolSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class Node<K, V> {
        private long cacheTime;
        public K key;
        private int size;
        public V value;

        private Node() {
        }
    }
}
