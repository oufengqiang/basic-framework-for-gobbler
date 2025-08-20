package com.jufamen.gobbler.common.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jufamen.gobbler.common.check.Delete;
import com.jufamen.gobbler.common.check.Edit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/*
    POJO类（Plain Old Java Object）即普通Java对象，
    是指不依赖特定框架、仅遵循Java基础规范的普通Java对象，强调简洁性和独立性。
    其核心特征包括：
    独立性：不继承框架特定类或实现侵入性接口
    简洁性：仅需符合Java基础语法规范，无需强制包含无参构造函数或特定方法
    灵活性：可封装业务逻辑或数据，常用于DTO（数据传输对象）、PO（持久化对象）等场景
 */
@Data
public class BaseEntity implements Serializable {

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

    @Schema(description = "主键ID", example = "添加时，系统自动生成；修改/删除时，必填参数")
    @NotNull(message = "主键ID不能为空",groups = {Edit.class, Delete.class})
    @TableId(type = IdType.AUTO)
    private Integer id;
}
