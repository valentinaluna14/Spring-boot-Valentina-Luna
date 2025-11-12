package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoService.obtenerTodos().stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(producto -> ResponseEntity.ok(convertirAResponseDTO(producto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Filtrar productos por categoría")
    public List<ProductoResponseDTO> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return productoService.obtenerPorCategoria(categoria).stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        Producto productoGuardado = productoService.crearProducto(producto);
        return ResponseEntity.status(201).body(convertirAResponseDTO(productoGuardado));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto completo")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(convertirAResponseDTO(productoActualizado));
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar stock de un producto")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id, @Valid @RequestBody ActualizarStockDTO stockDTO) {
        Producto producto = productoService.actualizarStock(id, stockDTO.getStock());
        return ResponseEntity.ok(convertirAResponseDTO(producto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }
}
