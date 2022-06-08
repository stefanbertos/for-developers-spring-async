package com.example.async.service;

import com.example.async.repository.AsyncRepository;
import com.example.async.repository.entity.AsyncStatus;
import com.example.async.util.SecurityUtil;
import com.example.async.vo.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AsyncService {
    private AsyncRepository asyncRepository;

    private SubmitService submitService;

    @Autowired
    public AsyncService(AsyncRepository asyncRepository, SubmitService submitService) {
        this.asyncRepository = asyncRepository;
        this.submitService = submitService;
    }

    @Transactional
    public long submit() {
        AsyncStatus asyncStatus = new AsyncStatus();
        asyncStatus.setStartDate(LocalDateTime.now());
        asyncStatus.setUserId(SecurityUtil.getUser());
        asyncStatus.setStatus(Status.STARTED);
        asyncStatus = asyncRepository.save(asyncStatus);

        log.info("submit {}", asyncStatus.toString());
        log.info ("security user in submit method: {}", SecurityUtil.getUser());
        submitService.run(asyncStatus.getId());

        return asyncStatus.getId();
    }

    @Transactional(readOnly = true)
    public String getStatus(long id) {
        return asyncRepository.getReferenceById(id).toString();
    }
}
