package org.atbyuan.note.spring.bean;

/**
 * @author atbyuan
 * @since 2022/2/28 14:19
 **/

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 这里给出一个实现了BeanPostProcessor的类，目的是为了看出postProcessAfterInitialization、postProcessBeforeInitialization调用的时间点。
 * 完成bean实例化、配置以及其他初始化方法前后要添加一些自己逻辑处理则要实现接口BeanPostProcessor
 *
 * @author atbyuan
 * @since 2022/2/28 14:18
 */
@Component
public class DevBeanPostProcessor implements BeanPostProcessor {

    public DevBeanPostProcessor() {
        super();
        System.out.println("【DevBeanPostProcessor】BeanPostProcessor实现类的构造函数");
    }

    // 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的业务
    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {

        if (arg0.getClass() == TestBeanA.class || arg0.getClass() == TestBeanB.class) {
            System.out.println("【BeanPostProcessor.postProcessAfterInitialization】来自DevBeanPostProcessor，beanName:" + arg1);
        }
        return arg0;
    }

    // 实例化、依赖注入、初始化后完成一些定制的业务
    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {

        if (arg0.getClass() == TestBeanA.class || arg0.getClass() == TestBeanB.class) {
            System.out.println("【BeanPostProcessor.postProcessBeforeInitialization】来自DevBeanPostProcessor，beanName:" + arg1);
        }
        return arg0;
    }
}
