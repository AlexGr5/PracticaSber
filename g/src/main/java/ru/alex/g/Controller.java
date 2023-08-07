package ru.alex.g;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.alex.g.addclasses.DisplayLogs;
import ru.alex.g.addclasses.ReadRowsFromTXT;
import ru.alex.g.addclasses.SplitLogs;
import ru.alex.g.dao.AllPartsForWeb;
import ru.alex.g.dao.InputParams;
import ru.alex.g.dao.ViewParams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
@RequestMapping("/logs")
public class Controller {

    private final AllPartsForWeb allPartsForWeb;


    private List<String> currentInputRows;

    private final ViewParams viewParams;

    private final InputParams inputParams;

    @Autowired
    public Controller( AllPartsForWeb allPartsForWeb,
                      ViewParams viewParams, InputParams inputParams) {
        this.allPartsForWeb = allPartsForWeb;
        this.viewParams = viewParams;
        this.inputParams = inputParams;
    }

    @GetMapping()
    public String index(Model model) {
        //model.addAttribute("people", personDAO.index());
        model.addAttribute("input", inputParams);
        return "index";
    }

    /*
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "logs/show";
    }
    */

    @GetMapping("/new")
    public String newPerson(/*@ModelAttribute("person") Person person*/Model model) {
        model.addAttribute("inputparams", inputParams);
        return "new";
    }

    @GetMapping("/logs")
    public String viewLogs(Model model) {
        //model.addAttribute("mylogs", allPartsForWeb.getAllLogs());

        //System.out.println(allPartsForWeb.getAllLogs().size());

        //mainLogic();
        model.addAttribute("viewparam", viewParams);

        return "logs";
    }

    @PostMapping()
    public String create(@ModelAttribute("inputparams") InputParams inputParams) {

        // Вызов основной логики
        //mainLogic();
        System.out.println(inputParams.getPattern());
        System.out.println(inputParams.getSortPattern());

        this.inputParams.setPattern(inputParams.getPattern());
        this.inputParams.setSortPattern(inputParams.getSortPattern());

        if(viewParams.getLogs() != null && currentInputRows != null) {
            if (viewParams.getLogs().size() > 0 && currentInputRows.size() > 0) {
                // 3) Использование цветной или монотонной подготовки отображаемых параметров
                //    с помощью одного метода на выбор, в зависимости от того, ввели сортируемый
                //    паттерн или нет.
                //***********************************************************
                if (inputParams.getSortPattern().length() > 0) {
                    this.viewParams.createViewParamsColor(inputParams.getPattern(), currentInputRows, inputParams.getSortPattern());
                } else {
                    this.viewParams.createViewParamsMono(inputParams.getPattern(), currentInputRows);
                }
                //***********************************************************
            }
        }


        return "redirect:/logs";
    }

    @GetMapping("/load")
    public String viewUploadFile(Model model){
        model.addAttribute("inputparams", inputParams);

        return "load";
    }

    @GetMapping("/clear")
    public String claerInputPararms(){

        this.inputParams.clear();
        this.currentInputRows = new ArrayList<>();

        return "redirect:/logs";
    }


    /*
        НЕ РАБОТАЕТ ЗАГРУЗКА ФАЙЛА!!!!
        ВООБЩЕ НИКАК!!!
     */
    /*
    @ResponseBody
    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file){
    //@PostMapping("/load")
    //public String uploadFile(@ModelAttribute("inputparams") InputParams inputParams){
        if (file == null)
        //if (inputParams.getFile() == null)
            System.out.println("Empty!");
        else
            System.out.println("Not Empty");
        return "redirect:/logs";
    }
     */

