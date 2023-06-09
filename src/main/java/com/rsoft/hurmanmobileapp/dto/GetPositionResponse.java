package com.rsoft.hurmanmobileapp.dto;

import java.util.List;

public class GetPositionResponse extends ReponseBase{
    private List<Position> positionList;

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
