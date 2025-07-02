package com.papus.productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.papus.productos.model.Productos;

@SpringBootTest
class ProductosApplicationTests {

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
	
}
