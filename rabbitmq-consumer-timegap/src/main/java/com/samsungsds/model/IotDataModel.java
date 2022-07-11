package com.samsungsds.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IotDataModel {

  public int id;
  public String message;
  public long date;
  
  public IotDataModel() {
  }
}