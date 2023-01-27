package cz.fio.hronza.testjavistaspringboot.business_logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StoreContactServiceImpl implements StoreContactService {

    public static final RecordAttributte FIRST_NAME = new RecordAttributte("jmeno", 0);
    public static final RecordAttributte LAST_NAME = new RecordAttributte("příj-mení-", 1);
    public static final RecordAttributte EMAIL = new RecordAttributte("email@email.cz", 2);

    public static final String NAME_CSV_FILE = "contacts.csv";

    private final Logger log = LoggerFactory.getLogger(StoreContactServiceImpl.class);

    private final CommonService commonService;

    public StoreContactServiceImpl(CommonServiceImpl commonServiceImpl) {
        this.commonService = commonServiceImpl;
    }

    @Override
    public void storeContact(String firstNameSearched, String lastNameSearched, String emailSearched) throws IOException {
        if (!commonService.isFoundContact(
                firstNameSearched, FIRST_NAME.index(),
                lastNameSearched, LAST_NAME.index(),
                emailSearched, EMAIL.index(),
                commonService.readContacts(FIRST_NAME, LAST_NAME, EMAIL, NAME_CSV_FILE))) {
            commonService.addNewContact(firstNameSearched, lastNameSearched, emailSearched, NAME_CSV_FILE);
            log.info("Contact [{}, {}, {}] was added.", firstNameSearched, lastNameSearched, emailSearched);
        } else
            log.error("Contact [{}, {}, {}] already exists.", firstNameSearched, lastNameSearched, emailSearched);
    }


}
