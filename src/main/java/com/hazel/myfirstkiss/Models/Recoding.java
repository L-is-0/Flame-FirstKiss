package com.hazel.myfirstkiss.Models;

public class Recoding {
    private int recordId;;
    private String recordContent;

    public void setRecordId(int id){
        this.recordId = id;
    }

    public void setRecordContent(String content){
        this.recordContent = content;
    }

    public int getRecordId(){
        return this.recordId;
    }

    public String getRecordContent(){
        return this.recordContent;
    }
}
