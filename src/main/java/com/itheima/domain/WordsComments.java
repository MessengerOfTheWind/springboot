package com.itheima.domain;

import lombok.Data;

@Data
public class WordsComments {
    private String id;
    private int WordsId;
    private int WordsRelId;
    private String content;
    private String seenState;
    private String pid;

}
