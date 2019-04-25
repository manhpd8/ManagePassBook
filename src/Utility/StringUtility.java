/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author Phạm Đức Mạnh
 */
public class StringUtility {
    public static String NOT_NULL_USERNAME = "user name is not null";
    public static String NOT_NULL_PASSWORD = "pass word is not null";
    public static String NOT_NULL_NAME = "name is not null";
    public static String NOT_NULL_IDCARD = "id card is not null";
    public static String NOT_NULL_ADDRESS = "address is not null";
    public static String NOT_EMPTY_SEARCH_KEY_INPUT="Search Key cannot empty, fill pls";
    public static String RESULT_EMPTY="Search result is empty";
    public static String INPUT_ONLY_NUMBER="search input only number";
    
    public static String INVALID_USERNAME = "user Name is invalid";
    public static String INVALID_PASSWORD = "pass word is invalid";
    public static String INVALID_IDCARD = "id card is invalid";
    public static String INVALID_PHONE = "phone is invalid";
    
    
    public static String SUCCESS_LOGIN = "login success";
    public static String SUCCESS_ADD_CLIENT = "created client";
    
    public static String ERROR_LOGIN = "error user name or password";
    public static String ERROR_DUPPLICATE_CLIENT = "client is dupplicate: id card is used";
    
    public static String GENDER_MALE = "male";
    public static String GENDER_FEMALE = "fmale";
    public static String GENDER_UNKNOWN = "unkn";
    
    public static double LAI_XUAT_KKH= 0.2;
    
    
    public static boolean NullOrEmpty(String str){
        return str == null || str.length() == 0;
    }
    public static boolean checkSqlInjection(String str){
        if(!NullOrEmpty(str)){
            for(int i=0;i<str.length();i++){
                if('\'' == str.charAt(i) || '\"' == str.charAt(i) || '-' == str.charAt(i)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
