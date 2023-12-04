package edu.hw6;

import edu.hw6.Task6.PortAssigner;
import edu.hw6.Task6.PortChecker;
import edu.hw6.Task6.PortModel;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TestTask6 {
    @Test
    void checkBusyPorts(){
        PortChecker portScanner = new PortChecker();
        List<Integer> res = portScanner.getBusyPorts();

        assertThat(res).isNotEmpty();
    }
    @Test
    void checkPortAssign(){
        int port = 3740;

        try(var pa = new PortAssigner()) {
            Optional<PortModel> result = pa.getPortAssign(3740);

            assertThat(result.isEmpty()).isFalse();
            assertThat(result.get().getServiceName()).isEqualTo("heartbeat");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void checkWriteData(){
        PortChecker portChecker = new PortChecker();
        List<Integer> busyPorts = portChecker.getBusyPorts();
        portChecker.writeResult(busyPorts);
    }
}
