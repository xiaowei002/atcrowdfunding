package com.xiaowei.util.result;

/**
 * 数据返回工具类。
 * @param <T>
 */
public class ResultEntity<T> {

    private static final String  SUCCESS = "SUCCESS";

    private static final String FAILED = "FAILED";

    //返回的结果，成功失败
    private String result;

    //错误信息
    private String message;

    //数据
    private T data;

    /**
     * 成功但不返回数据
     * @return
     * @param <E>
     */
    public static <E> ResultEntity<E> successWithOutData(){
        return new ResultEntity<>(SUCCESS,null,null);
    }

    /**
     * 成功并返回数据
     * @param data
     * @return
     * @param <E>
     */
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<>(SUCCESS,null,data);
    }

    /**
     * 失败返回错误信息
     * @param message
     * @return
     * @param <E>
     */
    public static <E>  ResultEntity<E> failed(String message){
        return new ResultEntity<>(FAILED,message,null);
    }


    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
