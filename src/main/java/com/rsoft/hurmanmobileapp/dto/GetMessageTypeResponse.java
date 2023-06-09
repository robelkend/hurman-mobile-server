package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetMessageTypeResponse extends ReponseBase{
    private List<String> messageTypeList;

    public List<String> getMessageTypeList() {
        return messageTypeList;
    }

    public void setMessageTypeList(List<String> messageTypeList) {
        this.messageTypeList = messageTypeList;
    }
}
