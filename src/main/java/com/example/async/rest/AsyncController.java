package com.example.async.rest;

import com.example.async.repository.entity.AsyncStatus;
import com.example.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/async")
public class AsyncController {

    private AsyncService asyncService;

    @Autowired
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @PostMapping
    public long submit() {
        return asyncService.submit();
    }

    @GetMapping
    public AsyncStatus getStatus(long id) {
        return asyncService.getStatus(id);
    }

}
