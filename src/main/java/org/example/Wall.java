package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

interface Structure {
  // zwraca dowolny element o podanym kolorze
  Optional<Block> findBlockByColor(String color);

  // zwraca wszystkie elementy z danego materiału
  List<Block> findBlocksByMaterial(String material);

  //zwraca liczbę wszystkich elementów tworzących strukturę
  int count();
}

public class Wall implements Structure {
  private List<Block> blocks;
  //Start of my code------------------------------------------------------------
  private int count;
  public Wall() {
    blocks = new ArrayList<>();
  }

  public List<Block> getBlocks() {
    return blocks;
  }

  public void addBlock(String color, String material) {

    Block block = new Block() {
      @Override
      public String getColor() {
        return color;
      }

      @Override
      public String getMaterial() {
        return material;
      }
    };

    blocks.add(block);
    count += 1;
  }

  public void addBlock(Block... elements) {

    Block compositeBlock = new CompositeBlock() {
      private final List<Block> compositeBlockList = Arrays.stream(elements).toList();

      @Override
      public List<Block> getBlocks() {
        return compositeBlockList;
      }

      @Override
      public String getColor() {

        StringBuilder s = new StringBuilder();
        for (Block block : compositeBlockList) {
          s.append(block.getColor()).append(" ");
        }

        return s.toString();
      }

      @Override
      public String getMaterial() {

        StringBuilder m = new StringBuilder();
        for (Block block : compositeBlockList) {
          m.append(block.getMaterial()).append(" ");
        }

        return m.toString();
      }
    };

    blocks.add(compositeBlock);
    count += elements.length;
  }


  //Overrides by Structure--------------------------------------------
  @Override
  public Optional<Block> findBlockByColor(String color) {
    return blocks.stream().filter(block -> block.getColor().equals(color)).findFirst();
  }

  @Override
  public List<Block> findBlocksByMaterial(String material) {
    return blocks.stream().filter(block -> block.getMaterial().equals(material)).toList();
  }

  @Override
  public int count() {
    return count;
  }
  //End---------------------------------
}

interface Block {
  String getColor();

  String getMaterial();
}

interface CompositeBlock extends Block {
  List<Block> getBlocks();
}
