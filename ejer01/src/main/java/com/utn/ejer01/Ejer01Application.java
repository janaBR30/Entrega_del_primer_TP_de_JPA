package com.utn.ejer01;

		import com.utn.ejer01.entidades.*;
		import com.utn.ejer01.enums.Estado;
		import com.utn.ejer01.enums.TipoEnvio;
		import com.utn.ejer01.repositorios.ClienteRepository;
		import com.utn.ejer01.repositorios.DomicilioRepository;
		import com.utn.ejer01.repositorios.PedidoRepository;
		import jakarta.annotation.PostConstruct;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.CommandLineRunner;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.context.ConfigurableApplicationContext;
		import org.springframework.context.annotation.Bean;

		import java.text.SimpleDateFormat;
		import java.util.Date;


@SpringBootApplication
public class Ejer01Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	PedidoRepository pedidoRepository;


	public static void main(String[] args) {
		SpringApplication.run(Ejer01Application.class, args);

	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo,DomicilioRepository domicilioRepo, PedidoRepository pedidoRepo) {
		return args -> {System.out.println("-----------------ESTOY FUNCIONANDO---------");

			SimpleDateFormat formatoFecha= new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			Date fecha= formatoFecha.parse(fechaString);

/*El método builder() se genera automáticamente por Lombok y te permite crear una instancia de Cliente.Builder.
Luego, puedes encadenar llamadas a los métodos setters generados automáticamente para establecer los valores de los atributos de la clase.
Finalmente, build() crea la instancia de la clase Cliente con los valores proporcionados.*/

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Calle 1")
					.numero("501")
					.localidad("Mendoza")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Calle 2")
					.numero("123")
					.localidad("Guaymallén")
					.build();

			Pedido pedido1 = Pedido.builder()
					.TIPODEENVIO(TipoEnvio.DELIVERY)
					.ESTADO(Estado.INICIADO)
					.total(5200.50)
					.fecha(fecha)
					.factura(Factura.builder().build())
					.build();
			Pedido pedido2 = Pedido.builder()
					.TIPODEENVIO(TipoEnvio.RETIRA)
					.ESTADO(Estado.PREPARACION)
					.total(10000.50)
					.fecha(fecha)
					.factura(Factura.builder().build())
					.build();

			// Crear instancia de Cliente y agregar domicilios
			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("123456")
					.email("juanperez@gmail.com")
					.build();

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);
			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);

			// Guardar el objeto CLiente en la base de datos
			clienteRepository.save(cliente);


			// Recuperar el objeto CLiente desde la base de datos
			Cliente clienteRecuperada = clienteRepository.findById(cliente.getId()).orElse(null);


			if (clienteRecuperada != null) {
				System.out.println("Nombre: " + clienteRecuperada.getNombre());
				System.out.println("Apellido: " + clienteRecuperada.getApellido());
				System.out.println("Telefono: " + clienteRecuperada.getTelefono());
				System.out.println("Email: " + clienteRecuperada.getEmail());
				clienteRecuperada.mostrarDomicilios();
				clienteRecuperada.mostrarPedidos();
			}

		};

	}

}
