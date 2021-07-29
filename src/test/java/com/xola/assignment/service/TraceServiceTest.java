package com.xola.assignment.service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.xola.assignment.dto.InputDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
public class TraceServiceTest {

    private TraceService traceService;
    private JsonMapper mapper;

    @Before
    public void init() {
        traceService = new TraceService();
        mapper = new JsonMapper();
    }

    @Test
    public void shouldTestTraceMovement() throws IOException {
        InputDto inputDto = mapper.readValue(this.getClass().getClassLoader().getResource("input.json"), InputDto.class);
        String result = traceService.traceMovement(inputDto);

        String expectedRs = "OOOXX\n" +
                "OOXXX\n" +
                "OXXXX\n" +
                "OXOXO\n" +
                "OOOOO\n";
        Assert.assertEquals(expectedRs, result);
    }
}