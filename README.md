# TaskManagerApp

_**A JavaFX task manager application**_

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)

## Introduction

TaskManagerApp is a JavaFX application developed as a project for Object-Oriented Programming and Databases course.The application allows users to efficiently manage their tasks by providing features such as task creation, viewing, updating, deleting, tagging, and commenting. Users can sign up for an account, log in, and start organizing their tasks seamlessly

## Features

- **User Authentication:** Users need to sign up with a unique username and password. Once logged in, they can access the full functionality of the application.

- **Task Management:**
  - **Create Tasks:** Users can create new tasks with detailed information, including name, description, due date, priority (low, medium, high), and time spent.
  - **View Tasks:** Users can view all their tasks and see relevant details.
  - **Update Tasks:** Users can edit and update existing tasks.
  - **Delete Tasks:** Users can delete existing tasks.
  - **Tagging:** Tasks can be tagged with labels such as "done," "in progress," or "not done" to track their status.
  - **Comments:** Users can add comments to tasks to provide additional context or updates.

- **Signup Process:**
  - During the signup process, users are prompted to choose a category (e.g., school, work, personal) and name their project. This helps in organizing tasks effectively.
 
- **Admin Functionality:**
  - An admin user has the capability to view all categories and projects selected.

## Installation

Follow these steps to set up and run the TaskManagerApp in IntelliJ IDEA:
1. **Intstall Git:**
   Ensure that Git is installed on your machine. If not, download and install Git from [https://git-scm.com/](https://git-scm.com/).

2. **Open IntelliJ IDEA:**
   Open IntelliJ IDEA and make sure the Git plugin is installed. If not, you can install it from the JetBrains Plugin Repository.
   
3. **Clone the repository:**
    Click on "Get from Version Control" on the welcome screen or go to `VCS > Get from Version Control` in the menu.
    In the "Get from Version Control" dialog, enter the URL of the GitHub repository you want to clone
   ```bash
      https://github.com/dariaungureanu/TaskManagerApp.git

4. **Set up dependencies and configurations for JavaFX and SceneBuilder:**

    Ensure that you have the necessary dependencies and configurations set up for JavaFX and SceneBuilder in your IntelliJ IDEA environment.
   - Open the project in IntelliJ IDEA.
   - Go to `File > Project Structure`.
   - In the Project Structure dialog, navigate to `Project > Project`.
   - Set the Project SDK to a version of Java compatible with your JavaFX project.
   - If JavaFX is not detected automatically, you may need to add it manually:
     - Go to `Libraries` and add the JavaFX library.
     - Make sure to include the necessary JAR files.

6. **Connect the application to a PostgreSQL database:**

    Use the provided DBeaver configuration to connect the application to a PostgreSQL database. Update the database connection details in the application configuration if necessary.

7. **Run the application:**

   - Locate the main class of your JavaFX application.
   - Right-click on the main class file and select `Run`.
   
Now, your TaskManagerApp should be up and running in IntelliJ IDEA. If you encounter any issues or have specific dependencies, ensure they are configured correctly in your project.

## Usage

1. **Launch the application:**

   Open the application in your preferred JavaFX environment.

2. **Sign up or log in:**

   - Sign up with a unique username and password if you are a new user.
   - Sign up as an admin using the username `admin` and the password `12345`.
   - Log in with your existing credentials if you already have an account.

3. **Manage Tasks:**

   - Create, view, and update tasks based on your requirements.
   - Utilize tagging and commenting features to enhance task organization.

4. **Admin Functionality (Under Development):**

   - For admin users, explore the admin functionality to view categories and projects.

## Documentation

_**Class Diagram**_
![Class Diagram](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/5586bdb0-9fed-42e5-90c9-895da899ffbb)

_**Database Project**_
![Database Project](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/3d7d4cf2-e57d-438c-ab0b-bce23e8a4b4e)

_**Interface View**_


  -_Log In Page_
  
![image](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/92469857-2d7e-4964-bd73-87c76a387d85)


  -_Create Project Page_
  
![image](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/14c3faed-3465-42b7-93e2-3454528cb65f)


  -_Create Task Page_
  
![image](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/99550af6-5eb9-4ef6-a852-ddefc63c6a46)


  -_View Tasks Page_
  
![image](https://github.com/dariaungureanu/TaskManagerApp/assets/131611604/cec56ed7-ec39-4d39-8b70-d5d159993122)
