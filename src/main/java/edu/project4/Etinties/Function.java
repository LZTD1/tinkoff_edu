package edu.project4.Etinties;

import java.util.ArrayList;
import java.util.List;
import static edu.project4.FactorialAssets.FactorialFactory.BUBBLE;
import static edu.project4.FactorialAssets.FactorialFactory.DIAMOND;
import static edu.project4.FactorialAssets.FactorialFactory.HANDKERCHIEF;
import static edu.project4.ReadyFunctions.Functions.DEFAULT_COEF_PARAMS;
import static edu.project4.ReadyFunctions.Functions.DEFAULT_PT_PARAMS;
import static edu.project4.ReadyFunctions.Functions.DEFAULT_VARIANT_PARAMS;

public record Function(Colors color,
                       Coefficients coefficients,
                       PostTransformation postTransformation,
                       List<Variant> variants
) {
    public static final Function DEFAULT_PRESET_DIAMOND = new Function(
        Colors.BLUE,
        DEFAULT_COEF_PARAMS,
        DEFAULT_PT_PARAMS,
        new ArrayList<Variant>() {{
            add(new Variant(
                1,
                DIAMOND,
                DEFAULT_VARIANT_PARAMS
            ));
        }}
    );
    public static final Function DEFAULT_PRESET_BUBBLE = new Function(
        Colors.GREEN,
        DEFAULT_COEF_PARAMS,
        DEFAULT_PT_PARAMS,
        new ArrayList<Variant>() {{
            add(new Variant(
                1,
                BUBBLE,
                DEFAULT_VARIANT_PARAMS
            ));
            add(new Variant(
                1,
                HANDKERCHIEF,
                DEFAULT_VARIANT_PARAMS
            ));

        }}
    );
}
