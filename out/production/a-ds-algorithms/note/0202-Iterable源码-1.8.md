```java
public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

> 增强 for 循环的底层使用的就是 iterator 接口中的 iterator()方法
> 首先，当且仅当 一个类实现了 Iterable 接口时，它才能使用该语法糖。
> 如果一个类未实现 Iterable 接口，而对其使用该语法糖，则编译时报错：
>
> `foreach not applicable to type 'java.lang.String'`