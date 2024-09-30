package org.example.lecturecleanarchitecture.infrastructure.web

import org.example.lecturecleanarchitecture.application.LectureRegisterApplicationService
import org.example.lecturecleanarchitecture.common.ApiResponse
import org.example.lecturecleanarchitecture.common.SuccessType
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/lectures")
class LectureController(
    private val lectureRegisterApplicationService: LectureRegisterApplicationService,
) {
    @PatchMapping("/{id}/register")
    fun register(
        @PathVariable id: Long,
        @RequestBody request: RegisterLectureRequest,
    ): ResponseEntity<ApiResponse<Unit>> {
        lectureRegisterApplicationService.register(id, request)

        return ResponseEntity.ok(
            ApiResponse.success(SuccessType.LECTURE_REGISTERED),
        )
    }
}
