package org.yufan.common;


public class ResultUtils {


    /**
     * 返回成功  不带返回结果
     */
    public static Result buildSuccess(){
        Result result=new Result();
        result.setStatus(200);
        return result;
    }


    /**
     * 返回成功  带返回结果
     */
    public static Result buildSuccess(Object obj){
        Result result=new Result();
        result.setStatus(200);
        result.setData(obj);
        return result;
    }


    /**
     * 返回失败
     */
    public static Result buildFail(Integer status,Object obj){
        Result result=new Result();
        result.setStatus(status);
        result.setData(obj);
        return result;
    }





}
