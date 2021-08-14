package SocialFb.Suppliers;

import SocialFb.Enums.ContactTypeEnum;
import SocialFb.Models.Contact;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ContactSupplier extends AbstractSupplier<Contact> {


    public Set<Contact> supplyMany (int count) {

        Set<Contact> contacts = new HashSet<>();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                contacts.add(this.supplyOne());
            }
        }

        return contacts;
    }

    public Contact supplyOne () {
        return Contact.builder()
                .type(ContactTypeEnum.PHONE)
                .value(faker.phoneNumber().phoneNumber())
                .build();
    }
}
