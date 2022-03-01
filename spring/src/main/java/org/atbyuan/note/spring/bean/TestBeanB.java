package org.atbyuan.note.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author atbyuan
 * @since 2022/2/28 14:22
 **/
@Component
public class TestBeanB {

    @Autowired
    private TestBeanA testBeanA;

    public TestBeanB() {
        System.out.println("【TestBeanB.默认构造器】");
    }
}
