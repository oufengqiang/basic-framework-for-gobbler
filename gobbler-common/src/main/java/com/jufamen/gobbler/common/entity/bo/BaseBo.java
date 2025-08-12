package com.jufamen.gobbler.common.entity.bo;

import java.io.Serial;
import java.io.Serializable;

/**
 * BO类（Business Object），即业务对象。
 * 可以由Service层输出的封装业务逻辑的对象。
 */
public class BaseBo implements Serializable {

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
}
