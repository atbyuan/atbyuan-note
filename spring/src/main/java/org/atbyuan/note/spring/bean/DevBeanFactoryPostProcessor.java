package org.atbyuan.note.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author atbyuan
 * @since 2022/2/28 14:18
 **/
@Component
public class DevBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public DevBeanFactoryPostProcessor() {
        super();
        System.out.println("【BeanFactoryPostProcessor】实现类postProcessBeanFactory的构造函数");
    }

    // 允许我们在工厂里所有的bean被加载进来后但是还没初始化前，对所有bean的属性进行修改也可以add属性值，该操作在对应bean的构造函数执行前
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
            throws BeansException {
        System.out.println("【BeanFactoryPostProcessor.postProcessBeanFactory】，来自DevBeanFactoryPostProcessor");
        //获取到Spring中所有的beanName
        String[] beanStr = arg0.getBeanDefinitionNames();
        //循环打印
        for (String beanName : beanStr) {
            System.out.print("bean name:" + beanName + ";");
        }
        System.out.println();
    }
}

