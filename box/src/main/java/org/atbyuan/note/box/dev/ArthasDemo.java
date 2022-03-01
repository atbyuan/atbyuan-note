package org.atbyuan.note.box.dev;

import com.alibaba.fastjson.JSON;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author atbyuan
 * @since 2022/2/23 21:56
 */
public class ArthasDemo {
    public static void main(String[] args) {
        String s = "[{\"name\":\"zhangsan\",\"age\":\"10\",\"telephone\":\"123456\",\"interests\":[\"sing\",\"dance\",\"rap\"]},\n" +
                "{\"name\":\"lisi\",\"age\":\"20\",\"telephone\":\"123457\",\"interests\":[\"sing\",\"swim\"]},\n" +
                "{\"name\":\"wangwu\",\"age\":\"30\",\"telephone\":\"123458\",\"interests\":[\"sing\",\"program\"]}]";

        //模拟一遍遍的调用方法的过程
        for (; ; ) {
            System.out.println(new ArthasDemo().convert(s));
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private List<People> convert(String s) {
        return JSON.parseArray(s, People.class);
    }

    @Getter
    @Setter
    @ToString
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class People {
        /** 姓名 */
        String name;
        /** 年龄 */
        String age;
        /** 电话 */
        String telephone;
        /** 兴趣列表 */
        List<String> interests;
    }
}