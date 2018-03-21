package com.indra.projetoejb3.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.indra.ejb3.model.Person;
import com.indra.projetoejb3.dao.PersonDao;

/**
 * 
 * Servlet implementation class ServletMain
 */
@WebServlet(urlPatterns = "/InserePessoa")
public class ServletMain extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String cpf = req.getParameter("cpf");
		String rg = req.getParameter("rg");
		String email = req.getParameter("email");
		String telefone = req.getParameter("telefone");
		String dbd = req.getParameter("dbd");

		Person person = new Person();
		person.setNome(name);
		person.setCpf(cpf);
		person.setRg(rg);
		person.setEmail(email);
		person.setTelefone(telefone);
		Calendar birthdayDate = converteToCalendar(dbd);
		person.setDbd(birthdayDate);
		
		PersonDao personDao = new PersonDao();
		try {
			personDao.save(person);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private Calendar converteToCalendar(String data) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();

		try {
			cal.setTime(sdf.parse(data));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return cal;
	}
}
