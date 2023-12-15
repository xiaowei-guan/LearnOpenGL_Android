package com.guanxw.android.learnopengl;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Lists {
  @SerializedName("name")
  private String name;
  @SerializedName("items")
  private List<Item> items = new ArrayList<Item>();

  public List<Item> getItems() { return items; }
}

