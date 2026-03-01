package com.santiagomarin.entities;

public enum StockMovementReason {

	PURCHASE,    // entrada por compra a proveedor
    SALE,        // salida por venta
    RETURN,      // devolución de cliente
    ADJUSTMENT,  // ajuste manual de inventario
    DAMAGE       // merma o producto dañado
}
