# JDK8中Iterator接口的源码分析

```java
public interface Iterator<E> {
    
    boolean hasNext();
    
    E next();
    
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
    
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

> 在调用it.next()方法之前必须要调用it.hasNext()进行检测。若不调用，且下一条记录无效，直接调用it.next()会抛出`NoSuchElementException异常`。

```java
// 表示操作不受支持的异常
public class UnsupportedOperationException extends RuntimeException {

    public UnsupportedOperationException() {
    }
    
    public UnsupportedOperationException(String message) {
        super(message);
    }
    
    public UnsupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UnsupportedOperationException(Throwable cause) {
        super(cause);
    }

    static final long serialVersionUID = -1242599979055084673L;
}
```





