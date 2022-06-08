package com.example.async.service;

import com.example.async.repository.AsyncRepository;
import com.example.async.repository.entity.AsyncStatus;
import com.example.async.util.SecurityUtil;
import com.example.async.vo.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SubmitService {

    private AsyncRepository asyncRepository;

    @Autowired
    public SubmitService(AsyncRepository asyncRepository) {
        this.asyncRepository = asyncRepository;
    }

    @Async
    public void run(long id) {
        try {
            Thread.sleep(5000);
            log.info ("security user: {}", SecurityUtil.getUser());

            AsyncStatus asyncStatus = asyncRepository.getReferenceById(id);
            asyncStatus.setStatus(Status.FINISHED);
            asyncStatus.setEndDate(LocalDateTime.now());
            asyncStatus = asyncRepository.save(asyncStatus);

            log.info("run {}", asyncStatus.toString());

        } catch (InterruptedException e) {
            //
        }
    }
}
