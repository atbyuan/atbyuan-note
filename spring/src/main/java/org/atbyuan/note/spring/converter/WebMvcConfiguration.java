package org.atbyuan.note.spring.converter;

import cn.hutool.core.date.DatePattern;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 重写Json序列化协议,指定通用日期转换格式和中文编码
 *
 * @author atbyuan
 * @since 2022/3/1 13:52
 **/
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.forEach(converter -> {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                DateFormat dateFormat = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
                jackson2HttpMessageConverter.getObjectMapper().setDateFormat(dateFormat);
//                jackson2HttpMessageConverter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            }
        });
    }

    //@Override
    //public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    //    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
    //            .indentOutput(true)
    //            .dateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN))
    //            .modulesToInstall(new ParameterNamesModule());
    //    converters.clear();
    //    converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    //}
}
