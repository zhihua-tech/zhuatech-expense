/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense.controller;

import cn.zhuatech.expense.common.ApiResponse;
import cn.zhuatech.expense.service.CorporateCardReconciliationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/expense")
public class CorporateCardReconciliationController {
    private final CorporateCardReconciliationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public CorporateCardReconciliationController(CorporateCardReconciliationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/corporate-card-reconciliation")
    public ApiResponse<CorporateCardReconciliationService.Assessment> assess(
            @Valid @RequestBody CorporateCardReconciliationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
