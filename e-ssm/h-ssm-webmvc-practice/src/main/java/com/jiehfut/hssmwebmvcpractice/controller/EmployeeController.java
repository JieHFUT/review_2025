package com.jiehfut.hssmwebmvcpractice.controller;


import com.jiehfut.hssmwebmvcpractice.bean.Employee;
import com.jiehfut.hssmwebmvcpractice.common.R;
import com.jiehfut.hssmwebmvcpractice.exception.BizExceptionEnume;
import com.jiehfut.hssmwebmvcpractice.service.EmployeeService;
import com.jiehfut.hssmwebmvcpractice.vo.request.EmployeeAddVo;
import com.jiehfut.hssmwebmvcpractice.vo.request.EmployeeUpdateVo;
import com.jiehfut.hssmwebmvcpractice.vo.response.EmployeeQueryAllVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CORS policy：同源策略（限制ajax请求，图片，css，js）； 跨域问题
 * 跨源资源共享（CORS）（Cross-Origin Resource Sharing）
 *    浏览器为了安全，默认会遵循同源策略（请求要去的服务器和当前项目所在的服务器必须是同一个源[同一个服务器]），如果不是，请求就会被拦截
 *    复杂的跨域请求会发送2次：
 *    1、options 请求：预检请求。浏览器会先发送options请求，询问服务器是否允许当前域名进行跨域访问
 *    2、真正的请求：POST、DELETE、PUT等
 *
 *
 * 浏览器页面所在的：http://localhost   /employee/base
 * 页面上要发去的请求：http://localhost:8080   /api/v1/employees
 *  /以前的东西，必须完全一样，一个字母不一样都不行。浏览器才能把请求（ajax）发出去。
 *
 *  跨域问题：
 *    1、前端自己解决：
 *    2、后端解决：允许前端跨域即可（一个注解即可）
 *          原理：服务器给浏览器的响应头中添加字段：Access-Control-Allow-Origin = *
 *
 *
 */

