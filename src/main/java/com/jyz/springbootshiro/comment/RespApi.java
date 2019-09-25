package com.jyz.springbootshiro.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RespApi {

    private  int code;
    private  String msg;
    private  Object data;


    public static   RespApi operateOk(){
        return  new RespApi(200,"操作成功",null);
    }

    public  static RespApi operateError(){
        return  new RespApi(500,"操作失败",null);
    }

    public  static RespApi operateError(String message){
        return  new RespApi(500,message,null);
    }

    public static   RespApi operateOk(String message){
        return  new RespApi(200,message,null);
    }

    public static   RespApi operateOk(int code,String message){
        return  new RespApi(code,message,null);
    }

    public static   RespApi operateOk(int code,String message,Object data){
        return  new RespApi(code,message,data);
    }


    public static   RespApi operateOk(String message,Object data){
        return  new RespApi(200,message,data);
    }

    public static   RespApi operateOk(Object data){
        return  new RespApi(200,"操作成功",data);
    }
}
