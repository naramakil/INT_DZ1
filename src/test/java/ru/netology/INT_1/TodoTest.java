package ru.netology.INT_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TodoTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSimpleTask() {
        Todos todos = new Todos();
        SimpleTask simple = new SimpleTask(1, "Простая задача");
        todos.add(simple);

        Task[] result = todos.search("задача");
        assertArrayEquals(new Task[]{simple}, result);
    }

    @Test
    public void testSearchEpicWithSubtask() {
        Todos todos = new Todos();
        Epic epic = new Epic(2, new String[]{"Подзадача 1", "Подзадача 2"});
        todos.add(epic);

        Task[] result = todos.search("2");
        assertArrayEquals(new Task[]{epic}, result);
    }

    @Test
    public void testSearchMeetingByTopic() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Обсуждение проекта", "Проект X", "2023-01-01");
        todos.add(meeting);

        Task[] result = todos.search("проекта");
        assertArrayEquals(new Task[]{meeting}, result);
    }

    @Test
    public void testSearchMeetingByProject() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Обсуждение", "Проект Y", "2023-01-01");
        todos.add(meeting);

        Task[] result = todos.search("Y");
        assertArrayEquals(new Task[]{meeting}, result);
    }
}
