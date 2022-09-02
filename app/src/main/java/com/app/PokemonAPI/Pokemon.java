package com.app.PokemonAPI;

public class Pokemon {

  private int num;
  private String name;
  private String url;

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

  public int getNum() {
    String[] urlPartes = url.split("/");
    return Integer.parseInt(urlPartes[urlPartes.length - 1]);
  }

  public void setNum(int num) {
    this.num = num;
  }
}
