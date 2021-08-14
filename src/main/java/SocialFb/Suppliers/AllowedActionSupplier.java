package SocialFb.Suppliers;

import SocialFb.Models.ActionType;
import SocialFb.Models.AllowedAction;
import SocialFb.Suppliers.abstraction.AbstractSupplier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AllowedActionSupplier extends AbstractSupplier<AllowedAction> {
    @Override
    public Set<AllowedAction> supplyMany (int count) {
        Set<AllowedAction> allowedActions = new HashSet<>();
        for ( int i = 0; i < count; i++ ) {
            allowedActions.add(this.supplyOne());
        }
         return allowedActions;
    }

    @Override
    public AllowedAction supplyOne () {
        return AllowedAction.builder()
                .actionType(
                        ActionType
                                .builder()
                                .type(this.faker.rickAndMorty().location())
                                  .build()
                ).activated(this.faker.random().nextBoolean()).build();
    }
}
