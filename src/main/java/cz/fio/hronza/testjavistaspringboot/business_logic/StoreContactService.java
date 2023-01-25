package cz.fio.hronza.testjavistaspringboot.business_logic;


import java.io.IOException;

public interface StoreContactService {
    void storeContact(String firstName, String lastName, String email) throws IOException;
}
