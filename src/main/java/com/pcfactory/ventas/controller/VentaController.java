package com.pcfactory.ventas.controller;

import com.pcfactory.ventas.dto.VentaProductoRequest;
import com.pcfactory.ventas.entity.VentaProducto;
import com.pcfactory.ventas.service.VentaProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VentaController {

    @Autowired
    private VentaProductoService ventaProductoService;

    @PostMapping("/ventas")
    public ResponseEntity<List<VentaProducto>> registrarVenta(
            @RequestBody
            @NotEmpty(message = "La venta debe contener al menos un producto")
            List<@NotNull @Valid VentaProductoRequest> request
    ) {
        List<VentaProducto> productosGuardados =
                ventaProductoService.registrarVenta(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productosGuardados);
    }
}
