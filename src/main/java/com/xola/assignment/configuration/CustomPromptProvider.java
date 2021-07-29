package com.xola.assignment.configuration;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

        @Override
        public AttributedString getPrompt() {
                return new AttributedString("cdc-shell:>");
        }
}