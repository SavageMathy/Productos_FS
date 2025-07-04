package productos.Productos_FS.controller;

//import de swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//import del proyecto
import productos.Productos_FS.model.Productos;
import productos.Productos_FS.service.ProductosService;

//import spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//import java
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductosController {


    
    @Autowired
    private ProductosService productosService;

    //GET - Listar
    @GetMapping
    public ResponseEntity<List<Productos>> listar() {
        return ResponseEntity.ok(productosService.findAll());
    }

    @Operation(
        summary = "Obtener un producto por su ID",
        description = "retorna un producto con su enlace HATEOAS",
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
        }
    )
    
    //GET - buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerPorId(@PathVariable Long id){
        return productosService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/hateoas/{id}")
    @Parameter(description = "ID del producto", example = "1", required = true)
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
    //POST - crear
    @PostMapping
    public ResponseEntity<Productos> crear(@RequestBody Productos productos) {
        return ResponseEntity.ok(productosService.save(productos));
    }
    
    //PUT - Actualzizar 
    @PutMapping("/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long id, @RequestBody Productos productos) {
        return productosService.findById(id).map(u -> {
            productos.setId(id);
            return ResponseEntity.ok(productosService.save(productos));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productosService.findById(id).isPresent()) {
            productosService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}