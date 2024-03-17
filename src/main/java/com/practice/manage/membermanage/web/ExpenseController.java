package com.practice.manage.membermanage.web;

import com.practice.manage.membermanage.main.domain.Expense;
import com.practice.manage.membermanage.main.service.ExpenseService;
import com.practice.manage.membermanage.web.dto.ExpenseRequest;
import com.practice.manage.membermanage.web.dto.ExpenseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.practice.manage.membermanage.config.ApiDocConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/expenses")
@Tag(name = "법인카드", description = "법인카드 API")
@SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
public class ExpenseController {

    final ExpenseService expenseService;

    @GetMapping
    @Operation(summary = "법인카드 리스트 조회")
    public ResponseEntity<List<ExpenseResponse>> getExpenseList(
            @RequestParam(required = false, value = "reportId", defaultValue = "-1") Integer reportId,
            @RequestParam(required = false, value = "from", defaultValue = "1") Integer from,
            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Expense> expenseList = expenseService.getExpenseList(uid, reportId, from, size);
        List<ExpenseResponse> expenseResponseList = ExpenseResponse.fromDomain(expenseList);
        return ResponseEntity.ok().body(expenseResponseList);
    }

    @GetMapping("/{expenseId}")
    @Operation(summary = "법인카드 조회")
    public ResponseEntity<ExpenseResponse> getExpense(
            @PathVariable("expenseId") Integer expenseId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Expense expense = expenseService.getExpense(uid, expenseId);
        return ResponseEntity.ok().body(ExpenseResponse.fromDomain(expense));
    }

    @PostMapping
    @Operation(summary = "법인카드 등록")
    public void postExpense(
            @RequestBody ExpenseRequest expenseRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Expense expense = ExpenseRequest.fromDto(expenseRequest);
        expenseService.postExpense(uid, expense);
    }

    @PutMapping("/{expenseId}")
    @Operation(summary = "법인카드 수정")
    public void putExpense(
            @PathVariable("expenseId") Integer expenseId,
            @RequestBody ExpenseRequest expenseRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Expense expense = ExpenseRequest.fromDto(expenseRequest);
        expenseService.putExpense(uid, expenseId, expense);
    }

    @DeleteMapping("/{expenseId}")
    @Operation(summary = "법인카드 삭제")
    public void deleteExpense(
            @PathVariable("expenseId") Integer expenseId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        expenseService.deleteExpense(uid, expenseId);
    }


}
