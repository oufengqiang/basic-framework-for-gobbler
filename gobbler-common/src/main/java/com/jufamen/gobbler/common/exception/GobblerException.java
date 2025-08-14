package com.jufamen.gobbler.common.exception;

import java.io.Serial;

/**
 * RuntimeException是所有运行时异常（runtime exceptions）的基类
 * 这些异常通常在程序执行期间发生，通常是由代码中的错误导致，而不需要在代码中显式捕获。
 * GobblerException是自定义系统程序运行时异常
 * 异常机制是指当程序出现异常后，程序如何处理。
 * 具体来说，异常机制提供了程序退出的安全通道。当出现异常后，程序执行的流程发生改变，程序的控制权转移到异常处理器。
 */
public class GobblerException extends RuntimeException {

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

    public GobblerException(String message) {
        super(message);
    }
}
