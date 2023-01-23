package com.tsolas.technico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResult<T> {

  private T data;
  private int errorCode;
  private String description;

}
