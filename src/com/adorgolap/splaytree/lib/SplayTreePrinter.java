package com.adorgolap.splaytree.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.adorgolap.splaytree.SplayNode;

public class SplayTreePrinter {

    public static <T extends Comparable<?>> void printNode(SplayNode root) {
        int maxLevel = SplayTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<SplayNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || SplayTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        SplayTreePrinter.printWhitespaces(firstSpaces);

        List<SplayNode> newNodes = new ArrayList<SplayNode>();
        for (SplayNode node : nodes) {
            if (node != null) {
            	if(node.parent == null)
            	{
            		System.out.print(node.key+" p: null");
            	}else
            	{
//            		System.out.print(node.key+" p "+ node.parent.key);
            		System.out.print(node.key);
            	}
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            SplayTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                SplayTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    SplayTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    SplayTreePrinter.printWhitespaces(1);

                SplayTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    SplayTreePrinter.printWhitespaces(1);

                SplayTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(SplayNode node) {
        if (node == null)
            return 0;

        return Math.max(SplayTreePrinter.maxLevel(node.left), SplayTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}