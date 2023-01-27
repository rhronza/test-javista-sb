package cz.fio.hronza.testjavistaspringboot.business_logic;

import cz.fio.hronza.testjavistaspringboot.exception.TestJavistaConversionException;
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CommonServiceImpl implements CommonService {

    private final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
    public static final String CHARSET_NAME = "CP1250";

    @Override
    public boolean isFoundContact(String firstName, int firstNameIndex, String lastName, int lastNameIndex, String email, int emailIndex, Iterable<CSVRecord> contacts) {
        AtomicBoolean isFound = new AtomicBoolean(false);
        for (CSVRecord csvrecord : contacts) {
            String firstName1250;
            String lastName1250;
            String email1250;
            try {
                firstName1250 = new String(firstName.getBytes(StandardCharsets.UTF_8), CHARSET_NAME);
                lastName1250 = new String(lastName.getBytes(StandardCharsets.UTF_8), CHARSET_NAME);
                email1250 = new String(email.getBytes(StandardCharsets.UTF_8), CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                throw new TestJavistaConversionException("Exception occurred when converting inputs to CP1250", firstName, lastName, email);
            }
            if (csvrecord.get(firstNameIndex).equals(firstName1250)
                    && csvrecord.get(lastNameIndex).equals(lastName1250)
                    && csvrecord.get(emailIndex).equals(email1250)) {
                isFound.set(true);
            }
        }
        return isFound.get();
    }

    @Override
    public Iterable<CSVRecord> readContacts(RecordAttributte firstName, RecordAttributte lastName, RecordAttributte email, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), CHARSET_NAME));
        String[] header = {firstName.name(), lastName.name(), email.name()};
        return CSVFormat.DEFAULT
                .withHeader(header)
                .withFirstRecordAsHeader()
                .parse(br);
    }

    public void addNewContact(String firstName, String lastName, String email, String fileName) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {
            out.write(String.format("%s,%s,%s", firstName, lastName, email));
            out.newLine();
        } catch (IOException ex) {
            log.error("IO exception occured", ex);
        }
    }

}
