package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedExam {

    private final String examName;
    private final Score maxScore = new Score(0);

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code examName}.
     */
    @JsonCreator
    public JsonAdaptedExam(String examName) {
        this.examName = examName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        examName = source.name;
    }

    @JsonValue
    public String getExamName() {
        return examName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Exam toModelType() throws IllegalValueException {
        if (!Exam.isValidName(examName)){
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        // didnt check for maxScore as eventhough used in the constructor
        return new Exam(examName, maxScore);
    }

}
