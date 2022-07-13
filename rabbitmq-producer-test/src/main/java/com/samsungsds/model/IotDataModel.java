package com.samsungsds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IotDataModel {

  private int id;
  private String message;

  // public IotDataModel(int i, String message) {
  //   this.id = i;
  //   this.message = message;
  // }
}