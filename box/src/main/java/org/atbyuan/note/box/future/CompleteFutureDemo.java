package org.atbyuan.note.box.future;

import com.google.common.collect.Maps;
import org.atbyuan.note.box.string.StringDemo;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: atbyuan
 * @Date: 2021/7/22 01:09
 **/
public class CompleteFutureDemo {

    public static void main(String[] args) {
        Map map = Maps.newTreeMap();

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);

        CompletableFuture completableFuture = CompletableFuture.completedFuture(false);

        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(StringDemo::new);
        runAsync.thenAccept((t) -> {
            System.out.println(t);
        });

        Arrays.parallelSort(args);
    }
    
}
