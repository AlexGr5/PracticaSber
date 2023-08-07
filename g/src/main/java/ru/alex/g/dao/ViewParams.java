package ru.alex.g.dao;

import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;
import ru.alex.g.addclasses.Log;
import ru.alex.g.addclasses.OnePattern;
import ru.alex.g.addclasses.SplitLogs;

import java.util.ArrayList;
import java.util.List;

@Component
public class ViewParams {


    //private String color;
    private int numberLight;
    private List<String> patterns;
    private List<Integer> lengthColumn;

    private List<Log> logs;


    public ViewParams() {
        logs = new ArrayList<>();
        patterns = new ArrayList<>();


        logs = new ArrayList<>();

        /*
        //List<List<String>> testList = new ArrayList<>();
        List<String> testUnderList1 = new ArrayList<>();
        List<String> testUnderList2 = new ArrayList<>();
        testUnderList1.add("one");
        testUnderList1.add("two");
        testUnderList1.add("three");
        testUnderList2.add("four");
        testUnderList2.add("five");
        testUnderList2.add("six");
        logs.add(testUnderList1);
        logs.add(testUnderList2);
         */


        lengthColumn = new ArrayList<>();

        patterns = new ArrayList<>();
        patterns.add("data");
        patterns.add("level");
        patterns.add("thread");


        numberLight = 1;
    }

    public ViewParams(int numberLight, List<String> patterns, List<Integer> lengthColumn, List<Log> logs) {
        this.numberLight = numberLight;
        this.patterns = patterns;
        this.lengthColumn = lengthColumn;
        this.logs = logs;
    }



    public int getNumberLight() {
        return numberLight;
    }

    public void setNumberLight(int numberLight) {
        this.numberLight = numberLight;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    public List<Integer> getLengthColumn() {
        return lengthColumn;
    }

    public void setLengthColumn(List<Integer> lengthColumn) {
        this.lengthColumn = lengthColumn;
    }

    public Integer getLengthForIndex(int index) { return this.lengthColumn.get(index);}

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    // Создать отображаемые параметры
    public void createViewParamsColor(String patterns, List<String> logs, String sortPattern)
    {
        if(sortPattern != null)
        {
            if (sortPattern.length() > 0)
            {

                // Список различных цветов
                List<String> listColorsStart = new ArrayList<>();
                listColorsStart.add("color:red");
                listColorsStart.add("color:SaddleBrown");
                listColorsStart.add("color:green");
                listColorsStart.add("color:blue");
                listColorsStart.add("color:Purple");
                listColorsStart.add("color:orangered");
                listColorsStart.add("color:DarkTurquoise");
                listColorsStart.add("color:DimGray");


                // Распознаем логи из файла по паттерну
                this.logs = SplitLogs.CreateAllLogs(logs, patterns);

                // Сортируем
                this.logs = SplitLogs.SortRecognLogs(this.logs, sortPattern);


                this.patterns = new ArrayList<>();

                // Вводим паттерн
                List<OnePattern> tmpPatterns = SplitLogs.RecognizePatternFromInputString(patterns);
                for (OnePattern tmp: tmpPatterns)
                {
                    this.patterns.add(tmp.GetNamePattern());
                }

                // Для ввода паттерна сортировки
                List<OnePattern> tmpSortPatterns = SplitLogs.RecognizePatternFromInputString(sortPattern);

                // Позиция сортируемого паттерна в изначальном паттерне логов
                int position = 0;
                for (position = 0; position < tmpPatterns.size(); position++)
                {
                    // Определяем позицию сортируемого паттерна в изначальном паттерне для всего лога
                    if (tmpPatterns.get(position).GetNamePattern().equals(tmpSortPatterns.get(0).GetNamePattern()))
                        break;
                }

                this.numberLight = position;

                // Список различных вариаций строк столбца
                List<String> differentRows = new ArrayList<>();

                // Создаем список уникальных записей столбца
                for (Log rows: this.logs)
                {
                    //if (!(Arrays.asList(differentRows).contains(rows.get(position))))
                    if (!(differentRows.contains(rows.GetIndex(position))))
                        differentRows.add(rows.GetIndex(position));
                }


                for (Log oneRow : this.logs)
                {
                    String format = new String();
                    for (int i = 0; i < oneRow.getOneLogRow().size(); i++) {

                        if (i == position) {
                            int tmpIndex = differentRows.indexOf(oneRow.GetIndex(position));
                            int useIndex = 0;

                            if (tmpIndex >= listColorsStart.size())
                            {
                                useIndex = tmpIndex % listColorsStart.size();
                                //System.out.println(useIndex);
                                //useIndex = listColorsStart.size() - 1;
                            }
                            else {
                                useIndex = tmpIndex;
                            }
                            oneRow.setColor(listColorsStart.get(useIndex));
                        }
                        else
                        {
                            ;
                        }
                    }
                }

                this.lengthColumn = createListLengths();

            } else
            {

            }
        }
    }

    // Создать отображаемые параметры
    public void createViewParamsMono(String patterns, List<String> logs)
    {

        // Распознаем логи из файла по паттерну
        this.logs = SplitLogs.CreateAllLogs(logs, patterns);

        this.patterns = new ArrayList<>();

        // Вводим паттерн
        List<OnePattern> tmpPatterns = SplitLogs.RecognizePatternFromInputString(patterns);
        for (OnePattern tmp: tmpPatterns)
        {
            this.patterns.add(tmp.GetNamePattern());
        }

        this.lengthColumn = createListLengths();

        //lengthColumn = 30;
    }



    {
        if (this.logs != null)
            if (this.logs.size() > 0)
                this.logs.get(0).getColor();
    }

    // Создать список размеров столбцов, с максимальным
    // размером равным максимальной длине части среди всех строк в столбце
    public List<Integer> createListLengths()
    {
        List<Integer> result = new ArrayList<>();

        if (patterns.size() > 0) {

            for (int i = 0; i < patterns.size(); i++)
                result.add(0);

            for (Log oneRow : this.logs) {
                for (int i = 0; i < oneRow.getOneLogRow().size(); i++) {
                    if (oneRow.getOneLogRow().get(i).length() > result.get(i))
                    {
                        result.set(i, oneRow.getOneLogRow().get(i).length());
                    }
                }
            }
        }
        return result;
    }

}
