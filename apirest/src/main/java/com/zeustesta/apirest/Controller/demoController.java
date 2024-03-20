package com.zeustesta.apirest.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class demoController {
  
  @GetMapping("/inicio")
  public String welcome() {
      return "¡Bienvenido a la demo!";
  }
  
}
