package com.example.todo.model.http

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TodoDto(
    var index:Int? =null,

    @field:NotBlank
    var title:String?=null,
    var description:String?=null,

    @field:NotBlank
    // yyyy-MM-dd HH:mm:ss
    var schedule: String?=null,
    var createdAt:LocalDateTime?=null,
    var updatedAt:LocalDateTime?=null
){
    // TODO 이전에 학습했던 custom annotaion으로 변경
    @AssertTrue(message = "yyyy-MM-dd HH:mm:ss 포맷이 맞지 않습니다.")
    fun validSchedule() : Boolean{
        return try {
            LocalDateTime.parse(schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch(e: Exception){
            false
        }
    }
}