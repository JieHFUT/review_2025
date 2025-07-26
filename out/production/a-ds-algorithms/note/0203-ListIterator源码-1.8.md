# JDK8中ListIterator接口的源码分析

```java
public interface ListIterator<E> extends Iterator<E> {
    boolean hasNext();如果仍有元素可以迭代，则返回 true。
    E next();返回迭代的下一个元素	
    boolean hasPrevious();
    E previous();
    int nextIndex();
    int previousIndex();
    void remove();  //此remove之前可以进行条件判定
    void set(E e);
    void add(E e);
}
```

