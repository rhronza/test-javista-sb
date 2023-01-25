package cz.fio.hronza.testjavistaspringboot.business_logic;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class StoreContactServiceImpl implements StoreContactService {

    public static final String FIRST_NAME = "jmeno";
    public static final String LAST_NAME = "pří-jmení-";
    public static final String EMAIL = "email@email.cz";
    public static final String NAME_CSV_FILE = "contacts.csv";
    public static final String CHARSET_NAME = "CP1250";

    private final Logger log = LoggerFactory.getLogger(StoreContactServiceImpl.class);

    @Override
    public void storeContact(String firstName, String lastName, String email) throws IOException {
        if (!isFound(firstName, lastName, email, readContacts())) {
            addNewContact(firstName, lastName, email);
            log.info("Contact [{}, {}, {}] was added.", firstName, lastName, email);
        } else
            log.error("Contact [{}, {}, {}] already exists.", firstName, lastName, email);
    }

    private void addNewContact(String firstName, String lastName, String email) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(NAME_CSV_FILE, true))) {
            out.write(String.format("%s,%s,%s", firstName, lastName, email));
            out.newLine();
        } catch (IOException ex) {
            log.error("IO exception occured", ex);
        }
    }

    private boolean isFound(String firstName, String lastName, String email, Iterable<CSVRecord> contacts) {
        AtomicBoolean isFound = new AtomicBoolean(false);
        contacts.forEach(csvrecord -> {
            if (csvrecord.get(0).equals(firstName) && csvrecord.get(1).equals(lastName) && csvrecord.get(2).equals(email)) {
                isFound.set(true);
            }
        });
        return isFound.get();
    }

    private Iterable<CSVRecord> readContacts() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(NAME_CSV_FILE), CHARSET_NAME));
        String[] header = {FIRST_NAME, LAST_NAME, EMAIL};
        return CSVFormat.DEFAULT
                .withHeader(header)
                .withFirstRecordAsHeader()
                .parse(br);
    }
}
