package com.hzyazilimci.blog.business.constants.messages;

public final class BusinessMessages {

    public static final class GlobalSuccessMessages{
        public static final String SUCCESS_LIST = "Listed Successfully.";
        public static final String SUCCESS_GET = "Getted Successfully.";
        public static final String SUCCESS_ADD = "Added Successfully.";
        public static final String SUCCESS_UPDATE = "Updated Successfully.";
        public static final String SUCCESS_DELETE = "Deleted Successfully.";
        public static final String SUCCESS_LIST_PAGED = "Paged Listed Successfully.";
        public static final String SUCCESS_LIST_SORTED = "Sorted Listed Successfully.";
    }

    public static final class PostExceptionMessages{
        public static final String POST_NOT_FOUND = "Post not found!";
        public static final String TITLE_ALREADY_EXIST = "This title has already exists! You can post that title.";
    }

    public static final class CommentExceptionMessages{
        public static final String COMMENT_NOT_FOUND = "Comment not found!";
        public static final String TITLE_ALREADY_EXIST = "This title has already exists! You can post that title.";
    }
}
