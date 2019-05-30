package model;

public class DigImage {

    private double score;
    private String id;

    public DigImage(String id, double score){
        this.score = score;
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public String getId(){
        return this.id;
    }

    public String toString() {
        return String.valueOf(this.id);
    }
}
