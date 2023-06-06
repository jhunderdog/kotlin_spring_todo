package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import com.example.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/todo")
class TodoApiController(
    val todoService: TodoService
) {
    //R
    @GetMapping(path = [""])
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any> {
        return index?.let {
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it)
        }?: kotlin.run {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/api/todo/all").build()
        }

    }

    @GetMapping(path = ["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    @PostMapping(path= [""])
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }


    //C
    @PutMapping(path= [""])
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index:Int): ResponseEntity<Any> {
        if (!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }

    //U
    //D
}