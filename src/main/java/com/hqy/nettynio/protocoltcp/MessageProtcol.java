package com.hqy.nettynio.protocoltcp;

import lombok.Data;

//协议包
@Data
public class MessageProtcol {
    private int len;
    private byte[] content;

}
