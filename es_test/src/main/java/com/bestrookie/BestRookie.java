package com.bestrookie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 21:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BestRookie {
    private String name;
    private String sex;
    private Integer age;

}
