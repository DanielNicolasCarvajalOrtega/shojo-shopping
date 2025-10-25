package shojo.shopping.party.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shojo.shopping.party.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para PartyRepository.
 */
class PartyRepositoryTest {

    private PartyRepository repository;

    @BeforeEach
    void setUp() {
        Party.resetContadorId();
        repository = new PartyRepository();
    }

    @Test
    void testCreateParty() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        
        boolean resultado = repository.createParty(persona);
        
        assertTrue(resultado);
        assertEquals(1, repository.findAllParty().size());
    }

    @Test
    void testCreatePartyNull() {
        boolean resultado = repository.createParty(null);
        
        assertFalse(resultado);
        assertEquals(0, repository.findAllParty().size());
    }

    @Test
    void testFindAllParty() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        Organization org = new Organization("Empresa ABC", RoleType.PROVEEDOR);
        
        repository.createParty(persona);
        repository.createParty(org);
        
        List<Party> todos = repository.findAllParty();
        
        assertEquals(2, todos.size());
    }

    @Test
    void testFindAllPartyVacio() {
        List<Party> todos = repository.findAllParty();
        assertTrue(todos.isEmpty());
    }

    @Test
    void testFindPartyByIdExistente() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        repository.createParty(persona);
        
        Party encontrado = repository.findPartyById(persona.getId());
        
        assertNotNull(encontrado);
        assertEquals(persona.getId(), encontrado.getId());
        assertEquals("Ana Pérez", encontrado.getNombre());
    }

    @Test
    void testFindPartyByIdInexistente() {
        Party encontrado = repository.findPartyById(999);
        assertNull(encontrado);
    }

    @Test
    void testFindPartyByNameCaseInsensitive() {
        Person p1 = new Person("Carlos", "Ruiz", RoleType.CLIENTE);
        Person p2 = new Person("Carla", "Gómez", RoleType.PROVEEDOR);
        repository.createParty(p1);
        repository.createParty(p2);
        
        List<Party> resultados = repository.findPartyByName("car");
        
        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carlos Ruiz")));
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carla Gómez")));
    }

    @Test
    void testFindPartyByNameUnResultado() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        Organization org = new Organization("Empresa ABC", RoleType.PROVEEDOR);
        repository.createParty(persona);
        repository.createParty(org);
        
        List<Party> resultados = repository.findPartyByName("empresa");
        
        assertEquals(1, resultados.size());
        assertEquals("Empresa ABC", resultados.get(0).getNombre());
    }

    @Test
    void testFindPartyByNameSinResultados() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        repository.createParty(persona);
        
        List<Party> resultados = repository.findPartyByName("xyz");
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testFindPartyByNameVacio() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        repository.createParty(persona);
        
        List<Party> resultados = repository.findPartyByName("");
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testFindPartyByNameNull() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        repository.createParty(persona);
        
        List<Party> resultados = repository.findPartyByName(null);
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testUpdateParty() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        repository.createParty(persona);
        
        persona.setNombre("Ana María Pérez");
        boolean resultado = repository.updateParty(persona);
        
        assertTrue(resultado);
        Party actualizado = repository.findPartyById(persona.getId());
        assertEquals("Ana María Pérez", actualizado.getNombre());
    }

    @Test
    void testUpdatePartyNull() {
        boolean resultado = repository.updateParty(null);
        assertFalse(resultado);
    }

    @Test
    void testUpdatePartyInexistente() {
        Person persona = new Person("Ana", "Pérez", RoleType.CLIENTE);
        // No se agrega al repositorio
        
        boolean resultado = repository.updateParty(persona);
        
        assertFalse(resultado);
    }
}
