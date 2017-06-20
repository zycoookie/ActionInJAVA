package me.cookie.test.jvm;

import com.alibaba.fastjson.JSONObject;
import me.cookie.test.jvm.classload.Sub;
import me.cookie.test.jvm.singleton.Singleton;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import me.cookie.test.jvm.singleton.SingletonEnum;
import org.junit.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.stream.Stream;

/**
 * Created by cookie on 2017/5/30.
 */
public class TestCglib {

    /**
     * 被代理的类 由于找不到目标class文件 所以无法通过cglib找到方法参数名
     * @throws NoSuchMethodException
     */
    @Test
    public void testCglibMethodParamName() throws NoSuchMethodException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ObjProxy.class);
        enhancer.setUseCache(false);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o,objects));
        Object obj = enhancer.create();
        System.out.println(obj);

        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        Method method = ObjProxy.class.getMethod("function", String.class, Integer.class);
        Stream.of(parameterNameDiscoverer.getParameterNames(method)).forEach(name -> System.out.println(name));
        method = obj.getClass().getMethod("function", String.class, Integer.class);
        Stream.of(parameterNameDiscoverer.getParameterNames(method)).forEach(name -> System.out.println(name));
    }

    /**
     * 查看被编译成字节码的MethodParameter文件
     */
    @Test
    public void testGetMethodParameters(){
        new MethodParameter();
    }

    /**
     * 使用内部类实现懒汉单例模式
     * 通过-XX:+TraceClassLoading查看
     */
    @Test
    public void test内部类懒汉模式(){
        System.out.println(Singleton.class);
        System.out.println(Singleton.getInstance());
    }

    /**
     * 使用枚举实现懒汉单例模式
     * 通过-XX:+TraceClassLoading查看
     */
    @Test
    public void test枚举懒汉模式(){
        System.out.println(SingletonEnum.class);
        System.out.println(SingletonEnum.Singleton);
    }

    /**
     * 测试类加载顺序
     */
    @Test
    public void test类加载顺序(){
        System.out.println(Sub.class);
    }

    /**
     * 测试类初始化顺序
     */
    @Test
    public void test类初始化顺序() throws InterruptedException {
        System.out.println(Sub.class);
        Thread.sleep(4000);
        new Sub();
    }

    /**
     * 测试fastjson等第三方序列化解决方案是否支持transient
     */
    @Test
    public void testTransient(){
        System.out.println(JSONObject.toJSONString(Bean.builder().name("name").value(1999).build()));
        JSONObject.parseObject(JSONObject.toJSONString(Bean.builder().name("name").value(1999).build()),Bean.class);
    }

    /**
     * 事实证明不是很容易把一个enum序列化
     */
    @Test
    public void testEnumDeserialize(){
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(SingletonEnum.Singleton);
        System.out.println(jsonObject);
    }

    /**
     * 测试自动装箱性能差距
     */
    @Test
    public void testAutoBox(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        long count = 0;
        start = System.currentTimeMillis();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            count += 1;
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += 1;
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
//        calendar.set(1970,1,1,0,0,0);
        calendar.setTimeInMillis(-100000);
        System.out.println(calendar.getTime());
    }
}
