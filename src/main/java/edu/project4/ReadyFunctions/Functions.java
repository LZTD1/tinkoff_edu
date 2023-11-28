package edu.project4.ReadyFunctions;

import edu.project4.Etinties.Coefficients;
import edu.project4.Etinties.Colors;
import edu.project4.Etinties.Function;
import edu.project4.Etinties.Parameters;
import edu.project4.Etinties.PostTransformation;
import edu.project4.Etinties.Variant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.FactorialAssets.FactorialFactory.HANDKERCHIEF;
import static edu.project4.FactorialAssets.FactorialFactory.LINEAR;

public class Functions {

    public final static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    public final static PostTransformation DEFAULT_PT_PARAMS = new PostTransformation(1, 0, 0, 0, 1, 0);
    public final static Coefficients DEFAULT_COEF_PARAMS = new Coefficients(1, 0, 0, 0, 1, 0);
    public final static Parameters DEFAULT_VARIANT_PARAMS = new Parameters(0, 0, 0, 0);
    public final static Parameters RANDOM_VARIANT_PARAMS =
        new Parameters(RANDOM.nextInt(2), RANDOM.nextInt(2), RANDOM.nextInt(2), RANDOM.nextInt(2));
    public final static Coefficients RANDOM_COEF_PARAMS = new Coefficients(
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0)
    );
    public final static PostTransformation RANDOM_PT_PARAMS = new PostTransformation(
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0),
        RANDOM.nextDouble(2.0)
    );

    private final List<Function> functions;

    public Functions(List<Function> functions) {
        this.functions = functions;
    }

    public Functions() {
        this.functions = new ArrayList<Function>() {{

            add(new Function(
                Colors.GREEN,
                DEFAULT_COEF_PARAMS,
                DEFAULT_PT_PARAMS,
                new ArrayList<Variant>() {{
                    add(new Variant(
                        1,
                        HANDKERCHIEF,
                        DEFAULT_VARIANT_PARAMS
                    ));
                    add(new Variant(
                        1,
                        LINEAR,
                        DEFAULT_VARIANT_PARAMS
                    ));
                }}
            ));
        }};
    }

    public Function getRandomFunction() {
        return this.functions.get(ThreadLocalRandom.current().nextInt(this.functions.size()));
    }
}
