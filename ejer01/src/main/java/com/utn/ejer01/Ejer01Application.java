package com.utn.ejer01;

		import com.utn.ejer01.entidades.*;
		import com.utn.ejer01.enums.Estado;
		import com.utn.ejer01.enums.FormaDePago;
		import com.utn.ejer01.enums.TipoEnvio;
		import com.utn.ejer01.enums.TipoProducto;
		import com.utn.ejer01.repositorios.*;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.annotation.Bean;
		import java.text.SimpleDateFormat;
		import java.util.Date;


@SpringBootApplication
public class Ejer01Application {
	@Autowired
	public RubroRepository rubroRepository;
	@Autowired
	public ProductoRepository productoRepository;
	@Autowired
	public DomicilioRepository domicilioRepository;
	@Autowired
	public ClienteRepository clienteRepository;
	@Autowired
	public PedidoRepository pedidoRepository;
	@Autowired
	public DetallePedidoRepository detallePedidoRepository;
	@Autowired
	public FacturaRepository facturaRepository;


	public static void main(String[] args) {
		SpringApplication.run(Ejer01Application.class, args);

	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo, DomicilioRepository domicilioRepo, PedidoRepository pedidoRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			Date fecha = formatoFecha.parse(fechaString);

/*El método builder() se genera automáticamente por Lombok y te permite crear una instancia de Cliente.Builder.
Luego, puedes encadenar llamadas a los métodos setters generados automáticamente para establecer los valores de los atributos de la clase.
Finalmente, build() crea la instancia de la clase Cliente con los valores proporcionados.*/

			//crear instancia rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Comida")
					.build();
			//crear instancias de productos
			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Cheddar")
					.precioVenta(2000.00)
					.precioCompra(1200.00)
					.stockActual(50)
					.stockMin(3)
					.unidadMedida("unidad1")
					.receta("receta1")
					.tipo(TipoProducto.INSUMO)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Baccon")
					.precioVenta(2200.00)
					.precioCompra(1500.00)
					.stockActual(32)
					.stockMin(4)
					.unidadMedida("unidad2")
					.receta("receta2")
					.tipo(TipoProducto.INSUMO)
					.build();
			//agregar productos al rubro
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			//guardar rubro
			rubroRepository.save(rubro1);
			//Crear instancia Cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Frida")
					.apellido("Kallo")
					.email("fridakallo@mail")
					.telefono("telefono1")
					.build();
			//Crear instancia domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("San Martin")
					.numero("6538")
					.localidad("Ciudad")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Epidio")
					.numero("1223")
					.localidad("Guaymallen")
					.build();
			//agregar domicilios a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			//Crear Instancia Detalle Pedido
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.00)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2200.00)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(60000.00)
					.build();

			//Crear Instancia Pedido
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.total(6200.00)
					.estado(Estado.ENTREGADO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(6000.00)
					.estado(Estado.ENTREGADO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.build();
			//Crear instancias de factura
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(5800.00)
					.numero(1)
					.descuento(400.00)
					.formaDePago(FormaDePago.EFECTIVO)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(5400.00)
					.numero(2)
					.descuento(600.00)
					.formaDePago(FormaDePago.MERCADOPAGO)
					.build();
			//Agregar detalles al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			//agregar pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			//vincular el detalle pedido con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			//vincular factura con pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//guardar cliente
			clienteRepository.save(cliente1);

//recuperar objeto rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}
			//recuperar Cliente desde la base de Datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}

		};
	}
}
