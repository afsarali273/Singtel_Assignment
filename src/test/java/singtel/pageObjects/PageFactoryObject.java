package singtel.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryObject {
   TodoListPage todoListPage;
    /*** Getters ***/

    public TodoListPage getTodoListPage() {
        return todoListPage;
    }

    public PageFactoryObject(WebDriver driver){
        todoListPage = new TodoListPage(driver);


    }





}
