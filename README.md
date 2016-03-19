SpringMVC4
==========

[![Build Status](https://travis-ci.org/levelp/SpringMVC4.svg?branch=master)](https://travis-ci.org/levelp/SpringMVC4)
[![Coverage Status](https://coveralls.io/repos/github/levelp/SpringMVC4/badge.svg?branch=master)](https://coveralls.io/github/levelp/SpringMVC4?branch=master)

Сайт, созданный на основе шаблона https://github.com/kolorobot/spring-mvc-quickstart-archetype

Spring Framework
----------------
* Spring MVC - интерфейс
 * @Controller - класс обрабатывает пользовательские запросы и возвращает данные (в виде простого текста, JSON, или одного из View)
 * @RestController - ответы на Ajax-запросы
 * @PathVariable - переменная, которая передаётся как часть пути (часть URL) "как часть названия каталога"
 * @RequestMapping - указывает URL
* Spring Core
 * beans
 * XML-конфигурации
 * Обработка Аннотаций @Component, @Service, @Autowired
* Spring Security
 * Авторизация
 * Роли
 * Проверка логина и пароля по БД
* Spring Data - @JpaRepository

Другие технологии
-----------------
* JDBC - подключение драйверов, работа с БД напрямую
* Hibernate (JPA) - работа с БД (генерация SQL-запросов)
* Thymeleaf - HTML-шаблоны
* Tomcat (веб-сервер)