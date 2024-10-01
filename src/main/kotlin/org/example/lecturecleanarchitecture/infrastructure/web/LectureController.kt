package org.example.lecturecleanarchitecture.infrastructure.web

import org.example.lecturecleanarchitecture.application.LectureQueryService
import org.example.lecturecleanarchitecture.application.LectureRegisterApplicationService
import org.example.lecturecleanarchitecture.common.ApiResponse
import org.example.lecturecleanarchitecture.common.SuccessType
import org.example.lecturecleanarchitecture.infrastructure.web.request.RegisterLectureRequest
import org.example.lecturecleanarchitecture.infrastructure.web.response.LectureResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/lectures")
class LectureController(
    private val lectureRegisterApplicationService: LectureRegisterApplicationService,
    private val lectureQueryService: LectureQueryService,
) {
    @PatchMapping("/{id}/register")
    fun register(
        @PathVariable id: Long,
        @RequestBody request: RegisterLectureRequest,
    ): ResponseEntity<ApiResponse<Unit>> {
        lectureRegisterApplicationService.enroll(id, request)

        return ResponseEntity.ok(
            ApiResponse.success(SuccessType.LECTURE_REGISTERED),
        )
    }

    @GetMapping
    fun findAll(
        @RequestParam date: LocalDate,
    ): ResponseEntity<ApiResponse<List<LectureResponse>>> {
        val response = lectureQueryService.findByDate(date)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ApiResponse.success(SuccessType.LECTURE_QUERY_LIST, response))
    }
}
