package edu.hw6.Task6;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

public class PortAssigner implements AutoCloseable {

    private final FileReader reader;
    private final List<PortModel> portsModels;

    public PortAssigner() throws FileNotFoundException {
        this.reader =
            new FileReader("src/main/resources/ports.csv"); // file from https://www.iana.org/assignments/service-names-port-numbers/
        CsvToBean<PortModel> csvToBean = new CsvToBeanBuilder<PortModel>(this.reader)
            .withType(PortModel.class)
            .withIgnoreLeadingWhiteSpace(true)
            .build();
        this.portsModels = csvToBean.parse();
    }

    public Optional<PortModel> getPortAssign(int port) {
        var convertedPort = String.valueOf(port);
        return this.portsModels.stream()
            .filter(model -> model.getPortNumber().equals(convertedPort))
            .findAny();
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }
}
