package com.example.userservice.feign;

import com.example.userservice.dto.ViewAccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "account-feign", url = "${account.service.uri}")
public interface AccountClient {

    @GetMapping("/by-email")
    List<ViewAccountDto> getAccountsByUserEmail(@RequestHeader("Authorization") String token);

}
