package navarich.models;

import navarich.helpers.stringModification.AddExtraSymbol;
import navarich.helpers.stringModification.IStringModification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {
    private List<String> cityArr = new ArrayList<>();
    private List<String> nameArr = new ArrayList<>();
    private List<String> surnameArr = new ArrayList<>();
    private List<String> addressyArr = new ArrayList<>();
    private List<String> numberArr = new ArrayList<>();

    public File(String location) throws IOException {
        this.cityArr = File.ReadFileByLine(getClass().getResourceAsStream("/data/" + location + "_cities.txt"));
        this.nameArr = File.ReadFileByLine(getClass().getResourceAsStream("/data/" + location + "_name_m.txt"));
        this.surnameArr = File.ReadFileByLine(getClass().getResourceAsStream("/data/" + location + "_surname_m.txt"));
        this.addressyArr = File.ReadFileByLine(getClass().getResourceAsStream("/data/" + location + "_streets.txt"));
        this.numberArr = File.ReadFileByLine(getClass().getResourceAsStream("/data/" + location + "_numbers.txt"));
    }

    public List<String> getCityArr() {
        return cityArr;
    }

    public List<String> getNameArr() {
        return nameArr;
    }

    public List<String> getSurnameArr() {
        return surnameArr;
    }

    public List<String> getAddressyArr() {
        return addressyArr;
    }

    public List<String> getNumberArr() {
        return numberArr;
    }

    public static List<String> ReadFileByLine(InputStream inputStream) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String buffer;
            while ((buffer = in.readLine()) != null) {
                result.add(buffer);
            }
        }

        return result;
    }

    public static void WriteGeneratedArrayToFile(Mistake mistake, int iterations, String location)
            throws IOException, IllegalAccessException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int errorNumber = 0;
        File file = new File(location);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < iterations; i++) {
            int index = mistake.getErrorRows().indexOf(i);
            boolean isNeedError = index >= 0;
            IStringModification modification = mistake.getErrorArray().size() != 0
                    ? mistake.getErrorArray().get(errorNumber)
                    : new AddExtraSymbol();

            out.write(new Person(file, modification, isNeedError).toString());


            if (isNeedError && errorNumber + 1 < mistake.getErrorArray().size()) {
                mistake.getErrorRows().remove(index);
                errorNumber++;
            }
        }

        out.flush();
    }
}
