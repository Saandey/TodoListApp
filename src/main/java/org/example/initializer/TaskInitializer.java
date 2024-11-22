package org.example.initializer;

import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskInitializer implements CommandLineRunner {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        // Добавляем начальные задачи
        taskRepository.save(new Task("", "Learn Spring Boot", LocalDate.of(2024,11,28), false));
        taskRepository.save(new Task(null, "Create a ToDo App",LocalDate.of(2024,11,29), false));
        taskRepository.save(new Task(null, "Upload project to GitHub", LocalDate.of(2024,11,30),false));

        System.out.println("Initial tasks added!");
    }
}
