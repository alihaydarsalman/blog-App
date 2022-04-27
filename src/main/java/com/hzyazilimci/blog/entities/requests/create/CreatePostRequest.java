package com.hzyazilimci.blog.entities.requests.create;

import com.hzyazilimci.blog.business.constants.messages.ValidationMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqxyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQXYZ.!?,_*:;'-/0123456789 ]{5,50}",
            message = ValidationMessages.PostValidationMessages.POST_TITLE_VALIDATION)
    private String title;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqxyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQXYZ.!?,;:'_*-/0123456789 ]{5,50}",
            message = ValidationMessages.PostValidationMessages.POST_DESCRIPTION_VALIDATION)
    private String description;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqxyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQXYZ.!?:;',_*-/0123456789 ]{20,5000}",
            message = ValidationMessages.PostValidationMessages.POST_CONTENT_VALIDATION)
    private String content;
}
