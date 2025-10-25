package shojo.shopping.party.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shojo.shopping.party.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartyManagerTest {
    
    private PartyManager manager;
    
    @BeforeEach
    void setUp() {
        manager = new PartyManager();
    }
    
    @Test
    void testRegistrarPersonaValida() {
        // Given: el usuario elige "registrar participante"
        // When: introduce tipo=Persona, nombre="Ana Pérez", rol=Cliente
        Party persona = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        // Then: el sistema crea el participante y devuelve ID
        assertNotNull(persona);
        assertTrue(persona.getID() > 0);
        assertEquals("Ana Pérez", persona.getNombre());
        assertEquals("Persona", persona.getTipo());
        assertEquals(RoleType.CLIENTE, persona.getRol());
    }
    
    @Test
    void testRegistrarOrganizacionValida() {
        // Given: el usuario elige "registrar organización"
        // When: introduce tipo=Organización, nombre="Tech Corp", rol=Proveedor
        Party organizacion = manager.registrarOrganizacion("Tech Corp", RoleType.PROVEEDOR);
        
        // Then: el sistema crea el participante
        assertNotNull(organizacion);
        assertTrue(organizacion.getID() > 0);
        assertEquals("Tech Corp", organizacion.getNombre());
        assertEquals("Organizacion", organizacion.getTipo());
        assertEquals(RoleType.PROVEEDOR, organizacion.getRol());
    }
    
    @Test
    void testListarParticipantes() {
        // Given: existen 3 participantes
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        manager.registrarOrganizacion("Tech Corp", RoleType.PROVEEDOR);
        manager.registrarParty("Carlos", "Ruiz", RoleType.ADMINISTRADOR);
        
        // When: el usuario elige "listar"
        List<Party> participantes = manager.listarTodos();
        
        // Then: muestra los 3 registros
        assertEquals(3, participantes.size());
    }
    
    @Test
    void testBuscarPorIdExistente() {
        // Given: hay participantes registrados
        Party persona = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        int id = persona.getID();
        
        // When: busca por ID existente
        Party encontrado = manager.buscarPorId(id);
        
        // Then: devuelve el participante
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getID());
        assertEquals("Ana Pérez", encontrado.getNombre());
    }
    
    @Test
    void testBuscarPorIdInexistente() {
        // Given: el usuario tiene datos cargados
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        // When: busca ID=999 no existente
        Party encontrado = manager.buscarPorId(999);
        
        // Then: devuelve null
        assertNull(encontrado);
    }
    
    @Test
    void testBuscarPorNombreParcialCaseInsensitive() {
        // Given: existen "Carlos Ruiz" y "Carla Gómez"
        manager.registrarParty("Carlos", "Ruiz", RoleType.CLIENTE);
        manager.registrarParty("Carla", "Gómez", RoleType.PROVEEDOR);
        manager.registrarParty("Ana", "Pérez", RoleType.ADMINISTRADOR);
        
        // When: busca nombre="car" (case insensitive)
        List<Party> resultados = manager.buscarPorNombre("car");
        
        // Then: devuelve ambos registros que contienen "car"
        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carlos Ruiz")));
        assertTrue(resultados.stream().anyMatch(p -> p.getNombre().equals("Carla Gómez")));
    }
    
    @Test
    void testBuscarPorNombreNoEncontrado() {
        // Given: hay participantes registrados
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        // When: busca nombre que no existe
        List<Party> resultados = manager.buscarPorNombre("xyz");
        
        // Then: devuelve lista vacía
        assertTrue(resultados.isEmpty());
    }
    
    @Test
    void testBuscarPorNombreVacio() {
        // Given: hay participantes registrados
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        // When: busca con nombre vacío
        List<Party> resultados = manager.buscarPorNombre("");
        
        // Then: devuelve lista vacía
        assertTrue(resultados.isEmpty());
    }
    
    @Test
    void testBuscarPorNombreNull() {
        // Given: hay participantes registrados
        manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        
        // When: busca con nombre null
        List<Party> resultados = manager.buscarPorNombre(null);
        
        // Then: devuelve lista vacía
        assertTrue(resultados.isEmpty());
    }
    
    @Test
    void testIdAutoincrementable() {
        // Given: se registran múltiples participantes
        Party p1 = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        Party p2 = manager.registrarParty("Carlos", "Ruiz", RoleType.PROVEEDOR);
        Party p3 = manager.registrarOrganizacion("Tech Corp", RoleType.ADMINISTRADOR);
        
        // Then: los IDs son secuenciales
        assertTrue(p2.getID() > p1.getID());
        assertTrue(p3.getID() > p2.getID());
    }
    
    @Test
    void testRolesValidos() {
        // Test que los tres roles se pueden asignar correctamente
        Party cliente = manager.registrarParty("Ana", "Pérez", RoleType.CLIENTE);
        Party proveedor = manager.registrarParty("Carlos", "Ruiz", RoleType.PROVEEDOR);
        Party admin = manager.registrarParty("Luis", "García", RoleType.ADMINISTRADOR);
        
        assertEquals(RoleType.CLIENTE, cliente.getRol());
        assertEquals(RoleType.PROVEEDOR, proveedor.getRol());
        assertEquals(RoleType.ADMINISTRADOR, admin.getRol());
    }
}
