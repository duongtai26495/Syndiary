package com.duongtai.sydiary.configs;

public class Snippets {
    //user
    public static String FOUNDED = "FOUNDED";
    public static String USER_NOT_FOUND = "User not found";
    public static String USER_FOUND = "User found";
    public static String YOU_DONT_HAVE_PERMISSION = "You don't have permission!";
    public static String SUCCESS = "SUCCESS";
    public static String FAILED = "FAILED";
    public static String EMAIL_ALREADY_TAKEN = "This email already taken!";
    public static String USERNAME_ALREADY_TAKEN = "This username already taken!";
    public static String USER_LOGGED_IN = "User logged in";
    public static String USER_DO_NOT_LOGIN = "User do not login";
    public static String USER_CREATE_SUCCESSFULLY = "User create successfully";
    public static String USER_EDITED = "User edited";
    public static String PASSWORD_UPDATED = "Your password updated";
    public static String UPLOAD_PROFILE_IMAGE_SUCCESS = "Upload profile image success";
    public static String UPLOAD_IMAGE_SUCCESS = "Upload image success";
    public static String NOT_PERMISSION = "You do not permission";

    //role
    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_EXIST = "Create role %s failed, this role already exist!";
    public static String CREATE_ROLE_SUCCESS = "The role %s create successfully";

    //time
    public static String TIME_PATTERN = "dd/MM/yy hh:mm:ss aa";

    //file
    public static String CANNOT_INITIALIZE_STORAGE = "Cannot initialize storage";
    public static String FAILED_STORE_EMPTY_FILE = "Failed to store empty file";
    public static String YOU_CAN_ONLY_UPLOAD_IMAGE = "You can only upload image file";
    public static String SIZE_UPLOAD_FILE = "Image must be <= 5mb";
    public static String CANNOT_STORE_OUSIDE = "Cannot store file ouside current directory";
    public static String STORE_FILE_FAILED = "Failed to store the file";

    //diary
    public static String DIARY_NOT_FOUND = "Diary not found";
    public static String DIARY_DELETED = "Diary deleted";
    public static String DIARY_EDITED = "Diary edited";
    public static String LIST_DIARY_BY = "List diary by %s";
    public static String UNTITLED_DIARY = "Untitled Diary";
    public static String DIARY_CREATE_SUCCESS = "Diary create successfully";

    //token
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    //authen
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SECRET_CODE = "secret";
    public static final String ROLES = "roles";
}
