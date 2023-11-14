package edu.hw6.Task6;

import com.opencsv.bean.CsvBindByName;

public class PortModel {
    @CsvBindByName(column = "Service Name")
    private String serviceName;

    @CsvBindByName(column = "Port Number")
    private String portNumber;

    @CsvBindByName(column = "Transport Protocol")
    private String transportProtocol;

    @CsvBindByName(column = "Description")
    private String description;

    public String getServiceName() {
        return serviceName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getDescription() {
        return description;
    }
}
