package productos.Productos_FS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import productos.Productos_FS.model.Productos;
import productos.Productos_FS.controller.ProductosController;
import productos.Productos_FS.service.ProductosService;


@SpringBootTest
class ProductosApplicationTests {
@Autowired
    private ProductosService productosService;
	@Test
	public void testCreacionProductos(){
		Productos producto = new Productos();

		//Prueba de getter & setter
		producto.setNombre("Laptop");
		producto.setPrecio(1500);
		producto.setCantidad(15);
		producto.setDescripcion("Lenovo KSG8700");

		assertEquals("Laptop", producto.getNombre());
        assertEquals(1500, producto.getPrecio());
        assertEquals(15, producto.getCantidad());
        assertNotNull(producto.getDescripcion());
	}
	@GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Long id){
        return productosService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
	}
	public ResponseEntity<EntityModel<Productos>> obtenerPorIdConHateoas(@PathVariable Long id) {
        ResponseEntity<Productos> respuestaOriginal = obtenerPorId(id); // Llama al método original
        
        if (respuestaOriginal.getStatusCode().is2xxSuccessful()) {
            Productos producto = respuestaOriginal.getBody();
            EntityModel<Productos> model = EntityModel.of(producto);
            model.add(linkTo(methodOn(ProductosController.class).obtenerPorIdConHateoas(id)).withSelfRel());
            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
}

