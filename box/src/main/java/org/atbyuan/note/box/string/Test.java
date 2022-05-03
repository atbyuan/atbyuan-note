package org.atbyuan.note.box.string;

import cn.hutool.core.util.RandomUtil;

/**
 * @author atbyuan
 * @date 2021/7/22 0:11
 **/
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            System.out.println("name:" + RandomUtil.randomString(5) + ".\tage:"+ RandomUtil.randomNumbers(2) + ".");
        }
    }

}
