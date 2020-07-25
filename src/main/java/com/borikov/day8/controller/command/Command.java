package com.borikov.day8.controller.command;

import java.util.Map;

public interface Command {
    Map<String, Object> execute(Map<String, String> data);
}
