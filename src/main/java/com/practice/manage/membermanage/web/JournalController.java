package com.practice.manage.membermanage.web;

import com.practice.manage.membermanage.main.domain.Journal;
import com.practice.manage.membermanage.main.service.JournalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.practice.manage.membermanage.config.ApiDocConfig;
import com.practice.manage.membermanage.web.dto.JournalRequest;
import com.practice.manage.membermanage.web.dto.JournalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/journals")
@Tag(name = "업무일지", description = "업무일지 API")
@SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
public class JournalController {

    final JournalService journalService;

    @GetMapping
    @Operation(summary = "업무일지 리스트 조회")
    public ResponseEntity<List<JournalResponse>> getJournalList(
            @RequestParam(required = false, value = "reportId", defaultValue = "-1") Integer reportId,
            @RequestParam(required = false, value = "from", defaultValue = "-1") Integer from,
            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Journal> journalList = journalService.getJournalList(uid, reportId, from, size);
        List<JournalResponse> journalResponseList = JournalResponse.fromDomain(journalList);
        return ResponseEntity.ok().body(journalResponseList);
    }

    @GetMapping("/{journalId}")
    @Operation(summary = "업무일지 조회")
    public ResponseEntity<JournalResponse> getJournal(
            @PathVariable("journalId") Integer journalId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Journal journal = journalService.getJournal(uid, journalId);
        return ResponseEntity.ok().body(JournalResponse.fromDomain(journal));
    }

    @PostMapping
    @Operation(summary = "업무일지 등록")
    public void postJournal(
            @RequestBody JournalRequest journalRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Journal journal = JournalRequest.fromDto(journalRequest);
        journalService.postJournal(uid, journal);
    }

    @PutMapping("/{journalId}")
    @Operation(summary = "업무일지 수정")
    public void putJournal(
            @PathVariable("journalId") Integer journalId,
            @RequestBody JournalRequest journalRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Journal journal = JournalRequest.fromDto(journalRequest);
        journalService.putJournal(uid, journalId, journal);
    }

    @DeleteMapping("/{journalId}")
    @Operation(summary = "업무일지 삭제")
    public void deleteJournal(
            @PathVariable("journalId") Integer journalId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        journalService.deleteJournal(uid, journalId);
    }


}
