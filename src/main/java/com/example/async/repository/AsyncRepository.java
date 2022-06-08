package com.example.async.repository;

import com.example.async.repository.entity.AsyncStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsyncRepository extends JpaRepository<AsyncStatus, Long> {
}
