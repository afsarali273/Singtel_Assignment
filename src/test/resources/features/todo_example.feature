Feature: TODO Feature for remembering things
"""
In order to	remember the things	I want to do, as an ToDo MVC user,
I want to manage my todo list
"""

  Scenario: I shall be able to create new Todo lists
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    Then I shall see 3 new Todo list is being added in the list
    And verify numbers of todo as 3 in the bottom text

  Scenario: I shall be able to mark todo as completed
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    Then I mark all todo list as completed
    And verify numbers of todo as 0 in the bottom text
    And I shall see all todo list with tick and strike out
    When I click on the active button
    Then I shall see 0 active todo records
    When I click on the completed button
    Then I shall see 3 completed todo records

  Scenario: I shall be able to delete todo list
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    And I mark all todo list as completed
    And I click on the ClearCompleted button
    Then I shall see 0 completed todo records


  Scenario: I shall be able to clear only completed todo list
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    And I mark first_two todo list as completed
    And I click on the ClearCompleted button
    Then I shall see 1 active todo records

  Scenario: I shall be able to undo completed todo list
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    And I mark all todo list as completed
    And I click on the active button
    Then I shall see 0 active todo records
    And I click on the all button
    Then I Undo below todo list from completed
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
    And I click on the active button
    Then I shall see 2 active todo records

  Scenario: I shall be able to edit todo list
    Given I am on the todo page
    When I insert my todo tasks in the input field and press enter
      | This is my First Task to complete.  |
      | This is my Second Task to complete. |
      | This is my Third Task to complete.  |
    And I Edit below todo list from completed
      | This is my First Task to complete.-> This is my First Edited Task to complete. |
      | This is my Second Task to complete.-> This is my Second Edited Task to complete. |
    And I click on the all button
    Then I shall see 3 active todo records
    And I shall see below todo lists
      | This is my First Edited Task to complete.  |
      | This is my Second Edited Task to complete. |
      | This is my Third Task to complete.         |