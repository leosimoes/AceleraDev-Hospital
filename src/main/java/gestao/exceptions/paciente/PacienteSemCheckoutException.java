package gestao.exceptions.paciente;

public class PacienteSemCheckoutException extends RuntimeException {
    public PacienteSemCheckoutException() {
        super("Paciente já está em atendimento");
    }
}
