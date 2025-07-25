# 1.8内部类

```java
final class EntryIterator extends HashIterator
    implements Iterator<Map.Entry<K,V>> {
    public final Map.Entry<K,V> next() { return nextNode(); }
}
===================================================================
final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
    public final int size()                 { return size; }
    public final void clear()               { HashMap.this.clear(); }
    public final Iterator<Map.Entry<K,V>> iterator() {
        return new EntryIterator();
    }
    public final boolean contains(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry<?,?>) o;
        Object key = e.getKey();
        Node<K,V> candidate = getNode(hash(key), key);
        return candidate != null && candidate.equals(e);
    }
    public final boolean remove(Object o) {
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>) o;
            Object key = e.getKey();
            Object value = e.getValue();
            return removeNode(hash(key), key, value, true, true) != null;
        }
        return false;
    }
    public final Spliterator<Map.Entry<K,V>> spliterator() {
        return new EntrySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }
    public final void forEach(Consumer<? super Map.Entry<K,V>> action) {
        Node<K,V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next)
                    action.accept(e);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
}
===================================================================
static final class EntrySpliterator<K,V>
    extends HashMapSpliterator<K,V>
    implements Spliterator<Map.Entry<K,V>> {
    EntrySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                     int expectedModCount) {
        super(m, origin, fence, est, expectedModCount);
    }

    public EntrySpliterator<K,V> trySplit() {
        int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
        return (lo >= mid || current != null) ? null :
        new EntrySpliterator<>(map, lo, index = mid, est >>>= 1,
                               expectedModCount);
    }

    public void forEachRemaining(Consumer<? super Map.Entry<K,V>> action) {
        int i, hi, mc;
        if (action == null)
            throw new NullPointerException();
        HashMap<K,V> m = map;
        Node<K,V>[] tab = m.table;
        if ((hi = fence) < 0) {
            mc = expectedModCount = m.modCount;
            hi = fence = (tab == null) ? 0 : tab.length;
        }
        else
            mc = expectedModCount;
        if (tab != null && tab.length >= hi &&
            (i = index) >= 0 && (i < (index = hi) || current != null)) {
            Node<K,V> p = current;
            current = null;
            do {
                if (p == null)
                    p = tab[i++];
                else {
                    action.accept(p);
                    p = p.next;
                }
            } while (p != null || i < hi);
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    public boolean tryAdvance(Consumer<? super Map.Entry<K,V>> action) {
        int hi;
        if (action == null)
            throw new NullPointerException();
        Node<K,V>[] tab = map.table;
        if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
            while (current != null || index < hi) {
                if (current == null)
                    current = tab[index++];
                else {
                    Node<K,V> e = current;
                    current = current.next;
                    action.accept(e);
                    if (map.modCount != expectedModCount)
                        throw new ConcurrentModificationException();
                    return true;
                }
            }
        }
        return false;
    }

    public int characteristics() {
        return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
            Spliterator.DISTINCT;
    }
}
===================================================================
abstract class HashIterator {
    Node<K,V> next;        // next entry to return
    Node<K,V> current;     // current entry
    int expectedModCount;  // for fast-fail
    int index;             // current slot

    HashIterator() {
        expectedModCount = modCount;
        Node<K,V>[] t = table;
        current = next = null;
        index = 0;
        if (t != null && size > 0) { // advance to first entry
            do {} while (index < t.length && (next = t[index++]) == null);
        }
    }

    public final boolean hasNext() {
        return next != null;
    }

    final Node<K,V> nextNode() {
        Node<K,V>[] t;
        Node<K,V> e = next;
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        if (e == null)
            throw new NoSuchElementException();
        if ((next = (current = e).next) == null && (t = table) != null) {
            do {} while (index < t.length && (next = t[index++]) == null);
        }
        return e;
    }

    public final void remove() {
        Node<K,V> p = current;
        if (p == null)
            throw new IllegalStateException();
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
        current = null;
        K key = p.key;
        removeNode(hash(key), key, null, false, false);
        expectedModCount = modCount;
    }
}
===================================================================
static class HashMapSpliterator<K,V> {
    final HashMap<K,V> map;
    Node<K,V> current;          // current node
    int index;                  // current index, modified on advance/split
    int fence;                  // one past last index
    int est;                    // size estimate
    int expectedModCount;       // for comodification checks

    HashMapSpliterator(HashMap<K,V> m, int origin,
                       int fence, int est,
                       int expectedModCount) {
        this.map = m;
        this.index = origin;
        this.fence = fence;
        this.est = est;
        this.expectedModCount = expectedModCount;
    }

    final int getFence() { // initialize fence and size on first use
        int hi;
        if ((hi = fence) < 0) {
            HashMap<K,V> m = map;
            est = m.size;
            expectedModCount = m.modCount;
            Node<K,V>[] tab = m.table;
            hi = fence = (tab == null) ? 0 : tab.length;
        }
        return hi;
    }

    public final long estimateSize() {
        getFence(); // force init
        return (long) est;
    }
}
===================================================================
final class KeyIterator extends HashIterator
    implements Iterator<K> {
    public final K next() { return nextNode().key; }
}
===================================================================
final class KeySet extends AbstractSet<K> {
    public final int size()                 { return size; }
    public final void clear()               { HashMap.this.clear(); }
    public final Iterator<K> iterator()     { return new KeyIterator(); }
    public final boolean contains(Object o) { return containsKey(o); }
    public final boolean remove(Object key) {
        return removeNode(hash(key), key, null, false, true) != null;
    }
    public final Spliterator<K> spliterator() {
        return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }
    public final void forEach(Consumer<? super K> action) {
        Node<K,V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next)
                    action.accept(e.key);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
}
===================================================================
static final class KeySpliterator<K,V>
    extends HashMapSpliterator<K,V>
    implements Spliterator<K> {
    KeySpliterator(HashMap<K,V> m, int origin, int fence, int est,
                   int expectedModCount) {
        super(m, origin, fence, est, expectedModCount);
    }

    public KeySpliterator<K,V> trySplit() {
        int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
        return (lo >= mid || current != null) ? null :
        new KeySpliterator<>(map, lo, index = mid, est >>>= 1,
                             expectedModCount);
    }

    public void forEachRemaining(Consumer<? super K> action) {
        int i, hi, mc;
        if (action == null)
            throw new NullPointerException();
        HashMap<K,V> m = map;
        Node<K,V>[] tab = m.table;
        if ((hi = fence) < 0) {
            mc = expectedModCount = m.modCount;
            hi = fence = (tab == null) ? 0 : tab.length;
        }
        else
            mc = expectedModCount;
        if (tab != null && tab.length >= hi &&
            (i = index) >= 0 && (i < (index = hi) || current != null)) {
            Node<K,V> p = current;
            current = null;
            do {
                if (p == null)
                    p = tab[i++];
                else {
                    action.accept(p.key);
                    p = p.next;
                }
            } while (p != null || i < hi);
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    public boolean tryAdvance(Consumer<? super K> action) {
        int hi;
        if (action == null)
            throw new NullPointerException();
        Node<K,V>[] tab = map.table;
        if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
            while (current != null || index < hi) {
                if (current == null)
                    current = tab[index++];
                else {
                    K k = current.key;
                    current = current.next;
                    action.accept(k);
                    if (map.modCount != expectedModCount)
                        throw new ConcurrentModificationException();
                    return true;
                }
            }
        }
        return false;
    }

    public int characteristics() {
        return (fence < 0 || est == map.size ? Spliterator.SIZED : 0) |
            Spliterator.DISTINCT;
    }
}
===================================================================
====================================================================================================================================================================================================================================================================================================================================
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
===================================================================
   ====================================================================================================================================================================================================================================================================================================================================
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
    TreeNode(int hash, K key, V val, Node<K,V> next) {
        super(hash, key, val, next);
    }

    /**
         * Returns root of tree containing this node.
         */
    final TreeNode<K,V> root() {
        for (TreeNode<K,V> r = this, p;;) {
            if ((p = r.parent) == null)
                return r;
            r = p;
        }
    }
===================================================================
private static final class UnsafeHolder {
    private UnsafeHolder() { throw new InternalError(); }
    private static final sun.misc.Unsafe unsafe
        = sun.misc.Unsafe.getUnsafe();
    private static final long LF_OFFSET;
    static {
        try {
            LF_OFFSET = unsafe.objectFieldOffset(HashMap.class.getDeclaredField("loadFactor"));
        } catch (NoSuchFieldException e) {
            throw new InternalError();
        }
    }
    static void putLoadFactor(HashMap<?, ?> map, float lf) {
        unsafe.putFloat(map, LF_OFFSET, lf);
    }
}
===================================================================
final class ValueIterator extends HashIterator
    implements Iterator<V> {
    public final V next() { return nextNode().value; }
}
===================================================================
final class Values extends AbstractCollection<V> {
    public final int size()                 { return size; }
    public final void clear()               { HashMap.this.clear(); }
    public final Iterator<V> iterator()     { return new ValueIterator(); }
    public final boolean contains(Object o) { return containsValue(o); }
    public final Spliterator<V> spliterator() {
        return new ValueSpliterator<>(HashMap.this, 0, -1, 0, 0);
    }
    public final void forEach(Consumer<? super V> action) {
        Node<K,V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (int i = 0; i < tab.length; ++i) {
                for (Node<K,V> e = tab[i]; e != null; e = e.next)
                    action.accept(e.value);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
}
===================================================================
static final class ValueSpliterator<K,V>
    extends HashMapSpliterator<K,V>
    implements Spliterator<V> {
    ValueSpliterator(HashMap<K,V> m, int origin, int fence, int est,
                     int expectedModCount) {
        super(m, origin, fence, est, expectedModCount);
    }

    public ValueSpliterator<K,V> trySplit() {
        int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
        return (lo >= mid || current != null) ? null :
        new ValueSpliterator<>(map, lo, index = mid, est >>>= 1,
                               expectedModCount);
    }

    public void forEachRemaining(Consumer<? super V> action) {
        int i, hi, mc;
        if (action == null)
            throw new NullPointerException();
        HashMap<K,V> m = map;
        Node<K,V>[] tab = m.table;
        if ((hi = fence) < 0) {
            mc = expectedModCount = m.modCount;
            hi = fence = (tab == null) ? 0 : tab.length;
        }
        else
            mc = expectedModCount;
        if (tab != null && tab.length >= hi &&
            (i = index) >= 0 && (i < (index = hi) || current != null)) {
            Node<K,V> p = current;
            current = null;
            do {
                if (p == null)
                    p = tab[i++];
                else {
                    action.accept(p.value);
                    p = p.next;
                }
            } while (p != null || i < hi);
            if (m.modCount != mc)
                throw new ConcurrentModificationException();
        }
    }

    public boolean tryAdvance(Consumer<? super V> action) {
        int hi;
        if (action == null)
            throw new NullPointerException();
        Node<K,V>[] tab = map.table;
        if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
            while (current != null || index < hi) {
                if (current == null)
                    current = tab[index++];
                else {
                    V v = current.value;
                    current = current.next;
                    action.accept(v);
                    if (map.modCount != expectedModCount)
                        throw new ConcurrentModificationException();
                    return true;
                }
            }
        }
        return false;
    }

    public int characteristics() {
        return (fence < 0 || est == map.size ? Spliterator.SIZED : 0);
    }
}
```



