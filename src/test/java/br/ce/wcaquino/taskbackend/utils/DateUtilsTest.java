package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void validarDataFuturaComSucesso() {
		
		//pré-condição	
	  
		
		//ação
		LocalDate data = LocalDate.now().plusDays(1);
		
		
		//resultado esperado
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
	
	@Test
	public void validarDataAtualComSucesso() {	
	  
		
		//ação
		LocalDate data = LocalDate.now();
		
		
		//resultado esperado
		Assert.assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
	
	
	@Test
	public void validarDataPassadaComErro() {
		
		//pré-condição	
	  
		
		//ação
		LocalDate data = LocalDate.now().minusDays(1);
		
		
		//resultado esperado
		Assert.assertFalse(DateUtils.isEqualOrFutureDate(data));
	}
}
