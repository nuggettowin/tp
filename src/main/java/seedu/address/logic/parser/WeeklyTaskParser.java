package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Locale;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Attendance;

/**
 * Validates the shared student index and week arguments for attendance commands.
 */
public class WeeklyTaskParser {

    private final String noWeek;
    private final String usage;
    private final Index index;
    private final int week;

    /**
     * Parses arguments using the calling command's usage in format errors.
     *
     * @param args Arguments following the command word.
     * @param usage Usage instructions for the calling command.
     * @param missingWeekMessage Message in event that no week is detected
     * @throws NullPointerException If any argument is null.
     * @throws ParseException If required information is missing or invalid.
     */
    public WeeklyTaskParser(String args, String usage, String missingWeekMessage) throws ParseException {
        requireNonNull(usage);
        requireNonNull(missingWeekMessage);
        this.usage = usage;
        this.noWeek = missingWeekMessage;

        requireNonNull(args);
        ArgumentMultimap arguments = ArgumentTokenizer.tokenize(normalizeArguments(args), PREFIX_WEEK);
        index = extractIndex(arguments);
        week = extractWeek(arguments);
    }

    /**
     * Extracts the index from user's string input.
     *
     * @param argMultimap Tokenized command arguments.
     * @return The validated displayed student index.
     * @throws ParseException If the index is missing or invalid.
     */
    private Index extractIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, usage), pe);
        }
    }

    /**
     * Extracts the studio week value.
     *
     * @param argMultimap Tokenized command arguments.
     * @return The valid week integer.
     * @throws ParseException If the week is missing, repeated, or invalid.
     */
    private int extractWeek(ArgumentMultimap argMultimap) throws ParseException {
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_WEEK);
        String week = argMultimap.getValue(PREFIX_WEEK)
                .orElseThrow(() -> new ParseException(noWeek));
        try {
            return ParserUtil.parseWeek(week);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, usage), pe);
        }
    }

    /**
     * Normalizes whitespace and prefix case for this numeric-only command.
     * The leading space allows the tokenizer to detect an initial prefix.
     *
     * @param args Original command arguments.
     * @return Arguments suitable for the existing tokenizer.
     */
    private String normalizeArguments(String args) {
        return " " + args.strip().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    /**
     * Returns the validated displayed student index.
     *
     * @return The student index.
     */
    public Index getIndex() {
        return index;
    }

    /**
     * Returns the validated studio week.
     *
     * @return The studio week.
     */
    public int getWeek() {
        return week;
    }
}
