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
public class Payment {

    private Long id;
    private String serial;

}
