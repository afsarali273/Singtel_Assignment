package singtel.stepDefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en_scouse.An;
import org.openqa.selenium.WebDriver;
import singtel.frameworkcore.DriverFactory;
import singtel.pageObjects.PageFactoryObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.concurrent.TimeUnit;

public class TodoListStepDef {

    public static PageFactoryObject pagefactory;
    public WebDriver driver;

    @Given("I am on the todo page")
    public void i_am_on_the_TodoPage() throws Exception {

        DriverFactory driverFactory = DriverFactory.getInstance();
        driverFactory.setDriver("Chrome");
        driver = driverFactory.getDriver();
        pagefactory = new PageFactoryObject(driver);
        driverFactory.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverFactory.getDriver().get("https://todomvc.com/examples/vue/");

    }

    @When("^I insert my todo tasks in the input field and press enter$")
    public void insertTodoListInTextField(DataTable table) {
        table.asList().stream().forEach(x -> {
            pagefactory.getTodoListPage().createTodoList(x);
        });
    }

    @Then("^I shall see (.*) new Todo list is being added in the list$")
    public void i_shall_see_newly_added_todo_list(String noOfLists) {
        pagefactory.getTodoListPage().verifyNoOfTodoLists(noOfLists);
    }

    @Then("^I shall see (.*) (active|completed) todo records$")
    public void verifyActiveTodoLists(String noOfLists, String active_completed) {
        pagefactory.getTodoListPage().verifyNoOfTodoLists(noOfLists);
    }

    @And("^verify numbers of todo as (.*) in the bottom text$")
    public void verifyNoOfTodoLists(String noOfTodo) {
        pagefactory.getTodoListPage().verifyBottomTextForTodoCounter(noOfTodo);
    }

    @When("^I mark (all|first_two) todo list as completed$")
    public void markAllTodoToComplete(String nosToMarkCompleted) {
        pagefactory.getTodoListPage().markAllTodoAsCompleted(nosToMarkCompleted);
    }

    @Then("I shall see all todo list with tick and strike out")
    public void verifyAllTodoListTickedAndStrikeThrough() {
        pagefactory.getTodoListPage().verifyAllListAsTickedAndStrikeThrough();
    }

    @When("^I click on the (active|all|completed|ClearCompleted) button$")
    public void clickActive_All_Clear_Button(String footerBtnName) {
        pagefactory.getTodoListPage().clickOnFooterButtons(footerBtnName);
    }

    @Then("^I Undo below todo list from completed$")
    public void undoBelowTodoFromCompleted(DataTable table) {
        table.asList().stream().forEach(todoMarkIncomplete -> {
            pagefactory.getTodoListPage().undoCertainTodoList(todoMarkIncomplete);
        });
    }

    @And("^I Edit below todo list from completed$")
    public void editExistingTodoList(DataTable table) {
        table.asList().forEach(todo -> {
            String[] array = todo.split("->");
            String existingTodo = array[0].trim();
            String editedTodo = array[1].trim();
            pagefactory.getTodoListPage().editTodoList(existingTodo, editedTodo);
        });
    }

    @And("I shall see below todo lists")
    public void verifyTodoLists(DataTable table) {
        table.asList().stream().forEach(todo -> {
            pagefactory.getTodoListPage().verifyTodoList(todo);
        });
    }


    @After
    public void AfterScenario(Scenario scenario) {
        driver.quit();
    }


}
