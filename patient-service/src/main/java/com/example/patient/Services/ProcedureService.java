package com.example.patient.Services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="feignProcedureService", url="http://localhost:8083/procedure/procedures")
public interface ProcedureService {
    @GetMapping
    public List<String> getProcedures();
}
