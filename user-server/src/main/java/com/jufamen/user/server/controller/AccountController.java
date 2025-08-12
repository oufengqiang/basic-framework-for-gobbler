package com.jufamen.user.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jufamen.gobbler.common.check.Add;
import com.jufamen.gobbler.common.check.Delete;
import com.jufamen.gobbler.common.check.Edit;
import com.jufamen.gobbler.common.controller.BaseController;
import com.jufamen.gobbler.common.response.Result;
import com.jufamen.user.server.entity.bo.AccountBo;
import com.jufamen.user.server.entity.dto.QueryAccountDto;
import com.jufamen.user.server.entity.pojo.Account;
import com.jufamen.user.server.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
    @RestController是Spring 4.0之后新增的注解，是@Controller和@ResponseBody的组合注解，
    因此无需每个方法都添加@ResponseBody，用于简化RESTful Web服务的开发。
    作用是声明一个类是控制器组件，并将方法的返回值直接序列化为JSON或XML，响应给客户端，不再经过视图解析器。
    它包含五个注解，分别是
        @Target({ElementType.TYPE})
            Java元注解，用于指定注解的作用目标，可以是类、方法、字段等
                ElementType.TYPE 注解可以应用到类、接口、枚举上
                ElementType.FIELD 注解可以应用到字段（成员变量）上
                ElementType.METHOD 注解可以应用到方法上
                ElementType.CONSTRUCTOR 注解可以应用到构造方法上
                ElementType.PARAMETER 注解可以应用到方法的参数上。
                ElementType.LOCAL_VARIABLE 注解可以应用到局部变量上。
                ElementType.ANNOTATION_TYPE 注解可以应用到其他注解上。
                ElementType.PACKAGE 注解可以应用到包上。
                ...
        @Retention(RetentionPolicy.RUNTIME)
            Java元注解，用于指定注解的保留期限，即注解在编译期、类加载期、运行期的生命周期
                RetentionPolicy.SOURCE 注解仅保留在源码中
                RetentionPolicy.CLASS 注解保留在字节码中，但是反射机制不能调用
                RetentionPolicy.RUNTIME 注解保留在字节码中，并且可以被反射机制所使用
        @Documented
            Java元注解，用于指定注解是否包含在JavaDoc文档中
        @Controller
            用于创建返回视图的Web控制器，并由视图解析器进一步解析
        @ResponseBody
            是后端的"数据转换器"，它能把Java对象自动转换成JSON/XML格式返回给前端
 */
@RestController
/*
    @RequestMapping用于建立URL到控制器处理方法映射，可以应用于类或方法上，提供请求的类型、路径等信息，是Spring MVC中实现请求映射的核心。
    它包含五个注解，分别是
        @Target({ElementType.TYPE, ElementType.METHOD})
            Java元注解，用于指定注解的作用目标，可以是类、方法、字段等
                ElementType.TYPE 注解可以应用到类、接口、枚举上
                ElementType.FIELD 注解可以应用到字段（成员变量）上
                ElementType.METHOD 注解可以应用到方法上
                ElementType.CONSTRUCTOR 注解可以应用到构造方法上
                ElementType.PARAMETER 注解可以应用到方法的参数上。
                ElementType.LOCAL_VARIABLE 注解可以应用到局部变量上。
                ElementType.ANNOTATION_TYPE 注解可以应用到其他注解上。
                ElementType.PACKAGE 注解可以应用到包上。
        @Retention(RetentionPolicy.RUNTIME)
            Java元注解，用于指定注解的保留期限，即注解在编译期、类加载期、运行期的生命周期
                RetentionPolicy.SOURCE 注解仅保留在源码中
                RetentionPolicy.CLASS 注解保留在字节码中，但是反射机制不能调用
                RetentionPolicy.RUNTIME 注解保留在字节码中，并且可以被反射机制所使用
        @Documented
            Java元注解，用于指定注解是否包含在JavaDoc文档中
        @Mapping
            是MapStruct框架中用于对象映射的核心注解，主要用于实体间的自动转换（如数据库实体转为页面对象、Model转为DTO等），支持自定义字段映射策略并处理特殊转换场景
        @Reflective({ControllerMappingReflectiveProcessor.class})
            用于指示元素需要反射处理，它会触发配置处理器并默认注册提示，支持自动装载和元注解应用
 */
@RequestMapping("/account")
@Slf4j
/*
   @RequiredArgsConstructor是Lombok框架提供的一个注解，用于简化依赖注入代码的编写。
   通过自动生成构造函数来替代手动添加@Autowired注解，主要用于Spring框架中减少重复的依赖注入代码。
 */
@RequiredArgsConstructor
public class AccountController extends BaseController {

    private final AccountService accountService;

    /**
     * 分页查询账号信息
     * @param queryAccountDto 分页查询账号参数
     * @return 需要返回接口响应结果
     */
    @GetMapping
    public Result<Object> pageList(QueryAccountDto queryAccountDto){
        IPage<AccountBo> page = this.buildPage(queryAccountDto);
        return accountService.pageList(page,queryAccountDto);
    }

    @GetMapping("{id}")
    public Result<Object> view(@PathVariable("id") Integer id){
        return accountService.view(id);
    }

    /**
     * 添加账号
     * @param account 添加的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @PostMapping
    public Result<Object> add(@Validated(Add.class) Account account){
        return accountService.add(account);
    }

    /**
     * 编辑账号
     * @param account 编辑的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @PutMapping
    public Result<Object> edit(@Validated(Edit.class) Account account){
        return accountService.edit(account);
    }

    /**
     * 删除账号
     * @param account 删除的账号信息实体对象
     * @return 需要返回接口响应结果
     */
    @DeleteMapping
    public Result<Object> delete(@Validated(Delete.class) Account account){
        return accountService.delete(account);
    }
}
