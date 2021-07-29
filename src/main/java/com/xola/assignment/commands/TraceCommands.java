package com.xola.assignment.commands;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.xola.assignment.dto.InputDto;
import com.xola.assignment.model.Region;
import com.xola.assignment.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;

@ShellComponent
public class TraceCommands {
	private static final String TRACE_HELP = "trace [parameter] [parameter value] \n" +
			"--file      mandatory param to mention input file (use -f for short)";


	@Autowired
	private TraceService traceService;

	@ShellMethod(value = "Trace with input json file", group = "Trace Commands")
	public String trace(
			@ShellOption(value = {"-f", "--file"}, help = TRACE_HELP) String file
	) throws Exception {
		File inputJsonFile = new File(file);
		boolean exists = inputJsonFile.exists();
		if(!exists){
			return "File not found on the path provided";
		} else if(!inputJsonFile.isFile()){
            return "Give a file as input, not directory";
        } else{
			JsonMapper mapper = new JsonMapper();
			InputDto input = mapper.readValue(inputJsonFile, InputDto.class);
			return traceService.traceMovement(input);
		}
	}
}
