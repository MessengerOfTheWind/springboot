package com.itheima.domainvo;


import com.itheima.domain.Signature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;




@EqualsAndHashCode(callSuper = false)
@Data
public class SignatureVo extends Signature {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;
    private String con;
    private Integer userid;
}
