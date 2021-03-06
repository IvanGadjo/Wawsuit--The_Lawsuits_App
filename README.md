# Wawsuit--The_Lawsuits_App
### *English*
Project built for the subject Web Programming, Faculty of computer science and engineering, Skopje, North Macedonia.
This is the back-end of the web application. The frontend can be found at https://github.com/IvanGadjo/Wawsuit--The_Lawsuits_App_Front 

### Theme
The goal of this project is to develop an aplication which would aid in managing and tracking lawsuits in some company. It is designated to be used by the lawyers and also their supervisors in the company.
Every user can create a profile and choose a role - lawyer or supervisor. On the homepage the user can see his/hers profile and change username or password. In the "cases" tab, the user can see all ongoing lawsuit cases, with CRUD actions available for every one of them. By clicking on the button "Employees on this case" the user can see all employees working on it, but he/she can add or remove employees to the case. "Documents" leads to the documents produced by a court or a company related to the respective case (with all CRUD operations available. Moreover by clicking "All employees" the user sees all employees and the lawsuit cases they work on, and "all lawsuit entities" displays every plaintiff/defendant person/company registered in the application.

### Technologies
Spring Boot, React JS, MySQL


##
##
### *Македонски*

#### Иван Гаџовски, 171095
Семинарска по предметот веб програмирање, ФИНКИ, зимски семестар 2019/2020

### Тема
Целта на оваа семинарска е изработка на апликација која би го олеснила следењето на сите судски постапки кои се водат во рамките на 
некоја компанија, нејзината намена е да биде користена од правните лица и нивните раководители во компанијата. 

Моделот е замислен така што секоја правна постапка е можно да има максимум 2 „подпостапки" во зависност од тоа до кој суд е стигната 
истата (основен, апелационен или врховен) при што двете подпостапки се водат како нови правни постапки - од апелационен или врховен суд 
соодветно (parent-child модел).

Секој корисник може да направи свој профил на апликацијата каде избира улога - правно лице или пак раководител. На почетната страна се
прикажани информациите за профилот на корисникот и има можност за промена на истите, како и можност за промена на корисничкото име и
лозинката.
Во табот „cases" корисникот може да ја види состојбата на сите тековни правни постапки (број, име, вредност, тужител, тужен...).
Секоја правна постапка може да биде модифицирана или пак истата може да биде избришана во делот „actions". При клик на dropdown менито
се прикажуваат сите подпостапки кои соодвествуваат на истата.
Во делот „Employees on this case" од соодветната постапка може да се видат сите вработени кои работат на постапката, има можност за нивно 
остранување од истата и можност за додавање нови вработени (претходно мора да се регистрирани на апликацијата). „Documents" ги означува
сите документи кои произлегуваат од судовите или од компанијата кои се однесуваат на постапката, како и функционалност за додавање нов
документ или пак промена на истиот и податоците кои се поврзани со него. 
Табот „all employees" ги прикажува сите вработени и на кои постапки работат, „all lawsuit entities" се однесува на сите правни субјекти
(тужители/тужени) кои биле регистрирани во апликацијата.

### Користени технологии
Spring Boot, React JS и MySQL

### Стартување на апликацијата
Пред да се стартува апликацијата потребно е да се креира MySQL база на податоци:
- Schema: lawsuitsdb
- User: root

Команда за стартување: mvn spring-boot:run
