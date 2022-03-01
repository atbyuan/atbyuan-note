package org.atbyuan.note.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * 适配器类，基类是BeanPostProcessor的实现类
 *
 * @author atbyuan
 * @since 2022/2/28 14:20
 **/
@Component
public class DevInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public DevInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("【DevInstantiationAwareBeanPostProcessor】InstantiationAwareBeanPostProcessorAdapter实现类的构造函数");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass() == TestBeanA.class || bean.getClass() == TestBeanB.class) {
            System.out.println("【InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInitialization】来自DevInstantiationAwareBeanPostProcessor，beanName:" + beanName);

        }
        return bean;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == TestBeanA.class || bean.getClass() == TestBeanB.class) {
            System.out.println("【InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInitialization】来自DevInstantiationAwareBeanPostProcessor，beanName:" + beanName);
        }
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

        if (bean.getClass() == TestBeanA.class || bean.getClass() == TestBeanB.class) {
            System.out.println("【InstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues】来自DevInstantiationAwareBeanPostProcessor，beanName:" + beanName);
        }
        return pvs;
    }
}