package org.example;

public class Main {
  public static void main(String[] args) {
    Wall wall = new Wall();

    wall.addBlock("red", "brick");
    wall.addBlock("green", "concrete");
    wall.addBlock("yellow", "concrete");

    wall.addBlock(wall.getBlocks().get(1), wall.getBlocks().get(0), wall.getBlocks().get(1));
    wall.addBlock(wall.getBlocks().get(1), wall.getBlocks().get(2), wall.getBlocks().get(1));
    wall.addBlock(wall.getBlocks().get(0), wall.getBlocks().get(1), wall.getBlocks().get(0));
    System.out.println("Total amount of created blocks: " + wall.count());

    CompositeBlock compositeBlock = (CompositeBlock) wall.getBlocks().get(3);
    System.out.println(compositeBlock.getColor());

    System.out.println(wall.getBlocks().get(5).getColor());

    wall.addBlock(wall.getBlocks().get(3), wall.getBlocks().get(4), wall.getBlocks().get(5));
    System.out.println(wall.getBlocks().get(6).getMaterial());

    System.out.println(wall.findBlockByColor("red"));
    System.out.println(wall.findBlocksByMaterial("concrete").size());

  }
}