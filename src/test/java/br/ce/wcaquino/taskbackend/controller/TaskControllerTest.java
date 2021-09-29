package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;


public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController taskController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		//preparacao
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		
		try {
		//acao
			taskController.save(todo);
		} catch (ValidationException e) {
		//validacao
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	
	}
	
	
	@Test
	public void naodeveSalvarTarefaComDataPassada() {
		//preparacao
		Task todo = new Task();
		todo.setTask("Estudar jenkins");
		todo.setDueDate(LocalDate.now().minusDays(1));
	
		try {
		//acao
			taskController.save(todo);
		} catch (ValidationException e) {
		//validacao
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		//preparacao
		Task todo = new Task();
		todo.setTask("Estudar jenkins");
		
		try {
		//acao
			taskController.save(todo);
		} catch (ValidationException e) {
		//validacao
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}
			
	
	@Test
	public void deveSalvarTarefa() throws ValidationException {
		//preparacao
		Task todo = new Task();
		todo.setTask("Estudar jenkins");
		todo.setDueDate(LocalDate.now());
		
		//acao
		taskController.save(todo);
		
		//validacao
		Mockito.verify(taskRepo).save(todo);
	}
}
