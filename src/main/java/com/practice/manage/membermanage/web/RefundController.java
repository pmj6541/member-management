package com.practice.manage.membermanage.web;

import com.practice.manage.membermanage.main.domain.Refund;
import com.practice.manage.membermanage.main.service.RefundService;
import com.practice.manage.membermanage.web.dto.RefundResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.practice.manage.membermanage.config.ApiDocConfig;
import com.practice.manage.membermanage.web.dto.RefundRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/refunds")
@Tag(name = "반품내역", description = "반품내역 API")
@SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
public class RefundController {

    final RefundService refundService;

    @GetMapping
    @Operation(summary = "반품내역 리스트 조회")
    public ResponseEntity<List<RefundResponse>> getRefundList(
            @RequestParam(required = false, value = "reportId", defaultValue = "-1") Integer reportId,
            @RequestParam(required = false, value = "from", defaultValue = "1") Integer from,
            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Refund> refundList = refundService.getRefundList(uid, reportId, from, size);
        List<RefundResponse> RefundResponseList = RefundResponse.fromDomain(refundList);
        return ResponseEntity.ok().body(RefundResponseList);
    }

    @GetMapping("/{refundId}")
    @Operation(summary = "반품내역 조회")
    public ResponseEntity<RefundResponse> getRefund(
            @PathVariable("refundId") Integer refundId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Refund refund = refundService.getRefund(uid, refundId);
        return ResponseEntity.ok().body(RefundResponse.fromDomain(refund));
    }

    @PostMapping
    @Operation(summary = "반품내역 등록")
    public void postRefund(
            @RequestBody RefundRequest refundRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Refund refund = RefundRequest.fromDto(refundRequest);
        refundService.postRefund(uid, refund);
    }

    @PutMapping("/{refundId}")
    @Operation(summary = "반품내역 수정")
    public void putRefund(
            @PathVariable("refundId") Integer refundId,
            @RequestBody RefundRequest refundRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Refund Refund = RefundRequest.fromDto(refundRequest);
        refundService.putRefund(uid, refundId, Refund);
    }

    @DeleteMapping("/{refundId}")
    @Operation(summary = "반품내역 삭제")
    public void deleteRefund(
            @PathVariable("refundId") Integer refundId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        refundService.deleteRefund(uid, refundId);
    }


}
