package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.InvalidArgumentException;

public class EventTask extends Task {
    private static final String INVALID_DATE_FORMAT = "eh the date/time format is wrong la, must be yyyy-mm-dd";
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";
    private static final String INVALID_FROM = "eh the event needs a start date/time";
    private static final String INVALID_TO = "eh the event needs a end date/time";

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an event task with the given name, start date, end date, and completion status.
     *
     * @param name   The name of the task.
     * @param from   The start date of the task.
     * @param to     The end date of the task.
     * @param isDone The completion status of the task.
     * @throws DateTimeException        If the date format is invalid.
     * @throws InvalidArgumentException If the name, start date, or end date is empty.
     */
    public EventTask(String name, String from, String to, boolean isDone)
            throws DateTimeException, InvalidArgumentException {
        super(name, isDone);
        if (name.isEmpty()) {
            throw new InvalidArgumentException(INVALID_NAME);
        }
        if (from.isEmpty()) {
            throw new InvalidArgumentException(INVALID_FROM);
        }
        if (to.isEmpty()) {
            throw new InvalidArgumentException(INVALID_TO);
        }

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            throw new DateTimeException(INVALID_DATE_FORMAT);
        }

    }

    /**
     * Constructs an event task with the given name, start date, and end date.
     *
     * @param name The name of the task.
     * @param from The start date of the task.
     * @param to   The end date of the task.
     * @throws DateTimeException        If the date format is invalid.
     * @throws InvalidArgumentException If the name, start date, or end date is empty.
     */
    public EventTask(String name, String from, String to) throws DateTimeException, InvalidArgumentException {
        this(name, from, to, false);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getName(), from, to);
    }
}
