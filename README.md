# Singtel Assignment

**How to Run**

```cmd
1. git clone "https://github.com/afsarali273/Singtel_Assignment.git"

2. cd /path/to/project/Singtel_Assignment

3. mvn clean install
4. mvn test 

5. Then you can see Html report at 
/target/pretty-cucumber/cucumber-html-reports/

```

or you can also run test from feature file

*Go to /singtel_Assignment/src/test/resources/features/
Run manually by right clicking and Run feature file.*

```html
Below Tests are covered:

Feature :
1.I shall be able to create new Todo lists
2.I shall be able to mark todo as completed
3.I shall be able to delete todo list
4.I shall be able to clear only completed todo list
5.I shall be able to undo completed todo list
6.I shall be able to edit todo list

```

```gherkin
Scenario: I shall be able to create new Todo lists
  Given I am on the todo page
  When I insert my todo tasks in the input field and press enter
    | This is my First Task to complete.  |
    | This is my Second Task to complete. |
    | This is my Third Task to complete.  |
  Then I shall see 3 new Todo list is being added in the list
  And verify numbers of todo as 3 in the bottom text

```

**Test Results**
![Test Results](https://github.com/afsarali273/Singtel_Assignment/blob/master/img.png)