@Tag(name = "", description = "员工管理控制器") // 描述接口文档中该类的作用
@CrossOrigin  //允许跨域
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    /**
     * "/employee/id" --- get ------ 查询某个员工信息 ------------ Employee-json
     * "/employee/id" --- delete --- 删除某个员工信息 ------------ boolean
     * "/employee" ------ get ------ 查询全部员工信息 ------------ List<Employee-json>
     * "/employee" ------ post ----- 新增某个员工信息 ------------ boolean
     * "/employee" ------ put ------ 修改某个员工信息 ------------ boolean
     * "employee/page" -- get ------ 查询所有员工信息（加分页） ---- pageList<Employee-json>
     *
     * GetMapping
     * PutMapping
     * DeleteMapping
     * PostMapping
     * PatchMapping
     *
     * 1.请求中有一个 id 属性
     * @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
     * 2.加一个星号代表可以有多层路径，如 *id = /a/b/c/3/id  接收使用 String id
     * @RequestMapping(value = "/employee/{*id}", method = RequestMethod.GET)
     * 3.使用正则表达式，可以有多个表达式
     * @RequestMapping(value = "/employee/{id:\\d+}.test", method = RequestMethod.GET)
     */

    @Autowired
    private EmployeeService employeeService;


    /**
     * 新增员工信息
     * 前端发送请求，将员工信息以 json 字符串传递过来
     * 使用 @Valid 进行数据校验 - 校验失败业务就不会执行了 - 直接响应异常
     *
     * 要求：校验出错，在控制层方法体里响应错误信息（code，msg，data）
     *      使用 BindingResult 接收校验出错的信息（封装校验结果）- 进行判断
     *
     * 最终的处理方式是利用全局异常处理捕捉 MethodArgumentNotValidException（参数校验失败异常） 统一返回
     * @param employee
     * @return
     */

    @Operation(summary = "请求携带 Employee employee 添加员工记录")
    @PostMapping("/employee")
    public R<Boolean> create(@RequestBody @Valid  Employee employee, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            int i = employeeService.addEmployee(employee);
            R r = new R<>(200, "ok", true);
            return i == 1 ? r : new R<Boolean>(i, "error", false);
        }
        // 说明校验错误信息，拿到所有错误信息
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // 1.拿到出错的属性名
            String fieldName = fieldError.getField();
            // 2.拿到默认出错消息
            String errorMessage = fieldError.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        }
        return R.error(BizExceptionEnume.EMPLOYEE_DATA_VALID, errorMap);
    }

    /**
     * 新增员工信息 - 最终的数据校验处理方式
     * 校验不匹配由全局异常直接捕捉，不会执行该方法，直接由全局异常响应
     * @param employee
     * @return
     */
    @Operation(summary = "请求携带 Employee employee 添加员工记录2")
    @PostMapping("/employee2")
    public R<Boolean> create2(@RequestBody @Valid  Employee employee) {
        int i = employeeService.addEmployee(employee);
        R r = new R<>(200, "ok", true);
        return i == 1 ? r : new R<Boolean>(i, "error", false);
    }

    @Operation(summary = "请求携带 Employee employee 添加员工记录3")
    @PostMapping("/employee3")
    public R<Boolean> create3(@RequestBody @Valid EmployeeAddVo employeeAddVo) {
        /**
         * 1.通过数据校验
         * 2.spring 提供了一个 BeanUtils 工具用于处理 pojo 和 VO 对象的转换
         *   进行属性对拷
         */
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeAddVo, employee);

        int i = employeeService.addEmployee(employee);
        R r = new R<>(200, "ok", true);
        return i == 1 ? r : new R<Boolean>(i, "error", false);
    }





    /**
     * 查询某个员工信息
     * "/employee/id" get
     * @param id
     * @return
     */
    @Operation(summary = "根据 ID 查看某个员工信息")
    @Parameters({
            // 用来在接口文档中描述属性信息的（参数是路径上的，且是必须的）
            @Parameter(name = "id", description = "员工ID", in = ParameterIn.PATH, required = true)
    })
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee get(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    /**
     * 删除某个员工信息
     * @param id
     * @return
     */
    @Operation(summary = "根据 ID 删除某个员工信息")
    @DeleteMapping("/employee/{id}")
    public R<Boolean> delete(@PathVariable("id") Long id) {
        System.out.println("根据用户 ID 查询用户信息控制器已经执行...");
        int i = employeeService.deleteEmployee(id);
        R r = new R<>(200, "ok", true);
        return i == 1 ? r : new R<Boolean>(i, "error", false);
    }

    /**
     * 修改某个员工信息
     * 分组校验（使用 @Validated 注解完成）
     * @param employee
     * @return
     */
    @Operation(summary = "修改某个员工信息")
    @PutMapping("/employee")
    public R<Boolean> update(@RequestBody @Validated Employee employee) {
        int i = employeeService.updateEmployee(employee);
        R r = new R<>(200, "ok", true);
        return i == 1 ? r : new R<Boolean>(i, "error", false);
    }

    @PutMapping("/employee2")
    @Operation(summary = "更新某个员工信息")
    public R<Boolean> update2(@RequestBody @Validated EmployeeUpdateVo employeeUpdateVo) {
        // 进行属性对拷
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeUpdateVo, employee);

        int i = employeeService.updateEmployee(employee);
        R r = new R<>(200, "ok", true);
        return i == 1 ? r : new R<Boolean>(i, "error", false);
    }




    /**
     * 查询全部员工信息
     * 不响应 age 信息，使用 EmployeeQueryAllVo 对象集合进行响应数据
     * @return
     */
    @GetMapping("/employees")
    @Operation(summary = "查询所有员工信息")
    public R<List<EmployeeQueryAllVo>> getAll() {
        System.out.println("获取所有的用户信息...");
        List<Employee> employees = employeeService.getAllEmployee();

        // 查询的员工列表转成 EmployeeQueryAllVo 列表，然后响应给前端
        List<EmployeeQueryAllVo> employeeQueryAllVos = new ArrayList<>();

        for (Employee employee : employees) {
            // 对拷
            EmployeeQueryAllVo employeeQueryAllVo = new EmployeeQueryAllVo();
            BeanUtils.copyProperties(employee, employeeQueryAllVo);
            employeeQueryAllVos.add(employeeQueryAllVo);
        }

        return new R<>(200, "ok", employeeQueryAllVos);
    }

    /**
     * 使用流进行数据的类型转换
     * @return
     */
    @GetMapping("/employees2")
    @Operation(summary = "查询全部员工信息2")
    public R<List<EmployeeQueryAllVo>> getAll2() {
        System.out.println("获取所有的用户信息...");
        List<Employee> employees = employeeService.getAllEmployee();

        // 查询的员工列表转成 EmployeeQueryAllVo 列表，然后响应给前端
        List<EmployeeQueryAllVo> collect = employees.stream()
                .map(employee -> {
                    EmployeeQueryAllVo employeeQueryAllVo = new EmployeeQueryAllVo();
                    BeanUtils.copyProperties(employee, employeeQueryAllVo);
                    return employeeQueryAllVo;
                }).collect(Collectors.toList());

        return new R<>(200, "ok", collect);
    }

}
