package ru.alex.g.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.alex.g.addclasses.LogsPattern;
import ru.alex.g.addclasses.OnePattern;

import java.util.List;

@Component
public class InputParams {

    private String pattern;
    private String sortPattern;
    private MultipartFile file;
    private boolean fileNotEmpty;

    private LogsPattern logsPatternForInfoInWeb;

    public InputParams() {
        fileNotEmpty = false;
    logsPatternForInfoInWeb = new LogsPattern();}


    public InputParams(String pattern, String sortPattern, MultipartFile file, boolean fileIsEmpty) {
        this.pattern = pattern;
        this.sortPattern = sortPattern;
        this.file = file;
        this.fileNotEmpty = fileIsEmpty;
        this.logsPatternForInfoInWeb = new LogsPattern();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getSortPattern() {
        return sortPattern;
    }

    public void setSortPattern(String sortPattern) {
        this.sortPattern = sortPattern;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean fileNotEmpty() {
        return fileNotEmpty;
    }

    public void setFileNotEmpty(boolean fileNotEmpty) {
        this.fileNotEmpty = fileNotEmpty;
    }

    public boolean fileIsEmpty()
    {
        if(file != null)
            if(!file.isEmpty())
                return true;
        return false;
    }

    public void clear()
    {
        this.pattern = "";
        this.sortPattern = "";
        this.file = null;
        this.fileNotEmpty = false;
    }

    public LogsPattern getLogsPatternForInfoInWeb() {
        return logsPatternForInfoInWeb;
    }

    public void setLogsPatternForInfoInWeb(LogsPattern logsPatternForInfoInWeb) {
        this.logsPatternForInfoInWeb = logsPatternForInfoInWeb;
    }

    public List<OnePattern> getListOnePattern()
    {
        return this.logsPatternForInfoInWeb.getAllPatterns();
    }
}
