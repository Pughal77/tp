package seedu.address.storage;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface ExamBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getExamBookFilePath();

    /**
     * Returns ExamBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyAddressBook> readExamBook() throws DataLoadingException;

    /**
     * @see #getExamBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readExamBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExamBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveExamBook(ReadOnlyAddressBook)
     */
    void saveExamBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;
}
