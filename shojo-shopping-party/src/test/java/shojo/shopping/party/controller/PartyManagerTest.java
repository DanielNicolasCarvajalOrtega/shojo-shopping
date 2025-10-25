package shojo.shopping.party.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shojo.shopping.party.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para PartyManager.
 */
class PartyManagerTest {

    private PartyManager manager;

    @BeforeEach
    void setUp() {
        Party.resetContadorId();
        manager = new PartyManager();
    }

    @Test
    void testRegistrarPersona() {
        Party persona = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        assertNotNull(persona);
        assertEquals(1, persona.getId());
        assertEquals("Ana Pérez", persona.getNombre());
        assertEquals(RoleType.CLIENTE, persona.getRol());
        assertEquals("Persona", persona.getTipo());
    }

    @Test
    void testRegistrarOrganizacion() {
        Party organizacion = manager.registrarOrganizacion("Empresa ABC", RoleType.PROVEEDOR);
        
        assertNotNull(organizacion);
        assertEquals(1, organizacion.getId());
        assertEquals("Empresa ABC", organizacion.getNombre());
        assertEquals(RoleType.PROVEEDOR, organizacion.getRol());
        assertEquals("Organizacion", organizacion.getTipo());
    }

    @Test
    void testBuscarPorIdExistente() {
        manager.registrarParty("Carlos", "Ruiz", RoleType.CLIENTE);
        
        Party resultado = manager.buscarPorId(1);
        
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Carlos Ruiz", resultado.getNombre());
    }

    @Test
    void testBuscarPorIdInexistente() {
        Party resultado = manager.buscarPorId(999);
        assertNull(resultado);
    }

    @Test
    void testListarTodos() {
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        manager.registrarOrganizacion("Empresa XYZ", RoleType.PROVEEDOR);
        
        List<Party> todos = manager.listarTodos();
        
        assertEquals(2, todos.size());
    }

    @Test
    void testListarTodosVacio() {
        List<Party> todos = manager.listarTodos();
        assertTrue(todos.isEmpty());
    }

    @Test
    void testBuscarPorNombreCaseInsensitive() {
        manager.registrarParty("Carlos", "Ruiz", RoleType.CLIENTE);
        manager.registrarParty("Carla", "Gómez", RoleType.PROVEEDOR);
        
        List<Party> resultados = manager.buscarPorNombre("car");
        
        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carlos Ruiz")));
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carla Gómez")));
    }

    @Test
    void testBuscarPorNombreUnResultado() {
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        manager.registrarOrganizacion("Empresa ABC", RoleType.PROVEEDOR);
        
        List<Party> resultados = manager.buscarPorNombre("empresa");
        
        assertEquals(1, resultados.size());
        assertEquals("Empresa ABC", resultados.get(0).getNombre());
    }

    @Test
    void testBuscarPorNombreSinResultados() {
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        List<Party> resultados = manager.buscarPorNombre("xyz");
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testBuscarPorNombreVacio() {
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        List<Party> resultados = manager.buscarPorNombre("");
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testBuscarPorNombreNull() {
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        List<Party> resultados = manager.buscarPorNombre(null);
        
        assertTrue(resultados.isEmpty());
    }

    @Test
    void testObtenerTotalParty() {
        assertEquals(0, manager.obtenerTotalParty());
        
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        assertEquals(1, manager.obtenerTotalParty());
        
        manager.registrarOrganizacion("Empresa ABC", RoleType.PROVEEDOR);
        assertEquals(2, manager.obtenerTotalParty());
    }

    @Test
    void testIdAutoincremental() {
        Party p1 = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        Party p2 = manager.registrarOrganizacion("Empresa ABC", RoleType.PROVEEDOR);
        Party p3 = manager.registrarParty("Carlos", "Ruiz", RoleType.ADMINISTRADOR);
        
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
        assertEquals(3, p3.getId());
    }
}
