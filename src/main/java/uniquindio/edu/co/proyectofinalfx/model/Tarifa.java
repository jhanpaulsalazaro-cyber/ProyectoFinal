package uniquindio.edu.co.proyectofinalfx.model;

public record Tarifa(TipoCliente tipoCliente, double valor) {

    public Tarifa {
        if (tipoCliente == null) {
            throw new IllegalArgumentException(
                    "El tipo de cliente no puede ser nulo."
            );
        }

        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(
                    "El valor de la tarifa debe ser un número válido y no negativo."
            );
        }
    }
}
