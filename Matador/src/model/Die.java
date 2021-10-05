package model;

public class Die {
    private int faceValue;

    public void roll() { this.faceValue = (int)(1 + Math.round(Math.random() * 5)); }

    public int getFaceValue() { return this.faceValue; }

    public void setFaceValue(int faceValue) { this.faceValue = faceValue; }
}
