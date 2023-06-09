package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetPayResponse extends ReponseBase {
    private List<Pay> payList;

    public List<Pay> getPayList() {
        return payList;
    }

    public void setPayList(List<Pay> payList) {
        this.payList = payList;
    }
}
