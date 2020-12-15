package br.com.gfe.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfe.status.StatusDTO;
import br.com.gfe.status.StatusService;

@RestController
public class StatusRestController {

	@Autowired
	StatusService statusService;

	@GetMapping(value = "/api/status")
	public StatusDTO ok() {
		return statusService.getStatus();
	}
}
