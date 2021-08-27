package org.example.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 宠物
 */
@NoArgsConstructor   //无参构造器
@AllArgsConstructor  //有参构造器
@Data
@ToString
public class Pet {

    private String name;

}
