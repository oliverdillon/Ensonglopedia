package backend;

public enum Criteria {
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
