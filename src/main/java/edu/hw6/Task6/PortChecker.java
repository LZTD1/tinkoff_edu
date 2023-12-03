package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortChecker {

    private final static Logger LOGGER = LogManager.getLogger();
    private List<Integer> availablePorts = new ArrayList<>();
    private final List<Integer> busyPorts = new ArrayList<>();
    private final static int MAX_PORT = 49151;

    public PortChecker() {
    }

    public List<Integer> getBusyPorts() {
        return checkAvailablePorts();
    }

    private void fullingAvailablePorts() {
        availablePorts = new ArrayList<>();
        for (int i = 1; i <= MAX_PORT; i++) {
            availablePorts.add(i);
        }
    }

    private List<Integer> checkAvailablePorts() {
        fullingAvailablePorts();
        for (int i = 0; i < availablePorts.size(); i++) {
            boolean resultServerSocket = checkCurrentPortServerSocket(availablePorts.get(i));
            boolean resultDatagramSocket = checkCurrentPortDatagramSocket(availablePorts.get(i));
            if (!resultServerSocket || !resultDatagramSocket) {
                busyPorts.add(availablePorts.get(i));
                availablePorts.remove(i);
            }
        }
        return busyPorts;
    }

    private boolean checkCurrentPortServerSocket(Integer port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean checkCurrentPortDatagramSocket(Integer port) {
        try (var socket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    public void writeResult(List<Integer> ports) {
        LOGGER.error("PORT | SERVICE | DESCRIPTION");
        try (var pa = new PortAssigner()) {
            for (Integer port : ports) {
                Optional<PortModel> result = pa.getPortAssign(port);
                if (result.isPresent()) {
                    PortModel data = result.get();
                    LOGGER.info("{} | {} | {}", port, data.getServiceName(), data.getDescription());
                } else {
                    LOGGER.info("{} | unknown | unknown", port);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
