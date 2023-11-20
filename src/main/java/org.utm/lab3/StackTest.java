package org.utm.lab3;

public class StackTest {
    public static void main(String[] args) {

        Stack<Integer> arrayStack = new ArrayStack<>();
        testStack(arrayStack);


        Stack<Integer> linkedListStack = new LinkedListStack<>();
        testStack(linkedListStack);


        Stack<Integer> dynamicArrayStack = new DynamicArrayStack<>();
        testStack(dynamicArrayStack);
    }

    private static void testStack(Stack<Integer> stack) {
        System.out.println("Testing " + stack.getClass().getSimpleName());

        // Push elements
        for (int i = 1; i <= 5; i++) {
            System.out.println("Pushing: " + i);
            stack.push(i);
        }

        // Peek element
        System.out.println("Peek: " + stack.peek());

        // Pop elements
        while (!stack.isEmpty()) {
            System.out.println("Popping: " + stack.pop());
        }

        System.out.println();
    }
}

