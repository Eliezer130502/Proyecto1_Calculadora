package com.example.calculadora;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde el frontend
public class CalculatorController {
    private final List<String> history = new LinkedList<>();

    @GetMapping("/api/calculate")
    public String calculate(@RequestParam String operation, @RequestParam double a, @RequestParam double b) {
        double result;
        String operationStr;

        switch (operation) {
            case "add":
                result = a + b;
                operationStr = "suma";
                break;
            case "subtract":
                result = a - b;
                operationStr = "resta";
                break;
            case "multiply":
                result = a * b;
                operationStr = "multiplicación";
                break;
            case "divide":
                if (b == 0) {
                    return "Error: División por cero";
                }
                result = a / b;
                operationStr = "división";
                break;
            default:
                return "Operación no válida";
        }

        String resultMessage = "El resultado de la " + operationStr + " es: " + result;
        addToHistory(resultMessage);
        return resultMessage;
    }

    @GetMapping("/api/history")
    public List<String> getHistory() {
        return history;
    }

    private void addToHistory(String entry) {
        if (history.size() >= 10) {
            history.remove(0);
        }
        history.add(entry);
    }
}
