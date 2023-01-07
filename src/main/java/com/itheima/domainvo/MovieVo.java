package com.itheima.domainvo;


import com.itheima.domain.Movie;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class MovieVo extends Movie {
    private Integer mid;
    private String muid;
    private String mmoviename;
    private String mseetime;
    private String mdescription;
}
