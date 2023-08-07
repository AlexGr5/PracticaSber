package ru.alex.g.addclasses;

import java.util.ArrayList;
import java.util.List;

public class LogsPattern {

    // Всевозможные паттерны
    static private List<OnePattern> allPatterns;

    // Конструктор по умоляанию
    public LogsPattern()
    {
        allPatterns = new ArrayList<OnePattern>();

        OnePattern data = new OnePattern("data", 30, "Date pattern. Can recognize date and time.");
        //
        data.AddRegular("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2} [0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[,]{1}[0-9]{0,8}");
        data.AddRegular("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2} [0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[\\.]{1}[0-9]{0,8}");

        data.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([-0-9:\\.,]{1,70})");

        data.AddRegular("([0-9]{0,2})?( [a-z]{3})?([.]{1})?( [0-9]{4})?([;]{1})?([0-9]{2})?([:]{1})?([0-9]{2})?([:]{1})?([0-9]{2})?([.]{1})?([0-9]{0,8})?");
        //data.AddRegular("[0-9]{1}[0-9:]{2}[0-9]{1}[0-9:]{2}[0-9]{1}[,0-9]{2}[0-9]{1,8}");
        data.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[\\,]{1}[0-9]{3})");
        //data.AddRegular("[0-9]{1}[0-9:]{2}[0-9]{1}[0-9:]{2}[0-9]{1}[.0-9]{2}[0-9]{1,8}");
        data.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([0-9]{2}[:]{1}[0-9]{2}[:]{1}[0-9]{2}[\\.]{1}[0-9]{3})");


