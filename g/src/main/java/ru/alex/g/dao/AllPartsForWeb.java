package ru.alex.g.dao;

import org.springframework.stereotype.Component;
import ru.alex.g.models.OneLog;
import ru.alex.g.models.OnePartLog;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllPartsForWeb {

    private List<OneLog> allLogs;


    {
        allLogs = new ArrayList<>();

        OnePartLog data1 = new OnePartLog("2023-06-20 12:29:50,221", 30, "red");
        OnePartLog data2 = new OnePartLog("2023-06-20 12:29:50,221", 30, "red");
        OnePartLog data3 = new OnePartLog("2023-06-20 12:29:50,221", 30, "red");

        OnePartLog thread1 = new OnePartLog("containersThreadPoolTaskScheduler-1", 30, "color:red");
        OnePartLog thread2 = new OnePartLog("ServerService Thread Pool -- 82", 30, "color:red");
        OnePartLog thread3 = new OnePartLog("consumer-0-C-1", 30, "color:red");

        OnePartLog logger1 = new OnePartLog("stdout", 30, "red");
        OnePartLog logger2 = new OnePartLog("stdout", 30, "red");
        OnePartLog logger3 = new OnePartLog("stdout", 30, "red");

        OnePartLog level1 = new OnePartLog("INFO", 30, "red");
        OnePartLog level2 = new OnePartLog("ERROR", 30, "red");
        OnePartLog level3 = new OnePartLog("WARNING", 30, "red");

        OnePartLog mess1 = new OnePartLog("sgsdgsdgsdfg", 30, "red");
        OnePartLog mess2 = new OnePartLog("23425356476456", 30, "red");
        OnePartLog mess3 = new OnePartLog("dhdhhfghfghfh", 30, "red");

        OneLog row1 = new OneLog();
        OneLog row2 = new OneLog();
        OneLog row3 = new OneLog();

        row1.add(data1);
        row1.add(thread1);
        row1.add(logger1);
        row1.add(level1);
        row1.add(mess1);

        row1.add(data2);
        row1.add(thread2);
        row1.add(logger2);
        row1.add(level2);
        row1.add(mess2);

        row1.add(data3);
        row1.add(thread3);
        row1.add(logger3);
        row1.add(level3);
        row1.add(mess3);

        allLogs.add(row1);
        allLogs.add(row2);
        allLogs.add(row3);

    }

    public AllPartsForWeb()
    {
    }

    public AllPartsForWeb(List<List<String>> recognizedLogs)
    {
        CreateFromListListString(recognizedLogs);
    }

    // Вернуть спиок логов
    public List<OneLog> getAllLogs() {
        return allLogs;
    }


    // Создать список объектов OneLog из переданного списка списков строк
    public void CreateFromListListString(List<List<String>> recognizedLogs)
    {
        allLogs = new ArrayList<>();

        for (int i = 0; i < recognizedLogs.size(); i++)
        {
            OneLog rowLog = new OneLog();

            for (int j = 0; j < recognizedLogs.get(i).size(); j++)
            {
                OnePartLog onePartLog = new OnePartLog(recognizedLogs.get(i).get(j),
                        recognizedLogs.get(i).get(j).length() + 5, "black");
                rowLog.add(onePartLog);
            }
            allLogs.add(rowLog);
        }
    }

    public String Hello() {return "Hello AllParts";}
}
