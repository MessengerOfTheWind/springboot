package com.itheima.domain;

import lombok.Data;

@Data
public class SignatureComments {
    private String id;
    private int signatureId;
    private int signature_rel_id;
    private String content;
    private String seenState;
    private String pid;
}
