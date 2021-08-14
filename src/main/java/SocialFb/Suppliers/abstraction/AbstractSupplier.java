package SocialFb.Suppliers.abstraction;

import com.github.javafaker.Faker;

import java.util.Set;


public abstract class AbstractSupplier<T> {


    public Faker faker = new Faker();

    public abstract Set<T> supplyMany (int count);

    public abstract T supplyOne ();
}
