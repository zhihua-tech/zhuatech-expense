/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense.controller;import cn.zhuatech.expense.common.ApiResponse;import cn.zhuatech.expense.service.ExpensePolicyEngineService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/advanced/expense") public class ExpensePolicyEngineController{private final ExpensePolicyEngineService service;/**
                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                      */
public ExpensePolicyEngineController(ExpensePolicyEngineService service){this.service=service;}/**
                                                                                                                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                     */
@PostMapping("/policy-check") public ApiResponse<ExpensePolicyEngineService.PolicyResult> check(@Valid @RequestBody ExpensePolicyEngineService.PolicyRequest request){return ApiResponse.ok(service.evaluate(request));}}
