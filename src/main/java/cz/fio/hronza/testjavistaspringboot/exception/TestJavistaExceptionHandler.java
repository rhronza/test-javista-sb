package cz.fio.hronza.testjavistaspringboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class TestJavistaExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(TestJavistaExceptionHandler.class);

    @ExceptionHandler(TestJavistaConversionException.class)
    public void testJavistaConversionErrorHandler(TestJavistaConversionException ex) {
        String fields = Arrays.toString(ex.getStrings());
        log.error(ex.getErrorMessage() + " {}", fields);
    }
}
