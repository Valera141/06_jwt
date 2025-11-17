package itst.example.mapper;

import java.util.Iterator;
import java.util.Set;

import itst.example.dto.UserLoginRequest;
import itst.example.dto.UserLoginResponse;
import itst.example.model.Role;
import itst.example.model.User;

public final class UserLoginMapper {

    public static UserLoginResponse toResponse(User user) {
        if (user == null) return null;

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }

        return UserLoginResponse.builder()
                .email(user.getEmail())
                .role(firstElement.getAuthority())
                .build();
    }

    public static User toEntity(UserLoginRequest dto) {
        if (dto == null) return null;

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UserLoginRequest dto, User entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
