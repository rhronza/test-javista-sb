package cz.fio.hronza.testjavistaspringboot.business_logic;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;

public interface CommonService {
    boolean isFoundContact(String firstName, int firstNameIndex, String lastName, int lastNameIndex, String email, int emailIndex, Iterable<CSVRecord> contacts);

    Iterable<CSVRecord> readContacts(RecordAttributte firstName, RecordAttributte lastName, RecordAttributte email, String fileName) throws IOException;

    void addNewContact(String firstName, String lastName, String email, String fileName);
}
