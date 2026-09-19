/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense.controller;

import cn.zhuatech.expense.common.ApiResponse;
import cn.zhuatech.expense.service.ExpenseComplianceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin/expense-compliance")
public class ExpenseComplianceController {
    private final ExpenseComplianceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ExpenseComplianceController(ExpenseComplianceService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping
    ApiResponse<ExpenseComplianceService.ComplianceResult> assess(
        @Valid @RequestBody ExpenseComplianceService.ComplianceRequest request) {
        return ApiResponse.ok(service.assess(request));
    }
}
