package com.excilys.computerdatabase.webservice;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.excilys.computerdatabase.mapping.query.QueryMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IService;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;

@RestController
public class ComputerRestController {

	@Autowired
	IService<Computer> computerService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ResponseEntity<List<Computer>> list(@RequestParam Map<String, String> params, ModelMap model) {
		List<Computer> computers = computerService.list(QueryMapper.fromMaptoQuery(params));
		if (computers.isEmpty()) {
			return new ResponseEntity<List<Computer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Computer>>(computers, HttpStatus.OK);
	}

	@RequestMapping(value = "/computer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<Computer> find(@PathVariable("id") long id) {
		Computer computer = ((ComputerServiceImpl) computerService).find(id);
		if (computer == null) {
			return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Computer>(computer, HttpStatus.OK);
	}

	@RequestMapping(value = "/computer/", method = RequestMethod.POST)
	public ResponseEntity<Void> createComputer(@RequestBody Computer computer, UriComponentsBuilder ucBuilder) {
		if (((ComputerServiceImpl) computerService).find(computer.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		((ComputerServiceImpl) computerService).createComputer(computer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/computer/{id}").buildAndExpand(computer.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/computer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Computer> updateComputer(@PathVariable("id") Long id, @RequestBody Computer computer) {
		Computer currentComputer = ((ComputerServiceImpl) computerService).find(id);

		if (currentComputer == null) {
			return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
		}

		currentComputer.setName(computer.getName());
		currentComputer.setIntroduced(computer.getIntroduced());
		currentComputer.setDiscontinued(computer.getDiscontinued());
		currentComputer.setManufacturer(computer.getManufacturer());

		((ComputerServiceImpl) computerService).updateComputer(currentComputer);
		return new ResponseEntity<Computer>(currentComputer, HttpStatus.OK);
	}

	@RequestMapping(value = "/computer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Computer> deleteComputer(@PathVariable("id") long id) {
		Computer computer = ((ComputerServiceImpl) computerService).find(id);
		if (computer == null) {
			return new ResponseEntity<Computer>(HttpStatus.NOT_FOUND);
		}

		((ComputerRestController) computerService).deleteComputer(id);
		return new ResponseEntity<Computer>(HttpStatus.NO_CONTENT);
	}

}