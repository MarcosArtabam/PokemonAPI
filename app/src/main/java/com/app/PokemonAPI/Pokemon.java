package com.app.PokemonAPI;

import java.util.List;

public class Pokemon{

  private Integer num;
  private String name;
  private List<PokemonType> types;
  private String url;

  String type;
  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getNum() {
    String[] urlPartes = url.split("/");
    return Integer.parseInt(urlPartes[urlPartes.length - 1]);
  }

  public void setNum(int num) {
    this.num = num;
  }
}

