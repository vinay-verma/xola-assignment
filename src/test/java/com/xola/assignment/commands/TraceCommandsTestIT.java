package com.xola.assignment.commands;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class TraceCommandsTestIT {

    @Autowired
    private Shell shell;

    @Test
    public void shouldTestTrace() {
        Map<String, MethodTarget> commands = shell.listCommands();
        MethodTarget methodTarget = commands.get("trace");
        Assert.assertNotNull(methodTarget);
        Assert.assertEquals(methodTarget.getGroup(), "Trace Commands");
        Assert.assertEquals(methodTarget.getHelp(), "Trace with input json file");
        Assert.assertEquals(
                methodTarget.getMethod(),
                ReflectionUtils.findMethod(TraceCommands.class, "trace", String.class)
        );
        Assert.assertTrue(methodTarget.getAvailability().isAvailable());
        String path = this.getClass().getClassLoader().getResource("input.json").getPath();
        String expectedRs = "OOOXX\n" +
                "OOXXX\n" +
                "OXXXX\n" +
                "OXOXO\n" +
                "OOOOO\n";
        Assert.assertEquals(expectedRs, shell.evaluate(new Input() {
            public String rawText() {
                return "trace " + path;
            }
            public List<String> words() {
                return Arrays.asList("trace", path);
            }
        }));
    }
}