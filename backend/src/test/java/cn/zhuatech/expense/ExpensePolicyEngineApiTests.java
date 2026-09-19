/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class ExpensePolicyEngineApiTests{@Autowired MockMvc mvc;static final String BODY="""
 {"claimNo":"EXP-1","employeeNo":"E-1","currency":"CNY","lines":[{"lineNo":"L1","category":"HOTEL","amount":800,"policyLimit":600,"receiptRequired":true,"receiptAttached":true,"fingerprint":"F-1","businessPurpose":"客户项目"},{"lineNo":"L2","category":"MEAL","amount":120,"policyLimit":150,"receiptRequired":true,"receiptAttached":false,"fingerprint":"F-2","businessPurpose":"出差"}]}
 """;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void appliesLimitsAndReceiptRules()throws Exception{mvc.perform(post("/api/advanced/expense/policy-check").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("REVIEW_REQUIRED")).andExpect(jsonPath("$.data.approvedTotal").value(600)).andExpect(jsonPath("$.data.blockedLines").value(1));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void requiresAuthentication()throws Exception{mvc.perform(post("/api/advanced/expense/policy-check").contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isUnauthorized());}}
