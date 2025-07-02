package com.example.userservice.services;

import com.example.userservice.dto.PresentUser;
import com.example.userservice.dto.RegistrationUserDto;
import com.example.userservice.dto.UpdateUserDto;
import com.example.userservice.dto.ViewAccountDto;
import com.example.userservice.feign.AccountClient;
import com.example.userservice.models.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountClient accountFeignClient;
    private final JwtService jwtService;
    @Transactional
    @Override
    public User registrationUser(RegistrationUserDto registrationUserDto) {
        User user = userMapper.toEntity(registrationUserDto);
        user.setPassword(passwordEncoder.encode(registrationUserDto.password()));
        return userRepository.save(user);
    }


    @Override
    public User getUserById(UUID id) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return userRepository.getUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " doesn't exists")
        );
    }

    @Transactional
    @Override
    public User updateUser(UUID id, UpdateUserDto updateUserDto) {
        User user = getUserById(id);
        user.setUsername(updateUserDto.username());
        user.setEmail(updateUserDto.email());
        return userRepository.save(user);
    }

    @Override
    public PresentUser presentUser(String token) {
        String email = jwtService.extractUserEmail(token);
        List<ViewAccountDto> accounts= accountFeignClient.getAccountsByUserEmail(token);
        return new PresentUser(email, accounts);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
