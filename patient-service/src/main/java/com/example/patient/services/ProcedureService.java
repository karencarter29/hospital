package com.example.patient.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="feignProcedureService", url="http://10.186.0.4:8083/procedure/procedures")
public interface ProcedureService {
    @GetMapping
    public List<String> getProcedures();
}
