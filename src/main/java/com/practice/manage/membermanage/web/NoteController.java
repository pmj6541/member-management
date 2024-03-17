package com.practice.manage.membermanage.web;

import com.practice.manage.membermanage.main.domain.Note;
import com.practice.manage.membermanage.main.service.NoteService;
import com.practice.manage.membermanage.web.dto.NoteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.practice.manage.membermanage.config.ApiDocConfig;
import com.practice.manage.membermanage.web.dto.NoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/notes")
@Tag(name = "기타사항", description = "기타사항 API")
@SecurityRequirement(name = ApiDocConfig.SECURITY_SCHEME_BEARER)
public class NoteController {

    final NoteService noteService;

    @GetMapping
    @Operation(summary = "기타사항 리스트 조회")
    public ResponseEntity<List<NoteResponse>> getNoteList(
            @RequestParam(required = false, value = "reportId", defaultValue = "-1") Integer reportId,
            @RequestParam(required = false, value = "from", defaultValue = "1") Integer from,
            @RequestParam(required = false, value = "size", defaultValue = "20") Integer size,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        List<Note> noteList = noteService.getNoteList(uid, reportId, from, size);
        List<NoteResponse> noteResponseList = NoteResponse.fromDomain(noteList);
        return ResponseEntity.ok().body(noteResponseList);
    }

    @GetMapping("/{noteId}")
    @Operation(summary = "기타사항 조회")
    public ResponseEntity<NoteResponse> getNote(
            @PathVariable("noteId") Integer noteId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Note note = noteService.getNote(uid, noteId);
        return ResponseEntity.ok().body(NoteResponse.fromDomain(note));
    }

    @PostMapping
    @Operation(summary = "기타사항 등록")
    public void postNote(
            @RequestBody NoteRequest noteRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Note note = NoteRequest.fromDto(noteRequest);
        noteService.postNote(uid, note);
    }

    @PutMapping("/{noteId}")
    @Operation(summary = "기타사항 수정")
    public void putNote(
            @PathVariable("noteId") Integer noteId,
            @RequestBody NoteRequest noteRequest,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        Note note = NoteRequest.fromDto(noteRequest);
        noteService.putNote(uid, noteId, note);
    }

    @DeleteMapping("/{noteId}")
    @Operation(summary = "기타사항 삭제")
    public void deleteNote(
            @PathVariable("noteId") Integer noteId,
            @AuthenticationPrincipal @Parameter(hidden = true) Jwt jwt
    ) {
        final String uid = jwt.getClaim("uid");
        noteService.deleteNote(uid, noteId);
    }


}
