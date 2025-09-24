package jiehfut.hfut.schedule.pojo;

/**
 * ClassName: SysUser
 * Package: jie.hfut.schedule.pojo
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/23 21:57
 * @Version 1.0
 */

import lombok.*;

import java.io.Serializable;

/**
 * 1.实体类的类名和表格名称应该对应
 * 2.实体类的属性名和表格的列名应该对应
 * 3.每个属性都应该具备 getter && setter
 * 4.必须具备无参构造器
 * 5.每个属性都必须是私有的
 * 6.应该实现序列化接口（缓存、分布式项目传递数据可能会将对象序列化）
 * 7.应该重写类的 hashcode && equels 方法
 * 8.toString 可写可不写
 *
 *
 *
 * 使用 lombok 帮助我们生成这些内容 getter setter 全参构造 无参构造 equals hashcode
 * lombok 使用步骤：
 *      1.检查 IDEA 是否已经安装了 lombok 插件
 *      2.在 settings-build.execution.deployment-compiler-annotation processors 检查是否勾选了 enable annotation processing
 *      3.导入 lombok.jar
 *      4.在实体类上添加注解（即视为添加了该有的方法）
 *
 *      @NoArgsConstructor    添加了全参构造
 *      @AllArgsConstructor   添加了无参构造
 *      @Getter               添加了 getter 方法
 *      @Setter               添加了 setter 方法
 *      @EqualsAndHashCode    添加了 equals && hashcode 方法
 *      @ToString             添加了 toString 方法
 *
 *      后面的四个方法可以合并成为同一个注解 @Data
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1329857489313L;

    private Integer uid;

    private String username;

    private String userPwd;







//    public SysUser() {
//    }
//
//    public SysUser(Integer uid, String username, String uesrPwd) {
//        this.uid = uid;
//        this.username = username;
//        this.uesrPwd = uesrPwd;
//    }
//
//    @Override
//    public String toString() {
//        return "SysUser [uid=" + uid + ", username=" + username + ", uesrPwd=";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SysUser sysUser = (SysUser) o;
//        return Objects.equals(uid, sysUser.uid) && Objects.equals(username, sysUser.username) && Objects.equals(uesrPwd, sysUser.uesrPwd);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(uid, username, uesrPwd);
//    }
//
//    public Integer getUid() {
//        return uid;
//    }
//
//    public void setId(Integer uid) {
//        this.uid = uid;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getUesrPwd() {
//        return uesrPwd;
//    }
//
//    public void setUesrPwd(String uesrPwd) {
//        this.uesrPwd = uesrPwd;
//    }


}
