package navarich.models;

import navarich.helpers.stringModification.IStringModification;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class Person {
    private String name;

    private String surname;

    private String address;

    private String number;

    private String city;

    public Person(File file, IStringModification mistake, boolean isMakeMistake) throws IllegalAccessException {
        this.name = getRandomItem(file.getNameArr());
        this.surname = getRandomItem(file.getSurnameArr());
        this.address = getRandomItem(file.getAddressyArr());
        this.number = getRandomItem(file.getNumberArr());
        this.city = getRandomItem(file.getCityArr());

        if (isMakeMistake)
            MakeMistakeInRandomField(mistake);
    }

    private String getRandomItem(List<String> list) {
        Random rnd = new Random();

        return list.get(rnd.nextInt(list.size()));
    }

    private void MakeMistakeInRandomField(IStringModification modification) throws IllegalAccessException {
        Random rnd = new Random();

        Field mistakeField = getClass().getDeclaredFields()[rnd.nextInt(4)];
        mistakeField.set(this, makeMistake((String) mistakeField.get(this), modification));
    }

    private static String makeMistake(String input, IStringModification modification) {
        String result;
        StringBuilder stringBuilder = new StringBuilder(input);

        return modification.Modification(input, stringBuilder);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return ("'" + name + '\'' +
                ", '" + surname + '\'' +
                ", '" + address + '\'' +
                ", '" + number + '\'' +
                ", '" + city + "\'\n");
    }
}
