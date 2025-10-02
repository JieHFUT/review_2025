package com.jiehfut.dssmtransaction.service.impl;

import com.jiehfut.dssmtransaction.bean.Book;
import com.jiehfut.dssmtransaction.dao.AccountDao;
import com.jiehfut.dssmtransaction.dao.BookDao;
import com.jiehfut.dssmtransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BookDao bookDao;

    @Autowired
    AccountDao accountDao;

    @Override
    @Transactional
    public void checkout(String username, Integer bookId, Integer buyNum) throws IOException {
        // 1.查询图书信息
        Book book = bookDao.getBookById(bookId);
        // 2.扣钱
        BigDecimal multiply = new BigDecimal(buyNum).multiply(book.getPrice());
        accountDao.updateByUsername(username, multiply);
        // 3.扣减库存
        bookDao.updateBook(bookId, buyNum);

        // 异常 测试事务是否回滚，运行时异常回滚
        // int num = 10 / 0;

        // 测试编译时异常，发现不回滚
        FileInputStream fileInputStream = new FileInputStream("D:\\12345.txt");
        System.out.println("文件长度 = " + fileInputStream.available());
    }

    @Transactional(timeout = 3) // 一旦超过约定时间的秒数，超时就会回滚（超时时间是从事务这个方法开始进入到最后一次数据库 DAO 操作完成的时间）
    public void timeout(){};

    @Transactional(readOnly = true) // 只读操作，可以开启底层优化
    public void readOnly(){};

    @Transactional(rollbackFor = {IOException.class, FileNotFoundException.class}) // 指明哪些编译时异常需要回滚（如指定 IOException 下面的读取文件异常就会回滚）=> 运行时异常 + 指定异常
    // 运行时异常（unchecked exception - 非受检异常）=> 回滚
    // 编译时异常（checked exception - 受检异常）=> 不回滚
    public void rollbackFor(){};
    @Transactional(rollbackForClassName = {"java.io.IOException", "java.io.FileNotFoundException"})
    public void rollbackForClassName(){};

    @Transactional(noRollbackFor = ArithmeticException.class) // 指定哪些运行时异常不去回滚
    public void noRollbackFor(){};



    /**
     * 事务细节信息 *
     * 1.TransactionManager 控制事务的获取、提交、回滚
     * 2.该接口底层使用的是 JdbcTransactionManager
     * 3.事务原理是有一个（切面）进行拦截
     *      （1）事务管理器：TransactionManager 控制事务的获取、提交、回滚
     *      （2）事务拦截器（切面拦截）：class TransactionInterceptor extends TransactionAspectSupport 控制何时提交和回滚
     *          出现异常的时候就会回滚，正常执行就会提交
     *

     @Target({ElementType.TYPE, ElementType.METHOD})
     @Retention(RetentionPolicy.RUNTIME)
     @Inherited
     @Documented
     @Reflective
     public @interface Transactional {
         @AliasFor("transactionManager")
         String value() default "";

         @AliasFor("value")
         String transactionManager() default "";

         String[] label() default {};

         Propagation propagation() default Propagation.REQUIRED;
         传播行为

         Isolation isolation() default Isolation.DEFAULT;
         隔离级别

         int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;
         控制事务超时时间 second

         String timeoutString() default "";


         boolean readOnly() default false;

         Class<? extends Throwable>[] rollbackFor() default {};

         String[] rollbackForClassName() default {};

         Class<? extends Throwable>[] noRollbackFor() default {};

         String[] noRollbackForClassName() default {};
     }



     public interface PlatformTransactionManager extends TransactionManager {
         控制事务的获取
         TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;
         控制事务提交
         void commit(TransactionStatus status) throws TransactionException;
         控制事务回滚
         void rollback(TransactionStatus status) throws TransactionException;
     }
     */


    /**
     * 事务的隔离级别
     * 数据库的写操作底层会加锁，所以写的慢，但是读的话就不用加锁，但是害怕发送边读边写问题
     * ========================== 容易产生并发问题 ============================
     *
     *     事务的隔离级别
     *     1.读未提交（Read Uncommitted）
     *       一个事务可以看到并且读取其他事务的未提交的数据，因为有可能其他事务会产生回滚操作，导致该事务读取的数据为脏数据
     *     2.读已提交（Read Committed）
     *       限制了一个事务不能读取其他事务没有提交的数据，但是如果这个事务多次读取数据
     *       在两次读取数据之间有其他事务修改并且提交了该数据，就会导致这个事务两次读取的数据不一样
     *     3.可重复读（Repeatable Read） - 快照读
     *       限制了一个数据如果被一个事务读取，但是还没有提交（回滚），其他事务就不会修改该数据
     *       保证了事务多次读取一个数据的一致性，但是如果其他事务在这个事务执行期间增加了一些记录，原先的事务对这些记录就比较迷幻
     *     4.串行化（Serializable）
     *       保证了事务实施串形执行，对每个读取的数据记录加一个共享锁
     *
     *     1.读未提交（Read Uncommitted）  脏读、不可重复读，幻读
     *     2.读已提交（Read Committed）    不可重复读，幻读
     *     3.可重复读（Repeatable Read）   幻读
     *     4.串行化（Serializable）‌        没有问题
     *
     *
     *     @Transactional(isolation = Isolation.DEFAULT) // mysql-可重复读 oracle-读已提交
     *     @Transactional(isolation = Isolation.READ_UNCOMMITTED)
     *     @Transactional(isolation = Isolation.READ_COMMITTED)
     *     @Transactional(isolation = Isolation.REPEATABLE_READ)
     *     @Transactional(isolation = Isolation.SERIALIZABLE)
     */







    /**
     * 事务的传播行为：一共有七种传播行为：
     *
     *     - REQUIRED（需要）：支持当前事务，如果不存在就新建一个(默认)【没有就新建，有就加入】
     *     - SUPPORTS（支持）：支持当前事务，如果当前没有事务，就以非事务方式执行【有就加入，没有就不管了】
     *     - MANDATORY（强制）：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常【有就加入，没有就抛异常】
     *     - REQUIRES_NEW（需要新的）：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】
     *     - NOT_SUPPORTED（不支持）：以非事务方式运行，如果有事务存在，挂起当前事务【不支持事务，存在就挂起】
     *     - NEVER（从不）：以非事务方式运行，如果有事务存在，抛出异常【不支持事务，存在就抛异常】
     *     - NESTED（基于保存点）：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像 REQUIRED 一样。
     *             【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和 REQUIRED一样。】
     */


    /**
     * 使用事务传播行为：NESTED（基于保存点）
     * 实现用户结账，（数学异常）触发回滚 => 账户金钱减少（子事务不回滚），库存不变（子事务回滚）
     * @param username
     * @param bookId
     * @param buyNum
     */
    @Transactional()
    public void nested(String username, Integer bookId, Integer buyNum) {
        // 1.扣减金额（金额不回滚，数值变化） - REQUIRES_NEW ====> @Transactional(propagation = Propagation.REQUIRES_NEW)

        // 2.扣减库存（库存回滚，数值不变） - REQUIRED ====> @Transactional(propagation = Propagation.REQUIRED)

        // 3.触发外层事务的回滚
        int rollback = 10 / 0;
    }

    /**
     * 异常传播影响事务传播
     * 使用事务传播行为：NESTED（基于保存点）
     * 实现用户结账：（账户金钱减少 数学异常 -子事务回滚） => 结账事物回滚 => 库存不变（子事务回滚）
     * @param username
     * @param bookId
     * @param buyNum
     */
    @Transactional()
    public void exception(String username, Integer bookId, Integer buyNum) {
        /**
         * 比较特别的是，异常传播会影响事务的传播
         * 如果在  2.扣减金额（金额不回滚，数值变化） - REQUIRES_NEW 中发生异常，子事务 2 发生回滚
         * 那么该外层事务就会发生回滚，三个事务全部回滚
         */
        // 1.扣减库存（库存回滚，数值不变） - REQUIRED ====> @Transactional(propagation = Propagation.REQUIRED)

        // 2（异常）.扣减金额（金额回滚，数值不变化） - REQUIRES_NEW ====> @Transactional(propagation = Propagation.REQUIRES_NEW)
    }


    /**
     * A { @Transactional
     *
     *       B(){    //REQUIRED
     *           F();//REQUIRES_NEW
     *           G();//REQUIRED
     *           H();//REQUIRES_NEW
     *       }
     *       C(){   //REQUIRES_NEW
     *          I();//REQUIRES_NEW
     *          J();//REQUIRED
     *       }
     *       D(){    //REQUIRES_NEW
     *           K();//REQUIRES_NEW
     *           L();//REQUIRES_NEW //点位2： 10/0； K,F,H,C(i,j) = ok, E整个代码走不到，剩下炸
     *       }
     *       E(){    //REQUIRED
     *           M();//REQUIRED
     *           //点位3：10/0；  F,H,C(i,j),D(K,L)= ok
     *           N();//REQUIRES_NEW
     *       }
     *
     *       int i = 10/0;  //点位1：C（I，J）,D(K，L) ，F，H,N= ok
     *   }
     *
     */


    /**
     * 如果外层事务设置超时时间为 3 秒
     *     内层事务设置为 REQUIRED，设置超时时间为 5 秒
     * ==> 内层事务的设置项失效
     * 总结：如果内层事务使用外层的事务，那么内层事务的设置项失效
     *
     *
     * 如果外层事务设置超时时间为 3 秒
     *     内层事务设置为 REQUIRES_NEW，设置超时时间为 5 秒
     * ==> 对于内层事务的设置，超时时间仍然为 5 秒
     * 总结：如果内层事务不使用外层的事务，那么内层事务的设置项起作用
     */







}
