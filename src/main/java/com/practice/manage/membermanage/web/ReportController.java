package com.practice.manage.membermanage.web;

import com.practice.manage.membermanage.main.domain.Report;
import com.practice.manage.membermanage.main.service.ReportService;
import com.practice.manage.membermanage.web.dto.ReportRequest;
import com.practice.manage.membermanage.web.dto.ReportResponse;
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
@RequestMapping("/reports")
@Tag(name = "보고서", description = "보고서 API")
@SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
public class ReportController {
    final ReportService reportService;

    @GetMapping
    @Operation(summary = "보고서 리스트 조회")
    public ResponseEntity<List<ReportResponse>> getReportList(
            @RequestParam(required = false, value = "from", defaultValue = "-1") Integer from,
            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Report> reportResponseList = reportService.getReportList(uid, from, size);
        return ResponseEntity.ok().body(ReportResponse.fromDomain(reportResponseList));
    }

    @GetMapping("/months")
    @Operation(summary = "보고서 월별 리스트 조회")
    public ResponseEntity<List<ReportResponse>> getReportListByMonth(
            @RequestParam String month,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Report> reportResponseList = reportService.getReportListByMonth(uid, month);
        return ResponseEntity.ok().body(ReportResponse.fromDomain(reportResponseList));
    }

    @GetMapping("/{reportId}")
    @Operation(summary = "보고서 조회")
    public ResponseEntity<ReportResponse> getReportById(
            @PathVariable("reportId") Integer reportId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Report report = reportService.getReport(uid, reportId);
        return ResponseEntity.ok().body(ReportResponse.fromDomain(report));
    }

    @PostMapping
    @Operation(summary = "보고서 생성")
    public ResponseEntity<Integer> postReport(
            @RequestBody ReportRequest reportRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Integer reportId = reportService.postReport(uid, reportRequest.getDate());
        return ResponseEntity.ok(reportId);
    }

    @PutMapping("/{reportId}/submit")
    @Operation(summary = "보고서 제출")
    public void submitReport(
            @PathVariable("reportId") Integer reportId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        reportService.submitReport(uid, reportId);
    }

    @DeleteMapping("/{reportId}")
    @Operation(summary = "보고서 삭제")
    public void deleteReport(
            @PathVariable("reportId") Integer reportId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        reportService.deleteReport(uid, reportId);
    }


}
