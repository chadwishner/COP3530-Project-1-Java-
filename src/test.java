public class test {

    /*
        This is a small set of testing code to make sure you are implementing our SparseInterface.
        You should be getting all "true" for the output

        This is NOT the full extent of our testing, but passing these cases are essential to
        passing our full tests.
    */

    public static void main(String[] args){
        SparseInterface myTest = new SparseMatrix();

        myTest.addElement(0, 0, 16);

        myTest.addElement(0, 1, 4);

        myTest.removeElement(0,1);

        String correctString = "0 0 16\n";

        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

        myTest.setSize(3);

        System.out.println("Size is 3: " + (myTest.getSize() == 3));

        correctString = "";

        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

        myTest.addElement(2,2,4);

        myTest.addElement(1,0,-3);

        correctString = "1 0 -3\n2 2 4\n";

        System.out.println("toString is correct: " + correctString.equals(myTest.toString()));

       System.out.println("The determinant is 0: " + (myTest.determinant() == 0));

        SparseInterface myMinor = myTest.minor(1,1);

        System.out.println("The (1,1) element of the minor is 4: " + (myMinor.getElement(1, 1) == 4));

       myTest.clear();
       
/*	THINGS TO TEST
 * 	1) different matrix sizes
 * 	2) adding elements in order
 * 	3) adding row out of order
 * 	4) adding col out of order
 * 	5) adding all out of order
 * 	6) using remove function
 * 	7) using minor function
 * 	8) using toString function
 * 	9) finding determinate
 * 
 * 	MAKE SURE YOU DO WEIRD TESTS OF NOT HAVING THE COL NUMBER IN THE PREVIOUSLY ADDED ROW. IE ADDING:
 * 		- add row 0 col 0, row 1, col 0
 * 	Check Against http://matrix.reshish.com/determinant.php
 *        
 */
       

       myTest.setSize(5);
       
   //row 0
       myTest.addElement(0, 0, 4);
       myTest.addElement(0, 1, 6);
       myTest.addElement(0, 2, 9); 
       myTest.addElement(0, 3, 10);      
       myTest.addElement(0, 4, 42); 
       
   //row 1
       myTest.addElement(1, 0, 3);
       myTest.addElement(1, 1, 5);
       myTest.addElement(1, 2, 2);
       myTest.addElement(1, 3, 31);
       myTest.addElement(1, 4, 78);
       
    //row 2
       myTest.addElement(2, 0, 4);
       myTest.addElement(2, 1, 1);
       myTest.addElement(2, 2, 8);
       myTest.addElement(2, 3, 91);
       myTest.addElement(2, 4, 34);

    //row 3
       myTest.addElement(3, 0, 4);
       myTest.addElement(3, 1, 1);
       myTest.addElement(3, 2, 8);
       myTest.addElement(3, 3, 91);
       myTest.addElement(3, 4, 34);
       
    //row 4
       myTest.addElement(4, 0, 4);
       myTest.addElement(4, 1, 1);
       myTest.addElement(4, 2, 8);
       myTest.addElement(4, 3, 91);
       myTest.addElement(4, 4, 34);
       
    //remove function test
       myTest.removeElement(1, 2);

    //to string
       System.out.println("\n\nThis is the Tests to check your own stuff");
       System.out.println("\nYour OG matrix: \n" + myTest.toString());
       System.out.println("Your minor matrix: \n" + myTest.minor(0,0).toString());
       System.out.println("The determinant is: " + myTest.determinant());

    }
}