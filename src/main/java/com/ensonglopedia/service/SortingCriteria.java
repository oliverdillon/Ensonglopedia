package com.ensonglopedia.service;

public enum SortingCriteria {
    Title,Artist,Album;

    public String toString(){
        switch(this){
            case Title: return "Title";
            case Artist:return "Artist";
            case Album: return "Album";
            default: return "";
        }
    }
}
