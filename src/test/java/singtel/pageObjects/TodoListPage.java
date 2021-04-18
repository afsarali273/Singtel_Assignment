package singtel.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TodoListPage {

    WebDriver driver;

    public TodoListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.TAG_NAME, using = "h1")
    WebElement todoTitle;

    @FindBy(how = How.CSS, using = ".new-todo")
    WebElement input_EnterTodo;

    @FindAll({@FindBy(how = How.CSS, using = ".todo")})
    List<WebElement> todoList;

    @FindBy(how = How.CSS, using = ".todo-count")
    WebElement txtCounterText;

    @FindBy(how = How.CSS, using = "footer > ul > li:nth-child(1) > a")
    WebElement btnSelectAll;

    @FindBy(how = How.CSS, using = "footer > ul > li:nth-child(2) > a")
    WebElement btn_Active;

    @FindBy(how = How.CSS, using = "footer > ul > li:nth-child(3) > a")
    WebElement btnCompleted;

    @FindBy(how = How.CSS, using = ".clear-completed")
    WebElement btnClearAll;


    public void createTodoList(String todoText) {
        input_EnterTodo.sendKeys(todoText + "\n");
    }

    public void verifyNoOfTodoLists(String noOfLists) {
        Assert.assertEquals(todoList.size(), Integer.parseInt(noOfLists), "Todo List doesnot exist as expected");
    }

    public void verifyBottomTextForTodoCounter(String noOfLists) {
        Assert.assertTrue(txtCounterText.getText().trim().equals(noOfLists + " items left"),
                "Counter in the bottom of the List doesn't matches as expected");
    }

    public void markAllTodoAsCompleted(String nosToMarkCompleted) {
        //First 2 to mark complete
        if (nosToMarkCompleted.contains("all")) {

            todoList.stream().forEach(todo -> {
                todo.findElement(By.xpath(".//div/input")).click();
            });
            System.out.println("Marked all todo as completed");

        } else if (nosToMarkCompleted.contains("first_two")) {
            todoList.stream().limit(2).forEach(todo -> {
                todo.findElement(By.xpath(".//div/input")).click();
            });
            System.out.println("Marked first " + nosToMarkCompleted + " todo as completed");
        }
    }

    public void verifyAllListAsTickedAndStrikeThrough() {
        todoList.forEach(todo -> {
            Assert.assertTrue(todo.findElement(By.xpath(".//div/label")).getCssValue("text-decoration").contains("line-through"), "TodoList is not strike through");
        });

        todoList.forEach(todo -> {
            Assert.assertTrue(todo.findElement(By.xpath(".//div/input")).isSelected(), "TodoList checkbox is not selected");
        });
    }

    public void clickOnFooterButtons(String btnName) {
        switch (btnName) {
            case "active":
                btn_Active.click();
                return;
            case "all":
                btnSelectAll.click();
                return;
            case "completed":
                btnCompleted.click();
                return;
            case "ClearCompleted":
                btnClearAll.click();
                return;
        }
    }

    public void undoCertainTodoList(String todoToUndoComplete) {
        todoList.stream().forEach(todo -> {
            if (todo.findElement(By.xpath(".//div/label")).getText().contains(todoToUndoComplete)) {
                todo.findElement(By.xpath(".//div/input")).click();
            }
        });
    }

    public void editTodoList(String existingTodo, String newTodo) {
        todoList.stream().forEach(todo -> {
            if (todo.findElement(By.xpath(".//div/label")).getText().contains(existingTodo)) {
                ((JavascriptExecutor) driver).executeScript(
                        "var ele=arguments[0]; ele.innerText = '" + newTodo + "';", todo.findElement(By.xpath(".//div/label")));
            }
        });
    }

    public void verifyTodoList(String todoText) {

        AtomicBoolean flag = new AtomicBoolean(false);
        todoList.stream().forEach(todo -> {
            if (todo.findElement(By.xpath(".//div/label")).getText().contains(todoText)) {
                Assert.assertTrue(true);
                flag.set(true);
            }
        });

        Assert.assertTrue(flag.get(), "Todo text is not present in the list for text : " + todoText);

    }


}
