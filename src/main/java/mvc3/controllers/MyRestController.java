package mvc3.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MyRestController {

    MyRestController() {
        System.out.println("MyRestController.MyRestController");
    }

    @RequestMapping("/text")
    public String simpleText() {
        return "Hello from MyRestController!";
    }

    /**
     * Пример вызова: http://localhost:8080/rest/idTestX/TestY
     * Ответ: x = TestX y = TestY
     */
    @RequestMapping(value = "/id{x}/{y}", method = RequestMethod.GET)
    public String myMethodWithParams(@PathVariable("x") String x,
                                     @PathVariable("y") String y) {
        return " x = " + x + " y = " + y;
    }

    /**
     * Пример вызова: http://localhost:8080/rest/sum?x=20&y=35
     * Ответ: 20 + 35 = 55
     */
    @RequestMapping("/sum")
    public String sum(@RequestParam("x") int x,
                      @RequestParam("y") int y) {
        return x + " + " + y + " = " + (x + y);
    }

    public static class Point {
        int id;
        static int count = 0;
        int x = 1;
        int y = 2;
        String name = "point";

        Point() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point(int x, int y) {
            id = ++count;
            name = "Точка #" + id;
            this.x = x;
            this.y = y;
        }
    }

    @RequestMapping(value = "/point", produces = "application/json; charset=UTF-8")
    public List<Point> point(@RequestParam("x") int x,
                             @RequestParam("y") int y) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(x, y));
        points.add(new Point(x + 1, y));
        points.add(new Point(x, y + 1));
        points.add(new Point(x - 1, y));
        points.add(new Point(x, y - 1));
        return points;
    }

    /**
     * Счётчик вызовов
     */
    int countCalls = 0;
    static int globalCountCalls = 0;

    @RequestMapping("/countCalls")
    public String countCalls() {
        globalCountCalls++;
        countCalls++;
        return "countCalls = " + countCalls + " (" + globalCountCalls + ")";
    }
}