        OnePattern thread = new OnePattern("thread", 100, "Thread pattern. Recognizes the log sending thread.");
        //-----------
        //thread.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)(.{1,50})");
        //-----------
        thread.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{1,16} [A-Za-z]{1,16} [A-Za-z]{1,16} -- [0-9]{1,12})");

        //thread.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Z]{1,27} [A-Z]{1,11} [A-Z]{1,11} -- [0-9]{1,13})");
        //VVV thread.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)(?!--$)([A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}[-A-Za-z]{1}[A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}([A-Za-z]{0,1})?([-A-Z]{0,1})?([0-9A-Z]{0,1})?([-A-Z]{0,1})?([A-Z]{0,1})?([-A-Z]{0,1})?([0-9A-Z]{0,1})?([A-Z]{0,8})?([-]{0,1})?([0-9]{0,13})?([-]{0,1})?([A-Z]{0,29})?([-]{0,1})?([0-9]{0,13})?)");
        thread.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)([A-Za-z]{1,28}-[0-9]{1,25}-[A-Za-z]{1}([a-z]{0,28})?-[0-9]{1,25})");
        //VVV thread.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)([A-Za-z]{1,10}([A-Z]{0,1})?([A-Za-z]{0,5})?([A-Z]{0,1})?([A-Za-z]{0,3})?([A-Z]{0,1})?([A-Za-z]{0,3})?([A-Z]{0,1})?([A-Za-z]{0,8})?([A-Z]{0,17})?-[0-9]{1,28})");

        //-----------
        thread.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([-0-9A-Z_a-z\\.|]{1,450})");
        //-----------

        thread.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)([A-Za-z]{1,51}-[0-9]{1,30})");
        //thread.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}[-A-Za-z]{1}[A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}([A-Za-z]{0,1})?([-A-Z]{0,1})?([0-9A-Z]{0,1})?([-A-Z]{0,2})?([-0-9]{0,1})?([0-9]{0,8})?([-]{0,1})?([A-Z]{0,11})?([-]{0,1})?([0-9]{0,13})?)");




        //thread.AddRegular("[A-Za-z]{1}[a-z]{11,12}([A-Za-z]{0,1})?([a-z]{0,15})? [A-Za-z]{1}[a-z]{5,25} [A-Za-z]{1}[a-z]{2,25} [-]{1,2} [0-9]{2,9}");
        //thread.AddRegular("[A-Z]{1}[a-z]{1}([A-Za-z]{0,1})?([a-z]{0,3})?([A-Za-z]{0,1})?([a-z]{0,7})?([A-Za-z]{0,1})?([a-z]{0,1})?([A-Za-z]{0,1})?([a-z]{0,15})? [A-Z]{1}[a-z]{1,14} [A-Z]{1}[a-z]{1,17} [-]{1,2} [0-9]{1,14}");
        //thread.AddRegular("[A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}[-A-Za-z]{1}[A-Za-z]{1}[-A-Za-z]{1}[0-9A-Za-z]{1}([A-Za-z]{0,1})?([-A-Za-z]{0,1})?([0-9A-Za-z]{0,1})?([-0-9A-Z]{0,1})?([-0-9]{0,1})?([0-9]{0,1})?([-0-9]{0,1})?([0-9A-Z]{0,1})?([-0-9]{0,1})?([0-9]{0,1})?([-0-9]{0,1})?([-A-Z]{0,1})?([A-Z]{0,1})?([-A-Z]{0,1})?([0-9A-Z]{0,1})?([A-Z]{0,4})?([-]{0,1})?([0-9]{0,12})?");
        //thread.AddRegular("[A-Za-z]{1}[-a-z]{1}[0-9a-z]{1}([a-z]{0,4})?([-a-z]{0,1})?([0-9a-z]{0,2})?([0-9A-Za-z]{0,1})?([-0-9a-z]{0,1})?([0-9a-z]{0,4})?([0-9A-Z]{0,1})?([0-9a-z]{0,2})?([a-z]{0,1})?([A-Z]{0,1})?([a-z]{0,3})?([A-Z]{0,1})?([a-z]{0,8})?([-]{0,1})?([0-9]{0,12})?");
        //thread.AddRegular("[0-9a-z]{1,50}([a-z]{0,50})?");

        OnePattern level = new OnePattern("level", 8, "Level pattern. Recognizes the importance level of the log.");
        level.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{1,69})");
        //level.AddRegular("[A-Za-z]{4,20}([a-z]{0,20})?");

        OnePattern logger = new OnePattern("logger", 50, "Pattern logger. Recognizes the sender of the log log.");
        //-----------
        //logger.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)(.{1,50})");
        logger.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([-0-9A-Z_a-z\\.]{1,450})");
        //-----------
        //??? logger.AddRegular("(?!.*\\]$)(?!.*\\[$)(?!.*\\)$)(?!.*\\($)(?!.* $)([A-Za-z]{1}(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,1})?(.{0,2})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?([.]{0,1})?([A-Za-z]{0,18})?)");
        //+-+-+-logger.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{6}([A-Z]{0,34})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?([.]{0,1})?([A-Z]{0,40})?)");
        logger.AddRegular("(?!.*\\[$)(?!.*\\]$)(?!.*\\($)(?!.*\\)$)(?!.* $)([A-Za-z]{1,18}(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?(\\.[A-Za-z]{1,18})?)");
        logger.AddRegular("(?!\\.$)(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{1,69})");
        //-----logger.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)([A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1}.{1}[A-Za-z]{1,10}([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?([.]{0,1})?([A-Za-z]{0,30})?)");
        //----logger.AddRegular("[A-Za-z]{1}(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Za-z]{0,6})?(.{0,1})?([A-Za-z]{0,5})?(.{0,1})?([A-Za-z]{0,7})?(.{0,1})?([A-Za-z]{0,8})?(.{0,1})?([A-Za-z]{0,7})?(.{0,1})?([A-Za-z]{0,1})?(.{0,1})?([A-Z]{0,1})?([A-Za-z]{0,6})?([A-Z]{0,1})?([A-Za-z]{0,10})?([A-Z]{0,21})?([.]{0,1})?([A-Z]{0,53})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([;]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?([.]{0,1})?([A-Z]{0,32})?");

        OnePattern message = new OnePattern("message", 120, "Message pattern. Specifies all text up to the end of the line as a message. If it is followed by a pattern, it will be ignored.");
        //message.AddRegular("(?!\\]$)(?!\\[$)(?!\\)$)(?!\\($)(.*)");
        message.AddRegular(".*");

        allPatterns.add(data);
        allPatterns.add(thread);
        allPatterns.add(level);
        allPatterns.add(logger);
        allPatterns.add(message);
    }

    // Добавить паттерн
    public void AddPattern(OnePattern onePattern)
    {
        allPatterns.add(onePattern);
    }

    // Добавить паттерн по частям
    public void AddPatternForGears(String namePattern , List<String> regulars, Integer lengthDisplayString1, String info)
    {
        OnePattern onePattern = new OnePattern(namePattern, lengthDisplayString1, info);
        for (String str: regulars)
        {
            onePattern.AddRegular(str);
        }
    }

    // Разбить строку на нужные паттерны
    public List<OnePattern> SplitStringForPatterns(String inputString)
    {
        List<OnePattern> result = new ArrayList<OnePattern>();

        String[] words = inputString.split("%");

        for (String word: words)
        {
            for(OnePattern onePattern: allPatterns)
            {
                if (onePattern.EqualsStringWithPattern(word))
                    result.add(onePattern);
            }
        }

        return result;
    }

    public List<OnePattern> getAllPatterns() {
        return allPatterns;
    }

    public void setAllPatterns(List<OnePattern> allPatterns) {
        this.allPatterns = allPatterns;
    }
}