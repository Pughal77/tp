package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORT;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ImportCommand} object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    private static final String MESSAGE_NOT_CSV = "File %s is not a csv file \nImport command only accepts csv files";

    private static final String MESSAGE_INVALID_PATH = "Invalid file path: %s";
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IMPORT);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_IMPORT);
        if (!isPrefixPresent(
                argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
        Path path;
        try {
            path = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_IMPORT).orElse(""));
        } catch (InvalidPathException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_PATH, e.getReason()));
        }


        if (!isCsvFile(path)) {
            throw new ParseException(String.format(MESSAGE_NOT_CSV, path));
        }
        return new ImportCommand(path);
    }

    /**
     * Returns true if the prefix contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(CliSyntax.PREFIX_IMPORT).isPresent();
    }

    /**
     * Returns true if the file is a CSV file.
     * @param path the path of the file
     * @return true if the file is a CSV file
     */
    private static boolean isCsvFile(Path path) {
        return path.toString().endsWith(".csv");
    }
}
