package cz.fio.hronza.testjavistaspringboot.exception;

public class TestJavistaConversionException extends RuntimeException {

    final String[] strings;
    final String errorMessage;


    public TestJavistaConversionException(String errorMessage, String... strings) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.strings = strings;
    }

    public String[] getStrings() {
        return strings;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
