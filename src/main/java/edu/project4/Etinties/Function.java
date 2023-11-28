package edu.project4.Etinties;

import java.util.List;

public record Function(Colors color,
                       Coefficients coefficients,
                       PostTransformation postTransformation,
                       List<Variant> variants
) {
}
