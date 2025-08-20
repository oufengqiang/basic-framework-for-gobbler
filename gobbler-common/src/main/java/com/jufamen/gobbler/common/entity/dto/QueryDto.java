package com.jufamen.gobbler.common.entity.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/*
    DTO类（Data Transfer Object）即数据传输对象，
    是一种设计模式，用于在应用程序间传递数据，通过封装业务逻辑和表示层的数据，实现解耦和减少数据传输量，
    从而提高系统的可维护性、可拓展性和性能效率。
    其特点和用途：
    封装数据：封装一个或多个领域模型的数据，转换为一个独立的数据对象，以便在不同层次或系统之间传输
    数据传输：封装好后可以在不同层次之间传输数据
    解耦合：可以独立进行开发、测试和维护
    减少数据传输：将多个领域的数据封装成一个DTO对象，减少数据传输量，提高系统性能效率
 */
@Data
public class QueryDto implements Serializable {

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

    /**
     * 每页显示记录数，默认为10
     */
    @Schema(description = "每页显示记录数，默认为10")
    private Long size = 10L;

    /**
     * 当前页，默认为1
     */
    @Schema(description = "当前页，默认为1")
    private Long current = 1L;

    /**
     * 查询列表总记录数，默认为0
     */
    @Schema(description = "查询列表总记录数，默认为0")
    private	Long total = 0L;

    /**
     * 排序字段信息
     */
    @Schema(description = "排序字段信息")
    private List<OrderItem> orders;

    /**
     * 是否自动优化COUNT SQL，默认为true
     */
    @Schema(description = "是否自动优化COUNT SQL，默认为true")
    private boolean	optimizeCountSql = true;

    /**
     * 自动优化COUNT SQL是否把join查询部分移除，默认为true
     */
    @Schema(description = "自动优化COUNT SQL是否把join查询部分移除，默认为true")
    private boolean	optimizeJoinOfCountSql = true;

    /**
     * 是否进行count查询，默认为true
     */
    @Schema(description = "是否进行count查询，默认为true")
    private boolean	searchCount = true;

    /**
     * 单页分页条数限制
     */
    @Schema(description = "单页分页条数限制")
    private Long maxLimit;

    /**
     * XML自定义count查询的statementId
     */
    @Schema(description = "XML自定义count查询的statementId")
    private String countId;
}
