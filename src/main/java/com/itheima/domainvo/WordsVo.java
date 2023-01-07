package com.itheima.domainvo;


import com.itheima.domain.Words;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@EqualsAndHashCode(callSuper = false)
@Data
public class WordsVo extends Words {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    private String con;
    private Integer userid;
}
