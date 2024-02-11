package junjie.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import junjie.exceptions.InvalidArgumentException;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private static final String INVALID_DATE_FORMAT = "eh the date/time format is wrong la, must be yyyy-mm-dd";
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";
    private static final String INVALID_DEADLINE = "bro this task needs a deadline bro";
    private final LocalDate deadline;

    /**
     * Constructs a deadline task with the given name, deadline, and done status.
     *
     * @param name     The name of the task.
     * @param deadline The deadline of the task.
     * @param isDone   The done status of the task.
     * @throws DateTimeException        If the date format is invalid.
     * @throws InvalidArgumentException If the name or deadline is empty.
     */
    public DeadlineTask(String name, String deadline, boolean isDone)
            throws DateTimeException, InvalidArgumentException {
        super(name, isDone);
        if (name.isEmpty()) {
            throw new InvalidArgumentException(INVALID_NAME);
        }
        if (deadline.isEmpty()) {
            throw new InvalidArgumentException(INVALID_DEADLINE);
        }

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new DateTimeException(INVALID_DATE_FORMAT);
        }

    }

    /**
     * Constructs a deadline task with the given name and deadline.
     *
     * @param name     The name of the task.
     * @param deadline The deadline of the task.
     * @throws DateTimeException        If the date format is invalid.
     * @throws InvalidArgumentException If the name or deadline is empty.
     */
    public DeadlineTask(String name, String deadline) throws DateTimeException, InvalidArgumentException {
        this(name, deadline, false);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getName(), deadline);
    }
}