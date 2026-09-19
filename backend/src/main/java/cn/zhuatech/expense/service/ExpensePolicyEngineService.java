/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expense.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
@Service public class ExpensePolicyEngineService{
 public PolicyResult evaluate(@Valid PolicyRequest request){Set<String> fingerprints=new HashSet<>();List<LineResult> results=new ArrayList<>();BigDecimal claimed=BigDecimal.ZERO,approved=BigDecimal.ZERO;int blocked=0;
  for(ExpenseLine line:request.lines()){claimed=claimed.add(line.amount());List<String> reasons=new ArrayList<>();BigDecimal allowed=line.amount();String status="APPROVED";
   if(line.receiptRequired()&&!line.receiptAttached()){allowed=BigDecimal.ZERO;status="BLOCKED";reasons.add("缺少必需票据");}
   if(line.fingerprint()!=null&&!line.fingerprint().isBlank()&&!fingerprints.add(line.fingerprint())){allowed=BigDecimal.ZERO;status="BLOCKED";reasons.add("检测到重复票据指纹");}
   if(allowed.compareTo(line.policyLimit())>0){reasons.add("超过政策限额，超额部分不予报销");allowed=line.policyLimit();status="ADJUSTED";}
   if(line.businessPurpose()==null||line.businessPurpose().isBlank()){allowed=BigDecimal.ZERO;status="BLOCKED";reasons.add("缺少业务用途");}
   if("BLOCKED".equals(status))blocked++;approved=approved.add(allowed);results.add(new LineResult(line.lineNo(),status,line.amount(),allowed,line.amount().subtract(allowed),reasons));}
  claimed=claimed.setScale(2,RoundingMode.HALF_UP);approved=approved.setScale(2,RoundingMode.HALF_UP);return new PolicyResult(blocked>0?"REVIEW_REQUIRED":approved.compareTo(claimed)<0?"ADJUSTED":"APPROVED",claimed,approved,claimed.subtract(approved),blocked,results);
 }
 public record PolicyRequest(@NotBlank String claimNo,@NotBlank String employeeNo,@NotBlank String currency,@NotEmpty List<@Valid ExpenseLine> lines){}
 public record ExpenseLine(@NotBlank String lineNo,@NotBlank String category,@NotNull @DecimalMin("0.01") BigDecimal amount,@NotNull @DecimalMin("0") BigDecimal policyLimit,boolean receiptRequired,boolean receiptAttached,String fingerprint,String businessPurpose){}
 public record LineResult(String lineNo,String status,BigDecimal claimedAmount,BigDecimal approvedAmount,BigDecimal deduction,List<String> reasons){}
 public record PolicyResult(String decision,BigDecimal claimedTotal,BigDecimal approvedTotal,BigDecimal deductionTotal,int blockedLines,List<LineResult> lines){}
}
