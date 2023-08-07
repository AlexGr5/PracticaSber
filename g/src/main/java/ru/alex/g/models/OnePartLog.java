package ru.alex.g.models;

public class OnePartLog {
    // Одна распознанная часть лога
    private String onePartLog;

    // Отображаемая длина паттерна
    private Integer lengthDisplayString;

    // Цвет текста
    private String colorText;

    public OnePartLog(String onePartLog, Integer lengthDisplayString, String colorText) {
        this.onePartLog = onePartLog;
        this.lengthDisplayString = lengthDisplayString;
        this.colorText = colorText;
    }

    public String getOnePartLog() {
        return onePartLog;
    }

    public void setOnePartLog(String onePartLog) {
        this.onePartLog = onePartLog;
    }

    public Integer getLengthDisplayString() {
        return lengthDisplayString;
    }

    public void setLengthDisplayString(Integer lengthDisplayString) {
        this.lengthDisplayString = lengthDisplayString;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public String Hello() {return "Hello OnePartLog";}
}
