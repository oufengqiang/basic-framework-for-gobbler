package com.jufamen.gobbler.common.exception;

import com.jufamen.gobbler.common.response.Result;
import com.jufamen.gobbler.common.response.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.Objects;

@Slf4j
/*
    @ControllerAdvice用于增强控制器功能的注解，主要用于实现全局异常处理、数据绑定和模型属性增强。
    主要功能
        1、全局异常处理：通过@ExceptionHandler注解捕获控制器抛出的指定异常，统一处理错误响应。
        2、全局数据绑定与预处理：通过@InitBinder注册自定义参数解析器，实现请求参数的预处理。
        3、全局模型属性：通过@ModelAttribute注解，该注解的方法会在控制器方法执行前运行，用于添加公共数据到模型中
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
        @ExceptionHandler注解主要用于处理控制器层中的异常，允许自定义一个方法处理控制器方法抛出的特定异常。
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    /*
        @ResponseBody用于指示方法的返回值应该被直接写入HTTP响应体。这通常用于处理返回非HTML内容的请求，如JSON或XML。
        当方法上标注了@ResponseBody，Spring会使用RequestMappingHandlerAdapter来处理请求，
        而不是默认的HttpRequestHandlerAdapter。这使得返回值能够通过HttpMessageConverter转换为客户端期望的格式。
            1、将返回值转换成JSON，如果返回值是String或者其他基本数据类型则不满足key-value形式，不能转换成json类型，则返回字符串
            2、设置响应头为application/json;charset=utf-8；返回值为字符串，则不能转换成json格式的则响应头设置为text/html
     */
    @ResponseBody
    public Result<Object> error(MethodArgumentNotValidException e){
        log.error("校验参数异常：{}",e.getMessage());
        //Objects.requireNonNull静态方法，用于检查对象引用是否为 null。如果对象引用为 null，则该方法会抛出一个 NullPointerException，并附带可选的错误消息
        return Result.failed(ResultEnum.BAD_REQUEST.getCode(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /*
        @ExceptionHandler注解主要用于处理控制器层中的异常，允许自定义一个方法处理控制器方法抛出的特定异常。
     */
    @ExceptionHandler(SQLException.class)
    /*
        @ResponseBody用于指示方法的返回值应该被直接写入HTTP响应体。这通常用于处理返回非HTML内容的请求，如JSON或XML。
        当方法上标注了@ResponseBody，Spring会使用RequestMappingHandlerAdapter来处理请求，
        而不是默认的HttpRequestHandlerAdapter。这使得返回值能够通过HttpMessageConverter转换为客户端期望的格式。
            1、将返回值转换成JSON，如果返回值是String或者其他基本数据类型则不满足key-value形式，不能转换成json类型，则返回字符串
            2、设置响应头为application/json;charset=utf-8；返回值为字符串，则不能转换成json格式的则响应头设置为text/html
     */
    @ResponseBody
    public Result<Object> handleSQLException(SQLException e) {
        log.error("发生SQL异常：",e);
        return Result.failed(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),ResultEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    /*
        @ExceptionHandler注解主要用于处理控制器层中的异常，允许自定义一个方法处理控制器方法抛出的特定异常。
     */
    @ExceptionHandler(BindingException.class)
    /*
        @ResponseBody用于指示方法的返回值应该被直接写入HTTP响应体。这通常用于处理返回非HTML内容的请求，如JSON或XML。
        当方法上标注了@ResponseBody，Spring会使用RequestMappingHandlerAdapter来处理请求，
        而不是默认的HttpRequestHandlerAdapter。这使得返回值能够通过HttpMessageConverter转换为客户端期望的格式。
            1、将返回值转换成JSON，如果返回值是String或者其他基本数据类型则不满足key-value形式，不能转换成json类型，则返回字符串
            2、设置响应头为application/json;charset=utf-8；返回值为字符串，则不能转换成json格式的则响应头设置为text/html
     */
    @ResponseBody
    public Result<Object> handleBindException(BindingException e) {
        log.error("发生绑定异常：",e);
        return Result.failed(ResultEnum.INTERNAL_SERVER_ERROR.getCode(),ResultEnum.INTERNAL_SERVER_ERROR.getMessage());
    }
}
