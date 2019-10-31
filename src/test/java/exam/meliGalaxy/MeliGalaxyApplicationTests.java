package exam.meliGalaxy;

import org.junit.Assert;
import org.junit.Test;

import exam.meliGalaxy.model.Coordenada;
import exam.meliGalaxy.service.SistemaMeteorologicoService;
import exam.meliGalaxy.service.SistemaMeteorologicoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = SistemaMeteorologicoServiceImpl.class)
public class MeliGalaxyApplicationTests {

	@Autowired
	SistemaMeteorologicoService sistemaMeteorologico;
	
	public MeliGalaxyApplicationTests() {
		super();
		sistemaMeteorologico = new SistemaMeteorologicoServiceImpl();
	}

	@Test
	public void test_es_recta_funciona() {
		Coordenada c1 = new Coordenada(0.0, 0.0);
		Coordenada c2 = new Coordenada(1.0, 1.0);
		Coordenada c3 = new Coordenada(2.0, 2.0);
		Coordenada c4 = new Coordenada(2.5, 12.25);
		boolean esRecta = sistemaMeteorologico.esRecta(c1, c2, c3);
		boolean noEsRecta = !sistemaMeteorologico.esRecta(c1, c2, c4);
		Assert.assertTrue(esRecta);
		Assert.assertTrue(noEsRecta);
	}
	
	@Test
	public void test_planeta_y_sol_alineados(){
		Coordenada c1 = new Coordenada(3.0, 3.0);
		Coordenada c2 = new Coordenada(1.0, 1.0);
		Coordenada c3 = new Coordenada(2.0, 2.0);
		Coordenada c4 = new Coordenada(0.0, 0.0);
		
		boolean esRecta = sistemaMeteorologico.esRecta(c1, c2, c3);
		boolean esRectaConSol = sistemaMeteorologico.esRecta(c1, c2, c4);
		Assert.assertTrue(esRecta);
		Assert.assertTrue(esRectaConSol);
	}
	
	@Test
	public void test_planeta_alineados_no_sol(){
		Coordenada c1 = new Coordenada(0.0, 0.0);
		Coordenada c2 = new Coordenada(1.0, 1.0);
		Coordenada c3 = new Coordenada(2.0, 2.0);
		Coordenada c4 = new Coordenada(2.5, 12.25);
		
		boolean esRecta = sistemaMeteorologico.esRecta(c1, c2, c3);
		boolean noEsRecta = !sistemaMeteorologico.esRecta(c1, c2, c4);
		Assert.assertTrue(esRecta);
		Assert.assertTrue(noEsRecta);
	}
	
	@Test
	public void test_planeta_forman_triangulo_sin_sol(){
		Coordenada c1 = new Coordenada(0.0, 4.0);
		Coordenada c2 = new Coordenada(1.0, 7.0);
		Coordenada c3 = new Coordenada(2.0, 2.0);
		Coordenada c4 = new Coordenada(2.5, 12.25);
		
		boolean esRecta = sistemaMeteorologico.esRecta(c1, c2, c3);
		Assert.assertFalse(esRecta);
		
		boolean esTrianguloConPunto = sistemaMeteorologico.formanUnTrianguloYPuntoPertenece(c4, c1, c2, c3);
		Assert.assertFalse(esTrianguloConPunto);
	}
	
	@Test
	public void test_planeta_forman_triangulo_con_sol(){
		Coordenada c1 = new Coordenada(0.0, 0.0);
		Coordenada c2 = new Coordenada(6.0, 0.0);
		Coordenada c3 = new Coordenada(3.0, 3.0);
		Coordenada c4 = new Coordenada(3.0, 2.0);
		
		boolean esRecta = sistemaMeteorologico.esRecta(c1, c2, c3);
		Assert.assertFalse(esRecta);
		
		boolean esTrianguloConPunto = sistemaMeteorologico.formanUnTrianguloYPuntoPertenece(c4, c1, c2, c3);
		Assert.assertTrue(esTrianguloConPunto);
	}

}
