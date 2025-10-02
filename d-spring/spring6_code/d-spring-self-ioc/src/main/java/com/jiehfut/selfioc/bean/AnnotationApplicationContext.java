package com.jiehfut.selfioc.bean;

import com.jiehfut.selfioc.annotation.Bean;
import com.jiehfut.selfioc.annotation.Di;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationApplicationContext implements ApplicationContext {


    // 创建一个 map 集合，用于放置 bean 对象
    private Map<Class, Object> beanFactory = new HashMap<Class, Object>();
    // 方便截取绝对路径到使用路径
    private String rootPath;



    @Override
    public Object getBean(Class clazz) {
        // 返回根据 Class 在 map 中找到的对象
        return beanFactory.get(clazz);
    }

    /**
     * 设置包的扫描规则：如果当前包以及其子包，如果哪个类有 @bean 注解，把这个类通过反射进行实例化
     * 创建其有参数的构造，传递包路径，设置包扫描规则
     * @param basePackage : 用户配置时设置的把该路径下注解的类载入"IoC"中，如：com.jiehfut.selfioc
     * @throws IOException
     */
    public AnnotationApplicationContext(String basePackage) throws Exception {
        // 根据包的路径来设置扫描方法
        // 1.把点替换成斜杠
        String packagePath = basePackage.replaceAll("\\.", "\\\\");
        // 2.获取包的绝对路径（编译之后的 target 文件中），packagePath：com\jiehfut\selfioc
        // 通过线程获得一个枚举对象
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement(); // 获得在 target 中的绝对路径（从盘符开始）
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
            rootPath = filePath.substring(0, filePath.length()-packagePath.length());
            // 3.根据包路径进行包扫描的过程
            loadBean(new File(filePath));
        }
        // 进行属性注入
        loadDi();
    }



    /**
     * 包进行扫描的过程
     * @param file，传入的是绝对路径（从盘符开始）创建的 File 类
     */
    private void loadBean(File file) throws Exception {
        // 1.判断是否是文件夹
        if (file.isDirectory()) {
            // 2.获取文件夹中的所有内容
            File[] childrenFiles = file.listFiles();
            // 3.判断：若为空就直接返回
            if (childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            // 4.如果文件夹内不为空，就遍历文件夹内所有的内容
            for (File childFile : childrenFiles) {
                if (childFile.isDirectory()) {
                    // 4.1.遍历得到每一个 File 对象，继续判断，如果还是文件夹就继续递归
                    loadBean(childFile);
                } else {
                    // 4.2.遍历得到的 File 对象不是文件夹，是一个文件，得到（包路径 + 类名称），进行字符串截取
                    String pathWithClass = childFile.getAbsolutePath().substring(rootPath.length() - 1);
                    if (pathWithClass.contains(".class")) {
                        // 4.3.判断是否是 .class 文件类型，如果是就把 \ 替换成为.  把 .class 去掉（com.jiehfut.selfioc.service.impl.userService）
                        String allName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        // 4.4.判断是否有注解 @Bean，有的话就实例化
                        Class clazz = Class.forName(allName);
                        if (!clazz.isInterface()) {
                            // 不是接口，判断是否有注解
                            Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                // 该类上有注解，将其实例化
                                Object instance = clazz.getConstructor().newInstance();

                                // 4.5.实例化后将其放入 map 中（beanFactory）
                                if (clazz.getInterfaces().length > 0) {
                                    // 做一个处理，如果当前这个类如果有接口，就让接口的 class 作为 key
                                    beanFactory.put(clazz.getInterfaces()[0], instance);
                                } else {
                                    // 没有接口
                                    beanFactory.put(clazz, instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    // 进行属性注入，已知实例化的对象都存储在 map 集合中
    private void loadDi() throws IllegalAccessException {
        // 1.遍历 map 集合
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            // 2.获取 map 集合中的每一个对象（value）
            Object object = entry.getValue();
            // 获取对象的 class
            Class<?> clazz = object.getClass();
            // 3.遍历得到的每个对象的属性数组，判断属性上是否有 @Di 注解
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Di annotation = declaredField.getAnnotation(Di.class);
                if (annotation != null) {
                    // 4.对有注解的属性，把对象进行注入
                    declaredField.setAccessible(true);
                    declaredField.set(object, beanFactory.get(declaredField.getType()));
                }
            }
        }
    }



}
