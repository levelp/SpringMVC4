package mvc3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Обычный контроллер, после вызова каждого метода
 * которого будет рисоваться соответствующий view
 */
@Controller
public class MyWebController {

    @RequestMapping("web/list")
    public String genList(Model model){
        List<String> list = new ArrayList<>();
        list.add("Санкт-Петербург");
        list.add("Москва");
        list.add("Брянск");
        list.add("Воронеж");
        // Добавляем список в параметры, которые переданы view
        model.addAttribute("list", list);

        // Создаём 2D массив
        int[][] mul = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mul[i][j] = (i+1) * (j+1);
            }
        }
        // и передаём его в view
        model.addAttribute("mul", mul);

        return "web/list";
    }
}
