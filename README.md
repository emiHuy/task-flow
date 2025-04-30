# TaskFlow

## Description
A simple to-do list application that helps users organize tasks and displays them in a sorted order.

## Project Metadata
**Author:** Emily <br>
**Date Created:** April 22, 2025 <br>
**Last Updated:** April 30, 2025 <br>

## Requirements
* Java 23
* JavaFX 21
* Recommended IDE: IntelliJ IDEA

## Features
- **Add tasks**: Create tasks with a title, priority level, due date, and category.
- **Add categories**: Create new categories to organize tasks.
- **Complete/Delete tasks**: Marks tasks as complete or removes them from the list.
- **Sort tasks**: Automatically sorts and displays tasks based on priority or due date.
- **Filter tasks**: View tasks by priority level or category for easier task management.
- **Save tasks locally**: Tasks are saved in a local file ('taskCollection.dat'), ensuring persistence between app sessions.
- **Load tasks from file**: When the app starts, tasks are loaded from the 'taskCollection.dat' file (if it exists), preserving previous entries.

## File and Folder Information
### Folders
#### controller/
- ToDoListApp.java → entry point and controller (event handlers).

#### model/
- Task.java → template for individual task objects.
- TaskCollection.java → collection of tasks.

#### view/
- ToDoListView.java → main view container.
- SideMenu.java → side menu containing buttons and components for displaying UI, and getting sorting and filter choices from the user.
##### view/panes/
- AddCategoryPane.java → pane containing input components for category creation.
- AddTaskPane.java → pane containing input components for task creation.
- TaskPane.java → pane for displaying tasks.
##### view/interfaces/
- ResettablePane.java → interface for input panes that need to be reset for the next use and hidden when not in use.
##### view/components/
- CategoryPane.java → pane containing category buttons for displaying tasks within a category; part of SideMenu.java.
- SideMenuButton.java → custom buttons for SideMenu.java.
- TaskCheckBox.java → custom checkboxes that hold a reference to their corresponding task objects. 
