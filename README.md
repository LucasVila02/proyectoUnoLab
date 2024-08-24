-Dominios separados: Se crearon dominios para todas las clases, organizando el código en paquetes que reflejan la estructura y lógica del negocio.

-Atributo importado en la clase abstracta Producto: Se agregó el atributo importado en la clase abstracta Producto, aunque la clase Limpieza no pueda tener productos importados. 
Esto se validó en la clase Limpieza para que no pueda ser true. Esta decisión se tomó pensando en la escalabilidad del proyecto, permitiendo la adición futura de más productos importados.
Se agrega el atributo precioVenta en donde se ve reflejado los cambios segun el porcentaje de ganancia, si o no importado, y si tiene porcentaje de descuento.

-Nueva clase ProductosComestibles: Se creó la clase ProductosComestibles, que hereda de Producto y sirve como clase base para productos comestibles (Bebida, Envasados). 
El objetivo es validar la fecha de vencimiento y el porcentaje de ganancia, evitando así la repetición de código en las clases hijas.

-Validación en los constructores: Se implementaron validaciones en todos los constructores para evitar la creación de productos con valores numéricos negativos o cero. 
Además, no se permite la compra de productos envasados que estén vencidos; estos productos deben tener al menos 30 días de margen con respecto a la fecha actual para ser adquiridos. 
Según la lógica del negocio, los productos vencidos deben ser retirados por los repositores para que no estén disponibles para la venta. 
Sin embargo, en la práctica, un producto vencido podría ser vendido si se escanea en la caja, ya que no se valida si el producto está vencido en el momento de la venta.

-Métodos en la clase Tienda: De acuerdo con las consignas del trabajo práctico (TP), se crearon los métodos comprarProducto, venderProducto, y mostrarBalanceTienda dentro de la clase Tienda. 
Cada vez que se llamen estos métodos, se mostrará el detalle de las transacciones realizadas.
