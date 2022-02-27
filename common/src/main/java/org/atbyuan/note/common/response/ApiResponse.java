package org.atbyuan.note.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author atbyuan
 * @since 2022/1/28 21:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T data;

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 500;

    private static final String SUCCESS_MSG = "请求成功";
    private static final String ERROR_MSG = "系统错误";

    public static <T> ApiResponse<T> SUCCESS() {
        return ApiResponse.<T>builder()
                .code(SUCCESS_CODE).message(SUCCESS_MSG)
                .build();
    }

    public static <T> ApiResponse<T> SUCCESS(T data) {
        return ApiResponse.<T>builder()
                .code(SUCCESS_CODE).message(SUCCESS_MSG).data(data)
                .build();
    }
}
