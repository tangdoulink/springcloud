package com.zpb.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @dec :
 * @Date: 2020/3/30
 * @Auther: pengbo.zhao
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCommont<T> {

    private Integer code;
    private String message;
    private T data;

    public ResultCommont(Integer code, String message){
        this(code,message,null);
    }
}
