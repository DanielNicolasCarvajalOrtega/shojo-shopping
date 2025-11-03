package party.repository;
import party.model.Organization;
import party.model.Party;
import party.model.Person;
import party.model.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;


public class PartyRepositoryTest {
    private PartyRepository repository;
    private Party persona01;
    private Party persona02;
    private Party organizacion01;

    @BeforeEach
    public void setUp() {
        repository = new PartyRepository();

        // USAMOS LOS ATRIBUTOS DE LA CLASE
        persona01 = new Person("Jose", "Herrera", RoleType.CLIENTE);
        organizacion01 = new Organization("El Aguila Curico", RoleType.PROVEEDOR);
        persona02 = new Person("Roberto", "HABARCA", RoleType.CLIENTE);

        repository.registrarParty(persona01);
        repository.registrarParty(organizacion01);
        repository.registrarParty(persona02);
    }

    @Test
    public void registrarPartyDevuelveTrueYQuedaAccesiblePorId() {
        Party nueva = new Person("Daniel", "Soto", RoleType.PROVEEDOR);
        boolean registro = repository.registrarParty(nueva);
        int idGenerado = nueva.getId();
        Optional<Party> buscada = repository.buscarPorId(idGenerado);

        assertEquals("Daniel",buscada.get().getNombre(),
                "EL NOMBRE DEBE SER 'Daniel'");

        assertTrue(buscada.isPresent(),
                "LA PARTY NUEVA DEBE QUEDAR ACCESIBLE POR ID");
        assertEquals(idGenerado, buscada.get().getId(),
                "EL ID DE LA PARTY GUARDADA DEBE COINCIDIR");
        assertEquals("Daniel", buscada.get().getNombre(),
                "EL NOMBRE DEBE SER 'Daniel'");
        assertEquals(RoleType.PROVEEDOR, buscada.get().getRol(),
                "EL ROL DEBE SER PROVEEDOR");
    }
    @Test
    void ingresamosPartyPersonDuplicadoPeroRechazaLaInsercion() {
        // Arrange (Preparar)
        Party nuevo = new Person("Julio", "Iglesias", RoleType.CLIENTE);
        Party duplicado = new Person("Julio", "Iglesias", RoleType.CLIENTE);

        // Act (Actuar)
        boolean registroNuevo = repository.registrarParty(nuevo);
        boolean registroDuplicado = repository.registrarParty(duplicado);

        // Assert (Afirmar)
        assertTrue(registroNuevo, "Debe registrar correctamente un nuevo Party");
        assertFalse(registroDuplicado, "NO debe registrar un duplicado por nombre");

        // Verificar que solo hay 1 participante
        assertEquals(4, repository.obtenerTodosMutables().size(),
                "Solo debe haber 4 participante registrado y 1 Julio Iglesias");
    }

    @Test
    void permiteRegistrarPersonasConNombresDiferentesAunqueApellidoIgual() {
        // Arrange
        Party julio = new Person("Julio", "Iglesias", RoleType.CLIENTE);
        Party enrique = new Person("Enrique", "Iglesias", RoleType.CLIENTE);

        // Act
        boolean registroJulio = repository.registrarParty(julio);
        boolean registroEnrique = repository.registrarParty(enrique);

        long cantidadIglesias = repository.obtenerTodosMutables().stream()
                .filter(p -> p.getApellido().contains("Iglesias"))
                .count();

        // Assert
        assertTrue(registroJulio, "Debe registrar a Julio");
        assertTrue(registroEnrique, "Debe registrar a Enrique (apellido igual pero nombre diferente)");
        assertEquals(2,cantidadIglesias);
    }
    @Test
    public void registraPartyConNullDevuelveFalse() {
        boolean resultado = repository.registrarParty(null);

        assertFalse(resultado,
                "SI PARTY ES NULL DEVUELVE FALSE");
        //verifica que no agregaste basura
        assertEquals(3, repository.obtenerTodosMutables().size(),
                "NO DEBIÓ CAMBIAR EL TAMAÑO DEL REPO");
    }

    @Test
    public void obtenerTodosLosMutablesDevuelveCopiaYNoListaInterna() {
        List<Party> copia = repository.obtenerTodosMutables();

        // DEBE TENER LOS 3 DEL SETUP()
        assertEquals(3, copia.size(),
                "DEBEMOS TENER 3 INICIALES");

        // POR SI SE ROMPE LA COPIA
        copia.clear();

        // DATA INTERNA INTACTA
        assertEquals(3, repository.obtenerTodosMutables().size(),
                "LIMPIAR LA COPIA NO DEBERIA VACIAR LA DATA INTERNA");
    }

    @Test
    public void obtenerPartysMutableTambienDevuelveCopiaIndependiente() {
        List<Party> copia = repository.obtenerPartysMutable();

        assertEquals(3, copia.size(),
                "LA COPIA INICIAL DE obtenerPartysMutable DEBE TENER 3");

        copia.remove(0);

        assertEquals(3, repository.obtenerPartysMutable().size(),
                "REMOVER DE LA COPIA NO DEBE AFECTAR EL ESTADO INTERNO");
    }

    @Test
    public void existePorIdDevuelveTrueSiElIdExiste() {
        int id1 = persona01.getId();
        int id2 = persona02.getId();
        int id3 = organizacion01.getId();

        assertTrue(repository.existePorId(id1),
                "DEBE EXISTIR persona01");
        assertTrue(repository.existePorId(id2),
                "DEBE EXISTIR persona02");
        assertTrue(repository.existePorId(id3),
                "DEBE EXISTIR organizacion01");
    }

    @Test
    public void existePorIdDevuelveFalseSiNoExiste() {
        assertFalse(repository.existePorId(800),
                "UN ID QUE NO ESTA REGISTRADO DEBE DAR FALSE");
    }

    @Test
    public void buscarPorIdDevuelveOptionalPartySiExiste() {
        int idOrg = organizacion01.getId();

        Optional<Party> resultado = repository.buscarPorId(idOrg);

        assertTrue(resultado.isPresent(),
                "DEBE ENCONTRAR LA ORGANIZACION POR SU ID");

        Party encontrada = resultado.get();

        assertEquals("El Aguila Curico", encontrada.getNombre(),
                "EL NOMBRE DEBE SER 'El Aguila Curico'");
        assertEquals(RoleType.PROVEEDOR, encontrada.getRol(),
                "EL ROL DEBE SER PROVEEDOR");
    }

    @Test
    public void buscarPorIdDevuelveEmptySiNoExiste() {
        Optional<Party> resultado = repository.buscarPorId(123456);

        assertTrue(resultado.isEmpty(),
                "SI EL ID NO EXISTE DEBE VOLVER Optional.empty()");
    }



}




