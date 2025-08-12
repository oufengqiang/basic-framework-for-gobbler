package com.jufamen.gobbler.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/*
    泛型是程序设计语言中通过参数化类型实现代码复用和类型安全的编程范式，
    允许在类、接口和方法中使用未指定的类型参数，并在实例化时确定具体类型
    泛型是一种编程范式，其核心思想是参数化类型，即在编写代码时不指定具体的数据类型，
    而是在使用时通过类型参数动态指定，这种机制广泛应用于类、接口和方法的定义中
    主要优势：
        1、类型安全：编译时检查类型匹配，避免运行时类型转换错误
        2、代码复用：同一套逻辑可适用于多种数据类型，减少重复代码
        3、性能优化：避免非泛型集合中的装箱/拆箱操作
 */
@Setter
@Getter
public class Result<T> implements Serializable {

    /*
        serialVersionUID是用于在序列化和反序列化过程中进行核验的一个版本号
        序列化运行时将一个版本号（称为serialVersionUID）与每个可序列化类相关联，
        该版本号在反序列化期间用于验证序列化对象的发送方和接收方是否为该对象加载了与序列化兼容的类
        如果接收方为对象加载的类与相应发送方类的serialVersionUID不同，则反序列化将导致InvalidClassException
        如果可序列化类没有显式声明serialVersionUID，则序列化运行时将根据类的各个方面计算该类的默认serialVersionUID值
        但这种默认值可能因编译器差异或类结构细微调整而变化，导致兼容性问题
     */
    @Serial
    private static final long serialVersionUID = 1L;

    // 接口响应状态码
    private int code;

    // 接口响应消息
    private String message;

    // 接口响应数据
    private T data;

    // 无参构造器
    public Result() {

    }

    /**
     * 需要传递接口响应状态码code和接口响应消息message的有参构造器
     * @param code 接口响应状态码
     * @param message 接口响应消息
     */
    public Result(int code, String message) {
        this.code = code; // 把构造器的参数code赋值给成员变量code
        this.message = message; // 把构造器的参数message赋值给成员变量message
    }

    /**
     * 需要传递接口响应状态码code、接口响应消息message和接口响应数据data的有参构造器
     * @param code 接口响应状态码
     * @param message 接口响应消息
     * @param data 接口响应数据
     */
    public Result(int code, String message, T data) {
        this.code = code; // 把构造器的参数code赋值给成员变量code
        this.message = message; // 把构造器的参数message赋值给成员变量message
        this.data = data;// 把构造器的参数data赋值给成员变量data
    }

    /**
     * 默认的接口响应成功结果方法
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> success() {
        // 根据ResultEnum枚举定义的SUCCESS信息创建Result实例并返回
        return new Result<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
    }

    /**
     * 需要传递接口响应数据data的接口响应成功结果方法
     * @param data 接口响应数据
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> success(T data) {
        // 根据ResultEnum枚举定义的SUCCESS信息和方法参数data创建Result实例并返回
        return new Result<>(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data);
    }

    /**
     * 需要传递接口响应消息message和接口响应数据data的接口响应成功结果方法
     * @param message 接口响应消息
     * @param data 接口响应数据
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> success(String message, T data) {
        // 根据ResultEnum枚举定义的SUCCESS信息、方法参数message和方法参数data创建Result实例并返回
        return new Result<>(ResultEnum.SUCCESS.getCode(),message,data);
    }

    /**
     * 默认的接口响应失败结果方法
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> failed() {
        // 根据ResultEnum枚举定义的SUCCESS信息创建Result实例并返回
        return new Result<>(ResultEnum.FAILED.getCode(),ResultEnum.FAILED.getMessage());
    }

    /**
     * 需要传递接口响应消息message的接口响应失败结果方法
     * @param message 接口响应消息
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> failed(String message) {
        // 根据ResultEnum枚举定义的FAILED信息和方法参数message创建Result实例并返回
        return new Result<>(ResultEnum.FAILED.getCode(),message);
    }

    /**
     * 需要传递响应结果枚举项resultEnum的接口响应失败结果方法
     * @param resultEnum 响应结果枚举项
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> failed(ResultEnum resultEnum) {
        // 根据方法参数resultEnum创建Result实例并返回
        return new Result<>(resultEnum.getCode(),resultEnum.getMessage());
    }

    /**
     * 需要传递接口响应状态码code和接口响应数据data的接口响应失败结果方法
     * @param code 接口响应状态码
     * @param message 接口响应消息
     * @return 需要返回Result实例
     * @param <T> 泛型，传递参数时确定数据类型
     */
    public static <T> Result<T> failed(int code,String message) {
        // 根据方法参数code和方法参数message创建Result实例并返回
        return new Result<>(code,message);
    }
}
