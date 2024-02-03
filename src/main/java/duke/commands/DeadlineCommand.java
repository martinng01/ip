package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.DeadlineTask;
import duke.tasks.Task;

import java.time.DateTimeException;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * The command word to add a deadline task.
     */
    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String deadline;

    /**
     * Constructs a command to add a deadline task with the given name and deadline.
     *
     * @param name The name of the deadline task.
     * @param deadline The deadline of the task.
     */
    public DeadlineCommand(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = new DeadlineTask(name, deadline);
            tasks.add(task);
            ui.print(String.format(MESSAGE, task));
        } catch (InvalidArgumentException | DateTimeException e) {
            ui.print(e.getMessage());
        }
    }
}
