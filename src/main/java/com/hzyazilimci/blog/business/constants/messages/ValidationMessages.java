package com.hzyazilimci.blog.business.constants.messages;

public final class ValidationMessages {

    public static final class GeneralValidationMessages {
        public static final String ID_CANNOT_LESS_THEN_ONE="Id cannot less than or equal zero.";
    }

    public static final class PostValidationMessages {
        public static final String POST_TITLE_VALIDATION="Title size has to be 5-50. And it can contains only (,!.?) special chars.";
        public static final String POST_DESCRIPTION_VALIDATION="Description size has to be 5-50. And it can contains only (,!.?) special chars.";
        public static final String POST_CONTENT_VALIDATION="Content size has to be greater than 20 and lower than 5000 chars.";
    }

    public static final class CommentValidationMessages {
        public static final String NAME_VALIDATION="Name size has to be 4-50. And name cannot contains special characters.";
        public static final String COMMENT_BODY_VALIDATION="Comment body size has to be greater than 5 and lower than 10000 chars.";
    }
}
