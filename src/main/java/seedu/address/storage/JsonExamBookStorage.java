package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonExamBookStorage implements ExamBookStorage {
    private static final Logger logger = LogsCenter.getLogger(seedu.address.storage.JsonAddressBookStorage.class);

    private Path filePath;

    private ExamBookStorage examBookStorage;

    public JsonExamBookStorage(Path filePath) {
        this.filePath = filePath;
    }
    public Path getExamBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readExamBook() throws DataLoadingException {
        return readExamBook(filePath);
    }

    /**
     * Similar to {@link #readExamBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyAddressBook> readExamBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveExamBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveExamBook(addressBook, filePath);
    }
    public void saveExamBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
//        JsonUtil.saveJsonFile(new JsonSerializableExamBook(addressBook), filePath);
    }
}
