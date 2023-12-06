package edu.hw10.Task1;

import edu.hw10.Task1.Classes.SimpleClass;
import edu.hw10.Task1.Classes.SimpleClassWithFabric;
import edu.hw10.Task1.Classes.SimpleClassWithFabricAndParams;
import edu.hw10.Task1.Classes.SimpleClassWithFabricError;
import edu.hw10.Task1.Classes.SimpleClassWithParams;
import edu.hw10.Task1.Classes.SimpleRecord;
import edu.hw10.Task1.Exceptions.MethodIsNofAFabricError;
import edu.hw10.Task1.Exceptions.MethodIsNotFindedError;
import edu.hw10.Task1.Exceptions.NoInformationForParametersError;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTask1 {

    @Test
    void createSimpleClass() {
        var rog = new RandomObjectGenerator();

        var object = rog.nextObject(SimpleClass.class);

        assertThat(object).isInstanceOf(SimpleClass.class);
    }

    @Test
    void createSimpleClassWithParams() {
        var rog = new RandomObjectGenerator();

        var object = rog.nextObject(SimpleClassWithParams.class);

        assertThat(object).isInstanceOf(SimpleClassWithParams.class);
    }

    @Test
    void createSimpleRecord() {
        var rog = new RandomObjectGenerator();

        var object = rog.nextObject(SimpleRecord.class);

        assertThat(object).isInstanceOf(SimpleRecord.class);
    }

    @Test
    void createSimpleClassWithFabric() {
        var rog = new RandomObjectGenerator();

        var object = rog.nextObject(SimpleClassWithFabric.class, "fabricMethod");

        assertThat(object).isInstanceOf(SimpleClassWithFabric.class);
    }

    @Test
    void createSimpleClassWithFabricError() {
        var rog = new RandomObjectGenerator();

        assertThrows(
            MethodIsNofAFabricError.class,
            () -> rog.nextObject(SimpleClassWithFabric.class, "getRandValues")
        );
    }

    @Test
    void createSimpleClassWithFabricAndParams() {
        var rog = new RandomObjectGenerator();

        var object = rog.nextObject(SimpleClassWithFabricAndParams.class, "fabricMethod");

        assertThat(object).isInstanceOf(SimpleClassWithFabricAndParams.class);
    }

    @Test
    void createSimpleClassWithFabricAndParams_MethodIsNotFindedError() {
        var rog = new RandomObjectGenerator();

        assertThrows(
            MethodIsNotFindedError.class,
            () -> rog.nextObject(SimpleClassWithFabric.class, "amogus")
        );
    }

    @Test
    void createSimpleClassWithFabricAndParams_NoInformationForParametersError() {
        var rog = new RandomObjectGenerator();

        assertThrows(
            NoInformationForParametersError.class,
            () -> rog.nextObject(SimpleClassWithFabricError.class, "fabricMethod")
        );
    }

}
