package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ImportExamCommand;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ImportExamCommandParserTest {
    private ImportExamCommandParser parser = new ImportExamCommandParser();

    @Test
    public void parse_noArgsPassed_failure() {
        assertParseFailure(
                parser, "importExam ie/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportExamCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notCsvFile_failure() {
        assertParseFailure(
                parser, "importExam ie/file.json", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportExamCommand.MESSAGE_USAGE));
    }
}
