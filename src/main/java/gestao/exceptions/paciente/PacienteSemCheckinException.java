package gestao.exceptions.paciente;

public class PacienteSemCheckinException extends RuntimeException{
    public PacienteSemCheckinException() {
        super("Paciente não deu entrada no hospital");
    }
}
