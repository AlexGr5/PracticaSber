package ru.alex.g.models;

import java.util.ArrayList;
import java.util.List;

public class OneLog {
    // Одна строка лога, состоящая из распознанных частей
    private List<OnePartLog> oneRowLog;
    {
        oneRowLog = new ArrayList<>();
    }

    public OneLog() {oneRowLog = new ArrayList<>();}

    public OneLog(List<OnePartLog> oneRowLog) {
        this.oneRowLog = oneRowLog;
    }

    public List<OnePartLog> getOneRowLog() {
        return oneRowLog;
    }

    public void setOneRowLog(List<OnePartLog> oneRowLog) {
        this.oneRowLog = oneRowLog;
    }

    public void add(OnePartLog onePartLog)
    {
        oneRowLog.add(onePartLog);
    }

    public String Hello() {return "Hello OneLog";}
}
