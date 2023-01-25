package cz.fio.hronza.testjavistaspringboot.rest;

import cz.fio.hronza.testjavistaspringboot.business_logic.StoreContactService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/test-javista")
public class StoreContact {

    private final StoreContactService storeContactService;

    public StoreContact(StoreContactService storeContactService) {
        this.storeContactService = storeContactService;
    }

    @GetMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public void storeContact(@RequestParam(value = "firstName") @NotNull String firstName, @RequestParam(value = "lastName") @NotNull String lastname, @RequestParam(value = "email") @NotNull @Email String email) {
        storeContactService.storeContact(firstName, lastname, email);
    }
}
