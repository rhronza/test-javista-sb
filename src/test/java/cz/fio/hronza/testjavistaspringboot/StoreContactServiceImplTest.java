package cz.fio.hronza.testjavistaspringboot;

import cz.fio.hronza.testjavistaspringboot.business_logic.CommonServiceImpl;
import cz.fio.hronza.testjavistaspringboot.business_logic.StoreContactServiceImpl;
import cz.fio.hronza.testjavistaspringboot.exception.TestJavistaConversionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreContactServiceImplTest {

    public static final String FIRST_NAME = "roman";
    public static final String LAST_NAME = "hronza";
    public static final String EMAIL = "roman.hronza@email.com";

    @Mock
    private CommonServiceImpl commonServiceImpl;
    @InjectMocks
    private StoreContactServiceImpl storeContactService;

    @Test
    void storeContactNegativeTest() {
        when(commonServiceImpl.isFoundContact(any(), anyInt(), any(), anyInt(), any(), anyInt(), any()))
                .thenThrow(new TestJavistaConversionException(
                        "Exception occurred when converting inputs to CP1250", "firstName", "lastName", "email"));
        assertThrows(TestJavistaConversionException.class, () -> storeContactService.storeContact(FIRST_NAME, LAST_NAME, EMAIL));
    }


}