    @PostMapping("/load")
    public String load (@RequestParam("file") MultipartFile file, Map<String,Object> model) throws IOException
    {
        if (!file.isEmpty())
        {
            System.out.println("File is select!");
            this.inputParams.setFile(file);

            this.inputParams.setFileNotEmpty(true);

            /*
            try {
                byte[] bytes = file.getBytes();

                ByteArrayInputStream in = new ByteArrayInputStream(bytes);

                BufferedInputStream stream = new BufferedInputStream(in);
                //stream.write(bytes);
                //stream.close();

                int c;
                while((c=stream.read())!=-1){

                    System.out.print((char)c);
                }

                System.out.println("File is upload!");
                return "redirect:/logs";
            } catch (Exception e)
            {
                System.out.println("File is not upload!");
                return "redirect:/logs";
            }
             */

            // ПРОГРАММА
            try
            {


                // 1) Чтение строк из файла
                //***********************************************************
                byte[] bytes = inputParams.getFile().getBytes();

                ByteArrayInputStream in = new ByteArrayInputStream(bytes);

                BufferedInputStream stream = new BufferedInputStream(in);

                DataInputStream dis = new DataInputStream(stream);

                List<String> listRows = new ArrayList<>();

                // Чтение файла
                while(dis.available() != 0) {
                    listRows.add(dis.readLine());
                }
                //***********************************************************


                currentInputRows = listRows;

                // 2) Считывание введенного паттерна и сортируемого паттерна
                //***********************************************************
                // Вводим паттерн
                String inputPattern = this.inputParams.getPattern();

                // Для ввода паттерна сортировки
                String sortedPattern = this.inputParams.getSortPattern();
                //***********************************************************




                // 3) Использование цветной или монотонной подготовки отображаемых параметров
                //    с помощью одного метода на выбор, в зависимости от того, ввели сортируемый
                //    паттерн или нет.
                //***********************************************************
                if (sortedPattern.length() > 0)
                {
                    this.viewParams.createViewParamsColor(inputPattern, listRows, sortedPattern);
                }
                else
                {
                    this.viewParams.createViewParamsMono(inputPattern, listRows);
                }
                //***********************************************************



            } catch (IOException ex) {
                System.out.println("Error work with file!");
            }


            return "redirect:/logs";

        } else
        {
            //model.put("error", "Файл не выбран.");
            System.out.println("File is not select!");
            return "redirect:/logs";
        }
    }



    /*
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "logs/edit";
    }
     */

    /*
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "logs/edit";

        personDAO.update(id, person);
        return "redirect:/logs";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/logs";
    }
     */

    private void mainLogic()
    {
        // Пример ввода данных:
        // D:\altstu\3kurs\LetPract\test1.txt   или   D:\altstu\3kurs\LetPract\log-example.txt
        // %data%level%logger%thread%message
        // Enter или %level


        // ПРОГРАММА
        try
        {
            byte[] bytes = inputParams.getFile().getBytes();

            System.out.println("XYX");

            ByteArrayInputStream in = new ByteArrayInputStream(bytes);

            BufferedInputStream stream = new BufferedInputStream(in);

            DataInputStream dis = new DataInputStream(stream);


            List<String> listRows = new ArrayList<>();


            // Чтение файла
            while(dis.available() != 0) {
                listRows.add(dis.readLine());
            }

            // Вводим паттерн
            String inputPattern = this.inputParams.getPattern();

            // Распознаем логи из файла по паттерну
            List<List<String>> result = SplitLogs.SplitLogsForPattern(listRows, inputPattern);


            // Для ввода паттерна сортировки
            String sortedPattern = this.inputParams.getSortPattern();

            if (sortedPattern.length() > 0)
            {
                result = SplitLogs.SortRecognLogsForOnePattern(result, inputPattern, sortedPattern);
                DisplayLogs.DisplayColorLogsForPattern(result, inputPattern, sortedPattern);
            }
            else
            {
                // Выводим на экран
                //System.out.println(result);
                DisplayLogs.DisplayLogsForPattern(result, inputPattern);
            }

        } catch (IOException ex) {
            System.out.println("Error work with file!");
        }
    }


}