# Task Management Application

A full-stack task management system built with Spring Boot (backend) and React (frontend), allowing users to register, login, and manage their personal todo lists.

## Features

- **User Authentication**: Secure user registration and login
- **Todo Management**: Create, read, update, delete todos
- **Task Status**: Mark todos as completed or pending
- **Responsive UI**: Modern, responsive interface using Bootstrap
- **RESTful API**: Well-structured backend API with proper HTTP methods
- **Database Integration**: MySQL database with JPA/Hibernate
- **Security**: Password encryption and basic security configuration(It will be replaced with JWT later).

## Tech Stack

### Backend

- **Java**: 21
- **Spring Boot**: 4.0.1
- **Database**: MySQL
- **ORM**: JPA/Hibernate
- **Security**: Spring Security
- **Build Tool**: Maven

### Frontend

- **React**: 19.2.0
- **Build Tool**: Vite
- **Styling**: Bootstrap 5.3.8
- **HTTP Client**: Axios
- **Routing**: React Router DOM
- **Notifications**: React Hot Toast

## Prerequisites

Before running this application, make sure you have the following installed:

- **Java**: JDK 21 or higher
- **Node.js**: Version 18 or higher
- **MySQL**: Version 8.0 or higher
- **Maven**: 3.6+ (usually comes with Spring Boot)
- **Git**: For cloning the repository

## Installation and Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd task-management-application
```

### 2. Backend Setup

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Configure the database:

   - Create a MySQL database named `todosDB`
   - Update database credentials in `src/main/resources/application.properties` if needed:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/todosDB
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. Build and run the backend:

   ```bash
   # Using Maven wrapper (recommended)
   ./mvnw spring-boot:run

   # Or using Maven
   mvn spring-boot:run
   ```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

1. Open a new terminal and navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

The frontend will start on `http://localhost:5173`

## Usage

1. Open your browser and go to `http://localhost:5173`
2. Register a new account or login with existing credentials
3. Once logged in, you can:
   - View your todo list
   - Add new todos with descriptions and target dates
   - Update existing todos
   - Mark todos as completed
   - Delete todos

## API Documentation

### Authentication Endpoints

- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/register` - User registration

### Todo Endpoints

- `GET /api/v1/todos` - Get all todos for authenticated user
- `GET /api/v1/todos/{id}` - Get specific todo by ID
- `POST /api/v1/todos` - Create new todo
- `PUT /api/v1/todos/{id}` - Update existing todo
- `PUT /api/v1/todos/mark/{id}` - Mark todo as done/undone
- `DELETE /api/v1/todos/{id}` - Delete todo

**Note**: Most endpoints require authentication via `useremail` header.(Just for now, would be replaced with JWT authentication).

## Project Structure

```
task-management-application/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/naveen/springboot/task_management_system/
│   │   │   │   ├── controller/     # REST controllers
│   │   │   │   ├── entity/         # JPA entities
│   │   │   │   ├── repository/     # Data repositories
│   │   │   │   ├── service/        # Business logic
│   │   │   │   ├── security/       # Security configuration
│   │   │   │   ├── exception/      # Exception handling
│   │   │   │   └── dto/            # Data transfer objects
│   │   │   └── resources/          # Application properties
│   │   └── test/                   # Unit tests
│   ├── pom.xml                     # Maven configuration
│   └── README.md
├── frontend/
│   ├── src/
│   │   ├── components/             # React components
│   │   ├── security/               # Authentication context
│   │   ├── api/                    # API client
│   │   └── assets/                 # Static assets
│   ├── package.json                # NPM configuration
│   ├── vite.config.js              # Vite configuration
│   └── README.md
└── README.md                       # This file
```

## Acknowledgments

- Spring Boot for the robust backend framework
- React for the dynamic frontend library
- Bootstrap for the responsive UI components
- MySQL for reliable database management
