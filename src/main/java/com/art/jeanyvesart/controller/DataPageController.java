// src/main/java/com/site/web/PageController.java
package com.art.jeanyvesart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataPageController {
  @GetMapping("/privacy/delete")
  public String deletePage() { return "privacy-delete"; }

  @GetMapping("/privacy/delete/done")
  public String deleteDone() { return "privacy-delete-done"; }

  @GetMapping("/privacy/delete/error")
  public String deleteError() { return "privacy-delete-error"; }
}
