/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense.controller;

import cn.zhuatech.expense.common.ApiResponse;
import cn.zhuatech.expense.service.ReimbursementReleaseGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/expense")
public class ReimbursementReleaseGovernanceController {
    private final ReimbursementReleaseGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ReimbursementReleaseGovernanceController(ReimbursementReleaseGovernanceService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/reimbursement-release")
    public ApiResponse<ReimbursementReleaseGovernanceService.Assessment> assess(
            @Valid @RequestBody ReimbursementReleaseGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
