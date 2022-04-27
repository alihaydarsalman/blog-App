package com.hzyazilimci.blog.entities.requests.userRequests;

import com.hzyazilimci.blog.business.constants.messages.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull
    @Min(value = 1, message = ValidationMessages.GeneralValidationMessages.ID_CANNOT_LESS_THEN_ONE)
    private int id;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQYZ ]{4,50}",
            message = ValidationMessages.CommentValidationMessages.NAME_VALIDATION)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQYZ ]{4,50}",
            message = ValidationMessages.CommentValidationMessages.NAME_VALIDATION)
    private String lastname;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQYZ_0123456789 ]{4,50}",
            message = ValidationMessages.CommentValidationMessages.NAME_VALIDATION)
    private String username;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>.]).{8,20}$",
            message = ValidationMessages.GeneralValidationMessages.PASSWORD_VALIDATION_ERROR)
    private String password;
}
