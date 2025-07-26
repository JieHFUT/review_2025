# JDK8中Collection接口的源码分析

```java
Java集合框架体系（java.util包下）
java.util.Collection:存储一个一个的数据
    |-----子接口：List:存储有序的、可重复的数据 ("动态"数组)
           |---- ArrayList(主要实现类)、LinkedList、Vector

    |-----子接口：Set:存储无序的、不可重复的数据(高中学习的集合)
           |---- HashSet(主要实现类)、LinkedHashSet、TreeSet


java.util.Map:存储一对一对的数据(key-value键值对，(x1,y1)、(x2,y2) --> y=f(x),类似于高中的函数)
    |---- HashMap(主要实现类)、LinkedHashMap、TreeMap、Hashtable、Properties
```



 ```java
 public interface Collection<E> extends Iterable<E> {
     
     int size();获取当前集合中实际存储的元素个数
         
     boolean isEmpty();判断当前集合是否为空集合
         
     boolean contains(Object o);判断当前集合中是否存在一个与obj对象equals返回true的元素
         
     Iterator<E> iterator();返回迭代器对象，用于集合遍历
         
     Object[] toArray();返回包含当前集合中所有元素的数组  Arrays.asList()返回一个固定长度的list
         
     <T> T[] toArray(T[] a);
     default <T> T[] toArray(IntFunction<T[]> generator) {
         return toArray(generator.apply(0));
     }
     boolean add(E e);添加元素对象到当前集合中
         
     boolean remove(Object o);从当前集合中删除第一个找到的与obj对象equals返回true的元素。
         
 	boolean containsAll(Collection<?> c);判断 c 集合中的元素是否在当前集合中都存在。即coll集合是否是当前集合的“子集”
         
     boolean addAll(Collection<? extends E> c);添加 c 集合中的所有元素对象到当前集合中，即this = this ∪ c
         
     boolean removeAll(Collection<?> c);从当前集合中删除所有与 c 集合中相同的元素。即this = this - this ∩ c
         
     default boolean removeIf(Predicate<? super E> filter) {
         Objects.requireNonNull(filter);
         boolean removed = false;
         final Iterator<E> each = iterator();
         while (each.hasNext()) {
             if (filter.test(each.next())) {
                 each.remove();
                 removed = true;
             }
         }
         return removed;
     }
     boolean retainAll(Collection<?> c);从当前集合中删除两个集合中不同的元素，使得当前集合仅保留与 c 集合中的元素相同的元										 素，即当前集合中仅保留两个集合的交集，即this  = this ∩ c；
     void clear();清空集合元素
         
     boolean equals(Object o);判断当前集合与obj是否相等
         
     int hashCode();获取集合对象的哈希值
         
     @Override
     default Spliterator<E> spliterator() {
         return Spliterators.spliterator(this, 0);
     }
     default Stream<E> stream() {
         return StreamSupport.stream(spliterator(), false);
     }
     default Stream<E> parallelStream() {
         return StreamSupport.stream(spliterator(), true);
     }
 }
 ```

