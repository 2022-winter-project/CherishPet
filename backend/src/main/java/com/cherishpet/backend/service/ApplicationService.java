package com.cherishpet.backend.service;

import com.cherishpet.backend.domain.Application;
import com.cherishpet.backend.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Application findOne(Long id){
        return applicationRepository.findOne(id);
    }

    public List<Application> findApplicationsByUsername(String username){
        return applicationRepository.findApplicationsByUsername(username);
    }
}
